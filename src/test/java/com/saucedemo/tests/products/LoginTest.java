package com.saucedemo.tests.products;

import com.saucedemo.base.BaseTest;
import com.saucedemo.driver.DriverManager;
import com.saucedemo.reports.ReportLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private final Logger log = LogManager.getLogger(this);

    @Test
    public void loginTest() {
        log.info("Login page loaded");
        ReportLogger.info("Navigated to login page");
        //ReportLogger.pass("Login successful");
        //ReportLogger.fail(new Exception("Login failed"));
    }
}
