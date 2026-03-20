package com.ctrlplay.datacollector.services;

import com.ctrlplay.datacollector.pages.LoginPage;

public class LoginService {

    private LoginPage loginPage;

    public LoginService(){
        this.loginPage = new LoginPage();
    }

    public void login(){
        loginPage.acessarPagina();
    }

}
