# Customer Detail Module - Quick Start Guide

## Quick Setup (2 Minutes)

### 1. Prerequisites
- Java 8+ installed
- Maven installed
- Chrome browser
- Git (optional)

### 2. Running Your First Test

#### Option A: Run Full Flow (Recommended for first time)
Double-click: `run-customer-detail-full-flow.bat`

#### Option B: Run All Tests
Double-click: `run-customer-detail-tests.bat`

#### Option C: Interactive Menu
Double-click: `run-customer-detail-menu.bat`
- Select test option (1-6)
- Press Enter

### 3. What Gets Tested?

The **Full Flow** test performs:
1. ✅ Login to LMS portal
2. ✅ Navigate to Customer Details
3. ✅ Edit Basic Details (Customer Info, Contact, PAN, Adhaar)
4. ✅ Edit NPA Details
5. ✅ Add Trust/Service Provider
6. ✅ Add Secondary Dealing Officer
7. ✅ Add Remarks
8. ✅ Navigate Recovery Summary tabs
9. ✅ Add Comments
10. ✅ Add Customer Account (with full workflow)
11. ✅ Add OTS Settlement
12. ✅ Add Information Request
13. ✅ Add New Address
14. ✅ View Audit Trail

**Total Time**: ~5-8 minutes per full flow test

## Quick Commands

### Windows Command Prompt / PowerShell
```batch
# Run all customer detail tests
run-customer-detail-tests.bat

# Run full flow only
run-customer-detail-full-flow.bat

# Interactive menu
run-customer-detail-menu.bat
```

### Maven Commands
```bash
# All customer detail tests
mvn clean test -Dcucumber.filter.tags=@CustomerDetailManagement

# Full flow only
mvn clean test -Dcucumber.filter.tags=@FullFlow

# Specific sections
mvn clean test -Dcucumber.filter.tags=@EditBasicDetails
mvn clean test -Dcucumber.filter.tags=@EditNPADetails
mvn clean test -Dcucumber.filter.tags=@AddCustomerAccount
```

## Test Structure

### Feature File Location
```
src/test/resources/features/CustomerDetailManagement.feature
```

### Key Files
- 📄 **CustomerDetailPage.java** - Page object with all actions
- 📄 **CustomerDetailStepDefinitions.java** - Step implementations
- 📄 **CustomerDetailManagement.feature** - Test scenarios
- 📄 **RandomDataGenerator.java** - Test data generation

## Sample Test Output

```
CustomerDetailPage: Clicking Borrowers menu
CustomerDetailPage: Borrowers menu clicked successfully
CustomerDetailPage: Clicking Customer submenu
CustomerDetailPage: Customer submenu clicked successfully
CustomerDetailPage: Clicking Load Customers button
CustomerDetailPage: Clicking Action button
CustomerDetailPage: Clicking Details button
CustomerDetailPage: Clicking Edit Basic Details
CustomerDetailPage: Entering Customer Number: 8273645901
CustomerDetailPage: Entering Customer Name: John Doe
...
All customer details have been successfully updated
```

## Test Tags

| Tag | Description | Command |
|-----|-------------|---------|
| @CustomerDetailManagement | All tests | `mvn test -Dcucumber.filter.tags=@CustomerDetailManagement` |
| @FullFlow | Complete workflow | `mvn test -Dcucumber.filter.tags=@FullFlow` |
| @EditBasicDetails | Basic info only | `mvn test -Dcucumber.filter.tags=@EditBasicDetails` |
| @EditNPADetails | NPA section only | `mvn test -Dcucumber.filter.tags=@EditNPADetails` |
| @AddCustomerAccount | Account section only | `mvn test -Dcucumber.filter.tags=@AddCustomerAccount` |
| @AddAddressDetails | Address section only | `mvn test -Dcucumber.filter.tags=@AddAddressDetails` |

## Viewing Reports

After test execution, reports are in:
```
target/cucumber-reports/cucumber.html
```

Open in browser to see:
- ✅ Pass/Fail status
- 📊 Execution time
- 📸 Screenshots (if configured)
- 📝 Step-by-step logs

## Common Usage Patterns

### Run Tests Before Deployment
```batch
run-customer-detail-tests.bat
```

### Run Smoke Test (Fast)
```bash
mvn test -Dcucumber.filter.tags="@FullFlow"
```

### Run Regression (Complete)
```bash
mvn test -Dcucumber.filter.tags="@CustomerDetailManagement"
```

### Debug Specific Section
```bash
mvn test -Dcucumber.filter.tags="@EditBasicDetails"
```

## Configuration

### Change Test User
Edit feature file:
```gherkin
When I login to LMS with username "YOUR_USERNAME" and password "YOUR_PASSWORD"
And I enter the OTP "YOUR_OTP"
```

### Change Test Environment
Edit step definitions:
```java
loginPage.openApplication("https://YOUR_ENVIRONMENT_URL");
```

## Troubleshooting (30 seconds)

| Issue | Solution |
|-------|----------|
| "mvn not recognized" | Install Maven or use full path |
| Test fails immediately | Check credentials in feature file |
| Element not found | Increase wait time in WaitHelper |
| Browser doesn't open | Check ChromeDriver version |

## Next Steps

1. ✅ Run `run-customer-detail-full-flow.bat`
2. ✅ Watch test execute automatically
3. ✅ Check console for status
4. ✅ Open HTML report in target/cucumber-reports/

## Support

For detailed documentation, see:
- 📘 **CUSTOMER_DETAIL_MODULE_GUIDE.md** - Complete documentation
- 📘 **README.md** - Framework overview

---

**Quick Start Time**: 2 minutes  
**First Test Time**: 5-8 minutes  
**Automation Coverage**: 13 customer detail sections
