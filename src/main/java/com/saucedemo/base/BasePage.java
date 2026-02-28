package com.saucedemo.base;

import com.saucedemo.driver.DriverManager;
import com.saucedemo.reports.ReportLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor jsExecutor;
    protected final Logger log = LogManager.getLogger(getClass());

    private static final int DEFAULT_TIMEOUT = 10;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        this.actions = new Actions(driver);
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    //Navigate to URL
    public void openUrl(String url) {
        log.info("Navigating to URL: {}", url);
        ReportLogger.info("Navigating to URL: " + url);
        driver.get(url);
    }

    //Click element safely
    public void click(By locator) {
        try {
            waitUntilClickable(locator).click();
            log.info("Clicked element: {}", locator);
            ReportLogger.info("Clicked element: " + locator);
        } catch (Exception e) {
            log.error("Failed to click element: {}", locator, e);
            ReportLogger.fail("Failed to click element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    //Type into an input
    public void type(By locator, String text) {
        try {
            WebElement element = waitUntilVisible(locator);
            element.clear();
            element.sendKeys(text);
            log.info("Typed '{}' into: {}", text, locator);
            ReportLogger.info("Typed '" + text + "' into: " + locator);
        } catch (Exception e) {
            log.error("Failed to type into element: {}", locator, e);
            ReportLogger.fail("Failed to type into element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    //Get element text
    public String getText(By locator) {
        try {
            String text = waitUntilVisible(locator).getText();
            log.info("Got text from element: {} -> {}", locator, text);
            ReportLogger.info("Got text from element: " + locator + " -> " + text);
            return text;
        } catch (Exception e) {
            log.error("Failed to get text from element: {}", locator, e);
            ReportLogger.fail("Failed to get text from element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    //Wait until element is visible
    public WebElement waitUntilVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //Wait until element is clickable
    public WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //Check if element is displayed
    public boolean isDisplayed(By locator) {
        try {
            return waitUntilVisible(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    //Accept alert if present
    public void acceptAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent()).accept();
            log.info("Alert accepted");
            ReportLogger.info("Alert accepted");
        } catch (TimeoutException e) {
            log.warn("No alert to accept");
            ReportLogger.warning("No alert to accept");
        }
    }

    //Scroll to element
    public void scrollTo(By locator) {
        WebElement element = waitUntilVisible(locator);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        log.info("Scrolled to element: {}", locator);
        ReportLogger.info("Scrolled to element: " + locator);
    }

    //Scroll by pixels
    public void scrollBy(int x, int y) {
        jsExecutor.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
        log.info("Scrolled by x:{}, y:{}", x, y);
        ReportLogger.info("Scrolled by x:" + x + ", y:" + y);
    }

    //Click element using JavaScript
    public void clickJS(By locator) {
        WebElement element = waitUntilVisible(locator);
        jsExecutor.executeScript("arguments[0].click();", element);
        log.info("Clicked element using JS: {}", locator);
        ReportLogger.info("Clicked element using JS: " + locator);
    }

    //Hover over element
    public void hover(By locator) {
        WebElement element = waitUntilVisible(locator);
        actions.moveToElement(element).perform();
        log.info("Hovered over element: {}", locator);
        ReportLogger.info("Hovered over element: " + locator);
    }

    //Double click element
    public void doubleClick(By locator) {
        WebElement element = waitUntilVisible(locator);
        actions.doubleClick(element).perform();
        log.info("Double clicked element: {}", locator);
        ReportLogger.info("Double clicked element: " + locator);
    }

    //Right click (context click) element
    public void rightClick(By locator) {
        WebElement element = waitUntilVisible(locator);
        actions.contextClick(element).perform();
        log.info("Right clicked element: {}", locator);
        ReportLogger.info("Right clicked element: " + locator);
    }

    //Drag element to target
    public void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = waitUntilVisible(sourceLocator);
        WebElement target = waitUntilVisible(targetLocator);
        actions.dragAndDrop(source, target).perform();
        log.info("Dragged element: {} to {}", sourceLocator, targetLocator);
        ReportLogger.info("Dragged element: " + sourceLocator + " to " + targetLocator);
    }

    //Sleep utility (use sparingly)
    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException ignored) {
        }
    }
}
