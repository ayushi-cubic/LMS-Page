@AccountManagement
Feature: Account Management
  As a user of the LMS system
  I want to create and search for accounts
  So that I can manage account information effectively

  @CreateAccount
  Scenario: Create a new account and search for it
    Given I open the LMS application
    When I login with username "ayushi" and password "Legal@1234"
    And I enter OTP "123456"
    Then I should be logged in successfully
    Given I navigate to Borrowers menu for accounts
    And I click on Accounts submenu
    And I click on Add New Account button
    When I fill the account form with random data
      | Account Number        | random_alphanumeric |
      | Account Type          | random_dropdown     |
      | Customer Number       | random_dropdown     |
      | Principal Amount      | random_numeric      |
      | Rate of Interest      | random_decimal      |
      | Outstanding Balance   | random_numeric      |
      | Loan Disbursal Date   | random_date         |
      | Account Status        | random_dropdown     |
      | Zone                  | random_dropdown     |
      | State                 | random_dropdown     |
      | Location              | random_dropdown     |
      | Business Unit         | random_dropdown     |
    And I click Next on all sections and save the account
    And I search for the created account
    And I verify all account details sections

  @VerifyAccountDetails
  Scenario: Verify account details and sections
    Given I open the LMS application
    When I login with username "ayushi" and password "Legal@1234"
    And I enter OTP "123456"
    Then I should be logged in successfully
    Given I navigate to Borrowers menu for accounts
    And I click on Accounts submenu
    And I click on Add New Account button
    When I fill the account form with random data
      | Account Number        | random_alphanumeric |
      | Account Type          | random_dropdown     |
      | Customer Number       | random_dropdown     |
      | Principal Amount      | random_numeric      |
      | Rate of Interest      | random_decimal      |
      | Outstanding Balance   | random_numeric      |
      | Loan Disbursal Date   | random_date         |
      | Account Status        | random_dropdown     |
      | Zone                  | random_dropdown     |
      | State                 | random_dropdown     |
      | Location              | random_dropdown     |
      | Business Unit         | random_dropdown     |
    And I click Next on all sections and save the account
    And I search for the created account
    And I verify all account details sections

  @SearchAccount
  Scenario: Search for an existing account
    Given I open the LMS application
    When I login with username "ayushi" and password "Legal@1234"
    And I enter OTP "123456"
    Then I should be logged in successfully
    Given I navigate to Borrowers menu for accounts
    And I click on Accounts submenu
    When I enter account number "ACC123456" in search field
    And I click Search button for account
    Then I should see search results for the account
