# Bulk Upload Module - Quick Start Guide

## Overview
The Bulk Upload module automates the process of downloading an Excel template, filling it with Civil Case test data, and uploading it back to the LMS system.

## Module Structure

### 1. Page Object
- **Location**: `src/main/java/com/example/pages/BulkUploadPage.java`
- **Purpose**: Contains all page elements and actions for bulk upload functionality

### 2. Step Definitions
- **Location**: `src/test/java/com/example/stepdefinitions/BulkUploadStepDefinitions.java`
- **Purpose**: Maps Gherkin steps to Java methods

### 3. Feature File
- **Location**: `src/test/resources/features/BulkUploadManagement.feature`
- **Purpose**: Contains test scenarios in Gherkin format

### 4. Test Runner
- **Location**: `src/test/java/com/example/runner/BulkUploadTestRunner.java`
- **Purpose**: JUnit 5 test runner configuration

## Automation Workflow

The bulk upload automation follows these steps:

1. **Login**: Uses the same LoginPage to authenticate into the portal
2. **Navigate**: Goes to Upload page (xpath: `/html/body/div[2]/div/div/div/ul/li[8]/a`)
3. **Download**: Clicks "Download Excel Format" button (xpath: `//*[@id="btnstddownload"]`)
4. **Wait**: Waits for Excel file to download to `/downloads` folder
5. **Fill Data**: Uses Apache POI to open and fill Excel with Civil Case test data:
   - Case Type: Civil
   - Case Number: Auto-generated
   - Case Title: Auto-generated
   - Plaintiff Name: Random name
   - Defendant Name: Random name
   - Court Name: Auto-generated
   - Filing Date: Random date
   - Case Status: Active
   - Lawyer Name: Random name with "Adv." prefix
   - Amount: Random amount between 100,000 and 10,000,000
   - Description: Civil case description
   - Contact Number: Random mobile number
   - Email Address: Random email
6. **Upload**: Uploads the filled Excel file (xpath: `//*[@id="Document"]`)
7. **Submit**: Clicks Upload button (xpath: `//*[@id="btnuploaddoc"]`)
8. **Validate**: Checks for success message or status

## Running Tests

### Option 1: Run via Batch File
```batch
run-bulk-upload-tests.bat
```

### Option 2: Run via Maven
```batch
mvn clean test -Dcucumber.filter.tags=@BulkUpload
```

### Option 3: Run Specific Test Runner
```batch
mvn test -Dtest=BulkUploadTestRunner
```

## Test Scenarios

### Scenario 1: Complete Bulk Upload Workflow
- **Tag**: `@BulkUploadCivilCase`
- **Description**: Downloads template, fills with Civil Case data, and uploads
- **Steps**:
  1. Login to LMS application
  2. Navigate to Upload page
  3. Download Excel template
  4. Wait for download completion
  5. Fill Excel with test data
  6. Upload the file
  7. Click Upload button
  8. Validate success

### Scenario 2: End-to-End Workflow
- **Tag**: `@BulkUploadComplete`
- **Description**: Complete workflow including login
- Uses the complete bulk upload workflow method

## File Locations

### Downloaded Files
- **Location**: `downloads/` folder in project root
- **Pattern**: Original filename from server (e.g., `BulkUploadTemplate.xlsx`)

### Updated Files
- **Location**: `downloads/` folder in project root
- **Pattern**: `Updated_<original_filename>.xlsx`

## Key Features

### 1. Dynamic File Handling
- Automatically detects latest downloaded file
- Creates updated version with "Updated_" prefix
- Handles file paths with spaces

### 2. Apache POI Integration
- Opens Excel files (.xlsx format)
- Reads header row to map columns
- Fills data in correct columns
- Saves updated workbook

### 3. Random Test Data Generation
- Uses `RandomDataGenerator` utility
- Generates valid Civil Case data
- Ensures data uniqueness for each run

### 4. Robust Validation
- Multiple methods to detect upload success
- Checks for success message elements
- Validates page source for success keywords
- Logs validation results

## Configuration

### Download Folder
The download folder is automatically set to:
```
<user.home>\Documents\Automation framework\LMS-Page\downloads
```

You can modify this in `BulkUploadPage.java` constructor if needed.

### Login Credentials
Default credentials (can be modified in feature file):
- **Username**: ayushi
- **Password**: Legal@1234
- **OTP**: 123456

## Dependencies

### Required Libraries
- Selenium WebDriver
- Apache POI (for Excel handling)
- Cucumber (for BDD)
- JUnit 5 (for test execution)

### Page Objects Used
- `LoginPage`: For authentication
- `OtpPage`: For OTP verification
- `BulkUploadPage`: For bulk upload operations

## Troubleshooting

### Issue: File not downloading
- Check download folder permissions
- Verify browser download settings
- Check if element xpath is correct

### Issue: Excel file not found
- Ensure sufficient wait time (default: 30 seconds)
- Check download folder path
- Verify file extension (.xlsx)

### Issue: Upload validation fails
- Check success message xpath
- Verify page source for success indicators
- Review browser console for errors

### Issue: Data not filling correctly
- Verify column names in template match code
- Check Excel file format (must be .xlsx)
- Ensure Apache POI dependencies are present

## Maintenance

### Adding New Data Fields
To add new fields to the Excel data:

1. Update `generateCivilCaseTestData()` method in `BulkUploadPage.java`
2. Add new field to the data map:
   ```java
   data.put("NewFieldName", "value");
   ```

### Modifying Test Data
Update the `generateCivilCaseTestData()` method to change:
- Data patterns
- Value ranges
- Field names
- Data types

### Changing Upload Path
Update the xpath in `BulkUploadPage.java`:
```java
@FindBy(xpath = "/html/body/div[2]/div/div/div/ul/li[8]/a")
private WebElement uploadMenuLink;
```

## Best Practices

1. **Clean Downloads Folder**: Periodically clean the downloads folder to avoid confusion
2. **Verify Template**: Ensure the downloaded template structure matches expected columns
3. **Check Logs**: Review console output for detailed execution information
4. **Wait Times**: Adjust wait times based on network speed
5. **File Naming**: Use descriptive names for updated files

## Future Enhancements

Potential improvements for the module:
- Support for multiple case types (Criminal, Civil, etc.)
- Bulk upload of multiple rows
- Excel validation before upload
- Upload result parsing
- Database verification
- Screenshot capture on failure
- Excel test case generator

## Quick Commands

```batch
# Run bulk upload tests
run-bulk-upload-tests.bat

# Generate Excel test cases (future feature)
generate-bulk-upload-excel.bat

# Run all tests including bulk upload
run-all-tests.bat
```

## Contact & Support

For issues or questions about the Bulk Upload module, refer to:
- Project README.md
- FRAMEWORK_SUMMARY.txt
- Other module guides (ACCOUNT_MODULE_GUIDE.md, CONTACT_MODULE_GUIDE.md, etc.)
