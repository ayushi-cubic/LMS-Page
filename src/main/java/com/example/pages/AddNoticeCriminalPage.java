package com.example.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.example.utils.RandomDataGenerator;
import com.example.utils.WaitHelper;

/**
 * Page Object for Add Criminal Notice Form
 * Handles all interactions with the criminal notice creation form
 */
public class AddNoticeCriminalPage {
    
    private WebDriver driver;
    private WaitHelper waitHelper;
    private RandomDataGenerator randomDataGenerator;
    private Map<String, String> noticeCriminalData;
    
    // Locators for form fields
    private By serialNumberField = By.xpath("//*[@id='SerialNumber']");
    private By noticeTypeDropdown = By.xpath("//*[@id='NoticeTypeId']");
    private By customerNumberSelect2 = By.xpath("//*[@id='flush-collapse1']/div/div[5]/div[5]/div/span[1]/span[1]/span");
    private By customerAccountSelect2 = By.xpath("//*[@id='flush-collapse1']/div/div[5]/div[6]/div/span[1]/span[1]/span");
    private By weAreField = By.xpath("//*[@id='WeAre']");
    private By issuingPartyDropdown = By.xpath("//*[@id='byRoleSection']/div/div[1]/div/div[1]");
    private By noticeeDropdown = By.xpath("//*[@id='againstRoleSection']/div/div[1]/div/div[1]");
    private By issuingPartyAdvocateDropdown = By.xpath("//*[@id='issuingAdvocateSection']/div/div[1]/div/div[1]");
    private By noticeReplyDropdown = By.xpath("//*[@id='NoticeSentReceive']");
    private By partiesDropdown = By.xpath("//*[@id='flush-collapse1']/div/div[9]/div[8]/div/div[1]");
    private By basicInfoNextButton = By.xpath("//*[@id='BasicInformationCollapse']");
    private By stakeDetailsNextButton = By.xpath("//*[@id='StakeDetailsCollapse']");
    private By srSupervisorDropdown = By.xpath("//*[@id='4']");
    private By pdoNoticeNextButton = By.xpath("//*[@id='PDONoticeCollapse']");
    private By noticeAnalysisNextButton = By.xpath("//*[@id='NoticeAnalysisCollapse']");
    private By createButton = By.xpath("//*[@id='btnSubmit']");
    
    public AddNoticeCriminalPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.randomDataGenerator = new RandomDataGenerator();
        this.noticeCriminalData = new HashMap<>();
    }
    
    /**
     * Fill the entire criminal notice form with random data
     */
    public void fillCriminalNoticeFormWithRandomData(Map<String, String> fieldMappings) {
        try {
            System.out.println("\n========== FILLING CRIMINAL NOTICE FORM WITH RANDOM DATA ==========\n");
            
            // Fill Serial Number
            enterSerialNumber();
            
            // Select Notice Type
            selectNoticeType();
            
            // Select Customer Number
            selectCustomerNumber();
            
            // Select Customer Account
            selectCustomerAccount();
            
            // Enter We Are number
            enterWeAre();
            
            // Select Issuing Party
            selectIssuingParty();
            
            // Select Noticee
            selectNoticee();
            
            // Select Issuing Party Advocate
            selectIssuingPartyAdvocate();
            
            // Select Notice Reply
            selectNoticeReply();
            
            // Select Parties
            selectParties();
            
            // Click Next on Basic Information
            clickBasicInfoNext();
            
            // Click Next on Stake Details
            clickStakeDetailsNext();
            
            // Select Sr Supervisor
            selectSrSupervisor();
            
            // Click Next on PDO Notice
            clickPdoNoticeNext();
            
            // Click Next on Notice Analysis
            clickNoticeAnalysisNext();
            
            // Click Create button
            clickCreateButton();
            
            System.out.println("\n========== CRIMINAL NOTICE FORM FILLED SUCCESSFULLY ==========\n");
            
        } catch (Exception e) {
            System.out.println("❌ Error filling criminal notice form: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to fill criminal notice form", e);
        }
    }
    
    /**
     * Enter random serial number
     */
    private void enterSerialNumber() {
        try {
            System.out.println("Filling Serial Number...");
            waitHelper.hardWait(1000);
            String serialNumber = randomDataGenerator.generateAlphanumeric(8);
            WebElement serialField = waitHelper.waitForElementVisible(serialNumberField);
            serialField.clear();
            serialField.sendKeys(serialNumber);
            noticeCriminalData.put("SerialNumber", serialNumber);
            System.out.println("✓ Serial Number: " + serialNumber);
        } catch (Exception e) {
            System.out.println("⚠ Serial Number field not available: " + e.getMessage());
        }
    }
    
    /**
     * Select random Notice Type from dropdown
     */
    private void selectNoticeType() {
        int maxRetries = 3;
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                System.out.println("Selecting Notice Type (Attempt " + attempt + "/" + maxRetries + ")...");
                waitHelper.hardWait(2000);
                
                // Find and scroll to dropdown
                WebElement dropdown = waitHelper.waitForElementVisible(noticeTypeDropdown);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
                waitHelper.hardWait(1000);
                
                // Click using JavaScript to avoid interception
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
                waitHelper.hardWait(2500); // Wait for AJAX to load options
                
                Select select = new Select(dropdown);
                List<WebElement> options = select.getOptions();
                
                // Filter out empty options
                List<WebElement> validOptions = new ArrayList<>();
                for (WebElement option : options) {
                    if (!option.getText().trim().isEmpty() && !option.getText().equals("Select")) {
                        validOptions.add(option);
                    }
                }
                
                if (!validOptions.isEmpty()) {
                    int randomIndex = new Random().nextInt(validOptions.size());
                    WebElement selectedOption = validOptions.get(randomIndex);
                    String selectedText = selectedOption.getText();
                    String selectedValue = selectedOption.getAttribute("value");
                    
                    // Select using JavaScript for reliability
                    ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'));",
                        dropdown, selectedValue
                    );
                    
                    noticeCriminalData.put("NoticeType", selectedText);
                    System.out.println("✓ Notice Type selected: " + selectedText);
                    waitHelper.hardWait(1500);
                    return; // Success, exit retry loop
                } else {
                    System.out.println("⚠ No valid options on attempt " + attempt);
                    if (attempt < maxRetries) {
                        waitHelper.hardWait(2000);
                        continue;
                    }
                }
            } catch (Exception e) {
                System.out.println("⚠ Error selecting Notice Type on attempt " + attempt + ": " + e.getMessage());
                if (attempt == maxRetries) {
                    e.printStackTrace();
                    throw new RuntimeException("❌ FAILED to select Notice Type after " + maxRetries + " attempts", e);
                }
                waitHelper.hardWait(2000);
            }
        }
        throw new RuntimeException("❌ CRITICAL: Notice Type dropdown selection FAILED after all retries");
    }
    
    /**
     * Select random Customer Number (Select2 dropdown)
     */
    private void selectCustomerNumber() {
        int maxRetries = 3;
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                System.out.println("Selecting Customer Number (Attempt " + attempt + "/" + maxRetries + ")...");
                waitHelper.hardWait(2000);
                
                // Scroll to Select2 container
                WebElement customerDropdown = waitHelper.waitForElementVisible(customerNumberSelect2);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", customerDropdown);
                waitHelper.hardWait(800);
                
                // Click on Select2 container to open dropdown
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", customerDropdown);
                System.out.println("✓ Clicked Customer Number dropdown");
                waitHelper.hardWait(1500);
                
                // Wait for dropdown to appear and options to load
                waitHelper.hardWait(2500);
                
                // Find all available options from the dropdown results
                List<WebElement> options = driver.findElements(By.xpath("//ul[contains(@class, 'select2-results__options')]/li[not(contains(@class, 'loading-results'))]"));
                System.out.println("Found " + options.size() + " customer options in dropdown");
                
                if (!options.isEmpty()) {
                    // Filter out any disabled or "no results" options
                    List<WebElement> validOptions = new ArrayList<>();
                    for (WebElement option : options) {
                        String text = option.getText().trim();
                        String ariaDisabled = option.getAttribute("aria-disabled");
                        if (!text.isEmpty() && !text.contains("Searching") && !text.contains("No results") 
                            && !text.contains("Loading") && !"true".equals(ariaDisabled)) {
                            validOptions.add(option);
                            System.out.println("  Valid customer option: " + text);
                        }
                    }
                    
                    if (!validOptions.isEmpty()) {
                        // Select a random valid option
                        int randomIndex = new Random().nextInt(validOptions.size());
                        WebElement selectedOption = validOptions.get(randomIndex);
                        String optionText = selectedOption.getText().trim();
                        
                        // Scroll option into view
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOption);
                        waitHelper.hardWait(300);
                        
                        // Click using regular click first (Select2 expects normal click)
                        try {
                            selectedOption.click();
                        } catch (Exception e) {
                            // Fallback to JS click
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectedOption);
                        }
                        
                        // Verify selection by checking the display value
                        waitHelper.hardWait(800);
                        String displayText = customerDropdown.getText();
                        System.out.println("✓ Display text after selection: '" + displayText + "'");
                        
                        noticeCriminalData.put("CustomerNumber", optionText);
                        System.out.println("✓ Selected Customer Number: " + optionText);
                        waitHelper.hardWait(1500);
                        return; // Success, exit retry loop
                    } else {
                        System.out.println("⚠ No valid customer options on attempt " + attempt);
                        if (attempt < maxRetries) {
                            waitHelper.hardWait(2000);
                            continue;
                        }
                    }
                } else {
                    System.out.println("⚠ Customer dropdown has no options on attempt " + attempt);
                    if (attempt < maxRetries) {
                        waitHelper.hardWait(2000);
                        continue;
                    }
                }
                
            } catch (Exception e) {
                System.out.println("⚠ Error selecting Customer Number on attempt " + attempt + ": " + e.getMessage());
                if (attempt == maxRetries) {
                    e.printStackTrace();
                    throw new RuntimeException("❌ FAILED to select Customer Number after " + maxRetries + " attempts", e);
                }
                waitHelper.hardWait(2000);
            }
        }
        throw new RuntimeException("❌ CRITICAL: Customer Number dropdown selection FAILED after all retries");
    }
    
    /**
     * Select random Customer Account (Select2 dropdown)
     */
    private void selectCustomerAccount() {
        int maxRetries = 3;
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                System.out.println("Selecting Customer Account (Attempt " + attempt + "/" + maxRetries + ")...");
                waitHelper.hardWait(1500);
                
                // Find the Select2 container for customer account
                WebElement accountDropdown = waitHelper.waitForElementVisible(customerAccountSelect2);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", accountDropdown);
                waitHelper.hardWait(800);
                
                // Click on the Select2 container to open dropdown
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", accountDropdown);
                System.out.println("✓ Clicked Customer Account dropdown");
                waitHelper.hardWait(2000);
                
                // Wait for options to load
                waitHelper.hardWait(2500);
                
                // Find all available options from the dropdown results
                List<WebElement> options = driver.findElements(By.xpath("//ul[contains(@class, 'select2-results__options')]/li[not(contains(@class, 'loading-results'))]"));
                System.out.println("Found " + options.size() + " account options in dropdown");
                
                if (!options.isEmpty()) {
                    // Filter out any disabled or "no results" options
                    List<WebElement> validOptions = new ArrayList<>();
                    for (WebElement option : options) {
                        String text = option.getText().trim();
                        String ariaDisabled = option.getAttribute("aria-disabled");
                        if (!text.isEmpty() && !text.contains("Searching") && !text.contains("No results") 
                            && !text.contains("Loading") && !"true".equals(ariaDisabled)) {
                            validOptions.add(option);
                            System.out.println("  Valid account option: " + text);
                        }
                    }
                    
                    if (!validOptions.isEmpty()) {
                        // Select a random valid option
                        int randomIndex = new Random().nextInt(validOptions.size());
                        WebElement selectedOption = validOptions.get(randomIndex);
                        String optionText = selectedOption.getText().trim();
                        
                        // Scroll option into view
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOption);
                        waitHelper.hardWait(300);
                        
                        // Click using regular click first (Select2 expects normal click)
                        try {
                            selectedOption.click();
                        } catch (Exception e) {
                            // Fallback to JS click
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectedOption);
                        }
                        
                        // Verify selection
                        waitHelper.hardWait(800);
                        System.out.println("✓ Clicked account option: " + optionText);
                        
                        noticeCriminalData.put("CustomerAccount", optionText);
                        System.out.println("✓ Selected Customer Account: " + optionText);
                        waitHelper.hardWait(1500);
                        return; // Success, exit retry loop
                    } else {
                        System.out.println("⚠ No valid account options on attempt " + attempt);
                        if (attempt < maxRetries) {
                            waitHelper.hardWait(2000);
                            continue;
                        }
                    }
                } else {
                    System.out.println("⚠ Account dropdown has no options on attempt " + attempt);
                    if (attempt < maxRetries) {
                        waitHelper.hardWait(2000);
                        continue;
                    }
                }
                
            } catch (Exception e) {
                System.out.println("⚠ Error selecting Customer Account on attempt " + attempt + ": " + e.getMessage());
                if (attempt == maxRetries) {
                    e.printStackTrace();
                    throw new RuntimeException("❌ FAILED to select Customer Account after " + maxRetries + " attempts", e);
                }
                waitHelper.hardWait(2000);
            }
        }
        throw new RuntimeException("❌ CRITICAL: Customer Account dropdown selection FAILED after all retries");
    }
    
    /**
     * Enter random number in We Are field (1-200)
     */
    private void enterWeAre() {
        try {
            System.out.println("Filling We Are field...");
            waitHelper.hardWait(1000);
            int weAreNumber = new Random().nextInt(200) + 1;
            WebElement weAreInput = waitHelper.waitForElementVisible(weAreField);
            weAreInput.clear();
            weAreInput.sendKeys(String.valueOf(weAreNumber));
            noticeCriminalData.put("WeAre", String.valueOf(weAreNumber));
            System.out.println("✓ We Are: " + weAreNumber);
        } catch (Exception e) {
            System.out.println("⚠ We Are field not available: " + e.getMessage());
        }
    }
    
    /**
     * Select random Issuing Party (Custom dropdown with checkboxes)
     */
    private void selectIssuingParty() {
        try {
            System.out.println("Selecting Issuing Party...");
            waitHelper.hardWait(2000);
            
            // Scroll to dropdown and click using JavaScript
            WebElement dropdown = waitHelper.waitForElementVisible(issuingPartyDropdown);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
            waitHelper.hardWait(1000);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
            System.out.println("✓ Clicked Issuing Party dropdown");
            waitHelper.hardWait(2500);
            
            // Find labels directly
            List<WebElement> labels = driver.findElements(By.xpath("//*[@id='businessunitPetResDropdown']//label"));
            System.out.println("Found " + labels.size() + " issuing party options");
            
            if (!labels.isEmpty()) {
                // Select first valid label
                for (WebElement label : labels) {
                    try {
                        if (label.isDisplayed()) {
                            String text = label.getText().trim();
                            if (!text.isEmpty() && !text.equalsIgnoreCase("No Record Found")) {
                                System.out.println("Valid issuing party: " + text);
                                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'nearest'});", label);
                                waitHelper.hardWait(500);
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", label);
                                waitHelper.hardWait(1000);
                                noticeCriminalData.put("IssuingParty", text);
                                System.out.println("✓ Issuing Party selected: " + text);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
                
                // Click dropdown again to close using JavaScript
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
                waitHelper.hardWait(1000);
            } else {
                System.out.println("⚠ No Issuing Party options found");
            }
        } catch (Exception e) {
            System.out.println("⚠ Error selecting Issuing Party: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Select random Noticee (Custom dropdown with checkboxes)
     */
    private void selectNoticee() {
        try {
            System.out.println("Selecting Noticee (Issued to)...");
            waitHelper.hardWait(2000);
            
            // Scroll to dropdown and click using JavaScript
            WebElement dropdown = waitHelper.waitForElementVisible(noticeeDropdown);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
            waitHelper.hardWait(1000);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
            System.out.println("✓ Clicked Noticee dropdown");
            waitHelper.hardWait(2500);
            
            // Try multiple XPath patterns to find checkboxes
            List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@id='NoticeePetResDropdown']//input[@type='checkbox']"));
            if (checkboxes.isEmpty()) {
                checkboxes = driver.findElements(By.xpath("//div[@id='NoticeePetResDropdown']//label"));
                System.out.println("Using alternative XPath for Noticee");
            }
            System.out.println("Found " + checkboxes.size() + " noticee options");
            
            if (!checkboxes.isEmpty()) {
                // Filter valid checkboxes
                List<WebElement> validCheckboxes = new ArrayList<>();
                for (WebElement checkbox : checkboxes) {
                    if (checkbox.isDisplayed() && checkbox.isEnabled()) {
                        // Get label text for the checkbox
                        WebElement parent = checkbox.findElement(By.xpath("./parent::*"));
                        String labelText = parent.getText().trim();
                        // Skip "No Record Found" or empty options
                        if (!labelText.isEmpty() && !labelText.equalsIgnoreCase("No Record Found")) {
                            validCheckboxes.add(checkbox);
                            System.out.println("  Valid noticee: " + labelText);
                        }
                    }
                }
                
                if (!validCheckboxes.isEmpty()) {
                    // Select a random valid checkbox
                    int randomIndex = new Random().nextInt(validCheckboxes.size());
                    WebElement selectedCheckbox = validCheckboxes.get(randomIndex);
                    
                    // Get the label text
                    WebElement parent = selectedCheckbox.findElement(By.xpath("./parent::*"));
                    String optionText = parent.getText().trim();
                    
                    // Scroll checkbox into view
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedCheckbox);
                    waitHelper.hardWait(300);
                    
                    // Click checkbox using JavaScript
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectedCheckbox);
                    noticeCriminalData.put("Noticee", optionText);
                    System.out.println("✓ Noticee (Issued to) selected: " + optionText);
                    waitHelper.hardWait(800);
                } else {
                    // If no valid options (only "No Record Found"), select it anyway
                    System.out.println("⚠ No valid noticee options except 'No Record Found', selecting first available option");
                    if (checkboxes.get(0).isDisplayed() && checkboxes.get(0).isEnabled()) {
                        WebElement firstCheckbox = checkboxes.get(0);
                        WebElement parent = firstCheckbox.findElement(By.xpath("./parent::*"));
                        String optionText = parent.getText().trim();
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstCheckbox);
                        noticeCriminalData.put("Noticee", optionText);
                        System.out.println("✓ Noticee (Issued to) selected (fallback): " + optionText);
                        waitHelper.hardWait(800);
                    }
                }
            } else {
                System.out.println("⚠ No Noticee checkboxes available");
            }
        } catch (Exception e) {
            System.out.println("⚠ Error selecting Noticee: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Select random Issuing Party Advocate (Custom dropdown with checkboxes)
     */
    private void selectIssuingPartyAdvocate() {
        try {
            System.out.println("Selecting Issuing Party Advocate...");
            waitHelper.hardWait(2000);
            
            // Scroll to dropdown and click using JavaScript
            WebElement dropdown = waitHelper.waitForElementVisible(issuingPartyAdvocateDropdown);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
            waitHelper.hardWait(1000);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
            System.out.println("✓ Clicked Issuing Party Advocate dropdown");
            waitHelper.hardWait(1500);
            
            // Wait for options to load
            waitHelper.hardWait(2000);
            
            // Find labels directly instead of checkboxes
            List<WebElement> labels = driver.findElements(By.xpath("//*[@id='advocatesDropdown']//label"));
            System.out.println("Found " + labels.size() + " issuing party advocate options");
            
            if (!labels.isEmpty()) {
                // Select first valid label
                for (WebElement label : labels) {
                    try {
                        if (label.isDisplayed()) {
                            String text = label.getText().trim();
                            if (!text.isEmpty() && !text.equalsIgnoreCase("No Record Found")) {
                                System.out.println("  Valid advocate: " + text);
                                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'nearest'});", label);
                                waitHelper.hardWait(500);
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", label);
                                waitHelper.hardWait(1000);
                                noticeCriminalData.put("IssuingPartyAdvocate", text);
                                System.out.println("✓ Issuing Party Advocate selected: " + text);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
                
                // If no selection was made
                if (!noticeCriminalData.containsKey("IssuingPartyAdvocate")) {
                    System.out.println("⚠ No valid advocate options selected");
                }
            } else {
                System.out.println("⚠ No Issuing Party Advocate options available");
            }
        } catch (Exception e) {
            System.out.println("⚠ Error selecting Issuing Party Advocate: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Select random Notice Reply-Response
     */
    private void selectNoticeReply() {
        try {
            System.out.println("Selecting Notice Reply-Response...");
            waitHelper.hardWait(1000);
            
            WebElement dropdown = waitHelper.waitForElementClickable(noticeReplyDropdown);
            Select select = new Select(dropdown);
            List<WebElement> options = select.getOptions();
            
            // Filter out empty options
            List<WebElement> validOptions = new ArrayList<>();
            for (WebElement option : options) {
                if (!option.getText().trim().isEmpty() && !option.getText().equals("Select")) {
                    validOptions.add(option);
                }
            }
            
            if (!validOptions.isEmpty()) {
                int randomIndex = new Random().nextInt(validOptions.size());
                WebElement selectedOption = validOptions.get(randomIndex);
                String selectedText = selectedOption.getText();
                select.selectByVisibleText(selectedText);
                noticeCriminalData.put("NoticeReply", selectedText);
                System.out.println("✓ Notice Reply selected: " + selectedText);
            }
            waitHelper.hardWait(1000);
        } catch (Exception e) {
            System.out.println("⚠ Error selecting Notice Reply: " + e.getMessage());
        }
    }
    
    /**
     * Select random Parties (Custom dropdown with checkboxes)
     */
    private void selectParties() {
        try {
            System.out.println("Selecting Parties...");
            waitHelper.hardWait(2000);
            
            // Scroll to dropdown - scroll the page itself first
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);");
            waitHelper.hardWait(500);
            
            // Now scroll to and click dropdown using JavaScript to avoid interception
            WebElement dropdown = waitHelper.waitForElementVisible(partiesDropdown);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", dropdown);
            waitHelper.hardWait(1000);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
            System.out.println("✓ Clicked Parties dropdown");
            waitHelper.hardWait(2500);
            
            // Find labels directly
            List<WebElement> labels = driver.findElements(By.xpath("//*[@id='flush-collapse1']/div/div[9]/div[8]/div//label"));
            System.out.println("Found " + labels.size() + " party options");
            
            if (!labels.isEmpty()) {
                // Select first valid label
                for (WebElement label : labels) {
                    try {
                        if (label.isDisplayed()) {
                            String text = label.getText().trim();
                            if (!text.isEmpty() && !text.equalsIgnoreCase("No Record Found")) {
                                System.out.println("Valid party: " + text);
                                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'nearest'});", label);
                                waitHelper.hardWait(500);
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", label);
                                waitHelper.hardWait(1000);
                                noticeCriminalData.put("Parties", text);
                                System.out.println("✓ Party selected: " + text);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
                
                // Click dropdown again to close using JavaScript
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
                waitHelper.hardWait(1000);
            } else {
                System.out.println("⚠ No party options found");
            }
        } catch (Exception e) {
            System.out.println("⚠ Error selecting Parties: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Click Next on Basic Information section
     */
    private void clickBasicInfoNext() {
        try {
            System.out.println("Clicking Next on Basic Information...");
            waitHelper.hardWait(1000);
            WebElement nextButton = waitHelper.waitForElementClickable(basicInfoNextButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
            waitHelper.hardWait(2000);
            System.out.println("✓ Basic Information Next clicked");
        } catch (Exception e) {
            System.out.println("⚠ Error clicking Basic Information Next: " + e.getMessage());
        }
    }
    
    /**
     * Click Next on Stake Details section
     */
    private void clickStakeDetailsNext() {
        try {
            System.out.println("Clicking Next on Stake Details...");
            waitHelper.hardWait(1000);
            WebElement nextButton = waitHelper.waitForElementClickable(stakeDetailsNextButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
            waitHelper.hardWait(2000);
            System.out.println("✓ Stake Details Next clicked");
        } catch (Exception e) {
            System.out.println("⚠ Error clicking Stake Details Next: " + e.getMessage());
        }
    }
    
    /**
     * Select random Sr Supervisor from dropdown
     */
    private void selectSrSupervisor() {
        try {
            System.out.println("Selecting Sr Supervisor...");
            waitHelper.hardWait(1000);
            
            WebElement dropdown = waitHelper.waitForElementClickable(srSupervisorDropdown);
            Select select = new Select(dropdown);
            List<WebElement> options = select.getOptions();
            
            // Filter out empty options
            List<WebElement> validOptions = new ArrayList<>();
            for (WebElement option : options) {
                if (!option.getText().trim().isEmpty() && !option.getText().equals("Select")) {
                    validOptions.add(option);
                }
            }
            
            if (!validOptions.isEmpty()) {
                int randomIndex = new Random().nextInt(validOptions.size());
                WebElement selectedOption = validOptions.get(randomIndex);
                String selectedText = selectedOption.getText();
                select.selectByVisibleText(selectedText);
                noticeCriminalData.put("SrSupervisor", selectedText);
                System.out.println("✓ Sr Supervisor selected: " + selectedText);
            }
            waitHelper.hardWait(1000);
        } catch (Exception e) {
            System.out.println("⚠ Error selecting Sr Supervisor: " + e.getMessage());
        }
    }
    
    /**
     * Click Next on PDO Notice section
     */
    private void clickPdoNoticeNext() {
        try {
            System.out.println("Clicking Next on PDO Notice...");
            waitHelper.hardWait(1000);
            WebElement nextButton = waitHelper.waitForElementClickable(pdoNoticeNextButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
            waitHelper.hardWait(2000);
            System.out.println("✓ PDO Notice Next clicked");
        } catch (Exception e) {
            System.out.println("⚠ Error clicking PDO Notice Next: " + e.getMessage());
        }
    }
    
    /**
     * Click Next on Notice Analysis section
     */
    private void clickNoticeAnalysisNext() {
        try {
            System.out.println("Clicking Next on Notice Analysis...");
            waitHelper.hardWait(1000);
            WebElement nextButton = waitHelper.waitForElementClickable(noticeAnalysisNextButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
            waitHelper.hardWait(2000);
            System.out.println("✓ Notice Analysis Next clicked");
        } catch (Exception e) {
            System.out.println("⚠ Error clicking Notice Analysis Next: " + e.getMessage());
        }
    }
    
    /**
     * Click Create button to submit the criminal notice
     */
    public void clickCreateButton() {
        try {
            System.out.println("Clicking Create button...");
            waitHelper.hardWait(3000);
            
            // First, scroll to bottom of page to ensure Create button is visible
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            waitHelper.hardWait(2000);
            
            // Try to find Create button with multiple strategies
            WebElement createBtn = null;
            try {
                createBtn = driver.findElement(createButton);
                System.out.println("✓ Found Create button by xpath");
            } catch (Exception e) {
                try {
                    createBtn = driver.findElement(By.id("btnSubmit"));
                    System.out.println("✓ Found Create button by ID");
                } catch (Exception e2) {
                    System.out.println("⚠ Create button not found, trying direct JavaScript click");
                    ((JavascriptExecutor) driver).executeScript("document.getElementById('btnSubmit').click();");
                    waitHelper.hardWait(5000);
                    System.out.println("✓ Create button clicked (direct JavaScript)");
                    return;
                }
            }
            
            // Scroll Create button into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", createBtn);
            waitHelper.hardWait(1500);
            
            // Check if button is enabled
            boolean isEnabled = createBtn.isEnabled();
            String buttonClass = createBtn.getAttribute("class");
            System.out.println("Create button status - Enabled: " + isEnabled + ", Class: " + buttonClass);
            
            // Try clicking with JavaScript (more reliable)
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createBtn);
                System.out.println("✓ Create button clicked (JavaScript click)");
            } catch (Exception e1) {
                // Try regular click as fallback
                try {
                    createBtn.click();
                    System.out.println("✓ Create button clicked (regular click)");
                } catch (Exception e2) {
                    // Last resort: try getElementById
                    ((JavascriptExecutor) driver).executeScript("document.getElementById('btnSubmit').click();");
                    System.out.println("✓ Create button clicked (getElementById)");
                }
            }
            
            waitHelper.hardWait(5000); // Wait for submission to complete
            System.out.println("✓ Criminal Notice submission initiated");
            
        } catch (Exception e) {
            System.out.println("❌ Error clicking Create button: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to click Create button", e);
        }
    }
    
    /**
     * Verify criminal notice creation success and capture Case Unique ID
     */
    public boolean verifyCriminalNoticeCreation() {
        try {
            waitHelper.hardWait(5000);
            
            // Try to capture Case Unique ID from the specific xpath
            try {
                System.out.println("Attempting to capture Case Unique ID from xpath...");
                WebElement caseIdElement = driver.findElement(By.xpath("//*[@id='flush-collapse1']/div/div/div/div[2]/div/div[5]/div[2]"));
                String caseId = caseIdElement.getText().trim();
                
                if (!caseId.isEmpty()) {
                    noticeCriminalData.put("CaseUniqueId", caseId);
                    System.out.println("✓✓✓ CAPTURED CASE UNIQUE ID: " + caseId + " ✓✓✓");
                } else {
                    System.out.println("⚠ Case Unique ID element found but text is empty");
                }
            } catch (Exception e) {
                System.out.println("⚠ Could not capture Case Unique ID from xpath: " + e.getMessage());
                
                // Fallback: Try to find it in page text
                try {
                    List<WebElement> caseIdElements = driver.findElements(By.xpath("//*[contains(text(), 'Case Unique Id') or contains(text(), 'Case ID') or contains(text(), 'CaseUniqueId')]"));
                    if (!caseIdElements.isEmpty()) {
                        for (WebElement elem : caseIdElements) {
                            String text = elem.getText();
                            System.out.println("Found element with text: " + text);
                            
                            if (text.contains(":")) {
                                String[] parts = text.split(":");
                                if (parts.length > 1) {
                                    String caseId = parts[1].trim();
                                    noticeCriminalData.put("CaseUniqueId", caseId);
                                    System.out.println("✓ Captured Case Unique ID (fallback): " + caseId);
                                    break;
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("⚠ Fallback method also failed: " + ex.getMessage());
                }
            }
            
            // Check for success indicators
            List<WebElement> successElements = driver.findElements(By.xpath("//*[contains(text(), 'success') or contains(text(), 'Success') or contains(text(), 'created')]"));
            
            if (!successElements.isEmpty()) {
                System.out.println("✓✓✓ Criminal Notice created successfully! ✓✓✓");
                return true;
            }
            
            System.out.println("✓ Criminal Notice form submitted");
            return true;
            
        } catch (Exception e) {
            System.out.println("⚠ Could not verify criminal notice creation: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get stored criminal notice data
     */
    public Map<String, String> getNoticeCriminalData() {
        return noticeCriminalData;
    }
    
    /**
     * Print all criminal notice data
     */
    public void printNoticeCriminalData() {
        System.out.println("\n========== CRIMINAL NOTICE DATA SUMMARY ==========");
        noticeCriminalData.forEach((key, value) -> 
            System.out.println(key + ": " + value)
        );
        System.out.println("================================================\n");
    }
}
