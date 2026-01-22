# Account Module Automation Guide

## Overview
The Account module automation follows the same structure as the Customer module, with separate feature files, step definitions, page objects, and Cucumber tags. This allows Account tests to run independently or alongside Customer tests.

## Module Structure

### 1. Page Objects
- **AccountNavigationPage.java**: Handles navigation to Accounts section
  - Borrowers menu → Accounts submenu → Add New Account
  
- **AddAccountPage.java**: Manages account creation and search
  - Account form fields (Account Number, Type, Customer, etc.)
  - Multi-section navigation (Section 1, Bank Consortium, Address)
  - Search functionality

### 2. Feature File
- **AccountManagement.feature**: Contains test scenarios with `@AccountManagement` tag
  - `@CreateAccount`: Create new account and search
  - `@SearchAccount`: Search for existing account

### 3. Step Definitions
- **AccountStepDefinitions.java**: Implements Cucumber steps for Account module

### 4. Excel Generation
- **GenerateAccountExcel.java**: Creates Excel test case documentation
- **ExcelTestCaseGenerator.java**: Updated with Account-specific test case methods

## Running Tests

### Run Customer Tests Only
```bash
run-customer-tests.bat
```
**Or using Maven:**
```bash
mvn clean test -Dcucumber.filter.tags="@CustomerManagement"
```

### Run Account Tests Only
```bash
run-account-tests.bat
```
**Or using Maven:**
```bash
mvn clean test -Dcucumber.filter.tags="@AccountManagement"
```

### Run Both Customer and Account Tests
```bash
run-all-tests.bat
```
**Or using Maven:**
```bash
mvn clean test -Dcucumber.filter.tags="@CustomerManagement or @AccountManagement"
```

### Run Specific Scenarios
**Create Account only:**
```bash
mvn clean test -Dcucumber.filter.tags="@CreateAccount"
```

**Search Account only:**
```bash
mvn clean test -Dcucumber.filter.tags="@SearchAccount"
```

**Create Customer and Create Account:**
```bash
mvn clean test -Dcucumber.filter.tags="@CreateCustomer or @CreateAccount"
```

## Excel Test Case Documentation

### Generate Customer Excel
```bash
generate-excel.bat
```
**Or:**
```bash
mvn compile exec:java -Dexec.mainClass="com.example.GenerateExcelTestCases"
```

### Generate Account Excel
```bash
generate-account-excel.bat
```
**Or:**
```bash
mvn compile exec:java -Dexec.mainClass="com.example.GenerateAccountExcel"
```

## Account Test Flow

1. **Login** → Application URL with credentials
2. **OTP Verification** → Enter OTP
3. **Navigate to Accounts** → Borrowers → Accounts → Add New Account
4. **Fill Account Form** with random data:
   - Account Number (alphanumeric, 12 chars)
   - Account Type (dropdown)
   - Customer Number (select2 dropdown)
   - Principal Amount (numeric, 6 digits)
   - Rate of Interest (decimal, 2.2 format)
   - Outstanding Balance (numeric, 6 digits)
   - Loan Disbursal Date (date format)
   - Account Status (dropdown)
   - Zone, State, Location, Business Unit (dropdowns)
5. **Multi-section Navigation**:
   - Click Next on Section 1
   - Click Next on Bank Consortium
   - Click Next on Address Information
6. **Save Account** → Click Save button
7. **Search Account** → Back to list → Enter Account Number → Search

## Account Form Fields (XPath Reference)

| Field | XPath |
|-------|-------|
| Account Number | `//*[@id='LoanAccountNumberOnCreate']` |
| Account Type | `//*[@id='AccountTypeOnCreate']` |
| Customer Number | `//*[@id='select2-CustomerIdOnCreatePage-container']` |
| Principal Amount | `//*[@id='PrincipalAmountCreateId']` |
| Rate of Interest | `//*[@id='RateofInterestId']` |
| Outstanding Balance | `//*[@id='OutStandingBalanceCreate']` |
| Loan Disbursal Date | `//*[@id='LoanDisbursalDateCreateId']` |
| Account Status | `//*[@id='CustomerAccountCreateAccountStatus']` |
| Zone | `//*[@id='CustomerAccountCreateZone']` |
| State | `//*[@id='CustomerAccountCreateState']` |
| Location | `//*[@id='CustomerAccountCreateLocation']` |
| Business Unit | `//*[@id='CustomerAccountCreateBusinessUnit']` |
| Next (Section 1) | `//*[@id='flush-collapse1']/div/div[10]` |
| Next (Section 2) | `//*[@id='BankConsortiumCollapse']` |
| Next (Section 3) | `//*[@id='AddressInformationCollapse']` |
| Save | `//*[@id='btnSubmit']` |
| Back | `/html/body/div[2]/div/main/div/div[1]/a` |
| Search Field | `//*[@id='LoanAccountNumberSearch']` |
| Search Button | `/html/body/div[2]/div/main/div/div[2]/div[1]/div/div[1]/div[1]/div[2]/div[1]/form/button` |

## Cucumber Tags

| Tag | Description |
|-----|-------------|
| `@CustomerManagement` | All Customer module tests |
| `@CreateCustomer` | Customer creation scenario |
| `@SearchCustomer` | Customer search scenario |
| `@AccountManagement` | All Account module tests |
| `@CreateAccount` | Account creation scenario |
| `@SearchAccount` | Account search scenario |

## Excel Reports

Both modules generate Excel test case documentation with:
- Test Case ID
- Test Case Name
- Test Scenario
- Test Steps
- Test Data
- Expected Result
- Actual Result
- Status (PASS/FAIL)
- Executed By
- Execution Date
- Comments

**Customer Excel:** `Customer_Search_Test_Cases_YYYYMMDD_HHMMSS.xlsx`
**Account Excel:** `Account_Test_Cases_YYYYMMDD_HHMMSS.xlsx`

## Quick Commands Summary

```bash
# Run tests independently
run-customer-tests.bat          # Customer only
run-account-tests.bat           # Account only
run-all-tests.bat               # Both modules

# Generate Excel reports
generate-excel.bat              # Customer Excel
generate-account-excel.bat      # Account Excel

# Maven commands
mvn clean test -Dcucumber.filter.tags="@CustomerManagement"
mvn clean test -Dcucumber.filter.tags="@AccountManagement"
mvn clean test -Dcucumber.filter.tags="@CustomerManagement or @AccountManagement"
```

## Files Created for Account Module

1. **src/main/java/com/example/pages/**
   - `AccountNavigationPage.java`
   - `AddAccountPage.java`

2. **src/test/java/com/example/stepdefinitions/**
   - `AccountStepDefinitions.java`

3. **src/test/resources/features/**
   - `AccountManagement.feature`

4. **src/main/java/com/example/**
   - `GenerateAccountExcel.java`

5. **Root directory:**
   - `run-customer-tests.bat`
   - `run-account-tests.bat`
   - `run-all-tests.bat`
   - `generate-account-excel.bat`

6. **Updated files:**
   - `ExcelTestCaseGenerator.java` (added Account test case methods)
   - `TestRunner.java` (removed hardcoded tag filter)

## Notes

- Both modules share the same login credentials and OTP
- Customer module must be completed before Account (if running sequentially)
- Each module maintains its own test data via RandomDataGenerator
- Page objects follow the same pattern for consistency
- Excel reports can be generated before or after test execution
