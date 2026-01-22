package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.utils.WaitHelper;

/**
 * Page Object for Logout functionality
 */
public class LogoutPage {
    
    private WebDriver driver;
    private WaitHelper waitHelper;
    private JavascriptExecutor js;
    
    // Locators
    private By userProfileLink = By.xpath("/html/body/header/div/div[2]/a");
    private By logoutLink = By.xpath("/html/body/header/div/div[2]/div[2]/div/div/div[1]/div/ul/li[2]/a");
    
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.js = (JavascriptExecutor) driver;
    }
    
    /**
     * Perform logout
     */
    public void logout() {
        try {
            System.out.println("\n========== LOGGING OUT ==========");
            
            // Switch to default content in case we're in an iframe
            try {
                driver.switchTo().defaultContent();
                System.out.println("Switched to default content");
            } catch (Exception e) {
                System.out.println("Already in default content");
            }
            
            // Print current URL for debugging
            System.out.println("Current URL: " + driver.getCurrentUrl());
            
            // Scroll to top
            js.executeScript("window.scrollTo(0, 0);");
            waitHelper.hardWait(2000);
            
            // Click user profile (Ayushi G or Ajinkya)
            System.out.println("Attempting to click user profile link with xpath: /html/body/header/div/div[2]/a");
            waitHelper.hardWait(2000);
            
            // Try multiple strategies to find and click the element
            WebElement userProfile = null;
            boolean clicked = false;
            
            // Strategy 1: Wait for visible and click with JS
            try {
                userProfile = waitHelper.waitForElementVisible(userProfileLink);
                System.out.println("User profile element found: " + userProfile.getText());
                
                // Highlight element for debugging
                js.executeScript("arguments[0].style.border='3px solid red'", userProfile);
                waitHelper.hardWait(500);
                
                // Scroll element into view
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", userProfile);
                waitHelper.hardWait(500);
                
                // Use JavaScript click
                js.executeScript("arguments[0].click();", userProfile);
                waitHelper.hardWait(2000);
                System.out.println("✓ User profile menu clicked (Strategy 1: JS click)");
                clicked = true;
            } catch (Exception e1) {
                System.out.println("Strategy 1 failed: " + e1.getMessage());
                
                // Strategy 2: Try direct click
                try {
                    userProfile = driver.findElement(userProfileLink);
                    userProfile.click();
                    waitHelper.hardWait(2000);
                    System.out.println("✓ User profile menu clicked (Strategy 2: Direct click)");
                    clicked = true;
                } catch (Exception e2) {
                    System.out.println("Strategy 2 failed: " + e2.getMessage());
                    
                    // Strategy 3: Try with Actions
                    try {
                        userProfile = driver.findElement(userProfileLink);
                        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
                        actions.moveToElement(userProfile).click().perform();
                        waitHelper.hardWait(2000);
                        System.out.println("✓ User profile menu clicked (Strategy 3: Actions)");
                        clicked = true;
                    } catch (Exception e3) {
                        System.out.println("Strategy 3 failed: " + e3.getMessage());
                        throw new RuntimeException("All strategies failed to click user profile link");
                    }
                }
            }
            
            if (!clicked) {
                throw new RuntimeException("Failed to click user profile link");
            }
            
            System.out.println("✓ User profile menu opened");
            
            // Click Logout
            System.out.println("Attempting to click Logout link...");
            waitHelper.hardWait(1000);
            WebElement logout = waitHelper.waitForElementVisible(logoutLink);
            System.out.println("Logout element found: " + logout.getText());
            js.executeScript("arguments[0].click();", logout);
            waitHelper.hardWait(3000);
            System.out.println("✓ Logged out successfully");
            
            System.out.println("========== LOGOUT SUCCESSFUL ==========\n");
            
        } catch (Exception e) {
            System.out.println("❌ Error during logout: " + e.getMessage());
            e.printStackTrace();
            
            // Print page source for debugging if needed
            System.out.println("Current page title: " + driver.getTitle());
            
            throw new RuntimeException("Failed to logout", e);
        }
    }
}
