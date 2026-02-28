package com.saucedemo.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Paths;

public final class ExtentReportGenerator {

    private static ExtentReports extent;

    private ExtentReportGenerator() {
        // private constructor to prevent instantiation
    }

    // Thread-safe singleton
    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = Paths.get(System.getProperty("user.dir"),
                    "test-output", "ExtentReport.html").toString();

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            // Load optional XML config if you have one
            try {
                sparkReporter.loadXMLConfig(Paths.get(System.getProperty("user.dir"),
                        "src", "main", "resources", "extent-config.xml").toFile());
            } catch (Exception e) {
                System.out.println("No extent-config.xml found, using default config");
            }

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Optional system info
            extent.setSystemInfo("Framework", "Selenium TestNG");
            extent.setSystemInfo("Execution", "Parallel");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java", System.getProperty("java.version"));
        }

        return extent;
    }

    // Flush the report (call at end of suite)
    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
