package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import com.saucedemo.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.saucedemo.elements.Element;

public class LoginPage extends BasePage {

    private final Element usernameField = new Element("Username Field", By.id("user-name"));

    private final Element passwordField = new Element("Password Field", By.id("password"));

    private final Element loginBtn = new Element("Login Button", By.id("login-button"));

    private final Element errorMsg = new Element("Error Message", By.cssSelector("h3[data-test='error']"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password){
        openUrl(ConfigManager.getInstance().getBaseUrl());
        type(usernameField,username);
        type(passwordField,password);
        click(loginBtn);
    }

    public String getErrorMsg() {
        return getText(errorMsg);
    }
}
