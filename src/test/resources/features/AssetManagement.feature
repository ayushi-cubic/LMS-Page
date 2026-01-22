@AssetManagement
Feature: Asset Management
  As a user of the LMS system
  I want to create and manage assets
  So that I can track asset information effectively

  @CreateAsset
  Scenario: Create a new asset and verify details
    Given I open the LMS application
    When I login with username "ayushi" and password "Legal@1234"
    And I enter OTP "123456"
    Then I should be logged in successfully
    Given I navigate to Borrowers menu for assets
    And I click on Assets submenu
    And I click on Add New Asset button
    When I fill the asset form with random data
    And I click Next button on asset form
    And I click Save button for asset
    Then the asset should be created successfully
    Then I verify all asset details sections and delete the asset
