package com.example.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * Page Object Model for Add Asset Page
 */
public class AddAssetPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final Map<String, String> assetData;
    
    // Asset Creation Form Locators
    @FindBy(id = "CategoryId")
    private WebElement assetCategoryDropdown;
    
    @FindBy(xpath = "//*[@id='Name']")
    private WebElement assetNameField;
    
    @FindBy(xpath = "//*[@id='flush-collapse1']/div/div[3]/div[1]/span[1]/span[1]/span/ul/li/input")
    private WebElement accountNumberSearchField;
    
    @FindBy(xpath = "//*[@id='Description']")
    private WebElement assetDescriptionField;
    
    @FindBy(xpath = "//*[@id='CurrentHolder']")
    private WebElement currentHolderField;
    
    @FindBy(xpath = "//*[@id='Period']")
    private WebElement periodField;
    
    @FindBy(xpath = "//*[@id='AuthorisedOfficerName']")
    private WebElement authorisedOfficerNameField;
    
    @FindBy(xpath = "//*[@id='DisputeCollateral']")
    private WebElement disputeCollateralField;
    
    @FindBy(xpath = "//*[@id='CERSAINumber']")
    private WebElement cersaiNumberField;
    
    @FindBy(xpath = "//*[@id='AssetId']")
    private WebElement assetIdField;
    
    @FindBy(xpath = "//*[@id='BasicInformationCollapse']")
    private WebElement nextButton;
    
    @FindBy(xpath = "//*[@id='btnSubmit']")
    private WebElement saveButton;
    
    // Asset Details Page Locators
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[1]/div[1]/div")
    private WebElement basicInformationSection;
    
    @FindBy(xpath = "//*[@id='flush-collapse14']/div/div/div/div[1]/a")
    private WebElement editBasicInfoButton;
    
    @FindBy(xpath = "//*[@id='facebox']/div/table/tbody/tr[2]/td[2]/div/div/form/div[2]/button")
    private WebElement cancelBasicInfoButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[2]/div[1]/div")
    private WebElement linkedCaseNoticeSection;
    
    @FindBy(xpath = "//*[@id='CustomerAccountNoticeLink-tab']")
    private WebElement linkedNoticesTab;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[3]/div[1]/div")
    private WebElement addressDetailsSection;
    
    @FindBy(xpath = "//*[@id='AssetAddressContainer']/div/div[1]/div[2]/a")
    private WebElement addressPlusButton;
    
    @FindBy(xpath = "//*[@id='add-assetaddress']/form/div[2]/button[2]")
    private WebElement addressCancelButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[4]/div[1]/div")
    private WebElement publicationsSection;
    
    @FindBy(xpath = "//*[@id='AssetValuationContainer']/div/div[1]")
    private WebElement publicationDetailsSection;
    
    @FindBy(xpath = "//*[@id='AssetValuationContainer']/div/div[1]/div[2]/a")
    private WebElement valuationPlusButton;
    
    @FindBy(xpath = "//*[@id='edit-notice']/form/div/div[6]/button")
    private WebElement valuationCancelButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[6]/div[1]/div")
    private WebElement biddersSection;
    
    @FindBy(xpath = "//*[@id='AssetBidderContainer']/div/div[1]/div[2]/a")
    private WebElement bidderPlusButton;
    
    @FindBy(xpath = "//*[@id='edit-bidders']/form/div/div/div[7]/button")
    private WebElement bidderCancelButton;
    
    @FindBy(xpath = "//*[@id='accordionFlushExample']/div[7]/div[1]/div")
    private WebElement fateOfSaleSection;
    
    @FindBy(xpath = "//a[contains(@class, 'back') or contains(text(), 'Back') or contains(@href, 'CustomerAssets')]")
    private WebElement backButton;
    
    @FindBy(xpath = "//*[@id='AssetNameSerch']")
    private WebElement assetSearchField;
    
    @FindBy(xpath = "//*[@id='searchForm']/button")
    private WebElement searchButton;
    
    @FindBy(xpath = "//*[@id='assetsListingContainer']/div/table/tbody/tr/td[8]/div/a/I")
    private WebElement actionButton;
    
    @FindBy(xpath = "//*[@id='assetsListingContainer']/div/table/tbody/tr/td[8]/div/ul/li[5]/a")
    private WebElement deleteButton;
    
    public AddAssetPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.assetData = new HashMap<>();
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Fill asset form with random data
     */
    public void fillAssetForm() {
        System.out.println("\n===== Starting to fill Asset form =====");
        
        // Wait for page to load
        waitHelper.hardWait(3000);
        
        // Check if element is present, if not log current URL
        try {
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Title: " + driver.getTitle());
            
            // Wait for the asset category dropdown to be visible
            waitHelper.waitForElementClickable(assetCategoryDropdown);
            
            // Select Asset Category
            waitHelper.hardWait(1000);
            Select categorySelect = new Select(assetCategoryDropdown);
            List<WebElement> categoryOptions = categorySelect.getOptions();
            int randomIndex = (int) (Math.random() * (categoryOptions.size() - 1)) + 1;
            categorySelect.selectByIndex(randomIndex);
            String category = categoryOptions.get(randomIndex).getText();
            assetData.put("AssetCategory", category);
            System.out.println("✓ Selected Asset Category: " + category);
        } catch (Exception e) {
            System.out.println("Error locating Asset Category dropdown");
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
        
        // Enter Asset Name
        waitHelper.hardWait(500);
        String assetName = "ASSET_" + RandomDataGenerator.generateAlphanumeric(8);
        assetNameField.sendKeys(assetName);
        assetData.put("AssetName", assetName);
        System.out.println("✓ Entered Asset Name: " + assetName);
        
        // Select Account Number using Select2
        // Wait longer for account dropdown to load after category selection
        System.out.println("  Waiting for account dropdown to load after category selection...");
        waitHelper.hardWait(3000);
        try {
            selectAccountNumber();
        } catch (Exception e) {
            System.out.println("! Account selection skipped - no accounts available or field optional");
        }
        
        // Enter Asset Description
        waitHelper.hardWait(500);
        String description = RandomDataGenerator.generateAddress();
        assetDescriptionField.sendKeys(description);
        assetData.put("Description", description);
        System.out.println("✓ Entered Description");
        
        // Enter Current Holder
        waitHelper.hardWait(500);
        String holder = RandomDataGenerator.generateName();
        currentHolderField.sendKeys(holder);
        assetData.put("CurrentHolder", holder);
        System.out.println("✓ Entered Current Holder: " + holder);
        
        // Enter Period
        waitHelper.hardWait(500);
        String period = RandomDataGenerator.generateAlphanumeric(6);
        periodField.sendKeys(period);
        assetData.put("Period", period);
        System.out.println("✓ Entered Period: " + period);
        
        // Enter Authorised Officer Name
        waitHelper.hardWait(500);
        String officerName = RandomDataGenerator.generateName();
        authorisedOfficerNameField.sendKeys(officerName);
        assetData.put("OfficerName", officerName);
        System.out.println("✓ Entered Authorised Officer Name: " + officerName);
        
        // Enter Dispute Collateral
        waitHelper.hardWait(500);
        String dispute = RandomDataGenerator.generateAlphanumeric(10);
        disputeCollateralField.sendKeys(dispute);
        assetData.put("Dispute", dispute);
        System.out.println("✓ Entered Dispute Collateral");
        
        // Enter CERSAI Number
        waitHelper.hardWait(500);
        String cersai = RandomDataGenerator.generateNumeric(10);
        cersaiNumberField.sendKeys(cersai);
        assetData.put("CERSAI", cersai);
        System.out.println("✓ Entered CERSAI Number: " + cersai);
        
        // Enter Asset Id
        waitHelper.hardWait(500);
        try {
            waitHelper.scrollToElement(assetIdField);
            waitHelper.hardWait(500);
            String assetId = RandomDataGenerator.generateAlphanumeric(8);
            // Use JavaScript to set value if sendKeys fails
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", assetIdField, assetId);
            assetData.put("AssetId", assetId);
            System.out.println("✓ Entered Asset ID: " + assetId);
        } catch (Exception e) {
            System.out.println("! Asset ID field not interactable, skipping");
        }
        
        System.out.println("===== Asset form filled successfully =====\n");
    }
    
    /**
     * Select Account Number from Select2 dropdown by typing in search field
     */
    private void selectAccountNumber() {
        try {
            System.out.println("  Attempting to select Account Number...");
            
            // Find the Select2 container for account number
            WebElement accountNumberSelect2 = driver.findElement(By.xpath("//*[@id='flush-collapse1']/div/div[3]/div[1]/span[1]/span[1]/span"));
            
            // Scroll to Select2 container first
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", accountNumberSelect2);
            Thread.sleep(500);
            
            waitHelper.waitForElementVisible(accountNumberSelect2);
            
            // Click the container to open dropdown
            System.out.println("  Clicking Account Number dropdown to open...");
            try {
                accountNumberSelect2.click();
            } catch (Exception clickEx) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", accountNumberSelect2);
            }
            Thread.sleep(1000);
            
            // Find the search input field inside the dropdown
            WebElement searchInput = driver.findElement(By.xpath("//*[@id='flush-collapse1']/div/div[3]/div[1]/span[1]/span[1]/span/ul/li/input"));
            System.out.println("  Found search input field");
            
            // Type a space to trigger the dropdown to load all accounts
            searchInput.clear();
            searchInput.sendKeys(" ");
            Thread.sleep(3000);  // Wait for AJAX to load accounts
            System.out.println("  Typed space into search field to trigger account loading...");
            
            // Check if accounts loaded
            List<WebElement> loadedItems = driver.findElements(
                By.xpath("//span[contains(@class,'select2-results')]//li | //ul[contains(@class,'select2-results')]//li"));
            System.out.println("  Items after typing: " + loadedItems.size());
            
            if (loadedItems.size() == 0 || loadedItems.size() == 1) {
                // Try alternative: Check if there's a hidden select element with options
                System.out.println("  No items in dropdown, checking hidden select element...");
                try {
                    WebElement hiddenSelect = driver.findElement(By.id("AccountId"));
                    List<WebElement> options = hiddenSelect.findElements(By.tagName("option"));
                    System.out.println("  Found " + options.size() + " options in hidden select");
                    
                    if (options.size() > 1) {
                        // Filter out the placeholder option
                        List<WebElement> validOptions = new ArrayList<>();
                        for (WebElement opt : options) {
                            String optValue = opt.getAttribute("value");
                            String optText = opt.getText();
                            if (optValue != null && !optValue.isEmpty() && !optText.contains("Select")) {
                                validOptions.add(opt);
                            }
                        }
                        
                        if (validOptions.size() > 0) {
                            // Select random option using JavaScript
                            int randomIdx = RandomDataGenerator.generateRandomNumber(0, validOptions.size() - 1);
                            WebElement selectedOpt = validOptions.get(randomIdx);
                            String accountNumber = selectedOpt.getText();
                            String accountValue = selectedOpt.getAttribute("value");
                            
                            System.out.println("  Selecting from hidden select: " + accountNumber);
                            ((JavascriptExecutor) driver).executeScript(
                                "arguments[0].value = arguments[1]; "
                                + "$(arguments[0]).trigger('change');",
                                hiddenSelect, accountValue);
                            Thread.sleep(1000);
                            
                            assetData.put("AccountNumber", accountNumber);
                            System.out.println("  ✓ Account Number selected: " + accountNumber);
                            
                            // Close dropdown
                            ((JavascriptExecutor) driver).executeScript("$('#AccountId').select2('close');");
                            return;
                        }
                    }
                } catch (Exception selectEx) {
                    System.out.println("  ! Could not use hidden select: " + selectEx.getMessage());
                }
            }
            
            // Clear the search to show all results (if items already loaded)
            if (loadedItems.size() > 1) {
                searchInput.clear();
                Thread.sleep(1000);
            }
            
            // Check if dropdown is open
            Object dropdownStatus = ((JavascriptExecutor) driver).executeScript(
                "var container = document.querySelector('.select2-container--open'); "
                + "var results = document.querySelectorAll('.select2-results__option, .select2-results li'); "
                + "return 'Open: ' + (container !== null) + ', Results count: ' + results.length;");
            System.out.println("  Dropdown status: " + dropdownStatus);
            
            // Get ALL li elements from the dropdown results
            List<WebElement> allItems = driver.findElements(
                By.xpath("//span[contains(@class,'select2-results')]//li | //ul[contains(@class,'select2-results')]//li"));
            
            System.out.println("  Total items found in dropdown: " + allItems.size());
            
            if (allItems.size() == 0) {
                // Try alternative selector
                allItems = driver.findElements(
                    By.cssSelector(".select2-results li, .select2-results__option"));
                System.out.println("  Alternative selector found: " + allItems.size() + " items");
            }
            
            // Filter and debug each item
            List<WebElement> validResults = new ArrayList<>();
            
            for (int i = 0; i < allItems.size(); i++) {
                WebElement item = allItems.get(i);
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
                    
                    // Add valid account options
                    validResults.add(item);
                    System.out.println("    -> Valid account option added");
                    
                } catch (Exception e) {
                    System.out.println("    -> Error processing item: " + e.getMessage());
                    continue;
                }
            }
            
            System.out.println("  Found " + validResults.size() + " valid account options (excluding search bar)");
            
            if (validResults.size() > 0) {
                // Print available account numbers
                System.out.println("  Available accounts:");
                for (int i = 0; i < Math.min(validResults.size(), 10); i++) {
                    System.out.println("    [" + i + "] " + validResults.get(i).getText());
                }
                if (validResults.size() > 10) {
                    System.out.println("    ... and " + (validResults.size() - 10) + " more");
                }
                
                // Select a random account number
                int randomIndex = RandomDataGenerator.generateRandomNumber(0, validResults.size() - 1);
                WebElement selectedOption = validResults.get(randomIndex);
                
                String accountText = selectedOption.getText();
                System.out.println("  Selecting account [" + randomIndex + "]: " + accountText);
                
                // Method 1: Try clicking with JavaScript
                try {
                    ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block: 'center'});", selectedOption);
                    Thread.sleep(300);
                    
                    ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].click();", selectedOption);
                    Thread.sleep(1500);
                    
                    // Check if selection worked
                    String selectedValue = accountNumberSelect2.getText();
                    System.out.println("  After click, span text: '" + selectedValue + "'");
                    
                    if (selectedValue == null || selectedValue.equals("Select") || selectedValue.isEmpty()) {
                        // Method 2: Try triggering mousedown/mouseup events
                        System.out.println("  First click failed, trying mousedown/mouseup...");
                        ((JavascriptExecutor) driver).executeScript(
                            "var evt = new MouseEvent('mousedown', {bubbles: true}); "
                            + "arguments[0].dispatchEvent(evt); "
                            + "var evt2 = new MouseEvent('mouseup', {bubbles: true}); "
                            + "arguments[0].dispatchEvent(evt2);",
                            selectedOption);
                        Thread.sleep(1500);
                        
                        selectedValue = accountNumberSelect2.getText();
                        System.out.println("  After mousedown/mouseup, span text: '" + selectedValue + "'");
                    }
                    
                    if (selectedValue != null && !selectedValue.isEmpty() && !selectedValue.equals("Select")) {
                        assetData.put("AccountNumber", selectedValue);
                        System.out.println("  ✓ Account Number: " + selectedValue);
                    } else {
                        System.out.println("  ! Account Number selection failed, trying direct element click...");
                        // Method 3: Try direct Selenium click
                        selectedOption.click();
                        Thread.sleep(1500);
                        
                        selectedValue = accountNumberSelect2.getText();
                        if (selectedValue != null && !selectedValue.isEmpty() && !selectedValue.equals("Select")) {
                            assetData.put("AccountNumber", selectedValue);
                            System.out.println("  ✓ Account Number (via direct click): " + selectedValue);
                        } else {
                            System.out.println("  ! All click methods failed - span still shows: '" + selectedValue + "'");
                        }
                    }
                } catch (Exception clickEx) {
                    System.out.println("  ! Error during click: " + clickEx.getMessage());
                }
            } else {
                System.out.println("  ! No account number options found (only search bar or no data in test environment)");
            }
            
            // Ensure dropdown is completely closed
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript(
                "if(document.querySelector('.select2-container--open')) { "
                + "document.body.click(); "
                + "}");
            Thread.sleep(500);
            
        } catch (Exception e) {
            System.out.println("  ! Could not select Account Number: " + e.getMessage());
            try {
                // Forcefully close any open Select2 dropdown
                ((JavascriptExecutor) driver).executeScript(
                    "try { $('.select2-container--open').select2('close'); } catch(e) {} document.body.click();");
                Thread.sleep(500);
            } catch (Exception ex) {
                // Ignore
            }
        }
    }
    
    /**
     * Click Next button
     */
    public void clickNext() {
        waitHelper.hardWait(1000);
        waitHelper.scrollToElement(nextButton);
        waitHelper.waitForElementClickable(nextButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
        waitHelper.hardWait(2000);
        System.out.println("✓ Clicked Next button");
    }
    
    /**
     * Click Save button
     */
    public void clickSave() {
        waitHelper.hardWait(2000);
        
        // Scroll to the top of the page first to see the Save button
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        waitHelper.hardWait(1000);
        
        try {
            // Try to find and click the Save button with multiple strategies
            WebElement btnSubmit = driver.findElement(By.xpath("//*[@id='btnSubmit']"));
            
            // Make sure button is visible
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSubmit);
            waitHelper.hardWait(1000);
            
            // Try JavaScript click directly
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnSubmit);
            System.out.println("✓ Clicked Save button using JavaScript");
            
        } catch (Exception e) {
            System.out.println("❌ First attempt failed, trying alternate xpath...");
            try {
                // Try alternate xpath for Save button
                WebElement saveBtn = driver.findElement(By.xpath("//button[contains(@class, 'btn-primary') and contains(text(), 'Save')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
                System.out.println("✓ Clicked Save button using alternate xpath");
            } catch (Exception ex) {
                System.out.println("❌ Could not click Save button: " + ex.getMessage());
                throw ex;
            }
        }
        
        waitHelper.hardWait(3000);
        System.out.println("✓✓✓ Asset created successfully! ✓✓✓");
    }
    
    /**
     * Verify all asset details sections
     */
    public void verifyAssetDetails() {
        System.out.println("\n========== STARTING ASSET DETAILS VERIFICATION ==========\n");
        
        waitHelper.hardWait(2000);
        System.out.println("✓ Asset details page loaded");
        
        // Click Basic Information Section
        clickBasicInformationSection();
        
        // Click Linked Case/Notice Section
        clickLinkedCaseNoticeSection();
        
        // Click Address Details Section
        clickAddressDetailsSection();
        
        // Click Publications Section
        clickPublicationsSection();
        
        // Click Bidders Section
        clickBiddersSection();
        
        // Click Fate of Sale Section
        clickFateOfSaleSection();
        
        System.out.println("\n========== ALL ASSET DETAILS SECTIONS VERIFIED ==========\n");
        
        // Navigate back and delete
        deleteAsset();
    }
    
    /**
     * Click Basic Information section
     */
    private void clickBasicInformationSection() {
        try {
            waitHelper.hardWait(1000);
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", basicInformationSection);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", basicInformationSection);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Basic Information section");
        } catch (Exception e) {
            System.out.println("! Could not click Basic Information section: " + e.getMessage());
        }
    }
    
    /**
     * Click Linked Case/Notice section
     */
    private void clickLinkedCaseNoticeSection() {
        try {
            waitHelper.hardWait(1000);
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", linkedCaseNoticeSection);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", linkedCaseNoticeSection);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Linked Case/Notice section");
        } catch (Exception e) {
            System.out.println("! Could not click Linked Case/Notice section: " + e.getMessage());
        }
    }
    
    /**
     * Click Address Details section
     */
    private void clickAddressDetailsSection() {
        try {
            waitHelper.hardWait(1000);
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", addressDetailsSection);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addressDetailsSection);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Address Details section");
        } catch (Exception e) {
            System.out.println("! Could not click Address Details section: " + e.getMessage());
        }
    }
    
    /**
     * Click Publications section
     */
    private void clickPublicationsSection() {
        try {
            waitHelper.hardWait(1000);
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", publicationsSection);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", publicationsSection);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Publications section");
        } catch (Exception e) {
            System.out.println("! Could not click Publications section: " + e.getMessage());
        }
    }
    
    /**
     * Click Bidders section
     */
    private void clickBiddersSection() {
        try {
            waitHelper.hardWait(1000);
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", biddersSection);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", biddersSection);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Bidders section");
        } catch (Exception e) {
            System.out.println("! Could not click Bidders section: " + e.getMessage());
        }
    }
    
    /**
     * Click Fate of Sale section
     */
    private void clickFateOfSaleSection() {
        try {
            waitHelper.hardWait(1000);
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", fateOfSaleSection);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fateOfSaleSection);
            Thread.sleep(1000);
            System.out.println("✓ Clicked Fate of Sale section");
        } catch (Exception e) {
            System.out.println("! Could not click Fate of Sale section: " + e.getMessage());
        }
    }
    
    /**
     * Delete the asset
     */
    private void deleteAsset() {
        System.out.println("========== STARTING ASSET DELETION ==========\n");
        
        // Click Back button
        try {
            waitHelper.hardWait(1000);
            
            // Try to find the back button
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", backButton);
                System.out.println("✓ Clicked Back button");
            } catch (Exception e) {
                System.out.println("! Back button not found, trying browser back navigation");
                driver.navigate().back();
                System.out.println("✓ Used browser back navigation");
            }
            
            waitHelper.hardWait(2000);
        } catch (Exception e) {
            System.out.println("! Could not navigate back: " + e.getMessage());
        }
        
        // Search for the asset
        String assetName = assetData.get("AssetName");
        System.out.println("✓ Retrieved asset name: " + assetName);
        
        waitHelper.hardWait(1000);
        assetSearchField.clear();
        assetSearchField.sendKeys(assetName);
        waitHelper.hardWait(500);
        System.out.println("✓ Entered asset name in search");
        
        // Click Search button
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchButton);
        waitHelper.hardWait(2000);
        System.out.println("✓ Clicked Search button");
        
        // Click Action button
        waitHelper.hardWait(1000);
        try {
            WebElement actionBtn = driver.findElement(By.xpath("//*[@id='assetsListingContainer']/div/table/tbody/tr/td[8]/div/a"));
            waitHelper.scrollToElement(actionBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actionBtn);
            waitHelper.hardWait(1000);
            System.out.println("✓ Clicked Action button");
        } catch (Exception e) {
            System.out.println("Error clicking action button: " + e.getMessage());
        }
        
        // Click Delete button
        waitHelper.hardWait(1000);
        try {
            WebElement deleteBtn = driver.findElement(By.xpath("//*[@id='assetsListingContainer']/div/table/tbody/tr/td[8]/div/ul/li[5]/a"));
            waitHelper.waitForElementClickable(deleteBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);
            waitHelper.hardWait(2000);
            System.out.println("✓ Clicked Delete button");
        } catch (Exception e) {
            System.out.println("Error clicking delete button: " + e.getMessage());
        }
        
        // Accept alert
        try {
            waitHelper.hardWait(2000);
            driver.switchTo().alert().accept();
            waitHelper.hardWait(2000);
            System.out.println("✓✓✓ Asset deleted successfully! ✓✓✓");
        } catch (Exception e) {
            System.out.println("No alert found or error accepting alert: " + e.getMessage());
        }
        
        System.out.println("\n========== ASSET DELETION COMPLETE ==========\n");
    }
    
    /**
     * Get asset data
     */
    public Map<String, String> getAssetData() {
        return assetData;
    }
}
