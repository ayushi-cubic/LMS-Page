package com.example.stepdefinitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import com.example.hooks.Hooks;
import com.example.pages.CivilCaseCreationPage;
import com.example.pages.LoginPage;
import com.example.pages.OtpPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions for Civil Case Creation Feature
 */
public class CivilCaseCreationStepDefinitions {
    
    private WebDriver driver;
    private LoginPage loginPage;
    private OtpPage otpPage;
    private CivilCaseCreationPage civilCaseCreationPage;
    private String capturedSystemId;
    
    // Constructor - WebDriver will be retrieved from Hooks
    public CivilCaseCreationStepDefinitions() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
        this.otpPage = new OtpPage(driver);
        this.civilCaseCreationPage = new CivilCaseCreationPage(driver);
    }
    
    @Given("I am logged into the LMS application for civil case creation")
    public void iAmLoggedIntoTheLMSApplicationForCivilCaseCreation() {
        loginPage.openApplication("https://qalmsbu.cubictree.com/");
        loginPage.login("ayushi", "Legal@1234");
        otpPage.verifyOtp("123456");
        
        // Wait for login to complete
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Successfully logged in for civil case creation");
    }
    
    @When("I click on Matters menu")
    public void iClickOnMattersMenu() {
        civilCaseCreationPage.clickMattersMenu();
    }
    
    @And("I click on Case submenu")
    public void iClickOnCaseSubmenu() {
        civilCaseCreationPage.clickCaseSubmenu();
    }
    
    @And("I click on Add New Case button")
    public void iClickOnAddNewCaseButton() {
        civilCaseCreationPage.clickAddNewCase();
    }
    
    @And("I click on Civil button")
    public void iClickOnCivilButton() {
        civilCaseCreationPage.clickCivil();
    }
    
    @And("I select random Case Type")
    public void iSelectRandomCaseType() {
        civilCaseCreationPage.selectRandomCaseType();
    }
    
    @And("I select random Customer ID")
    public void iSelectRandomCustomerId() {
        civilCaseCreationPage.selectRandomCustomerId();
    }
    
    @And("I select random Account Number")
    public void iSelectRandomAccountNumber() {
        civilCaseCreationPage.selectRandomAccountNumber();
    }
    
    @And("I enter Party name")
    public void iEnterPartyName() {
        civilCaseCreationPage.enterParty();
    }
    
    @And("I select random Issuing Party")
    public void iSelectRandomIssuingParty() {
        civilCaseCreationPage.selectRandomIssuingParty();
    }
    
    @And("I select random Respondent")
    public void iSelectRandomRespondent() {
        civilCaseCreationPage.selectRandomRespondent();
    }
    
    @And("I select random Petitioner's Advocate")
    public void iSelectRandomPetitionersAdvocate() {
        civilCaseCreationPage.selectRandomAdvocate();
    }
    
    @And("I enter random Case Number")
    public void iEnterRandomCaseNumber() {
        civilCaseCreationPage.enterCaseNumber();
    }
    
    @And("I select random Priority")
    public void iSelectRandomPriority() {
        civilCaseCreationPage.selectRandomPriority();
    }
    
    @And("I select random Parties")
    public void iSelectRandomParties() {
        civilCaseCreationPage.selectRandomParties();
    }
    
    @And("I click Next for Basic Details")
    public void iClickNextForBasicDetails() {
        civilCaseCreationPage.clickNextBasicDetails();
    }
    
    @And("I enter random Claim Amount")
    public void iEnterRandomClaimAmount() {
        civilCaseCreationPage.enterClaimAmount();
    }
    
    @And("I click Next for Stake Details")
    public void iClickNextForStakeDetails() {
        civilCaseCreationPage.clickNextStakeDetails();
    }
    
    @And("I select Ayushi G from Sr Supervisor")
    public void iSelectAyushiGFromSrSupervisor() {
        civilCaseCreationPage.selectSrSupervisor();
    }
    
    @And("I click Next for Owner Details")
    public void iClickNextForOwnerDetails() {
        civilCaseCreationPage.clickNextOwnerDetails();
    }
    
    @And("I click Next for Case Analysis")
    public void iClickNextForCaseAnalysis() {
        civilCaseCreationPage.clickNextCaseAnalysis();
    }
    
    @And("I enter Registration Date")
    public void iEnterRegistrationDate() {
        civilCaseCreationPage.enterRegistrationDate();
    }
    
    @And("I click Next for Important Dates")
    public void iClickNextForImportantDates() {
        civilCaseCreationPage.clickNextImpDates();
    }
    
    @And("I click Next for Contingent Liability")
    public void iClickNextForContingentLiability() {
        civilCaseCreationPage.clickNextContingentLiability();
    }
    
    @And("I click Save button for civil case")
    public void iClickSaveButtonForCivilCase() {
        civilCaseCreationPage.clickSave();
    }
    
    @And("I capture the System ID")
    public void iCaptureTheSystemId() {
        capturedSystemId = civilCaseCreationPage.captureSystemId();
        Assertions.assertNotNull(capturedSystemId, "System ID should be captured");
        System.out.println("Captured System ID: " + capturedSystemId);
    }
    
    @And("I click Back button")
    public void iClickBackButton() {
        civilCaseCreationPage.clickBack();
    }
    
    @And("I search for the captured System ID")
    public void iSearchForTheCapturedSystemId() {
        civilCaseCreationPage.searchSystemId();
    }
    
    @And("I click Action button")
    public void iClickActionButton() {
        civilCaseCreationPage.clickAction();
    }
    
    @Then("I click Details link")
    public void iClickDetailsLink() {
        civilCaseCreationPage.clickDetails();
        System.out.println("Civil case creation workflow completed successfully");
    }
}
