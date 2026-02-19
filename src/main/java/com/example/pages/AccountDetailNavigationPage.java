package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Account Detail Navigation
 * Handles navigation to Account Details page
 */
public class AccountDetailNavigationPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    
    // Navigation Locators
    @FindBy(xpath = "/html/body/div[3]/div/div/div/ul/li[2]/a")
    private WebElement borrowersMenu;
    
    @FindBy(xpath = "/html/body/div[3]/div/div/div/ul/li[2]/ul/li[2]/a")
    private WebElement accountSubmenu;
    
    @FindBy(xpath = "//*[@id='LoadCustomerAccounts']")
    private WebElement loadCustomerButton;
    
    @FindBy(xpath = "//*[@id='accountTableBody']/tr[1]/td[11]/div/a")
    private WebElement actionButton;
    
    @FindBy(xpath = "//*[@id='accountTableBody']/tr[1]/td[11]/div/ul/li[2]/a")
    private WebElement detailsButton;
    
    // Constructor
    public AccountDetailNavigationPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Click on Borrowers menu
     */
    public void clickBorrowersMenu() {
        waitHelper.waitForElementClickable(borrowersMenu);
        borrowersMenu.click();
        System.out.println("✓ Clicked on Borrowers menu");
    }
    
    /**
     * Click on Account submenu
     */
    public void clickAccountSubmenu() {
        try {
            Thread.sleep(1000);
            waitHelper.waitForElementClickable(accountSubmenu);
            accountSubmenu.click();
            System.out.println("✓ Clicked on Account submenu");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Click on Load Customer button
     */
    public void clickLoadCustomer() {
        try {
            waitHelper.waitForElementClickable(loadCustomerButton);
            loadCustomerButton.click();
            System.out.println("✓ Clicked on Load Customer button");
            Thread.sleep(3000); // Wait for data to load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Click on Action button
     */
    public void clickActionButton() {
        try {
            waitHelper.waitForElementClickable(actionButton);
            actionButton.click();
            System.out.println("✓ Clicked on Action button");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Click on Details button (opens new tab)
     */
    public void clickDetailsButton() {
        try {
            // Store the current window handle
            String originalWindow = driver.getWindowHandle();
            System.out.println("Current window before click: " + originalWindow);
            
            waitHelper.waitForElementClickable(detailsButton);
            detailsButton.click();
            System.out.println("✓ Clicked on Details button");
            
            // Wait for new tab to open
            Thread.sleep(3000);
            
            // Get all window handles
            java.util.Set<String> allWindows = driver.getWindowHandles();
            System.out.println("Total windows/tabs: " + allWindows.size());
            
            // Switch to the new tab
            for (String windowHandle : allWindows) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    System.out.println("✓ Switched to new tab");
                    break;
                }
            }
            
            // Wait for new page to load
            Thread.sleep(3000);
            System.out.println("New tab URL: " + driver.getCurrentUrl());
            System.out.println("Page fully loaded. Ready to edit account details.");
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Navigate to Account Details page (complete flow)
     */
    public void navigateToAccountDetails() {
        clickBorrowersMenu();
        clickAccountSubmenu();
        clickLoadCustomer();
        clickActionButton();
        clickDetailsButton();
    }
}
