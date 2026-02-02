@CustomerBulkUpload
Feature: Customer Bulk Upload Management
  As a user of the LMS system
  I want to perform bulk upload of Customers
  So that I can efficiently import multiple customer records

  @CustomerBulkUploadIndividual
  Scenario: Download Excel template, fill with Customer data, and upload
    Given I am logged into the LMS application for customer bulk upload
    When I navigate to the Bulk Upload page
    And I select Upload Type as Customers
    And I select Customer Type as Customer
    And I click on the Download Excel Format button for customers
    And I wait for the customer Excel file to be downloaded
    And I fill the Excel file with Customer test data
    And I upload the customer Excel file
    And I click on the Upload button for customers
    Then the customer upload should be successful
