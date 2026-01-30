# Contact Management Module - Implementation Guide

## Overview
This document describes the implementation of the Contact Management module, refactored from the plain Selenium Java repository (https://github.com/snehalgaikwad-rgb/Contacts_LMS) into a Cucumber BDD framework using Page Object Model.

## Module Summary
The Contact Management module handles different types of contacts in the LMS system:
- **Firm/Company Contacts** - Business entities
- **Individual Contacts** - Advocates and individual professionals
- **Employee Contacts** - Internal employees
- **Other Party Contacts** - Guarantors and other related parties

## Files Created

### 1. Page Objects (src/main/java/com/example/pages/)
- **ContactsNavigationPage.java** - Handles navigation to Contacts module and tab switching
- **AddFirmContactPage.java** - Page object for creating Firm/Company contacts
- **AddIndividualContactPage.java** - Page object for creating Individual (Advocate) contacts
- **AddEmployeeContactPage.java** - Page object for creating Employee contacts
- **AddOtherPartyContactPage.java** - Page object for creating Other Party (Guarantor) contacts
- **SearchContactPage.java** - Handles contact search and edit operations

### 2. Feature File (src/test/resources/features/)
- **ContactManagement.feature** - BDD scenarios covering all contact types and operations

### 3. Step Definitions (src/test/java/com/example/stepdefinitions/)
- **ContactStepDefinitions.java** - Cucumber step implementations

### 4. Test Runner (src/test/java/com/example/runner/)
- **ContactTestRunner.java** - JUnit 5 runner for Contact module tests

### 5. Utility Classes (src/main/java/com/example/)
- **GenerateContactExcel.java** - Excel report generator for Contact tests
- **ExcelTestCaseGenerator.java** (updated) - Added contact-specific test case methods

### 6. Batch Files (project root)
- **run-contact-tests.bat** - Runs Contact module tests
- **generate-contact-excel.bat** - Generates Excel test case documentation

## Page Object Architecture

### ContactsNavigationPage
**Responsibilities:**
- Navigate to Contacts module from dashboard
- Switch between contact tabs (Firm, Individual, Employee, Other Parties)
- Click Add New and Single Entry options
- Navigate back to Contacts page using breadcrumb

**Key Methods:**
```java
navigateToContacts()        // Navigate to Contacts module
clickFirmTab()              // Switch to Firm tab
clickIndividualTab()        // Switch to Individual tab
clickEmployeeTab()          // Switch to Employee tab
clickOtherPartiesTab()      // Switch to Other Parties tab
clickAddNewButton()         // Click Add New button
clickSingleEntry()          // Select Single Entry option
navigateBackToContacts()    // Return to Contacts page
```

### AddFirmContactPage
**Responsibilities:**
- Fill and submit Firm contact creation form
- Select contact type (Firm)
- Enter firm details (name, email, address)

**Key Methods:**
```java
selectContactType(String optionValue)
enterFirmName(String firmName)
enterEmail(String email)
clickNext()
clickSave()
fillFirmContactForm()       // Fill complete form with random data
getCreatedFirmName()        // Get the generated firm name
```

### AddIndividualContactPage
**Responsibilities:**
- Fill and submit Individual (Advocate) contact creation form
- Select contact type (Advocate - option 3)
- Enter advocate details

**Key Methods:**
```java
selectIndividualContactType()
enterAdvocateName(String advocateName)
enterEmail(String email)
clickNext()
clickSave()
fillIndividualContactForm() // Fill complete form with random data
getCreatedIndividualName()  // Get the generated name
```

### AddEmployeeContactPage
**Responsibilities:**
- Fill and submit Employee contact creation form
- Select contact type (Employee - value='6')
- Enter employee details

**Key Methods:**
```java
selectEmployeeContactType()
enterEmployeeName(String employeeName)
clickNext()
clickSave()
fillEmployeeContactForm()   // Fill complete form with random data
getCreatedEmployeeName()    // Get the generated name
```

### AddOtherPartyContactPage
**Responsibilities:**
- Fill and submit Other Party (Guarantor) contact creation form
- Select contact type (Guarantor - value='4')
- Enter other party details

**Key Methods:**
```java
selectOtherPartyContactType()
enterOtherPartyName(String otherPartyName)
enterEmail(String email)
clickNext()
clickSave()
fillOtherPartyContactForm() // Fill complete form with random data
getCreatedOtherPartyName()  // Get the generated name
```

### SearchContactPage
**Responsibilities:**
- Search for contacts by name
- Edit Employee contact details (Employee ID)
- Edit Other Party contact details (Designation)

**Key Methods:**
```java
searchContact(String contactName)
clickSearchedContact()
editEmployeeContact(String employeeId)
editOtherPartyContact(String designation)
isContactFound(String contactName)
```

## Feature Scenarios

### 1. Create Firm Contact (@CreateFirmContact)
- Navigate to Contacts module
- Click Firm tab
- Create new Firm contact with random data
- Verify creation success

### 2. Create Individual Contact (@CreateIndividualContact)
- Navigate to Contacts module
- Click Individual tab
- Create new Advocate contact with random data
- Verify creation success

### 3. Create Employee Contact (@CreateEmployeeContact)
- Navigate to Contacts module
- Click Employee tab
- Create new Employee contact with random data
- Verify creation success

### 4. Create Other Party Contact (@CreateOtherPartyContact)
- Navigate to Contacts module
- Click Other Parties tab
- Create new Guarantor contact with random data
- Verify creation success

### 5. Search and Edit Employee (@SearchAndEditEmployee)
- Create Employee contact
- Search for the created employee
- Edit employee with new Employee ID
- Verify update success

### 6. Search and Edit Other Party (@SearchAndEditOtherParty)
- Create Other Party contact
- Search for the created other party
- Edit with new Designation
- Verify update success

### 7. Create All Contact Types (@CreateAllContactTypes)
- Comprehensive scenario creating all contact types in sequence
- Creates Firm, Individual, Other Party, and Employee contacts
- Edits the Employee contact after creation

## How to Run Tests

### Run All Contact Tests
```bash
run-contact-tests.bat
```
OR
```bash
mvn clean test -Dtest=ContactTestRunner
```

### Run Specific Scenario by Tag
```bash
mvn clean test -Dtest=ContactTestRunner -Dcucumber.filter.tags="@CreateEmployeeContact"
```

### Run Multiple Scenarios
```bash
mvn clean test -Dtest=ContactTestRunner -Dcucumber.filter.tags="@CreateEmployeeContact or @SearchAndEditEmployee"
```

## Generate Excel Test Documentation
```bash
generate-contact-excel.bat
```
OR
```bash
mvn exec:java -Dexec.mainClass="com.example.GenerateContactExcel"
```

This creates an Excel file `Contact_Test_Cases_[timestamp].xlsx` with formatted test case documentation.

## Key Implementation Details

### Random Data Generation
All page objects generate random data when null is passed:
- Firm names: `Firm` + random 4-digit number
- Advocate names: `Advocate` + random 4-digit number
- Employee names: `Employee` + random 4-digit number
- Other Party names: `OtherParty` + random 4-digit number
- Emails: `[type][random]@example.com`
- Employee IDs: `EmpId` + random 4-digit number
- Designations: `Designation` + random 4-digit number

### Wait Strategies
- Uses `WaitHelper` utility for explicit waits
- JavaScript execution for reliable element clicks
- Thread.sleep() for stability on form submissions and page transitions

### Error Handling
- Try-catch blocks with descriptive RuntimeException messages
- Fallback strategies for element interactions
- Iframe switching for legacy UI elements

## Comparison with Original Repository

### Original Implementation (Plain Selenium)
- Main class with all logic in one file (ContactsAutomation.java)
- Separate handler classes (LoginHandler, EmployeeTabHandler, etc.)
- TestResultWriter for Excel reporting
- Manual test execution with hardcoded credentials

### Refactored Implementation (Cucumber BDD + POM)
- Clear separation of concerns with Page Objects
- BDD-style feature files for better readability
- Hooks for setup/teardown
- Parameterized scenarios using Cucumber
- Integration with existing framework (LoginPage, OtpPage from Hooks)
- Consistent with existing framework patterns

## Test Execution Flow
1. **Setup** (Hooks.java) - Browser initialization
2. **Login** - Reuses existing LoginPage and OtpPage
3. **Contact Operations** - Uses Contact-specific page objects
4. **Verification** - Console logs and assertions
5. **Teardown** (Hooks.java) - Browser cleanup

## Reports
- **Cucumber HTML Report**: target/cucumber-reports/cucumber.html
- **Cucumber JSON Report**: target/cucumber-reports/cucumber.json
- **Screenshots**: target/screenshots_[timestamp]/
- **Excel Documentation**: Contact_Test_Cases_[timestamp].xlsx

## Integration with Existing Framework
The Contact Management module seamlessly integrates with your existing framework:
- Uses same Hooks for browser management
- Reuses LoginPage and OtpPage
- Follows same naming conventions
- Uses WaitHelper utility
- Compatible with existing test runners
- Batch files follow same pattern as other modules

## Best Practices Implemented
1. **Page Object Model** - Clear separation of page interactions
2. **DRY Principle** - Reusable methods across page objects
3. **BDD Approach** - Human-readable feature files
4. **Explicit Waits** - No hardcoded delays where possible
5. **Error Handling** - Graceful failures with descriptive messages
6. **Random Data** - Test data generation to avoid conflicts
7. **Documentation** - Comprehensive JavaDoc comments
8. **Consistency** - Matches existing framework patterns

## Future Enhancements
- Add validation for success/error messages
- Implement contact deletion scenarios
- Add bulk contact import tests
- Enhance search with filters
- Add contact export functionality
- Implement data-driven testing with external test data
