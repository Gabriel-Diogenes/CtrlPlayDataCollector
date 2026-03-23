package com.ctrlplay.datacollector.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class ClientesPage {

    public List<WebElement> listarrDetalheCliente(WebDriver driver){
        List<WebElement> result = new ArrayList<>();

        List<WebElement> rows = driver.findElements(
                By.cssSelector("#clients-table-render tbody tr")
        );

        for (WebElement row : rows) {
            List<WebElement> buttons = row.findElements(By.cssSelector("td:last-child a"));

            if (buttons.size() >= 1) {
                result.add(buttons.get(0));
            }
        }

        return result;
    }

    public List<WebElement> listarPedidosCliente(WebDriver driver){
        List<WebElement> result = new ArrayList<>();

        List<WebElement> rows = driver.findElements(
                By.cssSelector("#clients-table-render tbody tr")
        );

        for (WebElement row : rows) {
            List<WebElement> buttons = row.findElements(By.cssSelector("td:last-child a"));

            if (buttons.size() >= 1) {
                result.add(buttons.get(2)); // terceiro
            }
        }
        return result;
    }
    public int quantidadeClientesNaPagina(WebDriver driver){
        List<WebElement> rows = driver.findElements(By.cssSelector("#clients-table-render tbody tr"));
        return  rows.size();
    }
}

