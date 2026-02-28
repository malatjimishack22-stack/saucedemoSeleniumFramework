package com.saucedemo.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.saucedemo.reports.ExtentReportGenerator;
import com.saucedemo.reports.ExtentManager;
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
        ExtentManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().fail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportGenerator.flush();
    }
}
