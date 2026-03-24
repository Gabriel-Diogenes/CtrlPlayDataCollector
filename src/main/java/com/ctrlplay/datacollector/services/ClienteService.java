package com.ctrlplay.datacollector.services;

import com.ctrlplay.datacollector.model.Cliente;
import com.ctrlplay.datacollector.pages.ClienteDetalhePage;
import com.ctrlplay.datacollector.pages.ClientePedidoPage;
import com.ctrlplay.datacollector.pages.ClientesPage;
import com.ctrlplay.datacollector.util.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ctrlplay.datacollector.repository.ControleRepository;
import java.util.Set;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private ClientesPage clientesPage;
    private ClienteDetalhePage clienteDetalhePage;
    private ClientePedidoPage clientePedidoPage;

    public ClienteService(){
        this.clientesPage = new ClientesPage();
        this.clienteDetalhePage = new ClienteDetalhePage();
        this.clientePedidoPage = new ClientePedidoPage();
    }

    private String gerarChave(Cliente cliente) {

        String cpf = cliente.getCpf() != null ? cliente.getCpf().trim() : "";
        String filho = cliente.getNomeFilhoCliente() != null
                ? cliente.getNomeFilhoCliente().trim().toLowerCase()
                : "";
        String parcela = cliente.getParcela() != null
                ? cliente.getParcela().trim()
                : "";

        return cpf + "_" + filho + "_" + parcela;
    }

    public List<Cliente> buscarInformacoesCliente(WebDriver driver) throws InterruptedException {

        List<Cliente> listaClientes = new ArrayList<>();
        boolean temProximaPagina = true;
        ControleRepository controleRepo = new ControleRepository();
        Set<String> controle = controleRepo.carregar();
        while(temProximaPagina){
            int total = clientesPage.quantidadeClientesNaPagina(driver);
            for (int i = 0; i < total; i++) {

                List<WebElement> detalhesCliente = clientesPage.listarrDetalheCliente(driver);

                Cliente clienteBase = new Cliente();

                detalhesCliente.get(i).click();
                Thread.sleep(2000);

                clienteDetalhePage.buscarInformacoesDetalheCliente(driver, clienteBase);
                clienteDetalhePage.voltarParaPaginaCliente(driver);

                if (clienteBase.getNomeResponsavel() == null || clienteBase.getNomeResponsavel().isEmpty()) {
                    driver.navigate().to("https://loja.ctrlplay.com.br/schools/1/clients");
                    continue;
                }

                List<WebElement> pedidoCliente = clientesPage.listarPedidosCliente(driver);

                pedidoCliente.get(i).click();
                Thread.sleep(3000);

                String guia = SeleniumUtils.trocarParaNovaGuia(driver);
                Thread.sleep(3000);

                List<Cliente> clientesPedidos = clientePedidoPage.processarPedidos(driver, clienteBase);

                for (Cliente clientePedido : clientesPedidos) {

                    String chave = gerarChave(clientePedido);

                    if (!controle.contains(chave)) {

                        listaClientes.add(clientePedido);
                        controle.add(chave);

                    } else {
                        System.out.println("Ignorando duplicado: " + chave);
                    }
                }

                SeleniumUtils.fecharGuiaEVoltar(driver, guia);
            }
            List<WebElement> botaoProximo = driver.findElements(By.cssSelector("a.page-link[rel='next']"));

            if (botaoProximo.isEmpty()) {
                temProximaPagina = false;
            } else {
                botaoProximo.get(0).click();
                Thread.sleep(3000);
            }
        }
        controleRepo.salvar(controle);
        return listaClientes;
    }
}
