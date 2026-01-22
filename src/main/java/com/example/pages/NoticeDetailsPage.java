package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.utils.WaitHelper;

/**
 * Page Object for Notice Details view
 * Handles viewing notice details after approval
 */
public class NoticeDetailsPage {
    
    private WebDriver driver;
    private WaitHelper waitHelper;
    
    // Locators
    private By mattersMenu = By.xpath("/html/body/div[2]/div/div/div/ul/li[3]/a");
    private By noticeSubmenu = By.xpath("/html/body/div[2]/div/div/div/ul/li[3]/ul/li[2]/a");
    private By loadNoticesButton = By.xpath("//*[@id='loadNoticeBtn']");
    private By actionDropdown = By.xpath("//*[@id='NoticeListingContainer']/div/table/tbody/tr[1]/td[9]/div/a/I");
    private By detailsLink = By.xpath("//*[@id='NoticeListingContainer']/div/table/tbody/tr[1]/td[9]/div/ul/li[2]/a");
    
    public NoticeDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }
    
    /**
     * Navigate to Notice listing and view details
     */
    public void viewNoticeDetails() {
        try {
            System.out.println("\n========== VIEWING NOTICE DETAILS ==========");
            
            // Click Matters menu
            System.out.println("Clicking Matters menu...");
            waitHelper.hardWait(2000);
            WebElement matters = waitHelper.waitForElementClickable(mattersMenu);
            matters.click();
            waitHelper.hardWait(1000);
            System.out.println("✓ Matters menu clicked");
            
            // Click Notice submenu
            System.out.println("Clicking Notice submenu...");
            waitHelper.hardWait(1000);
            WebElement notice = waitHelper.waitForElementClickable(noticeSubmenu);
            notice.click();
            waitHelper.hardWait(2000);
            System.out.println("✓ Notice submenu clicked");
            
            // Click Load Notices button
            System.out.println("Clicking Load Notices button...");
            waitHelper.hardWait(2000);
            WebElement loadNotices = waitHelper.waitForElementClickable(loadNoticesButton);
            loadNotices.click();
            waitHelper.hardWait(3000);
            System.out.println("✓ Notices loaded");
            
            // Click Action dropdown
            System.out.println("Clicking Action dropdown...");
            waitHelper.hardWait(2000);
            WebElement action = waitHelper.waitForElementClickable(actionDropdown);
            
            // Scroll to element
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", action);
            waitHelper.hardWait(1000);
            
            action.click();
            waitHelper.hardWait(1000);
            System.out.println("✓ Action dropdown opened");
            
            // Click Details link
            System.out.println("Clicking Details link...");
            waitHelper.hardWait(1000);
            WebElement details = waitHelper.waitForElementClickable(detailsLink);
            details.click();
            waitHelper.hardWait(3000);
            System.out.println("✓ Notice details page opened");
            
            System.out.println("========== NOTICE DETAILS DISPLAYED ==========\n");
            
        } catch (Exception e) {
            System.out.println("❌ Error viewing notice details: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to view notice details", e);
        }
    }
    
    /**
     * Verify notice details page is loaded
     */
    public boolean isNoticeDetailsPageLoaded() {
        try {
            waitHelper.hardWait(2000);
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);
            return currentUrl.contains("Notice") || currentUrl.contains("Details");
        } catch (Exception e) {
            System.out.println("Error verifying notice details page: " + e.getMessage());
            return false;
        }
    }
}
