package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Customer Navigation Page
 */
public class CustomerNavigationPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    
    // Locators - Using original working XPaths
    private final By borrowersMenu = By.xpath("/html/body/div[3]/div/div/div/ul/li[2]/a");
    private final By customerSubmenu = By.xpath("/html/body/div[3]/div/div/div/ul/li[2]/ul/li[3]/a");
    private final By addNewCustomerButton = By.xpath("/html/body/div[2]/div/main/div/div/main/div/div[2]/div[1]/div/div[1]/div[2]/div/div[3]/a");
    
    @FindBy(xpath = "/html/body/div[2]/div/main/div/div/main/div/div[1]/a")
    private WebElement backButton;
    
    @FindBy(xpath = "//*[@id='CustomerSearchRecordsFilter']")
    private WebElement customerSearchField;
    
    @FindBy(xpath = "//*[@id='searchForm']/button")
    private WebElement searchButton;
    
    // Constructor
    public CustomerNavigationPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Click Borrowers menu
     */
    public void clickBorrowersMenu() {
        System.out.println("CustomerNavigationPage: Clicking Borrowers menu");
        try {
            // Wait for page to stabilize after login
            Thread.sleep(2000);
            
            // Try to find and click Borrowers menu
            waitHelper.clickWithWait(borrowersMenu);
            System.out.println("CustomerNavigationPage: Borrowers menu clicked successfully");
            Thread.sleep(1000); // Wait for submenu to appear
        } catch (Exception e) {
            System.err.println("CustomerNavigationPage: Failed to click Borrowers menu - " + e.getMessage());
            throw new RuntimeException("Failed to click Borrowers menu: " + e.getMessage());
        }
    }
    
    /**
     * Click Customer submenu
     */
    public void clickCustomerSubmenu() {
        System.out.println("CustomerNavigationPage: Clicking Customer submenu (li[3])");
        try {
            waitHelper.clickWithWait(customerSubmenu);
            System.out.println("CustomerNavigationPage: Customer submenu clicked successfully");
            Thread.sleep(1000); // Wait for page to load
        } catch (Exception e) {
            System.err.println("CustomerNavigationPage: Failed to click Customer submenu - " + e.getMessage());
            throw new RuntimeException("Failed to click Customer submenu: " + e.getMessage());
        }
    }
    
    /**
     * Click Add New Customer button
     */
    public void clickAddNewCustomer() {
        waitHelper.clickWithWait(addNewCustomerButton);
    }
    
    /**
     * Navigate to Add Customer page
     */
    public void navigateToAddCustomer() {
        clickBorrowersMenu();
        waitHelper.hardWait(1000); // Wait for submenu to appear
        clickCustomerSubmenu();
        waitHelper.hardWait(1000); // Wait for page to load
        clickAddNewCustomer();
    }
    
    /**
     * Click Back button to return to customer list
     */
    public void clickBackButton() {
        waitHelper.waitForElementClickable(backButton);
        backButton.click();
    }
    
    /**
     * Enter customer number in search field
     * @param customerNumber customer number to search
     */
    public void enterCustomerNumberInSearch(String customerNumber) {
        waitHelper.waitForElementVisible(customerSearchField);
        customerSearchField.clear();
        customerSearchField.sendKeys(customerNumber);
    }
    
    /**
     * Click Search button
     */
    public void clickSearchButton() {
        waitHelper.waitForElementClickable(searchButton);
        searchButton.click();
        System.out.println("Search button clicked");
        waitHelper.hardWait(3000); // Wait for search results to load
    }
    
    /**
     * Search for customer
     * @param customerNumber customer number
     */
    public void searchCustomer(String customerNumber) {
        enterCustomerNumberInSearch(customerNumber);
        clickSearchButton();
    }
    
    /**
     * Verify customer appears in search results
     * @param customerNumber customer number to verify
     * @return true if customer is found
     */
    public boolean isCustomerInSearchResults(String customerNumber) {
        try {
            System.out.println("Searching for customer: " + customerNumber);
            waitHelper.hardWait(2000); // Wait for search results
            
            // Try multiple possible xpaths for search results
            String[] xpaths = {
                "//td[contains(text(),'" + customerNumber + "')]",
                "//td[text()='" + customerNumber + "']",
                "//*[contains(text(),'" + customerNumber + "')]",
                "//table//td[contains(.,'" + customerNumber + "')]"
            };
            
            for (String xpath : xpaths) {
                try {
                    WebElement result = driver.findElement(By.xpath(xpath));
                    if (result.isDisplayed()) {
                        System.out.println("Customer found using xpath: " + xpath);
                        return true;
                    }
                } catch (Exception e) {
                    // Try next xpath
                }
            }
            
            System.out.println("Customer not found in search results");
            System.out.println("Page source contains customer number: " + driver.getPageSource().contains(customerNumber));
            return false;
        } catch (Exception e) {
            System.err.println("Error while verifying search results: " + e.getMessage());
            return false;
        }
    }
}
