@CivilCaseCreation
Feature: Civil Case Creation Management
  As a user of the LMS system
  I want to create a civil case
  So that I can manage civil case records efficiently

  @CivilCaseCreationScenario
  Scenario: Create a new civil case and verify it
    Given I am logged into the LMS application for civil case creation
    When I click on Matters menu
    And I click on Case submenu
    And I click on Add New Case button
    And I click on Civil button
    And I select random Case Type
    And I select random Customer ID
    And I select random Account Number
    And I enter Party name
    And I select random Issuing Party
    And I select random Respondent
    And I select random Petitioner's Advocate
    And I enter random Case Number
    And I select random Priority
    And I select random Parties
    And I click Next for Basic Details
    And I enter random Claim Amount
    And I click Next for Stake Details
    And I select Ayushi G from Sr Supervisor
    And I click Next for Owner Details
    And I click Next for Case Analysis
    And I enter Registration Date
    And I click Next for Important Dates
    And I click Next for Contingent Liability
    And I click Save button for civil case
    And I capture the System ID
    And I click Back button
    And I search for the captured System ID
    And I click Action button
    Then I click Details link
