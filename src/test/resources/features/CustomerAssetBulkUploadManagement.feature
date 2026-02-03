@CustomerAssetBulkUpload
Feature: Customer Asset Bulk Upload Management
  As a user of the LMS system
  I want to perform bulk upload of Customer Assets
  So that I can efficiently import multiple customer asset records

  @CustomerAssetBulkUploadScenario
  Scenario: Download Excel template, fill with Customer Asset data, and upload
    Given I am logged into the LMS application for customer asset bulk upload
    When I navigate to the Upload page for customer assets
    And I select Upload Type as Customers for assets
    And I select Customer Type as Customer Securities
    And I click on the Download Excel Format button for customer assets
    And I wait for the customer asset Excel file to be downloaded
    And I fill the Excel file with Customer Asset test data
    And I upload the customer asset Excel file
    And I click on the Upload button for customer assets
    Then the customer asset upload should be successful
