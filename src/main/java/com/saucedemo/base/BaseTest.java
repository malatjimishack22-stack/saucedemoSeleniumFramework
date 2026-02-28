package com.saucedemo.base;

import com.saucedemo.config.ConfigManager;
import com.saucedemo.driver.DriverFactory;
import com.saucedemo.driver.DriverManager;
import com.saucedemo.enums.BrowserType;
import org.testng.annotations.*;

@Listeners({
        com.saucedemo.listeners.TestListener.class
})
public class BaseTest {

    private final ConfigManager config = ConfigManager.getInstance();

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional String browser) {
        // Use browser parameter if provided; else get from config.properties
        String browserToUse = (browser != null) ? browser : config.getBrowser();

        DriverFactory.initDriver(BrowserType.valueOf(browserToUse));
        DriverManager.getDriver().get(config.getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
