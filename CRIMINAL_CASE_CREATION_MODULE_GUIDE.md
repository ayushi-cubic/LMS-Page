# Criminal Case Creation Module Guide

## Overview
This document provides guidance on the Criminal Case Creation automation module. This module automates the complete workflow of creating a criminal case in the LMS system, including the approval workflow and case verification.

## Module Structure

The Criminal Case Creation module follows the same structure as other modules in the framework:

### 1. Page Object Model
**File**: `src/main/java/com/example/pages/CriminalCaseCreationPage.java`

Contains all locators and methods for interacting with criminal case creation pages:
- Navigation methods (Matters → Case → Add New Case → Criminal)
- Form field interactions (Case Type, Customer ID, Account Number, Party, etc.)
- Step navigation (Basic Details, Stake Details, Owner Details, etc.)
- Criminal-specific steps (Cheque Details, Add FIR, Summon Details)
- Approval workflow methods
- Login/Logout functionality

### 2. Step Definitions
**File**: `src/test/java/com/example/stepdefinitions/CriminalCaseCreationStepDefinitions.java`

Implements Cucumber step definitions that map Gherkin scenarios to Java methods.

### 3. Feature File
**File**: `src/test/resources/features/CriminalCaseCreationManagement.feature`

Defines the BDD test scenario in Gherkin language with the tag `@CriminalCaseCreation`.

### 4. Test Runner
**File**: `src/test/java/com/example/runner/CriminalCaseCreationTestRunner.java`

JUnit 5 test runner configured to run only criminal case creation tests.

### 5. Batch File
**File**: `run-criminal-case-tests.bat`

Convenience script to quickly run criminal case tests from command line.

## Test Workflow

The criminal case creation test follows this complete workflow:

### Phase 1: Case Creation (As Ayushi)
1. Login to LMS as "ayushi" user
2. Navigate to Matters → Case → Add New Case
3. Click Criminal button to create criminal case
4. Fill in Basic Details:
   - Select random Case Type
   - Select random Customer ID
   - Select random Account Number
   - Enter Party name
   - Select Issuing Party(s)
   - Select Respondent(s)
   - Select Petitioner's Advocate(s)
   - Enter random Case Number
   - Select random Priority
   - Select random Parties
5. Fill in Stake Details:
   - Enter random Claim Amount
6. Fill in Owner Details:
   - Select Ayushi G from Sr Supervisor dropdown
7. Complete Case Analysis section
8. Fill in Important Dates:
   - Enter Registration/Filing Date
9. Complete Cheque Details section
10. Complete Add FIR section
11. Complete Summon Details section
12. Complete Contingent Liability section
13. Save the case and capture the unique Case ID
14. Logout

### Phase 2: Approval Workflow (As Ajinkya)
1. Login as "Ajinkya" user
2. Navigate to Actionable Items → Approval → Advocate Allocation Approval
3. Apply Advance Filter
4. Navigate to Last page of results
5. Find and select the created case
6. Click Approve button
7. Accept the confirmation alert
8. Logout

### Phase 3: Verification (As Ayushi)
1. Login as "ayushi" user
2. Navigate to Matters → Case
3. Click Load Cases
4. Find the case in the listing
5. Click Action dropdown and select Details
6. Verify case details are displayed

## Differences from Civil Case

The criminal case creation flow includes additional steps compared to civil case:
- **Cheque Details** section
- **Add FIR** section
- **Summon Details** section

These steps occur after "Important Dates" and before "Contingent Liability".

## Running the Tests

### Option 1: Using Batch File
```bash
run-criminal-case-tests.bat
```

### Option 2: Using Maven
```bash
mvn clean test -Dtest=CriminalCaseCreationTestRunner
```

### Option 3: Run All Tests (Including Criminal Case)
```bash
run-all-tests.bat
```

## Key Features

### 1. Random Data Generation
The test generates random data for:
- Case Type (from dropdown options)
- Customer ID
- Account Number
- Party name
- Case Number (format: CRIM + timestamp)
- Priority
- Claim Amount (between 100,000 and 1,000,000)
- Registration Date (current date + random 0-30 days)

### 2. Multi-select Dropdowns
Handles custom multi-select dropdowns for:
- Issuing Party(s)
- Respondent(s)
- Petitioner's Advocate(s)
- Parties

### 3. Complete Approval Workflow
Implements full approval cycle:
- Case creation by one user
- Approval by another user
- Verification by original user

### 4. Case ID Capturing
Captures and stores the unique Case ID for:
- Verification in approval workflow
- Cross-referencing in case listing

## XPath References

All XPaths used in the criminal case creation module are documented in the Page Object Model file. Key XPaths include:

**Navigation:**
- Matters Menu: `/html/body/div[3]/div/div/div/ul/li[3]/a`
- Case Submenu: `/html/body/div[3]/div/div/div/ul/li[3]/ul/li[3]/a`
- Add New Case: `/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div[1]/div[2]/div/div/div[3]/a`
- Criminal Button: `/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div[1]/div[2]/div/div/div[3]/ul/li[2]/a`

**Form Fields:**
- Case Type: `//*[@id='CaseTypeId']`
- Customer ID Input: `//*[@id='flush-collapseOne']/div/div[6]/div/div[1]/div/div[3]/div/span/span[1]/span/ul/li/input`
- Account Number Input: `//*[@id='flush-collapseOne']/div/div[6]/div/div[1]/div/div[4]/div/span/span[1]/span/ul/li/input`
- Party: `//*[@id='WeAre']`
- Case Number: `//*[@id='CaseNumber']`
- Priority: `//*[@id='PriorityId']`
- Claim Amount: `//*[@id='ClaimAmount']`
- Registration Date: `//*[@id='RegistrationFillingDate']`

**Navigation Buttons:**
- Next (Basic Details): `//*[@id='createbasicdetailscust']`
- Next (Stake Details): `//*[@id='createStakeDetailsNXT']`
- Next (Owner Details): `//*[@id='createOwnerDetailsNXT']`
- Next (Case Analysis): `//*[@id='createcaseAnalysisNXT']`
- Next (Important Dates): `//*[@id='createimpdatesNXT']`
- Next (Cheque Details): `//*[@id='createChequeDetailsNXT']`
- Next (Add FIR): `//*[@id='createAddFIRNXT']`
- Next (Summon Details): `//*[@id='createSummonDetailsNXT']`
- Next (Contingent Liability): `//*[@id='createContingentLiabilityNXT']`
- Save: `//*[@id='CustomerCreateNew']`

**Case ID Capture:**
- Case ID Element: `//*[@id='case-basicdetails']/div/div[2]/div/div[4]/div`

**Approval Workflow:**
- Actionable Items: `//*[@id='navLinks']/a[3]`
- Approval Tab: `//*[@id='tab-approval']`
- Advocate Allocation: `//*[@id='ApprovalTypeTabs']/li[2]/a`
- Advance Filter: `//*[@id='v-pills-home']/div[1]/div[2]/a`
- Apply: `//*[@id='BtnAFApply1']`
- Last Page: `//*[@id='paginationControls']/ul/li[7]/a`
- Checkbox: `//*[@id='chkApprovalReq']`
- Approve: `//*[@id='btnapproveadvocate']`

## Login Credentials

The test uses two different user accounts:

**Primary User (Case Creator):**
- Username: ayushi
- Password: Legal@1234
- OTP: 123456

**Approver User:**
- Username: Ajinkya
- Password: pass@123
- OTP: 123456

## Error Handling

The module includes comprehensive error handling:
- Try-catch blocks for all interactions
- Fallback to JavaScript click when regular click fails
- Wait helpers for element visibility and clickability
- Detailed console logging for debugging

## Dependencies

Required dependencies (already in pom.xml):
- Selenium WebDriver
- Cucumber
- JUnit 5
- Maven Surefire Plugin

## Troubleshooting

### Common Issues

1. **Element Not Found**
   - Ensure page has loaded completely
   - Check if XPath has changed
   - Verify element is visible and not hidden

2. **Click Intercepted**
   - Module automatically falls back to JavaScript click
   - May need to scroll element into view

3. **Timeout Issues**
   - Adjust wait times in WaitHelper if needed
   - Check network latency

4. ## Best Practices

1. **Run Tests in Isolation**: Criminal case tests should be run separately to avoid conflicts
2. **Wait for Page Loads**: Module includes appropriate waits between actions
3. **Verify Prerequisites**: Ensure test data (customers, accounts) exist before running
4. **Check Test Environment**: Verify QA environment is accessible

## Integration with Framework

The Criminal Case Creation module integrates seamlessly with the existing framework:
- Uses shared `LoginPage` and `OtpPage` from other modules
- Uses common `WaitHelper` utility
- Follows same coding conventions as other modules
- Reuses Hooks for driver management

## Future Enhancements

Potential improvements:
1. Add data-driven testing for multiple case types
2. Include negative test scenarios
3. Add case deletion/update functionality
4. Implement parallel execution support
5. Add screenshot capture on failure

## Contact & Support

For issues or questions regarding the Criminal Case Creation module, refer to:
- Framework documentation
- Existing civil case module implementation
- Team lead or automation architect
