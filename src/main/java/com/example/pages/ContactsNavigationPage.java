package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Contacts Navigation
 */
public class ContactsNavigationPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    
    // Contacts Module Link
    @FindBy(xpath = "//a[.//i[contains(@class,'fa-address-book')]][contains(@class,'nav-link') or contains(@class,'nav-border')]")
    private WebElement contactsModuleLink;
    
    // Tabs
    @FindBy(id = "lFirm-tab")
    private WebElement firmTab;
    
    @FindBy(id = "lIndividual-tab")
    private WebElement individualTab;
    
    @FindBy(id = "lEmployee-tab")
    private WebElement employeeTab;
    
    @FindBy(id = "lOtherParties-tab")
    private WebElement otherPartiesTab;
    
    // Add New Button
    @FindBy(xpath = "//a[contains(@class,'btn') and contains(normalize-space(.),'Add New')]")
    private WebElement addNewButton;
    
    // Single Entry Option in Dropdown
    @FindBy(xpath = "//ul//li//a[contains(normalize-space(.),'Single Entry')]")
    private WebElement singleEntryOption;
    
    // Constructor
    public ContactsNavigationPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Navigate to Contacts Module
     */
    public void navigateToContacts() {
        try {
            // Wait for page to load
            Thread.sleep(1000);
            
            // Try to find and click contacts module
            boolean clicked = false;
            
            // First attempt - direct click
            try {
                waitHelper.waitForElementClickable(contactsModuleLink);
                contactsModuleLink.click();
                clicked = true;
            } catch (Exception e) {
                // Try with JavaScript
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", contactsModuleLink);
                js.executeScript("arguments[0].click();", contactsModuleLink);
                clicked = true;
            }
            
            // Try in iframes if not found
            if (!clicked) {
                int frameCount = driver.findElements(By.tagName("iframe")).size();
                for (int i = 0; i < frameCount && !clicked; i++) {
                    try {
                        driver.switchTo().frame(i);
                        WebElement contacts = driver.findElement(
                            By.xpath("//a[.//i[contains(@class,'fa-address-book')]][contains(@class,'nav-link') or contains(@class,'nav-border')]")
                        );
                        if (contacts.isDisplayed() && contacts.isEnabled()) {
                            contacts.click();
                            clicked = true;
                        }
                    } catch (Exception ignore) {
                    } finally {
                        driver.switchTo().defaultContent();
                    }
                }
            }
            
            if (!clicked) {
                throw new RuntimeException("Unable to click Contacts module");
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate to Contacts module: " + e.getMessage());
        }
    }
    
    /**
     * Click Firm Tab
     */
    public void clickFirmTab() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            waitHelper.waitForElementClickable(firmTab);
            js.executeScript("arguments[0].click();", firmTab);
            Thread.sleep(700);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Firm tab: " + e.getMessage());
        }
    }
    
    /**
     * Click Individual Tab
     */
    public void clickIndividualTab() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            waitHelper.waitForElementClickable(individualTab);
            js.executeScript("arguments[0].click();", individualTab);
            Thread.sleep(700);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Individual tab: " + e.getMessage());
        }
    }
    
    /**
     * Click Employee Tab
     */
    public void clickEmployeeTab() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            waitHelper.waitForElementClickable(employeeTab);
            js.executeScript("arguments[0].click();", employeeTab);
            Thread.sleep(700);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Employee tab: " + e.getMessage());
        }
    }
    
    /**
     * Click Other Parties Tab
     */
    public void clickOtherPartiesTab() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.querySelector('#lOtherParties-tab').click();");
            Thread.sleep(700);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Other Parties tab: " + e.getMessage());
        }
    }
    
    /**
     * Click Add New Button
     */
    public void clickAddNewButton() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Try multiple approaches
            try {
                waitHelper.waitForElementClickable(addNewButton);
                js.executeScript("arguments[0].click();", addNewButton);
            } catch (Exception e) {
                // Fallback with direct xpath
                WebElement addNewBtn = driver.findElement(
                    By.xpath("//a[contains(@class,'btn') and contains(normalize-space(.),'Add New')]")
                );
                js.executeScript("arguments[0].click();", addNewBtn);
            }
            
            Thread.sleep(400);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Add New button: " + e.getMessage());
        }
    }
    
    /**
     * Click Single Entry Option
     */
    public void clickSingleEntry() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Try with JavaScript selector
            js.executeScript(
                "var el = document.querySelector(\"body > div.container-fluid > div > main > div > div.row > div > div > div.d-flex.justify-content-between.align-items-center.border-bottom.p-3 > div:nth-child(2) > div > ul > li:nth-child(2) > a\"); " +
                "if(el) el.click();"
            );
            
            Thread.sleep(700);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Single Entry: " + e.getMessage());
        }
    }
    
    /**
     * Navigate back to Contacts using breadcrumb
     */
    public void navigateBackToContacts() {
        try {
            Thread.sleep(2000); // Wait for page to stabilize
            
            // Try to find the breadcrumb element freshly
            WebElement contactsBreadcrumb = driver.findElement(
                By.xpath("//nav[@aria-label='Breadcrumb' or contains(@class,'breadcrumb')]//a[contains(text(),'Contacts') or .//small[contains(text(),'Contacts')]]")
            );
            waitHelper.waitForElementClickable(contactsBreadcrumb);
            
            // Use JavaScript click to avoid stale element issues
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", contactsBreadcrumb);
            Thread.sleep(1500);
        } catch (Exception e) {
            // Fallback: try direct navigation to Contacts module
            try {
                navigateToContacts();
            } catch (Exception ex) {
                throw new RuntimeException("Failed to navigate back to Contacts: " + e.getMessage());
            }
        }
    }
}
