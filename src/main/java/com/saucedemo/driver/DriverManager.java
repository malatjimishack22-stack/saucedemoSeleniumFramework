package com.saucedemo.driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private DriverManager() {}

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    static void setDriver(WebDriver driverRef) {
        driver.set(driverRef);
    }

    static void unload() {
        driver.remove(); // MUST remove to prevent leaks in parallel execution
    }
}