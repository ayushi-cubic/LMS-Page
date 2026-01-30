package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Adding Firm/Company Contact
 */
public class AddFirmContactPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private String createdFirmName;
    
    // Contact Type Dropdown
    @FindBy(id = "ContactType")
    private WebElement contactTypeDropdown;
    
    // Name Field
    @FindBy(id = "Name")
    private WebElement nameField;
    
    // Email Field
    @FindBy(id = "EmailAddress1")
    private WebElement emailField;
    
    // Address Type Dropdown
    @FindBy(id = "AddressTypeIds")
    private WebElement addressTypeDropdown;
    
    // Address Field
    @FindBy(id = "txtAddress")
    private WebElement addressField;
    
    // Add Address Button
    @FindBy(id = "btnAddAddress")
    private WebElement addAddressButton;
    
    // Next Button (Basic Information Collapse)
    @FindBy(id = "BasicInformationCollapse")
    private WebElement nextButton;
    
    // Save Button
    @FindBy(id = "btnSubmit")
    private WebElement saveButton;
    
    // Constructor
    public AddFirmContactPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Select contact type from dropdown
     * @param optionValue option value or index
     */
    public void selectContactType(String optionValue) {
        try {
            waitHelper.waitForElementClickable(contactTypeDropdown);
            contactTypeDropdown.click();
            Thread.sleep(400);
            
            // Select option by value or by index (option[2] for first real option)
            WebElement option;
            if (optionValue.equals("firm") || optionValue.equals("2")) {
                option = driver.findElement(By.xpath("//select[@id='ContactType']/option[2]"));
            } else {
                option = driver.findElement(By.xpath("//select[@id='ContactType']/option[@value='" + optionValue + "']"));
            }
            
            option.click();
            Thread.sleep(400);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select contact type: " + e.getMessage());
        }
    }
    
    /**
     * Enter firm name (generates random name if not provided)
     * @param firmName firm name (null for random)
     */
    public void enterFirmName(String firmName) {
        try {
            waitHelper.waitForElementClickable(nameField);
            
            if (firmName == null || firmName.isEmpty()) {
                this.createdFirmName = "Firm" + (int)(Math.random() * 10000);
            } else {
                this.createdFirmName = firmName;
            }
            
            nameField.clear();
            nameField.sendKeys(this.createdFirmName);
            Thread.sleep(300);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter firm name: " + e.getMessage());
        }
    }
    
    /**
     * Enter email address (generates random email if not provided)
     * @param email email address (null for random)
     */
    public void enterEmail(String email) {
        try {
            waitHelper.waitForElementClickable(emailField);
            
            String emailToEnter;
            if (email == null || email.isEmpty()) {
                emailToEnter = "firm" + (int)(Math.random() * 10000) + "@example.com";
            } else {
                emailToEnter = email;
            }
            
            emailField.clear();
            emailField.sendKeys(emailToEnter);
            Thread.sleep(300);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter email: " + e.getMessage());
        }
    }
    
    /**
     * Click Next button to expand address section
     */
    public void clickNext() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            waitHelper.waitForElementClickable(nextButton);
            
            try {
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
     * Select address type
     * @param addressType address type or index
     */
    public void selectAddressType(String addressType) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", addressTypeDropdown);
            Thread.sleep(500);
            
            waitHelper.waitForElementClickable(addressTypeDropdown);
            addressTypeDropdown.click();
            Thread.sleep(300);
            
            // Select first option
            WebElement option = driver.findElement(
                By.xpath("//select[@id='AddressTypeIds']/option[2]")
            );
            option.click();
            Thread.sleep(300);
        } catch (Exception e) {
            // Address section might be optional
            System.out.println("Address type selection skipped: " + e.getMessage());
        }
    }
    
    /**
     * Enter address
     * @param address address text
     */
    public void enterAddress(String address) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            waitHelper.waitForElementClickable(addressField);
            js.executeScript("arguments[0].scrollIntoView(true);", addressField);
            Thread.sleep(200);
            
            addressField.clear();
            
            String addressToEnter = (address == null || address.isEmpty()) ? "123 Main St" : address;
            addressField.sendKeys(addressToEnter);
            Thread.sleep(300);
        } catch (Exception e) {
            // Address might be optional
            System.out.println("Address entry skipped: " + e.getMessage());
        }
    }
    
    /**
     * Click Add Address button
     */
    public void clickAddAddress() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            try {
                waitHelper.waitForElementClickable(addAddressButton);
                addAddressButton.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", addAddressButton);
            }
            
            Thread.sleep(500);
        } catch (Exception e) {
            // Address might be optional
            System.out.println("Add address button skipped: " + e.getMessage());
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
     * Fill complete firm contact form with random data
     */
    public void fillFirmContactForm() {
        selectContactType("2");
        enterFirmName(null);
        enterEmail(null);
        clickNext();
        // Address section is optional, commented out
        // selectAddressType(null);
        // enterAddress(null);
        // clickAddAddress();
    }
    
    /**
     * Get the created firm name
     * @return firm name
     */
    public String getCreatedFirmName() {
        return createdFirmName;
    }
}
