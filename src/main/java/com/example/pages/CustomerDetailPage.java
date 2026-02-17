package com.example.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.example.utils.RandomDataGenerator;
import com.example.utils.WaitHelper;

/**
 * Page Object Model for Customer Detail Page
 * Handles comprehensive customer detail operations including:
 * - Basic Details
 * - NPA Details
 * - Trust/Service Provider Details
 * - Ownership Details
 * - Remarks
 * - Recovery Summary/Expense
 * - Comments
 * - Customer Other Details
 * - OTS/One Time Settlement
 * - Information Request
 * - Address Details
 * - Audit Trail
 */
public class CustomerDetailPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    
    // Navigation Locators
    private final By borrowersMenu = By.xpath("/html/body/div[3]/div/div/div/ul/li[2]/a");
    private final By customerSubmenu = By.xpath("/html/body/div[3]/div/div/div/ul/li[2]/ul/li[3]/a");
    private final By loadCustomersButton = By.xpath("//*[@id='LoadCustomers']");
    private final By actionButton = By.xpath("//*[@id='customerListingContainer']/div[1]/table/tbody/tr[1]/td[7]/div/a/i");
    private final By detailsButton = By.xpath("//*[@id='customerListingContainer']/div[1]/table/tbody/tr[1]/td[7]/div/ul/li[2]/a");
    
    // Basic Details Section
    private final By editBasicDetails = By.xpath("//*[@id='flush-collapseOne']/div/div/div[1]/div[2]/a");
    private final By customerNumberField = By.xpath("//*[@id='CustomerNo']");
    private final By customerNameField = By.xpath("//*[@id='Name']");
    private final By fatherNameField = By.xpath("//*[@id='FatherName']");
    private final By customerTypeDropdown = By.xpath("//*[@id='customerTypeDropdown']");
    private final By industryDropdown = By.xpath("//*[@id='customerCreateIndustry']");
    private final By segmentDropdown = By.xpath("//*[@id='customerCreateSegment']");
    private final By phoneNumberField = By.xpath("//*[@id='PhoneNo']");
    private final By emailField = By.xpath("//*[@id='EmailId']");
    private final By adhaarNumberField = By.xpath("//*[@id='AdhaarNo']");
    private final By panField = By.xpath("//*[@id='PAN']");
    private final By addressTypeDropdown = By.xpath("//*[@id='CreateCustomerAddressType']");
    private final By address1Field = By.xpath("//*[@id='EditBasicDetailss']/form/div[6]");
    private final By saveBasicDetails = By.xpath("//*[@id='createbasicdetailscust']");
    
    // NPA Details Section
    private final By npaSection = By.xpath("//*[@id='accordionFlushExample']/div[2]/div[1]/div");
    private final By editNpaDetails = By.xpath("//*[@id='flush-collapse12']/div/div/div[1]/div[2]/a");
    private final By npaYesRadio = By.xpath("//*[@id='DeclaredNPACreateYes']");
    private final By npaTypeDropdown = By.xpath("//*[@id='NPATypeId']");
    private final By dateOfDeclaringNpa = By.xpath("//*[@id='CreateDateOfDeclarinfNPA']");
    private final By pendencyReasonDropdown = By.xpath("//*[@id='PendencyReasonId']");
    private final By pendencyRemarkField = By.xpath("//*[@id='PendencyRemark']");
    private final By saveNpaDetails = By.xpath("//*[@id='EditNPADetails']");
    
    // Trust/Service Provider Section
    private final By trustSection = By.xpath("//*[@id='accordionFlushExample']/div[3]/div[1]/div");
    private final By addTrustButton = By.xpath("//*[@id='flush-collapseThree']/div/div/div/div[1]/div[2]/a/i");
    private final By trustIdDropdown = By.xpath("//*[@id='trustnamecustcreate']");
    private final By dateOfAcquisition = By.xpath("//*[@id='DateOfAcquisition']");
    private final By sellingBankField = By.xpath("//*[@id='SellingBank']");
    private final By saveTrust = By.xpath("//*[@id='addBtntrustcustcreate']");
    
    // Ownership Details Section - Secondary Dealing Officer
    private final By ownershipSection = By.xpath("//*[@id='accordionFlushExample']/div[4]/div[1]/div");
    private final By secondaryDealingOfficerTab = By.xpath("//*[@id='CustSDO-tab']");
    private final By addSecondaryOfficerButton = By.xpath("//*[@id='CustSDO']/div/div[1]/span/a");
    private final By officerCheckbox = By.xpath("//*[@id='edit_4']");
    private final By officerCheckboxAlternative = By.xpath("//input[@id='edit_4' and @type='checkbox']");
    private final By officerCheckboxCss = By.cssSelector("input#edit_4[type='checkbox']");
    private final By officerCheckboxAny = By.xpath("//input[@type='checkbox'][contains(@id, 'edit')]");
    private final By saveSecondaryOfficer = By.xpath("//*[@id='secondary-dealing-user']/div/form[2]/div/div[2]/div/div/input");
    
    // Remarks Section
    private final By remarkSection = By.xpath("//*[@id='accordionFlushExample']/div[5]/div[1]/div");
    private final By editRemark = By.xpath("//*[@id='flush-Remark']/div/div/div[1]/div[2]/a");
    private final By remarkField = By.xpath("//*[@id='Remarks']");
    private final By saveRemark = By.xpath("//*[@id='edit-remark']/form/div/div[2]/div/div/input");
    
    // Recovery Summary / Expense Section
    private final By recoverySummarySection = By.xpath("//*[@id='accordionFlushExample']/div[6]/div[1]/div");
    private final By recoverySummaryTab = By.xpath("//*[@id='flush-collapse10']/ul/li[2]/a");
    private final By customerExpensesTab = By.xpath("//*[@id='flush-collapse10']/ul/li[3]/a");
    
    // Comment Section
    private final By commentSection = By.xpath("//*[@id='accordionFlushExample']/div[7]/div[1]/div");
    private final By commentField = By.xpath("//*[@id='name']");
    private final By addCommentButton = By.xpath("//*[@id='commentForm']/div/button");
    
    // Customer Other Details (Account) Section
    private final By customerOtherDetailsSection = By.xpath("//*[@id='accordionFlushExample']/div[8]/div[1]/div");
    private final By addAccountButton = By.xpath("//*[@id='lAccount']/div/div[1]/span/a");
    private final By loanAccountNumberField = By.xpath("//*[@id='LoanAccountNumberOnCreate']");
    private final By accountTypeDropdown = By.xpath("//*[@id='AccountTypeOnCreate']");
    private final By principalAmountField = By.xpath("//*[@id='PrincipalAmountCreateId']");
    private final By rateOfInterestField = By.xpath("//*[@id='RateofInterestId']");
    private final By outstandingBalanceField = By.xpath("//*[@id='OutStandingBalanceCreate']");
    private final By loanDisbursalDateField = By.xpath("//*[@id='LoanDisbursalDateCreateId']");
    private final By accountStatusDropdown = By.xpath("//*[@id='CustomerAccountCreateAccountStatus']");
    private final By basicInformationNext = By.xpath("//*[@id='BasicInformationCollapse']");
    private final By bankConsortiumNext = By.xpath("//*[@id='BankConsortiumCollapse']");
    private final By submitAccount = By.xpath("//*[@id='btnSubmit']");
    
    // OTS / One Time Settlement Section
    private final By otsSection = By.xpath("//*[@id='accordionFlushExample']/div[9]/div[1]/div");
    private final By addOtsButton = By.xpath("//*[@id='otsTableContainer']/div/div[1]/div[2]/span/a");
    private final By settlementStatusDropdown = By.xpath("//*[@id='ddlSettlementStatus']");
    private final By settlementAmountField = By.xpath("//*[@id='SettlementAmount']");
    private final By saveOts = By.xpath("//*[@id='edit-notice']/form/div/div[6]/div/div/input");
    
    // Information Request Section
    private final By informationRequestSection = By.xpath("//*[@id='accordionFlushExample']/div[10]/div[1]/div");
    private final By addInformationRequestButton = By.xpath("//*[@id='flush-collapseNine']/div/div/div[1]/div[2]/div/a");
    private final By toUserSelect2Container = By.xpath("//*[@id='create-informationrequest']/div/div[2]/div[3]/div/span[1]/span[1]/span");
    private final By toUserInput = By.xpath("//*[@id='create-informationrequest']/div/div[2]/div[3]/div/span[1]/span[1]/span/ul/li/input");
    private final By toUserDropdownResults = By.cssSelector(".select2-results__options");
    private final By contentField = By.xpath("//*[@id='create-informationrequest']/div/div[2]/div[6]/div/div/div[3]/div[2]");
    private final By saveInformationRequest = By.xpath("//*[@id='create-informationrequest']/div/div[3]/input[2]");
    
    // Address Details Section
    private final By addressDetailsSection = By.xpath("//*[@id='accordionFlushExample']/div[11]/div[1]/div");
    private final By addAddressButton = By.xpath("//*[@id='address-details-container']/div/div/div/div[1]/div[2]/a/i");
    private final By addressTypeDropdownInSection = By.xpath("//*[@id='CreateCustomerAddressType']");
    private final By addressField = By.xpath("//*[@id='AddressCust']");
    private final By stateDropdown = By.xpath("//*[@id='StateIds']");
    private final By locationDropdown = By.xpath("//*[@id='LocationIds']");
    private final By saveAddress = By.xpath("//*[@id='savebuttonid']");
    
    // Audit Trail Section
    private final By auditTrailSection = By.xpath("//*[@id='accordionFlushExample']/div[12]/div[1]/div");
    
    // Constructor
    @SuppressWarnings("this-escape")
    public CustomerDetailPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Helper method to select dropdown by visible text
     */
    private void selectDropdownByVisibleText(By locator, String text) {
        waitHelper.selectDropdownByTextWithWait(locator, text);
    }
    
    /**
     * Helper method to select random dropdown option
     */
    private void selectRandomDropdownOption(By locator) {
        try {
            WebElement dropdown = driver.findElement(locator);
            waitHelper.waitForElementVisible(dropdown);
            Select select = new Select(dropdown);
            java.util.List<WebElement> options = select.getOptions();
            // Skip first option if it's a placeholder (like "Select")
            int startIndex = options.size() > 1 && options.get(0).getText().trim().equalsIgnoreCase("Select") ? 1 : 0;
            if (options.size() > startIndex) {
                Random random = new Random();
                int randomIndex = startIndex + random.nextInt(options.size() - startIndex);
                select.selectByIndex(randomIndex);
                System.out.println("Selected random option: " + options.get(randomIndex).getText());
            }
        } catch (Exception e) {
            System.err.println("Failed to select random dropdown option: " + e.getMessage());
        }
    }
    
    // ==========================
    // Navigation Methods
    // ==========================
    
    public void clickBorrowersMenu() {
        System.out.println("CustomerDetailPage: Clicking Borrowers menu");
        try {
            Thread.sleep(2000);
            waitHelper.clickWithWait(borrowersMenu);
            System.out.println("CustomerDetailPage: Borrowers menu clicked successfully");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("CustomerDetailPage: Thread interrupted - " + e.getMessage());
            throw new RuntimeException("Failed to click Borrowers menu: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("CustomerDetailPage: Failed to click Borrowers menu - " + e.getMessage());
            throw new RuntimeException("Failed to click Borrowers menu: " + e.getMessage());
        }
    }
    
    public void clickCustomerSubmenu() {
        System.out.println("CustomerDetailPage: Clicking Customer submenu");
        try {
            waitHelper.clickWithWait(customerSubmenu);
            System.out.println("CustomerDetailPage: Customer submenu clicked successfully");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("CustomerDetailPage: Thread interrupted - " + e.getMessage());
            throw new RuntimeException("Failed to click Customer submenu: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("CustomerDetailPage: Failed to click Customer submenu - " + e.getMessage());
            throw new RuntimeException("Failed to click Customer submenu: " + e.getMessage());
        }
    }
    
    public void clickLoadCustomers() {
        System.out.println("CustomerDetailPage: Clicking Load Customers button");
        waitHelper.clickWithWait(loadCustomersButton);
        waitHelper.hardWait(2000); // Wait for customers to load
    }
    
    public void clickActionButton() {
        System.out.println("CustomerDetailPage: Clicking Action button");
        int retries = 3;
        for (int i = 0; i < retries; i++) {
            try {
                waitHelper.clickWithWait(actionButton);
                waitHelper.hardWait(1000);
                return; // Success, exit method
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                System.out.println("  - Stale element, retrying... (attempt " + (i + 1) + "/" + retries + ")");
                waitHelper.hardWait(500);
                if (i == retries - 1) {
                    throw e; // Rethrow if all retries failed
                }
            }
        }
    }
    
    public void clickDetailsButton() {
        System.out.println("CustomerDetailPage: Clicking Details button");
        int retries = 3;
        for (int i = 0; i < retries; i++) {
            try {
                waitHelper.clickWithWait(detailsButton);
                waitHelper.hardWait(2000); // Wait for details page to load
                return; // Success, exit method
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                System.out.println("  - Stale element, retrying... (attempt " + (i + 1) + "/" + retries + ")");
                waitHelper.hardWait(500);
                if (i == retries - 1) {
                    throw e; // Rethrow if all retries failed
                }
            }
        }
    }
    
    public void navigateToCustomerDetail() {
        clickBorrowersMenu();
        clickCustomerSubmenu();
        clickLoadCustomers();
        clickActionButton();
        clickDetailsButton();
    }
    
    /**
     * Re-navigate back to customer detail page after save redirects to listing
     * Use this after any save operation that redirects to customer listing page
     */
    private void reNavigateToCustomerDetail() {
        System.out.println("CustomerDetailPage: Checking if re-navigation is needed after save");
        
        // Wait longer for any save/redirect operation to complete
        waitHelper.hardWait(4000);
        
        // Check current URL to determine if we're on customer listing page
        String currentUrl = driver.getCurrentUrl();
        System.out.println("CustomerDetailPage: Current URL: " + currentUrl);
        
        // Check if we're still on the detail page by looking for edit button
        try {
            WebElement editButton = driver.findElement(editBasicDetails);
            boolean onDetailPage = editButton.isDisplayed();
            if (onDetailPage) {
                System.out.println("CustomerDetailPage: Still on detail page, no re-navigation needed");
                // Just scroll to top to ensure sections are accessible
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
                waitHelper.hardWait(1000);
                return;
            }
        } catch (Exception e) {
            System.out.println("CustomerDetailPage: Not on detail page, re-navigation required - " + e.getMessage());
        }
        
        // If we reach here, we're not on the detail page and need to navigate back
        System.out.println("CustomerDetailPage: Re-navigating back to customer detail page");
        waitHelper.hardWait(2000);
        
        try {
            // Step 1: Click Borrowers menu
            System.out.println("CustomerDetailPage: Step 1 - Clicking Borrowers menu");
            WebElement borrowersElement = waitHelper.waitForElementClickable(borrowersMenu);
            borrowersElement.click();
            waitHelper.hardWait(2000);
            System.out.println("CustomerDetailPage: Borrowers menu clicked");
            
            // Step 2: Click Customer submenu
            System.out.println("CustomerDetailPage: Step 2 - Clicking Customer submenu");
            WebElement customerElement = waitHelper.waitForElementClickable(customerSubmenu);
            customerElement.click();
            waitHelper.hardWait(2000);
            System.out.println("CustomerDetailPage: Customer submenu clicked");
            
            // Step 3: Click Load Customers button
            System.out.println("CustomerDetailPage: Step 3 - Clicking Load Customers");
            WebElement loadElement = waitHelper.waitForElementClickable(loadCustomersButton);
            loadElement.click();
            waitHelper.hardWait(5000); // Wait longer for customer list to load
            System.out.println("CustomerDetailPage: Load Customers clicked");
            
            // Step 4: Click Action button on first customer
            System.out.println("CustomerDetailPage: Step 4 - Clicking Action button");
            WebElement actionElement = waitHelper.waitForElementClickable(actionButton);
            actionElement.click();
            waitHelper.hardWait(2000);
            System.out.println("CustomerDetailPage: Action button clicked");
            
            // Step 5: Click Details button
            System.out.println("CustomerDetailPage: Step 5 - Clicking Details button");
            WebElement detailsElement = waitHelper.waitForElementClickable(detailsButton);
            detailsElement.click();
            waitHelper.hardWait(5000); // Wait longer for detail page to load
            System.out.println("CustomerDetailPage: Details button clicked");
            
            // Step 6: Verify we're back on detail page
            System.out.println("CustomerDetailPage: Step 6 - Verifying we're on detail page");
            WebElement verifyEdit = waitHelper.waitForElementVisible(editBasicDetails);
            if (verifyEdit.isDisplayed()) {
                System.out.println("CustomerDetailPage: ✓ Successfully verified detail page is loaded");
            }
            
            // Step 7: Scroll to top to ensure sections are accessible
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
            waitHelper.hardWait(1500);
            
            System.out.println("CustomerDetailPage: ✓ Re-navigation completed successfully");
            
        } catch (Exception e) {
            System.err.println("CustomerDetailPage: ✗ ERROR during re-navigation:");
            System.err.println("  Error message: " + e.getMessage());
            System.err.println("  Current URL: " + driver.getCurrentUrl());
            System.err.println("  Page title: " + driver.getTitle());
            e.printStackTrace();
            throw new RuntimeException("Failed to re-navigate to customer detail page", e);
        }
    }
    
    /**
     * Scroll to element to ensure it's visible before interaction
     */
    private void scrollToElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            waitHelper.hardWait(500);
        } catch (Exception e) {
            System.out.println("CustomerDetailPage: Scroll to element error - " + e.getMessage());
        }
    }
    
    // ==========================
    // Basic Details Methods
    // ==========================
    
    public void clickEditBasicDetails() {
        System.out.println("CustomerDetailPage: Clicking Edit Basic Details");
        waitHelper.clickWithWait(editBasicDetails);
        waitHelper.hardWait(1000);
    }
    
    public void enterCustomerNumber(String customerNumber) {
        System.out.println("CustomerDetailPage: Entering Customer Number: " + customerNumber);
        WebElement element = driver.findElement(customerNumberField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(customerNumber);
    }
    
    public void enterCustomerName(String customerName) {
        System.out.println("CustomerDetailPage: Entering Customer Name: " + customerName);
        WebElement element = driver.findElement(customerNameField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(customerName);
    }
    
    public void enterFatherName(String fatherName) {
        System.out.println("CustomerDetailPage: Entering Father Name: " + fatherName);
        WebElement element = driver.findElement(fatherNameField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(fatherName);
    }
    
    public void selectCustomerType(String customerType) {
        System.out.println("CustomerDetailPage: Selecting Customer Type: " + customerType);
        selectDropdownByVisibleText(customerTypeDropdown, customerType);
    }
    
    public void selectRandomCustomerType() {
        System.out.println("CustomerDetailPage: Selecting Random Customer Type");
        selectRandomDropdownOption(customerTypeDropdown);
    }
    
    public void selectIndustry(String industry) {
        System.out.println("CustomerDetailPage: Selecting Industry: " + industry);
        selectDropdownByVisibleText(industryDropdown, industry);
    }
    
    public void selectRandomIndustry() {
        System.out.println("CustomerDetailPage: Selecting Random Industry");
        selectRandomDropdownOption(industryDropdown);
    }
    
    public void selectSegment(String segment) {
        System.out.println("CustomerDetailPage: Selecting Segment: " + segment);
        selectDropdownByVisibleText(segmentDropdown, segment);
    }
    
    public void selectRandomSegment() {
        System.out.println("CustomerDetailPage: Selecting Random Segment");
        selectRandomDropdownOption(segmentDropdown);
    }
    
    public void enterPhoneNumber(String phoneNumber) {
        System.out.println("CustomerDetailPage: Entering Phone Number: " + phoneNumber);
        WebElement element = driver.findElement(phoneNumberField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(phoneNumber);
    }
    
    public void enterEmail(String email) {
        System.out.println("CustomerDetailPage: Entering Email: " + email);
        WebElement element = driver.findElement(emailField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(email);
    }
    
    public void enterAdhaarNumber(String adhaarNumber) {
        System.out.println("CustomerDetailPage: Entering Adhaar Number: " + adhaarNumber);
        WebElement element = driver.findElement(adhaarNumberField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(adhaarNumber);
    }
    
    public void enterPan(String pan) {
        System.out.println("CustomerDetailPage: Entering PAN: " + pan);
        WebElement element = driver.findElement(panField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(pan);
    }
    
    public void selectAddressType(String addressType) {
        System.out.println("CustomerDetailPage: Selecting Address Type: " + addressType);
        selectDropdownByVisibleText(addressTypeDropdown, addressType);
    }
    
    public void selectRandomAddressType() {
        System.out.println("CustomerDetailPage: Selecting Random Address Type");
        selectRandomDropdownOption(addressTypeDropdown);
    }
    
    public void enterAddress1(String address) {
        System.out.println("CustomerDetailPage: Entering Address in address1 field: " + address);
        try {
            // First try to find the container
            WebElement container = driver.findElement(address1Field);
            waitHelper.waitForElementVisible(container);
            
            // Then find the input field within the container
            WebElement inputField;
            try {
                // Try to find input element
                inputField = container.findElement(By.tagName("input"));
            } catch (Exception e) {
                // If input not found, try textarea
                try {
                    inputField = container.findElement(By.tagName("textarea"));
                } catch (Exception ex) {
                    // If neither found, use the container itself (might be an input)
                    inputField = container;
                }
            }
            
            // Clear and enter the address
            inputField.clear();
            waitHelper.hardWait(300);
            inputField.sendKeys(address);
            System.out.println("CustomerDetailPage: Address entered successfully");
            
        } catch (Exception e) {
            System.err.println("CustomerDetailPage: Error entering address - " + e.getMessage());
            throw e;
        }
    }
    
    public void clickSaveBasicDetails() {
        System.out.println("CustomerDetailPage: Clicking Save Basic Details");
        waitHelper.clickWithWait(saveBasicDetails);
        waitHelper.hardWait(2000);
    }
    
    public void fillBasicDetailsWithRandomData() {
        System.out.println("CustomerDetailPage: Starting fillBasicDetailsWithRandomData");
        
        clickEditBasicDetails();
        System.out.println("  - Filling customer details fields...");
        
        enterCustomerNumber(RandomDataGenerator.generateRandomNumericString(10));
        enterCustomerName(RandomDataGenerator.generateRandomName());
        enterFatherName(RandomDataGenerator.generateRandomName());
        selectRandomCustomerType();
        selectRandomIndustry();
        selectRandomSegment();
        enterPhoneNumber(RandomDataGenerator.generateRandomPhoneNumber());
        enterEmail(RandomDataGenerator.generateRandomEmail());
        enterAdhaarNumber(RandomDataGenerator.generateAadhaarNumber());
        enterPan(RandomDataGenerator.generatePan());
        
        System.out.println("  - Selecting address type...");
        selectRandomAddressType();
        waitHelper.hardWait(500); // Small wait after dropdown selection
        
        System.out.println("  - Entering address in address1 field...");
        String randomAddress = RandomDataGenerator.generateRandomAddress();
        System.out.println("    Generated address: " + randomAddress);
        enterAddress1(randomAddress);
        
        System.out.println("  - Clicking elsewhere on screen to trigger validation...");
        try {
            // Click on page body or heading to remove focus from address field
            ((JavascriptExecutor) driver).executeScript("document.body.click();");
            waitHelper.hardWait(500);
        } catch (Exception e) {
            System.out.println("    Could not click on body, trying alternative method...");
            try {
                // Alternative: click on a heading or label element
                WebElement heading = driver.findElement(By.tagName("h5"));
                heading.click();
                waitHelper.hardWait(500);
            } catch (Exception ex) {
                System.out.println("    Click elsewhere skipped - " + ex.getMessage());
            }
        }
        
        System.out.println("  - Clicking Save button...");
        clickSaveBasicDetails();
        System.out.println("  - Save button clicked, waiting for page response...");
        
        // After save, system redirects to customer listing page - navigate back to detail page
        System.out.println("  - Save complete, checking if re-navigation is needed...");
        reNavigateToCustomerDetail();
        System.out.println("CustomerDetailPage: fillBasicDetailsWithRandomData completed");
    }
    
    // ==========================
    // NPA Details Methods
    // ==========================
    
    public void clickNpaSection() {
        System.out.println("CustomerDetailPage: Clicking NPA Section");
        scrollToElement(npaSection);
        waitHelper.clickWithWait(npaSection);
        waitHelper.hardWait(1000);
    }
    
    public void clickEditNpaDetails() {
        System.out.println("CustomerDetailPage: Clicking Edit NPA Details");
        waitHelper.clickWithWait(editNpaDetails);
        waitHelper.hardWait(1000);
    }
    
    public void clickNpaYes() {
        System.out.println("CustomerDetailPage: Clicking NPA Yes");
        waitHelper.clickWithWait(npaYesRadio);
        waitHelper.hardWait(500);
    }
    
    public void selectNpaType(String npaType) {
        System.out.println("CustomerDetailPage: Selecting NPA Type: " + npaType);
        selectDropdownByVisibleText(npaTypeDropdown, npaType);
    }
    
    public void selectRandomNpaType() {
        System.out.println("CustomerDetailPage: Selecting Random NPA Type");
        selectRandomDropdownOption(npaTypeDropdown);
    }
    
    public void enterDateOfDeclaringNpa(String date) {
        System.out.println("CustomerDetailPage: Entering Date of Declaring NPA: " + date);
        WebElement element = driver.findElement(dateOfDeclaringNpa);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(date);
    }
    
    public void selectPendencyReason(String reason) {
        System.out.println("CustomerDetailPage: Selecting Pendency Reason: " + reason);
        selectDropdownByVisibleText(pendencyReasonDropdown, reason);
    }
    
    public void selectRandomPendencyReason() {
        System.out.println("CustomerDetailPage: Selecting Random Pendency Reason");
        selectRandomDropdownOption(pendencyReasonDropdown);
    }
    
    public void enterPendencyRemark(String remark) {
        System.out.println("CustomerDetailPage: Entering Pendency Remark: " + remark);
        WebElement element = driver.findElement(pendencyRemarkField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(remark);
    }
    
    public void clickSaveNpaDetails() {
        System.out.println("CustomerDetailPage: Clicking Save NPA Details");
        waitHelper.clickWithWait(saveNpaDetails);
        waitHelper.hardWait(2000);
    }
    
    public void fillNpaDetailsWithRandomData() {
        clickNpaSection();
        clickEditNpaDetails();
        clickNpaYes();
        selectRandomNpaType();
        enterDateOfDeclaringNpa(generateFormattedDate());
        selectRandomPendencyReason();
        enterPendencyRemark(RandomDataGenerator.generateRandomAlphabeticString(20));
        clickSaveNpaDetails();
        
        // After save, system redirects to customer listing page - navigate back to detail page
        reNavigateToCustomerDetail();
    }
    
    // ==========================
    // Trust/Service Provider Methods
    // ==========================
    
    public void clickTrustSection() {
        System.out.println("CustomerDetailPage: Clicking Trust Section");
        scrollToElement(trustSection);
        waitHelper.clickWithWait(trustSection);
        waitHelper.hardWait(1000);
    }
    
    public void clickAddTrust() {
        System.out.println("CustomerDetailPage: Clicking Add Trust");
        waitHelper.clickWithWait(addTrustButton);
        waitHelper.hardWait(1000);
    }
    
    public void selectTrustId(String trustId) {
        System.out.println("CustomerDetailPage: Selecting Trust ID: " + trustId);
        selectDropdownByVisibleText(trustIdDropdown, trustId);
    }
    
    public void selectRandomTrustId() {
        System.out.println("CustomerDetailPage: Selecting Random Trust ID");
        selectRandomDropdownOption(trustIdDropdown);
    }
    
    public void enterDateOfAcquisition(String date) {
        System.out.println("CustomerDetailPage: Entering Date of Acquisition: " + date);
        WebElement element = driver.findElement(dateOfAcquisition);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(date);
    }
    
    public void enterSellingBank(String bankName) {
        System.out.println("CustomerDetailPage: Entering Selling Bank: " + bankName);
        WebElement element = driver.findElement(sellingBankField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(bankName);
    }
    
    public void clickSaveTrust() {
        System.out.println("CustomerDetailPage: Clicking Save Trust");
        waitHelper.clickWithWait(saveTrust);
        waitHelper.hardWait(2000);
    }
    
    public void fillTrustDetailsWithRandomData() {
        clickTrustSection();
        clickAddTrust();
        selectRandomTrustId();
        enterDateOfAcquisition(generateFormattedDate());
        enterSellingBank(RandomDataGenerator.generateRandomCompanyName());
        clickSaveTrust();
        
        // After save, system redirects to customer listing page - navigate back to detail page
        reNavigateToCustomerDetail();
    }
    
    // ==========================
    // Ownership Details Methods
    // ==========================
    
    public void clickOwnershipSection() {
        System.out.println("CustomerDetailPage: Clicking Ownership Section");
        scrollToElement(ownershipSection);
        waitHelper.clickWithWait(ownershipSection);
        waitHelper.hardWait(1000);
    }
    
    public void clickSecondaryDealingOfficerTab() {
        System.out.println("CustomerDetailPage: Clicking Secondary Dealing Officer Tab");
        waitHelper.clickWithWait(secondaryDealingOfficerTab);
        waitHelper.hardWait(1000);
    }
    
    public void clickAddSecondaryOfficer() {
        System.out.println("CustomerDetailPage: Clicking Add Secondary Officer");
        waitHelper.clickWithWait(addSecondaryOfficerButton);
        System.out.println("  - Waiting for modal/form to load...");
        waitHelper.hardWait(3000); // Wait longer for modal/form to fully appear
    }
    
    public void clickOfficerCheckbox() {
        System.out.println("CustomerDetailPage: Attempting to click Officer Checkbox");
        System.out.println("  - Target XPath: //*[@id='edit_4']");
        System.out.println("  - Outer HTML: /html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/div/div/form[2]/div/div[1]/div/div/table/tbody/tr[1]/td[2]/input");
        
        try {
            // Wait longer for modal contents to be fully loaded
            System.out.println("  - Waiting 3 seconds for modal to fully load...");
            waitHelper.hardWait(3000);
            
            WebElement targetCheckbox = null;
            String foundBy = "";
            
            // Strategy 1: Try to find by XPath with type checkbox
            try {
                System.out.println("  - Locator Strategy 1: //input[@id='edit_4' and @type='checkbox']");
                List<WebElement> checkboxes = driver.findElements(officerCheckboxAlternative);
                System.out.println("    Found " + checkboxes.size() + " checkbox(es)");
                
                for (int i = 0; i < checkboxes.size(); i++) {
                    WebElement cb = checkboxes.get(i);
                    try {
                        System.out.println("    Checkbox #" + (i+1) + ": displayed=" + cb.isDisplayed() + 
                                         ", enabled=" + cb.isEnabled());
                        if (cb.isDisplayed() && cb.isEnabled()) {
                            targetCheckbox = cb;
                            foundBy = "XPath with type";
                            System.out.println("    ✓ Using checkbox #" + (i+1));
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("    Checkbox #" + (i+1) + " check failed: " + e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println("    Locator Strategy 1 failed: " + e.getMessage());
            }
            
            // Strategy 2: Try CSS Selector
            if (targetCheckbox == null) {
                try {
                    System.out.println("  - Locator Strategy 2: CSS Selector input#edit_4[type='checkbox']");
                    List<WebElement> checkboxes = driver.findElements(officerCheckboxCss);
                    System.out.println("    Found " + checkboxes.size() + " checkbox(es)");
                    
                    for (WebElement cb : checkboxes) {
                        if (cb.isDisplayed() && cb.isEnabled()) {
                            targetCheckbox = cb;
                            foundBy = "CSS Selector";
                            System.out.println("    ✓ Found visible checkbox");
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("    Locator Strategy 2 failed: " + e.getMessage());
                }
            }
            
            // Strategy 3: Try simple XPath by ID
            if (targetCheckbox == null) {
                try {
                    System.out.println("  - Locator Strategy 3: XPath //*[@id='edit_4']");
                    List<WebElement> elements = driver.findElements(officerCheckbox);
                    System.out.println("    Found " + elements.size() + " element(s)");
                    
                    for (WebElement el : elements) {
                        if (el.isDisplayed() && el.isEnabled()) {
                            targetCheckbox = el;
                            foundBy = "Simple XPath";
                            System.out.println("    ✓ Found visible element");
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("    Locator Strategy 3 failed: " + e.getMessage());
                }
            }
            
            // Strategy 4: Find any edit checkbox in modal
            if (targetCheckbox == null) {
                try {
                    System.out.println("  - Locator Strategy 4: Any checkbox with 'edit' in ID");
                    List<WebElement> checkboxes = driver.findElements(officerCheckboxAny);
                    System.out.println("    Found " + checkboxes.size() + " checkbox(es)");
                    
                    for (WebElement cb : checkboxes) {
                        try {
                            String id = cb.getAttribute("id");
                            System.out.println("    Found checkbox with id: " + id);
                            if (cb.isDisplayed() && cb.isEnabled()) {
                                targetCheckbox = cb;
                                foundBy = "Any edit checkbox";
                                System.out.println("    ✓ Using checkbox: " + id);
                                break;
                            }
                        } catch (Exception e) {
                            // Continue searching
                        }
                    }
                } catch (Exception e) {
                    System.out.println("    Locator Strategy 4 failed: " + e.getMessage());
                }
            }
            
            if (targetCheckbox == null) {
                System.out.println("  - ✗ ERROR: Could not find checkbox with any locator strategy");
                System.out.println("  - Dumping page source for debugging...");
                try {
                    String pageSource = driver.getPageSource();
                    if (pageSource.contains("edit_4")) {
                        System.out.println("  - Page DOES contain 'edit_4' in source");
                        int index = pageSource.indexOf("edit_4");
                        String snippet = pageSource.substring(Math.max(0, index - 100), Math.min(pageSource.length(), index + 200));
                        System.out.println("  - Context: " + snippet);
                    } else {
                        System.out.println("  - Page does NOT contain 'edit_4' in source");
                    }
                } catch (Exception e) {
                    System.out.println("  - Could not dump page source: " + e.getMessage());
                }
                throw new RuntimeException("Checkbox element not found after trying all locator strategies");
            }
            
            final WebElement checkbox = targetCheckbox;
            System.out.println("  - ✓ Checkbox found using: " + foundBy);
            System.out.println("  - Checkbox state: displayed=" + checkbox.isDisplayed() + 
                             ", enabled=" + checkbox.isEnabled() + 
                             ", selected=" + checkbox.isSelected());
            
            // Remove overlays
            try {
                ((JavascriptExecutor) driver).executeScript(
                    "var overlays = document.querySelectorAll('.modal-backdrop, .overlay');" +
                    "overlays.forEach(function(el) { el.style.display = 'none'; });"
                );
            } catch (Exception e) {
                // Ignore
            }
            
            waitHelper.hardWait(500);
            
            // Click Strategy 1: JavaScript click  with scroll
            try {
                System.out.println("  - Click Strategy 1: JavaScript scroll + click...");
                ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});" +
                    "arguments[0].click();", 
                    checkbox
                );
                System.out.println("  - ✓ Checkbox clicked successfully!");
                waitHelper.hardWait(800);
                return;
            } catch (Exception e) {
                System.out.println("    Failed: " + e.getMessage());
            }
            
            // Click Strategy 2: Set checked directly
            try {
                System.out.println("  - Click Strategy 2: Set checked property...");
                ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].checked = true;" +
                    "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    checkbox
                );
                System.out.println("  - ✓ Checkbox checked successfully!");
                waitHelper.hardWait(800);
                return;
            } catch (Exception e) {
                System.out.println("    Failed: " + e.getMessage());
            }
            
            // Click Strategy 3: Actions class
            try {
                System.out.println("  - Click Strategy 3: Actions class...");
                Actions actions = new Actions(driver);
                actions.moveToElement(checkbox).click().build().perform();
                System.out.println("  - ✓ Checkbox clicked successfully!");
                waitHelper.hardWait(800);
                return;
            } catch (Exception e) {
                System.out.println("    Failed: " + e.getMessage());
            }
            
            // Click Strategy 4: Regular click
            System.out.println("  - Click Strategy 4: Regular click...");
            checkbox.click();
            System.out.println("  - ✓ Checkbox clicked successfully!");
            waitHelper.hardWait(800);
            
        } catch (Exception e) {
            System.out.println("  - ✗ FINAL ERROR: All strategies failed!");
            System.out.println("  - Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to click Officer Checkbox: " + e.getMessage(), e);
        }
    }
    
    public void clickSaveSecondaryOfficer() {
        System.out.println("CustomerDetailPage: Clicking Save Secondary Officer");
        waitHelper.clickWithWait(saveSecondaryOfficer);
        waitHelper.hardWait(3000); // Wait longer for save operation
    }
    
    public void fillOwnershipDetailsWithRandomData() {
        System.out.println("CustomerDetailPage: Filling Ownership Details");
        clickOwnershipSection();
        System.out.println("  - Clicking Secondary Dealing Officer tab...");
        clickSecondaryDealingOfficerTab();
        
        System.out.println("  - Clicking Addition button: //*[@id='CustSDO']/div/div[1]/span/a");
        clickAddSecondaryOfficer();
        
        System.out.println("  - Clicking Checkbox: //*[@id='edit_4']");
        clickOfficerCheckbox();
        
        System.out.println("  - Clicking Save: //*[@id='secondary-dealing-user']/div/form[2]/div/div[2]/div/div/input");
        clickSaveSecondaryOfficer();
        
        System.out.println("  - Save completed, checking for re-navigation...");
        // After save, system redirects to customer listing page - navigate back to detail page
        reNavigateToCustomerDetail();
    }
    
    // ==========================
    // Remarks Methods
    // ==========================
    
    public void clickRemarkSection() {
        System.out.println("CustomerDetailPage: Clicking Remark Section");
        scrollToElement(remarkSection);
        waitHelper.clickWithWait(remarkSection);
        waitHelper.hardWait(1000);
    }
    
    public void clickEditRemark() {
        System.out.println("CustomerDetailPage: Clicking Edit Remark");
        waitHelper.clickWithWait(editRemark);
        waitHelper.hardWait(1000);
    }
    
    public void enterRemark(String remark) {
        System.out.println("CustomerDetailPage: Entering Remark: " + remark);
        WebElement element = driver.findElement(remarkField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(remark);
    }
    
    public void clickSaveRemark() {
        System.out.println("CustomerDetailPage: Clicking Save Remark");
        waitHelper.clickWithWait(saveRemark);
        waitHelper.hardWait(2000);
    }
    
    public void fillRemarkWithRandomData() {
        System.out.println("CustomerDetailPage: Filling Remark with Random Data");
        clickRemarkSection();
        clickEditRemark();
        enterRemark(RandomDataGenerator.generateRandomAlphanumericString(30));
        clickSaveRemark();
        
        System.out.println("  - Save completed, checking for re-navigation...");
        // After save, system redirects to customer listing page - navigate back to detail page
        reNavigateToCustomerDetail();
    }
    
    // ==========================
    // Recovery Summary / Expense Methods
    // ==========================
    
    public void clickRecoverySummarySection() {
        System.out.println("CustomerDetailPage: Clicking Recovery Summary Section");
        scrollToElement(recoverySummarySection);
        waitHelper.clickWithWait(recoverySummarySection);
        waitHelper.hardWait(1000);
    }
    
    public void clickRecoverySummaryTab() {
        System.out.println("CustomerDetailPage: Clicking Recovery Summary Tab");
        waitHelper.clickWithWait(recoverySummaryTab);
        waitHelper.hardWait(1000);
    }
    
    public void clickCustomerExpensesTab() {
        System.out.println("CustomerDetailPage: Clicking Customer Expenses Tab");
        waitHelper.clickWithWait(customerExpensesTab);
        waitHelper.hardWait(1000);
    }
    
    public void navigateRecoverySummaryTabs() {
        clickRecoverySummarySection();
        clickRecoverySummaryTab();
        clickCustomerExpensesTab();
    }
    
    // ==========================
    // Comment Methods
    // ==========================
    
    public void clickCommentSection() {
        System.out.println("CustomerDetailPage: Clicking Comment Section");
        scrollToElement(commentSection);
        waitHelper.clickWithWait(commentSection);
        waitHelper.hardWait(1000);
    }
    
    public void enterComment(String comment) {
        System.out.println("CustomerDetailPage: Entering Comment: " + comment);
        WebElement element = driver.findElement(commentField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(comment);
    }
    
    public void clickAddComment() {
        System.out.println("CustomerDetailPage: Clicking Add Comment");
        waitHelper.clickWithWait(addCommentButton);
        waitHelper.hardWait(2000);
    }
    
    public void fillCommentWithRandomData() {
        clickCommentSection();
        enterComment(RandomDataGenerator.generateRandomComment());
        clickAddComment();
        
        // After save, system redirects to customer listing page - navigate back to detail page
        reNavigateToCustomerDetail();
    }
    
    // ==========================
    // Customer Other Details (Account) Methods
    // ==========================
    
    public void clickCustomerOtherDetailsSection() {
        System.out.println("CustomerDetailPage: Clicking Customer Other Details Section");
        scrollToElement(customerOtherDetailsSection);
        waitHelper.clickWithWait(customerOtherDetailsSection);
        waitHelper.hardWait(1000);
    }
    
    public void clickAddAccount() {
        System.out.println("CustomerDetailPage: Clicking Add Account");
        waitHelper.clickWithWait(addAccountButton);
        waitHelper.hardWait(1000);
    }
    
    public void enterLoanAccountNumber(String accountNumber) {
        System.out.println("CustomerDetailPage: Entering Loan Account Number: " + accountNumber);
        WebElement element = driver.findElement(loanAccountNumberField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(accountNumber);
    }
    
    public void selectAccountType(String accountType) {
        System.out.println("CustomerDetailPage: Selecting Account Type: " + accountType);
        selectDropdownByVisibleText(accountTypeDropdown, accountType);
    }
    
    public void selectRandomAccountType() {
        System.out.println("CustomerDetailPage: Selecting Random Account Type");
        try {
            WebElement dropdown = driver.findElement(accountTypeDropdown);
            waitHelper.waitForElementVisible(dropdown);
            
            // Click dropdown to trigger onclick="CustomerAccountTypesCreateLoad()" if needed
            System.out.println("  - Clicking Account Type dropdown...");
            dropdown.click();
            waitHelper.hardWait(500); // Brief wait in case onclick loads data
            
            Select select = new Select(dropdown);
            java.util.List<WebElement> options = select.getOptions();
            System.out.println("  - Account Type dropdown has " + options.size() + " options");
            
            // Display all available options
            if (options.size() > 1) {
                System.out.println("  - Available Account Types:");
                for (int i = 0; i < options.size(); i++) {
                    String text = options.get(i).getText().trim();
                    String value = options.get(i).getAttribute("value");
                    if (!text.isEmpty() && !value.isEmpty()) {
                        System.out.println("    [" + i + "] " + text + " (value=" + value + ")");
                    }
                }
            }
            
            // Skip first option if it's a placeholder (like "Select")
            int startIndex = options.size() > 1 && 
                           (options.get(0).getText().trim().equalsIgnoreCase("Select") || 
                            options.get(0).getAttribute("value").isEmpty()) ? 1 : 0;
            
            if (options.size() > startIndex) {
                Random random = new Random();
                int randomIndex = startIndex + random.nextInt(options.size() - startIndex);
                String selectedValue = options.get(randomIndex).getText();
                select.selectByIndex(randomIndex);
                System.out.println("  ✓ Account Type selected: " + selectedValue);
                waitHelper.hardWait(500);
            } else {
                System.out.println("  - WARNING: No selectable options in Account Type dropdown");
            }
        } catch (Exception e) {
            System.err.println("  - ERROR selecting Account Type: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void enterPrincipalAmount(String amount) {
        System.out.println("CustomerDetailPage: Entering Principal Amount: " + amount);
        WebElement element = driver.findElement(principalAmountField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(amount);
    }
    
    public void enterRateOfInterest(String rate) {
        System.out.println("CustomerDetailPage: Entering Rate of Interest: " + rate);
        WebElement element = driver.findElement(rateOfInterestField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(rate);
    }
    
    public void enterOutstandingBalance(String balance) {
        System.out.println("CustomerDetailPage: Entering Outstanding Balance: " + balance);
        WebElement element = driver.findElement(outstandingBalanceField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(balance);
    }
    
    public void enterLoanDisbursalDate(String date) {
        System.out.println("CustomerDetailPage: Entering Loan Disbursal Date: " + date);
        WebElement element = driver.findElement(loanDisbursalDateField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(date);
    }
    
    public void selectAccountStatus(String status) {
        System.out.println("CustomerDetailPage: Selecting Account Status: " + status);
        // Trigger onclick event first to load options
        WebElement dropdown = driver.findElement(accountStatusDropdown);
        waitHelper.waitForElementVisible(dropdown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].onclick.call(arguments[0]);", dropdown);
        waitHelper.hardWait(1000);
        selectDropdownByVisibleText(accountStatusDropdown, status);
    }
    
    public void selectRandomAccountStatus() {
        System.out.println("CustomerDetailPage: Selecting Random Account Status");
        try {
            WebElement dropdown = driver.findElement(accountStatusDropdown);
            waitHelper.waitForElementVisible(dropdown);
            
            // Trigger onclick="CustomerAccountCreateAccountStatusLoad()" using JavaScript
            System.out.println("  - Triggering onclick event to load Account Status options...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].onclick.call(arguments[0]);", dropdown);
            waitHelper.hardWait(1500); // Wait longer for AJAX to load options
            
            Select select = new Select(dropdown);
            java.util.List<WebElement> options = select.getOptions();
            System.out.println("  - Account Status dropdown now has " + options.size() + " options");
            
            // Display all available options
            if (options.size() > 1) {
                System.out.println("  - Available Account Statuses:");
                for (int i = 0; i < options.size(); i++) {
                    String text = options.get(i).getText().trim();
                    String value = options.get(i).getAttribute("value");
                    if (!text.isEmpty() && !value.isEmpty()) {
                        System.out.println("    [" + i + "] " + text + " (value=" + value + ")");
                    }
                }
            }
            
            // Skip first option if it's a placeholder (like "Select")
            int startIndex = options.size() > 1 && 
                           (options.get(0).getText().trim().equalsIgnoreCase("Select") || 
                            options.get(0).getAttribute("value").isEmpty()) ? 1 : 0;
            
            if (options.size() > startIndex) {
                Random random = new Random();
                int randomIndex = startIndex + random.nextInt(options.size() - startIndex);
                String selectedValue = options.get(randomIndex).getText();
                select.selectByIndex(randomIndex);
                System.out.println("  ✓ Account Status selected: " + selectedValue);
                waitHelper.hardWait(500);
            } else {
                System.out.println("  - WARNING: No selectable options in Account Status dropdown after loading");
                System.out.println("  - Attempting to trigger onclick event again with click...");
                dropdown.click();
                waitHelper.hardWait(1000);
                
                // Try again after click
                select = new Select(dropdown);
                options = select.getOptions();
                System.out.println("  - After click, dropdown has " + options.size() + " options");
                
                startIndex = options.size() > 1 && 
                           (options.get(0).getText().trim().equalsIgnoreCase("Select") || 
                            options.get(0).getAttribute("value").isEmpty()) ? 1 : 0;
                
                if (options.size() > startIndex) {
                    Random random = new Random();
                    int randomIndex = startIndex + random.nextInt(options.size() - startIndex);
                    String selectedValue = options.get(randomIndex).getText();
                    select.selectByIndex(randomIndex);
                    System.out.println("  ✓ Account Status selected: " + selectedValue);
                } else {
                    System.out.println("  - ERROR: Still no options available in Account Status dropdown");
                }
            }
        } catch (Exception e) {
            System.err.println("  - ERROR selecting Account Status: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void clickBasicInformationNext() {
        System.out.println("CustomerDetailPage: Clicking Basic Information Next");
        waitHelper.clickWithWait(basicInformationNext);
        waitHelper.hardWait(1000);
    }
    
    public void clickBankConsortiumNext() {
        System.out.println("CustomerDetailPage: Clicking Bank Consortium Next");
        waitHelper.clickWithWait(bankConsortiumNext);
        waitHelper.hardWait(1000);
    }
    
    public void clickSubmitAccount() {
        System.out.println("CustomerDetailPage: Clicking Submit Account");
        waitHelper.clickWithWait(submitAccount);
        waitHelper.hardWait(2000);
    }
    
    public void fillCustomerAccountWithRandomData() {
        clickCustomerOtherDetailsSection();
        clickAddAccount();
        enterLoanAccountNumber(RandomDataGenerator.generateRandomAccountNumber());
        selectRandomAccountStatus(); // Select Account Status first, right after account number
        selectRandomAccountType();   // Then select Account Type
        enterPrincipalAmount(RandomDataGenerator.generateRandomAmount());
        enterRateOfInterest(RandomDataGenerator.generateRandomInterestRate());
        enterOutstandingBalance(RandomDataGenerator.generateRandomAmount());
        enterLoanDisbursalDate(generateFormattedDate());
        clickBasicInformationNext();
        clickBankConsortiumNext();
        clickSubmitAccount();
        
        // After save, system redirects to customer listing page - navigate back to detail page
        reNavigateToCustomerDetail();
    }
    
    // ==========================
    // OTS / One Time Settlement Methods
    // ==========================
    
    public void clickOtsSection() {
        System.out.println("CustomerDetailPage: Clicking OTS Section");
        scrollToElement(otsSection);
        waitHelper.clickWithWait(otsSection);
        waitHelper.hardWait(1000);
    }
    
    public void clickAddOts() {
        System.out.println("CustomerDetailPage: Clicking Add OTS");
        waitHelper.clickWithWait(addOtsButton);
        waitHelper.hardWait(1000);
    }
    
    public void selectSettlementStatus(String status) {
        System.out.println("CustomerDetailPage: Selecting Settlement Status: " + status);
        selectDropdownByVisibleText(settlementStatusDropdown, status);
    }
    
    public void selectRandomSettlementStatus() {
        System.out.println("CustomerDetailPage: Selecting Random Settlement Status");
        try {
            WebElement dropdown = driver.findElement(settlementStatusDropdown);
            waitHelper.waitForElementVisible(dropdown);
            
            // Use JavaScript to bypass any overlay issues
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Trigger focus event using JavaScript (may call GetSettlementStatus())
            js.executeScript("arguments[0].focus();", dropdown);
            waitHelper.hardWait(500);
            
            Select select = new Select(dropdown);
            java.util.List<WebElement> options = select.getOptions();
            System.out.println("  - Settlement Status dropdown has " + options.size() + " options");
            
            // Display all available options
            if (options.size() > 1) {
                System.out.println("  - Available Settlement Statuses:");
                for (int i = 0; i < options.size(); i++) {
                    String text = options.get(i).getText().trim();
                    String value = options.get(i).getAttribute("value");
                    System.out.println("    [" + i + "] " + text + " (value=" + value + ")");
                }
            }
            
            // Skip first option if it's a placeholder (like "Select")
            int startIndex = options.size() > 1 && 
                           (options.get(0).getText().trim().equalsIgnoreCase("Select") || 
                            options.get(0).getAttribute("value").isEmpty()) ? 1 : 0;
            
            if (options.size() > startIndex) {
                Random random = new Random();
                int randomIndex = startIndex + random.nextInt(options.size() - startIndex);
                String selectedValue = options.get(randomIndex).getText();
                
                // Use JavaScript to set the selected index - bypasses click interceptors
                js.executeScript("arguments[0].selectedIndex = arguments[1];", dropdown, randomIndex);
                
                // Trigger change event to notify the form
                js.executeScript("arguments[0].dispatchEvent(new Event('change'));", dropdown);
                
                System.out.println("  ✓ Settlement Status selected: " + selectedValue);
                waitHelper.hardWait(500);
            } else {
                System.out.println("  - ERROR: No selectable options in Settlement Status dropdown");
            }
        } catch (Exception e) {
            System.err.println("Failed to select random Settlement Status: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void enterSettlementAmount(String amount) {
        System.out.println("CustomerDetailPage: Entering Settlement Amount: " + amount);
        WebElement element = driver.findElement(settlementAmountField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(amount);
    }
    
    public void clickSaveOts() {
        System.out.println("CustomerDetailPage: Clicking Save OTS");
        waitHelper.clickWithWait(saveOts);
        waitHelper.hardWait(2000);
    }
    
    public void fillOtsWithRandomData() {
        clickOtsSection();
        clickAddOts();
        selectRandomSettlementStatus();
        enterSettlementAmount(RandomDataGenerator.generateRandomAmount());
        clickSaveOts();
        
        // After save, system redirects to customer listing page - navigate back to detail page
        reNavigateToCustomerDetail();
    }
    
    // ==========================
    // Information Request Methods
    // ==========================
    
    public void clickInformationRequestSection() {
        System.out.println("CustomerDetailPage: Clicking Information Request Section");
        scrollToElement(informationRequestSection);
        waitHelper.clickWithWait(informationRequestSection);
        waitHelper.hardWait(1000);
    }
    
    public void clickAddInformationRequest() {
        System.out.println("CustomerDetailPage: Clicking Add Information Request");
        waitHelper.clickWithWait(addInformationRequestButton);
        waitHelper.hardWait(1000);
    }
    
    public void selectToUser(String userName) {
        System.out.println("CustomerDetailPage: Selecting To User: " + userName);
        try {
            WebElement element = driver.findElement(toUserInput);
            waitHelper.waitForElementVisible(element);
            element.click();
            element.sendKeys(userName);
            element.sendKeys(Keys.ENTER);
            waitHelper.hardWait(500);
        } catch (Exception e) {
            System.err.println("CustomerDetailPage: Failed to select To User - " + e.getMessage());
        }
    }
    
    public void selectRandomToUser() {
        System.out.println("CustomerDetailPage: Selecting Random To User");
        
        int maxAttempts = 3;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                System.out.println("  - Attempt " + attempt + " of " + maxAttempts);
                
                // Wait longer for page to be ready (especially in full flow context)
                waitHelper.hardWait(3000);
                
                // Close any lingering modals or overlays that might block interaction
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript(
                    "var modals = document.querySelectorAll('.modal, .modal-backdrop, .select2-container--open'); "
                    + "modals.forEach(function(el) { "
                    + "  if (el.classList.contains('select2-container--open') && !el.querySelector('input[placeholder*=\"Select users\"]')) { "
                    + "    el.classList.remove('select2-container--open'); "
                    + "  } "
                    + "});");
                waitHelper.hardWait(500);
                
                // Scroll to Select2 container first
                WebElement select2Container = driver.findElement(toUserSelect2Container);
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", select2Container);
                waitHelper.hardWait(1000);
                
                // Click on Select2 container to open dropdown using JavaScript
                System.out.println("  - Clicking Select2 container to open dropdown...");
                waitHelper.waitForElementVisible(select2Container);
                
                // First close any open Select2 dropdown
                js.executeScript("$('.select2-container--open').select2('close');");
                waitHelper.hardWait(500);
                
                // Now open our dropdown
                js.executeScript("arguments[0].click();", select2Container);
                System.out.println("  - Dropdown click executed");
                waitHelper.hardWait(2000);
                
                // Wait for dropdown to appear and options to load
                waitHelper.hardWait(3000);
                
                // Check if dropdown is actually open
                Object dropdownStatus = js.executeScript(
                    "var container = document.querySelector('.select2-container--open'); "
                    + "var results = document.querySelectorAll('.select2-results__option, .select2-results li'); "
                    + "return 'Open: ' + (container !== null) + ', Results count: ' + results.length;");
                System.out.println("  - Dropdown status: " + dropdownStatus);
                
                // Check if dropdown opened
                Boolean isOpen = (Boolean) js.executeScript(
                    "return document.querySelector('.select2-container--open') !== null;");
                
                if (!isOpen) {
                    System.out.println("  - Dropdown not open, retrying...");
                    waitHelper.hardWait(1000);
                    continue;
                }
                
                // Get ALL li elements from the dropdown results using multiple strategies
                java.util.List<WebElement> allItems = driver.findElements(
                    By.xpath("//span[contains(@class,'select2-results')]//li | //ul[contains(@class,'select2-results')]//li"));
                
                System.out.println("  - Total items found in dropdown: " + allItems.size());
                
                if (allItems.size() == 0) {
                    // Try alternative CSS selector
                    allItems = driver.findElements(
                        By.cssSelector(".select2-results li, .select2-results__option"));
                    System.out.println("  - Alternative selector found: " + allItems.size() + " items");
                }
                
                if (allItems.size() == 0) {
                    System.out.println("  - No items found, retrying...");
                    continue;
                }
                
                // Filter out search bar and loading messages
                java.util.List<WebElement> validOptions = new java.util.ArrayList<>();
                for (WebElement item : allItems) {
                    try {
                        String text = item.getText().trim();
                        String itemClass = item.getAttribute("class");
                        String itemRole = item.getAttribute("role");
                        boolean isDisplayed = item.isDisplayed();
                        
                        System.out.println("  - Item: '" + text + "' | Class: " + itemClass + " | Role: " + itemRole + " | Displayed: " + isDisplayed);
                        
                        // Skip search bar, loading messages, empty items
                        if (text != null && !text.isEmpty() 
                            && !text.equals("Searching…") 
                            && !text.equals("Loading results…")
                            && !text.equals("No results found")
                            && !itemClass.contains("select2-search")
                            && isDisplayed
                            && (itemRole == null || itemRole.equals("option") || itemRole.equals("treeitem"))) {
                            validOptions.add(item);
                            System.out.println("  ✓ Valid user option: " + text);
                        }
                    } catch (Exception ex) {
                        // Skip items that cause errors
                        System.out.println("  - Skipped item due to error: " + ex.getMessage());
                    }
                }
                
                if (!validOptions.isEmpty()) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(validOptions.size());
                    WebElement selectedOption = validOptions.get(randomIndex);
                    String optionText = selectedOption.getText().trim();
                    
                    System.out.println("  - Selecting user: " + optionText);
                    
                    // Scroll option into view
                    js.executeScript("arguments[0].scrollIntoView(true);", selectedOption);
                    waitHelper.hardWait(500);
                    
                    // Try regular click first (Select2 expects normal click)
                    System.out.println("  - Attempting click on option...");
                    try {
                        selectedOption.click();
                        System.out.println("  - Regular click executed");
                    } catch (Exception e) {
                        // Fallback to JS click
                        System.out.println("  - Regular click failed, using JS click");
                        js.executeScript("arguments[0].click();", selectedOption);
                    }
                    
                    waitHelper.hardWait(800);
                    
                    // Press Enter using Actions class (more reliable for Select2)
                    System.out.println("  - Pressing Enter to confirm selection...");
                    Actions actions = new Actions(driver);
                    actions.sendKeys(Keys.ENTER).perform();
                    waitHelper.hardWait(1000);
                    
                    // Verify selection by checking the display value
                    String displayText = select2Container.getText();
                    System.out.println("  ✓ Display text after selection: '" + displayText + "'");
                    System.out.println("  ✓ User selected and confirmed: " + optionText);
                    
                    // Success - exit the retry loop
                    return;
                } else {
                    System.out.println("  - ERROR: No valid user options found, retrying...");
                    // Close dropdown by clicking elsewhere
                    js.executeScript("document.body.click();");
                    waitHelper.hardWait(1000);
                }
                
            } catch (Exception e) {
                System.err.println("  - Attempt " + attempt + " failed: " + e.getMessage());
                if (attempt == maxAttempts) {
                    System.err.println("CustomerDetailPage: Failed to select random To User after " + maxAttempts + " attempts");
                    e.printStackTrace();
                } else {
                    System.out.println("  - Retrying after error...");
                    waitHelper.hardWait(2000);
                }
            }
        }
    }
    
    public void enterInformationRequestContent(String content) {
        System.out.println("CustomerDetailPage: Entering Information Request Content: " + content);
        WebElement element = driver.findElement(contentField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(content);
    }
    
    public void clickSaveInformationRequest() {
        System.out.println("CustomerDetailPage: Clicking Save Information Request");
        waitHelper.clickWithWait(saveInformationRequest);
        waitHelper.hardWait(2000);
    }
    
    public void fillInformationRequestWithRandomData() {
        clickInformationRequestSection();
        clickAddInformationRequest();
        selectRandomToUser(); // Select a random user from dropdown
        enterInformationRequestContent(RandomDataGenerator.generateRandomAlphabeticString(50));
        clickSaveInformationRequest();
        
        // After save, system redirects to customer listing page - navigate back to detail page
        reNavigateToCustomerDetail();
    }
    
    // ==========================
    // Address Details Methods
    // ==========================
    
    public void clickAddressDetailsSection() {
        System.out.println("CustomerDetailPage: Clicking Address Details Section");
        scrollToElement(addressDetailsSection);
        waitHelper.clickWithWait(addressDetailsSection);
        waitHelper.hardWait(1000);
    }
    
    public void clickAddAddress() {
        System.out.println("CustomerDetailPage: Clicking Add Address");
        waitHelper.clickWithWait(addAddressButton);
        waitHelper.hardWait(1000);
    }
    
    public void selectAddressTypeInSection(String addressType) {
        System.out.println("CustomerDetailPage: Selecting Address Type in Section: " + addressType);
        selectDropdownByVisibleText(addressTypeDropdownInSection, addressType);
    }
    
    public void selectRandomAddressTypeInSection() {
        System.out.println("CustomerDetailPage: Selecting Random Address Type in Section");
        selectRandomDropdownOption(addressTypeDropdownInSection);
    }
    
    public void enterAddressInSection(String address) {
        System.out.println("CustomerDetailPage: Entering Address in Section: " + address);
        WebElement element = driver.findElement(addressField);
        waitHelper.waitForElementVisible(element);
        element.clear();
        element.sendKeys(address);
    }
    
    public void selectState(String state) {
        System.out.println("CustomerDetailPage: Selecting State: " + state);
        selectDropdownByVisibleText(stateDropdown, state);
    }
    
    public void selectRandomState() {
        System.out.println("CustomerDetailPage: Selecting Random State");
        selectRandomDropdownOption(stateDropdown);
    }
    
    public void selectLocation(String location) {
        System.out.println("CustomerDetailPage: Selecting Location: " + location);
        selectDropdownByVisibleText(locationDropdown, location);
    }
    
    public void selectRandomLocation() {
        System.out.println("CustomerDetailPage: Selecting Random Location");
        selectRandomDropdownOption(locationDropdown);
    }
    
    public void clickSaveAddress() {
        System.out.println("CustomerDetailPage: Clicking Save Address");
        waitHelper.clickWithWait(saveAddress);
        waitHelper.hardWait(2000);
    }
    
    public void fillAddressDetailsWithRandomData() {
        clickAddressDetailsSection();
        clickAddAddress();
        selectRandomAddressTypeInSection();
        enterAddressInSection(RandomDataGenerator.generateRandomAddress());
        selectRandomState();
        waitHelper.hardWait(1000); // Wait for location dropdown to populate
        selectRandomLocation();
        clickSaveAddress();
        
        // After save, system redirects to customer listing page - navigate back to detail page
        reNavigateToCustomerDetail();
    }
    
    // ==========================
    // Audit Trail Methods
    // ==========================
    
    public void clickAuditTrailSection() {
        System.out.println("CustomerDetailPage: Clicking Audit Trail Section");
        scrollToElement(auditTrailSection);
        waitHelper.clickWithWait(auditTrailSection);
        waitHelper.hardWait(1000);
    }
    
    // ==========================
    // Helper Methods
    // ==========================
    
    /**
     * Generate formatted date in "02-Feb-2026" format
     */
    private String generateFormattedDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return date.format(formatter);
    }
    
    /**
     * Fill all customer detail sections with random data
     */
    public void fillAllCustomerDetailsWithRandomData() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("CustomerDetailPage: Starting full customer details automation");
        System.out.println("=".repeat(80) + "\n");
        
        // Navigate to customer detail page
        System.out.println(">>> STEP 0: Initial Navigation");
        navigateToCustomerDetail();
        System.out.println("✓ Initial navigation complete\n");
        
        // Fill Basic Details (this will save and navigate back)
        System.out.println(">>> STEP 1: Fill Basic Details");
        try {
            fillBasicDetailsWithRandomData();
            System.out.println("✓ Basic details completed\n");
            waitHelper.hardWait(2000);
        } catch (Exception e) {
            System.err.println("✗ Error in Basic Details: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Fill NPA Details
        System.out.println(">>> STEP 2: Fill NPA Details");
        try {
            fillNpaDetailsWithRandomData();
            System.out.println("✓ NPA details completed\n");
            waitHelper.hardWait(2000);
        } catch (Exception e) {
            System.err.println("✗ Error in NPA Details: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Fill Trust/Service Provider Details
        System.out.println(">>> STEP 3: Fill Trust/Service Provider Details");
        try {
            fillTrustDetailsWithRandomData();
            System.out.println("✓ Trust details completed\n");
            waitHelper.hardWait(2000);
        } catch (Exception e) {
            System.err.println("✗ Error in Trust Details: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Fill Ownership Details
        System.out.println(">>> STEP 4: Fill Ownership Details");
        try {
            fillOwnershipDetailsWithRandomData();
            System.out.println("✓ Ownership details completed\n");
            waitHelper.hardWait(2000);
        } catch (Exception e) {
            System.err.println("✗ Error in Ownership Details: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Fill Remarks
        System.out.println(">>> STEP 5: Fill Remarks");
        try {
            fillRemarkWithRandomData();
            System.out.println("✓ Remarks completed\n");
            waitHelper.hardWait(2000);
        } catch (Exception e) {
            System.err.println("✗ Error in Remarks: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Navigate Recovery Summary Tabs
        System.out.println(">>> STEP 6: Navigate Recovery Summary Tabs");
        try {
            navigateRecoverySummaryTabs();
            System.out.println("✓ Recovery Summary tabs navigated\n");
            waitHelper.hardWait(2000);
        } catch (Exception e) {
            System.err.println("✗ Error in Recovery Summary: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Fill Comments
        System.out.println(">>> STEP 7: Fill Comments");
        try {
            fillCommentWithRandomData();
            System.out.println("✓ Comments completed\n");
            waitHelper.hardWait(2000);
        } catch (Exception e) {
            System.err.println("✗ Error in Comments: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Fill Customer Other Details (Account)
        System.out.println(">>> STEP 8: Fill Customer Account Details");
        try {
            fillCustomerAccountWithRandomData();
            System.out.println("✓ Customer account completed\n");
            waitHelper.hardWait(2000);
        } catch (Exception e) {
            System.err.println("✗ Error in Customer Account: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Fill OTS
        System.out.println(">>> STEP 9: Fill OTS Details");
        try {
            fillOtsWithRandomData();
            System.out.println("✓ OTS completed\n");
            waitHelper.hardWait(2000);
        } catch (Exception e) {
            System.err.println("✗ Error in OTS: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Fill Information Request
        System.out.println(">>> STEP 10: Fill Information Request");
        try {
            fillInformationRequestWithRandomData();
            System.out.println("✓ Information Request completed\n");
            waitHelper.hardWait(2000);
        } catch (Exception e) {
            System.err.println("✗ Error in Information Request: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Fill Address Details
        System.out.println(">>> STEP 11: Fill Address Details");
        try {
            fillAddressDetailsWithRandomData();
            System.out.println("✓ Address Details completed\n");
            waitHelper.hardWait(2000);
        } catch (Exception e) {
            System.err.println("✗ Error in Address Details: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Click Audit Trail
        System.out.println(">>> STEP 12: Click Audit Trail");
        try {
            clickAuditTrailSection();
            System.out.println("✓ Audit Trail clicked\n");
            waitHelper.hardWait(2000);
        } catch (Exception e) {
            System.err.println("✗ Error in Audit Trail: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("CustomerDetailPage: All customer details automation completed");
        System.out.println("=".repeat(80) + "\n");
    }
}
