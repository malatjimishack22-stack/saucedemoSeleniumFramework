package com.saucedemo.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.saucedemo.config.ConfigManager;
import com.saucedemo.enums.ScreenshotMode;
import com.saucedemo.reports.ExtentReportGenerator;
import com.saucedemo.reports.ExtentManager;
import com.saucedemo.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // Create ExtentTest from ExtentReports
        ExtentTest test = ExtentReportGenerator.getInstance()
                .createTest(result.getMethod().getMethodName());

        // Set in ThreadLocal for parallel safety
        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        if (ConfigManager.getInstance().getScreenshotMode() == ScreenshotMode.ALL) {

            String base64 = ScreenshotUtil.captureBase64();

            if (base64 != null) {
                ExtentManager
                        .getTest()
                        .addScreenCaptureFromBase64String(base64);
            }
        }

        ExtentManager
                .getTest()
                .log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        if (ConfigManager.getInstance().getScreenshotMode() == ScreenshotMode.ALL
                || ConfigManager.getInstance().getScreenshotMode() == ScreenshotMode.FAILED) {

            String base64 = ScreenshotUtil.captureBase64();

            if (base64 != null) {
                ExtentManager
                        .getTest()
                        .addScreenCaptureFromBase64String(base64);
            }
        }

        ExtentManager
                .getTest()
                .log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportGenerator.flush();
    }
}
