package com.example.stepdefinitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import com.example.hooks.Hooks;
import com.example.pages.AddNoticePage;
import com.example.pages.LoginPage;
import com.example.pages.NoticeNavigationPage;
import com.example.pages.OtpPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions for Notice Management Feature
 */
public class NoticeStepDefinitions {
    
    private WebDriver driver;
    private LoginPage loginPage;
    private OtpPage otpPage;
    private NoticeNavigationPage navigationPage;
    private AddNoticePage addNoticePage;
    
    // Static variable to store Notice Unique ID for approval workflow
    private static String createdNoticeUniqueId = null;
    
    /**
     * Get the created Notice Unique ID
     * @return The Notice Unique ID from the last created notice
     */
    public static String getCreatedNoticeUniqueId() {
        return createdNoticeUniqueId;
    }
    
    // Constructor - WebDriver will be retrieved from Hooks
    public NoticeStepDefinitions() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
        this.otpPage = new OtpPage(driver);
        this.navigationPage = new NoticeNavigationPage(driver);
        this.addNoticePage = new AddNoticePage(driver);
    }
    
    @Given("I navigate to Matters menu for notices")
    public void iNavigateToMattersMenuForNotices() {
        navigationPage.clickMattersMenu();
    }
    
    @And("I click on Notice submenu")
    public void iClickOnNoticeSubmenu() {
        navigationPage.clickNoticeSubmenu();
    }
    
    @And("I click on Add New Notice button")
    public void iClickOnAddNewNoticeButton() {
        navigationPage.clickAddNewNotice();
    }
    
    @And("I click on Civil option from dropdown")
    public void iClickOnCivilOptionFromDropdown() {
        navigationPage.clickCivilOption();
        // Re-initialize the page object after navigation to ensure elements are located on the new page
        addNoticePage = new AddNoticePage(driver);
    }
    
    @When("I fill the notice form with random data")
    public void iFillTheNoticeFormWithRandomData(DataTable dataTable) {
        addNoticePage.fillNoticeFormWithRandomData();
        addNoticePage.printNoticeData();
    }
    
    @When("I fill the notice form with complete details")
    public void iFillTheNoticeFormWithCompleteDetails() {
        addNoticePage.fillNoticeFormWithRandomData();
        addNoticePage.printNoticeData();
    }
    
    @And("I navigate through all notice sections")
    public void iNavigateThroughAllNoticeSections() {
        addNoticePage.navigateThroughAllSections();
    }
    
    @And("I select Sr Supervisor")
    public void iSelectSrSupervisor() {
        addNoticePage.selectSrSupervisor();
    }
    
    @And("I click Create to submit the notice")
    public void iClickCreateToSubmitTheNotice() {
        addNoticePage.clickNextSection3();
        addNoticePage.clickCreateButton();
    }
    
    @Then("the notice should be created successfully")
    public void theNoticeShouldBeCreatedSuccessfully() {
        // Wait for notice creation to complete
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Capture Notice Unique ID
        addNoticePage.verifyNoticeCreation();
        java.util.Map<String, String> noticeData = addNoticePage.getNoticeData();
        if (noticeData.containsKey("NoticeUniqueId")) {
            createdNoticeUniqueId = noticeData.get("NoticeUniqueId");
            System.out.println("✓✓✓ STORED NOTICE UNIQUE ID FOR APPROVAL: " + createdNoticeUniqueId + " ✓✓✓");
        } else {
            System.out.println("⚠⚠⚠ WARNING: Notice Unique ID was not captured! ⚠⚠⚠");
        }
        
        // Verify notice creation
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after notice creation: " + currentUrl);
        
        // Basic verification - page should not show error
        Assertions.assertFalse(currentUrl.contains("error"), 
            "Notice creation failed - error page detected");
        
        System.out.println("✓ Notice created successfully");
        
        // Perform logout to continue with approval workflow
        System.out.println("Starting logout process to continue with approval workflow...");
        try {
            Thread.sleep(2000);
            
            // Switch to default content
            driver.switchTo().defaultContent();
            
            // Scroll to top
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1000);
            
            // Click Ayushi G link
            org.openqa.selenium.By ayushiLink = org.openqa.selenium.By.xpath("/html/body/header/div/div[2]/a");
            org.openqa.selenium.WebElement ayushiElement = driver.findElement(ayushiLink);
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", ayushiElement);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", ayushiElement);
            Thread.sleep(1500);
            System.out.println("✓ Clicked on Ayushi G");
            
            // Click Logout link
            org.openqa.selenium.By logoutLink = org.openqa.selenium.By.xpath("/html/body/header/div/div[2]/div[2]/div/div/div[1]/div/ul/li[2]/a");
            org.openqa.selenium.WebElement logoutElement = driver.findElement(logoutLink);
            js.executeScript("arguments[0].click();", logoutElement);
            Thread.sleep(3000);
            System.out.println("✓ Clicked Logout - Login page should now be visible");
            
        } catch (Exception e) {
            System.out.println("⚠ Error during logout: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Then("the notice should be saved successfully")
    public void theNoticeShouldBeSavedSuccessfully() {
        // Wait for notice save to complete
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Verify notice save
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after notice save: " + currentUrl);
        
        // Basic verification
        Assertions.assertFalse(currentUrl.contains("error"), 
            "Notice save failed - error page detected");
        
        System.out.println("✓ Notice saved successfully");
    }
    
    @And("I click Next on notice section {int}")
    public void iClickNextOnNoticeSection(Integer sectionNumber) {
        switch (sectionNumber) {
            case 1:
                addNoticePage.clickNextSection1();
                break;
            case 2:
                addNoticePage.clickNextSection2();
                break;
            case 3:
                addNoticePage.clickNextSection3();
                break;
            default:
                throw new IllegalArgumentException("Invalid section number: " + sectionNumber);
        }
    }
}
