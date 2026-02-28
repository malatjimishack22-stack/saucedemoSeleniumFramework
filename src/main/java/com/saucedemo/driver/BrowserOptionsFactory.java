package com.saucedemo.driver;

import com.saucedemo.config.ConfigManager;
import com.saucedemo.enums.BrowserType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.MutableCapabilities;

public class BrowserOptionsFactory {

    private static final ConfigManager config = ConfigManager.getInstance();

    //Get browser options based on browser type and config
    public static MutableCapabilities getOptions(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                return getChromeOptions();
            case FIREFOX:
                return getFirefoxOptions();
            case EDGE:
                return getEdgeOptions();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }
    }

    //Chrome options
    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (config.isHeadless()) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        return options;
    }

    //Firefox options
    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (config.isHeadless()) {
            options.addArguments("--headless");
        }
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        return options;
    }

    //Edge options
    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        if (config.isHeadless()) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--start-maximized");
        return options;
    }
}
