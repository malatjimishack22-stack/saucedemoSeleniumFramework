package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.driver.DriverManager;
import com.saucedemo.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTest {

    @Test
    public void validLogin() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login("standard_user","secret_sauce");
        assertTrue(DriverManager.getDriver().getCurrentUrl().contains("inventory"));
    }

    @Test
    public void invalidLogin() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login("wrong","secret_sauce");
        assertTrue(loginPage.getErrorMsg().contains("Username and password do not match any user in this service"));
    }

    @Test
    public void missingUsername() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login("","secret_sauce");
        assertTrue(loginPage.getErrorMsg().contains("Epic sadface: Username is required"));
    }

    @Test
    public void missingPassword() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login("wrong","");
        assertTrue(loginPage.getErrorMsg().contains("Epic sadface: Password is required"));
    }
}
