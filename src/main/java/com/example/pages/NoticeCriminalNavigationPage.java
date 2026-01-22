package com.example.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.utils.WaitHelper;

/**
 * Page Object for Criminal Notice Navigation
 * Handles navigation to the Add Criminal Notice page
 */
public class NoticeCriminalNavigationPage {
    
    private WebDriver driver;
    private WaitHelper waitHelper;
    
    // Locators for navigation menu items
    private By mattersMenu = By.xpath("/html/body/div[3]/div/div/div/ul/li[3]/a");
    private By noticeSubmenu = By.xpath("/html/body/div[3]/div/div/div/ul/li[3]/ul/li[2]/a");
    private By addNewNoticeButton = By.xpath("/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div/div[1]/div[2]/div/div[3]/a");
    private By criminalOption = By.xpath("/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div/div[1]/div[2]/div/div[3]/ul/li[6]/a");
    
    public NoticeCriminalNavigationPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }
    
    /**
     * Navigate to Add Criminal Notice page
     */
    public void navigateToAddCriminalNotice() {
        try {
            System.out.println("\n========== NAVIGATING TO CRIMINAL NOTICE PAGE ==========");
            
            // Store the current window handle
            String mainWindow = driver.getWindowHandle();
            
            // Click Matters menu
            System.out.println("Step 1: Clicking Matters menu...");
            waitHelper.hardWait(2000);
            WebElement matters = waitHelper.waitForElementClickable(mattersMenu);
            matters.click();
            waitHelper.hardWait(1000);
            System.out.println("✓ Matters menu clicked");
            
            // Click Notice submenu
            System.out.println("Step 2: Clicking Notice submenu...");
            waitHelper.hardWait(1000);
            WebElement notice = waitHelper.waitForElementClickable(noticeSubmenu);
            notice.click();
            waitHelper.hardWait(2000);
            System.out.println("✓ Notice submenu clicked");
            
            // Click Add New Notice button
            System.out.println("Step 3: Clicking Add New Notice button...");
            waitHelper.hardWait(1000);
            WebElement addNotice = waitHelper.waitForElementClickable(addNewNoticeButton);
            addNotice.click();
            waitHelper.hardWait(2000);
            System.out.println("✓ Add New Notice button clicked");
            
            // Click Criminal option
            System.out.println("Step 4: Clicking Criminal option...");
            waitHelper.hardWait(1000);
            WebElement criminal = waitHelper.waitForElementClickable(criminalOption);
            criminal.click();
            waitHelper.hardWait(3000);
            System.out.println("✓ Criminal option clicked");
            
            // Check if a new tab/window was opened and switch to it
            Set<String> allWindows = driver.getWindowHandles();
            if (allWindows.size() > 1) {
                System.out.println("New tab detected, switching to it...");
                for (String windowHandle : allWindows) {
                    if (!windowHandle.equals(mainWindow)) {
                        driver.switchTo().window(windowHandle);
                        System.out.println("✓ Switched to new tab");
                        break;
                    }
                }
            }
            
            waitHelper.hardWait(3000);
            System.out.println("========== SUCCESSFULLY NAVIGATED TO CRIMINAL NOTICE PAGE ==========\n");
            
        } catch (Exception e) {
            System.out.println("❌ Error during navigation: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to navigate to Criminal Notice page", e);
        }
    }
    
    /**
     * Verify we are on the Criminal Notice page
     */
    public boolean isOnCriminalNoticePage() {
        try {
            waitHelper.hardWait(2000);
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);
            return currentUrl.contains("Notice") || currentUrl.contains("Criminal");
        } catch (Exception e) {
            System.out.println("Error verifying Criminal Notice page: " + e.getMessage());
            return false;
        }
    }
}
