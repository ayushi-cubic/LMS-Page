package com.example.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.WaitHelper;

/**
 * Page Object for Asset Navigation
 */
public class AssetNavigationPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    
    // Navigation Locators - Using original working XPaths
    @FindBy(xpath = "/html/body/div[3]/div/div/div/ul/li[2]/a")
    private WebElement borrowersMenu;
    
    @FindBy(xpath = "/html/body/div[3]/div/div/div/ul/li[2]/ul/li[4]/a")
    private WebElement assetsSubmenu;
    
    @FindBy(xpath = "/html/body/div[2]/div/main/div/div/main/div/div[2]/div[1]/div/div[1]/div[2]/div/a")
    private WebElement addNewAssetButton;
    
    public AssetNavigationPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Click on Borrowers menu
     */
    public void clickBorrowersMenu() {
        waitHelper.hardWait(1000);
        waitHelper.waitForElementClickable(borrowersMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", borrowersMenu);
        waitHelper.hardWait(1000);
        System.out.println("✓ Clicked on Borrowers menu");
    }
    
    /**
     * Click on Assets submenu
     */
    public void clickAssetsSubmenu() {
        waitHelper.hardWait(1000);
        waitHelper.waitForElementClickable(assetsSubmenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", assetsSubmenu);
        waitHelper.hardWait(2000);
        System.out.println("✓ Clicked on Assets submenu");
    }
    
    /**
     * Click on Add New Asset button
     */
    public void clickAddNewAssetButton() {
        waitHelper.hardWait(1000);
        
        // Store current window handle
        String originalWindow = driver.getWindowHandle();
        System.out.println("Current window before click: " + originalWindow);
        
        waitHelper.waitForElementClickable(addNewAssetButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNewAssetButton);
        System.out.println("✓ Clicked on Add New Asset button");
        
        // Wait and check for new window with timeout
        int maxWait = 10; // Wait up to 10 seconds
        int count = 0;
        while (driver.getWindowHandles().size() == 1 && count < maxWait) {
            waitHelper.hardWait(1000);
            count++;
        }
        
        // Check if a new window/tab opened
        if (driver.getWindowHandles().size() > 1) {
            System.out.println("Total windows/tabs: " + driver.getWindowHandles().size());
            // Switch to the new window
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    System.out.println("✓ Switched to new tab");
                    System.out.println("New tab URL: " + driver.getCurrentUrl());
                    break;
                }
            }
            
            // Wait for page to fully load
            waitHelper.hardWait(3000);
            System.out.println("Page fully loaded. Ready to fill form.");
        } else {
            System.out.println("No new window opened after waiting " + maxWait + " seconds, staying on current page");
            System.out.println("Current URL: " + driver.getCurrentUrl());
        }
    }
}
