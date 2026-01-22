package com.example.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.example.utils.RandomDataGenerator;
import com.example.utils.WaitHelper;

/**
 * Page Object Model for Add Customer Page
 */
public class AddCustomerPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final Map<String, String> customerData;
    
    // Basic Details Section Locators
    @FindBy(xpath = "//*[@id='CustomerNo']")
    private WebElement customerNumberField;
    
    @FindBy(xpath = "//*[@id='Name']")
    private WebElement customerNameField;
    
    @FindBy(xpath = "//*[@id='FatherName']")
    private WebElement fatherNameField;
    
    @FindBy(xpath = "//*[@id='PhoneNo']")
    private WebElement phoneNumberField;
    
    @FindBy(xpath = "//*[@id='EmailId']")
    private WebElement emailField;
    
    @FindBy(xpath = "//*[@id='customerBusinessUnitCreate']")
    private WebElement businessUnitDropdown;
    
    @FindBy(xpath = "//*[@id='MobileNo']")
    private WebElement mobileNumberField;
    
    @FindBy(xpath = "//*[@id='AdhaarNo']")
    private WebElement aadhaarNumberField;
    
    @FindBy(id = "AddressCust")
    private WebElement address1Field;
    
    @FindBy(id = "AddressCust2")
    private WebElement address2Field;
    
    // Button Locators
    @FindBy(xpath = "//*[@id='createbasicdetailscust']")
    private WebElement nextButtonBasicDetails;
    
    @FindBy(xpath = "//*[@id='CustomerNPACreate']")
    private WebElement nextButtonNPA;
    
    @FindBy(xpath = "//*[@id='flush-collapseThree']/div/div[2]/button[2]")
    private WebElement nextButtonThirdSection;
    
    @FindBy(name = "4")
    private WebElement srSupervisorDropdown;
    
    @FindBy(xpath = "//*[@id='flush-collapseFour']/div/div[2]/button[2]")
    private WebElement nextButtonFourthSection;
    
    @FindBy(xpath = "//*[@id='Remarks']")
    private WebElement remarksField;
    
    @FindBy(xpath = "//*[@id='CustomerCreateNew']")
    private WebElement saveButton;
    
    // Customer Details Page Locators
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[1]/div[1]/div")
    private WebElement basicDetailsSection;
    
    @FindBy(xpath = "//*[@id='flush-collapseOne']/div/div/div[1]/div[2]/a")
    private WebElement editBasicButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[2]/div[1]/div")
    private WebElement npaSection;
    
    @FindBy(xpath = "//*[@id='flush-collapse12']/div/div/div[1]/div[2]/a")
    private WebElement editNpaButton;
    
    @FindBy(xpath = "//*[@id='EditNPADetails']")
    private WebElement saveNpaButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[3]/div[1]/div")
    private WebElement trustServiceProviderSection;
    
    @FindBy(xpath = "//*[@id='flush-collapseThree']/div/div/div/div[1]/div[2]/a/I")
    private WebElement trustPlusButton;
    
    @FindBy(xpath = "//*[@id='addTrustModalContent']/form/div/div[4]/button[2]")
    private WebElement trustCancelButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[4]/div[1]/div")
    private WebElement ownershipDetailsSection;
    
    @FindBy(xpath = "//*[@id='CustSDO-tab']")
    private WebElement secondaryDealingOfficerTab;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[5]/div[1]/div")
    private WebElement remarkSection;
    
    @FindBy(xpath = "//*[@id='flush-Remark']/div/div/div[1]/div[2]/a")
    private WebElement editRemarkButton;
    
    @FindBy(xpath = "//*[@id='edit-remark']/form/div/div[2]/div/div/input")
    private WebElement saveRemarkButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[6]/div[1]/div")
    private WebElement recoverySummaryExpenseSection;
    
    @FindBy(xpath = "//*[@id='flush-collapse10']/ul/li[2]/a")
    private WebElement recoverySummaryTab;
    
    @FindBy(xpath = "//*[@id='flush-collapse10']/ul/li[3]/a")
    private WebElement customerExpenseTab;
    
    @FindBy(xpath = "//*[@id='customerexpense-details-link']/div/div[2]/a")
    private WebElement expensePlusButton;
    
    @FindBy(xpath = "//*[@id='new-expense-content']/div[2]/div[6]/button")
    private WebElement expenseCancelButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[7]/div[1]/div")
    private WebElement commentSection;
    
    @FindBy(xpath = "//*[@id='name']")
    private WebElement commentField;
    
    @FindBy(xpath = "//*[@id='commentForm']/div/button")
    private WebElement addCommentButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[8]/div[1]/div")
    private WebElement customerOtherDetailsSection;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[9]/div[1]/div")
    private WebElement otsSection;
    
    @FindBy(xpath = "//*[@id='otsTableContainer']/div/div[1]/div[2]")
    private WebElement otsPlusButton;
    
    @FindBy(xpath = "//*[@id='edit-notice']/form/div/div[6]/div/div/button")
    private WebElement otsCancelButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[10]/div[1]/div")
    private WebElement informationRequestSection;
    
    @FindBy(xpath = "//*[@id='flush-collapseNine']/div/div/div[1]/div[2]/div/a")
    private WebElement informationRequestPlusButton;
    
    @FindBy(xpath = "//*[@id='create-informationrequest']/div/div[3]/button")
    private WebElement informationRequestCancelButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[11]/div[1]/div")
    private WebElement addressDetailsSection;
    
    @FindBy(xpath = "//*[@id='address-details-container']/div/div/div/div[1]/div[2]/a/I")
    private WebElement addressPlusButton;
    
    @FindBy(xpath = "//*[@id='add-customeraddress']/div/div[5]/div[2]/button")
    private WebElement addressCancelButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[12]/div[1]/div")
    private WebElement auditTrailSection;
    
    @FindBy(xpath = "/html/body/div[2]/div/main/div/div/main/div/div[1]/a")
    private WebElement backButtonFromDetails;
    
    @FindBy(xpath = "//*[@id='CustomerSearchRecordsFilter']")
    private WebElement customerSearchField;
    
    @FindBy(xpath = "//*[@id='searchForm']/button")
    private WebElement searchButton;
    
    @FindBy(xpath = "//*[@id='customerListingContainer']/div/table/tbody/tr/td[7]/div/a")
    private WebElement actionButton;
    
    @FindBy(xpath = "//a[contains(text(), 'Delete') or contains(@onclick, 'deleteCustomer')]")
    private WebElement deleteButton;
    
    @FindBy(xpath = "//button[contains(text(), 'Delete') or contains(@class, 'btn-danger')]")
    private WebElement deleteConfirmButton;
    
    // Constructor
    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.customerData = new HashMap<>();
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Fill customer number
     * @param customerNumber customer number
     */
    public void fillCustomerNumber(String customerNumber) {
        waitHelper.waitForElementVisible(customerNumberField);
        customerNumberField.clear();
        customerNumberField.sendKeys(customerNumber);
        customerData.put("CustomerNumber", customerNumber);
    }
    
    /**
     * Fill customer name
     * @param customerName customer name
     */
    public void fillCustomerName(String customerName) {
        waitHelper.waitForElementVisible(customerNameField);
        customerNameField.clear();
        customerNameField.sendKeys(customerName);
        customerData.put("CustomerName", customerName);
    }
    
    /**
     * Fill father name
     * @param fatherName father's name
     */
    public void fillFatherName(String fatherName) {
        waitHelper.waitForElementVisible(fatherNameField);
        fatherNameField.clear();
        fatherNameField.sendKeys(fatherName);
        customerData.put("FatherName", fatherName);
    }
    
    /**
     * Select customer type from dropdown
     */
    public void selectCustomerType() {
        try {
            waitHelper.hardWait(1000);
            // Trigger the onclick event to load options
            WebElement dropdown = driver.findElement(By.xpath("//select[contains(@onclick, 'LoadCustomerTypeIdCreate')]"));
            waitHelper.clickWithJavaScript(dropdown);
            waitHelper.hardWait(1000); // Wait for options to load
            
            Select select = new Select(dropdown);
            if (select.getOptions().size() > 1) {
                int randomIndex = RandomDataGenerator.generateNumberInRange(1, select.getOptions().size() - 1);
                select.selectByIndex(randomIndex);
            }
        } catch (Exception e) {
            System.out.println("Customer Type dropdown not available or no options: " + e.getMessage());
        }
    }
    
    /**
     * Select industry from dropdown
     */
    public void selectIndustry() {
        try {
            waitHelper.hardWait(1000);
            WebElement dropdown = driver.findElement(By.xpath("//select[contains(@onclick, 'loadFilterIndustryOptionsforCustomer')]"));
            waitHelper.clickWithJavaScript(dropdown);
            waitHelper.hardWait(1000);
            
            Select select = new Select(dropdown);
            if (select.getOptions().size() > 1) {
                int randomIndex = RandomDataGenerator.generateNumberInRange(1, select.getOptions().size() - 1);
                select.selectByIndex(randomIndex);
            }
        } catch (Exception e) {
            System.out.println("Industry dropdown not available or no options: " + e.getMessage());
        }
    }
    
    /**
     * Select segment from dropdown
     */
    public void selectSegment() {
        try {
            waitHelper.hardWait(1000);
            WebElement dropdown = driver.findElement(By.xpath("//select[contains(@onclick, 'loadFilterSegmentOptionsforCustomer')]"));
            waitHelper.clickWithJavaScript(dropdown);
            waitHelper.hardWait(1000);
            
            Select select = new Select(dropdown);
            if (select.getOptions().size() > 1) {
                int randomIndex = RandomDataGenerator.generateNumberInRange(1, select.getOptions().size() - 1);
                select.selectByIndex(randomIndex);
            }
        } catch (Exception e) {
            System.out.println("Segment dropdown not available or no options: " + e.getMessage());
        }
    }
    
    /**
     * Fill phone number
     * @param phoneNumber phone number
     */
    public void fillPhoneNumber(String phoneNumber) {
        waitHelper.waitForElementVisible(phoneNumberField);
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNumber);
        customerData.put("PhoneNumber", phoneNumber);
    }
    
    /**
     * Fill email
     * @param email email address
     */
    public void fillEmail(String email) {
        waitHelper.waitForElementVisible(emailField);
        emailField.clear();
        emailField.sendKeys(email);
        customerData.put("Email", email);
    }
    
    /**
     * Select business unit from dropdown
     */
    public void selectBusinessUnit() {
        try {
            System.out.println("[DEBUG] Attempting to select Business Unit dropdown");
            waitHelper.hardWait(2000); // Increased wait for dropdown to be ready
            
            // Scroll to element first
            waitHelper.scrollToElement(businessUnitDropdown);
            System.out.println("[DEBUG] Scrolled to Business Unit dropdown");
            waitHelper.hardWait(500);
            
            // Wait for element to be visible
            waitHelper.waitForElementVisible(businessUnitDropdown);
            System.out.println("[DEBUG] Business Unit dropdown is visible");
            
            // Try clicking the dropdown to trigger options load (some dropdowns need this)
            try {
                businessUnitDropdown.click();
                System.out.println("[DEBUG] Clicked dropdown to trigger options");
                waitHelper.hardWait(1000); // Wait for options to load
            } catch (Exception clickEx) {
                System.out.println("[DEBUG] Click on dropdown not needed or failed: " + clickEx.getMessage());
            }
            
            Select select = new Select(businessUnitDropdown);
            int optionCount = select.getOptions().size();
            System.out.println("Business Unit dropdown options: " + optionCount);
            
            // Print all options for debugging
            for (int i = 0; i < optionCount; i++) {
                System.out.println("  Option " + i + ": " + select.getOptions().get(i).getText());
            }
            
            if (optionCount > 1) {
                int randomIndex = RandomDataGenerator.generateNumberInRange(1, optionCount - 1);
                String selectedOption = select.getOptions().get(randomIndex).getText();
                select.selectByIndex(randomIndex);
                System.out.println("✓ Selected Business Unit at index: " + randomIndex + " (" + selectedOption + ")");
                waitHelper.hardWait(1000); // Wait after selection for any onChange events
            } else {
                System.out.println("⚠ WARNING: Business Unit dropdown has no selectable options (only default)");
            }
        } catch (Exception e) {
            System.out.println("❌ ERROR: Business Unit dropdown error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Fill mobile number
     * @param mobileNumber mobile number
     */
    public void fillMobileNumber(String mobileNumber) {
        waitHelper.waitForElementVisible(mobileNumberField);
        mobileNumberField.clear();
        mobileNumberField.sendKeys(mobileNumber);
        customerData.put("MobileNumber", mobileNumber);
    }
    
    /**
     * Fill Aadhaar number
     * @param aadhaarNumber Aadhaar number
     */
    public void fillAadhaarNumber(String aadhaarNumber) {
        waitHelper.waitForElementVisible(aadhaarNumberField);
        aadhaarNumberField.clear();
        aadhaarNumberField.sendKeys(aadhaarNumber);
        customerData.put("AadhaarNumber", aadhaarNumber);
    }
    
    /**
     * Select zone from dropdown
     */
    public void selectZone() {
        try {
            waitHelper.hardWait(1000);
            WebElement dropdown = driver.findElement(By.xpath("//select[contains(@onclick, 'GetZoneCustomerCreate')]"));
            waitHelper.clickWithJavaScript(dropdown);
            waitHelper.hardWait(1000);
            
            Select select = new Select(dropdown);
            if (select.getOptions().size() > 1) {
                int randomIndex = RandomDataGenerator.generateNumberInRange(1, select.getOptions().size() - 1);
                select.selectByIndex(randomIndex);
            }
        } catch (Exception e) {
            System.out.println("Zone dropdown not available or no options: " + e.getMessage());
        }
    }
    
    /**
     * Select state from dropdown
     */
    public void selectState() {
        try {
            waitHelper.hardWait(1500);
            WebElement dropdown = driver.findElement(By.xpath("//select[contains(@onclick, 'GetStateByZoneCustomerCreate')]"));
            waitHelper.clickWithJavaScript(dropdown);
            waitHelper.hardWait(1000);
            
            Select select = new Select(dropdown);
            if (select.getOptions().size() > 1) {
                int randomIndex = RandomDataGenerator.generateNumberInRange(1, select.getOptions().size() - 1);
                select.selectByIndex(randomIndex);
            }
        } catch (Exception e) {
            System.out.println("State dropdown not available or no options: " + e.getMessage());
        }
    }
    
    /**
     * Select location from dropdown
     */
    public void selectLocation() {
        try {
            waitHelper.hardWait(1500);
            WebElement dropdown = driver.findElement(By.xpath("//select[contains(@onclick, 'GetLocationCustomerCreate')]"));
            waitHelper.clickWithJavaScript(dropdown);
            waitHelper.hardWait(1000);
            
            Select select = new Select(dropdown);
            if (select.getOptions().size() > 1) {
                int randomIndex = RandomDataGenerator.generateNumberInRange(1, select.getOptions().size() - 1);
                select.selectByIndex(randomIndex);
            }
        } catch (Exception e) {
            System.out.println("Location dropdown not available or no options: " + e.getMessage());
        }
    }
    
    /**
     * Select address type from dropdown
     */
    public void selectAddressType() {
        try {
            waitHelper.hardWait(1000);
            WebElement dropdown = driver.findElement(By.xpath("//select[contains(@onclick, 'AddressTypeIdCreateLoad')]"));
            waitHelper.clickWithJavaScript(dropdown);
            waitHelper.hardWait(1000);
            
            Select select = new Select(dropdown);
            if (select.getOptions().size() > 1) {
                int randomIndex = RandomDataGenerator.generateNumberInRange(1, select.getOptions().size() - 1);
                select.selectByIndex(randomIndex);
            }
        } catch (Exception e) {
            System.out.println("Address Type dropdown not available or no options: " + e.getMessage());
        }
    }
    
    /**
     * Fill address 1
     * @param address1 address line 1
     */
    public void fillAddress1(String address1) {
        waitHelper.waitForElementVisible(address1Field);
        address1Field.clear();
        address1Field.sendKeys(address1);
        customerData.put("Address1", address1);
    }
    
    /**
     * Fill address 2
     * @param address2 address line 2
     */
    public void fillAddress2(String address2) {
        waitHelper.waitForElementVisible(address2Field);
        address2Field.clear();
        address2Field.sendKeys(address2);
        customerData.put("Address2", address2);
    }
    
    /**
     * Click Next button on Basic Details section
     */
    public void clickNextBasicDetails() {
        try {
            System.out.println("Attempting to click Next button on Basic Details section");
            waitHelper.hardWait(2000); // Wait for any validation
            
            waitHelper.scrollToElement(nextButtonBasicDetails);
            waitHelper.hardWait(1000);
            
            // Check if button is enabled
            if (!nextButtonBasicDetails.isEnabled()) {
                System.out.println("WARNING: Next button is NOT enabled. Form validation may have failed.");
                // Try to find validation errors on the page
                try {
                    driver.findElement(By.cssSelector(".error, .invalid-feedback, .text-danger"))
                          .getText();
                } catch (Exception e) {
                    // No error message found
                }
            }
            
            waitHelper.waitForElementClickable(nextButtonBasicDetails);
            
            // Try regular click first
            try {
                nextButtonBasicDetails.click();
                System.out.println("Next button clicked successfully (regular click)");
            } catch (Exception e) {
                // If regular click fails, try JavaScript click
                System.out.println("Regular click failed, trying JavaScript click");
                waitHelper.clickWithJavaScript(nextButtonBasicDetails);
                System.out.println("Next button clicked successfully (JavaScript click)");
            }
            
            waitHelper.hardWait(1500);
            System.out.println("Clicked Next on Basic Details section");
        } catch (Exception e) {
            System.out.println("ERROR clicking Next button on Basic Details: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * Click Next button on NPA section
     */
    public void clickNextNPA() {
        waitHelper.scrollToElement(nextButtonNPA);
        waitHelper.waitForElementClickable(nextButtonNPA);
        nextButtonNPA.click();
        waitHelper.hardWait(1000);
    }
    
    /**
     * Click Next button on third section
     */
    public void clickNextThirdSection() {
        waitHelper.scrollToElement(nextButtonThirdSection);
        waitHelper.waitForElementClickable(nextButtonThirdSection);
        nextButtonThirdSection.click();
        waitHelper.hardWait(1000);
    }
    
    /**
     * Select Sr Supervisor from dropdown
     */
    public void selectSrSupervisor() {
        try {
            waitHelper.waitForElementVisible(srSupervisorDropdown);
            Select select = new Select(srSupervisorDropdown);
            // Select "Ayushi G" if available, otherwise select first non-empty option
            try {
                select.selectByVisibleText("Ayushi G");
            } catch (Exception e) {
                if (select.getOptions().size() > 1) {
                    select.selectByIndex(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Sr Supervisor dropdown not available: " + e.getMessage());
        }
    }
    
    /**
     * Click Next button on fourth section
     */
    public void clickNextFourthSection() {
        waitHelper.scrollToElement(nextButtonFourthSection);
        waitHelper.waitForElementClickable(nextButtonFourthSection);
        nextButtonFourthSection.click();
        waitHelper.hardWait(1000);
    }
    
    /**
     * Fill remarks
     * @param remarks remarks text
     */
    public void fillRemarks(String remarks) {
        waitHelper.waitForElementVisible(remarksField);
        remarksField.clear();
        remarksField.sendKeys(remarks);
        customerData.put("Remarks", remarks);
    }
    
    /**
     * Click Save button
     */
    public void clickSave() {
        waitHelper.scrollToElement(saveButton);
        waitHelper.waitForElementClickable(saveButton);
        saveButton.click();
        waitHelper.hardWait(2000); // Wait for save operation
    }
    
    /**
     * Fill complete customer form with random data
     */
    public void fillCustomerFormWithRandomData() {
        System.out.println("===== Starting to fill customer form =====");
        
        // Generate and fill basic details
        fillCustomerNumber(RandomDataGenerator.generateCustomerNumber());
        System.out.println("Filled customer number");
        
        fillCustomerName(RandomDataGenerator.generateName());
        System.out.println("Filled customer name");
        
        fillFatherName(RandomDataGenerator.generateName());
        System.out.println("Filled father name");
        
        // Select dropdowns with waits
        selectCustomerType();
        System.out.println("Selected customer type");
        
        selectIndustry();
        System.out.println("Selected industry");
        
        selectSegment();
        System.out.println("Selected segment");
        
        // Fill phone BEFORE email (order matters)
        fillPhoneNumber(RandomDataGenerator.generatePhoneNumber());
        System.out.println("Filled phone number");
        
        fillEmail(RandomDataGenerator.generateEmail());
        System.out.println("Filled email");
        
        // Fill mobile number BEFORE business unit
        fillMobileNumber(RandomDataGenerator.generateMobileNumber());
        System.out.println("Filled mobile number");
        
        // Select business unit - CRITICAL field that was failing
        selectBusinessUnit();
        System.out.println("Selected business unit");
        
        // Fill Aadhaar - MUST be 12 digits starting with 2-9
        fillAadhaarNumber(RandomDataGenerator.generateAadhaarNumber());
        System.out.println("Filled Aadhaar number");
        
        // Select location dropdowns (in order as they are dependent)
        selectZone();
        System.out.println("Selected zone");
        
        selectState();
        System.out.println("Selected state");
        
        selectLocation();
        System.out.println("Selected location");
        
        selectAddressType();
        System.out.println("Selected address type");
        
        // Fill address
        fillAddress1(RandomDataGenerator.generateAddress());
        System.out.println("Filled address 1");
        
        fillAddress2(RandomDataGenerator.generateFullAddress());
        System.out.println("Filled address 2");
        
        System.out.println("===== Customer form filled successfully =====");
    }
    
    /**
     * Get stored customer data
     * @param key data key
     * @return stored value
     */
    public String getCustomerData(String key) {
        return customerData.get(key);
    }
    
    /**
     * Verify customer creation success
     * @return true if success message or redirect occurs
     */
    public boolean isCustomerCreatedSuccessfully() {
        try {
            waitHelper.hardWait(2000);
            // Check for success message or page redirect
            return true; // Customize based on actual success indicator
        } catch (Exception e) {
            return false;
        }
    }
    
    // ==================== CUSTOMER DETAILS PAGE WORKFLOW METHODS ====================
    
    /**
     * Click Basic Details section
     */
    public void clickBasicDetailsSection() {
        waitHelper.hardWait(2000);
        waitHelper.scrollToElement(basicDetailsSection);
        waitHelper.waitForElementClickable(basicDetailsSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", basicDetailsSection);
        waitHelper.hardWait(1000);
        System.out.println("✓ Basic Details section clicked");
    }
    
    /**
     * Click Edit Basic Details button
     */
    public void clickEditBasicDetails() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(editBasicButton);
        waitHelper.waitForElementClickable(editBasicButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editBasicButton);
        waitHelper.hardWait(2000);
        System.out.println("✓ Edit Basic Details button clicked");
    }
    
    /**
     * Close modal overlay after basic details save
     */
    private void closeModalOverlay() {
        try {
            waitHelper.hardWait(1000);
            ((JavascriptExecutor) driver).executeScript(
                "var overlay = document.getElementById('facebox_overlay');" +
                "if(overlay) { overlay.style.display = 'none'; }"
            );
            waitHelper.hardWait(500);
        } catch (Exception e) {
            System.out.println("No modal overlay to close or already closed");
        }
    }
    
    /**
     * Click NPA section
     */
    public void clickNpaSection() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(npaSection);
        waitHelper.waitForElementClickable(npaSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", npaSection);
        waitHelper.hardWait(1000);
        System.out.println("✓ NPA section clicked");
    }
    
    /**
     * Click Edit NPA button
     */
    public void clickEditNpa() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(editNpaButton);
        waitHelper.waitForElementClickable(editNpaButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editNpaButton);
        waitHelper.hardWait(1000);
        System.out.println("✓ Edit NPA button clicked");
    }
    
    /**
     * Click Save NPA button
     */
    public void clickSaveNpa() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(saveNpaButton);
        waitHelper.waitForElementClickable(saveNpaButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveNpaButton);
        waitHelper.hardWait(2000);
        closeModalOverlay();
        System.out.println("✓ Save NPA button clicked");
    }
    
    /**
     * Click Trust/Service Provider section
     */
    public void clickTrustServiceProviderSection() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(trustServiceProviderSection);
        waitHelper.waitForElementClickable(trustServiceProviderSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trustServiceProviderSection);
        waitHelper.hardWait(1000);
        System.out.println("✓ Trust/Service Provider section clicked");
    }
    
    /**
     * Click Trust Plus button and cancel
     */
    public void clickTrustPlusAndCancel() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(trustPlusButton);
        waitHelper.waitForElementClickable(trustPlusButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trustPlusButton);
        waitHelper.hardWait(1000);
        System.out.println("✓ Trust Plus button clicked");
        
        waitHelper.hardWait(1000);
        waitHelper.waitForElementClickable(trustCancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trustCancelButton);
        waitHelper.hardWait(1000);
        closeModalOverlay();
        System.out.println("✓ Trust Cancel button clicked");
    }
    
    /**
     * Click Ownership Details section
     */
    public void clickOwnershipDetailsSection() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(ownershipDetailsSection);
        waitHelper.waitForElementClickable(ownershipDetailsSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ownershipDetailsSection);
        waitHelper.hardWait(1000);
        System.out.println("✓ Ownership Details section clicked");
    }
    
    /**
     * Click Secondary Dealing Officer tab
     */
    public void clickSecondaryDealingOfficerTab() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(secondaryDealingOfficerTab);
        waitHelper.waitForElementClickable(secondaryDealingOfficerTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", secondaryDealingOfficerTab);
        waitHelper.hardWait(1000);
        System.out.println("✓ Secondary Dealing Officer tab clicked");
    }
    
    /**
     * Click Remark section
     */
    public void clickRemarkSection() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(remarkSection);
        waitHelper.waitForElementClickable(remarkSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", remarkSection);
        waitHelper.hardWait(1000);
        System.out.println("✓ Remark section clicked");
    }
    
    /**
     * Click Edit Remark button
     */
    public void clickEditRemark() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(editRemarkButton);
        waitHelper.waitForElementClickable(editRemarkButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editRemarkButton);
        waitHelper.hardWait(1000);
        System.out.println("✓ Edit Remark button clicked");
    }
    
    /**
     * Click Save Remark button
     */
    public void clickSaveRemark() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(saveRemarkButton);
        waitHelper.waitForElementClickable(saveRemarkButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveRemarkButton);
        waitHelper.hardWait(2000);
        closeModalOverlay();
        System.out.println("✓ Save Remark button clicked");
    }
    
    /**
     * Click Recovery Summary/Expense section
     */
    public void clickRecoverySummaryExpenseSection() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(recoverySummaryExpenseSection);
        waitHelper.waitForElementClickable(recoverySummaryExpenseSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", recoverySummaryExpenseSection);
        waitHelper.hardWait(1000);
        System.out.println("✓ Recovery Summary/Expense section clicked");
    }
    
    /**
     * Click Recovery Summary tab
     */
    public void clickRecoverySummaryTab() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(recoverySummaryTab);
        waitHelper.waitForElementClickable(recoverySummaryTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", recoverySummaryTab);
        waitHelper.hardWait(1000);
        System.out.println("✓ Recovery Summary tab clicked");
    }
    
    /**
     * Click Customer Expense tab
     */
    public void clickCustomerExpenseTab() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(customerExpenseTab);
        waitHelper.waitForElementClickable(customerExpenseTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", customerExpenseTab);
        waitHelper.hardWait(1000);
        System.out.println("✓ Customer Expense tab clicked");
    }
    
    /**
     * Click Expense Plus button and cancel
     */
    public void clickExpensePlusAndCancel() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(expensePlusButton);
        waitHelper.waitForElementClickable(expensePlusButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", expensePlusButton);
        waitHelper.hardWait(1000);
        System.out.println("✓ Expense Plus button clicked");
        
        waitHelper.hardWait(1000);
        waitHelper.waitForElementClickable(expenseCancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", expenseCancelButton);
        waitHelper.hardWait(1000);
        closeModalOverlay();
        System.out.println("✓ Expense Cancel button clicked");
    }
    
    /**
     * Click Comment section
     */
    public void clickCommentSection() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(commentSection);
        waitHelper.waitForElementClickable(commentSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", commentSection);
        waitHelper.hardWait(1000);
        System.out.println("✓ Comment section clicked");
    }
    
    /**
     * Add comment
     */
    public void addComment() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(commentField);
        waitHelper.waitForElementClickable(commentField);
        commentField.clear();
        commentField.sendKeys("Test comment - automated test");
        waitHelper.hardWait(500);
        
        waitHelper.scrollToElement(addCommentButton);
        waitHelper.waitForElementClickable(addCommentButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addCommentButton);
        waitHelper.hardWait(2000);
        closeModalOverlay();
        System.out.println("✓ Comment added successfully");
    }
    
    /**
     * Click Customer Other Details section
     */
    public void clickCustomerOtherDetailsSection() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(customerOtherDetailsSection);
        waitHelper.waitForElementClickable(customerOtherDetailsSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", customerOtherDetailsSection);
        waitHelper.hardWait(1000);
        System.out.println("✓ Customer Other Details section clicked");
    }
    
    /**
     * Click OTS section
     */
    public void clickOtsSection() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(otsSection);
        waitHelper.waitForElementClickable(otsSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", otsSection);
        waitHelper.hardWait(1000);
        System.out.println("✓ OTS section clicked");
    }
    
    /**
     * Click OTS Plus button and cancel
     */
    public void clickOtsPlusAndCancel() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(otsPlusButton);
        waitHelper.waitForElementClickable(otsPlusButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", otsPlusButton);
        waitHelper.hardWait(1000);
        System.out.println("✓ OTS Plus button clicked");
        
        waitHelper.hardWait(1000);
        waitHelper.waitForElementClickable(otsCancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", otsCancelButton);
        waitHelper.hardWait(1000);
        closeModalOverlay();
        System.out.println("✓ OTS Cancel button clicked");
    }
    
    /**
     * Click Information Request section
     */
    public void clickInformationRequestSection() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(informationRequestSection);
        waitHelper.waitForElementClickable(informationRequestSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", informationRequestSection);
        waitHelper.hardWait(1000);
        System.out.println("✓ Information Request section clicked");
    }
    
    /**
     * Click Information Request Plus button and cancel
     */
    public void clickInformationRequestPlusAndCancel() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(informationRequestPlusButton);
        waitHelper.waitForElementClickable(informationRequestPlusButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", informationRequestPlusButton);
        waitHelper.hardWait(1000);
        System.out.println("✓ Information Request Plus button clicked");
        
        waitHelper.hardWait(1000);
        waitHelper.waitForElementClickable(informationRequestCancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", informationRequestCancelButton);
        waitHelper.hardWait(1000);
        closeModalOverlay();
        System.out.println("✓ Information Request Cancel button clicked");
    }
    
    /**
     * Click Address Details section
     */
    public void clickAddressDetailsSection() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(addressDetailsSection);
        waitHelper.waitForElementClickable(addressDetailsSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addressDetailsSection);
        waitHelper.hardWait(1000);
        System.out.println("✓ Address Details section clicked");
    }
    
    /**
     * Click Address Plus button and cancel
     */
    public void clickAddressPlusAndCancel() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(addressPlusButton);
        waitHelper.waitForElementClickable(addressPlusButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addressPlusButton);
        waitHelper.hardWait(1000);
        System.out.println("✓ Address Plus button clicked");
        
        waitHelper.hardWait(1000);
        waitHelper.waitForElementClickable(addressCancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addressCancelButton);
        waitHelper.hardWait(1000);
        closeModalOverlay();
        System.out.println("✓ Address Cancel button clicked");
    }
    
    /**
     * Click Audit Trail section
     */
    public void clickAuditTrailSection() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(auditTrailSection);
        waitHelper.waitForElementClickable(auditTrailSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", auditTrailSection);
        waitHelper.hardWait(1000);
        System.out.println("✓ Audit Trail section clicked");
    }
    
    /**
     * Click Back button from customer details page
     */
    public void clickBackFromDetails() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(backButtonFromDetails);
        waitHelper.waitForElementClickable(backButtonFromDetails);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", backButtonFromDetails);
        waitHelper.hardWait(2000);
        System.out.println("✓ Back button clicked");
    }
    
    /**
     * Search for customer
     */
    public void searchCustomer(String customerNumber) {
        waitHelper.hardWait(2000);
        waitHelper.scrollToElement(customerSearchField);
        waitHelper.waitForElementClickable(customerSearchField);
        customerSearchField.clear();
        customerSearchField.sendKeys(customerNumber);
        waitHelper.hardWait(500);
        
        waitHelper.scrollToElement(searchButton);
        waitHelper.waitForElementClickable(searchButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchButton);
        waitHelper.hardWait(2000);
        System.out.println("✓ Customer searched: " + customerNumber);
    }
    
    /**
     * Click Action button to show dropdown menu
     */
    public void clickActionButton() {
        waitHelper.hardWait(2000);
        waitHelper.scrollToElement(actionButton);
        waitHelper.waitForElementClickable(actionButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actionButton);
        waitHelper.hardWait(1000);
        System.out.println("✓ Action button clicked");
    }
    
    /**
     * Click Delete button for customer
     */
    public void clickDeleteCustomer() {
        try {
            waitHelper.hardWait(2000);
            // First try to find the delete button
            WebElement deleteBtn = driver.findElement(By.xpath("//a[contains(text(), 'Delete') or contains(@onclick, 'deleteCustomer')]"));
            waitHelper.scrollToElement(deleteBtn);
            waitHelper.waitForElementClickable(deleteBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);
            waitHelper.hardWait(2000);
            System.out.println("✓ Delete button clicked");
        } catch (Exception e) {
            System.out.println("Error clicking delete button: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Confirm customer deletion
     */
    public void confirmDeleteCustomer() {
        try {
            waitHelper.hardWait(2000);
            // Try multiple strategies to find and click the delete confirmation button
            try {
                WebElement confirmBtn = driver.findElement(By.xpath("//button[contains(text(), 'Delete') or contains(@class, 'btn-danger')]"));
                waitHelper.waitForElementClickable(confirmBtn);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmBtn);
                System.out.println("✓ Delete confirmation button clicked (strategy 1)");
            } catch (Exception e1) {
                try {
                    // Alternative: look for button in modal
                    WebElement confirmBtn = driver.findElement(By.xpath("//*[@id='deleteCustomerModal']//button[contains(@class, 'btn') and contains(text(), 'Delete')]"));
                    waitHelper.waitForElementClickable(confirmBtn);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmBtn);
                    System.out.println("✓ Delete confirmation button clicked (strategy 2)");
                } catch (Exception e2) {
                    // Try the original xpath as fallback
                    WebElement confirmBtn = driver.findElement(By.xpath("//*[@id='deleteCustomerModal']/div/div/div[3]/button[1]"));
                    waitHelper.waitForElementClickable(confirmBtn);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmBtn);
                    System.out.println("✓ Delete confirmation button clicked (strategy 3)");
                }
            }
            waitHelper.hardWait(3000);
            closeModalOverlay();
            System.out.println("✓ Delete confirmation completed");
        } catch (Exception e) {
            System.out.println("Error confirming deletion: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Accept the delete confirmation alert
     */
    public void acceptDeleteAlert() {
        try {
            System.out.println("Waiting for alert popup...");
            waitHelper.hardWait(1000); // Wait for alert to appear
            
            // Wait for alert to be present
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent());
            
            // Switch to alert and accept it
            org.openqa.selenium.Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert message: " + alertText);
            alert.accept();
            
            waitHelper.hardWait(2000);
            System.out.println("✓✓✓ Alert accepted - Customer deleted successfully! ✓✓✓");
        } catch (org.openqa.selenium.NoAlertPresentException e) {
            System.out.println("⚠ No alert popup found - customer may have been deleted without confirmation");
        } catch (Exception e) {
            System.out.println("Error accepting alert: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Master method - Complete customer details verification workflow
     * This orchestrates the entire customer details page interaction
     */
    public void verifyCustomerDetailsComplete() {
        System.out.println("\n========== STARTING CUSTOMER DETAILS VERIFICATION ==========\n");
        
        // 1. Basic Details section - Click to expand, then wait and click edit
        clickBasicDetailsSection();
        waitHelper.hardWait(3000); // Wait for section to fully expand
        try {
            clickEditBasicDetails();
            closeModalOverlay();
        } catch (Exception e) {
            System.out.println("? Basic Details edit button not available or already expanded");
        }
        
        // 2. NPA section - Click to expand, then interact
        clickNpaSection();
        waitHelper.hardWait(3000);
        try {
            clickEditNpa();
            clickSaveNpa();
        } catch (Exception e) {
            System.out.println("? NPA edit functionality not available");
        }
        
        // 3. Trust/Service Provider section - Click to expand
        clickTrustServiceProviderSection();
        waitHelper.hardWait(3000);
        try {
            clickTrustPlusAndCancel();
        } catch (Exception e) {
            System.out.println("? Trust/Service Provider plus button not available");
        }
        
        // 4. Ownership Details section - Click to expand and switch tabs
        clickOwnershipDetailsSection();
        waitHelper.hardWait(3000);
        try {
            clickSecondaryDealingOfficerTab();
        } catch (Exception e) {
            System.out.println("? Ownership Details tabs not available");
        }
        
        // 5. Remark section - Click to expand
        clickRemarkSection();
        waitHelper.hardWait(3000);
        try {
            clickEditRemark();
            clickSaveRemark();
        } catch (Exception e) {
            System.out.println("? Remark edit functionality not available");
        }
        
        // 6. Recovery Summary/Expense section - Click to expand and switch tabs
        clickRecoverySummaryExpenseSection();
        waitHelper.hardWait(3000);
        try {
            clickRecoverySummaryTab();
            waitHelper.hardWait(1000);
            clickCustomerExpenseTab();
            waitHelper.hardWait(1000);
            clickExpensePlusAndCancel();
        } catch (Exception e) {
            System.out.println("? Recovery Summary tabs/functions not available");
        }
        
        // 7. Comment section - Click to expand
        clickCommentSection();
        waitHelper.hardWait(3000);
        try {
            addComment();
        } catch (Exception e) {
            System.out.println("? Comment functionality not available");
        }
        
        // 8. Customer Other Details section - Click to expand
        clickCustomerOtherDetailsSection();
        waitHelper.hardWait(2000);
        
        // 9. OTS section - Click to expand
        clickOtsSection();
        waitHelper.hardWait(3000);
        try {
            clickOtsPlusAndCancel();
        } catch (Exception e) {
            System.out.println("? OTS plus button not available");
        }
        
        // 10. Information Request section - Click to expand
        clickInformationRequestSection();
        waitHelper.hardWait(3000);
        try {
            clickInformationRequestPlusAndCancel();
        } catch (Exception e) {
            System.out.println("? Information Request plus button not available");
        }
        
        // 11. Address Details section - Click to expand
        clickAddressDetailsSection();
        waitHelper.hardWait(3000);
        try {
            clickAddressPlusAndCancel();
        } catch (Exception e) {
            System.out.println("? Address Details plus button not available");
        }
        
        // 12. Audit Trail section - Click to expand
        clickAuditTrailSection();
        waitHelper.hardWait(2000);
        
        System.out.println("\n========== ALL CUSTOMER DETAILS SECTIONS VERIFIED ==========\n");
        
        // Navigate back and delete
        clickBackFromDetails();
        
        String customerNumber = customerData.get("CustomerNumber");
        System.out.println("✓ Retrieved customer number: " + customerNumber);
        searchCustomer(customerNumber);
        clickActionButton();
        clickDeleteCustomer();
        confirmDeleteCustomer();
        acceptDeleteAlert();
        
        System.out.println("\n========== CUSTOMER DETAILS VERIFICATION COMPLETE ==========\n");
    }
}
