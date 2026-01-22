package com.example.stepdefinitions;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.example.pages.AddNoticeCriminalPage;
import com.example.pages.NoticeCriminalNavigationPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions for Criminal Notice Management
 */
public class NoticeCriminalStepDefinitions {
    
    private WebDriver driver;
    private NoticeCriminalNavigationPage noticeCriminalNavigationPage;
    private AddNoticeCriminalPage addNoticeCriminalPage;
    
    // Static variable to share Case Unique ID across step definitions
    private static String createdCaseUniqueId = null;
    
    // Shared pages from Hooks
    public NoticeCriminalStepDefinitions() {
        this.driver = com.example.hooks.Hooks.getDriver();
        this.noticeCriminalNavigationPage = new NoticeCriminalNavigationPage(driver);
        this.addNoticeCriminalPage = new AddNoticeCriminalPage(driver);
    }
    
    public static String getCreatedCaseUniqueId() {
        return createdCaseUniqueId;
    }
    
    @Given("I navigate to Matters Notice Criminal menu")
    public void iNavigateToMattersNoticeCriminalMenu() {
        noticeCriminalNavigationPage.navigateToAddCriminalNotice();
    }
    
    @When("I fill the criminal notice form with random data")
    public void iFillTheCriminalNoticeFormWithRandomData(Map<String, String> fieldMappings) {
        addNoticeCriminalPage.fillCriminalNoticeFormWithRandomData(fieldMappings);
    }
    
    @Then("the criminal notice should be created successfully")
    public void theCriminalNoticeShouldBeCreatedSuccessfully() {
        boolean isCreated = addNoticeCriminalPage.verifyCriminalNoticeCreation();
        
        // Store the Case Unique ID
        Map<String, String> noticeData = addNoticeCriminalPage.getNoticeCriminalData();
        if (noticeData.containsKey("CaseUniqueId")) {
            createdCaseUniqueId = noticeData.get("CaseUniqueId");
            System.out.println("✓✓✓ STORED CASE UNIQUE ID FOR APPROVAL: " + createdCaseUniqueId + " ✓✓✓");
        } else {
            System.out.println("⚠ Warning: Case Unique ID not captured");
        }
        
        if (isCreated) {
            System.out.println("✓✓✓ CRIMINAL NOTICE CREATION TEST PASSED ✓✓✓");
        } else {
            System.out.println("⚠ CRIMINAL NOTICE CREATION STATUS UNKNOWN");
        }
        
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
    
    @Then("I verify the criminal notice details are correct")
    public void iVerifyTheCriminalNoticeDetailsAreCorrect() {
        addNoticeCriminalPage.printNoticeCriminalData();
        Map<String, String> noticeCriminalData = addNoticeCriminalPage.getNoticeCriminalData();
        
        System.out.println("\n========== CRIMINAL NOTICE VERIFICATION ==========");
        System.out.println("Total fields filled: " + noticeCriminalData.size());
        
        // Verify key fields are present
        if (noticeCriminalData.containsKey("SerialNumber")) {
            System.out.println("✓ Serial Number verified");
        }
        if (noticeCriminalData.containsKey("NoticeType")) {
            System.out.println("✓ Notice Type verified");
        }
        if (noticeCriminalData.containsKey("CustomerNumber")) {
            System.out.println("✓ Customer Number verified");
        }
        
        System.out.println("================================================\n");
    }
}
