package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Notice Navigation
 */
public class NoticeNavigationPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    
    // Navigation Locators
    @FindBy(xpath = "/html/body/div[3]/div/div/div/ul/li[3]/a")
    private WebElement mattersMenu;
    
    @FindBy(xpath = "/html/body/div[3]/div/div/div/ul/li[3]/ul/li[2]/a")
    private WebElement noticeSubmenu;
    
    @FindBy(xpath = "/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div/div[1]/div[2]/div/div[3]/a")
    private WebElement addNewNoticeButton;
    
    @FindBy(xpath = "/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div/div[1]/div[2]/div/div[3]/ul/li[2]/a")
    private WebElement civilOption;
    
    // Constructor
    public NoticeNavigationPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Click on Matters menu
     */
    public void clickMattersMenu() {
        System.out.println("NoticeNavigationPage: Clicking Matters menu (li[3])");
        try {
            Thread.sleep(2000);
            waitHelper.waitForElementClickable(mattersMenu);
            mattersMenu.click();
            Thread.sleep(1000);
            System.out.println("NoticeNavigationPage: Matters menu clicked successfully");
        } catch (Exception e) {
            System.err.println("NoticeNavigationPage: Failed to click Matters menu - " + e.getMessage());
            throw new RuntimeException("Failed to click Matters menu: " + e.getMessage());
        }
    }
    
    /**
     * Click on Notice submenu
     */
    public void clickNoticeSubmenu() {
        System.out.println("NoticeNavigationPage: Clicking Notice submenu (li[3]/ul/li[2])");
        try {
            waitHelper.waitForElementClickable(noticeSubmenu);
            noticeSubmenu.click();
            Thread.sleep(1000);
            System.out.println("NoticeNavigationPage: Notice submenu clicked successfully");
        } catch (Exception e) {
            System.err.println("NoticeNavigationPage: Failed to click Notice submenu - " + e.getMessage());
            throw new RuntimeException("Failed to click Notice submenu: " + e.getMessage());
        }
    }
    
    /**
     * Click on Add New Notice button
     */
    public void clickAddNewNotice() {
        waitHelper.waitForElementClickable(addNewNoticeButton);
        addNewNoticeButton.click();
        System.out.println("✓ Clicked on Add New Notice button");
    }
    
    /**
     * Click on Civil option from dropdown
     */
    public void clickCivilOption() {
        try {
            waitHelper.waitForElementClickable(civilOption);
            // Store the current window handle
            String originalWindow = driver.getWindowHandle();
            System.out.println("Current window before click: " + originalWindow);
            
            civilOption.click();
            System.out.println("✓ Clicked on Civil option");
            
            // Wait for new tab to open
            Thread.sleep(2000);
            
            // Get all window handles
            java.util.Set<String> allWindows = driver.getWindowHandles();
            System.out.println("Total windows/tabs: " + allWindows.size());
            
            // Switch to the new tab
            for (String windowHandle : allWindows) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    System.out.println("✓ Switched to new tab for Civil Notice creation");
                    break;
                }
            }
            
            // Wait for page to load
            Thread.sleep(2000);
            
        } catch (Exception e) {
            System.err.println("Error clicking Civil option: " + e.getMessage());
            throw new RuntimeException("Failed to click Civil option and switch to new tab", e);
        }
    }
    
    /**
     * Navigate to Notice section
     */
    public void navigateToNoticeSection() {
        clickMattersMenu();
        clickNoticeSubmenu();
        System.out.println("✓ Navigated to Notice section");
    }
}
