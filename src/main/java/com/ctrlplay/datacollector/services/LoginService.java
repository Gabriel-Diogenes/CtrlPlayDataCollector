package com.ctrlplay.datacollector.services;

import com.ctrlplay.datacollector.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginService {

    private LoginPage loginPage;

    public LoginService(){
        this.loginPage = new LoginPage();
    }

    public void login(WebDriver driver) throws InterruptedException {

        loginPage.acessarPagina(driver);
        loginPage.colocarUsuário(driver);
        loginPage.colocarSenha(driver);
        loginPage.logar(driver);
        loginPage.clicarNaAbaCliente(driver);
    }
}
