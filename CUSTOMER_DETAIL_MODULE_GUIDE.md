# Customer Detail Management Module

## Overview
The Customer Detail Management module provides comprehensive automation for editing and managing all sections of customer details in the LMS system. This module is organized in a separate "bulk upload" folder structure as requested.

## Module Structure

```
src/
├── main/java/com/example/
│   ├── pages/
│   │   └── CustomerDetailPage.java       # Page Object with all locators and methods
│   └── utils/
│       └── RandomDataGenerator.java      # Enhanced with new methods
├── test/java/com/example/
│   └── stepdefinitions/
│       └── CustomerDetailStepDefinitions.java  # Cucumber step implementations
└── test/resources/features/
    └── CustomerDetailManagement.feature   # BDD feature file with scenarios
```

## Features Covered

The Customer Detail module automates the following sections:

### 1. **Navigation**
- Click Borrowers Menu
- Click Customer Submenu
- Load Customers
- Access customer details via Action > Details

### 2. **Basic Details**
- Customer Number (random)
- Customer Name (random)
- Father Name (random)
- Customer Type (random selection)
- Industry (random selection)
- Segment (random selection)
- Phone Number (6-14 digits validation)
- Email ID (random)
- Adhaar Number (12-digit, starts with 2-9)
- PAN (Format: AAAAA9999A)
- Address Type (random selection)
- Address 1 (random)

### 3. **NPA Details**
- Declared NPA (Yes/No)
- NPA Type selection
- Date of Declaring NPA (DD-MMM-YYYY format)
- Pendency Reason
- Pendency Remark

### 4. **Trust/Service Provider Details**
- Trust ID selection
- Date of Acquisition
- Selling Bank name

### 5. **Ownership Details**
- Secondary Dealing Officer assignment
- Officer checkbox selection

### 6. **Remarks**
- Add/Edit customer remarks (alphanumeric)

### 7. **Recovery Summary / Expense**
- Recovery Summary tab navigation
- Customer Expenses tab navigation

### 8. **Comments**
- Add customer comments

### 9. **Customer Other Details (Accounts)**
- Loan Account Number
- Account Type (random selection)
- Principal Amount
- Rate of Interest
- Outstanding Balance
- Loan Disbursal Date (DD-MMM-YYYY format)
- Account Status (random selection)
- Multi-step form navigation (Basic Info → Bank Consortium → Submit)

### 10. **OTS (One Time Settlement)**
- Settlement Status (Settled/Broken)
- Settlement Amount

### 11. **Information Request**
- To User selection
- Content (alphanumeric)

### 12. **Address Details**
- Address Type (random selection)
- Address 1 (random)
- State (random selection)
- Location (random selection based on state)

### 13. **Audit Trail**
- View audit trail section

## How to Run Tests

### Run All Customer Detail Tests
```bash
run-customer-detail-tests.bat
```

Or using Maven:
```bash
mvn clean test -Dcucumber.filter.tags=@CustomerDetailManagement
```

### Run Specific Scenarios

#### Full Flow Test
```bash
mvn clean test -Dcucumber.filter.tags=@FullFlow
```

#### Individual Section Tests
```bash
# Edit Basic Details only
mvn clean test -Dcucumber.filter.tags=@EditBasicDetails

# Edit NPA Details only
mvn clean test -Dcucumber.filter.tags=@EditNPADetails

# Add Trust Details
mvn clean test -Dcucumber.filter.tags=@EditTrustDetails

# Edit Ownership Details
mvn clean test -Dcucumber.filter.tags=@EditOwnershipDetails

# Add Customer Account
mvn clean test -Dcucumber.filter.tags=@AddCustomerAccount

# Add OTS
mvn clean test -Dcucumber.filter.tags=@AddOTS

# Add Address
mvn clean test -Dcucumber.filter.tags=@AddAddressDetails
```

## Test Scenarios

The feature file includes the following scenarios:

1. **@EditCustomerDetails @FullFlow** - Complete end-to-end test filling all sections
2. **@NavigateCustomerDetails** - Navigation to customer details page
3. **@EditBasicDetails** - Edit basic customer information
4. **@EditNPADetails** - Edit NPA-related information
5. **@EditTrustDetails** - Add trust/service provider details
6. **@EditOwnershipDetails** - Manage ownership and secondary officers
7. **@EditRemarks** - Add/edit remarks
8. **@NavigateRecoverySummary** - Navigate recovery summary tabs
9. **@AddComment** - Add comments
10. **@AddCustomerAccount** - Add customer account details
11. **@AddOTS** - Add OTS settlement details
12. **@AddInformationRequest** - Create information requests
13. **@AddAddressDetails** - Add new address
14. **@ViewAuditTrail** - View audit trail
15. **@AllSectionsQuick** - Quick test for all sections

## Configuration

### Default Credentials
- **URL**: https://qalmsbu.cubictree.com/
- **Username**: ayushi
- **Password**: Legal@1234
- **OTP**: 123456

### Test Data
All test data is generated randomly using `RandomDataGenerator` utility:
- Numbers are validated (e.g., 12-digit Adhaar, 10-digit phone)
- PAN format is validated (AAAAA9999A)
- Dates are formatted as DD-MMM-YYYY
- Dropdowns select random valid options

## XPath Locators

All XPath locators are defined in `CustomerDetailPage.java` using the exact paths provided:
- Absolute XPaths for navigation elements
- Relative XPaths (with IDs) for form fields
- Dropdowns use Select class for interaction
- Dynamic waits ensure elements are ready before interaction

## Random Data Generation

Enhanced `RandomDataGenerator` with new methods:
- `generateRandomNumericString(int length)` - Numeric strings
- `generatePan()` - Valid PAN format
- `generateRandomAlphabeticString(int length)` - Alphabetic strings
- `generateRandomAlphanumericString(int length)` - Alphanumeric strings
- `generateRandomCompanyName()` - Company/bank names
- `generateRandomAccountNumber()` - Account numbers
- `generateRandomAmount()` - Monetary amounts (10,000 - 9,999,999)
- `generateRandomInterestRate()` - Interest rates (5.00 - 18.00%)

## Page Object Pattern

### CustomerDetailPage Methods

#### Navigation Methods
- `navigateToCustomerDetail()` - Complete navigation to details page
- `clickBorrowersMenu()`
- `clickCustomerSubmenu()`
- `clickLoadCustomers()`
- `clickActionButton()`
- `clickDetailsButton()`

#### Section-Specific Methods
Each section has:
- Click section method (e.g., `clickNpaSection()`)
- Individual field methods (e.g., `enterCustomerNumber()`)
- Random selection methods (e.g., `selectRandomCustomerType()`)
- Fill with random data method (e.g., `fillBasicDetailsWithRandomData()`)

#### Master Method
- `fillAllCustomerDetailsWithRandomData()` - Fills ALL sections in sequence

## Validation & Waits

- **WaitHelper**: Custom wait utility for element visibility and clickability
- **Hard waits**: Strategic delays after dropdowns and saves
- **Validation**: Built-in validation for:
  - Phone numbers (6-14 digits)
  - Adhaar (12 digits, starts with 2-9)
  - PAN (Format AAAAA9999A)
  - Dates (DD-MMM-YYYY format)

## Troubleshooting

### Common Issues

1. **Element not found**: Increase wait times in WaitHelper
2. **Dropdown not selecting**: Ensure options are loaded before selection
3. **Stale element**: Re-locate element after page refresh
4. **OTP timeout**: Check if OTP service is available

### Debug Mode
Enable verbose logging by checking console output:
```java
System.out.println("CustomerDetailPage: [operation description]");
```

## Reports

Test execution reports are generated in:
- `target/cucumber-reports/` - Cucumber JSON/HTML reports
- Console output with detailed step execution logs

## Integration with Existing Framework

This module integrates seamlessly with:
- **Hooks**: Uses shared WebDriver from `Hooks.getDriver()`
- **Login Flow**: Reuses existing `LoginPage` and `OtpPage`
- **Utilities**: Leverages existing `WaitHelper` and enhanced `RandomDataGenerator`
- **Runner**: Compatible with existing TestNG/Cucumber runner configuration

## Maintenance

### Adding New Fields
1. Add locator in `CustomerDetailPage.java`
2. Create interaction method in page class
3. Add step definition in `CustomerDetailStepDefinitions.java`
4. Update feature file with new step

### Modifying Locators
Update the By locators in `CustomerDetailPage.java` - all other code remains unchanged due to Page Object pattern.

## Contact & Support

For issues or enhancements, refer to:
- Feature file: `CustomerDetailManagement.feature`
- Page class: `CustomerDetailPage.java`
- Step definitions: `CustomerDetailStepDefinitions.java`

---

**Created**: February 2026  
**Module Type**: Bulk Upload - Customer Detail  
**Framework**: Selenium + Cucumber BDD + Page Object Model
