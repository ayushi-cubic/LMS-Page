package com.example.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.example.utils.RandomDataGenerator;
import com.example.utils.WaitHelper;

/**
 * Page Object Model for Account Detail Page
 * Handles editing account details with comprehensive field operations
 */
public class AccountDetailPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final Map<String, String> accountDetailData;
    private final Random random;
    
    // Edit Button
    @FindBy(xpath = "//*[@id='flush-collapse14']/div/div/div/div[1]/a")
    private WebElement editButton;
    
    // Form Fields
    @FindBy(xpath = "//*[@id='AccountTypeIdEdit']")
    private WebElement accountTypeDropdown;
    
    @FindBy(xpath = "//*[@id='ParentAccountIdEdit']")
    private WebElement parentAccountDropdown;
    
    @FindBy(xpath = "//*[@id='AgreementDateEdit']")
    private WebElement agreementDateField;
    
    @FindBy(xpath = "//*[@id='LoanDisbursalDateEdit']")
    private WebElement loanDisbursalDateField;
    
    @FindBy(xpath = "//*[@id='MaturityDateEdit']")
    private WebElement maturityDateField;
    
    @FindBy(xpath = "//*[@id='ZoneIdEdit']")
    private WebElement zoneDropdown;
    
    @FindBy(xpath = "//*[@id='StateIdEdit']")
    private WebElement stateDropdown;
    
    @FindBy(xpath = "//*[@id='LocationIdEdit']")
    private WebElement locationDropdown;
    
    @FindBy(xpath = "//*[@id='StatusEdit']")
    private WebElement accountStatusDropdown;
    
    @FindBy(xpath = "//*[@id='BusinessUnitIdEdit']")
    private WebElement businessUnitDropdown;
    
    @FindBy(xpath = "//*[@id='UploadDate']")
    private WebElement uploadDateField;
    
    @FindBy(xpath = "//*[@id='NPATypeOnEdit']")
    private WebElement npaTypeDropdown;
    
    @FindBy(xpath = "//*[@id='CustomerAccountBlockType1Create']")
    private WebElement blockType1Field;
    
    @FindBy(xpath = "//*[@id='CustomerAccountDateOfBlockType1Create']")
    private WebElement dateOfBlockType1Field;
    
    @FindBy(xpath = "//*[@id='CustomerAccountBlockType2Create']")
    private WebElement blockType2Field;
    
    @FindBy(xpath = "//*[@id='CustomerAccountDateOfBlockType2Create']")
    private WebElement dateOfBlockType2Field;
    
    @FindBy(xpath = "//*[@id='CustomerAccountCreateNonCooperativeBorrower']")
    private WebElement nonCooperativeBorrowerDropdown;
    
    @FindBy(xpath = "//*[@id='CustomerAccountNPARemovalDate']")
    private WebElement npaRemovalDateField;
    
    @FindBy(xpath = "//*[@id='EditBusinessDate']")
    private WebElement businessDateField;
    
    @FindBy(xpath = "//*[@id='SchemeId']")
    private WebElement schemeNameDropdown;
    
    @FindBy(xpath = "//*[@id='EditDateOfNPA']")
    private WebElement dateOfNPAField;
    
    @FindBy(xpath = "//*[@id='CustomerAccountFraudYes']")
    private WebElement fraudYesRadio;
    
    @FindBy(xpath = "//*[@id='SubmitBasicEditDetails']")
    private WebElement saveButton;
    
    // Bad Debt Section
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[2]/div[1]/div")
    private WebElement badDebtSection;
    
    @FindBy(xpath = "//*[@id='CustomerBadDepdDetailsSection']/div/div/div[1]/a")
    private WebElement badDebtEditButton;
    
    @FindBy(xpath = "//*[@id='NOCStatusTrue']")
    private WebElement nocStatusYesRadio;
    
    @FindBy(xpath = "//*[@id='BadDebtTrue']")
    private WebElement badDebtYesRadio;
    
    @FindBy(xpath = "//*[@id='submitButtonBadDebt']")
    private WebElement badDebtSaveButton;
    
    // Account Details Section (Extended)
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[3]/div[1]/div")
    private WebElement accountDetailsSection;
    
    @FindBy(xpath = "//*[@id='CustomerAccountCustomerAccountDetailsSection']/div/div/div[1]/a")
    private WebElement accountDetailsEditButton;
    
    @FindBy(xpath = "//*[@id='PrincipalAmountOnEdit']")
    private WebElement principalAmountField;
    
    @FindBy(xpath = "//*[@id='RateOfInterestOnEdit']")
    private WebElement rateOfInterestField;
    
    @FindBy(xpath = "//*[@id='InterestAmountOnEdit']")
    private WebElement interestAmountField;
    
    @FindBy(xpath = "//*[@id='InterestOverdue']")
    private WebElement interestOverdueField;
    
    @FindBy(xpath = "//*[@id='EMIAmount']")
    private WebElement emiAmountField;
    
    @FindBy(xpath = "//*[@id='EMIOutstanding']")
    private WebElement emiOutstandingField;
    
    @FindBy(xpath = "//*[@id='POSOnLastMonthEnd']")
    private WebElement posOnLastMonthEndField;
    
    @FindBy(xpath = "//*[@id='EditPaymentMode']")
    private WebElement repaymentModeDropdown;
    
    @FindBy(xpath = "//*[@id='OutstandingBalanceOnEdit']")
    private WebElement outstandingBalanceField;
    
    @FindBy(xpath = "//*[@id='PenalROI']")
    private WebElement penalROIField;
    
    @FindBy(xpath = "//*[@id='PenaltyOverdue']")
    private WebElement penaltyOverdueField;
    
    @FindBy(xpath = "//*[@id='Tenure']")
    private WebElement tenureField;
    
    @FindBy(xpath = "//*[@id='InstallmentOverdue']")
    private WebElement installmentOverdueField;
    
    @FindBy(xpath = "//*[@id='Receivable']")
    private WebElement receivableField;
    
    @FindBy(xpath = "//*[@id='InstallmentAmount']")
    private WebElement installmentAmountField;
    
    @FindBy(xpath = "//*[@id='LppCharges']")
    private WebElement lppChargesField;
    
    @FindBy(xpath = "//*[@id='ChequeBounceCharges']")
    private WebElement chequeBounceChargesField;
    
    @FindBy(xpath = "//*[@id='RegularWriteOff']")
    private WebElement regularWriteOffDropdown;
    
    @FindBy(xpath = "//*[@id='FDD']")
    private WebElement firstDueDateField;
    
    @FindBy(xpath = "//*[@id='LDD']")
    private WebElement lastDueDateField;
    
    @FindBy(xpath = "//*[@id='FuturePOS']")
    private WebElement futurePOSField;
    
    @FindBy(xpath = "//*[@id='SubmitOnlyAccountEditDetails']")
    private WebElement accountDetailsSaveButton;
    
    // Foreclosure Details
    @FindBy(xpath = "//*[@id='lForeclosure-tab']")
    private WebElement foreclosureTab;
    
    @FindBy(xpath = "//*[@id='CustomerAccountForeclosureDetailsSection']/div/div/div[1]/a")
    private WebElement foreclosureEditButton;
    
    @FindBy(xpath = "//*[@id='EditForeclosureAmount']")
    private WebElement foreclosureAmountField;
    
    @FindBy(xpath = "//*[@id='EditForeclosureCharges']")
    private WebElement foreclosureChargesField;
    
    @FindBy(xpath = "//*[@id='EditForeclosureDate']")
    private WebElement foreclosureDateField;
    
    @FindBy(xpath = "//*[@id='facebox']/div/table/tbody/tr[2]/td[2]/div/form/div[2]/button[1]")
    private WebElement foreclosureSaveButton;
    
    // Concilation Details
    @FindBy(xpath = "//*[@id='lConcilation-tab']")
    private WebElement concilationTab;
    
    @FindBy(xpath = "//*[@id='CustomerAccountConcilationDetailsList']/div/div/div[1]/a")
    private WebElement concilationEditButton;
    
    @FindBy(xpath = "//*[@id='EditCustomerAccountOD']")
    private WebElement odField;
    
    @FindBy(xpath = "//*[@id='EditCustomerAccountAFC']")
    private WebElement afcField;
    
    @FindBy(xpath = "//*[@id='EditCustomerAccountPCLAmount']")
    private WebElement pclAmountField;
    
    @FindBy(xpath = "//*[@id='EditCustomerAccountInterestFromRecallTillDate']")
    private WebElement interestFromRecallField;
    
    @FindBy(xpath = "//*[@id='EditCustomerAccountDisbursalAmount']")
    private WebElement disbursalAmountField;
    
    @FindBy(xpath = "//*[@id='submitconsillationButton']")
    private WebElement concilationSaveButton;
    
    // Present Status
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[4]/div[1]/div")
    private WebElement presentStatusSection;
    
    @FindBy(xpath = "//*[@id='CustomerAccountPrasentStatusSection']/div/div/div[2]/table/tbody/tr/td[5]/a")
    private WebElement presentStatusEditButton;
    
    @FindBy(xpath = "//*[@id='PrasentStatusStatus']")
    private WebElement presentStatusDropdown;
    
    @FindBy(xpath = "//*[@id='CurrentBucket']")
    private WebElement currentBucketField;
    
    @FindBy(xpath = "//*[@id='BOMBucket']")
    private WebElement bomBucketField;
    
    @FindBy(xpath = "//*[@id='BOMStatus']")
    private WebElement bomStatusDropdown;
    
    @FindBy(xpath = "//*[@id='PrasentStatusBucket']")
    private WebElement presentStatusSaveButton;
    
    // Recovery Summary Section
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[5]/div[1]/div")
    private WebElement recoverySummarySection;
    
    // Recovery Summary Applicant - Addition button (add existing applicant)
    @FindBy(xpath = "//*[@id='flush-collapse5']/div/div/div/div[1]/div[2]/a[1]")
    private WebElement addApplicantButton;
    
    @FindBy(xpath = "//*[@id='select2-AddApplicantOnDetail-container']")
    private WebElement applicantSelect2Container;
    
    @FindBy(xpath = "//*[@id='ApplicantType']")
    private WebElement applicantTypeDropdown;
    
    @FindBy(xpath = "//*[@id='AddApplicantSubmitButton']")
    private WebElement applicantSaveButton;
    
    // Recovery Summary Applicant - Add New button (create new contact)
    @FindBy(xpath = "//*[@id='flush-collapse5']/div/div/div/div[1]/div[2]/a[2]")
    private WebElement addNewContactButton;
    
    @FindBy(xpath = "//*[@id='ContactType']")
    private WebElement contactTypeDropdown;
    
    @FindBy(xpath = "//*[@id='Name']")
    private WebElement contactNameField;
    
    @FindBy(xpath = "//*[@id='FatherName']")
    private WebElement fatherNameField;
    
    @FindBy(xpath = "//*[@id='BasicInformationCollapse']")
    private WebElement contactNextButton;
    
    @FindBy(xpath = "//*[@id='btnSubmit']")
    private WebElement contactSaveButton;
    
    // Address Details Section
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[7]/div[1]/div")
    private WebElement addressDetailsSection;
    
    @FindBy(xpath = "//*[@id='flush-collapse6']/div/div/div/div[1]/div[2]/a")
    private WebElement addressAdditionButton;
    
    @FindBy(xpath = "//*[@id='AddressTypeIds']")
    private WebElement addressTypeIdsDropdown;
    
    @FindBy(xpath = "//*[@id='Address']")
    private WebElement addressInputField;
    
    @FindBy(xpath = "//*[@id='LocationIds']")
    private WebElement locationIdsDropdown;
    
    @FindBy(xpath = "//*[@id='AddressButtonSave']")
    private WebElement addressDetailsSaveButton;
    
    // Information Request Section
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[8]/div[1]/div")
    private WebElement infoRequestSection;
    
    @FindBy(xpath = "//*[@id='inforequest']/div/div[1]/div[2]/div/a")
    private WebElement infoRequestAdditionButton;
    
    @FindBy(xpath = "//*[@id='create-informationrequest']/div/div[2]/div[3]/div/span[1]/span[1]/span/ul/li/input")
    private WebElement toUserSearchInput;
    
    @FindBy(xpath = "//*[@id='create-informationrequest']/div/div[2]/div[6]/div/div/div[3]/div[2]")
    private WebElement contentDivEditor;
    
    @FindBy(xpath = "//*[@id='create-informationrequest']/div/div[3]/input[2]")
    private WebElement infoRequestSaveButton;
    
    // Bank Consortium Details Section
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[9]/div[1]/div")
    private WebElement bankConsortiumDetailsSection;
    
    @FindBy(xpath = "//*[@id='flush-collapse8']/div/div/div/div[1]/a")
    private WebElement bankConsortiumAdditionButton;
    
    @FindBy(xpath = "//*[@id='IsBankConsortium']")
    private WebElement bankConsortiumYesRadio;
    
    @FindBy(xpath = "//*[@id='BankConsortiumName-0']")
    private WebElement institutionName1Dropdown;
    
    @FindBy(xpath = "//*[@id='BankConsortiumRole-0']")
    private WebElement roleDropdown;
    
    @FindBy(xpath = "//*[@id='PercentageShare-0']")
    private WebElement shareField;
    
    @FindBy(xpath = "//*[@id='LimitSanctioned-0']")
    private WebElement limitSanctionedField;
    
    @FindBy(xpath = "//*[@id='Comments-0']")
    private WebElement commentsField;
    
    @FindBy(xpath = "//*[@id='facebox']/div/table/tbody/tr[2]/td[2]/div/form/div[2]/button[1]")
    private WebElement bankConsortiumDetailsSaveButton;
    
    // Instrument Details Section  
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[11]/div[1]/div")
    private WebElement instrumentDetailsAccordion;
    
    @FindBy(xpath = "//*[@id='flush-collapse11']/div/div/div/div[1]/a")
    private WebElement instrumentAdditionButton;
    
    @FindBy(xpath = "//*[@id='ChequeNo']")
    private WebElement instrumentNoField;
    
    @FindBy(xpath = "//*[@id='BankId']")
    private WebElement bankNameDropdown;
    
    @FindBy(xpath = "//*[@id='ChequeECSAmount']")
    private WebElement instrumentAmountField;
    
    @FindBy(xpath = "//*[@id='ChequeDate']")
    private WebElement instrumentDatePicker;
    
    @FindBy(xpath = "//*[@id='PresentationStatusId']")
    private WebElement presentationStatusDropdown;
    
    @FindBy(xpath = "//*[@id='submitDetailsBtn']")
    private WebElement instrumentDetailsSaveButton;
    
    // Applicant Section (older section - div[6])
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[6]/div[1]/div")
    private WebElement applicantSection;
    
    // Address Section
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[7]/div[1]/div")
    private WebElement addressSection;
    
    @FindBy(xpath = "//*[@id='CustomerAccountAddressDetailsSection']/div/div/div[2]/div/a")
    private WebElement addAddressButton;
    
    @FindBy(xpath = "//*[@id='AddressTypeList']")
    private WebElement addressTypeDropdown;
    
    @FindBy(xpath = "//*[@id='line_1']")
    private WebElement address1Field;
    
    @FindBy(xpath = "//*[@id='AddressLocation']")
    private WebElement addressLocationDropdown;
    
    @FindBy(xpath = "//*[@id='SubmitAddressDetailsButton']")
    private WebElement addressSaveButton;
    
    // Information Request Section
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[8]/div[1]/div")
    private WebElement informationRequestSection;
    
    @FindBy(xpath = "//*[@id='CustomerAccountInfoRequestSection']/div/div/div[2]/a")
    private WebElement addInformationRequestButton;
    
    @FindBy(xpath = "//*[@id='inforequesttolist']")
    private WebElement toUserSelect2Dropdown;
    
    @FindBy(xpath = "//*[@id='infocontent']")
    private WebElement informationContentField;
    
    @FindBy(xpath = "//*[@id='infoRequestsubmit']")
    private WebElement informationRequestSaveButton;
    
    // Bank Consortium Section
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[11]/div[1]/div")
    private WebElement bankConsortiumSection;
    
    @FindBy(xpath = "//*[@id='CustomerAccountBankConsortiumSection']/div/div/div[1]/a")
    private WebElement bankConsortiumEditButton;
    
    @FindBy(xpath = "//*[@id='editYesConsortium']")
    private WebElement consortiumYesRadio;
    
    @FindBy(xpath = "//*[@id='editYesConsortiumDetails']/div[1]/select")
    private WebElement leadMemberDropdown;
    
    @FindBy(xpath = "//*[@id='editYesConsortiumDetails']/div[2]/select")
    private WebElement consortiumMemberDropdown;
    
    @FindBy(xpath = "//*[@id='EditBankTotalAssets']")
    private WebElement totalAssetsField;
    
    @FindBy(xpath = "//*[@id='EditMemberSecurities']")
    private WebElement memberSecuritiesField;
    
    @FindBy(xpath = "//*[@id='EditMemberShareInSecurities']")
    private WebElement memberShareField;
    
    @FindBy(xpath = "//*[@id='EditBankOutstanding']")
    private WebElement bankOutstandingField;
    
    @FindBy(xpath = "//*[@id='EditMemberOutstanding']")
    private WebElement memberOutstandingField;
    
    @FindBy(xpath = "//*[@id='BankConsortiumSubmit']")
    private WebElement bankConsortiumSaveButton;
    
    // Constructor
    public AccountDetailPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.accountDetailData = new HashMap<>();
        this.random = new Random();
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Get stored account detail data
     */
    public Map<String, String> getAccountDetailData() {
        return new HashMap<>(accountDetailData);
    }
    
    /**
     * Click Edit button
     */
    public void clickEditButton() {
        try {
            waitHelper.waitForElementClickable(editButton);
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", editButton);
            Thread.sleep(1000);
            
            editButton.click();
            System.out.println("✓ Clicked Edit button");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("! Error clicking Edit button: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Select random Account Type
     */
    public void selectAccountType() {
        try {
            waitHelper.waitForElementVisible(accountTypeDropdown);
            Select select = new Select(accountTypeDropdown);
            
            // Get all options except the first one (which is "Select")
            List<WebElement> options = select.getOptions();
            if (options.size() > 1) {
                int randomIndex = random.nextInt(options.size() - 1) + 1;
                select.selectByIndex(randomIndex);
                
                String selectedValue = select.getFirstSelectedOption().getText();
                accountDetailData.put("AccountType", selectedValue);
                System.out.println("✓ Selected Account Type: " + selectedValue);
            }
        } catch (Exception e) {
            System.out.println("! Error selecting Account Type: " + e.getMessage());
        }
    }
    
    /**
     * Select random Parent Account
     */
    public void selectParentAccount() {
        try {
            waitHelper.waitForElementVisible(parentAccountDropdown);
            Select select = new Select(parentAccountDropdown);
            
            List<WebElement> options = select.getOptions();
            if (options.size() > 1) {
                int randomIndex = random.nextInt(options.size() - 1) + 1;
                select.selectByIndex(randomIndex);
                
                String selectedValue = select.getFirstSelectedOption().getText();
                accountDetailData.put("ParentAccount", selectedValue);
                System.out.println("✓ Selected Parent Account: " + selectedValue);
            }
        } catch (Exception e) {
            System.out.println("! Error selecting Parent Account: " + e.getMessage());
        }
    }
    
    /**
     * Get Loan Disbursal Date value
     * Tries multiple approaches to locate the date field
     */
    private LocalDate getLoanDisbursalDate() {
        try {
            // Try to find the element with different possible IDs
            WebElement dateElement = null;
            
            try {
                dateElement = driver.findElement(By.xpath("//*[@id='LoanDisbursalDateEdit']"));
            } catch (Exception e1) {
                try {
                    dateElement = driver.findElement(By.xpath("//*[@id='LoanDisbursalDateCreate']"));
                } catch (Exception e2) {
                    try {
                        dateElement = driver.findElement(By.xpath("//*[@id='LoanDisbursalDate']"));
                    } catch (Exception e3) {
                        System.out.println("! Loan Disbursal Date field not found, using current date as reference");
                        return LocalDate.now();
                    }
                }
            }
            
            if (dateElement != null) {
                String dateValue = dateElement.getAttribute("value");
                
                if (dateValue != null && !dateValue.isEmpty()) {
                    System.out.println("Loan Disbursal Date found: " + dateValue);
                    // Parse date in format dd-MMM-yyyy (e.g., "18-Feb-2026")
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                    return LocalDate.parse(dateValue, formatter);
                }
            }
        } catch (Exception e) {
            System.out.println("! Could not read Loan Disbursal Date: " + e.getMessage());
        }
        
        System.out.println("Using current date as reference for date calculations");
        return LocalDate.now(); // Default to current date
    }
    
    /**
     * Generate random date that is before the reference date
     */
    private String generateDateBefore(LocalDate referenceDate) {
        // Generate a date 30-90 days before the reference date
        int daysBack = 30 + random.nextInt(61); // 30 to 90 days
        LocalDate newDate = referenceDate.minusDays(daysBack);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return newDate.format(formatter);
    }
    
    /**
     * Generate random date that is after the reference date
     */
    private String generateDateAfter(LocalDate referenceDate) {
        // Generate a date 30-180 days after the reference date
        int daysForward = 30 + random.nextInt(151); // 30 to 180 days
        LocalDate newDate = referenceDate.plusDays(daysForward);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return newDate.format(formatter);
    }
    
    /**
     * Generate completely random date
     */
    private String generateRandomDate() {
        int year = 2024 + random.nextInt(3); // 2024-2026
        int month = 1 + random.nextInt(12); // 1-12
        int day = 1 + random.nextInt(28); // 1-28 (safe for all months)
        
        String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", 
                               "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        
        return String.format("%02d-%s-%04d", day, monthNames[month - 1], year);
    }
    
    /**
     * Fill date field using datepicker with retry logic
     */
    private void fillDateField(WebElement field, String date) {
        int maxRetries = 3;
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                // Scroll to element
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", field);
                Thread.sleep(800);
                
                // Check if field is enabled
                boolean isEnabled = field.isEnabled();
                if (!isEnabled) {
                    System.out.println("! Date field is disabled, skipping");
                    return;
                }
                
                // Try JavaScript approach first (more reliable for readonly="readonly" datepickers)
                try {
                    // Remove readonly attribute if present
                    js.executeScript("arguments[0].removeAttribute('readonly');", field);
                    Thread.sleep(200);
                } catch (Exception e) {
                    // Ignore
                }
                
                // Click to focus
                try {
                    js.executeScript("arguments[0].focus();", field);
                    Thread.sleep(300);
                    field.click();
                    Thread.sleep(500);
                } catch (Exception e) {
                    js.executeScript("arguments[0].click();", field);
                    Thread.sleep(500);
                }
                
                // Clear existing value
                try {
                    field.sendKeys(Keys.CONTROL + "a");
                    Thread.sleep(200);
                    field.sendKeys(Keys.BACK_SPACE);
                    Thread.sleep(300);
                } catch (Exception e) {
                    try {
                        js.executeScript("arguments[0].value = '';", field);
                        Thread.sleep(300);
                    } catch (Exception e2) {
                        // Ignore
                    }
                }
                
                // Type the date
                field.sendKeys(date);
                Thread.sleep(700);
                
                // Verify the value was entered
                String enteredValue = field.getAttribute("value");
                if (enteredValue != null && !enteredValue.isEmpty()) {
                    System.out.println("  → Date field filled with: " + enteredValue);
                    // Press Tab to confirm
                    field.sendKeys(Keys.TAB);
                    Thread.sleep(500);
                    return; // Success
                } else {
                    System.out.println("  → Attempt " + attempt + ": Date field appears empty, retrying...");
                    Thread.sleep(1000);
                }
                
            } catch (Exception e) {
                System.out.println("! Error filling date field (attempt " + attempt + "): " + e.getMessage());
                if (attempt == maxRetries) {
                    System.out.println("! Failed to fill date field after " + maxRetries + " attempts");
                }
                try { Thread.sleep(1000); } catch (InterruptedException ie) {}
            }
        }
    }
    
    /**
     * Select random Loan Agreement Date (must be before Disbursal Date)
     */
    public void selectAgreementDate() {
        try {
            LocalDate disbursalDate = getLoanDisbursalDate();
            String agreementDate = generateDateBefore(disbursalDate);
            
            fillDateField(agreementDateField, agreementDate);
            accountDetailData.put("AgreementDate", agreementDate);
            System.out.println("✓ Selected Loan Agreement Date: " + agreementDate);
        } catch (Exception e) {
            System.out.println("! Error selecting Agreement Date: " + e.getMessage());
        }
    }
    
    /**
     * Select random Maturity Date (must be after Disbursal Date)
     */
    public void selectMaturityDate() {
        try {
            LocalDate disbursalDate = getLoanDisbursalDate();
            String maturityDate = generateDateAfter(disbursalDate);
            
            fillDateField(maturityDateField, maturityDate);
            accountDetailData.put("MaturityDate", maturityDate);
            System.out.println("✓ Selected Maturity Date: " + maturityDate);
        } catch (Exception e) {
            System.out.println("! Error selecting Maturity Date: " + e.getMessage());
        }
    }
    
    /**
     * Select random Zone (triggers State dropdown load)
     */
    public void selectZone() {
        try {
            waitHelper.waitForElementVisible(zoneDropdown);
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", zoneDropdown);
            Thread.sleep(800);
            
            Select select = new Select(zoneDropdown);
            List<WebElement> options = select.getOptions();
            
            if (options.size() > 1) {
                int randomIndex = random.nextInt(options.size() - 1) + 1;
                select.selectByIndex(randomIndex);
                Thread.sleep(500);
                
                String selectedValue = select.getFirstSelectedOption().getText();
                accountDetailData.put("Zone", selectedValue);
                System.out.println("✓ Selected Zone: " + selectedValue);
                System.out.println("  Waiting for State dropdown to populate...");
                Thread.sleep(2000); // Wait for state dropdown to populate via AJAX
            }
        } catch (Exception e) {
            System.out.println("! Error selecting Zone: " + e.getMessage());
        }
    }
    
    /**
     * Select random State (triggers Location dropdown load)
     */
    public void selectState() {
        try {
            System.out.println("  Attempting to select State...");
            
            // Click to trigger onchange event from Zone
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", stateDropdown);
            Thread.sleep(1000);
            
            waitHelper.waitForElementVisible(stateDropdown);
            
            // Scroll to element
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", stateDropdown);
            Thread.sleep(800);
            
            Select select = new Select(stateDropdown);
            List<WebElement> options = select.getOptions();
            System.out.println("  State dropdown has " + options.size() + " options");
            
            if (options.size() > 1) {
                int randomIndex = random.nextInt(options.size() - 1) + 1;
                select.selectByIndex(randomIndex);
                Thread.sleep(800);
                
                String selectedValue = select.getFirstSelectedOption().getText();
                accountDetailData.put("State", selectedValue);
                System.out.println("✓ Selected State: " + selectedValue);
                System.out.println("  Waiting for Location dropdown to populate...");
                Thread.sleep(2000); // Wait for location dropdown to populate via AJAX
            } else {
                System.out.println("! State dropdown has no options to select (may not be dependent on Zone)");
            }
        } catch (Exception e) {
            System.out.println("! Error selecting State: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Select random Location (depends on State selection)
     */
    public void selectLocation() {
        try {
            System.out.println("  Waiting for Location dropdown to load...");
            
            // Click to trigger onchange event from State
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", locationDropdown);
            Thread.sleep(1000);
            
            // Extra wait for Location dropdown to populate after State selection
            Thread.sleep(2000);
            
            waitHelper.waitForElementVisible(locationDropdown);
            
            // Scroll to element
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", locationDropdown);
            Thread.sleep(800);
            
            //Verify dropdown has options
            Select select = new Select(locationDropdown);
            List<WebElement> options = select.getOptions();
            System.out.println("  Location dropdown has " + options.size() + " options");
            
            if (options.size() > 1) {
                int randomIndex = random.nextInt(options.size() - 1) + 1;
                select.selectByIndex(randomIndex);
                Thread.sleep(500);
                
                String selectedValue = select.getFirstSelectedOption().getText();
                accountDetailData.put("Location", selectedValue);
                System.out.println("✓ Selected Location: " + selectedValue);
            } else {
                System.out.println("! Location dropdown has no options to select (may not be dependent on State)");
            }
        } catch (Exception e) {
            System.out.println("! Error selecting Location: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Select random Account Status
     */
    public void selectAccountStatus() {
        try {
            waitHelper.waitForElementVisible(accountStatusDropdown);
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", accountStatusDropdown);
            Thread.sleep(500);
            
            Select select = new Select(accountStatusDropdown);
            List<WebElement> options = select.getOptions();
            
            if (options.size() > 0) {
                int randomIndex = random.nextInt(options.size());
                select.selectByIndex(randomIndex);
                
                String selectedValue = select.getFirstSelectedOption().getText();
                accountDetailData.put("AccountStatus", selectedValue);
                System.out.println("✓ Selected Account Status: " + selectedValue);
            }
        } catch (Exception e) {
            System.out.println("! Error selecting Account Status: " + e.getMessage());
        }
    }
    
    /**
     * Select random Business Unit
     */
    public void selectBusinessUnit() {
        try {
            waitHelper.waitForElementVisible(businessUnitDropdown);
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", businessUnitDropdown);
            Thread.sleep(500);
            
            Select select = new Select(businessUnitDropdown);
            List<WebElement> options = select.getOptions();
            
            if (options.size() > 1) {
                int randomIndex = random.nextInt(options.size() - 1) + 1;
                select.selectByIndex(randomIndex);
                
                String selectedValue = select.getFirstSelectedOption().getText();
                accountDetailData.put("BusinessUnit", selectedValue);
                System.out.println("✓ Selected Business Unit: " + selectedValue);
            }
        } catch (Exception e) {
            System.out.println("! Error selecting Business Unit: " + e.getMessage());
        }
    }
    
    /**
     * Select random Upload Date
     */
    public void selectUploadDate() {
        try {
            String uploadDate = generateRandomDate();
            fillDateField(uploadDateField, uploadDate);
            accountDetailData.put("UploadDate", uploadDate);
            System.out.println("✓ Selected Upload Date: " + uploadDate);
        } catch (Exception e) {
            System.out.println("! Error selecting Upload Date: " + e.getMessage());
        }
    }
    
    /**
     * Select random NPA Type
     */
    public void selectNPAType() {
        try {
            waitHelper.waitForElementVisible(npaTypeDropdown);
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", npaTypeDropdown);
            Thread.sleep(500);
            
            Select select = new Select(npaTypeDropdown);
            List<WebElement> options = select.getOptions();
            
            if (options.size() > 1) {
                int randomIndex = random.nextInt(options.size() - 1) + 1;
                select.selectByIndex(randomIndex);
                
                String selectedValue = select.getFirstSelectedOption().getText();
                accountDetailData.put("NPAType", selectedValue);
                System.out.println("✓ Selected NPA Type: " + selectedValue);
            }
        } catch (Exception e) {
            System.out.println("! Error selecting NPA Type: " + e.getMessage());
        }
    }
    
    /**
     * Enter Block Type 1
     */
    public void enterBlockType1() {
        try {
            String blockType1 = RandomDataGenerator.generateAlphanumeric(8);
            
            waitHelper.waitForElementVisible(blockType1Field);
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", blockType1Field);
            Thread.sleep(500);
            
            blockType1Field.clear();
            blockType1Field.sendKeys(blockType1);
            
            accountDetailData.put("BlockType1", blockType1);
            System.out.println("✓ Entered Block Type 1: " + blockType1);
        } catch (Exception e) {
            System.out.println("! Error entering Block Type 1: " + e.getMessage());
        }
    }
    
    /**
     * Select random Date of Block Type 1
     */
    public void selectDateOfBlockType1() {
        try {
            String date = generateRandomDate();
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", dateOfBlockType1Field);
            Thread.sleep(500);
            
            fillDateField(dateOfBlockType1Field, date);
            accountDetailData.put("DateOfBlockType1", date);
            System.out.println("✓ Selected Date of Block Type 1: " + date);
        } catch (Exception e) {
            System.out.println("! Error selecting Date of Block Type 1: " + e.getMessage());
        }
    }
    
    /**
     * Enter Block Type 2
     */
    public void enterBlockType2() {
        try {
            String blockType2 = RandomDataGenerator.generateAlphanumeric(8);
            
            waitHelper.waitForElementVisible(blockType2Field);
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", blockType2Field);
            Thread.sleep(500);
            
            blockType2Field.clear();
            blockType2Field.sendKeys(blockType2);
            
            accountDetailData.put("BlockType2", blockType2);
            System.out.println("✓ Entered Block Type 2: " + blockType2);
        } catch (Exception e) {
            System.out.println("! Error entering Block Type 2: " + e.getMessage());
        }
    }
    
    /**
     * Select random Date of Block Type 2
     */
    public void selectDateOfBlockType2() {
        try {
            String date = generateRandomDate();
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", dateOfBlockType2Field);
            Thread.sleep(500);
            
            fillDateField(dateOfBlockType2Field, date);
            accountDetailData.put("DateOfBlockType2", date);
            System.out.println("✓ Selected Date of Block Type 2: " + date);
        } catch (Exception e) {
            System.out.println("! Error selecting Date of Block Type 2: " + e.getMessage());
        }
    }
    
    /**
     * Select random Non Cooperative Borrower
     */
    public void selectNonCooperativeBorrower() {
        try {
            waitHelper.waitForElementVisible(nonCooperativeBorrowerDropdown);
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", nonCooperativeBorrowerDropdown);
            Thread.sleep(500);
            
            Select select = new Select(nonCooperativeBorrowerDropdown);
            List<WebElement> options = select.getOptions();
            
            if (options.size() > 1) {
                int randomIndex = random.nextInt(options.size() - 1) + 1;
                select.selectByIndex(randomIndex);
                
                String selectedValue = select.getFirstSelectedOption().getText();
                accountDetailData.put("NonCooperativeBorrower", selectedValue);
                System.out.println("✓ Selected Non Cooperative Borrower: " + selectedValue);
            }
        } catch (Exception e) {
            System.out.println("! Error selecting Non Cooperative Borrower: " + e.getMessage());
        }
    }
    
    /**
     * Select random NPA Removal Date
     */
    public void selectNPARemovalDate() {
        try {
            String date = generateRandomDate();
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", npaRemovalDateField);
            Thread.sleep(500);
            
            fillDateField(npaRemovalDateField, date);
            accountDetailData.put("NPARemovalDate", date);
            System.out.println("✓ Selected NPA Removal Date: " + date);
        } catch (Exception e) {
            System.out.println("! Error selecting NPA Removal Date: " + e.getMessage());
        }
    }
    
    /**
     * Select random Business Date
     */
    public void selectBusinessDate() {
        try {
            String date = generateRandomDate();
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", businessDateField);
            Thread.sleep(500);
            
            fillDateField(businessDateField, date);
            accountDetailData.put("BusinessDate", date);
            System.out.println("✓ Selected Business Date: " + date);
        } catch (Exception e) {
            System.out.println("! Error selecting Business Date: " + e.getMessage());
        }
    }
    
    /**
     * Select random Scheme Name
     */
    public void selectSchemeName() {
        try {
            waitHelper.waitForElementVisible(schemeNameDropdown);
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", schemeNameDropdown);
            Thread.sleep(500);
            
            Select select = new Select(schemeNameDropdown);
            List<WebElement> options = select.getOptions();
            
            if (options.size() > 1) {
                int randomIndex = random.nextInt(options.size() - 1) + 1;
                select.selectByIndex(randomIndex);
                
                String selectedValue = select.getFirstSelectedOption().getText();
                accountDetailData.put("SchemeName", selectedValue);
                System.out.println("✓ Selected Scheme Name: " + selectedValue);
            }
        } catch (Exception e) {
            System.out.println("! Error selecting Scheme Name: " + e.getMessage());
        }
    }
    
    /**
     * Select random Date of NPA
     */
    public void selectDateOfNPA() {
        try {
            String date = generateRandomDate();
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", dateOfNPAField);
            Thread.sleep(500);
            
            fillDateField(dateOfNPAField, date);
            accountDetailData.put("DateOfNPA", date);
            System.out.println("✓ Selected Date of NPA: " + date);
        } catch (Exception e) {
            System.out.println("! Error selecting Date of NPA: " + e.getMessage());
        }
    }
    
    /**
     * Click Fraud Radio Button (Yes)
     */
    public void clickFraudYes() {
        try {
            waitHelper.waitForElementClickable(fraudYesRadio);
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", fraudYesRadio);
            Thread.sleep(500);
            
            fraudYesRadio.click();
            accountDetailData.put("Fraud", "Yes");
            System.out.println("✓ Selected Fraud: Yes");
        } catch (Exception e) {
            System.out.println("! Error clicking Fraud Yes: " + e.getMessage());
        }
    }
    
    /**
     * Click Save button
     */
    public void clickSaveButton() {
        try {
            waitHelper.waitForElementClickable(saveButton);
            
            // Scroll to element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", saveButton);
            Thread.sleep(1000);
            
            saveButton.click();
            System.out.println("✓ Clicked Save button");
            Thread.sleep(2000);
            
            // Handle any alert/popup that might appear after save
            try {
                driver.switchTo().alert().accept();
                System.out.println("✓ Accepted alert after save");
                Thread.sleep(1000);
            } catch (Exception alertEx) {
                // No alert present, continue
            }
            
            // Scroll page back to top for next sections
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1500);
            System.out.println("✓ Page scrolled to top, ready for next sections");
            
            // Additional wait for page to stabilize after save
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("! Error clicking Save button: " + e.getMessage());
        }
    }
    
    /**
     * Fill all account detail fields with random data
     */
    public void fillAccountDetailsForm() {
        System.out.println("\n========================================");
        System.out.println("Starting to fill Account Detail form...");
        System.out.println("========================================\n");
        
        clickEditButton();
        
        selectAccountType();
        selectParentAccount();
        selectAgreementDate();
        selectMaturityDate();
        selectZone();
        selectState();
        selectLocation();
        selectAccountStatus();
        selectBusinessUnit();
        selectUploadDate();
        selectNPAType();
        enterBlockType1();
        selectDateOfBlockType1();
        enterBlockType2();
        selectDateOfBlockType2();
        selectNonCooperativeBorrower();
        selectNPARemovalDate();
        selectBusinessDate();
        selectSchemeName();
        selectDateOfNPA();
        clickFraudYes();
        
        System.out.println("\n========================================");
        System.out.println("✓ Account Detail form filled successfully");
        System.out.println("========================================\n");
    }
    
    /**
     * Complete account detail update (fill and save)
     */
    public void completeAccountDetailUpdate() {
        fillAccountDetailsForm();
        clickSaveButton();
        
        System.out.println("\n========================================");
        System.out.println("✓ Account Detail update completed successfully");
        System.out.println("========================================\n");
    }
    
    // ==================== Additional Sections Methods ====================
    
    /**
     * Complete Bad Debt section
     */
    public void completeBadDebtSection() {
        try {
            System.out.println("\n=== Starting Bad Debt Section ===");
            
            // Ensure page is ready
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Thread.sleep(2000);
            
            // Click Bad Debt section to expand accordion
            try {
                waitHelper.waitForElementVisible(badDebtSection);
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", badDebtSection);
                Thread.sleep(800);
                
                // Try JavaScript click first for accordion
                js.executeScript("arguments[0].click();", badDebtSection);
                System.out.println("✓ Clicked Bad Debt section");
                Thread.sleep(2000); // Wait for accordion to expand
            } catch (Exception e) {
                System.out.println("! Error clicking Bad Debt section: " + e.getMessage());
                throw e;
            }
            
            // Click Edit button
            try {
                System.out.println("  Waiting for Edit button to be visible...");
                waitHelper.waitForElementVisible(badDebtEditButton);
                waitHelper.waitForElementClickable(badDebtEditButton);
                
                // Try regular click first
                try {
                    badDebtEditButton.click();
                } catch (Exception e) {
                    // Fallback to JavaScript click
                    System.out.println("  Using JavaScript click for Edit button");
                    js.executeScript("arguments[0].click();", badDebtEditButton);
                }
                System.out.println("✓ Clicked Edit button");
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println("! Error clicking Edit button: " + e.getMessage());
                e.printStackTrace();
                throw e;
            }
            
            // Select NOC Status Yes
            try {
                waitHelper.waitForElementClickable(nocStatusYesRadio);
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", nocStatusYesRadio);
                Thread.sleep(500);
                nocStatusYesRadio.click();
                System.out.println("✓ Selected NOC Status: Yes");
            } catch (Exception e) {
                System.out.println("! Error selecting NOC Status: " + e.getMessage());
                throw e;
            }
            
            // Select Bad Debt Yes
            try {
                waitHelper.waitForElementClickable(badDebtYesRadio);
                badDebtYesRadio.click();
                System.out.println("✓ Selected Bad Debt: Yes");
            } catch (Exception e) {
                System.out.println("! Error selecting Bad Debt: " + e.getMessage());
                throw e;
            }
            
            // Click Save
            try {
                Thread.sleep(500);
                badDebtSaveButton.click();
                System.out.println("✓ Bad Debt section saved");
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("! Error clicking Save button: " + e.getMessage());
                throw e;
            }
            
        } catch (Exception e) {
            System.out.println("! Error in Bad Debt section: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Complete Account Details Extended section
     */
    public void completeAccountDetailsExtended() {
        try {
            System.out.println("\n=== Starting Account Details (Extended) Section ===");
            Thread.sleep(1500);
            
            // Click Account Details section
            JavascriptExecutor js = (JavascriptExecutor) driver;
            waitHelper.waitForElementVisible(accountDetailsSection);
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", accountDetailsSection);
            Thread.sleep(800);
            js.executeScript("arguments[0].click();", accountDetailsSection);
            System.out.println("✓ Clicked Account Details section");
            Thread.sleep(2000); // Wait for accordion to expand
            
            // Click Edit button
            try {
                waitHelper.waitForElementVisible(accountDetailsEditButton);
                waitHelper.waitForElementClickable(accountDetailsEditButton);
                try {
                    accountDetailsEditButton.click();
                } catch (Exception e) {
                    js.executeScript("arguments[0].click();", accountDetailsEditButton);
                }
                System.out.println("✓ Clicked Edit button");
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println("! Error clicking Edit button: " + e.getMessage());
                throw e;
            }
            
            // Fill numeric fields
            String principalAmount = String.valueOf(RandomDataGenerator.generateRandomNumber(100000, 9999999));
            principalAmountField.clear();
            principalAmountField.sendKeys(principalAmount);
            System.out.println("✓ Entered Principal Amount: " + principalAmount);
            
            String roi = String.valueOf(RandomDataGenerator.generateRandomNumber(5, 15));
            rateOfInterestField.clear();
            rateOfInterestField.sendKeys(roi);
            System.out.println("✓ Entered Rate of Interest: " + roi);
            
            String interestAmount = String.valueOf(RandomDataGenerator.generateRandomNumber(10000, 999999));
            interestAmountField.clear();
            interestAmountField.sendKeys(interestAmount);
            System.out.println("✓ Entered Interest Amount: " + interestAmount);
            
            String interestOverdue = String.valueOf(RandomDataGenerator.generateRandomNumber(5000, 500000));
            interestOverdueField.clear();
            interestOverdueField.sendKeys(interestOverdue);
            System.out.println("✓ Entered Interest Overdue: " + interestOverdue);
            
            String emiAmount = String.valueOf(RandomDataGenerator.generateRandomNumber(5000, 50000));
            emiAmountField.clear();
            emiAmountField.sendKeys(emiAmount);
            System.out.println("✓ Entered EMI Amount: " + emiAmount);
            
            String emiOutstanding = String.valueOf(RandomDataGenerator.generateRandomNumber(50000, 500000));
            emiOutstandingField.clear();
            emiOutstandingField.sendKeys(emiOutstanding);
            System.out.println("✓ Entered EMI Outstanding: " + emiOutstanding);
            
            String pos = String.valueOf(RandomDataGenerator.generateRandomNumber(100000, 999999));
            posOnLastMonthEndField.clear();
            posOnLastMonthEndField.sendKeys(pos);
            System.out.println("✓ Entered POS On Last Month End: " + pos);
            
            // Select Repayment Mode
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", repaymentModeDropdown);
            Thread.sleep(500);
            Select repaymentModeSelect = new Select(repaymentModeDropdown);
            List<WebElement> modes = repaymentModeSelect.getOptions();
            if (modes.size() > 1) {
                int randomModeIndex = 1 + random.nextInt(modes.size() - 1);
                repaymentModeSelect.selectByIndex(randomModeIndex);
                System.out.println("✓ Selected Repayment Mode: " + modes.get(randomModeIndex).getText());
            }
            
            String outstandingBalance = String.valueOf(RandomDataGenerator.generateRandomNumber(100000, 9999999));
            outstandingBalanceField.clear();
            outstandingBalanceField.sendKeys(outstandingBalance);
            System.out.println("✓ Entered Outstanding Balance: " + outstandingBalance);
            
            String penalROI = String.valueOf(RandomDataGenerator.generateRandomNumber(1, 5));
            penalROIField.clear();
            penalROIField.sendKeys(penalROI);
            System.out.println("✓ Entered Penal ROI: " + penalROI);
            
            String penaltyOverdue = String.valueOf(RandomDataGenerator.generateRandomNumber(1000, 50000));
            penaltyOverdueField.clear();
            penaltyOverdueField.sendKeys(penaltyOverdue);
            System.out.println("✓ Entered Penalty Overdue: " + penaltyOverdue);
            
            String tenure = String.valueOf(RandomDataGenerator.generateRandomNumber(12, 360));
            tenureField.clear();
            tenureField.sendKeys(tenure);
            System.out.println("✓ Entered Tenure: " + tenure);
            
            String installmentOverdue = String.valueOf(RandomDataGenerator.generateRandomNumber(1000, 100000));
            installmentOverdueField.clear();
            installmentOverdueField.sendKeys(installmentOverdue);
            System.out.println("✓ Entered Installment Overdue: " + installmentOverdue);
            
            String receivable = String.valueOf(RandomDataGenerator.generateRandomNumber(10000, 999999));
            receivableField.clear();
            receivableField.sendKeys(receivable);
            System.out.println("✓ Entered Receivable: " + receivable);
            
            String installmentAmount = String.valueOf(RandomDataGenerator.generateRandomNumber(5000, 50000));
            installmentAmountField.clear();
            installmentAmountField.sendKeys(installmentAmount);
            System.out.println("✓ Entered Installment Amount: " + installmentAmount);
            
            String lppCharges = String.valueOf(RandomDataGenerator.generateRandomNumber(100, 10000));
            lppChargesField.clear();
            lppChargesField.sendKeys(lppCharges);
            System.out.println("✓ Entered LPP Charges: " + lppCharges);
            
            String chequeBounce = String.valueOf(RandomDataGenerator.generateRandomNumber(100, 5000));
            chequeBounceChargesField.clear();
            chequeBounceChargesField.sendKeys(chequeBounce);
            System.out.println("✓ Entered Cheque Bounce Charges: " + chequeBounce);
            
            // Select Regular Write Off
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", regularWriteOffDropdown);
            Thread.sleep(500);
            Select regularWriteOffSelect = new Select(regularWriteOffDropdown);
            List<WebElement> writeOffOptions = regularWriteOffSelect.getOptions();
            if (writeOffOptions.size() > 1) {
                int randomIndex = 1 + random.nextInt(writeOffOptions.size() - 1);
                regularWriteOffSelect.selectByIndex(randomIndex);
                System.out.println("✓ Selected Regular Write Off: " + writeOffOptions.get(randomIndex).getText());
            }
            
            // Fill date fields
            String fddDate = generateRandomDate();
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", firstDueDateField);
            Thread.sleep(500);
            fillDateField(firstDueDateField, fddDate);
            System.out.println("✓ Selected First Due Date: " + fddDate);
            
            String lddDate = generateRandomDate();
            fillDateField(lastDueDateField, lddDate);
            System.out.println("✓ Selected Last Due Date: " + lddDate);
            
            String futurePos = String.valueOf(RandomDataGenerator.generateRandomNumber(100000, 9999999));
            futurePOSField.clear();
            futurePOSField.sendKeys(futurePos);
            System.out.println("✓ Entered Future POS: " + futurePos);
            
            // Click Save
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", accountDetailsSaveButton);
            Thread.sleep(500);
            accountDetailsSaveButton.click();
            System.out.println("✓ Account Details (Extended) saved");
            Thread.sleep(1500);
            
        } catch (Exception e) {
            System.out.println("! Error in Account Details Extended: " + e.getMessage());
        }
    }
    
    /**
     * Complete Foreclosure Details section
     */
    public void completeForeclosureDetails() {
        try {
            System.out.println("\n=== Starting Foreclosure Details Section ===");
            Thread.sleep(2000);
            
            // Re-open Account Details accordion first (Foreclosure tab is inside it)
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);
            
            // Ensure Account Details accordion is expanded
            try {
                waitHelper.waitForElementVisible(accountDetailsSection);
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", accountDetailsSection);
                Thread.sleep(800);
                js.executeScript("arguments[0].click();", accountDetailsSection);
                System.out.println("✓ Re-opened Account Details accordion");
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("! Warning: Could not re-open Account Details accordion: " + e.getMessage());
            }
            
            // Now click Foreclosure tab
            waitHelper.waitForElementVisible(foreclosureTab);
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", foreclosureTab);
            Thread.sleep(800);
            js.executeScript("arguments[0].click();", foreclosureTab);
            System.out.println("✓ Clicked Foreclosure tab");
            Thread.sleep(2000);
            
            // Click Edit button
            try {
                waitHelper.waitForElementVisible(foreclosureEditButton);
                waitHelper.waitForElementClickable(foreclosureEditButton);
                try {
                    foreclosureEditButton.click();
                } catch (Exception e) {
                    js.executeScript("arguments[0].click();", foreclosureEditButton);
                }
                System.out.println("✓ Clicked Edit button");
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println("! Error clicking Edit button: " + e.getMessage());
                throw e;
            }
            
            // Fill Foreclosure Amount
            String foreclosureAmount = String.valueOf(RandomDataGenerator.generateRandomNumber(100000, 9999999));
            foreclosureAmountField.clear();
            foreclosureAmountField.sendKeys(foreclosureAmount);
            System.out.println("✓ Entered Foreclosure Amount: " + foreclosureAmount);
            
            // Fill Foreclosure Charges
            String foreclosureCharges = String.valueOf(RandomDataGenerator.generateRandomNumber(1000, 50000));
            foreclosureChargesField.clear();
            foreclosureChargesField.sendKeys(foreclosureCharges);
            System.out.println("✓ Entered Foreclosure Charges: " + foreclosureCharges);
            
            // Fill Foreclosure Date
            String foreclosureDate = generateRandomDate();
            fillDateField(foreclosureDateField, foreclosureDate);
            System.out.println("✓ Selected Foreclosure Date: " + foreclosureDate);
            
            // Click Save
            try {
                foreclosureSaveButton.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", foreclosureSaveButton);
            }
            System.out.println("✓ Foreclosure Details saved");
            Thread.sleep(1500);
            
        } catch (Exception e) {
            System.out.println("! Error in Foreclosure Details: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Complete Concilation Details section
     */
    public void completeConcilationDetails() {
        try {
            System.out.println("\n=== Starting Concilation Details Section ===");
            Thread.sleep(2000);
            
            // Re-open Account Details accordion first (Concilation tab is inside it)
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);
            
            // Ensure Account Details accordion is expanded
            try {
                waitHelper.waitForElementVisible(accountDetailsSection);
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", accountDetailsSection);
                Thread.sleep(800);
                js.executeScript("arguments[0].click();", accountDetailsSection);
                System.out.println("✓ Re-opened Account Details accordion for Concilation");
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("! Warning: Could not re-open Account Details accordion: " + e.getMessage());
            }
            
            // Now click Concilation tab
            waitHelper.waitForElementVisible(concilationTab);
            waitHelper.waitForElementClickable(concilationTab);
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", concilationTab);
            Thread.sleep(1000);
            
            // Try regular click first, then JavaScript as fallback
            try {
                concilationTab.click();
                System.out.println("✓ Clicked Concilation tab (regular click)");
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", concilationTab);
                System.out.println("✓ Clicked Concilation tab (JavaScript click)");
            }
            Thread.sleep(2000);
            
            // Click Edit button
            try {
                waitHelper.waitForElementVisible(concilationEditButton);
                waitHelper.waitForElementClickable(concilationEditButton);
                try {
                    concilationEditButton.click();
                } catch (Exception e) {
                    js.executeScript("arguments[0].click();", concilationEditButton);
                }
                System.out.println("✓ Clicked Concilation Edit button");
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println("! Error clicking Concilation Edit button: " + e.getMessage());
                throw e;
            }
            
            // Wait for tab content to load - try to find OD field
            System.out.println("  Waiting for Concilation form fields to appear...");
            try {
                // Wait explicitly for OD field to be visible (30 seconds)
                waitHelper.waitForElementVisible(odField);
                System.out.println("  ✓ Form fields loaded");
                
                // Scroll OD field into view
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", odField);
                Thread.sleep(500);
                
                // Now fill all fields using Selenium
                String od = String.valueOf(RandomDataGenerator.generateRandomNumber(10000, 999999));
                odField.clear();
                odField.sendKeys(od);
                System.out.println("✓ Entered OD: " + od);
                Thread.sleep(300);
                
                String afc = String.valueOf(RandomDataGenerator.generateRandomNumber(5000, 500000));
                afcField.clear();
                afcField.sendKeys(afc);
                System.out.println("✓ Entered AFC: " + afc);
                Thread.sleep(300);
                
                String pclAmount = String.valueOf(RandomDataGenerator.generateRandomNumber(10000, 999999));
                pclAmountField.clear();
                pclAmountField.sendKeys(pclAmount);
                System.out.println("✓ Entered PCL Amount: " + pclAmount);
                Thread.sleep(300);
                
                String interestFromRecall = String.valueOf(RandomDataGenerator.generateRandomNumber(5000, 500000));
                interestFromRecallField.clear();
                interestFromRecallField.sendKeys(interestFromRecall);
                System.out.println("✓ Entered Interest From Recall Till Date: " + interestFromRecall);
                Thread.sleep(300);
                
                String disbursalAmount = String.valueOf(RandomDataGenerator.generateRandomNumber(100000, 9999999));
                disbursalAmountField.clear();
                disbursalAmountField.sendKeys(disbursalAmount);
                System.out.println("✓ Entered Disbursal Amount: " + disbursalAmount);
                Thread.sleep(500);
                
                // Click Save button
                waitHelper.waitForElementVisible(concilationSaveButton);
                waitHelper.waitForElementClickable(concilationSaveButton);
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", concilationSaveButton);
                Thread.sleep(500);
                try {
                    concilationSaveButton.click();
                } catch (Exception e) {
                    js.executeScript("arguments[0].click();", concilationSaveButton);
                }
                System.out.println("✓ Concilation Details saved");
                Thread.sleep(1500);
                
            } catch (Exception fieldError) {
                System.out.println("! Concilation form fields not available (may not exist for this account type)");
                System.out.println("  " + fieldError.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("! Error in Concilation Details: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Complete Present Status section
     */
    public void completePresentStatus() {
        try {
            System.out.println("\n=== Starting Present Status Section ===");
            Thread.sleep(1500);
            
            // Click Present Status section
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);
            waitHelper.waitForElementVisible(presentStatusSection);
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", presentStatusSection);
            Thread.sleep(800);
            js.executeScript("arguments[0].click();", presentStatusSection);
            System.out.println("✓ Clicked Present Status section");
            Thread.sleep(2000);
            
            // Click Edit button
            try {
                waitHelper.waitForElementVisible(presentStatusEditButton);
                waitHelper.waitForElementClickable(presentStatusEditButton);
                try {
                    presentStatusEditButton.click();
                } catch (Exception e) {
                    js.executeScript("arguments[0].click();", presentStatusEditButton);
                }
                System.out.println("✓ Clicked Edit button");
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println("! Error clicking Edit button: " + e.getMessage());
                throw e;
            }
            
            // Select Present Status
            Select presentStatusSelect = new Select(presentStatusDropdown);
            List<WebElement> statusOptions = presentStatusSelect.getOptions();
            if (statusOptions.size() > 1) {
                int randomIndex = 1 + random.nextInt(statusOptions.size() - 1);
                presentStatusSelect.selectByIndex(randomIndex);
                System.out.println("✓ Selected Present Status: " + statusOptions.get(randomIndex).getText());
            }
            
            // Fill Current Bucket
            String currentBucket = String.valueOf(RandomDataGenerator.generateRandomNumber(1, 12));
            currentBucketField.clear();
            currentBucketField.sendKeys(currentBucket);
            System.out.println("✓ Entered Current Bucket: " + currentBucket);
            
            // Fill BOM Bucket
            String bomBucket = String.valueOf(RandomDataGenerator.generateRandomNumber(1, 12));
            bomBucketField.clear();
            bomBucketField.sendKeys(bomBucket);
            System.out.println("✓ Entered BOM Bucket: " + bomBucket);
            
            // Select BOM Status
            Select bomStatusSelect = new Select(bomStatusDropdown);
            List<WebElement> bomStatusOptions = bomStatusSelect.getOptions();
            if (bomStatusOptions.size() > 1) {
                int randomIndex = 1 + random.nextInt(bomStatusOptions.size() - 1);
                bomStatusSelect.selectByIndex(randomIndex);
                System.out.println("✓ Selected BOM Status: " + bomStatusOptions.get(randomIndex).getText());
            }
            
            // Click Save
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", presentStatusSaveButton);
            Thread.sleep(500);
            presentStatusSaveButton.click();
            System.out.println("✓ Present Status saved");
            Thread.sleep(1500);
            
        } catch (Exception e) {
            System.out.println("! Error in Present Status: " + e.getMessage());
        }
    }
    
    /**
     * Complete Recovery Summary Applicant addition
     */
    public void completeRecoverySummaryApplicant() {
        try {
            System.out.println("\n=== Starting Recovery Summary Applicant Section ===");
            Thread.sleep(1500);
            
            // Click Recovery Summary section (Applicant parent section)
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);
            waitHelper.waitForElementVisible(recoverySummarySection);
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", recoverySummarySection);
            Thread.sleep(800);
            js.executeScript("arguments[0].click();", recoverySummarySection);
            System.out.println("✓ Clicked Recovery Summary section (Applicant)");
            Thread.sleep(3000); // Wait for accordion expansion
            
            // Scroll down to ensure content is visible
            js.executeScript("window.scrollBy(0, 300);");
            Thread.sleep(2000);
            
            // Click Addition button using JavaScript directly
            System.out.println("  Looking for Addition button...");
            try {
                // Try to find and click the button using JavaScript
                String jsClickButton = "var addBtn = document.evaluate(\"//*[@id='flush-collapse5']/div/div/div/div[1]/div[2]/a[1]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue; " +
                                      "if (addBtn) { addBtn.scrollIntoView({behavior: 'smooth', block: 'center'}); return true; } else { return false; }";
                
                Boolean buttonFound = (Boolean) js.executeScript(jsClickButton);
                
                if (buttonFound) {
                    Thread.sleep(500);
                    // Now click it
                    js.executeScript("document.evaluate(\"//*[@id='flush-collapse5']/div/div/div/div[1]/div[2]/a[1]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();");
                    System.out.println("✓ Clicked Addition button (JavaScript direct)");
                    Thread.sleep(2000); // Wait for form to load
                } else {
                    System.out.println("! Addition button not found in DOM, trying Selenium approach...");
                    
                    // Fallback to Selenium
                    waitHelper.waitForElementVisible(addApplicantButton);
                    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", addApplicantButton);
                    Thread.sleep(1000);
                    waitHelper.waitForElementClickable(addApplicantButton);
                    try {
                        addApplicantButton.click();
                        System.out.println("✓ Clicked Addition button (Selenium click)");
                    } catch (Exception e) {
                        js.executeScript("arguments[0].click();", addApplicantButton);
                        System.out.println("✓ Clicked Addition button (JavaScript element click)");
                    }
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                System.out.println("! Error clicking Addition button: " + e.getMessage());
                e.printStackTrace();
                throw e;
            }
            
            // Click Select2 to open dropdown - try multiple approaches
            waitHelper.waitForElementVisible(applicantSelect2Container);
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", applicantSelect2Container);
            Thread.sleep(500);
            
            // Try to find and click the parent select2-selection element
            WebElement select2Selection = null;
            try {
                // Find the parent select2-selection element (the actual clickable container)
                select2Selection = driver.findElement(By.xpath("//span[contains(@class, 'select2-selection') and .//span[@id='select2-AddApplicantOnDetail-container']]"));
                System.out.println("  Found parent select2-selection element");
            } catch (Exception e) {
                // Fallback to clicking the container itself
                select2Selection = applicantSelect2Container;
                System.out.println("  Using select2-container element directly");
            }
            
            // First click on the selection element
            js.executeScript("arguments[0].click();", select2Selection);
            System.out.println("✓ Clicked Select2 selection (first click)");
            Thread.sleep(1500);
            
            // Second click to ensure dropdown opens
            js.executeScript("arguments[0].click();", select2Selection);
            System.out.println("✓ Clicked Select2 selection (second click)");
            Thread.sleep(1500);
            
            // If still not open, try triggering mousedown event which Select2 listens for
            try {
                String openScript = 
                    "var element = arguments[0];" +
                    "var event = new MouseEvent('mousedown', {" +
                    "  bubbles: true," +
                    "  cancelable: true," +
                    "  view: window" +
                    "});" +
                    "element.dispatchEvent(event);";
                js.executeScript(openScript, select2Selection);
                System.out.println("✓ Triggered mousedown event on Select2");
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println("  Could not trigger mousedown event: " + e.getMessage());
            }
            
            // Wait for dropdown to appear and populate
            try {
                System.out.println("  Waiting for Select2 dropdown to open and populate...");
                
                // Check if dropdown container appeared
                boolean dropdownFound = false;
                for (int i = 0; i < 10; i++) {
                    List<WebElement> dropdown = driver.findElements(By.xpath("//span[contains(@class, 'select2-dropdown')]"));
                    if (dropdown.size() > 0) {
                        System.out.println("  ✓ Select2 dropdown container found");
                        dropdownFound = true;
                        break;
                    }
                    Thread.sleep(500);
                }
                
                if (!dropdownFound) {
                    System.out.println("  ! Select2 dropdown container not found after 5 seconds");
                }
                
                // Wait a bit more for options to load via AJAX
                Thread.sleep(2000);
                
            } catch (Exception e) {
                System.out.println("  ! Error waiting for dropdown: " + e.getMessage());
            }
            
            // Try to find and click options directly from the dropdown
            try {
                System.out.println("  Looking for applicant options in dropdown...");
                
                // Print all list items for debugging
                List<WebElement> allLi = driver.findElements(By.xpath("//li"));
                System.out.println("  DEBUG: Found " + allLi.size() + " total <li> elements in page");
                
                // Try finding options with all possible patterns
                List<WebElement> applicantOptions = new ArrayList<>();
                
                // Pattern 1: By specific results UL ID
                applicantOptions = driver.findElements(By.xpath("//ul[@id='select2-AddApplicantOnDetail-results']/li"));
                System.out.println("  Pattern 1 (ul#select2-AddApplicantOnDetail-results/li): " + applicantOptions.size() + " elements");
                
                if (applicantOptions.size() == 0) {
                    // Pattern 2: By role attribute
                    applicantOptions = driver.findElements(By.xpath("//li[@role='option']"));
                    System.out.println("  Pattern 2 (li[@role='option']): " + applicantOptions.size() + " elements");
                }
                
                if (applicantOptions.size() == 0) {
                    // Pattern 3: By select2-results class
                    applicantOptions = driver.findElements(By.xpath("//li[contains(@class, 'select2-results__option')]"));
                    System.out.println("  Pattern 3 (li with select2-results__option class): " + applicantOptions.size() + " elements");
                }
                
                if (applicantOptions.size() == 0) {
                    // Pattern 4: CSS selector
                    applicantOptions = driver.findElements(By.cssSelector(".select2-results__option"));
                    System.out.println("  Pattern 4 (CSS .select2-results__option): " + applicantOptions.size() + " elements");
                }
                
                if (applicantOptions.size() == 0) {
                    // Pattern 5: More specific with select2-results container
                    applicantOptions = driver.findElements(By.xpath("//ul[contains(@class, 'select2-results__options')]/li"));
                    System.out.println("  Pattern 5 (ul.select2-results__options/li): " + applicantOptions.size() + " elements");
                }
                
                // Filter out any loading or no results messages
                List<WebElement> validOptions = new ArrayList<>();
                for (WebElement option : applicantOptions) {
                    String text = option.getText().trim();
                    String classes = option.getAttribute("class");
                    System.out.println("  Found option - Text: '" + text + "', Classes: '" + classes + "'");
                    
                    if (!text.isEmpty() && 
                        !text.contains("Loading") && 
                        !text.contains("No results") && 
                        !text.contains("Searching") &&
                        !text.equalsIgnoreCase("Select") &&
                        !classes.contains("loading")) {
                        validOptions.add(option);
                    }
                }
                
                if (validOptions.size() > 0) {
                    System.out.println("  ✓ Found " + validOptions.size() + " valid applicant options");
                    int randomIndex = random.nextInt(validOptions.size());
                    WebElement selectedOption = validOptions.get(randomIndex);
                    String applicantName = selectedOption.getText();
                    
                    // Scroll option into view and click
                    js.executeScript("arguments[0].scrollIntoView({block: 'nearest'});", selectedOption);
                    Thread.sleep(300);
                    js.executeScript("arguments[0].click();", selectedOption);
                    System.out.println("✓ Selected Applicant: " + applicantName);
                    Thread.sleep(500);
                    
                    // Hit Enter to confirm (if needed)
                    try {
                        Actions actions = new Actions(driver);
                        actions.sendKeys(Keys.ENTER).perform();
                        System.out.println("✓ Pressed Enter to confirm");
                    } catch (Exception e) {
                        System.out.println("  Selection already confirmed by click");
                    }
                    Thread.sleep(1000);
                } else {
                    System.out.println("! No valid applicant options found - dropdown may be empty");
                    System.out.println("  This is normal if no applicants exist in the system yet");
                }
                
            } catch (Exception e) {
                System.out.println("! Error finding applicant options: " + e.getMessage());
            }
            Thread.sleep(1500);
            
            // Select random Applicant Type from dropdown
            waitHelper.waitForElementVisible(applicantTypeDropdown);
            Select applicantTypeSelect = new Select(applicantTypeDropdown);
            List<WebElement> typeOptions = applicantTypeSelect.getOptions();
            if (typeOptions.size() > 1) {
                // Skip first option (Select) and choose random from remaining
                int randomIndex = 1 + random.nextInt(typeOptions.size() - 1);
                applicantTypeSelect.selectByIndex(randomIndex);
                System.out.println("✓ Selected Applicant Type: " + typeOptions.get(randomIndex).getText());
            }
            Thread.sleep(1000);
            
            // Click Save
            try {
                waitHelper.waitForElementVisible(applicantSaveButton);
                Thread.sleep(1000);
                // Try JavaScript click directly
                js.executeScript("arguments[0].click();", applicantSaveButton);
                System.out.println("✓ Recovery Summary Applicant saved");
            } catch (Exception e) {
                System.out.println("! Error clicking Save button: " + e.getMessage());
                // Try clicking with JavaScript using XPath
                try {
                    js.executeScript("document.querySelector('#AddApplicantSubmitButton').click();");
                    System.out.println("✓ Recovery Summary Applicant saved (CSS selector)");
                } catch (Exception ex) {
                    System.out.println("! Could not click Save button");
                }
            }
            Thread.sleep(1500);
            
        } catch (Exception e) {
            System.out.println("! Error in Recovery Summary Applicant: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Add new contact in Recovery Summary Applicant section
     */
    public void addNewApplicantContact() {
        try {
            System.out.println("\n=== Starting Add New Contact Section ===");
            Thread.sleep(1500);
            
            // Click Recovery Summary section (same as Applicant section)
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);
            waitHelper.waitForElementVisible(recoverySummarySection);
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", recoverySummarySection);
            Thread.sleep(800);
            js.executeScript("arguments[0].click();", recoverySummarySection);
            System.out.println("✓ Clicked Recovery Summary section (Add New)");
            Thread.sleep(3000);
            
            // Scroll down to ensure content is visible
            js.executeScript("window.scrollBy(0, 300);");
            Thread.sleep(2000);
            
            // Click Add New button using JavaScript
            System.out.println("  Looking for Add New button...");
            try {
                String jsClickButton = "var addNewBtn = document.evaluate(\"//*[@id='flush-collapse5']/div/div/div/div[1]/div[2]/a[2]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue; " +
                                      "if (addNewBtn) { addNewBtn.scrollIntoView({behavior: 'smooth', block: 'center'}); return true; } else { return false; }";
                
                Boolean buttonFound = (Boolean) js.executeScript(jsClickButton);
                
                if (buttonFound) {
                    Thread.sleep(500);
                    js.executeScript("document.evaluate(\"//*[@id='flush-collapse5']/div/div/div/div[1]/div[2]/a[2]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();");
                    System.out.println("✓ Clicked Add New button (JavaScript direct)");
                    Thread.sleep(2000);
                } else {
                    System.out.println("! Add New button not found in DOM, trying Selenium approach...");
                    waitHelper.waitForElementVisible(addNewContactButton);
                    js.executeScript("arguments[0].click();", addNewContactButton);
                    System.out.println("✓ Clicked Add New button (Selenium)");
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                System.out.println("! Error clicking Add New button: " + e.getMessage());
                e.printStackTrace();
                throw e;
            }
            
            // Select random Contact Type
            try {
                System.out.println("  Waiting for Contact Type dropdown to load...");
                waitHelper.waitForElementVisible(contactTypeDropdown);
                Thread.sleep(2000); // Initial wait for modal/form to fully render
                
                // Click the dropdown to trigger any onclick handlers that might populate it
                System.out.println("  Clicking dropdown to trigger population...");
                js.executeScript("arguments[0].click();", contactTypeDropdown);
                Thread.sleep(1000);
                
                // Also try triggering focus and mousedown events
                js.executeScript("arguments[0].focus();", contactTypeDropdown);
                js.executeScript("arguments[0].dispatchEvent(new MouseEvent('mousedown', {bubbles: true}));", contactTypeDropdown);
                Thread.sleep(1000);
                
                // Wait for dropdown to populate with options (may load via AJAX)
                Select contactTypeSelect = new Select(contactTypeDropdown);
                int attempts = 0;
                int maxAttempts = 15;
                List<WebElement> contactTypes = null;
                
                while (attempts < maxAttempts) {
                    contactTypes = contactTypeSelect.getOptions();
                    System.out.println("  Attempt " + (attempts + 1) + ": Found " + contactTypes.size() + " contact type options");
                    
                    if (contactTypes.size() > 1) {
                        // Options have loaded
                        break;
                    }
                    
                    Thread.sleep(1000);
                    attempts++;
                    
                    // Try clicking again every 5 attempts
                    if (attempts % 5 == 0) {
                        System.out.println("  Retriggering dropdown click...");
                        js.executeScript("arguments[0].click();", contactTypeDropdown);
                        Thread.sleep(500);
                    }
                    
                    // Refresh the Select object in case DOM changed
                    contactTypeSelect = new Select(contactTypeDropdown);
                }
                
                if (contactTypes != null && contactTypes.size() > 1) {
                    // Print all options for debugging
                    System.out.println("  Available Contact Types:");
                    for (int i = 0; i < contactTypes.size(); i++) {
                        System.out.println("    Option " + i + ": " + contactTypes.get(i).getText());
                    }
                    
                    // Select random option (skip first "Select" option)
                    int randomIndex = 1 + random.nextInt(contactTypes.size() - 1);
                    
                    // Try different selection approaches
                    try {
                        // Approach 1: selectByIndex
                        contactTypeSelect.selectByIndex(randomIndex);
                        System.out.println("✓ Selected Contact Type: " + contactTypes.get(randomIndex).getText() + " (index " + randomIndex + ")");
                    } catch (Exception e1) {
                        System.out.println("  ! selectByIndex failed: " + e1.getMessage());
                        try {
                            // Approach 2: Click with JavaScript
                            WebElement selectedOption = contactTypes.get(randomIndex);
                            js.executeScript("arguments[0].selected = true;", selectedOption);
                            js.executeScript("arguments[0].dispatchEvent(new Event('change'));", contactTypeDropdown);
                            System.out.println("✓ Selected Contact Type: " + selectedOption.getText() + " (JavaScript)");
                        } catch (Exception e2) {
                            System.out.println("  ! JavaScript selection also failed: " + e2.getMessage());
                        }
                    }
                    Thread.sleep(1500); // Wait for any onChange handlers to complete
                } else {
                    System.out.println("  ! Contact Type dropdown didn't load options after " + maxAttempts + " attempts");
                    System.out.println("  ! Skipping Contact Type selection and continuing with form...");
                }
            } catch (Exception e) {
                System.out.println("! Error selecting Contact Type: " + e.getMessage());
                e.printStackTrace();
            }
            Thread.sleep(1000);
            
            // Enter random alphanumeric Contact Name
            waitHelper.waitForElementVisible(contactNameField);
            String contactName = RandomDataGenerator.generateAlphanumeric(10);
            contactNameField.clear();
            contactNameField.sendKeys(contactName);
            System.out.println("✓ Entered Contact Name: " + contactName);
            Thread.sleep(1000);
            
            // Enter random Father Name
            try {
                waitHelper.waitForElementVisible(fatherNameField);
                String fatherName = RandomDataGenerator.generateName();
                fatherNameField.clear();
                fatherNameField.sendKeys(fatherName);
                System.out.println("✓ Entered Father Name: " + fatherName);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("  ! Father Name field not available or error: " + e.getMessage());
            }
            
            // Click Next (BasicInformationCollapse)
            waitHelper.waitForElementClickable(contactNextButton);
            js.executeScript("arguments[0].click();", contactNextButton);
            System.out.println("✓ Clicked Next button");
            Thread.sleep(1500);
            
            // Click Save
            waitHelper.waitForElementClickable(contactSaveButton);
            js.executeScript("arguments[0].click();", contactSaveButton);
            System.out.println("✓ New Contact saved");
            Thread.sleep(1500);
            
        } catch (Exception e) {
            System.out.println("! Error in Add New Contact: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Add Address Details
     */
    public void addAddressDetails() {
        try {
            System.out.println("\n=== Starting Address Details Section ===");
            Thread.sleep(1500);
            
            // Click Address Details section
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);
            waitHelper.waitForElementVisible(addressDetailsSection);
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", addressDetailsSection);
            Thread.sleep(800);
            js.executeScript("arguments[0].click();", addressDetailsSection);
            System.out.println("✓ Clicked Address Details section");
            Thread.sleep(3000);
            
            // Scroll down to ensure content is visible
            js.executeScript("window.scrollBy(0, 300);");
            Thread.sleep(2000);
            
            // Click Addition button using JavaScript
            System.out.println("  Looking for Addition button...");
            try {
                String jsClickButton = "var addBtn = document.evaluate(\"//*[@id='flush-collapse6']/div/div/div/div[1]/div[2]/a\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue; " +
                                      "if (addBtn) { addBtn.scrollIntoView({behavior: 'smooth', block: 'center'}); return true; } else { return false; }";
                
                Boolean buttonFound = (Boolean) js.executeScript(jsClickButton);
                
                if (buttonFound) {
                    Thread.sleep(500);
                    js.executeScript("document.evaluate(\"//*[@id='flush-collapse6']/div/div/div/div[1]/div[2]/a\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();");
                    System.out.println("✓ Clicked Addition button (JavaScript direct)");
                    Thread.sleep(2000);
                } else {
                    System.out.println("! Addition button not found in DOM, trying Selenium approach...");
                    waitHelper.waitForElementVisible(addressAdditionButton);
                    js.executeScript("arguments[0].click();", addressAdditionButton);
                    System.out.println("✓ Clicked Addition button (Selenium)");
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                System.out.println("! Error clicking Addition button: " + e.getMessage());
                e.printStackTrace();
                throw e;
            }
            
            // Select random Address Type
            try {
                System.out.println("  Waiting for Address Type dropdown...");
                waitHelper.waitForElementVisible(addressTypeIdsDropdown);
                Thread.sleep(2000);
                
                // Click the dropdown to trigger onclick handler that populates it
                System.out.println("  Clicking dropdown to trigger population...");
                js.executeScript("arguments[0].click();", addressTypeIdsDropdown);
                Thread.sleep(1000);
                js.executeScript("arguments[0].focus();", addressTypeIdsDropdown);
                Thread.sleep(1000);
                
                Select addressTypeSelect = new Select(addressTypeIdsDropdown);
                int attempts = 0;
                int maxAttempts = 15;
                List<WebElement> addressTypes = null;
                
                while (attempts < maxAttempts) {
                    addressTypes = addressTypeSelect.getOptions();
                    System.out.println("  Attempt " + (attempts + 1) + ": Found " + addressTypes.size() + " address type options");
                    
                    if (addressTypes.size() > 1) {
                        break;
                    }
                    
                    Thread.sleep(1000);
                    attempts++;
                    
                    if (attempts % 5 == 0) {
                        System.out.println("  Retriggering dropdown click...");
                        js.executeScript("arguments[0].click();", addressTypeIdsDropdown);
                        Thread.sleep(500);
                    }
                    
                    addressTypeSelect = new Select(addressTypeDropdown);
                }
                
                if (addressTypes != null && addressTypes.size() > 1) {
                    System.out.println("  Available Address Types:");
                    for (int i = 0; i < addressTypes.size(); i++) {
                        System.out.println("    Option " + i + ": " + addressTypes.get(i).getText());
                    }
                    
                    int randomIndex = 1 + random.nextInt(addressTypes.size() - 1);
                    
                    try {
                        addressTypeSelect.selectByIndex(randomIndex);
                        System.out.println("✓ Selected Address Type: " + addressTypes.get(randomIndex).getText());
                    } catch (Exception e1) {
                        WebElement selectedOption = addressTypes.get(randomIndex);
                        js.executeScript("arguments[0].selected = true;", selectedOption);
                        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", addressTypeIdsDropdown);
                        System.out.println("✓ Selected Address Type: " + selectedOption.getText() + " (JavaScript)");
                    }
                    Thread.sleep(1500);
                } else {
                    System.out.println("  ! Address Type dropdown didn't load options");
                }
            } catch (Exception e) {
                System.out.println("! Error selecting Address Type: " + e.getMessage());
                e.printStackTrace();
            }
            Thread.sleep(1000);
            
            // Enter random address in Address 1 field
            waitHelper.waitForElementVisible(addressInputField);
            String address = RandomDataGenerator.generateAlphanumeric(20);
            addressInputField.clear();
            addressInputField.sendKeys(address);
            System.out.println("✓ Entered Address: " + address);
            Thread.sleep(1000);
            
            // Select random Location
            try {
                System.out.println("  Waiting for Location dropdown...");
                waitHelper.waitForElementVisible(locationIdsDropdown);
                Thread.sleep(1000);
                
                // Click to trigger onclick handler
                js.executeScript("arguments[0].click();", locationIdsDropdown);
                Thread.sleep(1000);
                
                Select locationSelect = new Select(locationIdsDropdown);
                List<WebElement> locations = locationSelect.getOptions();
                
                System.out.println("  Available Locations: " + locations.size());
                for (int i = 0; i < locations.size(); i++) {
                    System.out.println("    Option " + i + ": " + locations.get(i).getText());
                }
                
                if (locations.size() > 1) {
                    // Skip "Select" option at index 0
                    int randomIndex = 1 + random.nextInt(locations.size() - 1);
                    
                    try {
                        locationSelect.selectByIndex(randomIndex);
                        System.out.println("✓ Selected Location: " + locations.get(randomIndex).getText());
                    } catch (Exception e1) {
                        WebElement selectedOption = locations.get(randomIndex);
                        js.executeScript("arguments[0].selected = true;", selectedOption);
                        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", locationIdsDropdown);
                        System.out.println("✓ Selected Location: " + selectedOption.getText() + " (JavaScript)");
                    }
                    Thread.sleep(1500);
                }
            } catch (Exception e) {
                System.out.println("! Error selecting Location: " + e.getMessage());
                e.printStackTrace();
            }
            Thread.sleep(1000);
            
            // Click Save
            waitHelper.waitForElementClickable(addressDetailsSaveButton);
            js.executeScript("arguments[0].click();", addressDetailsSaveButton);
            System.out.println("✓ Address Details saved");
            Thread.sleep(2000);
            
        } catch (Exception e) {
            System.out.println("! Error in Address Details: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Add Information Request
     */
    public void addInformationRequest() {
        try {
            System.out.println("\n=== Starting Information Request Section ===");
            Thread.sleep(1500);
            
            // Click Information Request section
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);
            waitHelper.waitForElementVisible(infoRequestSection);
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", infoRequestSection);
            Thread.sleep(800);
            js.executeScript("arguments[0].click();", infoRequestSection);
            System.out.println("✓ Clicked Information Request section");
            Thread.sleep(3000);
            
            // Scroll down to ensure content is visible
            js.executeScript("window.scrollBy(0, 300);");
            Thread.sleep(2000);
            
            // Click Addition button using JavaScript
            System.out.println("  Looking for Addition button...");
            try {
                String jsClickButton = "var addBtn = document.evaluate(\"//*[@id='inforequest']/div/div[1]/div[2]/div/a\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue; " +
                                      "if (addBtn) { addBtn.scrollIntoView({behavior: 'smooth', block: 'center'}); return true; } else { return false; }";
                
                Boolean buttonFound = (Boolean) js.executeScript(jsClickButton);
                
                if (buttonFound) {
                    Thread.sleep(500);
                    js.executeScript("document.evaluate(\"//*[@id='inforequest']/div/div[1]/div[2]/div/a\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();");
                    System.out.println("✓ Clicked Addition button (JavaScript direct)");
                    Thread.sleep(2000);
                } else {
                    System.out.println("! Addition button not found in DOM, trying Selenium approach...");
                    waitHelper.waitForElementVisible(infoRequestAdditionButton);
                    js.executeScript("arguments[0].click();", infoRequestAdditionButton);
                    System.out.println("✓ Clicked Addition button (Selenium)");
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                System.out.println("! Error clicking Addition button: " + e.getMessage());
                e.printStackTrace();
                throw e;
            }
            
            // Click and select in To User (Select2 with search input)
            try {
                System.out.println("  Looking for To User Select2...");
                Thread.sleep(2000);
                
                // Find parent Select2 container (the span that wraps the entire selector)
                WebElement select2Container = driver.findElement(By.xpath(
                    "//*[@id='create-informationrequest']/div/div[2]/div[3]/div/span[1]/span[1]/span"
                ));
                
                System.out.println("  Found Select2 container, clicking to open...");
                js.executeScript("arguments[0].click();", select2Container);
                Thread.sleep(1000);
                
                // Find and click the search input field to trigger the dropdown
                try {
                    WebElement searchInput = driver.findElement(By.xpath(
                        "//*[@id='create-informationrequest']/div/div[2]/div[3]/div/span[1]/span[1]/span/ul/li/input"
                    ));
                    System.out.println("  Found search input, clicking and sending ENTER...");
                    
                    // Click the input field
                    js.executeScript("arguments[0].click();", searchInput);
                    Thread.sleep(500);
                    
                    // Focus the input
                    js.executeScript("arguments[0].focus();", searchInput);
                    Thread.sleep(500);
                    
                    // Send ENTER or just trigger to load results
                    searchInput.sendKeys(Keys.ENTER);
                    System.out.println("  ✓ Sent ENTER to search input");
                    Thread.sleep(1500);
                } catch (Exception inputEx) {
                    System.out.println("  ! Search input not found, trying alternative approach: " + inputEx.getMessage());
                    // If input not found, try clicking container again
                    js.executeScript("arguments[0].click();", select2Container);
                    Thread.sleep(1000);
                }
                
                // Wait for dropdown to appear and find options
                List<WebElement> userOptions = new ArrayList<>();
                for (int i = 0; i < 15; i++) {
                    Thread.sleep(500);
                    
                    // Try to find user options in dropdown
                    userOptions = driver.findElements(By.xpath("//ul[contains(@class, 'select2-results__options')]/li[contains(@class, 'select2-results__option')]"));
                    
                    if (userOptions.size() > 0) {
                        System.out.println("  Found " + userOptions.size() + " user options");
                        break;
                    }
                }
                
                if (userOptions.size() > 0) {
                    // Filter valid options
                    List<WebElement> validUsers = new ArrayList<>();
                    System.out.println("  Available Users:");
                    for (WebElement option : userOptions) {
                        String text = option.getText().trim();
                        if (!text.isEmpty() && !text.equalsIgnoreCase("Searching…") && 
                            !text.equalsIgnoreCase("Loading results…") && 
                            !text.equalsIgnoreCase("No results found") &&
                            !text.contains("Loading more")) {
                            validUsers.add(option);
                            System.out.println("    - " + text);
                        }
                    }
                    
                    if (validUsers.size() > 0) {
                        // Select random user
                        int randomIndex = random.nextInt(validUsers.size());
                        WebElement selectedUser = validUsers.get(randomIndex);
                        String userName = selectedUser.getText();
                        
                        // Click the option
                        js.executeScript("arguments[0].click();", selectedUser);
                        System.out.println("✓ Selected To User: " + userName);
                        
                        // Send ENTER to confirm selection (for multi-select)
                        try {
                            WebElement searchInput = driver.findElement(By.xpath(
                                "//*[@id='create-informationrequest']/div/div[2]/div[3]/div/span[1]/span[1]/span/ul/li/input"
                            ));
                            searchInput.sendKeys(Keys.ENTER);
                            System.out.println("  ✓ Sent ENTER to confirm selection");
                        } catch (Exception enterEx) {
                            System.out.println("  ! Could not send ENTER: " + enterEx.getMessage());
                        }
                        
                        Thread.sleep(2000);
                    } else {
                        System.out.println("  ! No valid users found");
                    }
                } else {
                    System.out.println("  ! To User dropdown didn't open or no options available");
                }
            } catch (Exception e) {
                System.out.println("! Error selecting To User: " + e.getMessage());
                e.printStackTrace();
            }
            Thread.sleep(1500);
            
            // Enter random content in div editor
            try {
                System.out.println("  Looking for Content editor...");
                waitHelper.waitForElementVisible(contentDivEditor);
                
                String content = RandomDataGenerator.generateRemarks();
                
                // Use JavaScript to set content in contenteditable div
                js.executeScript("arguments[0].innerHTML = arguments[1];", contentDivEditor, content);
                System.out.println("✓ Entered Content: " + content);
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println("! Error entering Content: " + e.getMessage());
                e.printStackTrace();
            }
            
            // Click Save
            waitHelper.waitForElementClickable(infoRequestSaveButton);
            js.executeScript("arguments[0].click();", infoRequestSaveButton);
            System.out.println("✓ Information Request saved");
            Thread.sleep(2000);
            
        } catch (Exception e) {
            System.out.println("! Error in Information Request: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Complete Bank Consortium Details
     */
    public void completeBankConsortiumDetails() {
        try {
            System.out.println("\n=== Starting Bank Consortium Details Section ===");
            Thread.sleep(1500);
            
            // Click Bank Consortium Details section
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);
            waitHelper.waitForElementVisible(bankConsortiumDetailsSection);
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", bankConsortiumDetailsSection);
            Thread.sleep(800);
            js.executeScript("arguments[0].click();", bankConsortiumDetailsSection);
            System.out.println("✓ Clicked Bank Consortium Details section");
            Thread.sleep(3000);
            
            // Scroll down to ensure content is visible
            js.executeScript("window.scrollBy(0, 300);");
            Thread.sleep(2000);
            
            // Click Addition button using JavaScript
            System.out.println("  Looking for Addition button...");
            try {
                String jsClickButton = "var addBtn = document.evaluate(\"//*[@id='flush-collapse8']/div/div/div/div[1]/a\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue; " +
                                      "if (addBtn) { addBtn.scrollIntoView({behavior: 'smooth', block: 'center'}); return true; } else { return false; }";
                
                Boolean buttonFound = (Boolean) js.executeScript(jsClickButton);
                
                if (buttonFound) {
                    Thread.sleep(500);
                    js.executeScript("document.evaluate(\"//*[@id='flush-collapse8']/div/div/div/div[1]/a\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();");
                    System.out.println("✓ Clicked Addition button (JavaScript direct)");
                    Thread.sleep(3000);
                } else {
                    System.out.println("! Addition button not found in DOM, trying Selenium approach...");
                    waitHelper.waitForElementVisible(bankConsortiumAdditionButton);
                    js.executeScript("arguments[0].click();", bankConsortiumAdditionButton);
                    System.out.println("✓ Clicked Addition button (Selenium)");
                    Thread.sleep(3000);
                }
            } catch (Exception e) {
                System.out.println("! Error clicking Addition button: " + e.getMessage());
                e.printStackTrace();
                throw e;
            }
            
            // Click Yes radio button
            try {
                waitHelper.waitForElementVisible(bankConsortiumYesRadio);
                js.executeScript("arguments[0].click();", bankConsortiumYesRadio);
                System.out.println("✓ Clicked Yes radio button");
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("! Error clicking Yes radio: " + e.getMessage());
            }
            
            // Select random Institution Name 1
            try {
                waitHelper.waitForElementVisible(institutionName1Dropdown);
                Select institutionSelect = new Select(institutionName1Dropdown);
                List<WebElement> institutions = institutionSelect.getOptions();
                
                if (institutions.size() > 1) {
                    int randomIndex = 1 + random.nextInt(institutions.size() - 1);
                    institutionSelect.selectByIndex(randomIndex);
                    System.out.println("✓ Selected Institution Name: " + institutions.get(randomIndex).getText());
                    Thread.sleep(1500);
                }
            } catch (Exception e) {
                System.out.println("! Error selecting Institution Name: " + e.getMessage());
            }
            
            // Select random Role
            try {
                waitHelper.waitForElementVisible(roleDropdown);
                Select roleSelect = new Select(roleDropdown);
                List<WebElement> roles = roleSelect.getOptions();
                
                if (roles.size() > 1) {
                    int randomIndex = 1 + random.nextInt(roles.size() - 1);
                    roleSelect.selectByIndex(randomIndex);
                    System.out.println("✓ Selected Role: " + roles.get(randomIndex).getText());
                    Thread.sleep(1500);
                }
            } catch (Exception e) {
                System.out.println("! Error selecting Role: " + e.getMessage());
            }
            
            // Enter random number in Share
            try {
                waitHelper.waitForElementVisible(shareField);
                String share = String.valueOf(RandomDataGenerator.generateRandomNumber(10, 100));
                shareField.clear();
                shareField.sendKeys(share);
                System.out.println("✓ Entered Share: " + share);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("! Error entering Share: " + e.getMessage());
            }
            
            // Enter random number in Limit Sanctioned
            try {
                waitHelper.waitForElementVisible(limitSanctionedField);
                String limit = String.valueOf(RandomDataGenerator.generateRandomNumber(100000, 9999999));
                limitSanctionedField.clear();
                limitSanctionedField.sendKeys(limit);
                System.out.println("✓ Entered Limit Sanctioned: " + limit);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("! Error entering Limit Sanctioned: " + e.getMessage());
            }
            
            // Enter random alphanumeric in Comment
            try {
                waitHelper.waitForElementVisible(commentsField);
                String comment = RandomDataGenerator.generateAlphanumeric(20);
                commentsField.clear();
                commentsField.sendKeys(comment);
                System.out.println("✓ Entered Comment: " + comment);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("! Error entering Comment: " + e.getMessage());
            }
            
            // Click Save
            try {
                waitHelper.waitForElementClickable(bankConsortiumDetailsSaveButton);
                js.executeScript("arguments[0].click();", bankConsortiumDetailsSaveButton);
                System.out.println("✓ Bank Consortium Details saved");
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("! Error clicking Save: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("! Error in Bank Consortium Details: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Add Instrument Details
     */
    public void addInstrumentDetails() {
        try {
            System.out.println("\n=== Starting Instrument Details Section ===");
            Thread.sleep(1500);
            
            // Click Instrument Details section
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);
            waitHelper.waitForElementVisible(instrumentDetailsAccordion);
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", instrumentDetailsAccordion);
            Thread.sleep(800);
            js.executeScript("arguments[0].click();", instrumentDetailsAccordion);
            System.out.println("✓ Clicked Instrument Details section");
            Thread.sleep(3000);
            
            // Scroll down to ensure content is visible
            js.executeScript("window.scrollBy(0, 300);");
            Thread.sleep(2000);
            
            // Click Addition button using JavaScript
            System.out.println("  Looking for Addition button...");
            try {
                String jsClickButton = "var addBtn = document.evaluate(\"//*[@id='flush-collapse11']/div/div/div/div[1]/a\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue; " +
                                      "if (addBtn) { addBtn.scrollIntoView({behavior: 'smooth', block: 'center'}); return true; } else { return false; }";
                
                Boolean buttonFound = (Boolean) js.executeScript(jsClickButton);
                
                if (buttonFound) {
                    Thread.sleep(500);
                    js.executeScript("document.evaluate(\"//*[@id='flush-collapse11']/div/div/div/div[1]/a\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();");
                    System.out.println("✓ Clicked Addition button (JavaScript direct)");
                    Thread.sleep(3000);
                } else {
                    System.out.println("! Addition button not found in DOM, trying Selenium approach...");
                    waitHelper.waitForElementVisible(instrumentAdditionButton);
                    js.executeScript("arguments[0].click();", instrumentAdditionButton);
                    System.out.println("✓ Clicked Addition button (Selenium)");
                    Thread.sleep(3000);
                }
            } catch (Exception e) {
                System.out.println("! Error clicking Addition button: " + e.getMessage());
                e.printStackTrace();
                throw e;
            }
            
            // Enter random alphanumeric in Instrument No
            try {
                waitHelper.waitForElementVisible(instrumentNoField);
                String instrumentNo = RandomDataGenerator.generateAlphanumeric(12);
                instrumentNoField.clear();
                instrumentNoField.sendKeys(instrumentNo);
                System.out.println("✓ Entered Instrument No: " + instrumentNo);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("! Error entering Instrument No: " + e.getMessage());
            }
            
            // Select random Bank Name
            try {
                waitHelper.waitForElementVisible(bankNameDropdown);
                
                // Click to trigger onclick handler
                js.executeScript("arguments[0].click();", bankNameDropdown);
                Thread.sleep(1000);
                
                Select bankSelect = new Select(bankNameDropdown);
                List<WebElement> banks = bankSelect.getOptions();
                
                if (banks.size() > 1) {
                    System.out.println("  Available Banks: " + banks.size());
                    for (int i = 0; i < banks.size(); i++) {
                        System.out.println("    Option " + i + ": " + banks.get(i).getText());
                    }
                    
                    int randomIndex = 1 + random.nextInt(banks.size() - 1);
                    bankSelect.selectByIndex(randomIndex);
                    System.out.println("✓ Selected Bank Name: " + banks.get(randomIndex).getText());
                    Thread.sleep(1500);
                }
            } catch (Exception e) {
                System.out.println("! Error selecting Bank Name: " + e.getMessage());
            }
            
            // Enter random number in Instrument Amount
            try {
                waitHelper.waitForElementVisible(instrumentAmountField);
                String amount = String.valueOf(RandomDataGenerator.generateRandomNumber(10000, 9999999));
                instrumentAmountField.clear();
                instrumentAmountField.sendKeys(amount);
                System.out.println("✓ Entered Instrument Amount: " + amount);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("! Error entering Instrument Amount: " + e.getMessage());
            }
            
            // Select random Instrument Date
            try {
                waitHelper.waitForElementVisible(instrumentDatePicker);
                
                // Generate random date in format like "18-Feb-2026"
                String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                int day = RandomDataGenerator.generateRandomNumber(1, 28);
                int monthIndex = random.nextInt(months.length);
                int year = 2025 + random.nextInt(2); // 2025 or 2026
                
                String formattedDate = String.format("%02d-%s-%d", day, months[monthIndex], year);
                
                instrumentDatePicker.clear();
                instrumentDatePicker.sendKeys(formattedDate);
                System.out.println("✓ Entered Instrument Date: " + formattedDate);
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println("! Error entering Instrument Date: " + e.getMessage());
            }
            
            // Select random Presentation Status
            try {
                waitHelper.waitForElementVisible(presentationStatusDropdown);
                
                Select statusSelect = new Select(presentationStatusDropdown);
                List<WebElement> statuses = statusSelect.getOptions();
                
                if (statuses.size() > 1) {
                    System.out.println("  Available Presentation Statuses: " + statuses.size());
                    for (int i = 0; i < statuses.size(); i++) {
                        System.out.println("    Option " + i + ": " + statuses.get(i).getText());
                    }
                    
                    int randomIndex = 1 + random.nextInt(statuses.size() - 1);
                    statusSelect.selectByIndex(randomIndex);
                    System.out.println("✓ Selected Presentation Status: " + statuses.get(randomIndex).getText());
                    Thread.sleep(1500);
                }
            } catch (Exception e) {
                System.out.println("! Error selecting Presentation Status: " + e.getMessage());
            }
            
            // Click Save
            try {
                waitHelper.waitForElementClickable(instrumentDetailsSaveButton);
                js.executeScript("arguments[0].click();", instrumentDetailsSaveButton);
                System.out.println("✓ Instrument Details saved");
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("! Error clicking Save: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("! Error in Instrument Details: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Complete all account detail sections in sequence
     */
    public void completeAllAccountDetailSections() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("STARTING COMPLETE ACCOUNT DETAIL WORKFLOW");
        System.out.println("=".repeat(60));
        
        // Complete all sections
        completeBadDebtSection();
        completeAccountDetailsExtended();
        completeForeclosureDetails();
        completeConcilationDetails();
        completePresentStatus();
        completeRecoverySummaryApplicant();
        addNewApplicantContact();
        addAddressDetails();
        addInformationRequest();
        completeBankConsortiumDetails();
        addInstrumentDetails();
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("✓ ALL ACCOUNT DETAIL SECTIONS COMPLETED SUCCESSFULLY");
        System.out.println("=".repeat(60) + "\n");
    }
}
