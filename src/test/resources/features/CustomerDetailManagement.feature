@CustomerDetailManagement
Feature: Customer Detail Management
  As a user of the LMS system
  I want to navigate to customer details and edit all sections
  So that I can manage comprehensive customer information effectively

  Background:
    Given I open the LMS application for customer details
    When I login to LMS with username "ayushi" and password "Legal@1234" for customer details
    And I enter the OTP "123456" for customer details
    Then I should be successfully logged in to customer details

  @EditCustomerDetails @FullFlow
  Scenario: Edit all customer detail sections with random data
    When I fill all customer details with random data
    Then all customer details should be successfully updated

  @NavigateCustomerDetails
  Scenario: Navigate to customer details page
    When I click on Borrowers menu for customer details
    And I click on Customer submenu for customer details
    And I click Load Customers button
    And I click on Action button for first customer
    And I click on Details option
    Then all customer details should be successfully updated

  @EditBasicDetails
  Scenario: Edit customer basic details
    Given I navigate to customer details page
    When I click Edit Basic Details
    And I enter random customer number
    And I enter random customer name
    And I enter random father name
    And I select random customer type
    And I select random industry
    And I select random segment
    And I enter random phone number
    And I enter random email
    And I enter random Adhaar number
    And I enter random PAN
    And I select random address type
    And I enter random address in Address 1
    And I click Save Basic Details
    Then all customer details should be successfully updated

  @EditNPADetails
  Scenario: Edit customer NPA details
    Given I navigate to customer details page
    When I click NPA section
    And I click Edit NPA Details
    And I click Yes for Declared NPA
    And I select random NPA type
    And I enter date of declaring NPA
    And I select random pendency reason
    And I enter random pendency remark
    And I click Save NPA Details
    Then all customer details should be successfully updated

  @EditTrustDetails
  Scenario: Edit Trust/Service Provider details
    Given I navigate to customer details page
    When I click Trust Service Provider section
    And I click Add Trust button
    And I select random Trust ID
    And I enter date of acquisition
    And I enter random selling bank name
    And I click Save Trust
    Then all customer details should be successfully updated

  @EditOwnershipDetails
  Scenario: Edit ownership details with Secondary Dealing Officer
    Given I navigate to customer details page
    When I click Ownership Details section
    And I click Secondary Dealing Officer tab
    And I click Add Secondary Officer button
    And I click officer checkbox
    And I click Save Secondary Officer
    Then all customer details should be successfully updated

  @EditRemarks
  Scenario: Edit customer remarks
    Given I navigate to customer details page
    When I click Remark section
    And I click Edit Remark
    And I enter random remark
    And I click Save Remark
    Then all customer details should be successfully updated

  @NavigateRecoverySummary
  Scenario: Navigate through Recovery Summary and Expense tabs
    Given I navigate to customer details page
    When I click Recovery Summary Expense section
    And I click Recovery Summary tab
    And I click Customer Expenses tab
    Then all customer details should be successfully updated

  @AddComment
  Scenario: Add comment to customer
    Given I navigate to customer details page
    When I click Comment section
    And I enter random comment
    And I click Add Comment button
    Then all customer details should be successfully updated

  @AddCustomerAccount
  Scenario: Add customer account details
    Given I navigate to customer details page
    When I click Customer Other Details section
    And I click Add Account button
    And I enter random loan account number
    And I select random account type
    And I enter random principal amount
    And I enter random rate of interest
    And I enter random outstanding balance
    And I enter loan disbursal date
    And I select random account status
    And I click Basic Information Next
    And I click Bank Consortium Next
    And I click Submit Account
    Then all customer details should be successfully updated

  @AddOTS
  Scenario: Add OTS (One Time Settlement) details
    Given I navigate to customer details page
    When I click OTS One Time Settlement section
    And I click Add OTS button
    And I select random settlement status
    And I enter random settlement amount
    And I click Save OTS
    Then all customer details should be successfully updated

  @AddInformationRequest
  Scenario: Add information request
    Given I navigate to customer details page
    When I click Information Request section
    And I click Add Information Request button
    And I select random To User
    And I enter random information request content
    And I click Save Information Request
    Then all customer details should be successfully updated

  @AddAddressDetails
  Scenario: Add address details
    Given I navigate to customer details page
    When I click Address Details section
    And I click Add Address button
    And I select random address type in section
    And I enter random address in section
    And I select random state
    And I select random location
    And I click Save Address
    Then all customer details should be successfully updated

  @ViewAuditTrail
  Scenario: View Audit Trail
    Given I navigate to customer details page
    When I click Audit Trail section
    Then all customer details should be successfully updated

  @AllSectionsQuick
  Scenario: Quick test - Fill all sections with random data
    Given I navigate to customer details page
    When I fill all basic details with random data
    And I fill all NPA details with random data
    And I fill all trust details with random data
    And I fill all ownership details with random data
    And I fill remark with random data
    And I navigate through recovery summary tabs
    And I fill comment with random data
    And I fill all customer account details with random data
    And I fill all OTS details with random data
    And I fill all information request with random data
    And I fill all address details with random data
    And I click Audit Trail section
    Then all customer details should be successfully updated
