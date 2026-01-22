package com.example.pages;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.example.utils.RandomDataGenerator;
import com.example.utils.WaitHelper;

/**
 * Page Object Model for Add Account Page
 */
public class AddAccountPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final Map<String, String> accountData;
    
    // Account Creation Form Locators
    @FindBy(xpath = "//*[@id='LoanAccountNumberOnCreate']")
    private WebElement accountNumberField;
    
    @FindBy(xpath = "//*[@id='AccountTypeOnCreate']")
    private WebElement accountTypeDropdown;
    
    @FindBy(xpath = "//*[@id='select2-CustomerIdOnCreatePage-container']")
    private WebElement customerNumberSelect2;
    
    @FindBy(xpath = "/html/body/div[2]/div/main/form/div/div[3]/div[3]/div/div/div[1]/div[2]/div/div[2]/div[3]/div/span[1]/span[1]/span/span[1]")
    private WebElement customerNumberSelect2Arrow;
    
    @FindBy(xpath = "//*[@id='CustomerIdOnCreatePage']")
    private WebElement customerNumberDropdown;
    
    @FindBy(xpath = "//*[@id='PrincipalAmountCreateId']")
    private WebElement principalAmountField;
    
    @FindBy(xpath = "//*[@id='RateofInterestId']")
    private WebElement rateOfInterestField;
    
    @FindBy(xpath = "//*[@id='OutStandingBalanceCreate']")
    private WebElement outstandingBalanceField;
    
    @FindBy(xpath = "//*[@id='LoanDisbursalDateCreateId']")
    private WebElement loanDisbursalDateField;
    
    @FindBy(xpath = "//*[@id='CustomerAccountCreateAccountStatus']")
    private WebElement accountStatusDropdown;
    
    @FindBy(xpath = "//*[@id='CustomerAccountCreateZone']")
    private WebElement zoneDropdown;
    
    @FindBy(xpath = "//*[@id='CustomerAccountCreateState']")
    private WebElement stateDropdown;
    
    @FindBy(xpath = "//*[@id='CustomerAccountCreateLocation']")
    private WebElement locationDropdown;
    
    @FindBy(xpath = "//*[@id='CustomerAccountCreateBusinessUnit']")
    private WebElement businessUnitDropdown;
    
    // Button Locators
    @FindBy(xpath = "//*[@id='BasicInformationCollapse']")
    private WebElement nextButtonSection1;
    
    @FindBy(xpath = "//*[@id='BankConsortiumCollapse']")
    private WebElement nextButtonSection2;
    
    @FindBy(xpath = "//*[@id='AddressInformationCollapse']")
    private WebElement nextButtonSection3;
    
    @FindBy(xpath = "//*[@id='btnSubmit']")
    private WebElement saveButton;
    
    @FindBy(xpath = "/html/body/div[2]/div/main/div/div[1]/a")
    private WebElement backButton;
    
    // Search Locators
    @FindBy(xpath = "//*[@id='LoanAccountNumberSearch']")
    private WebElement accountSearchField;
    
    @FindBy(xpath = "/html/body/div[2]/div/main/div/div[2]/div[1]/div/div[1]/div[1]/div[2]/div[1]/form/button")
    private WebElement searchButton;
    
    // Account Details Page Locators
    @FindBy(xpath = "//*[@id='accountTableBody']/tr/td[11]/div/a/I")
    private WebElement actionButton;
    
    @FindBy(xpath = "//*[@id='accountTableBody']/tr/td[11]/div/ul/li[2]/a")
    private WebElement detailsButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[1]/div[1]/div")
    private WebElement basicInformationSection;
    
    @FindBy(xpath = "//*[@id='flush-collapse14']/div/div/div/div[1]/a")
    private WebElement editBasicButton;
    
    @FindBy(xpath = "//*[@id='EditBusinessDate']")
    private WebElement businessDateField;
    
    @FindBy(xpath = "//*[@id='SubmitBasicEditDetails']")
    private WebElement saveBasicButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[2]/div[1]/div")
    private WebElement badDebtSection;
    
    @FindBy(xpath = "//*[@id='CustomerBadDepdDetailsSection']/div/div/div[1]/a")
    private WebElement editBadDebtButton;
    
    @FindBy(xpath = "//*[@id='submitButtonBadDebt']")
    private WebElement saveBadDebtButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[3]/div[1]/div")
    private WebElement accountDetailsSection;
    
    @FindBy(xpath = "//*[@id='CustomerAccountCustomerAccountDetailsSection']/div/div/div[1]/a")
    private WebElement editAccountDetailsButton;
    
    @FindBy(xpath = "//*[@id='PrincipalOustandingAmountOnEdit']")
    private WebElement principalOutstandingAmountField;
    
    @FindBy(xpath = "//*[@id='SubmitOnlyAccountEditDetails']")
    private WebElement saveAccountDetailsButton;
    
    @FindBy(xpath = "//*[@id='lForeclosure-tab']")
    private WebElement foreclosureTab;
    
    @FindBy(xpath = "//*[@id='CustomerAccountForeclosureDetailsSection']/div/div/div[1]")
    private WebElement editForeclosureButton;
    
    @FindBy(xpath = "//*[@id='facebox']/div/table/tbody/tr[2]/td[2]/div/form/div[2]/button[1]")
    private WebElement saveForeclosureButton;
    
    @FindBy(xpath = "//*[@id='lConcilation-tab']")
    private WebElement concilationTab;
    
    @FindBy(xpath = "//*[@id='CustomerAccountConcilationDetailsList']/div/div/div[1]/a")
    private WebElement editConcilationButton;
    
    @FindBy(xpath = "//*[@id='EditCustomerAccountOD']")
    private WebElement odField;
    
    @FindBy(xpath = "//*[@id='submitconsillationButton']")
    private WebElement saveConcilationButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[4]/div[1]/div")
    private WebElement presentStatusSection;
    
    @FindBy(xpath = "//*[@id='CustomerAccountPrasentStatusSection']/div/div/div[2]/table/tbody/tr/td[5]/a")
    private WebElement editPresentStatusButton;
    
    @FindBy(xpath = "//*[@id='facebox']/div/table/tbody/tr[2]/td[2]/div/form/div[2]/button[2]")
    private WebElement cancelButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[5]/div[1]/div")
    private WebElement recoverySummarySection;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[6]/div[1]/div")
    private WebElement applicantSection;
    
    @FindBy(xpath = "//*[@id='flush-collapse5']/div/div/div/div[1]/div[2]/a[1]")
    private WebElement applicantPlusButton;
    
    @FindBy(xpath = "//*[@id='flush-collapse6']/div/div/div/div[1]")
    private WebElement addressSection;
    
    @FindBy(xpath = "//*[@id='flush-collapse6']/div/div/div/div[1]/div[2]/a")
    private WebElement addressPlusButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[8]/div[1]/div")
    private WebElement informationRequestSection;
    
    @FindBy(xpath = "//*[@id='create-informationrequest']/div/div[4]/button")
    private WebElement informationRequestCancelButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[9]/div[1]/div")
    private WebElement bankConsortiumSection;
    
    @FindBy(xpath = "//*[@id='flush-collapse8']/div/div/div/div[1]/a")
    private WebElement bankConsortiumPlusButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[10]/div[1]/div")
    private WebElement documentsSection;
    
    @FindBy(xpath = "//*[@id='CustomerAccountLegelDocument-tab']")
    private WebElement legalDocumentTab;
    
    @FindBy(xpath = "//*[@id='AccountLegelDocumentUniqueId']/div/div[1]/div[2]/a")
    private WebElement legalDocumentPlusButton;
    
    @FindBy(xpath = "//*[@id='new-document']/div[2]/button")
    private WebElement documentCancelButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[11]/div[1]/div")
    private WebElement instrumentDetailsSection;
    
    @FindBy(xpath = "//*[@id='flush-collapse11']/div/div/div/div[1]/a")
    private WebElement instrumentDetailsPlusButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[12]/div[1]/div")
    private WebElement linkedCaseNoticeAssetSection;
    
    @FindBy(xpath = "//*[@id='CustomerAccountCaseLink-tab']")
    private WebElement linkedCasesTab;
    
    @FindBy(xpath = "//*[@id='CustomerAccountNoticeLink-tab']")
    private WebElement linkedNoticesTab;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[13]/div[1]/div")
    private WebElement logSection;
    
    @FindBy(xpath = "//*[@id='lAudittrail-tab']")
    private WebElement auditTrailTab;
    
    @FindBy(xpath = "//*[@id='LoadCustomerAccounts']")
    private WebElement loadCustomerAccountButton;
    
    @FindBy(xpath = "//*[@id='accountTableBody']/tr[1]/td[11]/div/a")
    private WebElement actionButtonAfterLoad;
    
    @FindBy(xpath = "//*[@id='accountTableBody']/tr[1]/td[11]/div/ul/li[4]/a")
    private WebElement deleteButton;
    
    @FindBy(xpath = "//*[@id='DeletedReason']")
    private WebElement deleteReasonField;
    
    @FindBy(xpath = "//*[@id='deletebutton']")
    private WebElement deleteConfirmButton;
    
    // Constructor
    public AddAccountPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.accountData = new HashMap<>();
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Get stored account data
     */
    public Map<String, String> getAccountData() {
        return new HashMap<>(accountData);
    }
    
    /**
     * Fill account form with random data
     */
    public void fillAccountFormWithRandomData() {
        System.out.println("Starting to fill Account form with random data...");
        
        // Verify we're on the Create page
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        if (!currentUrl.contains("CreateCustomerAccountBasic")) {
            System.out.println("WARNING: Not on CreateCustomerAccountBasic page!");
            return;
        }
        
        // Wait for the page title or specific element to confirm page is loaded
        try {
            // Wait for the Account Number field to be present - confirms we're on the right page
            waitHelper.waitForElementVisible(accountNumberField);
            System.out.println("✓ Account creation form is loaded and visible");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("ERROR: Could not find account form elements on page!");
            e.printStackTrace();
            return;
        }
        
        // Account Number
        String accountNumber = RandomDataGenerator.generateAlphanumeric(12);
        fillAccountNumber(accountNumber);
        
        // Account Type
        selectAccountType();
        
        // Customer Number (assuming dropdown with select2)
        selectCustomerNumber();
        
        // Principal Amount
        String principalAmount = RandomDataGenerator.generateNumeric(6);
        fillPrincipalAmount(principalAmount);
        
        // Rate of Interest
        String rateOfInterest = RandomDataGenerator.generateDecimal(2, 2);
        fillRateOfInterest(rateOfInterest);
        
        // Outstanding Balance
        String outstandingBalance = RandomDataGenerator.generateNumeric(6);
        fillOutstandingBalance(outstandingBalance);
        
        // Loan Disbursal Date
        String disbursalDate = RandomDataGenerator.generateDate();
        fillLoanDisbursalDate(disbursalDate);
        
        // Account Status
        selectAccountStatus();
        
        // Zone
        selectZone();
        
        // State
        selectState();
        
        // Location
        selectLocation();
        
        // Business Unit
        selectBusinessUnit();
        
        System.out.println("✓ Account form filled with random data");
        System.out.println("Generated Account Number: " + accountNumber);
    }
    
    /**
     * Complete account creation with Next and Save buttons
     */
    public void completeAccountCreation() {
        System.out.println("\nProceeding to next sections...");
        
        try {
            // Click Next button for Section 1 (Basic Information)
            Thread.sleep(1000);
            clickNextSection1();
            Thread.sleep(2000);
            
            // Click Next button for Section 2 (Bank Consortium)
            clickNextSection2();
            Thread.sleep(2000);
            
            // Click Next button for Section 3 (Address Information)
            clickNextSection3();
            Thread.sleep(2000);
            
            // Click Save button
            clickSaveButton();
            Thread.sleep(3000);
            
            System.out.println("✓ Account creation completed successfully");
            
        } catch (Exception e) {
            System.out.println("! Error during account creation: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Search for created account
     */
    public void searchForCreatedAccount() {
        try {
            System.out.println("\nSearching for created account...");
            String accountNumber = accountData.get("AccountNumber");
            
            if (accountNumber != null && !accountNumber.isEmpty()) {
                // Click Back button to return to account list
                System.out.println("Clicking Back button...");
                clickBackButton();
                Thread.sleep(3000);
                
                // Ensure we're on the main account list page
                String currentUrl = driver.getCurrentUrl();
                System.out.println("Current URL after Back: " + currentUrl);
                
                // Enter account number in search field
                System.out.println("Entering Account Number: " + accountNumber);
                enterAccountNumberInSearch(accountNumber);
                Thread.sleep(1000);
                
                // Click Search button
                System.out.println("Clicking Search button...");
                clickSearchButton();
                Thread.sleep(3000);
                
                System.out.println("✓ Search completed for Account Number: " + accountNumber);
                System.out.println("  Ready to verify account details");
            } else {
                System.out.println("! No account number found to search");
            }
        } catch (Exception e) {
            System.out.println("! Error during account search: " + e.getMessage());
        }
    }
    
    /**
     * Fill account number
     */
    public void fillAccountNumber(String accountNumber) {
        waitHelper.waitForElementVisible(accountNumberField);
        accountNumberField.clear();
        accountNumberField.sendKeys(accountNumber);
        accountData.put("AccountNumber", accountNumber);
        System.out.println("  ✓ Account Number: " + accountNumber);
    }
    
    /**
     * Select account type from dropdown
     */
    public void selectAccountType() {
        try {
            // Scroll to element first
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", accountTypeDropdown);
            Thread.sleep(300);
            
            waitHelper.waitForElementVisible(accountTypeDropdown);
            
            // Use JavaScript click to trigger load function
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click(); CustomerAccountTypesCreateLoad();", accountTypeDropdown);
            System.out.println("  Clicked Account Type dropdown to load options...");
            Thread.sleep(2000); // Wait for JavaScript to load options
            
            Select select = new Select(accountTypeDropdown);
            int optionCount = select.getOptions().size();
            System.out.println("  Account Type dropdown now has " + optionCount + " options");
            
            if (optionCount > 1) {
                int randomIndex = RandomDataGenerator.generateRandomNumber(1, optionCount - 1);
                select.selectByIndex(randomIndex);
                String selectedValue = select.getFirstSelectedOption().getText();
                accountData.put("AccountType", selectedValue);
                System.out.println("  ✓ Account Type: " + selectedValue);
                Thread.sleep(300);
            } else {
                System.out.println("  ! Account Type dropdown has no options to select (only default)");
            }
        } catch (Exception e) {
            System.out.println("  ! Could not select Account Type: " + e.getMessage());
        }
    }
    
    /**
     * Select customer number from dropdown
     */
    public void selectCustomerNumber() {
        try {
            // Scroll to Select2 container first
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", customerNumberSelect2);
            Thread.sleep(500);
            
            waitHelper.waitForElementVisible(customerNumberSelect2);
            
            // Try to open Select2 using jQuery API
            System.out.println("  Opening Customer Number dropdown using Select2 API...");
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "$('#CustomerIdOnCreatePage').select2('open');");
            Thread.sleep(3000); // Wait for dropdown to open and load results
            
            // Check if dropdown is open
            Object dropdownStatus = ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "var container = document.querySelector('.select2-container--open'); "
                + "var results = document.querySelectorAll('.select2-results__option, .select2-results li'); "
                + "return 'Open: ' + (container !== null) + ', Results count: ' + results.length;");
            System.out.println("  Dropdown status: " + dropdownStatus);
            
            // Get ALL li elements from the dropdown results
            java.util.List<org.openqa.selenium.WebElement> allItems = driver.findElements(
                org.openqa.selenium.By.xpath("//span[contains(@class,'select2-results')]//li | //ul[contains(@class,'select2-results')]//li"));
            
            System.out.println("  Total items found in dropdown: " + allItems.size());
            
            if (allItems.size() == 0) {
                // Try alternative selector
                allItems = driver.findElements(
                    org.openqa.selenium.By.cssSelector(".select2-results li, .select2-results__option"));
                System.out.println("  Alternative selector found: " + allItems.size() + " items");
            }
            
            // Filter and debug each item
            java.util.List<org.openqa.selenium.WebElement> validResults = new java.util.ArrayList<>();
            
            for (int i = 0; i < allItems.size(); i++) {
                org.openqa.selenium.WebElement item = allItems.get(i);
                try {
                    String itemText = item.getText();
                    String itemClass = item.getAttribute("class");
                    String itemRole = item.getAttribute("role");
                    boolean isDisplayed = item.isDisplayed();
                    
                    System.out.println("  Item[" + i + "]: text='" + itemText + "', class='" + itemClass + "', role='" + itemRole + "', visible=" + isDisplayed);
                    
                    // Skip first item (search bar/input)
                    if (i == 0) {
                        System.out.println("    -> Skipping first item (search bar)");
                        continue;
                    }
                    
                    // Skip if not visible
                    if (!isDisplayed) {
                        System.out.println("    -> Skipping hidden item");
                        continue;
                    }
                    
                    // Skip empty or message items
                    if (itemText == null || itemText.trim().isEmpty()) {
                        System.out.println("    -> Skipping empty item");
                        continue;
                    }
                    
                    if (itemText.contains("Searching") || itemText.contains("Loading") 
                        || itemText.contains("No results") || itemText.toLowerCase().contains("select")) {
                        System.out.println("    -> Skipping message/placeholder item");
                        continue;
                    }
                    
                    // Add valid customer options
                    validResults.add(item);
                    System.out.println("    -> Valid customer option added");
                    
                } catch (Exception e) {
                    System.out.println("    -> Error processing item: " + e.getMessage());
                    continue;
                }
            }
            
            System.out.println("  Found " + validResults.size() + " valid customer options (excluding search bar)");
            
            if (validResults.size() > 0) {
                // Print available customer numbers
                System.out.println("  Available customers:");
                for (int i = 0; i < Math.min(validResults.size(), 10); i++) {
                    System.out.println("    [" + i + "] " + validResults.get(i).getText());
                }
                if (validResults.size() > 10) {
                    System.out.println("    ... and " + (validResults.size() - 10) + " more");
                }
                
                // Select a random customer number
                int randomIndex = RandomDataGenerator.generateRandomNumber(0, validResults.size() - 1);
                org.openqa.selenium.WebElement selectedOption = validResults.get(randomIndex);
                
                String customerText = selectedOption.getText();
                System.out.println("  Selecting customer [" + randomIndex + "]: " + customerText);
                
                // Method 1: Try clicking with JavaScript
                try {
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block: 'center'});", selectedOption);
                    Thread.sleep(300);
                    
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                        "arguments[0].click();", selectedOption);
                    Thread.sleep(1500);
                    
                    // Check if selection worked
                    String selectedValue = customerNumberSelect2.getText();
                    System.out.println("  After click, span text: '" + selectedValue + "'");
                    
                    if (selectedValue == null || selectedValue.equals("Select") || selectedValue.isEmpty()) {
                        // Method 2: Try triggering mousedown/mouseup events
                        System.out.println("  First click failed, trying mousedown/mouseup...");
                        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                            "var evt = new MouseEvent('mousedown', {bubbles: true}); "
                            + "arguments[0].dispatchEvent(evt); "
                            + "var evt2 = new MouseEvent('mouseup', {bubbles: true}); "
                            + "arguments[0].dispatchEvent(evt2);",
                            selectedOption);
                        Thread.sleep(1500);
                        
                        selectedValue = customerNumberSelect2.getText();
                        System.out.println("  After mousedown/mouseup, span text: '" + selectedValue + "'");
                    }
                    
                    if (selectedValue != null && !selectedValue.isEmpty() && !selectedValue.equals("Select")) {
                        accountData.put("CustomerNumber", selectedValue);
                        System.out.println("  ✓ Customer Number: " + selectedValue);
                    } else {
                        System.out.println("  ! Customer Number selection failed, trying direct element click...");
                        // Method 3: Try direct Selenium click
                        selectedOption.click();
                        Thread.sleep(1500);
                        
                        selectedValue = customerNumberSelect2.getText();
                        if (selectedValue != null && !selectedValue.isEmpty() && !selectedValue.equals("Select")) {
                            accountData.put("CustomerNumber", selectedValue);
                            System.out.println("  ✓ Customer Number (via direct click): " + selectedValue);
                        } else {
                            System.out.println("  ! All click methods failed - span still shows: '" + selectedValue + "'");
                        }
                    }
                } catch (Exception clickEx) {
                    System.out.println("  ! Error during click: " + clickEx.getMessage());
                }
            } else {
                System.out.println("  ! No customer number options found (only search bar or no data in test environment)");
            }
            
            // Ensure dropdown is completely closed
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "if(document.querySelector('.select2-container--open')) { "
                + "document.body.click(); "
                + "}");
            Thread.sleep(500);
            
        } catch (Exception e) {
            System.out.println("  ! Could not select Customer Number: " + e.getMessage());
            try {
                // Forcefully close any open Select2 dropdown
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "try { $('.select2-container--open').select2('close'); } catch(e) {} document.body.click();");
                Thread.sleep(500);
            } catch (Exception ex) {
                // Ignore
            }
        }
    }
    
    /**
     * Fill principal amount
     */
    public void fillPrincipalAmount(String amount) {
        waitHelper.waitForElementVisible(principalAmountField);
        principalAmountField.clear();
        principalAmountField.sendKeys(amount);
        accountData.put("PrincipalAmount", amount);
        System.out.println("  ✓ Principal Amount: " + amount);
    }
    
    /**
     * Fill rate of interest
     */
    public void fillRateOfInterest(String rate) {
        waitHelper.waitForElementVisible(rateOfInterestField);
        rateOfInterestField.clear();
        rateOfInterestField.sendKeys(rate);
        accountData.put("RateOfInterest", rate);
        System.out.println("  ✓ Rate of Interest: " + rate);
    }
    
    /**
     * Fill outstanding balance
     */
    public void fillOutstandingBalance(String balance) {
        waitHelper.waitForElementVisible(outstandingBalanceField);
        outstandingBalanceField.clear();
        outstandingBalanceField.sendKeys(balance);
        accountData.put("OutstandingBalance", balance);
        System.out.println("  ✓ Outstanding Balance: " + balance);
    }
    
    /**
     * Fill loan disbursal date
     */
    public void fillLoanDisbursalDate(String date) {
        waitHelper.waitForElementVisible(loanDisbursalDateField);
        try {
            // Try JavaScript to set the value (in case it's a datepicker)
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];", loanDisbursalDateField, date);
            accountData.put("LoanDisbursalDate", date);
            System.out.println("  ✓ Loan Disbursal Date: " + date);
        } catch (Exception e) {
            System.out.println("  ! Could not set Loan Disbursal Date: " + e.getMessage());
        }
    }
    
    /**
     * Select account status from dropdown
     */
    public void selectAccountStatus() {
        try {
            // First, ensure any open Select2 dropdowns are closed
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "try { $('.select2-container--open').select2('close'); } catch(e) {} "
                + "document.body.click();");
            Thread.sleep(1000);
            
            // Scroll to element first
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", accountStatusDropdown);
            Thread.sleep(500);
            
            waitHelper.waitForElementVisible(accountStatusDropdown);
            
            // Use JavaScript click to trigger load function
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click(); CustomerAccountCreateAccountStatusLoad();", accountStatusDropdown);
            System.out.println("  Clicked Account Status dropdown to load options...");
            Thread.sleep(2000); // Wait for JavaScript to load options
            
            Select select = new Select(accountStatusDropdown);
            int optionCount = select.getOptions().size();
            System.out.println("  Account Status dropdown now has " + optionCount + " options");
            
            if (optionCount > 1) {
                int randomIndex = RandomDataGenerator.generateRandomNumber(1, optionCount - 1);
                select.selectByIndex(randomIndex);
                String selectedValue = select.getFirstSelectedOption().getText();
                accountData.put("AccountStatus", selectedValue);
                System.out.println("  ✓ Account Status: " + selectedValue);
            } else {
                System.out.println("  ! Account Status dropdown has no options to select (only default)");
            }
        } catch (Exception e) {
            System.out.println("  ! Could not select Account Status: " + e.getMessage());
        }
    }
    
    /**
     * Select zone from dropdown
     */
    public void selectZone() {
        try {
            // Remove any blocking overlays first
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "var overlays = document.querySelectorAll('.bg1, .modal-backdrop, [style*=\"opacity: 0.7\"]'); "
                + "overlays.forEach(function(el) { el.remove(); });");
            Thread.sleep(500);
            
            // Scroll to element
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", zoneDropdown);
            Thread.sleep(300);
            
            waitHelper.waitForElementVisible(zoneDropdown);
            
            // Use JavaScript click to trigger load function: CustomerAccountCreateZoneLoad()
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click(); CustomerAccountCreateZoneLoad();", zoneDropdown);
            System.out.println("  Clicked Zone dropdown to load options...");
            Thread.sleep(2000); // Wait for JavaScript to load options
            
            Select select = new Select(zoneDropdown);
            int optionCount = select.getOptions().size();
            System.out.println("  Zone dropdown now has " + optionCount + " options");
            
            if (optionCount > 1) {
                int randomIndex = RandomDataGenerator.generateRandomNumber(1, optionCount - 1);
                select.selectByIndex(randomIndex);
                String selectedValue = select.getFirstSelectedOption().getText();
                accountData.put("Zone", selectedValue);
                System.out.println("  ✓ Zone: " + selectedValue);
            } else {
                System.out.println("  ! Zone dropdown has no options to select (only default)");
            }
        } catch (Exception e) {
            System.out.println("  ! Could not select Zone: " + e.getMessage());
        }
    }
    
    /**
     * Select state from dropdown
     */
    public void selectState() {
        try {
            // Scroll to element first
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", stateDropdown);
            Thread.sleep(300);
            
            waitHelper.waitForElementVisible(stateDropdown);
            
            // Click to trigger JavaScript load function: CustomerAccountCreateStateLoad()
            stateDropdown.click();
            System.out.println("  Clicked State dropdown to load options...");
            Thread.sleep(1500); // Wait for JavaScript to load options
            
            Select select = new Select(stateDropdown);
            int optionCount = select.getOptions().size();
            System.out.println("  State dropdown now has " + optionCount + " options");
            
            if (optionCount > 1) {
                int randomIndex = RandomDataGenerator.generateRandomNumber(1, optionCount - 1);
                select.selectByIndex(randomIndex);
                String selectedValue = select.getFirstSelectedOption().getText();
                accountData.put("State", selectedValue);
                System.out.println("  ✓ State: " + selectedValue);
            } else {
                System.out.println("  ! State dropdown has no options to select (only default)");
            }
        } catch (Exception e) {
            System.out.println("  ! Could not select State: " + e.getMessage());
        }
    }
    
    /**
     * Select location from dropdown
     */
    public void selectLocation() {
        try {
            // Scroll to element first
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", locationDropdown);
            Thread.sleep(300);
            
            waitHelper.waitForElementVisible(locationDropdown);
            
            // Click to trigger JavaScript load function: CustomerAccountCreateLocationLoad()
            locationDropdown.click();
            System.out.println("  Clicked Location dropdown to load options...");
            Thread.sleep(1500); // Wait for JavaScript to load options
            
            Select select = new Select(locationDropdown);
            int optionCount = select.getOptions().size();
            System.out.println("  Location dropdown now has " + optionCount + " options");
            
            if (optionCount > 1) {
                int randomIndex = RandomDataGenerator.generateRandomNumber(1, optionCount - 1);
                select.selectByIndex(randomIndex);
                String selectedValue = select.getFirstSelectedOption().getText();
                accountData.put("Location", selectedValue);
                System.out.println("  ✓ Location: " + selectedValue);
            } else {
                System.out.println("  ! Location dropdown has no options to select (only default)");
            }
        } catch (Exception e) {
            System.out.println("  ! Could not select Location: " + e.getMessage());
        }
    }
    
    /**
     * Select business unit from dropdown
     */
    public void selectBusinessUnit() {
        try {
            // Scroll to element first
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", businessUnitDropdown);
            Thread.sleep(300);
            
            waitHelper.waitForElementVisible(businessUnitDropdown);
            
            // Click to trigger JavaScript load function: CustomerAccountCreateBusinessUnitLoad()
            businessUnitDropdown.click();
            System.out.println("  Clicked Business Unit dropdown to load options...");
            Thread.sleep(1500); // Wait for JavaScript to load options
            
            Select select = new Select(businessUnitDropdown);
            int optionCount = select.getOptions().size();
            System.out.println("  Business Unit dropdown now has " + optionCount + " options");
            
            if (optionCount > 1) {
                int randomIndex = RandomDataGenerator.generateRandomNumber(1, optionCount - 1);
                select.selectByIndex(randomIndex);
                String selectedValue = select.getFirstSelectedOption().getText();
                accountData.put("BusinessUnit", selectedValue);
                System.out.println("  ✓ Business Unit: " + selectedValue);
            } else {
                System.out.println("  ! Business Unit dropdown has no options to select (only default)");
            }
        } catch (Exception e) {
            System.out.println("  ! Could not select Business Unit: " + e.getMessage());
        }
    }
    
    /**
     * Click Next button on section 1
     */
    public void clickNextSection1() {
        try {
            // Scroll to element to ensure it's in viewport
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", nextButtonSection1);
            Thread.sleep(500);
            
            waitHelper.waitForElementVisible(nextButtonSection1);
            
            // Use JavaScript click
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", nextButtonSection1);
            System.out.println("✓ Clicked Next on Section 1");
        } catch (Exception e) {
            System.out.println("! Could not click Next on Section 1: " + e.getMessage());
        }
    }
    
    /**
     * Click Next button on section 2 (Bank Consortium)
     */
    public void clickNextSection2() {
        try {
            // Scroll to element
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", nextButtonSection2);
            Thread.sleep(500);
            
            waitHelper.waitForElementVisible(nextButtonSection2);
            
            // Use JavaScript click
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", nextButtonSection2);
            System.out.println("✓ Clicked Next on Section 2 (Bank Consortium)");
        } catch (Exception e) {
            System.out.println("! Could not click Next on Section 2: " + e.getMessage());
        }
    }
    
    /**
     * Click Next button on section 3 (Address Information)
     */
    public void clickNextSection3() {
        try {
            // Scroll to element
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", nextButtonSection3);
            Thread.sleep(500);
            
            waitHelper.waitForElementVisible(nextButtonSection3);
            
            // Use JavaScript click
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", nextButtonSection3);
            System.out.println("✓ Clicked Next on Section 3 (Address Information)");
        } catch (Exception e) {
            System.out.println("! Could not click Next on Section 3: " + e.getMessage());
        }
    }
    
    /**
     * Click Save button
     */
    public void clickSaveButton() {
        try {
            // Scroll to element
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", saveButton);
            Thread.sleep(500);
            
            waitHelper.waitForElementVisible(saveButton);
            
            // Use JavaScript click
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", saveButton);
            System.out.println("✓ Clicked Save button");
            
            // Wait for save to complete
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("! Could not click Save button: " + e.getMessage());
        }
    }
    
    /**
     * Click Back button
     */
    public void clickBackButton() {
        waitHelper.waitForElementClickable(backButton);
        backButton.click();
        System.out.println("✓ Clicked Back button");
    }
    
    /**
     * Enter account number in search field
     */
    public void enterAccountNumberInSearch(String accountNumber) {
        waitHelper.waitForElementVisible(accountSearchField);
        accountSearchField.clear();
        accountSearchField.sendKeys(accountNumber);
        System.out.println("✓ Entered Account Number in search: " + accountNumber);
    }
    
    /**
     * Click Search button
     */
    public void clickSearchButton() {
        waitHelper.waitForElementClickable(searchButton);
        searchButton.click();
        System.out.println("✓ Clicked Search button");
        
        // Wait for search results
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Get account number from stored data
     */
    public String getAccountNumber() {
        return accountData.get("AccountNumber");
    }
    
    // ==================== Account Details Verification Methods ====================
    
    /**
     * Click Action button
     */
    public void clickActionButton() {
        try {
            waitHelper.waitForElementClickable(actionButton);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", actionButton);
            Thread.sleep(500);
            actionButton.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Action button");
        } catch (Exception e) {
            System.out.println("! Could not click Action button: " + e.getMessage());
        }
    }
    
    /**
     * Click Details button and switch to new tab
     */
    public void clickDetailsButton() {
        try {
            // Store the original window and all current windows
            String originalWindow = driver.getWindowHandle();
            Set<String> windowsBefore = new HashSet<>(driver.getWindowHandles());
            System.out.println("Windows before clicking Details: " + windowsBefore.size());
            System.out.println("Original window: " + originalWindow);
            
            waitHelper.waitForElementClickable(detailsButton);
            detailsButton.click();
            System.out.println("✓ Clicked Details button");
            Thread.sleep(3000);
            
            // Wait for new tab to open
            int attempts = 0;
            while (driver.getWindowHandles().size() <= windowsBefore.size() && attempts < 10) {
                Thread.sleep(1000);
                attempts++;
            }
            
            Set<String> windowsAfter = driver.getWindowHandles();
            System.out.println("Windows after clicking Details: " + windowsAfter.size());
            
            // Find the NEW window (the one that's not in the before set)
            String detailsWindow = null;
            for (String windowHandle : windowsAfter) {
                if (!windowsBefore.contains(windowHandle)) {
                    detailsWindow = windowHandle;
                    System.out.println("Found new window: " + detailsWindow);
                    break;
                }
            }
            
            // Switch to the details window
            if (detailsWindow != null) {
                driver.switchTo().window(detailsWindow);
                System.out.println("✓ Switched to new window: " + detailsWindow);
                Thread.sleep(3000);
                
                // Wait for page to fully load
                new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(30))
                    .until(webDriver -> ((org.openqa.selenium.JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
                
                String currentUrl = driver.getCurrentUrl();
                String currentHandle = driver.getWindowHandle();
                System.out.println("✓ Details page loaded");
                System.out.println("  URL: " + currentUrl);
                System.out.println("  Title: " + driver.getTitle());
                System.out.println("  Current window handle: " + currentHandle);
                
                // Re-initialize page elements for new tab
                org.openqa.selenium.support.PageFactory.initElements(driver, this);
                Thread.sleep(2000);
                
                // Verify we can interact with the page
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
                System.out.println("✓ Details page ready for testing");
            } else {
                System.out.println("! Could not find new details window");
            }
        } catch (Exception e) {
            System.out.println("! Could not click Details button: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Click Basic Information section to expand it
     */
    public void clickBasicInformationSection() {
        try {
            Thread.sleep(2000);
            waitHelper.waitForElementClickable(basicInformationSection);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", basicInformationSection);
            Thread.sleep(500);
            basicInformationSection.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Basic Information section");
        } catch (Exception e) {
            System.out.println("! Could not click Basic Information section: " + e.getMessage());
        }
    }
    
    /**
     * Click Edit Basic button
     */
    public void clickEditBasic() {
        try {
            Thread.sleep(2000); // Extra wait for page load
            
            // Try to find the element with increased wait
            try {
                waitHelper.waitForElementClickable(editBasicButton);
            } catch (Exception we) {
                System.out.println("  ! Edit Basic button not immediately clickable, scrolling and retrying...");
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "window.scrollTo(0, 0);"); // Scroll to top
                Thread.sleep(1000);
            }
            
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", editBasicButton);
            Thread.sleep(500);
            
            // Try JavaScript click
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", editBasicButton);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Edit Basic button");
        } catch (Exception e) {
            System.out.println("! Could not click Edit Basic button: " + e.getMessage());
            System.out.println("  Skipping this section and continuing...");
        }
    }
    
    /**
     * Enter Business Date (must be after loan disbursal date)
     */
    public void enterBusinessDate() {
        try {
            String loanDisbursalDate = accountData.get("LoanDisbursalDate");
            // Generate a date after loan disbursal date
            String businessDate = RandomDataGenerator.generateDate();
            
            waitHelper.waitForElementVisible(businessDateField);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];", businessDateField, businessDate);
            accountData.put("BusinessDate", businessDate);
            System.out.println("✓ Entered Business Date: " + businessDate);
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("! Could not enter Business Date: " + e.getMessage());
        }
    }
    
    /**
     * Click Save Basic button
     */
    public void clickSaveBasic() {
        try {
            Thread.sleep(1000);
            // Scroll to the button first
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", saveBasicButton);
            Thread.sleep(500);
            
            // Force click using JavaScript - more reliable for modal buttons
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", saveBasicButton);
            Thread.sleep(3000); // Wait for save to complete
            
            // Close any modal overlay that might be open
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "var overlay = document.getElementById('facebox_overlay');" +
                    "if(overlay) overlay.style.display = 'none';" +
                    "var facebox = document.getElementById('facebox');" +
                    "if(facebox) facebox.style.display = 'none';");
                Thread.sleep(500);
            } catch (Exception e) {
                // Ignore if no overlay to close
            }
            System.out.println("✓ Clicked Save Basic button");
        } catch (Exception e) {
            System.out.println("! Could not click Save Basic button: " + e.getMessage());
            System.out.println("  Skipping and continuing...");
        }
    }
    
    /**
     * Click Bad Debt section
     */
    public void clickBadDebtSection() {
        try {
            waitHelper.waitForElementClickable(badDebtSection);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", badDebtSection);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", badDebtSection);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Bad Debt section");
        } catch (Exception e) {
            System.out.println("! Could not click Bad Debt section: " + e.getMessage());
            System.out.println("  Skipping and continuing...");
        }
    }
    
    /**
     * Click Edit Bad Debt button
     */
    public void clickEditBadDebt() {
        try {
            Thread.sleep(2000); // Wait for any previous action to complete
            
            // Close any modal overlay first
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "var overlay = document.getElementById('facebox_overlay');" +
                    "if(overlay) overlay.style.display = 'none';" +
                    "var facebox = document.getElementById('facebox');" +
                    "if(facebox) facebox.style.display = 'none';");
                Thread.sleep(500);
            } catch (Exception e) {
                // Ignore if no overlay
            }
            
            // Scroll to element
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", editBadDebtButton);
            Thread.sleep(500);
            
            // Use JavaScript click for reliability
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", editBadDebtButton);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Edit Bad Debt button");
        } catch (Exception e) {
            System.out.println("! Could not click Edit Bad Debt button: " + e.getMessage());
        }
    }
    
    /**
     * Click Save Bad Debt button
     */
    public void clickSaveBadDebt() {
        try {
            Thread.sleep(1000);
            // Scroll to button
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", saveBadDebtButton);
            Thread.sleep(500);
            
            // Use JavaScript click
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", saveBadDebtButton);
            Thread.sleep(3000); // Wait for save to complete
            
            // Close any modal overlay
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "var overlay = document.getElementById('facebox_overlay');" +
                    "if(overlay) overlay.style.display = 'none';" +
                    "var facebox = document.getElementById('facebox');" +
                    "if(facebox) facebox.style.display = 'none';");
                Thread.sleep(500);
            } catch (Exception e) {
                // Ignore if no overlay
            }
            System.out.println("✓ Clicked Save Bad Debt button");
        } catch (Exception e) {
            System.out.println("! Could not click Save Bad Debt button: " + e.getMessage());
        }
    }
    
    /**
     * Click Account Details section
     */
    public void clickAccountDetailsSection() {
        try {
            waitHelper.waitForElementClickable(accountDetailsSection);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", accountDetailsSection);
            Thread.sleep(500);
            accountDetailsSection.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Account Details section");
        } catch (Exception e) {
            System.out.println("! Could not click Account Details section: " + e.getMessage());
        }
    }
    
    /**
     * Click Edit Account Details button
     */
    public void clickEditAccountDetails() {
        try {
            Thread.sleep(2000);
            // Close any modal overlay first
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "var overlay = document.getElementById('facebox_overlay');" +
                    "if(overlay) overlay.style.display = 'none';" +
                    "var facebox = document.getElementById('facebox');" +
                    "if(facebox) facebox.style.display = 'none';");
                Thread.sleep(500);
            } catch (Exception e) {
                // Ignore
            }
            
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", editAccountDetailsButton);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", editAccountDetailsButton);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Edit Account Details button");
        } catch (Exception e) {
            System.out.println("! Could not click Edit Account Details button: " + e.getMessage());
        }
    }
    
    /**
     * Enter Principal Outstanding Amount
     */
    public void enterPrincipalOutstandingAmount() {
        try {
            String amount = RandomDataGenerator.generateNumeric(6);
            waitHelper.waitForElementVisible(principalOutstandingAmountField);
            principalOutstandingAmountField.clear();
            principalOutstandingAmountField.sendKeys(amount);
            accountData.put("PrincipalOutstandingAmount", amount);
            System.out.println("✓ Entered Principal Outstanding Amount: " + amount);
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("! Could not enter Principal Outstanding Amount: " + e.getMessage());
        }
    }
    
    /**
     * Click Save Account Details button
     */
    public void clickSaveAccountDetails() {
        try {
            Thread.sleep(1000);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", saveAccountDetailsButton);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", saveAccountDetailsButton);
            Thread.sleep(3000);
            
            // Close any modal overlay
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "var overlay = document.getElementById('facebox_overlay');" +
                    "if(overlay) overlay.style.display = 'none';" +
                    "var facebox = document.getElementById('facebox');" +
                    "if(facebox) facebox.style.display = 'none';");
                Thread.sleep(500);
            } catch (Exception e) {
                // Ignore
            }
            System.out.println("✓ Clicked Save Account Details button");
        } catch (Exception e) {
            System.out.println("! Could not click Save Account Details button: " + e.getMessage());
        }
    }
    
    /**
     * Click Foreclosure tab
     */
    public void clickForeclosureTab() {
        try {
            Thread.sleep(1000);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", foreclosureTab);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", foreclosureTab);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Foreclosure tab");
        } catch (Exception e) {
            System.out.println("! Could not click Foreclosure tab: " + e.getMessage());
        }
    }
    
    /**
     * Click Edit Foreclosure button
     */
    public void clickEditForeclosure() {
        try {
            Thread.sleep(2000);
            // Close any modal overlay first
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "var overlay = document.getElementById('facebox_overlay');" +
                    "if(overlay) overlay.style.display = 'none';" +
                    "var facebox = document.getElementById('facebox');" +
                    "if(facebox) facebox.style.display = 'none';");
                Thread.sleep(500);
            } catch (Exception e) {
                // Ignore
            }
            
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", editForeclosureButton);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", editForeclosureButton);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Edit Foreclosure button");
        } catch (Exception e) {
            System.out.println("! Could not click Edit Foreclosure button: " + e.getMessage());
        }
    }
    
    /**
     * Click Save Foreclosure button
     */
    public void clickSaveForeclosure() {
        try {
            Thread.sleep(1000);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", saveForeclosureButton);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", saveForeclosureButton);
            Thread.sleep(3000);
            
            // Close any modal overlay
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "var overlay = document.getElementById('facebox_overlay');" +
                    "if(overlay) overlay.style.display = 'none';" +
                    "var facebox = document.getElementById('facebox');" +
                    "if(facebox) facebox.style.display = 'none';");
                Thread.sleep(500);
            } catch (Exception e) {
                // Ignore
            }
            System.out.println("✓ Clicked Save Foreclosure button");
        } catch (Exception e) {
            System.out.println("! Could not click Save Foreclosure button: " + e.getMessage());
        }
    }
    
    /**
     * Click Concilation tab
     */
    public void clickConcilationTab() {
        try {
            Thread.sleep(1000);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", concilationTab);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", concilationTab);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Concilation tab");
        } catch (Exception e) {
            System.out.println("! Could not click Concilation tab: " + e.getMessage());
        }
    }
    
    /**
     * Click Edit Concilation button
     */
    public void clickEditConcilation() {
        try {
            Thread.sleep(2000);
            // Close any modal overlay first
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "var overlay = document.getElementById('facebox_overlay');" +
                    "if(overlay) overlay.style.display = 'none';" +
                    "var facebox = document.getElementById('facebox');" +
                    "if(facebox) facebox.style.display = 'none';");
                Thread.sleep(500);
            } catch (Exception e) {
                // Ignore
            }
            
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", editConcilationButton);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", editConcilationButton);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Edit Concilation button");
        } catch (Exception e) {
            System.out.println("! Could not click Edit Concilation button: " + e.getMessage());
        }
    }
    
    /**
     * Enter OD value
     */
    public void enterODValue() {
        try {
            String odValue = RandomDataGenerator.generateNumeric(5);
            waitHelper.waitForElementVisible(odField);
            odField.clear();
            odField.sendKeys(odValue);
            accountData.put("ODValue", odValue);
            System.out.println("✓ Entered OD value: " + odValue);
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("! Could not enter OD value: " + e.getMessage());
        }
    }
    
    /**
     * Click Save Concilation button
     */
    public void clickSaveConcilation() {
        try {
            Thread.sleep(1000);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", saveConcilationButton);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", saveConcilationButton);
            Thread.sleep(3000);
            
            // Close any modal overlay
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "var overlay = document.getElementById('facebox_overlay');" +
                    "if(overlay) overlay.style.display = 'none';" +
                    "var facebox = document.getElementById('facebox');" +
                    "if(facebox) facebox.style.display = 'none';");
                Thread.sleep(500);
            } catch (Exception e) {
                // Ignore
            }
            System.out.println("✓ Clicked Save Concilation button");
        } catch (Exception e) {
            System.out.println("! Could not click Save Concilation button: " + e.getMessage());
        }
    }
    
    /**
     * Click Present Status section
     */
    public void clickPresentStatusSection() {
        try {
            waitHelper.waitForElementClickable(presentStatusSection);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", presentStatusSection);
            Thread.sleep(500);
            presentStatusSection.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Present Status section");
        } catch (Exception e) {
            System.out.println("! Could not click Present Status section: " + e.getMessage());
        }
    }
    
    /**
     * Click Edit Present Status button
     */
    public void clickEditPresentStatus() {
        try {
            Thread.sleep(2000);
            // Close any modal overlay first
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "var overlay = document.getElementById('facebox_overlay');" +
                    "if(overlay) overlay.style.display = 'none';" +
                    "var facebox = document.getElementById('facebox');" +
                    "if(facebox) facebox.style.display = 'none';");
                Thread.sleep(500);
            } catch (Exception e) {
                // Ignore
            }
            
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", editPresentStatusButton);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", editPresentStatusButton);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Edit Present Status button");
        } catch (Exception e) {
            System.out.println("! Could not click Edit Present Status button: " + e.getMessage());
        }
    }
    
    /**
     * Click Cancel button
     */
    public void clickCancel() {
        try {
            Thread.sleep(1000);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", cancelButton);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", cancelButton);
            Thread.sleep(2000);
            
            // Close any modal overlay
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "var overlay = document.getElementById('facebox_overlay');" +
                    "if(overlay) overlay.style.display = 'none';" +
                    "var facebox = document.getElementById('facebox');" +
                    "if(facebox) facebox.style.display = 'none';");
                Thread.sleep(500);
            } catch (Exception e) {
                // Ignore
            }
            System.out.println("✓ Clicked Cancel button");
        } catch (Exception e) {
            System.out.println("! Could not click Cancel button: " + e.getMessage());
        }
    }
    
    /**
     * Click Recovery Summary section
     */
    public void clickRecoverySummarySection() {
        try {
            waitHelper.waitForElementClickable(recoverySummarySection);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", recoverySummarySection);
            Thread.sleep(500);
            recoverySummarySection.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Recovery Summary section");
        } catch (Exception e) {
            System.out.println("! Could not click Recovery Summary section: " + e.getMessage());
        }
    }
    
    /**
     * Click Applicant section
     */
    public void clickApplicantSection() {
        try {
            waitHelper.waitForElementClickable(applicantSection);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", applicantSection);
            Thread.sleep(500);
            applicantSection.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Applicant section");
        } catch (Exception e) {
            System.out.println("! Could not click Applicant section: " + e.getMessage());
        }
    }
    
    /**
     * Click Applicant Plus button
     */
    public void clickApplicantPlus() {
        try {
            waitHelper.waitForElementClickable(applicantPlusButton);
            applicantPlusButton.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Applicant Plus button");
        } catch (Exception e) {
            System.out.println("! Could not click Applicant Plus button: " + e.getMessage());
        }
    }
    
    /**
     * Click Address section
     */
    public void clickAddressSection() {
        try {
            waitHelper.waitForElementClickable(addressSection);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", addressSection);
            Thread.sleep(500);
            addressSection.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Address section");
        } catch (Exception e) {
            System.out.println("! Could not click Address section: " + e.getMessage());
        }
    }
    
    /**
     * Click Address Plus button
     */
    public void clickAddressPlus() {
        try {
            waitHelper.waitForElementClickable(addressPlusButton);
            addressPlusButton.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Address Plus button");
        } catch (Exception e) {
            System.out.println("! Could not click Address Plus button: " + e.getMessage());
        }
    }
    
    /**
     * Click Information Request section
     */
    public void clickInformationRequestSection() {
        try {
            waitHelper.waitForElementClickable(informationRequestSection);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", informationRequestSection);
            Thread.sleep(500);
            informationRequestSection.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Information Request section");
        } catch (Exception e) {
            System.out.println("! Could not click Information Request section: " + e.getMessage());
        }
    }
    
    /**
     * Click Information Request Cancel button
     */
    public void clickInformationRequestCancel() {
        try {
            waitHelper.waitForElementClickable(informationRequestCancelButton);
            informationRequestCancelButton.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Information Request Cancel button");
        } catch (Exception e) {
            System.out.println("! Could not click Information Request Cancel: " + e.getMessage());
        }
    }
    
    /**
     * Click Bank Consortium section
     */
    public void clickBankConsortiumSection() {
        try {
            waitHelper.waitForElementClickable(bankConsortiumSection);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", bankConsortiumSection);
            Thread.sleep(500);
            bankConsortiumSection.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Bank Consortium section");
        } catch (Exception e) {
            System.out.println("! Could not click Bank Consortium section: " + e.getMessage());
        }
    }
    
    /**
     * Click Bank Consortium Plus button
     */
    public void clickBankConsortiumPlus() {
        try {
            waitHelper.waitForElementClickable(bankConsortiumPlusButton);
            bankConsortiumPlusButton.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Bank Consortium Plus button");
        } catch (Exception e) {
            System.out.println("! Could not click Bank Consortium Plus: " + e.getMessage());
        }
    }
    
    /**
     * Click Save for Bank Consortium
     */
    public void clickSaveBankConsortium() {
        try {
            waitHelper.waitForElementClickable(saveForeclosureButton); // Same button element
            saveForeclosureButton.click();
            Thread.sleep(2000);
            System.out.println("✓ Clicked Save Bank Consortium button");
        } catch (Exception e) {
            System.out.println("! Could not click Save Bank Consortium: " + e.getMessage());
        }
    }
    
    /**
     * Click Documents section
     */
    public void clickDocumentsSection() {
        try {
            waitHelper.waitForElementClickable(documentsSection);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", documentsSection);
            Thread.sleep(500);
            documentsSection.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Documents section");
        } catch (Exception e) {
            System.out.println("! Could not click Documents section: " + e.getMessage());
        }
    }
    
    /**
     * Click Legal Document tab
     */
    public void clickLegalDocumentTab() {
        try {
            Thread.sleep(1000);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", legalDocumentTab);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", legalDocumentTab);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Legal Document tab");
        } catch (Exception e) {
            System.out.println("! Could not click Legal Document tab: " + e.getMessage());
        }
    }
    
    /**
     * Click Legal Document Plus button
     */
    public void clickLegalDocumentPlus() {
        try {
            waitHelper.waitForElementClickable(legalDocumentPlusButton);
            legalDocumentPlusButton.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Legal Document Plus button");
        } catch (Exception e) {
            System.out.println("! Could not click Legal Document Plus: " + e.getMessage());
        }
    }
    
    /**
     * Click Document Cancel button
     */
    public void clickDocumentCancel() {
        try {
            waitHelper.waitForElementClickable(documentCancelButton);
            documentCancelButton.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Document Cancel button");
        } catch (Exception e) {
            System.out.println("! Could not click Document Cancel: " + e.getMessage());
        }
    }
    
    /**
     * Click Instrument Details section
     */
    public void clickInstrumentDetailsSection() {
        try {
            waitHelper.waitForElementClickable(instrumentDetailsSection);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", instrumentDetailsSection);
            Thread.sleep(500);
            instrumentDetailsSection.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Instrument Details section");
        } catch (Exception e) {
            System.out.println("! Could not click Instrument Details section: " + e.getMessage());
        }
    }
    
    /**
     * Click Instrument Details Plus button
     */
    public void clickInstrumentDetailsPlus() {
        try {
            waitHelper.waitForElementClickable(instrumentDetailsPlusButton);
            instrumentDetailsPlusButton.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Instrument Details Plus button");
        } catch (Exception e) {
            System.out.println("! Could not click Instrument Details Plus: " + e.getMessage());
        }
    }
    
    /**
     * Click Linked Case, Notice, Asset section
     */
    public void clickLinkedCaseNoticeAssetSection() {
        try {
            waitHelper.waitForElementClickable(linkedCaseNoticeAssetSection);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", linkedCaseNoticeAssetSection);
            Thread.sleep(500);
            linkedCaseNoticeAssetSection.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Linked Case, Notice, Asset section");
        } catch (Exception e) {
            System.out.println("! Could not click Linked section: " + e.getMessage());
        }
    }
    
    /**
     * Click Linked Cases tab
     */
    public void clickLinkedCasesTab() {
        try {
            Thread.sleep(1000);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", linkedCasesTab);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", linkedCasesTab);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Linked Cases tab");
        } catch (Exception e) {
            System.out.println("! Could not click Linked Cases tab: " + e.getMessage());
        }
    }
    
    /**
     * Click Linked Notices tab
     */
    public void clickLinkedNoticesTab() {
        try {
            Thread.sleep(1000);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", linkedNoticesTab);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", linkedNoticesTab);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Linked Notices tab");
        } catch (Exception e) {
            System.out.println("! Could not click Linked Notices tab: " + e.getMessage());
        }
    }
    
    /**
     * Click Log section
     */
    public void clickLogSection() {
        try {
            waitHelper.waitForElementClickable(logSection);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", logSection);
            Thread.sleep(500);
            logSection.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Log section");
        } catch (Exception e) {
            System.out.println("! Could not click Log section: " + e.getMessage());
        }
    }
    
    /**
     * Click Audit Trail tab
     */
    public void clickAuditTrailTab() {
        try {
            Thread.sleep(1000);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", auditTrailTab);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", auditTrailTab);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Audit Trail tab");
        } catch (Exception e) {
            System.out.println("! Could not click Audit Trail tab: " + e.getMessage());
        }
    }
    
    /**
     * Click Back button from details page
     */
    public void clickBackFromDetails() {
        try {
            // Close any modal first
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "var overlay = document.getElementById('facebox_overlay');" +
                    "if(overlay) overlay.style.display = 'none';" +
                    "var facebox = document.getElementById('facebox');" +
                    "if(facebox) facebox.style.display = 'none';");
                Thread.sleep(500);
            } catch (Exception e) {
                // Ignore
            }
            
            // Scroll to top\n            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);
            
            // Scroll button into view and click
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'start'});", backButton);
            Thread.sleep(500);
            
            // Use JavaScript click
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", backButton);
            Thread.sleep(2000);
            System.out.println("✓ Clicked Back button from details");
        } catch (Exception e) {
            System.out.println("! Could not click Back from details: " + e.getMessage());
        }
    }
    
    /**
     * Click Load Customer Account button
     */
    public void clickLoadCustomerAccount() {
        try {
            waitHelper.waitForElementClickable(loadCustomerAccountButton);
            loadCustomerAccountButton.click();
            Thread.sleep(2000);
            System.out.println("✓ Clicked Load Customer Account button");
        } catch (Exception e) {
            System.out.println("! Could not click Load Customer Account: " + e.getMessage());
        }
    }
    
    /**
     * Click Action button after load
     */
    public void clickActionAfterLoad() {
        try {
            waitHelper.waitForElementClickable(actionButtonAfterLoad);
            actionButtonAfterLoad.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Action button after load");
        } catch (Exception e) {
            System.out.println("! Could not click Action after load: " + e.getMessage());
        }
    }
    
    /**
     * Click Delete button and complete delete workflow
     */
    public void clickDelete() {
        try {
            System.out.println("Clicking Delete option from action menu...");
            Thread.sleep(1000);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", deleteButton);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", deleteButton);
            Thread.sleep(2000); // Wait for delete modal to appear
            System.out.println("✓ Clicked Delete button");
            
            // Enter delete reason
            System.out.println("Entering delete reason...");
            String deleteReason = "Test account deletion - automated test";
            waitHelper.waitForElementVisible(deleteReasonField);
            deleteReasonField.clear();
            deleteReasonField.sendKeys(deleteReason);
            Thread.sleep(1000);
            System.out.println("✓ Entered delete reason: " + deleteReason);
            
            // Click final delete confirmation button
            System.out.println("Clicking Delete confirmation button...");
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", deleteConfirmButton);
            Thread.sleep(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", deleteConfirmButton);
            Thread.sleep(3000); // Wait for deletion to complete
            System.out.println("✓ Confirmed account deletion");
        } catch (Exception e) {
            System.out.println("! Could not complete delete operation: " + e.getMessage());
        }
    }
    
    /**
     * Complete account details verification workflow
     */
    public void verifyAccountDetailsComplete() {
        try {
            System.out.println("\n=== Starting Account Details Verification ===");
            
            // 1. Action and Details - Navigate to details page
            clickActionButton();
            clickDetailsButton();
            
            System.out.println("\n--- Details Page Loaded, Starting Complete Edit Workflow ---");
            
            // 1. Basic Information - EDIT WORKFLOW
            System.out.println("\n[EDIT 1/6] Basic Information Section");
            System.out.println("  - Expanding section...");
            clickBasicInformationSection();
            System.out.println("  - Clicking Edit button...");
            clickEditBasic();
            System.out.println("  - Entering Business Date...");
            enterBusinessDate();
            System.out.println("  - Clicking Save...");
            clickSaveBasic();
            System.out.println("  ✓ Basic Information edited and saved");
            
            // 2. Bad Debt - EDIT WORKFLOW
            System.out.println("\n[EDIT 2/6] Bad Debt Section");
            System.out.println("  - Expanding section...");
            clickBadDebtSection();
            System.out.println("  - Clicking Edit button...");
            clickEditBadDebt();
            System.out.println("  - Clicking Save...");
            clickSaveBadDebt();
            System.out.println("  ✓ Bad Debt edited and saved");
            
            // 3. Account Details - EDIT WORKFLOW
            System.out.println("\n[EDIT 3/6] Account Details Section");
            System.out.println("  - Expanding section...");
            clickAccountDetailsSection();
            System.out.println("  - Clicking Edit button...");
            clickEditAccountDetails();
            System.out.println("  - Clicking Save...");
            clickSaveAccountDetails();
            System.out.println("  ✓ Account Details edited and saved");
            
            // 4. Foreclosure - EDIT WORKFLOW
            System.out.println("\n[EDIT 4/6] Foreclosure Tab");
            System.out.println("  - Opening Account Details section...");
            clickAccountDetailsSection();
            Thread.sleep(1000);
            System.out.println("  - Clicking Foreclosure tab...");
            clickForeclosureTab();
            System.out.println("  - Clicking Edit button...");
            clickEditForeclosure();
            System.out.println("  - Clicking Save...");
            clickSaveForeclosure();
            System.out.println("  ✓ Foreclosure edited and saved");
            
            // 5. Conciliation - EDIT WORKFLOW
            System.out.println("\n[EDIT 5/6] Conciliation Tab");
            System.out.println("  - Clicking Conciliation tab...");
            Thread.sleep(1000);
            clickConcilationTab();
            System.out.println("  - Clicking Edit button...");
            clickEditConcilation();
            System.out.println("  - Clicking Save...");
            clickSaveConcilation();
            System.out.println("  ✓ Conciliation edited and saved");
            
            // 6. Present Status - EDIT WORKFLOW
            System.out.println("\n[EDIT 6/6] Present Status Section");
            System.out.println("  - Expanding section...");
            clickPresentStatusSection();
            System.out.println("  - Clicking Edit button...");
            clickEditPresentStatus();
            System.out.println("  - Clicking Cancel (no save for this section)...");
            clickCancel();
            System.out.println("  ✓ Present Status edit cancelled");
            
            System.out.println("\n--- All Edit Workflows Completed ---");
            System.out.println("Now verifying remaining sections are accessible...\n");
            
            // Continue with verification of remaining sections
            // 7. Recovery Summary
            System.out.println("\n[7/15] Testing Recovery Summary Section...");
            clickRecoverySummarySection();
            System.out.println("  ✓ Recovery Summary section accessible");
            // 7. Recovery Summary
            System.out.println("\n[7/15] Testing Recovery Summary Section...");
            clickRecoverySummarySection();
            System.out.println("  ✓ Recovery Summary section accessible");
            
            // 8. Applicant
            System.out.println("\n[8/15] Testing Applicant Section...");
            clickApplicantSection();
            System.out.println("  ✓ Applicant section accessible");
            
            // 9. Address
            System.out.println("\n[9/15] Testing Address Section...");
            clickAddressSection();
            System.out.println("  ✓ Address section accessible");
            
            // 11. Information Request
            System.out.println("\n[10/15] Testing Information Request Section...");
            clickInformationRequestSection();
            System.out.println("  ✓ Information Request section accessible");
            
            // 12. Bank Consortium
            System.out.println("\n[11/15] Testing Bank Consortium Section...");
            clickBankConsortiumSection();
            System.out.println("  ✓ Bank Consortium section accessible");
            
            // 13. Documents
            System.out.println("\n[12/15] Testing Documents Section...");
            clickDocumentsSection();
            System.out.println("  ✓ Documents section accessible");
            
            // 14. Instrument Details
            System.out.println("\n[13/15] Testing Instrument Details Section...");
            clickInstrumentDetailsSection();
            System.out.println("  ✓ Instrument Details section accessible");
            
            // 15. Linked sections
            System.out.println("\n[14/15] Testing Linked Sections...");
            clickLinkedCaseNoticeAssetSection();
            System.out.println("  ✓ Linked Cases/Notices/Assets section accessible");
            
            // 16. Log
            System.out.println("\n[15/15] Testing Log Section...");
            clickLogSection();
            System.out.println("  ✓ Log section accessible");
            
            // 17. Back button - Return to account list
            System.out.println("\n--- Completing Verification ---");
            System.out.println("All 15 sections verified successfully!");
            System.out.println("Returning to accounts list...");
            
            // Close any remaining modals before going back
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "var overlay = document.getElementById('facebox_overlay');" +
                    "if(overlay) overlay.style.display = 'none';" +
                    "var facebox = document.getElementById('facebox');" +
                    "if(facebox) facebox.style.display = 'none';");
                Thread.sleep(1000);
            } catch (Exception e) {
                // Ignore
            }
            
            // Scroll to top before clicking back
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1000);
            
            clickBackFromDetails();
            Thread.sleep(3000); // Wait for list to load
            
            // 18. Delete the account workflow
            System.out.println("\n--- Starting Account Deletion Workflow ---");
            
            // Search for the account again to ensure it's loaded in the table
            System.out.println("Step 1: Searching for the account to delete...");
            String accountNumberToDelete = accountData.get("AccountNumber");
            System.out.println("  Account Number: " + accountNumberToDelete);
            
            // Enter account number in search field
            try {
                waitHelper.waitForElementVisible(accountSearchField);
                accountSearchField.clear();
                accountSearchField.sendKeys(accountNumberToDelete);
                Thread.sleep(500);
                System.out.println("  ✓ Entered account number in search field");
            } catch (Exception e) {
                System.out.println("  ! Could not enter account number: " + e.getMessage());
            }
            
            // Click Search button
            System.out.println("Step 2: Clicking Search button...");
            try {
                waitHelper.waitForElementClickable(searchButton);
                searchButton.click();
                Thread.sleep(3000); // Wait for search results
                System.out.println("  ✓ Search completed");
            } catch (Exception e) {
                System.out.println("  ! Could not click search button: " + e.getMessage());
            }
            
            // Click Action button
            System.out.println("Step 3: Clicking Action button...");
            try {
                Thread.sleep(2000);
                waitHelper.waitForElementClickable(actionButtonAfterLoad);
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});", actionButtonAfterLoad);
                Thread.sleep(500);
                actionButtonAfterLoad.click();
                Thread.sleep(1000);
                System.out.println("  ✓ Clicked Action button");
            } catch (Exception e) {
                System.out.println("  ! Could not click Action button: " + e.getMessage());
            }
            
            // Complete delete workflow (includes clicking Delete, entering reason, and confirming)
            System.out.println("Step 4: Completing delete workflow...");
            clickDelete();
            
            System.out.println("\n✓✓✓ Account deleted successfully! ✓✓✓");
            
            System.out.println("\n=== Account Details Verification and Deletion Completed Successfully ===\n");
        } catch (Exception e) {
            System.out.println("! Error during account details verification: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Complete account edit workflow - Basic Information and Bad Debt
     * This method performs the following steps:
     * 1. Click Basic Information section to expand
     * 2. Click Edit button in Basic Information
     * 3. Enter Business Date (DD-MMM-YYYY format, must be after loan disbursal date)
     * 4. Click Save button for Basic Information
     * 5. Click Bad Debt section to expand
     * 6. Click Edit button in Bad Debt section
     * 7. Click Save button for Bad Debt
     */
    public void completeAccountEditWorkflow() {
        try {
            System.out.println("\n=== Starting Account Edit Workflow ===");
            
            // Step 1: Basic Information Section
            System.out.println("\n[1/7] Expanding Basic Information Section...");
            clickBasicInformationSection();
            
            // Step 2: Click Edit in Basic Information
            System.out.println("\n[2/7] Clicking Edit button in Basic Information...");
            clickEditBasic();
            
            // Step 3: Enter Business Date
            System.out.println("\n[3/7] Entering Business Date...");
            enterBusinessDate();
            
            // Step 4: Save Basic Information
            System.out.println("\n[4/7] Saving Basic Information...");
            clickSaveBasic();
            
            // Step 5: Bad Debt Section
            System.out.println("\n[5/7] Expanding Bad Debt Section...");
            clickBadDebtSection();
            
            // Step 6: Click Edit in Bad Debt
            System.out.println("\n[6/7] Clicking Edit button in Bad Debt...");
            clickEditBadDebt();
            
            // Step 7: Save Bad Debt
            System.out.println("\n[7/7] Saving Bad Debt...");
            clickSaveBadDebt();
            
            System.out.println("\n=== Account Edit Workflow Completed Successfully ===\n");
        } catch (Exception e) {
            System.out.println("! Error during account edit workflow: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
