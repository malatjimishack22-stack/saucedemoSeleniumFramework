package com.saucedemo.base;

import com.saucedemo.elements.Element;
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

    public BasePage(WebDriver driver) {
        this.driver = driver;
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
    public void click(Element element) {
        try {
            waitUntilClickable(element.getLocator()).click();
            log.info("Clicked {}", element.getName());
            ReportLogger.info("Clicked " + element.getName());
        } catch (Exception e) {
            log.error("Failed to click element: {}", element.getLocator(), e);
            ReportLogger.fail("Failed to click element: " + element.getLocator() + " - " + e.getMessage());
            throw e;
        }
    }

    //Type into an input
    public void type(Element element, String text) {
        try {
            WebElement _element = waitUntilVisible(element.getLocator());
            _element.clear();
            _element.sendKeys(text);
            log.info("Typed '{}' into {}", text, element.getName());
            ReportLogger.info("Typed '" + text + "' into " + element.getName());
        } catch (Exception e) {
            log.error("Failed to type into element: {}", element.getLocator(), e);
            ReportLogger.fail("Failed to type into element: " + element.getLocator() + " - " + e.getMessage());
            throw e;
        }
    }

    //Get element text
    public String getText(Element element) {
        try {
            String text = waitUntilVisible(element.getLocator()).getText();
            log.info("Got text {} from {}", text, element.getName());
            ReportLogger.info("Got text "+text+" from " + element.getName());
            return text;
        } catch (Exception e) {
            log.error("Failed to get text from element: {}", element.getLocator(), e);
            ReportLogger.fail("Failed to get text from element: " + element.getLocator() + " - " + e.getMessage());
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
    public boolean isDisplayed(Element element) {
        try {
            return waitUntilVisible(element.getLocator()).isDisplayed();
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
    public void scrollTo(Element element) {
        WebElement _element = waitUntilVisible(element.getLocator());
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", _element);
        log.info("Scrolled to : {}", element.getName());
        ReportLogger.info("Scrolled to : " + element.getName());
    }

    //Scroll by pixels
    public void scrollBy(int x, int y) {
        jsExecutor.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
        log.info("Scrolled by x:{}, y:{}", x, y);
        ReportLogger.info("Scrolled by x:" + x + ", y:" + y);
    }

    //Click element using JavaScript
    public void clickJS(Element element) {
        WebElement _element = waitUntilVisible(element.getLocator());
        jsExecutor.executeScript("arguments[0].click();", _element);
        log.info("Clicked {}", element.getName());
        ReportLogger.info("Clicked "+ element.getName() );
    }

    //Hover over element
    public void hover(Element element) {
        WebElement _element = waitUntilVisible(element.getLocator());
        actions.moveToElement(_element).perform();
        log.info("Hovered over : {}", element.getName());
        ReportLogger.info("Hovered over : " + element.getName());
    }

    //Double click element
    public void doubleClick(Element element) {
        WebElement _element = waitUntilVisible(element.getLocator());
        actions.doubleClick(_element).perform();
        log.info("Double clicked : {}", element.getName());
        ReportLogger.info("Double clicked : " + element.getName());
    }

    //Right click (context click) element
    public void rightClick(Element element) {
        WebElement _element = waitUntilVisible(element.getLocator());
        actions.contextClick(_element).perform();
        log.info("Right clicked : {}", element.getName());
        ReportLogger.info("Right clicked : " + element.getName());
    }

    //Drag element to target
    public void dragAndDrop(Element sourceElement, Element targetElement) {
        WebElement source = waitUntilVisible(sourceElement.getLocator());
        WebElement target = waitUntilVisible(targetElement.getLocator());
        actions.dragAndDrop(source, target).perform();
        log.info("Dragged : {} to {}", sourceElement.getName(), targetElement.getName());
        ReportLogger.info("Dragged : " + sourceElement.getName() + " to " + targetElement.getName());
    }

    //Sleep utility (use sparingly)
    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException ignored) {
        }
    }
}
