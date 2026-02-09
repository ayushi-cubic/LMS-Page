# Bulk Upload Issues - Diagnosis and Data Specification

## Current Status ✅

1. **Data is being written to downloaded Excel files directly** - FIXED ✓
2. **Customer, Asset, and Account bulk upload tests execute** - Working ✓

## Issues Found 🔴

### 1. Wrong Template Downloaded for Customer Bulk Upload

**Problem:**
- Selected: "Upload Type = Customers" → "Customer Type = Customer (value=3)"
- Downloaded: "Customer Security (3).xlsx" (WRONG - this is an asset template)
- Expected: "Customer.xlsx" (customer template)

**Evidence from test output:**
```
Selected Customer Type: Customer (value=3)
Currently selected: Customer
File downloaded successfully: Customer Security (3).xlsx
Excel columns found:
  Column [0]: Asset Nature
  Column [1]: Type
  Column [2]: Asset Category
  ...
```

**Impact:**
- Customer test data fields don't match the downloaded template columns
- Only 2 fields matched (State, Location) out of 11 customer fields
- Most customer-specific data is lost

### 2. Data Specification for Each Bulk Upload Type

## CUSTOMER BULK UPLOAD

**Current Test Data (from generateCustomerTestData()):**
```java
data.put("customer number", RandomDataGenerator.generateAlphanumeric(10));
data.put("customer name", RandomDataGenerator.generateName());
data.put("father name", "");  // Empty as per requirement
data.put("customer type", "Individual");
data.put("business unit", "CTQA");
data.put("zone", "NA");
data.put("state", "NA");
data.put("location", "NA");
data.put("trust name", "ABC");
data.put("trust code", "002");
data.put("dealing officer 1 emailid", "Ayushi@Test.com");
```

**Expected Template Columns** (need to verify actual Customer.xlsx file):
- Customer Number
- Customer Name  
- Father Name
- Customer Type
- Business Unit
- Zone
- State
- Location
- Trust Name
- Trust Code
- Dealing Officer 1 EmailID

**Wrong Template Downloaded** (Customer Security.xlsx - Asset columns):
- Asset Nature
- Type
- Asset Category
- Asset Name
- Account Number
- (71 total columns - all asset-related)

---

## CUSTOMER ASSET BULK UPLOAD

**Current Test Data (from generateCustomerAssetTestData()):**
```java
data.put("asset nature", "Movable");
data.put("type", "Primary");
data.put("asset category", "New Category");
data.put("asset name", "New asset");
data.put("account number", "ayushi645311");
```

**Template:** Customer Security.xlsx (CORRECT for assets)
**Expected Columns:**
- Asset Nature
- Type
- Asset Category
- Asset Name
- Account Number
- (and 66 other asset-related columns)

**Status:** ✅ Correct template and data match

---

## CUSTOMER ACCOUNT BULK UPLOAD

**Current Test Data (from generateCustomerAccountTestData()):**
```java
data.put("account number", accountNumber);  // ACC + timestamp
data.put("customer number", "202040");
data.put("facility (account type)", "Saving account");
data.put("principal amount", "100000");
data.put("intrate (rate of interest)", "3");
data.put("outstanding balance", "10000");
data.put("business unit", "CTQA");
data.put("zone", "West");
data.put("state", "Maharashtra");
data.put("location", "Akola");
data.put("account status", "Live");
data.put("loan agreement date", "03.08.2025");
data.put("loan disbursal date", "05.08.2025");
data.put("maturity date", "06.08.2025");
```

**Expected Template:** Customer Account.xlsx
**Expected Columns:**
- Account Number
- Customer Number
- Facility (Account Type)
- Principal Amount
- IntRate (Rate of Interest)
- Outstanding Balance
- Business Unit
- Zone
- State
- Location
- Account Status
- Loan Agreement Date
- Loan Disbursal Date
- Maturity Date

**Status:** Need to verify template downloaded matches

---

## Test Reports - Excel Test Case Files

**Files Generated Automatically:**
- `Customer_Search_Test_Cases_<timestamp>.xlsx` - Generated for each test run
- Contains test case documentation
- Located in project root directory

**Purpose:** Documents test cases and execution details in Excel format for review

---

## Recommended Actions

### 1. Verify Template Downloads
Need to check what file is actually downloaded for each selection:
- Customer → should download "Customer.xlsx"
- Customer Securities → should download "Customer Security.xlsx"  
- Customer Account → should download "Customer Account.xlsx"

### 2. Fix Dropdown Selection if Needed
If value="3" for "Customer" is downloading wrong template, may need to:
- Verify dropdown values are correct
- Check if there's a wait needed after selection
- Confirm application is responding correctly to selection

### 3. Verify Data Matches Templates
Once correct templates are downloaded, verify all test data fields match template columns

### 4. Data You Provided
Please specify the exact data you want entered for:
- **Customer Bulk Upload** - What should be in each column?
- **Customer Asset Bulk Upload** - Current data okay or need changes?
- **Customer Account Bulk Upload** - Current data okay or need changes?

---

## Test Execution Results

**Last Run: Customer Bulk Upload**
- Status: ✅ PASSED (but with wrong template/data)
- Template Downloaded: Customer Security (3).xlsx (WRONG)
- Data Matched: Only 2/11 fields (State, Location)
- Upload: Successful (but incorrect data uploaded)

**Tests run: 31, Failures: 0, Errors: 0, Skipped: 30**
**Time: 62.33 seconds**

---

## Next Steps

1. ✅ **DONE:** Data is written to downloaded file (not new timestamped file)
2. 🔴 **NEEDED:** Fix template download issue for Customer bulk upload
3. 🔴 **NEEDED:** Confirm test data specifications from you
4. 🔴 **NEEDED:** Verify all three bulk upload flows with correct templates
5. 🔴 **NEEDED:** Generate proper test report showing pass/fail with correct data

Please provide:
1. The specific data you want entered for Customer, Asset, and Account bulk uploads
2. Confirmation if current asset and account data are correct
3. Any specific test report format you need
