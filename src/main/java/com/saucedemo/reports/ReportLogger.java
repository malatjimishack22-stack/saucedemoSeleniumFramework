package com.saucedemo.reports;

import com.aventstack.extentreports.ExtentTest;

/**
 * Helper utility to log test steps to ExtentReport.
 * Thread-safe via ExtentManager's ThreadLocal.
 */
public final class ReportLogger {

    private ReportLogger() {
        // private constructor to prevent instantiation
    }

    public static void info(String message) {
        ExtentTest test = ExtentManager.getTest();
        if (test != null) {
            test.info(message);
        }
    }

    public static void pass(String message) {
        ExtentTest test = ExtentManager.getTest();
        if (test != null) {
            test.pass(message);
        }
    }

    public static void fail(String message) {
        ExtentTest test = ExtentManager.getTest();
        if (test != null) {
            test.fail(message);
        }
    }

    public static void fail(Throwable throwable) {
        ExtentTest test = ExtentManager.getTest();
        if (test != null) {
            test.fail(throwable);
        }
    }

    public static void warning(String message) {
        ExtentTest test = ExtentManager.getTest();
        if (test != null) {
            test.warning(message);
        }
    }
}
