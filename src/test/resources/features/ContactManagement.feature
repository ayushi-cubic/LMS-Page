@ContactManagement
Feature: Contact Management
  As a user of the LMS system
  I want to manage different types of contacts
  So that I can maintain contact information effectively

  Background:
    Given I open the LMS application
    When I login with username "snehal" and password "pass@123"
    And I enter OTP "123456"
    Then I should be logged in successfully

  @CreateFirmContact
  Scenario: Create a new Firm contact
    Given I navigate to Contacts module
    And I click on Firm tab
    When I click Add New button for contacts
    And I click Single Entry option
    And I fill the Firm contact form with random data
    And I click Save button for contact
    Then the Firm contact should be created successfully
    And I navigate back to Contacts page

  @CreateIndividualContact
  Scenario: Create a new Individual contact (Advocate)
    Given I navigate to Contacts module
    And I click on Individual tab
    When I click Add New button for contacts
    And I click Single Entry option
    And I fill the Individual contact form with random data
    And I click Save button for contact
    Then the Individual contact should be created successfully
    And I navigate back to Contacts page

  @CreateEmployeeContact
  Scenario: Create a new Employee contact
    Given I navigate to Contacts module
    And I click on Employee tab
    When I click Add New button for contacts
    And I click Single Entry option
    And I fill the Employee contact form with random data
    And I click Save button for contact
    Then the Employee contact should be created successfully
    And I navigate back to Contacts page

  @CreateOtherPartyContact
  Scenario: Create a new Other Party contact (Guarantor)
    Given I navigate to Contacts module
    And I click on Other Parties tab
    When I click Add New button for contacts
    And I click Single Entry option
    And I fill the Other Party contact form with random data
    And I click Save button for contact
    Then the Other Party contact should be created successfully
    And I navigate back to Contacts page

  @SearchAndEditEmployee
  Scenario: Create and edit an Employee contact
    Given I navigate to Contacts module
    And I click on Employee tab
    When I click Add New button for contacts
    And I click Single Entry option
    And I fill the Employee contact form with random data
    And I click Save button for contact
    Then the Employee contact should be created successfully
    And I navigate back to Contacts page
    When I click on Employee tab
    And I search for the created Employee contact
    And I click on the searched contact
    And I edit the Employee contact with random employee ID
    Then the Employee contact should be updated successfully

  @SearchAndEditOtherParty
  Scenario: Create and edit an Other Party contact
    Given I navigate to Contacts module
    And I click on Other Parties tab
    When I click Add New button for contacts
    And I click Single Entry option
    And I fill the Other Party contact form with random data
    And I click Save button for contact
    Then the Other Party contact should be created successfully
    And I navigate back to Contacts page
    When I click on Other Parties tab
    And I search for the created Other Party contact
    And I click on the searched contact
    And I edit the Other Party contact with random designation
    Then the Other Party contact should be updated successfully

  @CreateAllContactTypes
  Scenario: Create all types of contacts in sequence
    Given I navigate to Contacts module
    # Create Firm
    When I click on Firm tab
    And I click Add New button for contacts
    And I click Single Entry option
    And I fill the Firm contact form with random data
    And I click Save button for contact
    Then the Firm contact should be created successfully
    And I navigate back to Contacts page
    # Create Individual
    When I click on Individual tab
    And I click Add New button for contacts
    And I click Single Entry option
    And I fill the Individual contact form with random data
    And I click Save button for contact
    Then the Individual contact should be created successfully
    And I navigate back to Contacts page
    # Create Other Party
    When I click on Other Parties tab
    And I click Add New button for contacts
    And I click Single Entry option
    And I fill the Other Party contact form with random data
    And I click Save button for contact
    Then the Other Party contact should be created successfully
    And I navigate back to Contacts page
    # Create Employee and Edit
    When I click on Employee tab
    And I click Add New button for contacts
    And I click Single Entry option
    And I fill the Employee contact form with random data
    And I click Save button for contact
    Then the Employee contact should be created successfully
    And I navigate back to Contacts page
    When I click on Employee tab
    And I search for the created Employee contact
    And I click on the searched contact
    And I edit the Employee contact with random employee ID
    Then the Employee contact should be updated successfully
