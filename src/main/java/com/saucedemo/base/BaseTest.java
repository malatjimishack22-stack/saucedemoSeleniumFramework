package com.saucedemo.base;

import com.saucedemo.config.ConfigManager;
import com.saucedemo.driver.DriverFactory;
import com.saucedemo.enums.BrowserType;
import org.testng.annotations.*;

@Listeners({
        com.saucedemo.listeners.TestListener.class
})
public class BaseTest {

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional String browser) {
        // Use browser parameter if provided; else get from config.properties
        String browserToUse = (browser != null) ? browser : ConfigManager.getInstance().getBrowser();

        DriverFactory.initDriver(BrowserType.valueOf(browserToUse));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
