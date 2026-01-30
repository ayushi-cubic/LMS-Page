package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Reports Navigation
 */
public class ReportsNavigationPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    
    // Constructor
    public ReportsNavigationPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Navigate to Reports Module - Enhanced version with dynamic element detection
     */
    public void navigateToReports() {
        try {
            System.out.println("ReportsNavigationPage: Attempting to navigate to Reports module");
            Thread.sleep(3000); // Increased wait for page to stabilize
            
            // Scroll to top first to ensure menu is visible
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1000);
            
            // Try multiple strategies to find the Reports link
            WebElement reportsLink = null;
            
            // Strategy 1: Try text-based locator first (more reliable)
            try {
                reportsLink = driver.findElement(By.linkText("Reports"));
                System.out.println("ReportsNavigationPage: Found Reports link using linkText");
            } catch (Exception e1) {
                try {
                    reportsLink = driver.findElement(By.partialLinkText("Report"));
                    System.out.println("ReportsNavigationPage: Found Reports link using partialLinkText");
                } catch (Exception e2) {
                    // Strategy 2: Try original XPath
                    try {
                        reportsLink = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/ul/li[9]/a"));
                        System.out.println("ReportsNavigationPage: Found Reports link using XPath (div[3])");
                    } catch (Exception e3) {
                        // Strategy 3: Try alternative div[2] (in case structure changed)
                        reportsLink = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[9]/a"));
                        System.out.println("ReportsNavigationPage: Found Reports link using XPath (div[2])");
                    }
                }
            }
            
            // Scroll element into view
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", reportsLink);
            Thread.sleep(1000);
            
            // Highlight the element for visibility
            js.executeScript("arguments[0].style.border='3px solid red'", reportsLink);
            Thread.sleep(500);
            
            // Click the Reports link
            try {
                reportsLink.click();
                System.out.println("ReportsNavigationPage: Reports module clicked successfully (normal click)");
            } catch (Exception clickException) {
                // Fallback to JavaScript click
                System.out.println("ReportsNavigationPage: Normal click failed, trying JavaScript click");
                js.executeScript("arguments[0].click();", reportsLink);
                System.out.println("ReportsNavigationPage: Reports module clicked successfully (JavaScript click)");
            }
            
            // Wait longer for the Reports page to fully load
            Thread.sleep(5000);
            System.out.println("ReportsNavigationPage: Waiting for Reports page to load completely (5 seconds)");
            
            // Scroll back to top to ensure form elements are accessible
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1000);
            System.out.println("ReportsNavigationPage: Page scrolled to top, ready for interaction");
            
        } catch (Exception e) {
            System.err.println("ReportsNavigationPage: Failed to navigate to Reports module - " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to navigate to Reports module: " + e.getMessage());
        }
    }
}
