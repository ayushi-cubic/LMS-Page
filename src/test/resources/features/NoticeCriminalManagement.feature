@NoticeCriminalManagement
Feature: Notice Criminal Management
  As a user of the LMS system
  I want to create criminal notices
  So that I can manage criminal notice information effectively

  @CreateNoticeCriminal
  Scenario: Create a new criminal notice
    Given I open the LMS application
    When I login with username "ayushi" and password "Legal@1234"
    And I enter OTP "123456"
    Then I should be logged in successfully
    Given I navigate to Matters Notice Criminal menu
    When I fill the criminal notice form with random data
      | Serial Number         | random_alphanumeric |
      | Notice Type          | random_dropdown     |
      | Customer Number      | random_dropdown     |
      | Customer Account     | random_dropdown     |
      | We Are               | random_number       |
      | Issuing Party        | random_checkbox     |
      | Noticee              | random_checkbox     |
      | Notice Reply         | random_dropdown     |
      | Parties              | random_checkbox     |
      | Sr Supervisor        | random_dropdown     |
    Then the criminal notice should be created successfully
    # Approval workflow - logout already done in previous step
    When I login as "ajinkya" with password "pass@123"
    And I enter OTP "123456" for approval user
    Given I navigate to Advocate Allocation Approval section
    When I apply notice filter and navigate to last page
    And I approve the notice
    Then the notice should be approved successfully

  @ApproveCriminalNotice
  Scenario: Create and approve criminal notice
    Given I open the LMS application
    When I login with username "ayushi" and password "Legal@1234"
    And I enter OTP "123456"
    Then I should be logged in successfully
    Given I navigate to Matters Notice Criminal menu
    When I fill the criminal notice form with random data
      | Serial Number         | random_alphanumeric |
      | Notice Type          | random_dropdown     |
      | Customer Number      | random_dropdown     |
      | Customer Account     | random_dropdown     |
      | We Are               | random_number       |
      | Issuing Party        | random_checkbox     |
      | Noticee              | random_checkbox     |
      | Notice Reply         | random_dropdown     |
      | Parties              | random_checkbox     |
      | Sr Supervisor        | random_dropdown     |
    Then the criminal notice should be created successfully
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

  @VerifyNoticeCriminalCreation
  Scenario: Verify criminal notice creation workflow
    Given I open the LMS application
    When I login with username "ayushi" and password "Legal@1234"
    And I enter OTP "123456"
    Then I should be logged in successfully
    Given I navigate to Matters Notice Criminal menu
    When I fill the criminal notice form with random data
      | Serial Number         | random_alphanumeric |
      | Notice Type          | random_dropdown     |
      | Customer Number      | random_dropdown     |
      | Customer Account     | random_dropdown     |
      | We Are               | random_number       |
      | Issuing Party        | random_checkbox     |
      | Noticee              | random_checkbox     |
      | Notice Reply         | random_dropdown     |
      | Parties              | random_checkbox     |
      | Sr Supervisor        | random_dropdown     |
    Then the criminal notice should be created successfully
    And I verify the criminal notice details are correct
