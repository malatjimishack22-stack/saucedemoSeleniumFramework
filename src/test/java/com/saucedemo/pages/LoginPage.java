package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By errorMsg = By.cssSelector("h3[data-test='error']");

    public void login(String username, String password){
        type(usernameField,username);
        type(passwordField,password);
        click(loginBtn);
    }

    public String getErrorMsg() {
        return getText(errorMsg);
    }
}
