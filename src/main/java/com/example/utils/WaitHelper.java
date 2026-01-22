package com.example.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility class for handling explicit waits and common wait operations
 */
public class WaitHelper {
    
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final int DEFAULT_TIMEOUT = 30;
    private static final int POLLING_INTERVAL = 500;
    
    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        this.wait.pollingEvery(Duration.ofMillis(POLLING_INTERVAL));
    }
    
    public WaitHelper(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        this.wait.pollingEvery(Duration.ofMillis(POLLING_INTERVAL));
    }
    
    /**
     * Wait for element to be visible
     * @param locator element locator
     * @return WebElement
     */
    public WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be visible
     * @param element WebElement
     * @return WebElement
     */
    public WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Wait for element to be clickable
     * @param locator element locator
     * @return WebElement
     */
    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Wait for element to be clickable
     * @param element WebElement
     * @return WebElement
     */
    public WebElement waitForElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    /**
     * Wait for element to be present in DOM
     * @param locator element locator
     * @return WebElement
     */
    public WebElement waitForElementPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Wait for all elements to be present
     * @param locator element locator
     * @return List of WebElements
     */
    public List<WebElement> waitForElementsPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    
    /**
     * Wait for element to disappear
     * @param locator element locator
     * @return true if element is invisible
     */
    public boolean waitForElementInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for text to be present in element
     * @param locator element locator
     * @param text expected text
     * @return true if text is present
     */
    public boolean waitForTextPresent(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    
    /**
     * Wait for alert to be present
     * @return Alert
     */
    public Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }
    
    /**
     * Wait for page title to contain text
     * @param title expected title text
     * @return true if title contains text
     */
    public boolean waitForTitleContains(String title) {
        return wait.until(ExpectedConditions.titleContains(title));
    }
    
    /**
     * Wait for URL to contain text
     * @param urlFragment expected URL fragment
     * @return true if URL contains text
     */
    public boolean waitForUrlContains(String urlFragment) {
        return wait.until(ExpectedConditions.urlContains(urlFragment));
    }
    
    /**
     * Wait for frame to be available and switch to it
     * @param locator frame locator
     * @return WebDriver
     */
    public WebDriver waitForFrameAndSwitch(By locator) {
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }
    
    /**
     * Wait for dropdown to have options
     * @param locator dropdown locator
     * @return Select object
     */
    public Select waitForDropdownToHaveOptions(By locator) {
        wait.until(driver -> {
            Select select = new Select(driver.findElement(locator));
            return select.getOptions().size() > 1; // More than just default option
        });
        return new Select(driver.findElement(locator));
    }
    
    /**
     * Click element with wait and retry
     * @param locator element locator
     */
    public void clickWithWait(By locator) {
        WebElement element = waitForElementClickable(locator);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            // Retry with JavaScript click
            clickWithJavaScript(element);
        }
    }
    
    /**
     * Click element using JavaScript
     * @param element WebElement to click
     */
    public void clickWithJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    
    /**
     * Scroll element into view
     * @param element WebElement to scroll to
     */
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Send keys with wait
     * @param locator element locator
     * @param text text to send
     */
    public void sendKeysWithWait(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Select dropdown option by visible text with wait
     * @param locator dropdown locator
     * @param text visible text to select
     */
    public void selectDropdownByTextWithWait(By locator, String text) {
        Select select = waitForDropdownToHaveOptions(locator);
        select.selectByVisibleText(text);
    }
    
    /**
     * Select dropdown option by index with wait
     * @param locator dropdown locator
     * @param index index to select
     */
    public void selectDropdownByIndexWithWait(By locator, int index) {
        Select select = waitForDropdownToHaveOptions(locator);
        select.selectByIndex(index);
    }
    
    /**
     * Wait with custom condition
     * @param timeoutInSeconds timeout duration
     * @param condition custom expected condition
     * @return result of condition
     */
    public <T> T waitWithCustomCondition(int timeoutInSeconds, 
                                          org.openqa.selenium.support.ui.ExpectedCondition<T> condition) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(condition);
    }
    
    /**
     * Hard wait (use sparingly)
     * @param milliseconds time to wait in milliseconds
     */
    public void hardWait(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
