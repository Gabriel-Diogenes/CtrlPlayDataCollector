package com.ctrlplay.datacollector.pages;

import com.ctrlplay.datacollector.model.Cliente;
import com.ctrlplay.datacollector.util.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ClientePedidoPage {

    public boolean verficaSePedidoExiste(WebDriver driver){
        if(driver.findElement(By.cssSelector("#dataTableBuilder_info")).getText().equals("Mostrando 0 até 0 de 0 registros")){
            System.out.println("Não possui nenhum pedido");
            return false;
        }
        return true;
    }

    public List<Cliente> processarPedidos(WebDriver driver, Cliente clienteBase) {

        if(!verficaSePedidoExiste(driver)) return new ArrayList<>();

        List<Cliente> lista = new ArrayList<>();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#dataTableBuilder tbody")));

        List<WebElement> linhas = driver.findElements(By.cssSelector("#dataTableBuilder tbody tr"));
        int total = linhas.size();

        for (int i = 0; i < total; i++) {

        List<WebElement> linhasAtualizadas = driver.findElements(By.cssSelector("#dataTableBuilder tbody tr"));
        WebElement linha = linhasAtualizadas.get(i);

            WebElement botaoDetalhes = linha.findElement(
                    By.cssSelector("td:nth-child(7) a[title='Detalhes']")
            );

            botaoDetalhes.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table.table")));

            Cliente clienteAtual;

            if (total > 1) {
                clienteAtual = clienteBase.clone();
            } else {
                clienteAtual = clienteBase;
            }

            buscarParcelaAtual(driver, clienteAtual);

            if (clienteAtual.getValorPago() != null && !clienteAtual.getValorPago().isEmpty()) {
                lista.add(clienteAtual);
            }

            driver.navigate().back();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#dataTableBuilder tbody")));

        }

        return lista;
    }

    public String pegarNomedoFilho(WebDriver driver){
        List<WebElement> tabelas = driver.findElements(By.cssSelector("table.table"));

        WebElement tabela = tabelas.get(3);

        List<WebElement> linhas = tabela.findElements(By.cssSelector("tr.pdf-enrollment-list"));

        WebElement primeiraLinha = linhas.get(0);

        List<WebElement> colunas = primeiraLinha.findElements(By.tagName("td"));

        String nomeAluno = colunas.get(0).getText().trim();
        return nomeAluno;
    }

    public void buscarParcelaAtual(WebDriver driver, Cliente cliente) {
        if(!SeleniumUtils.foiEncontradoElementos(driver,By.cssSelector("#wrapper > div.main.container.mx-auto > div >" +
                                                                        " div.container.tex" +
                                                                        "t-center.mt-5.mb-3 > h1"),5)) return;

        List<WebElement> tabelas = driver.findElements(By.cssSelector("table.table"));

        if (tabelas.size() < 2) return;

        WebElement segundaTabela = tabelas.get(1);
        List<WebElement> linhas = segundaTabela.findElements(By.tagName("tr"));

        for (int i = linhas.size() - 1; i > 0; i--) {

            List<WebElement> colunas = linhas.get(i).findElements(By.tagName("td"));

            if (colunas.size() < 7) continue;

            String parcela = colunas.get(0).getText().trim();
            String valor = colunas.get(1).getText().trim();
            String vencimento = colunas.get(3).getText().trim();
            String status = colunas.get(4).getText().trim().toLowerCase();
            String pagoEm = colunas.get(5).getText().trim();
            if(status.contains("ancelado")) return;
            if(status.contains("encida")) return;
            if (status.equals("pago")) {

                cliente.setParcela(parcela);
                cliente.setValorPago(valor);
                cliente.setDataVencimento(vencimento);
                cliente.setDataPagamento(pagoEm);
                cliente.setNomeFilhoCliente(pegarNomedoFilho(driver));

                break;
            }
        }
    }
}
