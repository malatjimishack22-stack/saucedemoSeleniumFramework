package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.dataproviders.TestDataProvider;
import com.saucedemo.driver.DriverManager;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.reports.ReportLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertTrue;

public class LoginDataDrivenJSONTests extends BaseTest {

    private static final Logger log = LogManager.getLogger(LoginDataDrivenJSONTests.class);

    @Test(dataProvider = "loginJsonData", dataProviderClass = TestDataProvider.class)
    public void loginTest(Map<String, String> data) {

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        log.info("Executing login test for {}", data.get("expectedOutcome"));
        ReportLogger.info("Executing login test for " + data.get("expectedOutcome"));

        loginPage.login(data.get("username"), data.get("password"));

        if ("SUCCESS".equalsIgnoreCase(data.get("expectedOutcome"))) {
            assertTrue(DriverManager.getDriver().getCurrentUrl().contains("inventory"),
                    "User should be navigated to inventory page"
            );
        } else {
            assertTrue(loginPage.getErrorMsg().contains(data.get("expectedMessage")),
                    "Expected error message was not displayed"
            );
        }
    }
}