# Customer Management Automation Framework

## Overview
This is a complete Selenium-based automation framework for testing Customer Management functionality in the LMS application. The framework uses:
- **Selenium 4** for browser automation
- **Cucumber** for BDD-style test scenarios
- **JUnit 5** for test execution
- **Page Object Model (POM)** design pattern
- **Apache POI** for Excel test case documentation
- **WebDriverManager** for automatic driver management

## Project Structure
```
demo/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/example/
│   │           ├── pages/          # Page Object classes
│   │           │   ├── LoginPage.java
│   │           │   ├── OtpPage.java
│   │           │   ├── CustomerNavigationPage.java
│   │           │   └── AddCustomerPage.java
│   │           └── utils/          # Utility classes
│   │               ├── RandomDataGenerator.java
│   │               ├── WaitHelper.java
│   │               └── ExcelTestCaseGenerator.java
│   └── test/
│       ├── java/
│       │   └── com/example/
│       │       ├── hooks/          # Cucumber hooks
│       │       │   └── Hooks.java
│       │       ├── runner/         # Test runner
│       │       │   └── TestRunner.java
│       │       └── stepdefinitions/ # Step definitions
│       │           └── CustomerStepDefinitions.java
│       └── resources/
│           └── features/           # Cucumber feature files
│               └── CustomerManagement.feature
├── pom.xml                         # Maven dependencies
└── README.md                       # This file
```

## Prerequisites
- Java 25 (or Java 11+)
- Maven 3.6+
- Chrome browser installed
- Internet connection for downloading dependencies

## Setup Instructions

### 1. Clone/Download the project
```bash
cd "c:\Users\DELL\Documents\Automation framework\Customer1\demo"
```

### 2. Install dependencies
```bash
mvn clean install -DskipTests
```

### 3. Run tests
```bash
# Run all tests
mvn test

# Run specific tag
mvn test -Dcucumber.filter.tags="@CreateCustomer"

# Run with specific scenarios
mvn test -Dcucumber.filter.tags="@SearchCustomer"
mvn test -Dcucumber.filter.tags="@EndToEnd"
```

## Test Scenarios

### 1. Login Test
- Opens the LMS application
- Logs in with credentials
- Verifies OTP

### 2. Create Customer Test
- Navigates to Add Customer page
- Fills all customer details with random data
- Saves the customer

### 3. Search Customer Test
- Searches for created customer
- Verifies customer appears in results

### 4. End-to-End Test
- Complete flow from login to customer creation and search

## Page Objects

### LoginPage
Handles login functionality:
- Enter email and password
- Click Sign In button

### OtpPage
Handles OTP verification:
- Enter OTP code
- Submit OTP

### CustomerNavigationPage
Handles navigation and search:
- Navigate to Borrowers > Customer > Add New Customer
- Search for customers
- Verify search results

### AddCustomerPage
Handles customer form:
- Fill all customer details
- Navigate through form sections
- Save customer

## Utility Classes

### RandomDataGenerator
Generates random test data:
- Customer numbers, names, emails
- Phone numbers, mobile numbers
- Aadhaar numbers, addresses
- Random alphanumeric strings

### WaitHelper
Provides explicit wait utilities:
- Wait for element visible/clickable
- Wait for dropdowns to load
- JavaScript click and scroll
- Custom wait conditions

### ExcelTestCaseGenerator
Creates Excel test case documentation:
- Generates formatted test case documents
- Includes test steps, data, and status
- Can be run standalone or integrated with tests

## Hooks

The Hooks class manages:
- **@Before**: WebDriver initialization, browser setup
- **@After**: Screenshot on failure, browser cleanup
- **@AfterStep**: Optional step-by-step screenshots

## Configuration

### Test Data
- **URL**: https://qalmsbu.cubictree.com/
- **Username**: ayushi
- **Password**: Legal@1234
- **OTP**: 123456

### Browser Options
Configure in `Hooks.java`:
- Maximized window
- Disable notifications
- Disable popup blocking
- Optional headless mode

## Reports

### Cucumber Reports
After test execution, reports are generated:
- **HTML Report**: `target/cucumber-reports/cucumber.html`
- **JSON Report**: `target/cucumber-reports/cucumber.json`

### Excel Test Case Document
Generate standalone test case documentation:
```bash
mvn exec:java -Dexec.mainClass="com.example.utils.ExcelTestCaseGenerator"
```

## Best Practices Implemented

1. **Page Object Model**: Separation of page logic from tests
2. **Explicit Waits**: Reliable element synchronization
3. **Random Data Generation**: Unique test data for each run
4. **Screenshot on Failure**: Automatic failure documentation
5. **BDD with Cucumber**: Readable test scenarios
6. **Reusable Utilities**: DRY principle
7. **Clean Code**: Well-documented and maintainable

## Troubleshooting

### Common Issues

1. **WebDriver not found**
   - WebDriverManager handles this automatically
   - Ensure internet connection for first run

2. **Element not found**
   - Increase timeout in WaitHelper if needed
   - Check element locators in page objects

3. **Tests failing on dropdowns**
   - Some dropdowns load dynamically
   - Adjust wait times in AddCustomerPage

4. **Chrome version mismatch**
   - WebDriverManager auto-downloads matching driver
   - Update Chrome to latest version

## Running Individual Tests

### Run only Login test
```bash
mvn test -Dcucumber.filter.tags="@CustomerManagement"
```

### Run with custom properties
```bash
mvn test -Dbrowser=chrome -Dheadless=false
```

## Extending the Framework

### Add new page objects
1. Create class in `src/main/java/com/example/pages/`
2. Extend with WebDriver and WaitHelper
3. Use @FindBy annotations for elements

### Add new test scenarios
1. Add scenario in feature file
2. Implement step definitions
3. Update page objects if needed

## Contact & Support
For issues or questions about this framework, please review the code documentation and comments.

## License
This is an automation testing framework created for educational and testing purposes.
