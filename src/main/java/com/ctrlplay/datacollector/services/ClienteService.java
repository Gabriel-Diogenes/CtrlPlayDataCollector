package com.ctrlplay.datacollector.services;

import com.ctrlplay.datacollector.model.Cliente;
import com.ctrlplay.datacollector.pages.ClienteDetalhePage;
import com.ctrlplay.datacollector.pages.ClientePedidoPage;
import com.ctrlplay.datacollector.pages.ClientesPage;
import com.ctrlplay.datacollector.util.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public List<Cliente> buscarInformacoesCliente(WebDriver driver) throws InterruptedException {

        List<Cliente> listaClientes = new ArrayList<>();
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

            listaClientes.addAll(clientesPedidos);

            SeleniumUtils.fecharGuiaEVoltar(driver, guia);
        }

        return listaClientes;
    }
}
