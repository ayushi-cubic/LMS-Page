package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Adding Other Party Contact (Guarantor)
 */
public class AddOtherPartyContactPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private String createdOtherPartyName;
    
    // Contact Type Dropdown
    @FindBy(id = "ContactType")
    private WebElement contactTypeDropdown;
    
    // Name Field
    @FindBy(id = "Name")
    private WebElement nameField;
    
    // Email Field
    @FindBy(id = "EmailAddress1")
    private WebElement emailField;
    
    // Next Button
    @FindBy(id = "BasicInformationCollapse")
    private WebElement nextButton;
    
    // Save Button
    @FindBy(id = "btnSubmit")
    private WebElement saveButton;
    
    // Constructor
    public AddOtherPartyContactPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Select Other Party Contact Type (Guarantor)
     * Option value='4' represents Guarantor type
     */
    public void selectOtherPartyContactType() {
        try {
            waitHelper.waitForElementClickable(contactTypeDropdown);
            contactTypeDropdown.click();
            Thread.sleep(400);
            
            // Select Guarantor option (value='4')
            WebElement guarantorOption = driver.findElement(
                By.xpath("//*[@id=\"ContactType\"]/option[@value='4']")
            );
            guarantorOption.click();
            Thread.sleep(400);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select other party contact type: " + e.getMessage());
        }
    }
    
    /**
     * Enter other party name (generates random name if not provided)
     * @param otherPartyName other party name (null for random)
     */
    public void enterOtherPartyName(String otherPartyName) {
        try {
            waitHelper.waitForElementClickable(nameField);
            
            if (otherPartyName == null || otherPartyName.isEmpty()) {
                this.createdOtherPartyName = "OtherParty" + (int)(Math.random() * 10000);
            } else {
                this.createdOtherPartyName = otherPartyName;
            }
            
            nameField.clear();
            nameField.sendKeys(this.createdOtherPartyName);
            Thread.sleep(300);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter other party name: " + e.getMessage());
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
                emailToEnter = "other" + (int)(Math.random() * 10000) + "@example.com";
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
     * Fill complete other party contact form with random data
     */
    public void fillOtherPartyContactForm() {
        selectOtherPartyContactType();
        enterOtherPartyName(null);
        enterEmail(null);
        clickNext();
    }
    
    /**
     * Get the created other party name
     * @return other party name
     */
    public String getCreatedOtherPartyName() {
        return createdOtherPartyName;
    }
}
