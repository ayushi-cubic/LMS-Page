package com.example.stepdefinitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import com.example.hooks.Hooks;
import com.example.pages.CriminalCaseCreationPage;
import com.example.pages.LoginPage;
import com.example.pages.OtpPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions for Criminal Case Creation Feature
 */
public class CriminalCaseCreationStepDefinitions {
    
    private WebDriver driver;
    private LoginPage loginPage;
    private OtpPage otpPage;
    private CriminalCaseCreationPage criminalCaseCreationPage;
    private String capturedCaseId;
    
    // Constructor - WebDriver will be retrieved from Hooks
    public CriminalCaseCreationStepDefinitions() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
        this.otpPage = new OtpPage(driver);
        this.criminalCaseCreationPage = new CriminalCaseCreationPage(driver);
    }
    
    @Given("I am logged into the LMS application for criminal case creation")
    public void iAmLoggedIntoTheLMSApplicationForCriminalCaseCreation() {
        loginPage.openApplication("https://qalmsbu.cubictree.com/");
        loginPage.login("ayushi", "Legal@1234");
        otpPage.verifyOtp("123456");
        
        // Wait for login to complete
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Successfully logged in for criminal case creation");
    }
    
    @When("I click on Matters menu for criminal case")
    public void iClickOnMattersMenuForCriminalCase() {
        criminalCaseCreationPage.clickMattersMenu();
    }
    
    @And("I click on Case submenu for criminal case")
    public void iClickOnCaseSubmenuForCriminalCase() {
        criminalCaseCreationPage.clickCaseSubmenu();
    }
    
    @And("I click on Add New Case button for criminal case")
    public void iClickOnAddNewCaseButtonForCriminalCase() {
        criminalCaseCreationPage.clickAddNewCase();
    }
    
    @And("I click on Criminal button")
    public void iClickOnCriminalButton() {
        criminalCaseCreationPage.clickCriminal();
    }
    
    @And("I select random Case Type for criminal case")
    public void iSelectRandomCaseTypeForCriminalCase() {
        criminalCaseCreationPage.selectRandomCaseType();
    }
    
    @And("I select random Customer ID for criminal case")
    public void iSelectRandomCustomerIdForCriminalCase() {
        criminalCaseCreationPage.selectRandomCustomerId();
    }
    
    @And("I select random Account Number for criminal case")
    public void iSelectRandomAccountNumberForCriminalCase() {
        criminalCaseCreationPage.selectRandomAccountNumber();
    }
    
    @And("I enter Party name for criminal case")
    public void iEnterPartyNameForCriminalCase() {
        criminalCaseCreationPage.enterParty();
    }
    
    @And("I select random Issuing Party for criminal case")
    public void iSelectRandomIssuingPartyForCriminalCase() {
        criminalCaseCreationPage.selectRandomIssuingParty();
    }
    
    @And("I select random Respondent for criminal case")
    public void iSelectRandomRespondentForCriminalCase() {
        criminalCaseCreationPage.selectRandomRespondent();
    }
    
    @And("I select random Petitioner's Advocate for criminal case")
    public void iSelectRandomPetitionersAdvocateForCriminalCase() {
        criminalCaseCreationPage.selectRandomAdvocate();
    }
    
    @And("I enter random Case Number for criminal case")
    public void iEnterRandomCaseNumberForCriminalCase() {
        criminalCaseCreationPage.enterCaseNumber();
    }
    
    @And("I select random Priority for criminal case")
    public void iSelectRandomPriorityForCriminalCase() {
        criminalCaseCreationPage.selectRandomPriority();
    }
    
    @And("I select random Parties for criminal case")
    public void iSelectRandomPartiesForCriminalCase() {
        criminalCaseCreationPage.selectRandomParties();
    }
    
    @And("I click Next for Basic Details for criminal case")
    public void iClickNextForBasicDetailsForCriminalCase() {
        criminalCaseCreationPage.clickNextBasicDetails();
    }
    
    @And("I enter random Claim Amount for criminal case")
    public void iEnterRandomClaimAmountForCriminalCase() {
        criminalCaseCreationPage.enterClaimAmount();
    }
    
    @And("I click Next for Stake Details for criminal case")
    public void iClickNextForStakeDetailsForCriminalCase() {
        criminalCaseCreationPage.clickNextStakeDetails();
    }
    
    @And("I select Ayushi G from Sr Supervisor for criminal case")
    public void iSelectAyushiGFromSrSupervisorForCriminalCase() {
        criminalCaseCreationPage.selectSrSupervisor();
    }
    
    @And("I click Next for Owner Details for criminal case")
    public void iClickNextForOwnerDetailsForCriminalCase() {
        criminalCaseCreationPage.clickNextOwnerDetails();
    }
    
    @And("I click Next for Case Analysis for criminal case")
    public void iClickNextForCaseAnalysisForCriminalCase() {
        criminalCaseCreationPage.clickNextCaseAnalysis();
    }
    
    @And("I enter Registration Date for criminal case")
    public void iEnterRegistrationDateForCriminalCase() {
        criminalCaseCreationPage.enterRegistrationDate();
    }
    
    @And("I click Next for Important Dates for criminal case")
    public void iClickNextForImportantDatesForCriminalCase() {
        criminalCaseCreationPage.clickNextImpDates();
    }
    
    @And("I click Next for Cheque Details")
    public void iClickNextForChequeDetails() {
        criminalCaseCreationPage.clickNextChequeDetails();
    }
    
    @And("I click Next for Add FIR")
    public void iClickNextForAddFIR() {
        criminalCaseCreationPage.clickNextAddFIR();
    }
    
    @And("I click Next for Summon Details")
    public void iClickNextForSummonDetails() {
        criminalCaseCreationPage.clickNextSummonDetails();
    }
    
    @And("I click Next for Contingent Liability for criminal case")
    public void iClickNextForContingentLiabilityForCriminalCase() {
        criminalCaseCreationPage.clickNextContingentLiability();
    }
    
    @And("I click Save button for criminal case")
    public void iClickSaveButtonForCriminalCase() {
        criminalCaseCreationPage.clickSave();
    }
    
    @And("I capture the Criminal Case ID")
    public void iCaptureTheCriminalCaseId() {
        capturedCaseId = criminalCaseCreationPage.captureCaseId();
        Assertions.assertNotNull(capturedCaseId, "Criminal Case ID should be captured");
        System.out.println("Captured Criminal Case ID: " + capturedCaseId);
    }
    
    @And("I click user dropdown for criminal case")
    public void iClickUserDropdownForCriminalCase() {
        criminalCaseCreationPage.clickUserDropdown();
    }
    
    @And("I click Logout for criminal case")
    public void iClickLogoutForCriminalCase() {
        criminalCaseCreationPage.clickLogout();
    }
    
    @And("I login as {string} with password {string} and OTP {string} for criminal case")
    public void iLoginAsWithPasswordAndOTPForCriminalCase(String username, String password, String otp) {
        criminalCaseCreationPage.loginAs(username, password, otp);
    }
    
    @And("I click Actionable Items for criminal case")
    public void iClickActionableItemsForCriminalCase() {
        criminalCaseCreationPage.clickActionableItems();
    }
    
    @And("I click Approval tab for criminal case")
    public void iClickApprovalTabForCriminalCase() {
        criminalCaseCreationPage.clickApprovalTab();
    }
    
    @And("I click Advocate Allocation Approval for criminal case")
    public void iClickAdvocateAllocationApprovalForCriminalCase() {
        criminalCaseCreationPage.clickAdvocateAllocationApproval();
    }
    
    @And("I click Advance Filter for criminal case")
    public void iClickAdvanceFilterForCriminalCase() {
        criminalCaseCreationPage.clickAdvanceFilter();
    }
    
    @And("I click Apply button for criminal case")
    public void iClickApplyButtonForCriminalCase() {
        criminalCaseCreationPage.clickApply();
    }
    
    @And("I click Last page for criminal case")
    public void iClickLastPageForCriminalCase() {
        criminalCaseCreationPage.clickLastPage();
    }
    
    @And("I click criminal case checkbox")
    public void iClickCriminalCaseCheckbox() {
        criminalCaseCreationPage.clickApprovalCheckbox();
    }
    
    @And("I click Approve button for criminal case")
    public void iClickApproveButtonForCriminalCase() {
        criminalCaseCreationPage.clickApprove();
    }
    
    @And("I accept the alert for criminal case")
    public void iAcceptTheAlertForCriminalCase() {
        criminalCaseCreationPage.acceptAlert();
    }
    
    @When("I click Load Cases button for criminal case")
    public void iClickLoadCasesButtonForCriminalCase() {
        criminalCaseCreationPage.clickLoadCases();
    }
    
    @And("I click Action dropdown for criminal case")
    public void iClickActionDropdownForCriminalCase() {
        criminalCaseCreationPage.clickActionDropdown();
    }
    
    @Then("I click Details link from action for criminal case")
    public void iClickDetailsLinkFromActionForCriminalCase() {
        criminalCaseCreationPage.clickDetailsLink();
        System.out.println("===============================================");
        System.out.println("Criminal case creation workflow completed successfully");
        System.out.println("Case ID: " + criminalCaseCreationPage.getCapturedCaseId());
        System.out.println("===============================================");
    }
}
