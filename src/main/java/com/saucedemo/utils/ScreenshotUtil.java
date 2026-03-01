package com.saucedemo.utils;

import com.saucedemo.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ScreenshotUtil {

    private ScreenshotUtil() {}

    public static String captureBase64() {

        try {
            if (DriverManager.getDriver() == null) {
                return null;
            }

            return ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BASE64);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}