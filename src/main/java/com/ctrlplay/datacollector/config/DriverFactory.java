package com.ctrlplay.datacollector.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }

    private static void createDriver() {

        WebDriverManager.chromedriver().setup();

        System.setProperty("webdriver.chrome.driver", "C:/ROBOS/chromedriver_win32/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();

        String userProfile = System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\User Data\\CtrlPlayDataCollector";

        options.addArguments("user-data-dir=" + userProfile);

        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);

        prefs.put("plugins.always_open_pdf_externally", true);
        options.addArguments("--disable-pdf-viewer");
        options.addArguments("--incognito");

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}