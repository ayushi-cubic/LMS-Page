# ============================================
# QUICK START GUIDE
# ============================================

## FOR FIRST-TIME USERS

### Step 1: Install Prerequisites
Make sure you have:
- Java 11 or higher (Java 25 recommended)
- Maven 3.6 or higher
- Chrome browser

To check if installed:
```bash
java -version
mvn -version
```

### Step 2: Navigate to Project Directory
```bash
cd "c:\Users\DELL\Documents\Automation framework\Customer1\demo"
```

### Step 3: Install Dependencies
```bash
mvn clean install -DskipTests
```
This will download all required libraries (first time may take 5-10 minutes).

### Step 4: Run Your First Test

#### OPTION A: Using the Batch Script (Easiest)
1. Double-click: `run-tests.bat`
2. Select option 1 (Run all tests)
3. Wait for execution
4. Open: `target\cucumber-reports\cucumber.html` to view results

#### OPTION B: Using Maven Command
```bash
mvn test
```

### Step 5: View Reports
After execution, open in browser:
```
target\cucumber-reports\cucumber.html
```

### Step 6: Generate Excel Test Case Document
```bash
# Option A: Using batch script
Double-click run-tests.bat → Select option 6

# Option B: Using Maven
mvn exec:java -Dexec.mainClass="com.example.utils.ExcelTestCaseGenerator"
```

An Excel file will be created in the project root directory.

## ============================================
## RUNNING SPECIFIC TESTS
## ============================================

### Create Customer Test Only
```bash
mvn test -Dcucumber.filter.tags="@CreateCustomer"
```

### Search Customer Test Only
```bash
mvn test -Dcucumber.filter.tags="@SearchCustomer"
```

### End-to-End Test Only
```bash
mvn test -Dcucumber.filter.tags="@EndToEnd"
```

## ============================================
## UNDERSTANDING THE TEST FLOW
## ============================================

The framework will:
1. Open Chrome browser
2. Navigate to https://qalmsbu.cubictree.com/
3. Login with username: ayushi, password: Legal@1234
4. Enter OTP: 123456
5. Navigate to Borrowers → Customer → Add New Customer
6. Fill form with random data:
   - Customer Number: Random alphanumeric
   - Customer Name: Random name
   - Father Name: Random name
   - All dropdowns: Random selections
   - Phone/Mobile: Random 10-digit numbers
   - Email: Random email
   - Aadhaar: Random 16-digit number
   - Address: Random addresses
7. Click through all Next buttons
8. Save customer
9. Navigate back and search for created customer
10. Verify customer appears in results
11. Take screenshot if any step fails
12. Close browser

## ============================================
## WHAT'S INSIDE
## ============================================

### Test Features (BDD Scenarios)
- src/test/resources/features/CustomerManagement.feature

### Page Objects (Selenium Page Models)
- src/main/java/com/example/pages/LoginPage.java
- src/main/java/com/example/pages/OtpPage.java
- src/main/java/com/example/pages/CustomerNavigationPage.java
- src/main/java/com/example/pages/AddCustomerPage.java

### Utilities (Helpers)
- src/main/java/com/example/utils/RandomDataGenerator.java
- src/main/java/com/example/utils/WaitHelper.java
- src/main/java/com/example/utils/ExcelTestCaseGenerator.java

### Test Configuration
- src/test/java/com/example/runner/TestRunner.java
- src/test/java/com/example/hooks/Hooks.java
- src/test/java/com/example/stepdefinitions/CustomerStepDefinitions.java

### Documentation
- README.md - Detailed documentation
- FRAMEWORK_SUMMARY.txt - Complete implementation summary
- QUICK_START.md - This file

## ============================================
## TROUBLESHOOTING COMMON ISSUES
## ============================================

### Issue 1: "mvn: command not found"
Solution: Install Maven and add to PATH
Download from: https://maven.apache.org/download.cgi

### Issue 2: "java: command not found"
Solution: Install Java JDK and add to PATH
Download from: https://www.oracle.com/java/technologies/downloads/

### Issue 3: Chrome driver version mismatch
Solution: WebDriverManager handles this automatically.
Make sure Chrome browser is up to date.

### Issue 4: Tests fail due to timeout
Solution: 
- Check internet connection
- Increase timeout in WaitHelper.java
- Check if application URL is accessible

### Issue 5: Element not found errors
Solution:
- Application might have changed
- Update locators in page objects
- Check if dropdowns need more wait time

### Issue 6: Cannot compile - Java version error
Solution: Update pom.xml compiler version:
```xml
<maven.compiler.source>11</maven.compiler.source>
<maven.compiler.target>11</maven.compiler.target>
```

## ============================================
## CUSTOMIZATION
## ============================================

### Change Test Data
Edit in CustomerStepDefinitions.java:
- Username/Password
- OTP
- Application URL

### Add New Test Scenario
1. Add scenario in CustomerManagement.feature
2. Implement steps in CustomerStepDefinitions.java
3. Create/update page objects as needed

### Run in Headless Mode
Edit Hooks.java, uncomment:
```java
options.addArguments("--headless");
```

### Change Browser
Replace ChromeDriver with:
- FirefoxDriver
- EdgeDriver
Update WebDriverManager call accordingly

## ============================================
## NEED HELP?
## ============================================

1. Check README.md for detailed documentation
2. Review FRAMEWORK_SUMMARY.txt for complete overview
3. Look at code comments for method explanations
4. Check console output for error messages
5. Review screenshots in reports for failure analysis

## ============================================
## SUCCESS INDICATORS
## ============================================

After running tests, you should see:
✓ Browser opens automatically
✓ Login successful
✓ Customer form appears
✓ Form filled with random data
✓ Customer saved successfully
✓ Search finds the customer
✓ Browser closes automatically
✓ Reports generated in target/cucumber-reports/
✓ Console shows "BUILD SUCCESS"

## ============================================
## YOU'RE READY TO GO! 🚀
## ============================================

Run your first test now:
```bash
mvn test
```

Or double-click: run-tests.bat
