@AccountDetailManagement
Feature: Account Detail Management
  As a user of the LMS system
  I want to edit account details
  So that I can update account information with various fields

  Background: User is logged in
    Given I open the LMS application
    When I login with username "ayushi" and password "Legal@1234"
    And I enter OTP "123456"
    Then I should be logged in successfully

  @UpdateAccountDetails
  Scenario: Update account details with all fields
    Given I navigate to Borrowers menu for account details
    And I click on Account submenu for details
    And I click on Load Customer button
    And I click on Action button for first account
    And I click on Details button to open account details
    When I complete account detail update with all fields
    Then the account details should be updated successfully

  @UpdateAccountDetailsStepByStep
  Scenario: Update account details step by step
    Given I navigate to Borrowers menu for account details
    And I click on Account submenu for details
    And I click on Load Customer button
    And I click on Action button for first account
    And I click on Details button to open account details
    When I click Edit button on account details
    And I select random Account Type
    And I select random Parent Account
    And I select random Loan Agreement Date
    And I select random Maturity Date
    And I select random Zone
    And I select random State
    And I select random Location
    And I select random Account Status
    And I select random Business Unit
    And I select random Upload Date
    And I select random NPA Type
    And I enter random Block Type 1
    And I select random Date of Block Type 1
    And I enter random Block Type 2
    And I select random Date of Block Type 2
    And I select random Non Cooperative Borrower
    And I select random NPA Removal Date
    And I select random Business Date
    And I select random Scheme Name
    And I select random Date of NPA
    And I click Fraud Yes radio button
    And I click Save button on account details
    Then the account details should be updated successfully

  @QuickAccountDetailsUpdate
  Scenario: Quick account details update
    Given I navigate to account details page
    When I fill all account detail fields with random data
    And I save the account details
    Then the account details should be updated successfully

  @CompleteAccountDetailWorkflow
  Scenario: Complete account detail workflow with all sections
    Given I navigate to Borrowers menu for account details
    And I click on Account submenu for details
    And I click on Load Customer button
    And I click on Action button for first account
    And I click on Details button to open account details
    When I complete account detail update with all fields
    And I complete all account detail sections
    Then all account detail sections should be completed successfully

  @IndividualSectionsUpdate
  Scenario: Update individual account detail sections
    Given I navigate to Borrowers menu for account details
    And I click on Account submenu for details
    And I click on Load Customer button
    And I click on Action button for first account
    And I click on Details button to open account details
    When I complete account detail update with all fields
    And I complete Bad Debt section
    And I complete Account Details Extended section
    And I complete Foreclosure Details section
    And I complete Concilation Details section
    And I complete Present Status section
    And I complete Recovery Summary Applicant section
    And I add new applicant contact
    And I add address details
    And I add information request
    And I complete Bank Consortium Details section
    And I add instrument details
    Then all account detail sections should be completed successfully
