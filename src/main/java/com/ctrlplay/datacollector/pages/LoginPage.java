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
        driver.navigate().to("https://www.asaas.com/login/auth?customerSignUpOriginChannel=HOME_SALES_PARTNER");
    }
    public void colocarUsuário(){
        if(SeleniumUtils.foiEncontradoElementos(driver, By.cssSelector(""))){

        }
    }
}
