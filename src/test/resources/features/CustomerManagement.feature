@CustomerManagement
Feature: Customer Management
  As a user of the LMS system
  I want to create and search for customers
  So that I can manage customer information effectively

  @CreateCustomer
  Scenario: Create a new customer and search for it
    Given I open the LMS application
    When I login with username "ayushi" and password "Legal@1234"
    And I enter OTP "123456"
    Then I should be logged in successfully
    Given I navigate to Borrowers menu
    And I click on Customer submenu
    And I click on Add New Customer button
    When I fill the customer form with random data
      | Customer Number   | random_alphanumeric |
      | Customer Name     | random_name         |
      | Father Name       | random_name         |
      | Customer Type     | random_dropdown     |
      | Industry          | random_dropdown     |
      | Segment           | random_dropdown     |
      | Phone No          | random_phone        |
      | Email             | random_email        |
      | Business Unit     | random_dropdown     |
      | Mobile No         | random_mobile       |
      | Aadhaar Number    | random_aadhaar      |
      | Zone              | random_dropdown     |
      | State             | random_dropdown     |
      | Location          | random_dropdown     |
      | Address Type      | random_dropdown     |
      | Address 1         | random_address      |
      | Address 2         | random_address      |
    And I click Next button on Basic Details section
    And I click Next button on NPA section
    And I click Next button on third section
    And I select Sr Supervisor from dropdown
    And I click Next button on fourth section
    And I enter remarks
    And I click Save button
    Then the customer should be created successfully
    Then I verify all customer details sections and delete the customer

# Additional scenarios can be added below as needed
# @SearchCustomer
# @EndToEnd
