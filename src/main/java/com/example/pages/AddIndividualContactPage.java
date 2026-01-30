package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Adding Individual Contact (Advocate)
 */
public class AddIndividualContactPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private String createdIndividualName;
    
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
    public AddIndividualContactPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Select Individual Contact Type (Advocate)
     * Option[3] typically represents Advocate type
     */
    public void selectIndividualContactType() {
        try {
            waitHelper.waitForElementClickable(contactTypeDropdown);
            contactTypeDropdown.click();
            Thread.sleep(400);
            
            // Select option[3] for Advocate
            WebElement advocateOption = driver.findElement(
                By.xpath("//*[@id=\"ContactType\"]/option[3]")
            );
            advocateOption.click();
            Thread.sleep(400);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select individual contact type: " + e.getMessage());
        }
    }
    
    /**
     * Enter advocate name (generates random name if not provided)
     * @param advocateName advocate name (null for random)
     */
    public void enterAdvocateName(String advocateName) {
        try {
            waitHelper.waitForElementClickable(nameField);
            
            if (advocateName == null || advocateName.isEmpty()) {
                this.createdIndividualName = "Advocate" + (int)(Math.random() * 10000);
            } else {
                this.createdIndividualName = advocateName;
            }
            
            nameField.clear();
            nameField.sendKeys(this.createdIndividualName);
            Thread.sleep(300);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter advocate name: " + e.getMessage());
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
                emailToEnter = "adv" + (int)(Math.random() * 10000) + "@example.com";
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
     * Fill complete individual contact form with random data
     */
    public void fillIndividualContactForm() {
        selectIndividualContactType();
        enterAdvocateName(null);
        enterEmail(null);
        clickNext();
    }
    
    /**
     * Get the created individual/advocate name
     * @return advocate name
     */
    public String getCreatedIndividualName() {
        return createdIndividualName;
    }
}
