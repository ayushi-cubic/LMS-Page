package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Adding Employee Contact
 */
public class AddEmployeeContactPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private String createdEmployeeName;
    
    // Contact Type Dropdown
    @FindBy(id = "ContactType")
    private WebElement contactTypeDropdown;
    
    // Name Field
    @FindBy(id = "Name")
    private WebElement nameField;
    
    // Next Button
    @FindBy(id = "BasicInformationCollapse")
    private WebElement nextButton;
    
    // Save Button
    @FindBy(id = "btnSubmit")
    private WebElement saveButton;
    
    // Constructor
    public AddEmployeeContactPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Select Employee Contact Type
     * Option value='6' represents Employee type
     */
    public void selectEmployeeContactType() {
        try {
            waitHelper.waitForElementClickable(contactTypeDropdown);
            contactTypeDropdown.click();
            Thread.sleep(400);
            
            // Select Employee option (value='6')
            WebElement employeeOption = driver.findElement(
                By.xpath("//*[@id=\"ContactType\"]/option[@value='6']")
            );
            employeeOption.click();
            Thread.sleep(400);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select employee contact type: " + e.getMessage());
        }
    }
    
    /**
     * Enter employee name (generates random name if not provided)
     * @param employeeName employee name (null for random)
     */
    public void enterEmployeeName(String employeeName) {
        try {
            waitHelper.waitForElementClickable(nameField);
            
            if (employeeName == null || employeeName.isEmpty()) {
                this.createdEmployeeName = "Employee" + (int)(Math.random() * 10000);
            } else {
                this.createdEmployeeName = employeeName;
            }
            
            nameField.clear();
            nameField.sendKeys(this.createdEmployeeName);
            Thread.sleep(300);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter employee name: " + e.getMessage());
        }
    }
    
    /**
     * Click Next button
     */
    public void clickNext() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            try {
                waitHelper.waitForElementClickable(nextButton);
                nextButton.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", nextButton);
            }
            
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Next button: " + e.getMessage());
        }
    }
    
    /**
     * Click Save button to create contact
     */
    public void clickSave() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            waitHelper.waitForElementClickable(saveButton);
            js.executeScript("arguments[0].click();", saveButton);
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Save button: " + e.getMessage());
        }
    }
    
    /**
     * Fill complete employee contact form with random data
     */
    public void fillEmployeeContactForm() {
        selectEmployeeContactType();
        enterEmployeeName(null);
        clickNext();
    }
    
    /**
     * Get the created employee name
     * @return employee name
     */
    public String getCreatedEmployeeName() {
        return createdEmployeeName;
    }
}
