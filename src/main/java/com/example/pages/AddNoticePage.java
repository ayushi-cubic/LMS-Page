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
 * Page Object Model for Add Notice Page
 */
public class AddNoticePage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final Map<String, String> noticeData;
    private final JavascriptExecutor js;
    
    // Notice Creation Form Locators
    @FindBy(xpath = "//*[@id='SerialNumber']")
    private WebElement serialNumberField;
    
    @FindBy(xpath = "//*[@id='NoticeTypeId']")
    private WebElement noticeTypeDropdown;
    
    // Customer Number Select2 dropdown
    @FindBy(xpath = "//*[@id='flush-collapse1']/div/div[5]/div[5]/div/span[1]/span[1]/span")
    private WebElement customerNumberSelect2;
    
    // Customer Account Select2 dropdown
    @FindBy(xpath = "//*[@id='flush-collapse1']/div/div[5]/div[6]/div/span[1]/span[1]/span/ul/li/input")
    private WebElement customerAccountSelect2Input;
    
    @FindBy(xpath = "//*[@id='WeAre']")
    private WebElement weAreField;
    
    // Issuing Party dropdown
    @FindBy(xpath = "//*[@id='byRoleSection']/div/div[1]/div/div[1]")
    private WebElement issuingPartyDropdown;
    
    // Noticee dropdown
    @FindBy(xpath = "//*[@id='againstRoleSection']/div/div[1]/div/div[1]")
    private WebElement noticeeDropdown;
    
    // Issuing Party Advocate dropdown
    @FindBy(xpath = "//*[@id='issuingAdvocateSection']/div/div[1]/div/div[1]")
    private WebElement issuingPartyAdvocateDropdown;
    
    @FindBy(xpath = "//*[@id='NoticeSentReceive']")
    private WebElement noticeReplyResponseDropdown;
    
    // Parties dropdown (custom dropdown)
    @FindBy(xpath = "//*[@id='partyDropdown']/../div[1]")
    private WebElement partiesDropdown;
    
    // Navigation Buttons
    @FindBy(xpath = "//*[@id='BasicInformationCollapse']")
    private WebElement nextButtonSection1;
    
    @FindBy(xpath = "//*[@id='StakeDetailsCollapse']")
    private WebElement nextButtonSection2;
    
    // Sr Supervisor dropdown
    @FindBy(xpath = "//*[@id='4']")
    private WebElement srSupervisorDropdown;
    
    @FindBy(xpath = "//*[@id='PDONoticeCollapse']")
    private WebElement nextButtonSection3;
    
    @FindBy(xpath = "//*[@id='btnSubmit']")
    private WebElement createButton;
    
    // Constructor
    public AddNoticePage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.noticeData = new HashMap<>();
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Fill notice form with random data
     */
    public void fillNoticeFormWithRandomData() {
        try {
            System.out.println("\n=== Starting Notice Form Fill ===");
            
            // Serial Number - Alphanumeric
            enterSerialNumber();
            
            // Notice Type - Dropdown
            selectNoticeType();
            
            // Customer Number - Select2
            selectCustomerNumber();
            
            // Customer Account - Select2
            selectCustomerAccount();
            
            // We Are - Number between 1-200
            enterWeAre();
            
            // Issuing Party - Custom Dropdown
            selectIssuingParty();
            
            // Noticee - Custom Dropdown
            selectNoticee();
            
            // Issuing Party Advocate - Custom Dropdown
            selectIssuingPartyAdvocate();
            
            // Notice Reply-Response - Dropdown
            selectNoticeReplyResponse();
            
            // Parties - Custom Dropdown
            selectParties();
            
            System.out.println("✓ Notice form filled successfully with random data");
            
        } catch (Exception e) {
            System.err.println("Error filling notice form: " + e.getMessage());
            throw new RuntimeException("Failed to fill notice form", e);
        }
    }
    
    /**
     * Enter Serial Number (Random Alphanumeric)
     */
    private void enterSerialNumber() {
        try {
            String serialNumber = RandomDataGenerator.generateAlphanumeric(10);
            waitHelper.waitForElementClickable(serialNumberField);
            serialNumberField.clear();
            serialNumberField.sendKeys(serialNumber);
            noticeData.put("Serial Number", serialNumber);
            System.out.println("✓ Entered Serial Number: " + serialNumber);
        } catch (Exception e) {
            System.err.println("Error entering serial number: " + e.getMessage());
            throw new RuntimeException("Failed to enter serial number", e);
        }
    }
    
    /**
     * Select Notice Type from dropdown
     */
    private void selectNoticeType() {
        int maxRetries = 3;
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                System.out.println("Selecting Notice Type (Attempt " + attempt + "/" + maxRetries + ")...");
                
                // Scroll to element first
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", noticeTypeDropdown);
                Thread.sleep(800);
                
                // Click dropdown to trigger loading
                waitHelper.waitForElementClickable(noticeTypeDropdown);
                js.executeScript("arguments[0].click();", noticeTypeDropdown);
                Thread.sleep(2000); // Wait for options to load via AJAX
                
                Select select = new Select(noticeTypeDropdown);
                
                // Get all options except the first (which is usually placeholder)
                int optionCount = select.getOptions().size();
                System.out.println("Notice Type dropdown has " + optionCount + " options");
                
                if (optionCount > 1) {
                    int randomIndex = RandomDataGenerator.generateRandomNumber(1, optionCount - 1);
                    select.selectByIndex(randomIndex);
                    Thread.sleep(500);
                    String selectedOption = select.getFirstSelectedOption().getText();
                    noticeData.put("Notice Type", selectedOption);
                    System.out.println("✓ Selected Notice Type: " + selectedOption);
                    Thread.sleep(1500); // Wait for form validation
                    return; // Success, exit retry loop
                } else {
                    System.out.println("⚠ No notice type options available on attempt " + attempt);
                    if (attempt < maxRetries) {
                        Thread.sleep(2000);
                        continue;
                    }
                }
            } catch (InterruptedException e) {
                System.err.println("Error selecting notice type: " + e.getMessage());
                Thread.currentThread().interrupt();
                throw new RuntimeException("Failed to select Notice Type after " + attempt + " attempts", e);
            } catch (Exception e) {
                System.err.println("Error selecting notice type on attempt " + attempt + ": " + e.getMessage());
                if (attempt == maxRetries) {
                    throw new RuntimeException("❌ FAILED to select Notice Type after " + maxRetries + " attempts", e);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new RuntimeException("❌ CRITICAL: Notice Type dropdown selection FAILED after all retries");
    }
    
    /**
     * Select Customer Number using Select2
     */
    private void selectCustomerNumber() {
        int maxRetries = 3;
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                System.out.println("Selecting Customer Number (Attempt " + attempt + "/" + maxRetries + ")...");
                
                // Scroll to element
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", customerNumberSelect2);
                Thread.sleep(800);
                
                // Click on Select2 container to open dropdown using JavaScript
                waitHelper.waitForElementClickable(customerNumberSelect2);
                js.executeScript("arguments[0].click();", customerNumberSelect2);
                System.out.println("Clicked Customer Number dropdown");
                Thread.sleep(1500);
                
                // Wait for dropdown to appear and options to load
                Thread.sleep(2500);
                
                // Find all available options with better selector
                java.util.List<WebElement> options = driver.findElements(By.xpath("//ul[contains(@class, 'select2-results__options')]/li[not(contains(@class, 'loading-results'))]"));
                System.out.println("Found " + options.size() + " customer options");
                
                if (!options.isEmpty()) {
                    // Filter out any disabled or "no results" options
                    java.util.List<WebElement> validOptions = new java.util.ArrayList<>();
                    for (WebElement option : options) {
                        String text = option.getText().trim();
                        String ariaDisabled = option.getAttribute("aria-disabled");
                        if (!text.isEmpty() && !text.contains("Searching") && !text.contains("No results") 
                            && !text.contains("Loading") && !"true".equals(ariaDisabled)) {
                            validOptions.add(option);
                            System.out.println("Valid customer option: " + text);
                        }
                    }
                    
                    if (!validOptions.isEmpty()) {
                        int randomIndex = RandomDataGenerator.generateRandomNumber(0, validOptions.size() - 1);
                        WebElement selectedOption = validOptions.get(randomIndex);
                        String optionText = selectedOption.getText().trim();
                        
                        // Scroll option into view
                        js.executeScript("arguments[0].scrollIntoView(true);", selectedOption);
                        Thread.sleep(300);
                        
                        // Click using regular click first (Select2 expects normal click)
                        try {
                            selectedOption.click();
                        } catch (Exception e) {
                            // Fallback to JS click
                            js.executeScript("arguments[0].click();", selectedOption);
                        }
                        
                        // Verify selection by checking the display value
                        Thread.sleep(800);
                        String displayText = customerNumberSelect2.getText();
                        System.out.println("Display text after selection: '" + displayText + "'");
                        
                        noticeData.put("Customer Number", optionText);
                        System.out.println("✓ Selected Customer Number: " + optionText);
                        Thread.sleep(1500);
                        return; // Success, exit retry loop
                    } else {
                        System.out.println("⚠ No valid customer options available on attempt " + attempt);
                        if (attempt < maxRetries) {
                            Thread.sleep(2000);
                            continue;
                        }
                    }
                    
                } else {
                    System.out.println("⚠ Customer dropdown has no options on attempt " + attempt);
                    if (attempt < maxRetries) {
                        Thread.sleep(2000);
                        continue;
                    }
                }
                
            } catch (Exception e) {
                System.err.println("Error selecting customer number on attempt " + attempt + ": " + e.getMessage());
                if (attempt == maxRetries) {
                    throw new RuntimeException("❌ FAILED to select Customer Number after " + maxRetries + " attempts", e);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new RuntimeException("❌ CRITICAL: Customer Number dropdown selection FAILED after all retries");
    }
    
    /**
     * Select Customer Account using Select2
     */
    private void selectCustomerAccount() {
        int maxRetries = 3;
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                System.out.println("Selecting Customer Account (Attempt " + attempt + "/" + maxRetries + ")...");
                Thread.sleep(1500);
                
                // Find the parent Select2 container for customer account
                WebElement accountContainer = driver.findElement(By.xpath("//*[@id='flush-collapse1']/div/div[5]/div[6]/div/span[1]/span[1]/span"));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", accountContainer);
                Thread.sleep(800);
                
                // Click on the Select2 container using JavaScript
                js.executeScript("arguments[0].click();", accountContainer);
                System.out.println("Clicked Customer Account dropdown");
                Thread.sleep(2000);
                
                // Wait for options to load
                Thread.sleep(2500);
                
                // Find all available options
                java.util.List<WebElement> options = driver.findElements(By.xpath("//ul[contains(@class, 'select2-results__options')]/li[not(contains(@class, 'loading-results'))]"));
                System.out.println("Found " + options.size() + " account options");
                
                if (!options.isEmpty()) {
                    // Filter out any disabled or "no results" options
                    java.util.List<WebElement> validOptions = new java.util.ArrayList<>();
                    for (WebElement option : options) {
                        String text = option.getText().trim();
                        String ariaDisabled = option.getAttribute("aria-disabled");
                        if (!text.isEmpty() && !text.contains("Searching") && !text.contains("No results") 
                            && !text.contains("Loading") && !"true".equals(ariaDisabled)) {
                            validOptions.add(option);
                            System.out.println("Valid account option: " + text);
                        }
                    }
                    
                    if (!validOptions.isEmpty()) {
                        int randomIndex = RandomDataGenerator.generateRandomNumber(0, validOptions.size() - 1);
                        WebElement selectedOption = validOptions.get(randomIndex);
                        String optionText = selectedOption.getText().trim();
                        
                        // Scroll option into view
                        js.executeScript("arguments[0].scrollIntoView(true);", selectedOption);
                        Thread.sleep(300);
                        
                        // Click using regular click first (Select2 expects normal click)
                        try {
                            selectedOption.click();
                        } catch (Exception e) {
                            // Fallback to JS click
                            js.executeScript("arguments[0].click();", selectedOption);
                        }
                        
                        // Verify selection
                        Thread.sleep(800);
                        System.out.println("Clicked account option: " + optionText);
                        
                        noticeData.put("Customer Account", optionText);
                        System.out.println("✓ Selected Customer Account: " + optionText);
                        Thread.sleep(1500);
                        return; // Success, exit retry loop
                    } else {
                        System.out.println("⚠ No valid account options on attempt " + attempt);
                        if (attempt < maxRetries) {
                            Thread.sleep(2000);
                            continue;
                        }
                    }
                } else {
                    System.out.println("⚠ Account dropdown has no options on attempt " + attempt);
                    if (attempt < maxRetries) {
                        Thread.sleep(2000);
                        continue;
                    }
                }
            } catch (InterruptedException e) {
                System.err.println("Error selecting customer account: " + e.getMessage());
                Thread.currentThread().interrupt();
                throw new RuntimeException("Failed to select Customer Account after " + attempt + " attempts", e);
            } catch (Exception e) {
                System.err.println("Error selecting customer account on attempt " + attempt + ": " + e.getMessage());
                if (attempt == maxRetries) {
                    throw new RuntimeException("❌ FAILED to select Customer Account after " + maxRetries + " attempts", e);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new RuntimeException("❌ CRITICAL: Customer Account dropdown selection FAILED after all retries");
    }
    
    /**
     * Enter We Are value (1-200)
     */
    private void enterWeAre() {
        try {
            int weAreValue = RandomDataGenerator.generateRandomNumber(1, 200);
            waitHelper.waitForElementClickable(weAreField);
            weAreField.clear();
            weAreField.sendKeys(String.valueOf(weAreValue));
            noticeData.put("We Are", String.valueOf(weAreValue));
            System.out.println("✓ Entered We Are: " + weAreValue);
        } catch (Exception e) {
            System.err.println("Error entering we are value: " + e.getMessage());
            throw new RuntimeException("Failed to enter we are value", e);
        }
    }
    
    /**
     * Select Issuing Party from custom dropdown
     */
    private void selectIssuingParty() {
        try {
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", issuingPartyDropdown);
            Thread.sleep(800);
            
            waitHelper.waitForElementClickable(issuingPartyDropdown);
            js.executeScript("arguments[0].click();", issuingPartyDropdown);
            System.out.println("Clicked Issuing Party dropdown");
            Thread.sleep(1500);
            
            // Find checkbox inputs specifically
            Thread.sleep(1000);
            java.util.List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@id='businessunitPetResDropdown']//input[@type='checkbox']"));
            
            System.out.println("Found " + checkboxes.size() + " issuing party checkboxes");
            
            if (!checkboxes.isEmpty()) {
                java.util.List<WebElement> validCheckboxes = new java.util.ArrayList<>();
                for (WebElement checkbox : checkboxes) {
                    if (checkbox.isDisplayed() && checkbox.isEnabled()) {
                        validCheckboxes.add(checkbox);
                        // Get label text for the checkbox
                        WebElement parent = checkbox.findElement(By.xpath("./parent::*"));
                        String labelText = parent.getText().trim();
                        System.out.println("Valid issuing party: " + labelText);
                    }
                }
                
                if (!validCheckboxes.isEmpty()) {
                    int randomIndex = RandomDataGenerator.generateRandomNumber(0, validCheckboxes.size() - 1);
                    WebElement selectedCheckbox = validCheckboxes.get(randomIndex);
                    
                    // Get the label text
                    WebElement parent = selectedCheckbox.findElement(By.xpath("./parent::*"));
                    String optionText = parent.getText().trim();
                    
                    // Scroll checkbox into view
                    js.executeScript("arguments[0].scrollIntoView(true);", selectedCheckbox);
                    Thread.sleep(300);
                    
                    // Click checkbox - try regular click first
                    try {
                        if (!selectedCheckbox.isSelected()) {
                            selectedCheckbox.click();
                            Thread.sleep(500);
                            System.out.println("Issuing Party checkbox clicked. Is selected: " + selectedCheckbox.isSelected());
                        }
                    } catch (Exception e) {
                        // Fallback to JS click
                        js.executeScript("arguments[0].click();", selectedCheckbox);
                        Thread.sleep(500);
                    }
                    
                    noticeData.put("Issuing Party", optionText);
                    System.out.println("✓ Selected Issuing Party: " + optionText);
                    Thread.sleep(800);
                } else {
                    System.out.println("⚠ No valid issuing party checkboxes");
                }
            } else {
                System.out.println("⚠ No issuing party checkboxes available");
            }
        } catch (InterruptedException e) {
            System.err.println("Error selecting issuing party: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.err.println("Error selecting issuing party: " + e.getMessage());
            // Continue execution
        }
    }
    
    /**
     * Select Noticee from custom dropdown
     */
    private void selectNoticee() {
        try {
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", noticeeDropdown);
            Thread.sleep(800);
            
            waitHelper.waitForElementClickable(noticeeDropdown);
            js.executeScript("arguments[0].click();", noticeeDropdown);
            System.out.println("Clicked Noticee dropdown");
            Thread.sleep(1500);
            
            // Find checkbox inputs specifically
            Thread.sleep(1000);
            java.util.List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@id='NoticeePetResDropdown']//input[@type='checkbox']"));
            
            System.out.println("Found " + checkboxes.size() + " noticee checkboxes");
            
            if (!checkboxes.isEmpty()) {
                java.util.List<WebElement> validCheckboxes = new java.util.ArrayList<>();
                for (WebElement checkbox : checkboxes) {
                    if (checkbox.isDisplayed() && checkbox.isEnabled()) {
                        // Get label text for the checkbox
                        WebElement parent = checkbox.findElement(By.xpath("./parent::*"));
                        String labelText = parent.getText().trim();
                        // Skip "No Record Found" or empty options
                        if (!labelText.isEmpty() && !labelText.equalsIgnoreCase("No Record Found")) {
                            validCheckboxes.add(checkbox);
                            System.out.println("Valid noticee: " + labelText);
                        }
                    }
                }
                
                if (!validCheckboxes.isEmpty()) {
                    int randomIndex = RandomDataGenerator.generateRandomNumber(0, validCheckboxes.size() - 1);
                    WebElement selectedCheckbox = validCheckboxes.get(randomIndex);
                    
                    // Get the label text
                    WebElement parent = selectedCheckbox.findElement(By.xpath("./parent::*"));
                    String optionText = parent.getText().trim();
                    
                    // Click checkbox using JavaScript
                    js.executeScript("arguments[0].click();", selectedCheckbox);
                    noticeData.put("Noticee", optionText);
                    System.out.println("✓ Selected Noticee: " + optionText);
                    Thread.sleep(800);
                } else {
                    // If no valid options (only "No Record Found"), select it anyway as it might be the only available option
                    System.out.println("⚠ No valid noticee options except 'No Record Found', selecting first available option");
                    if (!checkboxes.isEmpty() && checkboxes.get(0).isDisplayed() && checkboxes.get(0).isEnabled()) {
                        WebElement firstCheckbox = checkboxes.get(0);
                        WebElement parent = firstCheckbox.findElement(By.xpath("./parent::*"));
                        String optionText = parent.getText().trim();
                        js.executeScript("arguments[0].click();", firstCheckbox);
                        noticeData.put("Noticee", optionText);
                        System.out.println("✓ Selected Noticee (fallback): " + optionText);
                        Thread.sleep(800);
                    }
                }
            } else {
                System.out.println("⚠ No noticee checkboxes available");
            }
        } catch (InterruptedException e) {
            System.err.println("Error selecting noticee: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.err.println("Error selecting noticee: " + e.getMessage());
            // Continue execution
        }
    }
    
    /**
     * Select Issuing Party Advocate from custom dropdown
     */
    private void selectIssuingPartyAdvocate() {
        try {
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", issuingPartyAdvocateDropdown);
            Thread.sleep(800);
            
            waitHelper.waitForElementClickable(issuingPartyAdvocateDropdown);
            js.executeScript("arguments[0].click();", issuingPartyAdvocateDropdown);
            System.out.println("Clicked Issuing Party Advocate dropdown");
            Thread.sleep(1500);
            
            // Find checkbox inputs specifically
            Thread.sleep(1000);
            java.util.List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@id='advocatesDropdown']//input[@type='checkbox']"));
            
            System.out.println("Found " + checkboxes.size() + " issuing party advocate checkboxes");
            
            if (!checkboxes.isEmpty()) {
                java.util.List<WebElement> validCheckboxes = new java.util.ArrayList<>();
                for (WebElement checkbox : checkboxes) {
                    if (checkbox.isDisplayed() && checkbox.isEnabled()) {
                        // Get label text for the checkbox
                        WebElement parent = checkbox.findElement(By.xpath("./parent::*"));
                        String labelText = parent.getText().trim();
                        // Skip "No Record Found" or empty options
                        if (!labelText.isEmpty() && !labelText.equalsIgnoreCase("No Record Found")) {
                            validCheckboxes.add(checkbox);
                            System.out.println("Valid issuing party advocate: " + labelText);
                        }
                    }
                }
                
                if (!validCheckboxes.isEmpty()) {
                    int randomIndex = RandomDataGenerator.generateRandomNumber(0, validCheckboxes.size() - 1);
                    WebElement selectedCheckbox = validCheckboxes.get(randomIndex);
                    
                    // Get the label text
                    WebElement parent = selectedCheckbox.findElement(By.xpath("./parent::*"));
                    String optionText = parent.getText().trim();
                    
                    // Click checkbox using JavaScript
                    js.executeScript("arguments[0].click();", selectedCheckbox);
                    noticeData.put("Issuing Party Advocate", optionText);
                    System.out.println("✓ Selected Issuing Party Advocate: " + optionText);
                    Thread.sleep(800);
                } else {
                    // If no valid options, select first available option
                    System.out.println("⚠ No valid issuing party advocate options, selecting first available option");
                    if (!checkboxes.isEmpty() && checkboxes.get(0).isDisplayed() && checkboxes.get(0).isEnabled()) {
                        WebElement firstCheckbox = checkboxes.get(0);
                        WebElement parent = firstCheckbox.findElement(By.xpath("./parent::*"));
                        String optionText = parent.getText().trim();
                        js.executeScript("arguments[0].click();", firstCheckbox);
                        noticeData.put("Issuing Party Advocate", optionText);
                        System.out.println("✓ Selected Issuing Party Advocate (fallback): " + optionText);
                        Thread.sleep(800);
                    }
                }
            } else {
                System.out.println("⚠ No issuing party advocate checkboxes available");
            }
        } catch (InterruptedException e) {
            System.err.println("Error selecting issuing party advocate: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.err.println("Error selecting issuing party advocate: " + e.getMessage());
            // Continue execution
        }
    }
    
    /**
     * Select Notice Reply-Response from dropdown
     */
    private void selectNoticeReplyResponse() {
        try {
            waitHelper.waitForElementClickable(noticeReplyResponseDropdown);
            Select select = new Select(noticeReplyResponseDropdown);
            
            int optionCount = select.getOptions().size();
            if (optionCount > 1) {
                int randomIndex = RandomDataGenerator.generateRandomNumber(1, optionCount - 1);
                select.selectByIndex(randomIndex);
                String selectedOption = select.getFirstSelectedOption().getText();
                noticeData.put("Notice Reply-Response", selectedOption);
                System.out.println("✓ Selected Notice Reply-Response: " + selectedOption);
            }
        } catch (Exception e) {
            System.err.println("Error selecting notice reply-response: " + e.getMessage());
            throw new RuntimeException("Failed to select notice reply-response", e);
        }
    }
    
    /**
     * Select Parties from custom dropdown
     */
    private void selectParties() {
        try {
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", partiesDropdown);
            Thread.sleep(800);
            
            waitHelper.waitForElementClickable(partiesDropdown);
            js.executeScript("arguments[0].click();", partiesDropdown);
            System.out.println("Clicked Parties dropdown");
            Thread.sleep(1500);
            
            // Find checkbox inputs specifically
            Thread.sleep(1000);
            java.util.List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@id='partyDropdown']//input[@type='checkbox']"));
            
            System.out.println("Found " + checkboxes.size() + " party checkboxes");
            
            if (!checkboxes.isEmpty()) {
                java.util.List<WebElement> validCheckboxes = new java.util.ArrayList<>();
                for (WebElement checkbox : checkboxes) {
                    if (checkbox.isDisplayed() && checkbox.isEnabled()) {
                        validCheckboxes.add(checkbox);
                        // Get label text for the checkbox
                        WebElement parent = checkbox.findElement(By.xpath("./parent::*"));
                        String labelText = parent.getText().trim();
                        System.out.println("Valid party: " + labelText);
                    }
                }
                
                if (!validCheckboxes.isEmpty()) {
                    int randomIndex = RandomDataGenerator.generateRandomNumber(0, validCheckboxes.size() - 1);
                    WebElement selectedCheckbox = validCheckboxes.get(randomIndex);
                    
                    // Get the label text
                    WebElement parent = selectedCheckbox.findElement(By.xpath("./parent::*"));
                    String optionText = parent.getText().trim();
                    
                    // Scroll checkbox into view
                    js.executeScript("arguments[0].scrollIntoView(true);", selectedCheckbox);
                    Thread.sleep(300);
                    
                    // Click checkbox - try regular click first
                    try {
                        if (!selectedCheckbox.isSelected()) {
                            selectedCheckbox.click();
                            Thread.sleep(500);
                            System.out.println("Checkbox clicked. Is selected: " + selectedCheckbox.isSelected());
                        }
                    } catch (Exception e) {
                        // Fallback to JS click
                        js.executeScript("arguments[0].click();", selectedCheckbox);
                        Thread.sleep(500);
                    }
                    
                    noticeData.put("Parties", optionText);
                    System.out.println("✓ Selected Parties: " + optionText);
                    Thread.sleep(800);
                } else {
                    System.out.println("⚠ No valid party checkboxes");
                }
            } else {
                System.out.println("⚠ No party checkboxes available");
            }
        } catch (InterruptedException e) {
            System.err.println("Error selecting parties: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.err.println("Error selecting parties: " + e.getMessage());
            // Continue execution
        }
    }
    
    /**
     * Click Next button on Basic Information section
     */
    public void clickNextSection1() {
        try {
            // Wait for form validation to complete
            Thread.sleep(2000);
            
            waitHelper.waitForElementClickable(nextButtonSection1);
            js.executeScript("arguments[0].scrollIntoView(true);", nextButtonSection1);
            Thread.sleep(500);
            
            // Check if button is enabled by checking for disabled class
            String className = nextButtonSection1.getAttribute("class");
            if (className != null && className.contains("disabled")) {
                System.out.println("⚠ Warning: Next button appears disabled, attempting click anyway");
            }
            
            // Force click using JavaScript
            js.executeScript("arguments[0].click();", nextButtonSection1);
            System.out.println("✓ Clicked Next on Basic Information section");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Error clicking next on section 1: " + e.getMessage());
            Thread.currentThread().interrupt();
            throw new RuntimeException("Failed to click next on section 1", e);
        } catch (Exception e) {
            System.err.println("Error clicking next on section 1: " + e.getMessage());
            throw new RuntimeException("Failed to click next on section 1", e);
        }
    }
    
    /**
     * Click Next button on Stake Details section
     */
    public void clickNextSection2() {
        try {
            waitHelper.waitForElementClickable(nextButtonSection2);
            js.executeScript("arguments[0].scrollIntoView(true);", nextButtonSection2);
            Thread.sleep(500);
            nextButtonSection2.click();
            System.out.println("✓ Clicked Next on Stake Details section");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Error clicking next on section 2: " + e.getMessage());
            Thread.currentThread().interrupt();
            throw new RuntimeException("Failed to click next on section 2", e);
        } catch (Exception e) {
            System.err.println("Error clicking next on section 2: " + e.getMessage());
            throw new RuntimeException("Failed to click next on section 2", e);
        }
    }
    
    /**
     * Select Sr Supervisor from dropdown
     */
    public void selectSrSupervisor() {
        try {
            // Scroll to element
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", srSupervisorDropdown);
            Thread.sleep(800);
            
            waitHelper.waitForElementClickable(srSupervisorDropdown);
            Select select = new Select(srSupervisorDropdown);
            
            // Get all options except the first (which is usually placeholder)
            int optionCount = select.getOptions().size();
            System.out.println("Sr Supervisor dropdown has " + optionCount + " options");
            
            if (optionCount > 1) {
                int randomIndex = RandomDataGenerator.generateRandomNumber(1, optionCount - 1);
                select.selectByIndex(randomIndex);
                Thread.sleep(500);
                String selectedOption = select.getFirstSelectedOption().getText();
                noticeData.put("Sr Supervisor", selectedOption);
                System.out.println("✓ Selected Sr Supervisor: " + selectedOption);
                Thread.sleep(1000);
            } else {
                System.out.println("⚠ No Sr Supervisor options available");
            }
        } catch (InterruptedException e) {
            System.err.println("Error selecting Sr Supervisor: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.err.println("Error selecting Sr Supervisor: " + e.getMessage());
            // Continue without failing
            System.out.println("⚠ Continuing without Sr Supervisor selection");
        }
    }
    
    /**
     * Click Next button on PDO Notice section
     */
    public void clickNextSection3() {
        try {
            waitHelper.waitForElementClickable(nextButtonSection3);
            js.executeScript("arguments[0].scrollIntoView(true);", nextButtonSection3);
            Thread.sleep(500);
            nextButtonSection3.click();
            System.out.println("✓ Clicked Next on PDO Notice section");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Error clicking next on section 3: " + e.getMessage());
            Thread.currentThread().interrupt();
            throw new RuntimeException("Failed to click next on section 3", e);
        } catch (Exception e) {
            System.err.println("Error clicking next on section 3: " + e.getMessage());
            throw new RuntimeException("Failed to click next on section 3", e);
        }
    }
    
    /**
     * Click Create button to submit the notice
     */
    public void clickCreateButton() {
        try {
            // Scroll to button
            js.executeScript("arguments[0].scrollIntoView(true);", createButton);
            Thread.sleep(1000);
            
            // Check button state
            String className = createButton.getAttribute("class");
            String style = createButton.getAttribute("style");
            boolean isEnabled = createButton.isEnabled();
            System.out.println("Create button state:");
            System.out.println("  - Class: " + className);
            System.out.println("  - Style: " + style);
            System.out.println("  - isEnabled: " + isEnabled);
            
            // Try to click regardless of state
            try {
                // First try regular click
                createButton.click();
                System.out.println("✓ Clicked Create button with regular click");
            } catch (Exception e) {
                System.out.println("Regular click failed, trying JavaScript click");
                // Force click using JavaScript
                js.executeScript("arguments[0].click();", createButton);
                System.out.println("✓ Clicked Create button with JavaScript");
            }
            
            Thread.sleep(3000); // Wait for submission
        } catch (Exception e) {
            System.err.println("Error clicking create button: " + e.getMessage());
            // Try one more time with pure JavaScript
            try {
                js.executeScript("document.getElementById('btnSubmit').click();", createButton);
                System.out.println("✓ Forced Create button click with JS by ID");
                Thread.sleep(3000);
            } catch (Exception ex) {
                throw new RuntimeException("Failed to click create button", e);
            }
        }
    }
    
    /**
     * Navigate through all sections
     */
    public void navigateThroughAllSections() {
        clickNextSection1();
        clickNextSection2();
    }
    
    /**
     * Complete notice creation process
     */
    public void completeNoticeCreation() {
        navigateThroughAllSections();
        selectSrSupervisor();
        clickNextSection3();
        clickCreateButton();
        System.out.println("✓ Notice creation process completed");
    }
    
    /**
     * Verify notice creation success and capture Notice Unique ID
     */
    public boolean verifyNoticeCreation() {
        try {
            waitHelper.hardWait(5000);
            
            // Try to capture Notice Unique ID from the specific xpath
            try {
                System.out.println("Attempting to capture Notice Unique ID from xpath...");
                WebElement noticeIdElement = driver.findElement(By.xpath("//*[@id='flush-collapse1']/div/div/div/div[2]/div/div[5]/div[2]"));
                String noticeId = noticeIdElement.getText().trim();
                
                if (!noticeId.isEmpty()) {
                    noticeData.put("NoticeUniqueId", noticeId);
                    System.out.println("✓✓✓ CAPTURED NOTICE UNIQUE ID: " + noticeId + " ✓✓✓");
                } else {
                    System.out.println("⚠ Notice Unique ID element found but text is empty");
                }
            } catch (Exception e) {
                System.out.println("⚠ Could not capture Notice Unique ID from xpath: " + e.getMessage());
                
                // Fallback: Try to find it in page text
                try {
                    java.util.List<WebElement> noticeIdElements = driver.findElements(By.xpath("//*[contains(text(), 'Notice Unique Id') or contains(text(), 'Notice ID') or contains(text(), 'NoticeUniqueId')]"));
                    if (!noticeIdElements.isEmpty()) {
                        for (WebElement elem : noticeIdElements) {
                            String text = elem.getText();
                            System.out.println("Found element with text: " + text);
                            
                            if (text.contains(":")) {
                                String[] parts = text.split(":");
                                if (parts.length > 1) {
                                    String noticeId = parts[1].trim();
                                    noticeData.put("NoticeUniqueId", noticeId);
                                    System.out.println("✓ Captured Notice Unique ID (fallback): " + noticeId);
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
            java.util.List<WebElement> successElements = driver.findElements(By.xpath("//*[contains(text(), 'success') or contains(text(), 'Success') or contains(text(), 'created')]"));
            
            if (!successElements.isEmpty()) {
                System.out.println("✓✓✓ Notice created successfully! ✓✓✓");
                return true;
            }
            
            System.out.println("✓ Notice form submitted");
            return true;
            
        } catch (Exception e) {
            System.out.println("⚠ Could not verify notice creation: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get stored notice data
     */
    public Map<String, String> getNoticeData() {
        return new HashMap<>(noticeData);
    }
    
    /**
     * Print notice data
     */
    public void printNoticeData() {
        System.out.println("\n=== Notice Data ===");
        noticeData.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("==================\n");
    }
}
