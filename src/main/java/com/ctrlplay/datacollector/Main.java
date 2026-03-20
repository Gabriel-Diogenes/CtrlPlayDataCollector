package com.ctrlplay.datacollector;

import com.ctrlplay.datacollector.config.DriverFactory;
import com.ctrlplay.datacollector.services.LoginService;

public class Main {
    public static void main(String[] args) {

        try{
            LoginService loginService = new LoginService();
            loginService.login();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.quitDriver();
        }
    }
}