package com.saucedemo.driver;

import com.saucedemo.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static void initDriver(BrowserType browserType) {
        MutableCapabilities options = BrowserOptionsFactory.getOptions(browserType);
        WebDriver driver;
        switch (browserType) {
            case CHROME:
                driver = new ChromeDriver((ChromeOptions) options);
                break;
            case FIREFOX:
                driver = new FirefoxDriver((FirefoxOptions) options);
                break;
            case EDGE:
                driver = new EdgeDriver((EdgeOptions) options);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }
        DriverManager.setDriver(driver);
    }

    public static void quitDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}