@CriminalCaseCreation
Feature: Criminal Case Creation Management
  As a user of the LMS system
  I want to create a criminal case
  So that I can manage criminal case records efficiently

  @CriminalCaseCreationScenario
  Scenario: Create a new criminal case and verify it with approval workflow
    Given I am logged into the LMS application for criminal case creation
    When I click on Matters menu for criminal case
    And I click on Case submenu for criminal case
    And I click on Add New Case button for criminal case
    And I click on Criminal button
    And I select random Case Type for criminal case
    And I select random Customer ID for criminal case
    And I select random Account Number for criminal case
    And I enter Party name for criminal case
    And I select random Issuing Party for criminal case
    And I select random Respondent for criminal case
    And I select random Petitioner's Advocate for criminal case
    And I enter random Case Number for criminal case
    And I select random Priority for criminal case
    And I select random Parties for criminal case
    And I click Next for Basic Details for criminal case
    And I enter random Claim Amount for criminal case
    And I click Next for Stake Details for criminal case
    And I select Ayushi G from Sr Supervisor for criminal case
    And I click Next for Owner Details for criminal case
    And I click Next for Case Analysis for criminal case
    And I enter Registration Date for criminal case
    And I click Next for Important Dates for criminal case
    And I click Next for Cheque Details
    And I click Next for Add FIR
    And I click Next for Summon Details
    And I click Next for Contingent Liability for criminal case
    And I click Save button for criminal case
    And I capture the Criminal Case ID
    And I click user dropdown for criminal case
    And I click Logout for criminal case
    And I login as "Ajinkya" with password "pass@123" and OTP "123456" for criminal case
    And I click Actionable Items for criminal case
    And I click Approval tab for criminal case
    And I click Advocate Allocation Approval for criminal case
    And I click Advance Filter for criminal case
    And I click Apply button for criminal case
    And I click Last page for criminal case
    And I click criminal case checkbox
    And I click Approve button for criminal case
    And I accept the alert for criminal case
    And I click user dropdown for criminal case
    And I click Logout for criminal case
    And I login as "ayushi" with password "Legal@1234" and OTP "123456" for criminal case
    When I click on Matters menu for criminal case
    And I click on Case submenu for criminal case
    When I click Load Cases button for criminal case
    And I click Action dropdown for criminal case
    Then I click Details link from action for criminal case
