# Reports Module - Quick Commands

## Run Reports Tests

### Using Batch File (Easiest)
```bash
run-reports-tests.bat
```

### Using Maven Command
```bash
mvn clean test -Dtest=ReportsTestRunner
```

### Run Specific Report Scenario
```bash
# Case Reports Only
mvn clean test -Dtest=ReportsTestRunner -Dcucumber.filter.tags="@GenerateCaseReports"

# Team Ownership Report
mvn clean test -Dtest=ReportsTestRunner -Dcucumber.filter.tags="@GenerateTeamOwnershipReport"

# Notice Reports
mvn clean test -Dtest=ReportsTestRunner -Dcucumber.filter.tags="@GenerateNoticeReports"

# Notice Team Ownership
mvn clean test -Dtest=ReportsTestRunner -Dcucumber.filter.tags="@GenerateNoticeTeamOwnershipReport"

# Customer Section Switch
mvn clean test -Dtest=ReportsTestRunner -Dcucumber.filter.tags="@SwitchToCustomerReport"

# End-to-End All Reports
mvn clean test -Dtest=ReportsTestRunner -Dcucumber.filter.tags="@GenerateAllReports"
```

## Generate Reports Excel Test Cases

### Using Batch File (Easiest)
```bash
generate-reports-excel.bat
```

### Using Maven Command
```bash
mvn compile
java -cp "target/classes;target/test-classes;%USERPROFILE%\.m2\repository\org\apache\poi\poi\5.2.3\poi-5.2.3.jar;%USERPROFILE%\.m2\repository\org\apache\poi\poi-ooxml\5.2.3\poi-ooxml-5.2.3.jar;%USERPROFILE%\.m2\repository\org\apache\commons\commons-collections4\4.4\commons-collections4-4.4.jar;%USERPROFILE%\.m2\repository\org\apache\xmlbeans\xmlbeans\5.1.1\xmlbeans-5.1.1.jar" com.example.GenerateReportsExcel
```

## All Available Test Runners

| Module | Batch File | Maven Command | Tag Filter |
|--------|-----------|---------------|-----------|
| **Reports** | `run-reports-tests.bat` | `mvn clean test -Dtest=ReportsTestRunner` | `@ReportsManagement` |
| Customer | `run-customer-tests.bat` | `mvn clean test -Dtest=CustomerTestRunner` | `@CustomerManagement` |
| Account | `run-account-tests.bat` | `mvn clean test -Dtest=AccountTestRunner` | `@AccountManagement` |
| Asset | `run-asset-tests.bat` | `mvn clean test -Dtest=AssetTestRunner` | `@AssetManagement` |
| Contact | `run-contact-tests.bat` (need to create) | `mvn clean test -Dtest=ContactTestRunner` | `@ContactManagement` |
| Notice | `run-notice-tests.bat` | `mvn clean test -Dtest=NoticeTestRunner` | `@NoticeManagement` |
| NoticeCriminal | `run-notice-criminal-tests.bat` | `mvn clean test -Dtest=NoticeCriminalTestRunner` | `@NoticeCriminalManagement` |
| All Tests | `run-all-tests.bat` | `mvn clean test` | All tags |

## All Available Excel Generators

| Module | Batch File | Java Class |
|--------|-----------|------------|
| **Reports** | `generate-reports-excel.bat` | `GenerateReportsExcel` |
| Customer | `generate-excel.bat` | `ExcelTestCaseGenerator` |
| Account | `generate-account-excel.bat` | `GenerateAccountExcel` |
| Contact | `generate-contact-excel.bat` (need to create) | `GenerateContactExcel` |
| Notice | `generate-notice-excel.bat` | `GenerateExcelNoticeDetails` |
| NoticeCriminal | `generate-notice-criminal-excel.bat` | `GenerateExcelNoticeCriminalDetails` |

## Test Scenarios in Reports Module

1. **@GenerateCaseReports** - Generate Case Reports with Recovery, Expenses, and Assets
2. **@GenerateTeamOwnershipReport** - Generate Team Case Ownership Report
3. **@GenerateNoticeReports** - Generate Notice Reports with Recovery and Proceedings
4. **@GenerateNoticeTeamOwnershipReport** - Generate Notice Team Ownership Report
5. **@SwitchToCustomerReport** - Switch to Customer Report Section
6. **@GenerateAllReports** - Generate All Types of Reports End to End

## Login Credentials

All modules use the same credentials:
- **Username**: ayushi
- **Password**: Legal@1234
- **OTP**: 123456

Exception: Contact module uses:
- **Username**: snehal
- **Password**: pass@123
- **OTP**: 123456

## File Structure

```
LMS-Page/
├── src/
│   ├── main/java/com/example/
│   │   ├── pages/
│   │   │   ├── ReportsNavigationPage.java
│   │   │   └── GenerateReportPage.java
│   │   └── GenerateReportsExcel.java
│   └── test/
│       ├── java/com/example/
│       │   ├── runner/
│       │   │   └── ReportsTestRunner.java
│       │   └── stepdefinitions/
│       │       └── ReportsStepDefinitions.java
│       └── resources/features/
│           └── ReportsManagement.feature
├── run-reports-tests.bat
├── generate-reports-excel.bat
└── REPORTS_MODULE_IMPLEMENTATION_SUMMARY.md
```

## View Test Results

### Cucumber HTML Report
```
target/cucumber-reports/cucumber.html
```
Open in browser after test execution.

### Surefire Reports
```
target/surefire-reports/
```

### Generated Excel Files
Look in project root:
```
Reports_Test_Cases_YYYYMMDD_HHMMSS.xlsx
```

## Common Issues and Solutions

### Issue: "Tests run: 0"
**Solution**: Check if tag filter matches feature file tag
```bash
# Verify tag in feature file
findstr "@ReportsManagement" src\test\resources\features\ReportsManagement.feature
```

### Issue: "Element not clickable"
**Solution**: Increase wait times in page objects or check XPath locators

### Issue: "No scenarios found"
**Solution**: Ensure feature file is in `src/test/resources/features/` directory

### Issue: "Excel generation failed"
**Solution**: Ensure Apache POI dependencies are in Maven local repository

## Quick Verification

### Verify Compilation
```bash
mvn clean test-compile -q
```

### Verify Feature File
```bash
type src\test\resources\features\ReportsManagement.feature
```

### Verify Test Runner
```bash
type src\test\java\com\example\runner\ReportsTestRunner.java
```

### List All Test Runners
```bash
dir /b src\test\java\com\example\runner\*TestRunner.java
```

### List All Feature Files
```bash
dir /b src\test\resources\features\*.feature
```

## Next Steps After Test Execution

1. **Review HTML Report**
   - Open `target/cucumber-reports/cucumber.html`
   - Check scenario pass/fail status
   - Review step execution details

2. **Check Screenshots** (if failures occurred)
   - Look in timestamped folders: `screenshots_YYYYMMDD_HHMMSS/`

3. **Review Excel Test Cases**
   - Open generated Excel file
   - Verify test case documentation
   - Share with team for review

4. **Analyze Failures** (if any)
   - Check console output
   - Review stack traces
   - Verify element locators

---
**Quick Start**: Run `run-reports-tests.bat` to execute all Reports tests!
