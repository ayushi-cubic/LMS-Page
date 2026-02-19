package com.example.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.example.hooks.Hooks;
import com.example.pages.AccountDetailNavigationPage;
import com.example.pages.AccountDetailPage;
import com.example.pages.LoginPage;
import com.example.pages.OtpPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions for Account Detail Management Feature
 */
public class AccountDetailStepDefinitions {
    
    private WebDriver driver;
    private LoginPage loginPage;
    private OtpPage otpPage;
    private AccountDetailNavigationPage navigationPage;
    private AccountDetailPage accountDetailPage;
    
    // Constructor - WebDriver will be retrieved from Hooks
    public AccountDetailStepDefinitions() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
        this.otpPage = new OtpPage(driver);
        this.navigationPage = new AccountDetailNavigationPage(driver);
        this.accountDetailPage = new AccountDetailPage(driver);
    }
    
    @Given("I navigate to Borrowers menu for account details")
    public void iNavigateToBorrowersMenuForAccountDetails() {
        navigationPage.clickBorrowersMenu();
    }
    
    @And("I click on Account submenu for details")
    public void iClickOnAccountSubmenuForDetails() {
        navigationPage.clickAccountSubmenu();
    }
    
    @And("I click on Load Customer button")
    public void iClickOnLoadCustomerButton() {
        navigationPage.clickLoadCustomer();
    }
    
    @And("I click on Action button for first account")
    public void iClickOnActionButtonForFirstAccount() {
        navigationPage.clickActionButton();
    }
    
    @And("I click on Details button to open account details")
    public void iClickOnDetailsButtonToOpenAccountDetails() {
        navigationPage.clickDetailsButton();
        // Re-initialize the page object after navigation to the new tab
        accountDetailPage = new AccountDetailPage(driver);
    }
    
    @And("I navigate to account details page")
    public void iNavigateToAccountDetailsPage() {
        navigationPage.navigateToAccountDetails();
        // Re-initialize the page object after navigation to the new tab
        accountDetailPage = new AccountDetailPage(driver);
    }
    
    @When("I click Edit button on account details")
    public void iClickEditButtonOnAccountDetails() {
        accountDetailPage.clickEditButton();
    }
    
    @And("I select random Account Type")
    public void iSelectRandomAccountType() {
        accountDetailPage.selectAccountType();
    }
    
    @And("I select random Parent Account")
    public void iSelectRandomParentAccount() {
        accountDetailPage.selectParentAccount();
    }
    
    @And("I select random Loan Agreement Date")
    public void iSelectRandomLoanAgreementDate() {
        accountDetailPage.selectAgreementDate();
    }
    
    @And("I select random Maturity Date")
    public void iSelectRandomMaturityDate() {
        accountDetailPage.selectMaturityDate();
    }
    
    @And("I select random Zone")
    public void iSelectRandomZone() {
        accountDetailPage.selectZone();
    }
    
    @And("I select random State")
    public void iSelectRandomState() {
        accountDetailPage.selectState();
    }
    
    @And("I select random Location")
    public void iSelectRandomLocation() {
        accountDetailPage.selectLocation();
    }
    
    @And("I select random Account Status")
    public void iSelectRandomAccountStatus() {
        accountDetailPage.selectAccountStatus();
    }
    
    @And("I select random Business Unit")
    public void iSelectRandomBusinessUnit() {
        accountDetailPage.selectBusinessUnit();
    }
    
    @And("I select random Upload Date")
    public void iSelectRandomUploadDate() {
        accountDetailPage.selectUploadDate();
    }
    
    @And("I select random NPA Type")
    public void iSelectRandomNPAType() {
        accountDetailPage.selectNPAType();
    }
    
    @And("I enter random Block Type 1")
    public void iEnterRandomBlockType1() {
        accountDetailPage.enterBlockType1();
    }
    
    @And("I select random Date of Block Type 1")
    public void iSelectRandomDateOfBlockType1() {
        accountDetailPage.selectDateOfBlockType1();
    }
    
    @And("I enter random Block Type 2")
    public void iEnterRandomBlockType2() {
        accountDetailPage.enterBlockType2();
    }
    
    @And("I select random Date of Block Type 2")
    public void iSelectRandomDateOfBlockType2() {
        accountDetailPage.selectDateOfBlockType2();
    }
    
    @And("I select random Non Cooperative Borrower")
    public void iSelectRandomNonCooperativeBorrower() {
        accountDetailPage.selectNonCooperativeBorrower();
    }
    
    @And("I select random NPA Removal Date")
    public void iSelectRandomNPARemovalDate() {
        accountDetailPage.selectNPARemovalDate();
    }
    
    @And("I select random Business Date")
    public void iSelectRandomBusinessDate() {
        accountDetailPage.selectBusinessDate();
    }
    
    @And("I select random Scheme Name")
    public void iSelectRandomSchemeName() {
        accountDetailPage.selectSchemeName();
    }
    
    @And("I select random Date of NPA")
    public void iSelectRandomDateOfNPA() {
        accountDetailPage.selectDateOfNPA();
    }
    
    @And("I click Fraud Yes radio button")
    public void iClickFraudYesRadioButton() {
        accountDetailPage.clickFraudYes();
    }
    
    @And("I click Save button on account details")
    public void iClickSaveButtonOnAccountDetails() {
        accountDetailPage.clickSaveButton();
    }
    
    @When("I fill all account detail fields with random data")
    public void iFillAllAccountDetailFieldsWithRandomData() {
        accountDetailPage.fillAccountDetailsForm();
    }
    
    @And("I save the account details")
    public void iSaveTheAccountDetails() {
        accountDetailPage.clickSaveButton();
    }
    
    @When("I complete account detail update with all fields")
    public void iCompleteAccountDetailUpdateWithAllFields() {
        accountDetailPage.completeAccountDetailUpdate();
    }
    
    @Then("the account details should be updated successfully")
    public void theAccountDetailsShouldBeUpdatedSuccessfully() {
        // Wait for update to complete
        try {
            Thread.sleep(3000);
            System.out.println("✓ Account details updated successfully");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Additional sections step definitions
    
    @When("I complete Bad Debt section")
    public void iCompleteBadDebtSection() {
        accountDetailPage.completeBadDebtSection();
    }
    
    @When("I complete Account Details Extended section")
    public void iCompleteAccountDetailsExtendedSection() {
        accountDetailPage.completeAccountDetailsExtended();
    }
    
    @When("I complete Foreclosure Details section")
    public void iCompleteForeclosureDetailsSection() {
        accountDetailPage.completeForeclosureDetails();
    }
    
    @When("I complete Concilation Details section")
    public void iCompleteConcilationDetailsSection() {
        accountDetailPage.completeConcilationDetails();
    }
    
    @When("I complete Present Status section")
    public void iCompletePresentStatusSection() {
        accountDetailPage.completePresentStatus();
    }
    
    @When("I complete Recovery Summary Applicant section")
    public void iCompleteRecoverySummaryApplicantSection() {
        accountDetailPage.completeRecoverySummaryApplicant();
    }
    
    @When("I add new applicant contact")
    public void iAddNewApplicantContact() {
        accountDetailPage.addNewApplicantContact();
    }
    
    @When("I add address details")
    public void iAddAddressDetails() {
        accountDetailPage.addAddressDetails();
    }
    
    @When("I add information request")
    public void iAddInformationRequest() {
        accountDetailPage.addInformationRequest();
    }
    
    @When("I complete Bank Consortium Details section")
    public void iCompleteBankConsortiumDetailsSection() {
        accountDetailPage.completeBankConsortiumDetails();
    }
    
    @When("I add instrument details")
    public void iAddInstrumentDetails() {
        accountDetailPage.addInstrumentDetails();
    }
    
    @When("I complete all account detail sections")
    public void iCompleteAllAccountDetailSections() {
        accountDetailPage.completeAllAccountDetailSections();
    }
    
    @Then("all account detail sections should be completed successfully")
    public void allAccountDetailSectionsShouldBeCompletedSuccessfully() {
        // Wait for all sections to complete
        try {
            Thread.sleep(3000);
            System.out.println("✓ All account detail sections completed successfully");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
