package com.ctrlplay.datacollector.pages;
import com.ctrlplay.datacollector.config.DriverFactory;
import org.openqa.selenium.WebDriver;


public class ClientesPage {

    private WebDriver driver;

    public ClientesPage() {
        this.driver = DriverFactory.getDriver();
    }
}
