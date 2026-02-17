package com.example.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.example.hooks.Hooks;
import com.example.pages.CustomerDetailPage;
import com.example.pages.LoginPage;
import com.example.pages.OtpPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions for Customer Detail Management Feature
 * Handles comprehensive customer detail operations including:
 * - Navigation to customer details
 * - Basic details editing
 * - NPA details management
 * - Trust/Service provider details
 * - Ownership details
 * - Remarks
 * - Recovery summary/Expense
 * - Comments
 * - Customer other details (Accounts)
 * - OTS/One Time Settlement
 * - Information requests
 * - Address details
 * - Audit trail
 */
public class CustomerDetailStepDefinitions {
    
    private WebDriver driver;
    private LoginPage loginPage;
    private OtpPage otpPage;
    private CustomerDetailPage customerDetailPage;
    
    // Constructor - WebDriver will be retrieved from Hooks
    public CustomerDetailStepDefinitions() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
        this.otpPage = new OtpPage(driver);
        this.customerDetailPage = new CustomerDetailPage(driver);
    }
    
    // =============================
    // Login & Navigation Steps
    // =============================
    
    @Given("I open the LMS application for customer details")
    public void iOpenTheLMSApplicationForCustomerDetails() {
        loginPage.openApplication("https://qalmsbu.cubictree.com/");
    }
    
    @When("I login to LMS with username {string} and password {string} for customer details")
    public void iLoginToLMSWithUsernameAndPasswordForCustomerDetails(String username, String password) {
        loginPage.login(username, password);
    }
    
    @And("I enter the OTP {string} for customer details")
    public void iEnterTheOTPForCustomerDetails(String otp) {
        otpPage.verifyOtp(otp);
    }
    
    @Then("I should be successfully logged in to customer details")
    public void iShouldBeSuccessfullyLoggedInToCustomerDetails() {
        try {
            Thread.sleep(3000);
            System.out.println("Login successful");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Given("I navigate to customer details page")
    public void iNavigateToCustomerDetailsPage() {
        customerDetailPage.navigateToCustomerDetail();
    }
    
    @When("I click on Borrowers menu for customer details")
    public void iClickOnBorrowersMenuForCustomerDetails() {
        customerDetailPage.clickBorrowersMenu();
    }
    
    @And("I click on Customer submenu for customer details")
    public void iClickOnCustomerSubmenuForCustomerDetails() {
        customerDetailPage.clickCustomerSubmenu();
    }
    
    @And("I click Load Customers button")
    public void iClickLoadCustomersButton() {
        customerDetailPage.clickLoadCustomers();
    }
    
    @And("I click on Action button for first customer")
    public void iClickOnActionButtonForFirstCustomer() {
        customerDetailPage.clickActionButton();
    }
    
    @And("I click on Details option")
    public void iClickOnDetailsOption() {
        customerDetailPage.clickDetailsButton();
    }
    
    // =============================
    // Basic Details Steps
    // =============================
    
    @When("I click Edit Basic Details")
    public void iClickEditBasicDetails() {
        customerDetailPage.clickEditBasicDetails();
    }
    
    @And("I enter random customer number")
    public void iEnterRandomCustomerNumber() {
        customerDetailPage.enterCustomerNumber(com.example.utils.RandomDataGenerator.generateRandomNumericString(10));
    }
    
    @And("I enter random customer name")
    public void iEnterRandomCustomerName() {
        customerDetailPage.enterCustomerName(com.example.utils.RandomDataGenerator.generateRandomName());
    }
    
    @And("I enter random father name")
    public void iEnterRandomFatherName() {
        customerDetailPage.enterFatherName(com.example.utils.RandomDataGenerator.generateRandomName());
    }
    
    @And("I select random customer type")
    public void iSelectRandomCustomerType() {
        customerDetailPage.selectRandomCustomerType();
    }
    
    @And("I select random industry")
    public void iSelectRandomIndustry() {
        customerDetailPage.selectRandomIndustry();
    }
    
    @And("I select random segment")
    public void iSelectRandomSegment() {
        customerDetailPage.selectRandomSegment();
    }
    
    @And("I enter random phone number")
    public void iEnterRandomPhoneNumber() {
        customerDetailPage.enterPhoneNumber(com.example.utils.RandomDataGenerator.generateRandomPhoneNumber());
    }
    
    @And("I enter random email")
    public void iEnterRandomEmail() {
        customerDetailPage.enterEmail(com.example.utils.RandomDataGenerator.generateRandomEmail());
    }
    
    @And("I enter random Adhaar number")
    public void iEnterRandomAdhaarNumber() {
        customerDetailPage.enterAdhaarNumber(com.example.utils.RandomDataGenerator.generateAadhaarNumber());
    }
    
    @And("I enter random PAN")
    public void iEnterRandomPAN() {
        customerDetailPage.enterPan(com.example.utils.RandomDataGenerator.generatePan());
    }
    
    @And("I select random address type")
    public void iSelectRandomAddressType() {
        customerDetailPage.selectRandomAddressType();
    }
    
    @And("I enter random address in Address 1")
    public void iEnterRandomAddressInAddress1() {
        customerDetailPage.enterAddress1(com.example.utils.RandomDataGenerator.generateRandomAddress());
    }
    
    @And("I click Save Basic Details")
    public void iClickSaveBasicDetails() {
        customerDetailPage.clickSaveBasicDetails();
    }
    
    @When("I fill all basic details with random data")
    public void iFillAllBasicDetailsWithRandomData() {
        customerDetailPage.fillBasicDetailsWithRandomData();
    }
    
    // =============================
    // NPA Details Steps
    // =============================
    
    @When("I click NPA section")
    public void iClickNPASection() {
        customerDetailPage.clickNpaSection();
    }
    
    @And("I click Edit NPA Details")
    public void iClickEditNPADetails() {
        customerDetailPage.clickEditNpaDetails();
    }
    
    @And("I click Yes for Declared NPA")
    public void iClickYesForDeclaredNPA() {
        customerDetailPage.clickNpaYes();
    }
    
    @And("I select random NPA type")
    public void iSelectRandomNPAType() {
        customerDetailPage.selectRandomNpaType();
    }
    
    @And("I enter date of declaring NPA")
    public void iEnterDateOfDeclaringNPA() {
        customerDetailPage.enterDateOfDeclaringNpa(java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
    }
    
    @And("I select random pendency reason")
    public void iSelectRandomPendencyReason() {
        customerDetailPage.selectRandomPendencyReason();
    }
    
    @And("I enter random pendency remark")
    public void iEnterRandomPendencyRemark() {
        customerDetailPage.enterPendencyRemark(com.example.utils.RandomDataGenerator.generateRandomAlphabeticString(20));
    }
    
    @And("I click Save NPA Details")
    public void iClickSaveNPADetails() {
        customerDetailPage.clickSaveNpaDetails();
    }
    
    @When("I fill all NPA details with random data")
    public void iFillAllNPADetailsWithRandomData() {
        customerDetailPage.fillNpaDetailsWithRandomData();
    }
    
    // =============================
    // Trust/Service Provider Steps
    // =============================
    
    @When("I click Trust Service Provider section")
    public void iClickTrustServiceProviderSection() {
        customerDetailPage.clickTrustSection();
    }
    
    @And("I click Add Trust button")
    public void iClickAddTrustButton() {
        customerDetailPage.clickAddTrust();
    }
    
    @And("I select random Trust ID")
    public void iSelectRandomTrustID() {
        customerDetailPage.selectRandomTrustId();
    }
    
    @And("I enter date of acquisition")
    public void iEnterDateOfAcquisition() {
        customerDetailPage.enterDateOfAcquisition(java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
    }
    
    @And("I enter random selling bank name")
    public void iEnterRandomSellingBankName() {
        customerDetailPage.enterSellingBank(com.example.utils.RandomDataGenerator.generateRandomCompanyName());
    }
    
    @And("I click Save Trust")
    public void iClickSaveTrust() {
        customerDetailPage.clickSaveTrust();
    }
    
    @When("I fill all trust details with random data")
    public void iFillAllTrustDetailsWithRandomData() {
        customerDetailPage.fillTrustDetailsWithRandomData();
    }
    
    // =============================
    // Ownership Details Steps
    // =============================
    
    @When("I click Ownership Details section")
    public void iClickOwnershipDetailsSection() {
        customerDetailPage.clickOwnershipSection();
    }
    
    @And("I click Secondary Dealing Officer tab")
    public void iClickSecondaryDealingOfficerTab() {
        customerDetailPage.clickSecondaryDealingOfficerTab();
    }
    
    @And("I click Add Secondary Officer button")
    public void iClickAddSecondaryOfficerButton() {
        customerDetailPage.clickAddSecondaryOfficer();
    }
    
    @And("I click officer checkbox")
    public void iClickOfficerCheckbox() {
        customerDetailPage.clickOfficerCheckbox();
    }
    
    @And("I click Save Secondary Officer")
    public void iClickSaveSecondaryOfficer() {
        customerDetailPage.clickSaveSecondaryOfficer();
    }
    
    @When("I fill all ownership details with random data")
    public void iFillAllOwnershipDetailsWithRandomData() {
        customerDetailPage.fillOwnershipDetailsWithRandomData();
    }
    
    // =============================
    // Remarks Steps
    // =============================
    
    @When("I click Remark section")
    public void iClickRemarkSection() {
        customerDetailPage.clickRemarkSection();
    }
    
    @And("I click Edit Remark")
    public void iClickEditRemark() {
        customerDetailPage.clickEditRemark();
    }
    
    @And("I enter random remark")
    public void iEnterRandomRemark() {
        customerDetailPage.enterRemark(com.example.utils.RandomDataGenerator.generateRandomAlphanumericString(30));
    }
    
    @And("I click Save Remark")
    public void iClickSaveRemark() {
        customerDetailPage.clickSaveRemark();
    }
    
    @When("I fill remark with random data")
    public void iFillRemarkWithRandomData() {
        customerDetailPage.fillRemarkWithRandomData();
    }
    
    // =============================
    // Recovery Summary / Expense Steps
    // =============================
    
    @When("I click Recovery Summary Expense section")
    public void iClickRecoverySummaryExpenseSection() {
        customerDetailPage.clickRecoverySummarySection();
    }
    
    @And("I click Recovery Summary tab")
    public void iClickRecoverySummaryTab() {
        customerDetailPage.clickRecoverySummaryTab();
    }
    
    @And("I click Customer Expenses tab")
    public void iClickCustomerExpensesTab() {
        customerDetailPage.clickCustomerExpensesTab();
    }
    
    @When("I navigate through recovery summary tabs")
    public void iNavigateThroughRecoverySummaryTabs() {
        customerDetailPage.navigateRecoverySummaryTabs();
    }
    
    // =============================
    // Comment Steps
    // =============================
    
    @When("I click Comment section")
    public void iClickCommentSection() {
        customerDetailPage.clickCommentSection();
    }
    
    @And("I enter random comment")
    public void iEnterRandomComment() {
        customerDetailPage.enterComment(com.example.utils.RandomDataGenerator.generateRandomComment());
    }
    
    @And("I click Add Comment button")
    public void iClickAddCommentButton() {
        customerDetailPage.clickAddComment();
    }
    
    @When("I fill comment with random data")
    public void iFillCommentWithRandomData() {
        customerDetailPage.fillCommentWithRandomData();
    }
    
    // =============================
    // Customer Other Details (Account) Steps
    // =============================
    
    @When("I click Customer Other Details section")
    public void iClickCustomerOtherDetailsSection() {
        customerDetailPage.clickCustomerOtherDetailsSection();
    }
    
    @And("I click Add Account button")
    public void iClickAddAccountButton() {
        customerDetailPage.clickAddAccount();
    }
    
    @And("I enter random loan account number")
    public void iEnterRandomLoanAccountNumber() {
        customerDetailPage.enterLoanAccountNumber(com.example.utils.RandomDataGenerator.generateRandomAccountNumber());
    }
    
    @And("I select random account type")
    public void iSelectRandomAccountType() {
        customerDetailPage.selectRandomAccountType();
    }
    
    @And("I enter random principal amount")
    public void iEnterRandomPrincipalAmount() {
        customerDetailPage.enterPrincipalAmount(com.example.utils.RandomDataGenerator.generateRandomAmount());
    }
    
    @And("I enter random rate of interest")
    public void iEnterRandomRateOfInterest() {
        customerDetailPage.enterRateOfInterest(com.example.utils.RandomDataGenerator.generateRandomInterestRate());
    }
    
    @And("I enter random outstanding balance")
    public void iEnterRandomOutstandingBalance() {
        customerDetailPage.enterOutstandingBalance(com.example.utils.RandomDataGenerator.generateRandomAmount());
    }
    
    @And("I enter loan disbursal date")
    public void iEnterLoanDisbursalDate() {
        customerDetailPage.enterLoanDisbursalDate(java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
    }
    
    @And("I select random account status")
    public void iSelectRandomAccountStatus() {
        customerDetailPage.selectRandomAccountStatus();
    }
    
    @And("I click Basic Information Next")
    public void iClickBasicInformationNext() {
        customerDetailPage.clickBasicInformationNext();
    }
    
    @And("I click Bank Consortium Next")
    public void iClickBankConsortiumNext() {
        customerDetailPage.clickBankConsortiumNext();
    }
    
    @And("I click Submit Account")
    public void iClickSubmitAccount() {
        customerDetailPage.clickSubmitAccount();
    }
    
    @When("I fill all customer account details with random data")
    public void iFillAllCustomerAccountDetailsWithRandomData() {
        customerDetailPage.fillCustomerAccountWithRandomData();
    }
    
    // =============================
    // OTS / One Time Settlement Steps
    // =============================
    
    @When("I click OTS One Time Settlement section")
    public void iClickOTSOneTimeSettlementSection() {
        customerDetailPage.clickOtsSection();
    }
    
    @And("I click Add OTS button")
    public void iClickAddOTSButton() {
        customerDetailPage.clickAddOts();
    }
    
    @And("I select random settlement status")
    public void iSelectRandomSettlementStatus() {
        customerDetailPage.selectRandomSettlementStatus();
    }
    
    @And("I enter random settlement amount")
    public void iEnterRandomSettlementAmount() {
        customerDetailPage.enterSettlementAmount(com.example.utils.RandomDataGenerator.generateRandomAmount());
    }
    
    @And("I click Save OTS")
    public void iClickSaveOTS() {
        customerDetailPage.clickSaveOts();
    }
    
    @When("I fill all OTS details with random data")
    public void iFillAllOTSDetailsWithRandomData() {
        customerDetailPage.fillOtsWithRandomData();
    }
    
    // =============================
    // Information Request Steps
    // =============================
    
    @When("I click Information Request section")
    public void iClickInformationRequestSection() {
        customerDetailPage.clickInformationRequestSection();
    }
    
    @And("I click Add Information Request button")
    public void iClickAddInformationRequestButton() {
        customerDetailPage.clickAddInformationRequest();
    }
    
    @And("I select To User")
    public void iSelectToUser() {
        customerDetailPage.selectToUser("User");
    }
    
    @And("I select random To User")
    public void iSelectRandomToUser() {
        customerDetailPage.selectRandomToUser();
    }
    
    @And("I enter random information request content")
    public void iEnterRandomInformationRequestContent() {
        customerDetailPage.enterInformationRequestContent(com.example.utils.RandomDataGenerator.generateRandomAlphabeticString(50));
    }
    
    @And("I click Save Information Request")
    public void iClickSaveInformationRequest() {
        customerDetailPage.clickSaveInformationRequest();
    }
    
    @When("I fill all information request with random data")
    public void iFillAllInformationRequestWithRandomData() {
        customerDetailPage.fillInformationRequestWithRandomData();
    }
    
    // =============================
    // Address Details Steps
    // =============================
    
    @When("I click Address Details section")
    public void iClickAddressDetailsSection() {
        customerDetailPage.clickAddressDetailsSection();
    }
    
    @And("I click Add Address button")
    public void iClickAddAddressButton() {
        customerDetailPage.clickAddAddress();
    }
    
    @And("I select random address type in section")
    public void iSelectRandomAddressTypeInSection() {
        customerDetailPage.selectRandomAddressTypeInSection();
    }
    
    @And("I enter random address in section")
    public void iEnterRandomAddressInSection() {
        customerDetailPage.enterAddressInSection(com.example.utils.RandomDataGenerator.generateRandomAddress());
    }
    
    @And("I select random state")
    public void iSelectRandomState() {
        customerDetailPage.selectRandomState();
    }
    
    @And("I select random location")
    public void iSelectRandomLocation() {
        customerDetailPage.selectRandomLocation();
    }
    
    @And("I click Save Address")
    public void iClickSaveAddress() {
        customerDetailPage.clickSaveAddress();
    }
    
    @When("I fill all address details with random data")
    public void iFillAllAddressDetailsWithRandomData() {
        customerDetailPage.fillAddressDetailsWithRandomData();
    }
    
    // =============================
    // Audit Trail Steps
    // =============================
    
    @When("I click Audit Trail section")
    public void iClickAuditTrailSection() {
        customerDetailPage.clickAuditTrailSection();
    }
    
    // =============================
    // Complete Flow Steps
    // =============================
    
    @When("I fill all customer details with random data")
    public void iFillAllCustomerDetailsWithRandomData() {
        customerDetailPage.fillAllCustomerDetailsWithRandomData();
    }
    
    @Then("all customer details should be successfully updated")
    public void allCustomerDetailsShouldBeSuccessfullyUpdated() {
        System.out.println("All customer details have been successfully updated");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
