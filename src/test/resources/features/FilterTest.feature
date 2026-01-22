@FilterTest
Feature: Filter Testing
  Test filters independently

  Scenario: Test Customer Filters
    Given I open the LMS application
    When I login with username "ayushi" and password "Legal@1234"
    And I enter OTP "123456"
    Then I should be logged in successfully
    Given I navigate to Borrowers menu
    And I click on Customer submenu
    And I test all filters for the current tab
