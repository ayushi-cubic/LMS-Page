package com.example.pages;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Login Page
 */
public class LoginPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    
    // Locators using @FindBy annotation
    @FindBy(id = "USEREMAILID")
    private WebElement emailField;
    
    @FindBy(id = "USERPASSWORD")
    private WebElement passwordField;
    
    @FindBy(id = "submitbtn")
    private WebElement signInButton;
    
    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Open the application URL
     * @param url application URL
     */
    public void openApplication(String url) {
        try {
            driver.get(url);
            driver.manage().window().maximize();
        } catch (NoSuchWindowException e) {
            java.util.Set<String> handles = driver.getWindowHandles();
            if (!handles.isEmpty()) {
                driver.switchTo().window(handles.iterator().next());
                driver.get(url);
                driver.manage().window().maximize();
            } else {
                throw e;
            }
        }
    }
    
    /**
     * Enter email/username
     * @param email email or username
     */
    public void enterEmail(String email) {
        waitHelper.waitForElementVisible(emailField);
        emailField.clear();
        emailField.sendKeys(email);
    }
    
    /**
     * Enter password
     * @param password user password
     */
    public void enterPassword(String password) {
        waitHelper.waitForElementVisible(passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    
    /**
     * Click Sign In button
     */
    public void clickSignIn() {
        waitHelper.waitForElementClickable(signInButton);
        signInButton.click();
    }
    
    /**
     * Complete login flow
     * @param email user email
     * @param password user password
     */
    public void login(String email, String password) {
        System.out.println("LoginPage.login() called with:");
        System.out.println("  Email/Username: [" + email + "]");
        System.out.println("  Password: [" + password + "]");
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
    }
    
    /**
     * Check if login page is displayed
     * @return true if email field is visible
     */
    public boolean isLoginPageDisplayed() {
        try {
            waitHelper.waitForElementVisible(emailField);
            return emailField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
