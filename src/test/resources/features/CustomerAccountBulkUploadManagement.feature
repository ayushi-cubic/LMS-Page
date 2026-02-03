@CustomerAccountBulkUpload
Feature: Customer Account Bulk Upload Management
  As a user of the LMS system
  I want to perform bulk upload of Customer Accounts
  So that I can efficiently import multiple customer account records

  @CustomerAccountBulkUploadScenario
  Scenario: Download Excel template, fill with Customer Account data, and upload
    Given I am logged into the LMS application for customer account bulk upload
    When I navigate to the Upload page for customer accounts
    And I select Upload Type as Customers for accounts
    And I click on the Download Excel Format button for customer accounts
    And I wait for the customer account Excel file to be downloaded
    And I fill the Excel file with Customer Account test data
    And I upload the customer account Excel file
    And I click on the Upload button for customer accounts
    Then the customer account upload should be successful
