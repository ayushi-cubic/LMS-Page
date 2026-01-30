@ReportsManagement
Feature: Reports Management
  As a user of the LMS system
  I want to generate various reports
  So that I can analyze case, notice, and customer data

  Background:
    Given I open the LMS application
    When I login with username "snehal" and password "pass@123"
    And I enter OTP "123456"
    Then I should be logged in successfully

  @GenerateCaseReports
  Scenario: Generate Case Reports with Recovery, Expenses, and Assets
    Given I navigate to Reports module
    When I select template report name "Case Report"
    And I select Include for cases
    And I select Recovery section
    And I select All Recovery
    And I click Generate Case Report
    And I select Case Expenses section
    And I select All Case Expenses
    And I click Generate Case Report
    And I select Asset section
    And I select Asset Include
    And I click Generate Case Report
    And I click Reset button
    And I select template report name "Team Ownership Report"
    And I click Team case ownership filter
    And I select Include for cases
    And I select Recovery section
    And I select All Recovery
    And I click Generate Case Report
    And I select Case Expenses section
    And I select All Case Expenses
    And I click Generate Case Report
    And I select Asset section
    And I select Asset Include
    And I click Generate Case Report
    And I click Reset button
    And I click Notice checkbox
    And I select template report name "Notice Report"
    And I click Include all for notices
    And I select all notice recovery
    And I click Generate Notice Report
    And I select Notice Proceedings
    And I select all Notice Proceedings
    And I click Generate Notice Report
    And I click Multiple Notice
    And I select all Multiple Notice
    And I click Generate Notice Report
    And I click Notice Expenses
    And I select all Notice Expenses
    And I click Generate Notice Report
    And I click Notice Asset
    And I click Notice Asset Include
    And I click Generate Notice Report
    And I click Reset button
    Then case reports should be generated successfully

  @GenerateTeamOwnershipReport
  Scenario: Generate Team Case Ownership Report
    Given I navigate to Reports module
    When I click Reset button
    And I select template report name "Team Ownership Report"
    And I click Team case ownership filter
    And I click Generate Case Report
    Then team ownership report should be generated successfully

  @GenerateNoticeReports
  Scenario: Generate Notice Reports with Recovery and Proceedings
    Given I navigate to Reports module
    When I select template report name from dropdown randomly
    And I select Include for cases
    And I select Recovery section
    And I select All Recovery
    And I click Generate Case Report
    And I select Case Expenses section
    And I select All Case Expenses
    And I click Generate Case Report
    And I select Asset section
    And I select Asset Include
    And I click Generate Case Report
    And I click Reset button
    And I click Notice checkbox
    And I select template report name "Notice Report"
    And I click Include all for notices
    And I select all notice recovery
    And I click Generate Notice Report
    And I select Notice Proceedings
    And I select all Notice Proceedings
    And I click Generate Notice Report
    And I click Reset button
    And I click Customer radio button
    And I select template report name "Customer Report"
    And I click Customer My tab
    And I click Customer Account Include
    And I click Generate Case Report
    And I click Customer Asset
    And I click Customer Asset Include
    And I click Generate Case Report
    And I click Customer OTS Offer
    And I select all Customer OTS Offer
    And I click Generate Case Report
    And I click Customer Expenses
    And I select all Customer Expenses
    And I click Generate Case Report
    And I click SARFAESI Report tab
    And I force click Generate Report
    And I click Standard Report tab
    And I select random Standard Report Type
    And I force click Generate Report
    And I click Reset button
    Then notice reports should be generated successfully

  @GenerateNoticeTeamOwnershipReport
  Scenario: Generate Notice Team Ownership Report
    Given I navigate to Reports module
    When I click Notice checkbox
    And I select template report name "Notice Team Report"
    And I click Notice Team ownership filter
    And I click Generate Notice Report
    Then notice team ownership report should be generated successfully

  @SwitchToCustomerReport
  Scenario: Switch to Customer Report Section
    Given I navigate to Reports module
    When I click Reset button
    And I click Customer radio button
    Then customer report section should be displayed

  @GenerateAllReports
  Scenario: Generate All Types of Reports End to End
    Given I navigate to Reports module
    When I select template report name "Case Report"
    And I select Include for cases
    And I select Recovery section
    And I select All Recovery
    And I click Generate Case Report
    And I select Case Expenses section
    And I select All Case Expenses
    And I click Generate Case Report
    And I select Asset section
    And I select Asset Include
    And I click Generate Case Report
    And I click Reset button
    And I select template report name "Team Ownership Report"
    And I click Team case ownership filter
    And I click Generate Case Report
    And I click Notice checkbox
    And I select template report name "Notice Report"
    And I click Include all for notices
    And I select all notice recovery
    And I click Generate Notice Report
    And I select Notice Proceedings
    And I select all Notice Proceedings
    And I click Generate Notice Report
    And I click Notice Team ownership filter
    And I click Generate Notice Report
    And I click Reset button
    And I click Customer radio button
    Then all reports workflow should be completed successfully

  @GenerateCompleteNoticeReports
  Scenario: Generate Complete Notice Reports with My Tab Flow
    Given I navigate to Reports module
    When I select template report name from dropdown randomly
    And I select Include for cases
    And I select Recovery section
    And I select All Recovery
    And I click Generate Case Report
    And I select Case Expenses section
    And I select All Case Expenses
    And I click Generate Case Report
    And I select Asset section
    And I select Asset Include
    And I click Generate Case Report
    And I click Reset button
    And I select template report name from dropdown randomly
    And I click Team case ownership filter
    And I select Include for cases
    And I select Recovery section
    And I select All Recovery
    And I click Generate Case Report
    And I select Case Expenses section
    And I select All Case Expenses
    And I click Generate Case Report
    And I select Asset section
    And I select Asset Include
    And I click Generate Case Report
    And I click Reset button
    And I click Notice checkbox
    And I select template report name from dropdown randomly
    And I click Include all for notices
    And I select all notice recovery
    And I click Generate Notice Report
    And I select Notice Proceedings
    And I select all Notice Proceedings
    And I click Generate Notice Report
    And I click Multiple Notice
    And I select all Multiple Notice
    And I click Generate Notice Report
    And I click Notice Expenses
    And I select all Notice Expenses
    And I click Generate Notice Report
    And I click Notice Asset
    And I click Notice Asset Include
    And I click Generate Notice Report
    And I click Reset button
    And I navigate to Reports module
    And I click Notice checkbox
    And I click Notice Team ownership filter
    And I select template report name from dropdown randomly
    And I click Include all for notices
    And I select all notice recovery
    And I click Generate Notice Report
    And I select Notice Proceedings
    And I select all Notice Proceedings
    And I click Generate Notice Report
    And I click Multiple Notice
    And I select all Multiple Notice
    And I click Generate Notice Report
    And I click Notice Expenses
    And I select all Notice Expenses
    And I click Generate Notice Report
    And I click Notice Asset
    And I click Notice Asset Include
    And I click Generate Notice Report
    And I click Reset button
    And I click Customer radio button
    And I select template report name from dropdown randomly
    And I click Customer My tab
    And I click Customer Account Include
    And I click Generate Case Report
    And I click Customer Asset
    And I click Customer Asset Include
    And I click Generate Case Report
    And I click Customer OTS Offer
    And I select all Customer OTS Offer
    And I click Generate Case Report
    And I click Customer Expenses
    And I select all Customer Expenses
    And I click Generate Case Report
    And I click Customer Team tab
    And I click Customer Account Include
    And I click Generate Case Report
    And I click Customer Asset
    And I click Customer Asset Include
    And I click Generate Case Report
    And I click Customer OTS Offer
    And I select all Customer OTS Offer
    And I click Generate Case Report
    And I click Customer Expenses
    And I select all Customer Expenses
    And I click Generate Case Report
    And I click Customer All tab
    And I click Customer Account Include
    And I click Generate Case Report
    And I click Customer Asset
    And I click Customer Asset Include
    And I click Generate Case Report
    And I click Customer OTS Offer
    And I select all Customer OTS Offer
    And I click Generate Case Report
    And I click Customer Expenses
    And I select all Customer Expenses
    And I click Generate Case Report
    And I click SARFAESI Report tab
    And I force click Generate Report
    And I click Standard Report tab
    And I select random Standard Report Type
    And I force click Generate Report
    And I click Reset button
    Then complete notice reports should be generated successfully
