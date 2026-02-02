@BulkUpload
Feature: Bulk Upload Management
  As a user of the LMS system
  I want to perform bulk upload of Civil Cases
  So that I can efficiently import multiple case records

  @BulkUploadCivilCase
  Scenario: Download Excel template, fill with Civil Case data, and upload
    Given I am logged into the LMS application for bulk upload
    When I navigate to the Upload page
    And I click on the Download Excel Format button
    And I wait for the Excel file to be downloaded
    And I fill the Excel file with Civil Case test data
    And I upload the Excel file
    And I click on the Upload button
    Then the upload should be successful
