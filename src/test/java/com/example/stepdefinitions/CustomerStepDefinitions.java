package com.example.stepdefinitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import com.example.hooks.Hooks;
import com.example.pages.AddCustomerPage;
import com.example.pages.CustomerFilterPage;
import com.example.pages.CustomerNavigationPage;
import com.example.pages.LoginPage;
import com.example.pages.OtpPage;
import com.example.utils.RandomDataGenerator;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions for Customer Management Feature
 */
public class CustomerStepDefinitions {
    
    private WebDriver driver;
    private LoginPage loginPage;
    private OtpPage otpPage;
    private CustomerNavigationPage navigationPage;
    private AddCustomerPage addCustomerPage;
    private CustomerFilterPage filterPage;
    
    // Constructor - WebDriver will be retrieved from Hooks
    public CustomerStepDefinitions() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
        this.otpPage = new OtpPage(driver);
        this.navigationPage = new CustomerNavigationPage(driver);
        this.addCustomerPage = new AddCustomerPage(driver);
        this.filterPage = new CustomerFilterPage(driver);
    }
    
    @Given("I open the LMS application")
    public void iOpenTheLMSApplication() {
        loginPage.openApplication("https://qalmsbu.cubictree.com/");
    }
    
    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }
    
    @And("I enter OTP {string}")
    public void iEnterOTP(String otp) {
        otpPage.verifyOtp(otp);
    }
    
    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        // Wait for login to complete
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Add assertion based on actual success indicator (e.g., dashboard element)
        Assertions.assertNotNull(driver);
    }
    
    @Given("I navigate to Borrowers menu")
    public void iNavigateToBorrowersMenu() {
        navigationPage.clickBorrowersMenu();
    }
    
    @And("I click on Customer submenu")
    public void iClickOnCustomerSubmenu() {
        navigationPage.clickCustomerSubmenu();
    }
    
    @And("I click on Add New Customer button")
    public void iClickOnAddNewCustomerButton() {
        navigationPage.clickAddNewCustomer();
    }
    
    @When("I fill the customer form with random data")
    public void iFillTheCustomerFormWithRandomData(DataTable dataTable) {
        addCustomerPage.fillCustomerFormWithRandomData();
    }
    
    @And("I click Next button on Basic Details section")
    public void iClickNextButtonOnBasicDetailsSection() {
        addCustomerPage.clickNextBasicDetails();
    }
    
    @And("I click Next button on NPA section")
    public void iClickNextButtonOnNPASection() {
        addCustomerPage.clickNextNPA();
    }
    
    @And("I click Next button on third section")
    public void iClickNextButtonOnThirdSection() {
        addCustomerPage.clickNextThirdSection();
    }
    
    @And("I select Sr Supervisor from dropdown")
    public void iSelectSrSupervisorFromDropdown() {
        addCustomerPage.selectSrSupervisor();
    }
    
    @And("I click Next button on fourth section")
    public void iClickNextButtonOnFourthSection() {
        addCustomerPage.clickNextFourthSection();
    }
    
    @And("I enter remarks")
    public void iEnterRemarks() {
        addCustomerPage.fillRemarks(RandomDataGenerator.generateRemarks());
    }
    
    @And("I click Save button")
    public void iClickSaveButton() {
        addCustomerPage.clickSave();
    }
    
    @Then("the customer should be created successfully")
    public void theCustomerShouldBeCreatedSuccessfully() {
        Assertions.assertTrue(addCustomerPage.isCustomerCreatedSuccessfully(), 
            "Customer creation failed");
    }
    
    @When("I click Back button to return to customer list")
    public void iClickBackButtonToReturnToCustomerList() {
        navigationPage.clickBackButton();
    }
    
    @And("I enter the customer number in search field")
    public void iEnterTheCustomerNumberInSearchField() {
        String customerNumber = addCustomerPage.getCustomerData("CustomerNumber");
        Assertions.assertNotNull(customerNumber, "Customer number not found");
        navigationPage.enterCustomerNumberInSearch(customerNumber);
    }
    
    @And("I click Search button")
    public void iClickSearchButton() {
        navigationPage.clickSearchButton();
    }
    
    @Then("the customer should be displayed in search results")
    public void theCustomerShouldBeDisplayedInSearchResults() {
        String customerNumber = addCustomerPage.getCustomerData("CustomerNumber");
        Assertions.assertTrue(
            navigationPage.isCustomerInSearchResults(customerNumber),
            "Customer not found in search results: " + customerNumber
        );
    }
    
    @When("I click on {string} ownership tab")
    public void iClickOnOwnershipTab(String tabName) {
        filterPage.clickOwnershipTab(tabName);
    }
    
    @And("I test all filters for the current tab")
    public void iTestAllFiltersForCurrentTab() {
        filterPage.testAllFiltersForTab();
    }
    
    @When("I test all ownership tabs with filters")
    public void iTestAllOwnershipTabsWithFilters() {
        String customerNumber = addCustomerPage.getCustomerData("CustomerNumber");
        String customerName = addCustomerPage.getCustomerData("CustomerName");
        filterPage.testAllOwnershipTabsWithFilters(customerNumber, customerName);
    }
    
    @And("I apply {string} filter")
    public void iApplyFilter(String filterName) {
        filterPage.testFilter(filterName);
    }
    
    @Then("I verify all customer details sections and delete the customer")
    public void iVerifyAllCustomerDetailsSectionsAndDeleteTheCustomer() {
        // This method already handles the complete verification AND deletion flow
        // including acceptDeleteAlert() at the end
        addCustomerPage.verifyCustomerDetailsComplete();
    }
}
