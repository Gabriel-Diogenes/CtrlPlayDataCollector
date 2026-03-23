package com.ctrlplay.datacollector;

import com.ctrlplay.datacollector.config.DriverFactory;
import com.ctrlplay.datacollector.model.Cliente;
import com.ctrlplay.datacollector.services.ClienteService;
import com.ctrlplay.datacollector.services.LoginService;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getDriver();;

        try{
            LoginService loginService = new LoginService();
            loginService.login(driver);

            ClienteService clienteService = new ClienteService();
            List<Cliente> clientes = clienteService.buscarInformacoesCliente(driver);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.quitDriver();
        }
    }
}