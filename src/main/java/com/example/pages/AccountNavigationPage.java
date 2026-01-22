package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Account Navigation
 */
public class AccountNavigationPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    
    // Navigation Locators
    @FindBy(xpath = "/html/body/div[3]/div/div/div/ul/li[2]/a")
    private WebElement borrowersMenu;
    
    @FindBy(xpath = "/html/body/div[3]/div/div/div/ul/li[2]/ul/li[2]/a")
    private WebElement accountsSubmenu;
    
    @FindBy(xpath = "/html/body/div[2]/div/main/div/div[2]/div[1]/div/div[1]/div[2]/div/a")
    private WebElement addNewAccountButton;
    
    // Constructor
    public AccountNavigationPage(WebDriver driver) {
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
     * Click on Accounts submenu
     */
    public void clickAccountsSubmenu() {
        waitHelper.waitForElementClickable(accountsSubmenu);
        accountsSubmenu.click();
        System.out.println("✓ Clicked on Accounts submenu");
    }
    
    /**
     * Click on Add New Account button
     */
    public void clickAddNewAccount() {
        try {
            waitHelper.waitForElementClickable(addNewAccountButton);
            
            // Store the current window handle
            String originalWindow = driver.getWindowHandle();
            System.out.println("Current window before click: " + originalWindow);
            
            addNewAccountButton.click();
            System.out.println("✓ Clicked on Add New Account button");
            
            // Wait for new tab to open
            Thread.sleep(2000);
            
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
            System.out.println("Page fully loaded. Ready to fill form.");
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
