# Bulk Upload Module - Quick Reference

## 🚀 Quick Start

Run the bulk upload tests:
```batch
run-bulk-upload-tests.bat
```

## 📁 Files Created

### Page Objects
- [BulkUploadPage.java](src/main/java/com/example/pages/BulkUploadPage.java)

### Step Definitions
- [BulkUploadStepDefinitions.java](src/test/java/com/example/stepdefinitions/BulkUploadStepDefinitions.java)

### Feature Files
- [BulkUploadManagement.feature](src/test/resources/features/BulkUploadManagement.feature)

### Test Runners
- [BulkUploadTestRunner.java](src/test/java/com/example/runner/BulkUploadTestRunner.java)

### Batch Files
- [run-bulk-upload-tests.bat](run-bulk-upload-tests.bat)
- [generate-bulk-upload-excel.bat](generate-bulk-upload-excel.bat)

### Documentation
- [BULK_UPLOAD_MODULE_GUIDE.md](BULK_UPLOAD_MODULE_GUIDE.md)

## 🔄 Workflow

1. **Login** → Uses existing LoginPage
2. **Navigate** → Upload page (xpath: `/html/body/div[2]/div/div/div/ul/li[8]/a`)
3. **Download** → Excel template (button: `//*[@id="btnstddownload"]`)
4. **Fill** → Civil Case test data using Apache POI
5. **Upload** → File input (xpath: `//*[@id="Document"]`)
6. **Submit** → Upload button (xpath: `//*[@id="btnuploaddoc"]`)
7. **Validate** → Success message

## 📊 Test Data Generated

The automation generates realistic Civil Case data:
- Case Number: CIV + random alphanumeric
- Names: Random plaintiff, defendant, lawyer names
- Court Name: Random district court
- Filing Date: Random date (2020-2026)
- Amount: Random (100,000 - 10,000,000)
- Contact: Random mobile number
- Email: Random email address

## 📂 Download Location

Files are saved to:
```
<project-root>/downloads/
```

## 🎯 Tags Available

- `@BulkUpload` - All bulk upload tests
- `@BulkUploadCivilCase` - Civil case upload scenario
- `@BulkUploadComplete` - Complete workflow scenario

## 🛠️ Maven Commands

```batch
# Run bulk upload tests
mvn clean test -Dcucumber.filter.tags=@BulkUpload

# Run specific test runner
mvn test -Dtest=BulkUploadTestRunner

# Compile only
mvn compile
```

## 📋 Test Scenarios

### Scenario 1: Step-by-Step Upload
Performs each step individually for better visibility and debugging.

### Scenario 2: Complete Workflow
Executes the entire workflow as a single operation.

## ✅ Features

- ✅ Reuses existing LoginPage (same login logic as other modules)
- ✅ Apache POI integration for Excel handling
- ✅ Dynamic file download detection
- ✅ Random test data generation
- ✅ Multiple success validation methods
- ✅ Detailed logging
- ✅ BDD with Cucumber
- ✅ Follows project structure (similar to customer, account, asset modules)

## 📞 Login Credentials

Default credentials (configured in feature file):
- Username: `ayushi`
- Password: `Legal@1234`
- OTP: `123456`

## 🔍 Verification

The automation validates upload success by checking:
1. Success message element
2. Page text for success indicators
3. Page source for success keywords

## 📖 Full Documentation

For detailed documentation, see [BULK_UPLOAD_MODULE_GUIDE.md](BULK_UPLOAD_MODULE_GUIDE.md)
