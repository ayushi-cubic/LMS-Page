# Account Detail Module - Quick Start Guide

## Overview
The Account Detail module automates the process of editing and updating account details in the LMS system. This module navigates to an account's detail page and fills in various fields with random or validated data.

## Module Structure

```
Account Detail Module
├── Page Objects
│   ├── AccountDetailNavigationPage.java  - Navigation to account details
│   └── AccountDetailPage.java           - Account detail form operations
├── Step Definitions
│   └── AccountDetailStepDefinitions.java - Cucumber step implementations
├── Feature File
│   └── AccountDetailManagement.feature   - Test scenarios
├── Test Runner
│   └── AccountDetailManagementTestRunner.java
└── Batch File
    └── run-account-detail-tests.bat     - Quick test execution
```

## Quick Start

### Running Tests

#### Option 1: Run all Account Detail tests
```bash
run-account-detail-tests.bat
```

#### Option 2: Run specific scenario
```bash
mvn clean test -Dcucumber.filter.tags=@UpdateAccountDetails
```

#### Option 3: Run step-by-step scenario
```bash
mvn clean test -Dcucumber.filter.tags=@UpdateAccountDetailsStepByStep
```

## Test Scenarios

### 1. Update Account Details (Complete Flow)
**Tag:** `@UpdateAccountDetails`

**Steps:**
1. Login to LMS application
2. Navigate to Borrowers → Account
3. Click Load Customer
4. Click Action → Details (opens new tab)
5. Fill all account detail fields
6. Save changes

### 2. Update Account Details Step by Step
**Tag:** `@UpdateAccountDetailsStepByStep`

**Description:** Same as above but with individual step execution for each field

### 3. Quick Account Details Update
**Tag:** `@QuickAccountDetailsUpdate`

**Description:** Simplified flow for quick testing

## Fields Automated

The module handles the following fields:

### Dropdowns (Random Selection)
- Account Type
- Parent Account
- Zone
- State
- Location
- Account Status
- Business Unit
- NPA Type
- Non Cooperative Borrower
- Scheme Name

### Date Fields (Random/Validated)
- Loan Agreement Date (before Disbursal Date)
- Maturity Date (after Disbursal Date)
- Upload Date
- Date of Block Type 1
- Date of Block Type 2
- NPA Removal Date
- Business Date
- Date of NPA

### Text Fields (Alphanumeric)
- Block Type 1
- Block Type 2

### Radio Buttons
- Fraud (Yes)

## Date Validation Rules

The module includes intelligent date validation:

1. **Loan Agreement Date**: Must be before Loan Disbursal Date
2. **Maturity Date**: Must be after Loan Disbursal Date
3. **Other Dates**: Generated randomly within valid ranges

## Page Objects

### AccountDetailNavigationPage
Handles navigation to account details:
- `clickBorrowersMenu()` - Opens Borrowers menu
- `clickAccountSubmenu()` - Opens Account submenu
- `clickLoadCustomer()` - Loads customer accounts
- `clickActionButton()` - Opens action dropdown
- `clickDetailsButton()` - Opens details in new tab
- `navigateToAccountDetails()` - Complete navigation flow

### AccountDetailPage
Handles form operations:
- `clickEditButton()` - Enables edit mode
- `selectAccountType()` - Selects random account type
- `selectParentAccount()` - Selects random parent account
- `selectAgreementDate()` - Selects date before disbursal
- `selectMaturityDate()` - Selects date after disbursal
- `selectZone()` - Selects random zone
- `selectState()` - Selects random state
- `selectLocation()` - Selects random location
- `selectAccountStatus()` - Selects random status
- `selectBusinessUnit()` - Selects random business unit
- `selectUploadDate()` - Selects random date
- `selectNPAType()` - Selects random NPA type
- `enterBlockType1()` - Enters random alphanumeric
- `selectDateOfBlockType1()` - Selects random date
- `enterBlockType2()` - Enters random alphanumeric
- `selectDateOfBlockType2()` - Selects random date
- `selectNonCooperativeBorrower()` - Selects Yes/No
- `selectNPARemovalDate()` - Selects random date
- `selectBusinessDate()` - Selects random date
- `selectSchemeName()` - Selects random scheme
- `selectDateOfNPA()` - Selects random date
- `clickFraudYes()` - Selects Fraud Yes
- `clickSaveButton()` - Saves changes
- `fillAccountDetailsForm()` - Fills all fields
- `completeAccountDetailUpdate()` - Complete update flow

## XPath Reference

### Navigation
```
Borrowers Menu:    /html/body/div[3]/div/div/div/ul/li[2]/a
Account Submenu:   /html/body/div[2]/div/div/div/ul/li[2]/ul/li[2]/a
Load Customer:     //*[@id="LoadCustomerAccounts"]
Action Button:     //*[@id="accountTableBody"]/tr[1]/td[11]/div/a
Details Button:    //*[@id="accountTableBody"]/tr[1]/td[11]/div/ul/li[2]/a
```

### Form Fields
```
Edit Button:              //*[@id="flush-collapse14"]/div/div/div/div[1]/a
Account Type:             //*[@id="AccountTypeIdEdit"]
Parent Account:           //*[@id="ParentAccountIdEdit"]
Agreement Date:           //*[@id="AgreementDateEdit"]
Maturity Date:            //*[@id="MaturityDateEdit"]
Zone:                     //*[@id="ZoneIdEdit"]
State:                    //*[@id="StateIdEdit"]
Location:                 //*[@id="LocationIdEdit"]
Account Status:           //*[@id="StatusEdit"]
Business Unit:            //*[@id="BusinessUnitIdEdit"]
Upload Date:              //*[@id="UploadDate"]
NPA Type:                 //*[@id="NPATypeOnEdit"]
Block Type 1:             //*[@id="CustomerAccountBlockType1Create"]
Date of Block Type 1:     //*[@id="CustomerAccountDateOfBlockType1Create"]
Block Type 2:             //*[@id="CustomerAccountBlockType2Create"]
Date of Block Type 2:     //*[@id="CustomerAccountDateOfBlockType2Create"]
Non Cooperative Borrower: //*[@id="CustomerAccountCreateNonCooperativeBorrower"]
NPA Removal Date:         //*[@id="CustomerAccountNPARemovalDate"]
Business Date:            //*[@id="EditBusinessDate"]
Scheme Name:              //*[@id="SchemeId"]
Date of NPA:              //*[@id="EditDateOfNPA"]
Fraud Yes:                //*[@id="CustomerAccountFraudYes"]
Save Button:              //*[@id="SubmitBasicEditDetails"]
```

## Dependencies

The module uses existing framework utilities:
- `LoginPage` - For authentication
- `OtpPage` - For OTP verification
- `WaitHelper` - For explicit waits
- `RandomDataGenerator` - For generating test data
- `Hooks` - For WebDriver management

## Test Execution Flow

1. **Setup Phase**
   - Initialize WebDriver
   - Open LMS application
   - Login with credentials
   - Enter OTP

2. **Navigation Phase**
   - Click Borrowers menu
   - Click Account submenu
   - Load customer accounts
   - Open first account details

3. **Data Entry Phase**
   - Click Edit button
   - Fill all form fields with random/validated data
   - Validate date constraints

4. **Save Phase**
   - Click Save button
   - Wait for confirmation

5. **Cleanup Phase**
   - Close browser
   - Generate reports

## Console Output

The module provides detailed console output:
```
✓ Clicked on Borrowers menu
✓ Clicked on Account submenu
✓ Clicked on Load Customer button
✓ Clicked on Action button
✓ Clicked on Details button
✓ Switched to new tab
✓ Clicked Edit button
✓ Selected Account Type: TERM LOAN
✓ Selected Parent Account: ACC379722428700
✓ Selected Loan Agreement Date: 15-Jan-2026
...
✓ Account details updated successfully
```

## Reports

Test reports are generated in:
- HTML Report: `target/cucumber-reports/account-detail-cucumber.html`
- JSON Report: `target/cucumber-reports/account-detail-cucumber.json`

## Troubleshooting

### Common Issues

1. **Element not clickable**
   - The module uses JavascriptExecutor for scrolling
   - Wait times are included before actions

2. **Date validation fails**
   - Module reads existing Disbursal Date
   - Agreement Date is generated before Disbursal
   - Maturity Date is generated after Disbursal

3. **New tab not switching**
   - Module includes explicit tab switching logic
   - Re-initializes page objects after switch

## Best Practices

1. **Running Tests**
   - Use batch file for quick execution
   - Use Maven command for specific tags
   - Check console output for debugging

2. **Maintenance**
   - XPaths are centralized in Page Objects
   - Easy to update if UI changes
   - Step definitions remain unchanged

3. **Extending**
   - Add new fields in AccountDetailPage
   - Add step definitions in AccountDetailStepDefinitions
   - Update feature file scenarios

## Support

For issues or questions:
1. Check console output for detailed logs
2. Review generated reports
3. Verify XPaths are still valid
4. Ensure all dependencies are available

---

**Created:** 2026
**Framework:** Selenium + Cucumber + JUnit 5
**Pattern:** Page Object Model
