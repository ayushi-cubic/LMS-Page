# Quick Command Reference Card

## 🚀 RUN TESTS

### Customer Only
```bash
run-customer-tests.bat
# OR
mvn clean test -Dcucumber.filter.tags="@CustomerManagement"
```

### Account Only
```bash
run-account-tests.bat
# OR
mvn clean test -Dcucumber.filter.tags="@AccountManagement"
```

### Both Modules
```bash
run-all-tests.bat
# OR
mvn clean test -Dcucumber.filter.tags="@CustomerManagement or @AccountManagement"
```

### Specific Scenarios
```bash
# Create Customer only
mvn test -Dcucumber.filter.tags="@CreateCustomer"

# Create Account only
mvn test -Dcucumber.filter.tags="@CreateAccount"

# Search tests only
mvn test -Dcucumber.filter.tags="@SearchAccount or @SearchCustomer"

# Create scenarios from both modules
mvn test -Dcucumber.filter.tags="@CreateCustomer or @CreateAccount"
```

## 📊 GENERATE EXCEL REPORTS

### Customer Excel
```bash
generate-excel.bat
# OR
mvn compile exec:java -Dexec.mainClass="com.example.GenerateExcelTestCases"
```
**Output:** `Customer_Search_Test_Cases_YYYYMMDD_HHMMSS.xlsx`

### Account Excel
```bash
generate-account-excel.bat
# OR
mvn compile exec:java -Dexec.mainClass="com.example.GenerateAccountExcel"
```
**Output:** `Account_Test_Cases_YYYYMMDD_HHMMSS.xlsx`

## 📁 QUICK FILE LOCATIONS

### Page Objects
- Customer: `src/main/java/com/example/pages/CustomerNavigationPage.java`, `AddCustomerPage.java`
- Account: `src/main/java/com/example/pages/AccountNavigationPage.java`, `AddAccountPage.java`

### Feature Files
- Customer: `src/test/resources/features/CustomerManagement.feature`
- Account: `src/test/resources/features/AccountManagement.feature`

### Step Definitions
- Customer: `src/test/java/com/example/stepdefinitions/CustomerStepDefinitions.java`
- Account: `src/test/java/com/example/stepdefinitions/AccountStepDefinitions.java`

### Excel Generators
- Customer: `src/main/java/com/example/GenerateExcelTestCases.java`
- Account: `src/main/java/com/example/GenerateAccountExcel.java`

## 🏷️ CUCUMBER TAGS REFERENCE

| Tag | Description |
|-----|-------------|
| `@CustomerManagement` | All Customer tests |
| `@CreateCustomer` | Customer creation |
| `@SearchCustomer` | Customer search |
| `@AccountManagement` | All Account tests |
| `@CreateAccount` | Account creation |
| `@SearchAccount` | Account search |

## 📝 REPORTS LOCATION

- **Cucumber HTML Report**: `target/cucumber-reports/cucumber.html`
- **Cucumber JSON Report**: `target/cucumber-reports/cucumber.json`
- **Excel Reports**: Project root directory

## ⚙️ USEFUL COMMANDS

```bash
# Clean and compile
mvn clean compile

# Run all tests (default)
mvn clean test

# Clean target folder
mvn clean

# Compile only
mvn compile

# Run with verbose output
mvn clean test -X
```

---
**Quick Start:** Just double-click the `.bat` files to run tests!
