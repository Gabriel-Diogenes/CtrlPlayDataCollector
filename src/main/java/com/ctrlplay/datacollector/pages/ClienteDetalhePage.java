package com.ctrlplay.datacollector.pages;

import com.ctrlplay.datacollector.config.DriverFactory;
import org.openqa.selenium.WebDriver;

public class ClienteDetalhePage {

    private WebDriver driver;

    public ClienteDetalhePage() {
        this.driver = DriverFactory.getDriver();
    }
}
