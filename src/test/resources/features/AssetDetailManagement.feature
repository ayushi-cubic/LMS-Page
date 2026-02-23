@AssetDetailManagement
Feature: Asset Detail Management
  As a user of the LMS system
  I want to update asset detail information
  So that I can maintain asset records from the detail page

  Background:
    Given I open the LMS application for asset details
    When I login to LMS with username "ayushi" and password "Legal@1234" for asset details
    And I enter the OTP "123456" for asset details
    Then I should be successfully logged in to asset details

  @UpdateAssetDetails
  Scenario: Update first asset details with random data
    Given I navigate to asset details page
    When I complete asset detail update with random data
    Then the asset details should be updated successfully

  @NavigateAssetDetails
  Scenario: Navigate to asset details page from borrowers menu
    When I click on Borrowers menu for asset details
    And I click on Asset submenu for asset details
    And I click Load asset button
    And I click on Action button for first asset
    And I click on Details option for first asset
    Then the asset details should be updated successfully
