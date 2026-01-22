@NoticeManagement
Feature: Notice Management
  As a user of the LMS system
  I want to create and manage notices
  So that I can handle legal notice information effectively

  @CreateNotice
  Scenario: Create a new civil notice with complete details
    Given I open the LMS application
    When I login with username "ayushi" and password "Legal@1234"
    And I enter OTP "123456"
    Then I should be logged in successfully
    Given I navigate to Matters menu for notices
    And I click on Notice submenu
    And I click on Add New Notice button
    And I click on Civil option from dropdown
    When I fill the notice form with random data
      | Serial Number           | random_alphanumeric |
      | Notice Type             | random_dropdown     |
      | Customer Number         | random_select2      |
      | Customer Account        | random_select2      |
      | We Are                  | random_numeric_1_200|
      | Issuing Party           | random_dropdown     |
      | Noticee                 | random_dropdown     |
      | Notice Reply-Response   | random_dropdown     |
      | Parties                 | random_dropdown     |
    And I navigate through all notice sections
    And I select Sr Supervisor
    And I click Create to submit the notice
    Then the notice should be created successfully
    # Approval workflow - logout already done in previous step
    When I login as "ajinkya" with password "pass@123"
    And I enter OTP "123456" for approval user
    Given I navigate to Advocate Allocation Approval section
    When I apply notice filter and navigate to last page
    And I approve the notice
    Then the notice should be approved successfully

  @ApproveNotice
  Scenario: Approve notice and verify details
    Given I open the LMS application
    When I login with username "ayushi" and password "Legal@1234"
    And I enter OTP "123456"
    Then I should be logged in successfully
    Given I navigate to Matters menu for notices
    And I click on Notice submenu
    And I click on Add New Notice button
    And I click on Civil option from dropdown
    When I fill the notice form with random data
      | Serial Number           | random_alphanumeric |
      | Notice Type             | random_dropdown     |
      | Customer Number         | random_select2      |
      | Customer Account        | random_select2      |
      | We Are                  | random_numeric_1_200|
      | Issuing Party           | random_dropdown     |
      | Noticee                 | random_dropdown     |
      | Notice Reply-Response   | random_dropdown     |
      | Parties                 | random_dropdown     |
    And I navigate through all notice sections
    And I select Sr Supervisor
    And I click Create to submit the notice
    Then the notice should be created successfully
    # Approval workflow - logout already done in previous step
    When I login as "ajinkya" with password "pass@123"
    And I enter OTP "123456" for approval user
    Given I navigate to Advocate Allocation Approval section
    When I apply notice filter and navigate to last page
    And I approve the notice
    Then the notice should be approved successfully
    # Verify as Ayushi
    Given I logout from the current session
    When I login as "ayushi" with password "Legal@1234"
    And I enter OTP "123456" for approval user
    When I navigate to notice listing and view details
    Then I should see the notice details page

  @VerifyNoticeCreation
  Scenario: Verify notice creation with all mandatory fields
    Given I open the LMS application
    When I login with username "ayushi" and password "Legal@1234"
    And I enter OTP "123456"
    Then I should be logged in successfully
    Given I navigate to Matters menu for notices
    And I click on Notice submenu
    And I click on Add New Notice button
    And I click on Civil option from dropdown
    When I fill the notice form with complete details
    And I navigate through all notice sections
    And I select Sr Supervisor
    And I click Create to submit the notice
    Then the notice should be saved successfully
