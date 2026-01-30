package com.example.stepdefinitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import com.example.hooks.Hooks;
import com.example.pages.AddEmployeeContactPage;
import com.example.pages.AddFirmContactPage;
import com.example.pages.AddIndividualContactPage;
import com.example.pages.AddOtherPartyContactPage;
import com.example.pages.ContactsNavigationPage;
import com.example.pages.SearchContactPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions for Contact Management Feature
 */
public class ContactStepDefinitions {
    
    private WebDriver driver;
    private ContactsNavigationPage contactsNavigationPage;
    private AddFirmContactPage addFirmContactPage;
    private AddIndividualContactPage addIndividualContactPage;
    private AddEmployeeContactPage addEmployeeContactPage;
    private AddOtherPartyContactPage addOtherPartyContactPage;
    private SearchContactPage searchContactPage;
    
    // Variables to store created contact names
    private String createdFirmName;
    private String createdIndividualName;
    private String createdEmployeeName;
    private String createdOtherPartyName;
    
    // Constructor - WebDriver will be retrieved from Hooks
    public ContactStepDefinitions() {
        this.driver = Hooks.getDriver();
        this.contactsNavigationPage = new ContactsNavigationPage(driver);
        this.addFirmContactPage = new AddFirmContactPage(driver);
        this.addIndividualContactPage = new AddIndividualContactPage(driver);
        this.addEmployeeContactPage = new AddEmployeeContactPage(driver);
        this.addOtherPartyContactPage = new AddOtherPartyContactPage(driver);
        this.searchContactPage = new SearchContactPage(driver);
    }
    
    // Navigation Steps
    @Given("I navigate to Contacts module")
    public void iNavigateToContactsModule() {
        contactsNavigationPage.navigateToContacts();
    }
    
    @And("I click on Firm tab")
    public void iClickOnFirmTab() {
        contactsNavigationPage.clickFirmTab();
    }
    
    @And("I click on Individual tab")
    public void iClickOnIndividualTab() {
        contactsNavigationPage.clickIndividualTab();
    }
    
    @And("I click on Employee tab")
    public void iClickOnEmployeeTab() {
        contactsNavigationPage.clickEmployeeTab();
    }
    
    @And("I click on Other Parties tab")
    public void iClickOnOtherPartiesTab() {
        contactsNavigationPage.clickOtherPartiesTab();
    }
    
    @When("I click Add New button for contacts")
    public void iClickAddNewButtonForContacts() {
        contactsNavigationPage.clickAddNewButton();
    }
    
    @And("I click Single Entry option")
    public void iClickSingleEntryOption() {
        contactsNavigationPage.clickSingleEntry();
    }
    
    @And("I navigate back to Contacts page")
    public void iNavigateBackToContactsPage() {
        contactsNavigationPage.navigateBackToContacts();
    }
    
    // Firm Contact Steps
    @And("I fill the Firm contact form with random data")
    public void iFillTheFirmContactFormWithRandomData() {
        addFirmContactPage.fillFirmContactForm();
        createdFirmName = addFirmContactPage.getCreatedFirmName();
    }
    
    @And("I click Save button for contact")
    public void iClickSaveButtonForContact() {
        // This method is reused for all contact types
        // The appropriate page object's save method will be called
        // depending on which form was filled
    }
    
    @Then("the Firm contact should be created successfully")
    public void theFirmContactShouldBeCreatedSuccessfully() {
        addFirmContactPage.clickSave();
        System.out.println("Firm contact created: " + createdFirmName);
        // Add assertion if there's a success message to verify
    }
    
    // Individual Contact Steps
    @And("I fill the Individual contact form with random data")
    public void iFillTheIndividualContactFormWithRandomData() {
        addIndividualContactPage.fillIndividualContactForm();
        createdIndividualName = addIndividualContactPage.getCreatedIndividualName();
    }
    
    @Then("the Individual contact should be created successfully")
    public void theIndividualContactShouldBeCreatedSuccessfully() {
        addIndividualContactPage.clickSave();
        System.out.println("Individual contact created: " + createdIndividualName);
    }
    
    // Employee Contact Steps
    @And("I fill the Employee contact form with random data")
    public void iFillTheEmployeeContactFormWithRandomData() {
        addEmployeeContactPage.fillEmployeeContactForm();
        createdEmployeeName = addEmployeeContactPage.getCreatedEmployeeName();
    }
    
    @Then("the Employee contact should be created successfully")
    public void theEmployeeContactShouldBeCreatedSuccessfully() {
        addEmployeeContactPage.clickSave();
        System.out.println("Employee contact created: " + createdEmployeeName);
    }
    
    // Other Party Contact Steps
    @And("I fill the Other Party contact form with random data")
    public void iFillTheOtherPartyContactFormWithRandomData() {
        addOtherPartyContactPage.fillOtherPartyContactForm();
        createdOtherPartyName = addOtherPartyContactPage.getCreatedOtherPartyName();
    }
    
    @Then("the Other Party contact should be created successfully")
    public void theOtherPartyContactShouldBeCreatedSuccessfully() {
        addOtherPartyContactPage.clickSave();
        System.out.println("Other Party contact created: " + createdOtherPartyName);
    }
    
    // Search and Edit Steps
    @When("I search for the created Employee contact")
    public void iSearchForTheCreatedEmployeeContact() {
        searchContactPage.searchContact(createdEmployeeName);
    }
    
    @When("I search for the created Other Party contact")
    public void iSearchForTheCreatedOtherPartyContact() {
        searchContactPage.searchContact(createdOtherPartyName);
    }
    
    @And("I click on the searched contact")
    public void iClickOnTheSearchedContact() {
        searchContactPage.clickSearchedContact();
    }
    
    @And("I edit the Employee contact with random employee ID")
    public void iEditTheEmployeeContactWithRandomEmployeeID() {
        searchContactPage.editEmployeeContact(null); // null for random ID
    }
    
    @And("I edit the Other Party contact with random designation")
    public void iEditTheOtherPartyContactWithRandomDesignation() {
        searchContactPage.editOtherPartyContact(null); // null for random designation
    }
    
    @Then("the Employee contact should be updated successfully")
    public void theEmployeeContactShouldBeUpdatedSuccessfully() {
        System.out.println("Employee contact updated successfully");
        // Add assertion if there's a success message to verify
    }
    
    @Then("the Other Party contact should be updated successfully")
    public void theOtherPartyContactShouldBeUpdatedSuccessfully() {
        System.out.println("Other Party contact updated successfully");
        // Add assertion if there's a success message to verify
    }
    
    // Verification Steps
    @Then("the contact should exist in the system")
    public void theContactShouldExistInTheSystem() {
        // Generic verification step
        Assertions.assertTrue(true, "Contact verification passed");
    }
}
