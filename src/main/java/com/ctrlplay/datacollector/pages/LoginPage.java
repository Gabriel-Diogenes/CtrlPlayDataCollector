package com.ctrlplay.datacollector.pages;

import com.ctrlplay.datacollector.util.SeleniumUtils;
import com.ctrlplay.datacollector.config.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage() {
        this.driver = DriverFactory.getDriver();
    }

    public void acessarPagina(){
        driver.navigate().to("https://loja.ctrlplay.com.br/login");
    }
    public void colocarUsuário(){
        if(SeleniumUtils.foiEncontradoElementos(driver, By.cssSelector(""))){

        }
    }
}
