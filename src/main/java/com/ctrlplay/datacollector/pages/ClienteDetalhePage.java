package com.ctrlplay.datacollector.pages;

import com.ctrlplay.datacollector.config.DriverFactory;
import com.ctrlplay.datacollector.model.Cliente;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClienteDetalhePage {

    private WebDriver driver;

    public ClienteDetalhePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void buscarInformacoesDetalheCliente(WebDriver driver, Cliente cliente){
        cliente.setNomeResponsavel(driver.findElement(By.cssSelector("body > div.wrapper > div > div > div.box.box-primary > div > div > div:nth-child(3) > p")).getText());
        cliente.setCpf(driver.findElement(By.cssSelector("body > div.wrapper > div > div > div.box.box-primary > div > div > div:nth-child(4) > p")).getText());
        cliente.setTelefone(driver.findElement(By.cssSelector("body > div.wrapper > div > div > div.box.box-primary > div > div > div:nth-child(5) > p")).getText());
        cliente.setEmail(driver.findElement(By.cssSelector("body > div.wrapper > div > div > div.box.box-primary > div > div > div:nth-child(6) > p")).getText());
        cliente.setEndereco(driver.findElement(By.cssSelector("body > div.wrapper > div > div > div.box.box-primary > div > div > div:nth-child(7) > p")).getText());
        cliente.setNumero(driver.findElement(By.cssSelector("body > div.wrapper > div > div > div.box.box-primary > div > div > div:nth-child(8) > p")).getText());
        cliente.setBairro(driver.findElement(By.cssSelector("body > div.wrapper > div > div > div.box.box-primary > div > div > div:nth-child(9) > p")).getText());
        cliente.setCidade(driver.findElement(By.cssSelector("body > div.wrapper > div > div > div.box.box-primary > div > div > div:nth-child(10) > p")).getText());
        cliente.setEstado(driver.findElement(By.cssSelector("body > div.wrapper > div > div > div.box.box-primary > div > div > div:nth-child(11) > p")).getText());
        cliente.setCep(driver.findElement(By.cssSelector("body > div.wrapper > div > div > div.box.box-primary > div > div > div:nth-child(12) > p")).getText());
        cliente.setComplemento(driver.findElement(By.cssSelector("body > div.wrapper > div > div > div.box.box-primary > div > div > div:nth-child(13) > p")).getText());
    }

    public void voltarParaPaginaCliente(WebDriver driver) {
        driver.navigate().back();
    }
}
