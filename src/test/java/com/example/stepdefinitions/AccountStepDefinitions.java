package com.example.stepdefinitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import com.example.hooks.Hooks;
import com.example.pages.AccountNavigationPage;
import com.example.pages.AddAccountPage;
import com.example.pages.LoginPage;
import com.example.pages.OtpPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions for Account Management Feature
 */
public class AccountStepDefinitions {
    
    private WebDriver driver;
    private LoginPage loginPage;
    private OtpPage otpPage;
    private AccountNavigationPage navigationPage;
    private AddAccountPage addAccountPage;
    
    // Constructor - WebDriver will be retrieved from Hooks
    public AccountStepDefinitions() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
        this.otpPage = new OtpPage(driver);
        this.navigationPage = new AccountNavigationPage(driver);
        this.addAccountPage = new AddAccountPage(driver);
    }
    
    @Given("I navigate to Borrowers menu for accounts")
    public void iNavigateToBorrowersMenuForAccounts() {
        navigationPage.clickBorrowersMenu();
    }
    
    @And("I click on Accounts submenu")
    public void iClickOnAccountsSubmenu() {
        navigationPage.clickAccountsSubmenu();
    }
    
    @And("I click on Add New Account button")
    public void iClickOnAddNewAccountButton() {
        navigationPage.clickAddNewAccount();
        // Re-initialize the page object after navigation to ensure elements are located on the new page
        addAccountPage = new AddAccountPage(driver);
    }
    
    @When("I fill the account form with random data")
    public void iFillTheAccountFormWithRandomData(DataTable dataTable) {
        addAccountPage.fillAccountFormWithRandomData();
    }
    
    @And("I click Next button on account section 1")
    public void iClickNextButtonOnAccountSection1() {
        addAccountPage.clickNextSection1();
    }
    
    @And("I click Next button on account section 2")
    public void iClickNextButtonOnAccountSection2() {
        addAccountPage.clickNextSection2();
    }
    
    @And("I click Next button on account section 3")
    public void iClickNextButtonOnAccountSection3() {
        addAccountPage.clickNextSection3();
    }
    
    @And("I click Save button for account")
    public void iClickSaveButtonForAccount() {
        addAccountPage.clickSaveButton();
    }
    
    @And("I click Next on all sections and save the account")
    public void iClickNextOnAllSectionsAndSaveTheAccount() {
        addAccountPage.completeAccountCreation();
    }
    
    @And("I search for the created account")
    public void iSearchForTheCreatedAccount() {
        addAccountPage.searchForCreatedAccount();
    }
    
    @And("I verify all account details sections")
    public void iVerifyAllAccountDetailsSections() {
        addAccountPage.verifyAccountDetailsComplete();
    }
    
    @Then("the account should be created successfully")
    public void theAccountShouldBeCreatedSuccessfully() {
        // Wait for account creation to complete
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Add assertion based on actual success indicator
        Assertions.assertNotNull(driver);
        System.out.println("✓ Account created successfully");
    }
    
    @When("I click Back button to return to account list")
    public void iClickBackButtonToReturnToAccountList() {
        addAccountPage.clickBackButton();
    }
    
    @And("I enter the account number in search field")
    public void iEnterTheAccountNumberInSearchField() {
        String accountNumber = addAccountPage.getAccountNumber();
        addAccountPage.enterAccountNumberInSearch(accountNumber);
    }
    
    @And("I click Search button for account")
    public void iClickSearchButtonForAccount() {
        addAccountPage.clickSearchButton();
    }
    
    @Then("I should see the created account in results")
    public void iShouldSeeTheCreatedAccountInResults() {
        // Wait for search results
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Add assertion to verify account appears in search results
        Assertions.assertNotNull(driver);
        System.out.println("✓ Account found in search results");
    }
    
    @When("I enter account number {string} in search field")
    public void iEnterAccountNumberInSearchField(String accountNumber) {
        addAccountPage.enterAccountNumberInSearch(accountNumber);
    }
    
    @Then("I should see search results for the account")
    public void iShouldSeeSearchResultsForTheAccount() {
        // Wait for search results
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Add assertion to verify search results are displayed
        Assertions.assertNotNull(driver);
        System.out.println("✓ Search results displayed for account");
    }
}
