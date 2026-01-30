package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Searching and Editing Contacts
 */
public class SearchContactPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    
    // Search Field
    @FindBy(id = "ContactName")
    private WebElement searchField;
    
    // Search Icon/Button
    @FindBy(css = "i.fa-solid.fa-magnifying-glass.fs-6")
    private WebElement searchIcon;
    
    // Dynamic Table Body
    @FindBy(id = "dynamicTableBody")
    private WebElement dynamicTableBody;
    
    // Constructor
    public SearchContactPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Search for a contact by name
     * @param contactName name of the contact to search
     */
    public void searchContact(String contactName) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Wait for search field
            waitHelper.waitForElementClickable(searchField);
            
            // Enter contact name using JavaScript for reliability
            js.executeScript("arguments[0].value = arguments[1];", searchField, contactName);
            Thread.sleep(500);
            
            // Click search icon
            waitHelper.waitForElementClickable(searchIcon);
            js.executeScript("arguments[0].click();", searchIcon);
            Thread.sleep(3000); // Wait for search results to load
        } catch (Exception e) {
            throw new RuntimeException("Failed to search for contact: " + e.getMessage());
        }
    }
    
    /**
     * Click on the searched contact from results
     * Clicks the first result in the dynamic table
     */
    public void clickSearchedContact() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Wait for table to be updated with results
            waitHelper.waitForElementVisible(
                driver.findElement(By.xpath("//*[@id=\"dynamicTableBody\"]/tr"))
            );
            
            // Click the first contact in results using JavaScript
            js.executeScript("document.querySelector('#dynamicTableBody > tr > td:nth-child(1) > a').click();");
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click searched contact: " + e.getMessage());
        }
    }
    
    /**
     * Edit Employee Contact Details
     * @param employeeId new employee ID to set
     */
    public void editEmployeeContact(String employeeId) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Click the Employee details section
            WebElement employeeDetails = driver.findElement(
                By.xpath("//*[@id=\"accordionFlushExample\"]/div[1]/div[1]/div")
            );
            waitHelper.waitForElementClickable(employeeDetails);
            js.executeScript("arguments[0].click();", employeeDetails);
            Thread.sleep(2000);
            
            // Click settings (three dots)
            WebElement settingsButton = driver.findElement(
                By.xpath("//*[@id=\"EmployeesBasicDetailsSection\"]/div/div[1]/a")
            );
            waitHelper.waitForElementClickable(settingsButton);
            js.executeScript("arguments[0].click();", settingsButton);
            Thread.sleep(500);
            
            // Click edit button
            WebElement editButton = driver.findElement(
                By.xpath("//*[@id=\"OpenEmployeeBasicDetailsEditPopup\"]")
            );
            waitHelper.waitForElementClickable(editButton);
            js.executeScript("arguments[0].click();", editButton);
            Thread.sleep(1000);
            
            // Enter employee ID
            WebElement employeeIdField = driver.findElement(
                By.xpath("//*[@id=\"ContactUniqueId\"]")
            );
            waitHelper.waitForElementClickable(employeeIdField);
            
            String newEmployeeId = (employeeId == null || employeeId.isEmpty()) 
                ? "EmpId" + (int)(Math.random() * 10000) 
                : employeeId;
            
            employeeIdField.clear();
            employeeIdField.sendKeys(newEmployeeId);
            Thread.sleep(500);
            
            // Click save
            WebElement saveButton = driver.findElement(
                By.xpath("//*[@id=\"SubmitBasicEditDetails\"]")
            );
            waitHelper.waitForElementClickable(saveButton);
            js.executeScript("arguments[0].click();", saveButton);
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to edit employee contact: " + e.getMessage());
        }
    }
    
    /**
     * Edit Other Party Contact Details
     * @param designation new designation to set
     */
    public void editOtherPartyContact(String designation) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Click the Other Parties details tab
            WebElement detailsTab = driver.findElement(
                By.xpath("//*[text()='Other Parties Details']")
            );
            waitHelper.waitForElementClickable(detailsTab);
            js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", detailsTab);
            Thread.sleep(1000);
            
            // Click settings (three dots) using JavaScript selector
            WebElement settingsButton = (WebElement) js.executeScript(
                "return document.querySelector('#OtherPartiesBasicDetailsSection > div > div.dropstart > a > i');"
            );
            js.executeScript("arguments[0].click();", settingsButton);
            Thread.sleep(500);
            
            // Click edit button
            WebElement editButton = driver.findElement(
                By.xpath("//*[@id=\"OpenOtherPartiesBasicDetailsEditPopup\"]")
            );
            waitHelper.waitForElementClickable(editButton);
            js.executeScript("arguments[0].click();", editButton);
            Thread.sleep(1000);
            
            // Enter designation
            WebElement designationField = driver.findElement(
                By.xpath("//*[@id=\"Designation\"]")
            );
            waitHelper.waitForElementClickable(designationField);
            
            String newDesignation = (designation == null || designation.isEmpty()) 
                ? "Designation" + (int)(Math.random() * 10000) 
                : designation;
            
            designationField.clear();
            designationField.sendKeys(newDesignation);
            Thread.sleep(500);
            
            // Click save using JavaScript selector
            WebElement saveButton = (WebElement) js.executeScript(
                "return document.querySelector('#SubmitBasicEditDetails');"
            );
            js.executeScript("arguments[0].click();", saveButton);
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to edit other party contact: " + e.getMessage());
        }
    }
    
    /**
     * Verify if contact exists in search results
     * @param contactName name to verify
     * @return true if contact found, false otherwise
     */
    public boolean isContactFound(String contactName) {
        try {
            WebElement tableBody = driver.findElement(By.id("dynamicTableBody"));
            String tableText = tableBody.getText();
            return tableText.contains(contactName);
        } catch (Exception e) {
            return false;
        }
    }
}
