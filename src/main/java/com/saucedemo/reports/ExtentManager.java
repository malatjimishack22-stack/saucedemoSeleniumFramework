package com.saucedemo.reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {

    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void setTest(ExtentTest extentTest){
        test.set(extentTest);
    }

    public static ExtentTest getTest(){
        return test.get();
    }

    static void unload(){
        test.remove();
    }
}
