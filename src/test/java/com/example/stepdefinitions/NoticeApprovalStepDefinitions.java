package com.example.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.example.hooks.Hooks;
import com.example.pages.LoginPage;
import com.example.pages.LogoutPage;
import com.example.pages.NoticeApprovalPage;
import com.example.pages.NoticeDetailsPage;
import com.example.pages.OtpPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions for Notice Approval workflow
 */
public class NoticeApprovalStepDefinitions {
    
    private WebDriver driver;
    private LogoutPage logoutPage;
    private LoginPage loginPage;
    private OtpPage otpPage;
    private NoticeApprovalPage approvalPage;
    private NoticeDetailsPage detailsPage;
    
    public NoticeApprovalStepDefinitions() {
        this.driver = Hooks.getDriver();
        this.logoutPage = new LogoutPage(driver);
        this.loginPage = new LoginPage(driver);
        this.otpPage = new OtpPage(driver);
        this.approvalPage = new NoticeApprovalPage(driver);
        this.detailsPage = new NoticeDetailsPage(driver);
    }
    
    @Given("I logout from the current session")
    public void iLogoutFromTheCurrentSession() {
        logoutPage.logout();
    }
    
    @When("I login as {string} with password {string}")
    public void iLoginAsWithPassword(String username, String password) {
        System.out.println("========================================");
        System.out.println("STEP: I login as \"" + username + "\" with password \"" + password + "\"");
        System.out.println("Username received: " + username);
        System.out.println("Password received: " + password);
        System.out.println("========================================");
        loginPage.login(username, password);
    }
    
    @When("I enter OTP {string} for approval user")
    public void iEnterOTPForApprovalUser(String otp) {
        otpPage.enterOtp(otp);
        otpPage.clickSubmit();
    }
    
    @Given("I navigate to Advocate Allocation Approval section")
    public void iNavigateToAdvocateAllocationApprovalSection() {
        approvalPage.navigateToAdvocateAllocationApproval();
    }
    
    @When("I apply notice filter and navigate to last page")
    public void iApplyNoticeFilterAndNavigateToLastPage() {
        approvalPage.applyNoticeFilter();
    }
    
    @When("I approve the notice")
    public void iApproveTheNotice() {
        // Check for both Notice Unique ID (regular notices) and Case Unique ID (criminal notices)
        String noticeUniqueId = com.example.stepdefinitions.NoticeStepDefinitions.getCreatedNoticeUniqueId();
        String caseUniqueId = com.example.stepdefinitions.NoticeCriminalStepDefinitions.getCreatedCaseUniqueId();
        
        // Prefer Notice Unique ID if available, otherwise use Case Unique ID
        String uniqueId = null;
        if (noticeUniqueId != null && !noticeUniqueId.isEmpty()) {
            uniqueId = noticeUniqueId;
            System.out.println("========================================");
            System.out.println("APPROVING NOTICE WITH NOTICE UNIQUE ID: " + uniqueId);
            System.out.println("========================================");
        } else if (caseUniqueId != null && !caseUniqueId.isEmpty()) {
            uniqueId = caseUniqueId;
            System.out.println("========================================");
            System.out.println("APPROVING NOTICE WITH CASE UNIQUE ID: " + uniqueId);
            System.out.println("========================================");
        }
        
        if (uniqueId != null) {
            approvalPage.approveNotice(uniqueId);
        } else {
            System.out.println("⚠ No Unique ID found, approving first notice in list");
            approvalPage.approveNotice();
        }
    }
    
    @Then("the notice should be approved successfully")
    public void theNoticeShouldBeApprovedSuccessfully() {
        System.out.println("✓✓✓ NOTICE APPROVED SUCCESSFULLY ✓✓✓");
    }
    
    @When("I navigate to notice listing and view details")
    public void iNavigateToNoticeListingAndViewDetails() {
        detailsPage.viewNoticeDetails();
    }
    
    @Then("I should see the notice details page")
    public void iShouldSeeTheNoticeDetailsPage() {
        if (detailsPage.isNoticeDetailsPageLoaded()) {
            System.out.println("✓✓✓ NOTICE DETAILS VERIFICATION COMPLETE ✓✓✓");
        } else {
            throw new RuntimeException("Notice details page not loaded");
        }
    }
}
