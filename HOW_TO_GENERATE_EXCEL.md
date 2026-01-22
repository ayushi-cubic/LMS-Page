# How to Generate Excel Test Case Document

## Method 1: Using the Batch Script (Easiest)
1. Double-click `run-tests.bat`
2. Select option `6` (Generate Excel test case document)
3. Wait for the generation to complete
4. Find the Excel file in the project root directory with name format:
   `Customer_Search_Test_Cases_YYYYMMDD_HHMMSS.xlsx`

## Method 2: Using Maven Command
Open terminal/command prompt in the project directory and run:
```bash
mvn compile exec:java -Dexec.mainClass="com.example.GenerateExcelTestCases"
```

## Method 3: Run from IDE (Eclipse/IntelliJ)
1. Open `GenerateExcelTestCases.java` file
2. Right-click on the file
3. Select "Run As" → "Java Application"
4. Check console output for file location

## Excel File Contents

The generated Excel file includes:
- **Test Case ID** - Unique identifier for each test
- **Test Case Name** - Descriptive name
- **Test Scenario** - What is being tested
- **Test Steps** - Detailed step-by-step instructions
- **Test Data** - Input data used
- **Expected Result** - What should happen
- **Actual Result** - What actually happened (sample)
- **Status** - PASS/FAIL with color coding (green/red)
- **Executed By** - Automation
- **Execution Date** - Timestamp
- **Comments** - Additional notes

## Test Cases Included

1. **TC_LOGIN_001** - Login to LMS Application
2. **TC_NAV_001** - Navigate to Add Customer Page
3. **TC_CUST_001** - Create New Customer
4. **TC_SEARCH_001** - Search Customer by Customer Number
5. **TC_E2E_001** - Complete Customer Creation and Search Flow

## File Location

The Excel file will be created in:
```
C:\Users\DELL\Documents\Automation framework\Customer1\demo\
```

Look for files matching the pattern: `Customer_Search_Test_Cases_*.xlsx`

## Troubleshooting

### Issue: Excel file not generated
**Solution:** 
1. Ensure project is compiled: `mvn clean compile`
2. Check if Apache POI dependencies are downloaded
3. Verify you have write permissions in the project directory

### Issue: Cannot open Excel file
**Solution:** 
1. Ensure Microsoft Excel or compatible software is installed
2. If file is corrupted, delete and regenerate
3. Check if antivirus is blocking the file

### Issue: "ClassNotFoundException"
**Solution:** 
Run `mvn clean install` first to download all dependencies

## Customizing Test Cases

To add your own test cases, edit `GenerateExcelTestCases.java`:

```java
// Add custom test case
generator.addCustomerCreationTestCase(
    true,                    // Pass/Fail status
    "CUST789012",           // Customer number
    "Jane Smith"            // Customer name
);
```

Then run the generator again to create a new Excel file.

## Using the Excel File

After generation:
1. Open the Excel file
2. Review the formatted test cases
3. Update actual results after manual testing
4. Change status to PASS or FAIL as needed
5. Add comments for failed tests
6. Share with team or attach to test reports
7. Archive for compliance and documentation
