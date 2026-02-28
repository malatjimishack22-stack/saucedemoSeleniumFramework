package com.saucedemo.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Singleton ConfigManager to read properties from config.properties
 */
public class ConfigManager {

    private static ConfigManager instance;
    private final Properties properties;

    private ConfigManager() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file", e);
        }
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    //Get property by key
    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property " + key + " not found in config.properties");
        }
        return value;
    }

    //Get property with default
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    //Convenience methods for common properties

    public String getBrowser() {
        return getProperty("browser");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }

    public String getBaseUrl() {
        return getProperty("baseUrl");
    }

    public int getImplicitWait() {
        return Integer.parseInt(getProperty("implicitWait", "10"));
    }

    public int getExplicitWait() {
        return Integer.parseInt(getProperty("explicitWait", "15"));
    }

    public int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("pageLoadTimeout", "30"));
    }

    public String getReportPath() {
        return getProperty("reportPath", "./test-results/extent-report.html");
    }
}
