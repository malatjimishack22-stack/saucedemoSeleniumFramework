package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.driver.DriverManager;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.reports.ReportLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTest {

    private final Logger log = LogManager.getLogger(this);

    @Test
    public void validLogin() {
        log.info("Navigated to login page");
        ReportLogger.info("Navigated to login page");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login("standard_user","secret_sauce");
    }

    @Test
    public void invalidLogin() {
        log.info("Navigated to login page");
        ReportLogger.info("Navigated to login page");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login("wrong","secret_sauce");
        assertTrue(loginPage.getErrorMsg().contains("Username and password do not match any user in this service"));
    }

    @Test
    public void missingUsername() {
        log.info("Navigated to login page");
        ReportLogger.info("Navigated to login page");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login("","secret_sauce");
        assertTrue(loginPage.getErrorMsg().contains("Epic sadface: Username is required"));
    }

    @Test
    public void missingPassword() {
        log.info("Navigated to login page");
        ReportLogger.info("Navigated to login page");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login("wrong","");
        assertTrue(loginPage.getErrorMsg().contains("Epic sadface: Password is required"));
    }
}
