package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for OTP Page
 */
public class OtpPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    
    // Locators
    @FindBy(id = "Otp")
    private WebElement otpField;
    
    @FindBy(id = "submitbtn")
    private WebElement submitButton;
    
    // Constructor
    public OtpPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Enter OTP
     * @param otp OTP code
     */
    public void enterOtp(String otp) {
        waitHelper.waitForElementVisible(otpField);
        otpField.clear();
        otpField.sendKeys(otp);
    }
    
    /**
     * Click Submit button
     */
    public void clickSubmit() {
        waitHelper.waitForElementClickable(submitButton);
        submitButton.click();
    }
    
    /**
     * Complete OTP verification
     * @param otp OTP code
     */
    public void verifyOtp(String otp) {
        enterOtp(otp);
        clickSubmit();
    }
    
    /**
     * Check if OTP page is displayed
     * @return true if OTP field is visible
     */
    public boolean isOtpPageDisplayed() {
        try {
            waitHelper.waitForElementVisible(otpField);
            return otpField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
