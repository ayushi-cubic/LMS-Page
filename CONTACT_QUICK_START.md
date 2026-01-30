# Contact Management - Quick Reference

## Quick Commands

### Run All Contact Tests
```bash
run-contact-tests.bat
```

### Run Specific Contact Test Scenario
```bash
# Create Firm Contact
mvn test -Dtest=ContactTestRunner -Dcucumber.filter.tags="@CreateFirmContact"

# Create Employee Contact  
mvn test -Dtest=ContactTestRunner -Dcucumber.filter.tags="@CreateEmployeeContact"

# Edit Employee Contact
mvn test -Dtest=ContactTestRunner -Dcucumber.filter.tags="@SearchAndEditEmployee"

# All Contact Types
mvn test -Dtest=ContactTestRunner -Dcucumber.filter.tags="@CreateAllContactTypes"
```

### Generate Excel Documentation
```bash
generate-contact-excel.bat
```

## Module Files Overview

### Page Objects Created
| File | Purpose | Location |
|------|---------|----------|
| ContactsNavigationPage.java | Navigation & tabs | src/main/java/com/example/pages/ |
| AddFirmContactPage.java | Create Firm contacts | src/main/java/com/example/pages/ |
| AddIndividualContactPage.java | Create Individual contacts | src/main/java/com/example/pages/ |
| AddEmployeeContactPage.java | Create Employee contacts | src/main/java/com/example/pages/ |
| AddOtherPartyContactPage.java | Create Other Party contacts | src/main/java/com/example/pages/ |
| SearchContactPage.java | Search & edit contacts | src/main/java/com/example/pages/ |

### Test Files Created
| File | Purpose | Location |
|------|---------|----------|
| ContactManagement.feature | BDD scenarios | src/test/resources/features/ |
| ContactStepDefinitions.java | Step implementations | src/test/java/com/example/stepdefinitions/ |
| ContactTestRunner.java | JUnit test runner | src/test/java/com/example/runner/ |

### Utilities Created
| File | Purpose | Location |
|------|---------|----------|
| GenerateContactExcel.java | Excel generator | src/main/java/com/example/ |
| run-contact-tests.bat | Test executor | Project root |
| generate-contact-excel.bat | Excel generator | Project root |

## Test Scenarios Available

| Tag | Scenario | Description |
|-----|----------|-------------|
| @CreateFirmContact | Create Firm | Creates a Firm/Company contact |
| @CreateIndividualContact | Create Individual | Creates an Advocate contact |
| @CreateEmployeeContact | Create Employee | Creates an Employee contact |
| @CreateOtherPartyContact | Create Other Party | Creates a Guarantor contact |
| @SearchAndEditEmployee | Edit Employee | Create and edit Employee with ID |
| @SearchAndEditOtherParty | Edit Other Party | Create and edit Other Party with designation |
| @CreateAllContactTypes | All Contacts | Creates all types + edits Employee |

## Contact Types in System

| Contact Type | Form Option | Description |
|--------------|-------------|-------------|
| Firm | option[2] | Company/Organization |
| Individual | option[3] | Advocate/Lawyer |
| Employee | value='6' | Internal employee |
| Other Party | value='4' | Guarantor/External party |

## Reports Location

| Report Type | Location |
|-------------|----------|
| Cucumber HTML | target/cucumber-reports/cucumber.html |
| Cucumber JSON | target/cucumber-reports/cucumber.json |
| Screenshots | target/screenshots_[timestamp]/ |
| Excel Documentation | Contact_Test_Cases_[timestamp].xlsx |

## Framework Integration

✅ **Reused from existing framework:**
- Hooks.java (browser setup/teardown)
- LoginPage.java (login functionality)
- OtpPage.java (OTP entry)
- WaitHelper.java (wait utilities)
- ExcelTestCaseGenerator.java (enhanced with contact methods)

✅ **New implementations:**
- 6 Contact-specific page objects
- 1 Feature file with 7 scenarios
- 1 Step definition class
- 1 Test runner
- 1 Excel generator
- 2 Batch files

## Troubleshooting

### Test Fails to Navigate to Contacts
**Issue:** Cannot find Contacts module link
**Solution:** Check if user has permission to Contacts module. Verify xpath: `//a[.//i[contains(@class,'fa-address-book')]]`

### Element Not Clickable
**Issue:** Element is not clickable or invisible
**Solution:** Page objects use JavaScript click as fallback. Check for iframes or wait time adjustments.

### Random Data Conflicts
**Issue:** Random names might conflict in rare cases
**Solution:** System generates 4-digit random numbers (0-9999), collision probability is low. If needed, increase range.

### Contact Type Selection Fails
**Issue:** Dropdown option not found
**Solution:** Verify option values in application. Current mappings:
- Firm: option[2]
- Advocate: option[3]
- Employee: value='6'
- Guarantor: value='4'

## Next Steps

1. **Run First Test:**
   ```bash
   run-contact-tests.bat
   ```

2. **View Results:**
   - Open `target/cucumber-reports/cucumber.html`
   - Check console output

3. **Generate Documentation:**
   ```bash
   generate-contact-excel.bat
   ```

4. **Integrate with CI/CD:**
   - Add to your Jenkins/GitLab pipeline
   - Use Maven command: `mvn test -Dtest=ContactTestRunner`

## Support

For issues or questions:
1. Check CONTACT_MODULE_GUIDE.md for detailed documentation
2. Review existing page objects in src/main/java/com/example/pages/
3. Compare with Account/Customer modules for pattern consistency
