package com.example.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Customer Filter functionality
 */
public class CustomerFilterPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final Random random;
    
    // Ownership tabs
    @FindBy(xpath = "//*[@id='ownership-my']")
    private WebElement myTab;
    
    @FindBy(xpath = "//*[@id='ownership-team']")
    private WebElement teamTab;
    
    @FindBy(xpath = "//*[@id='ownership-all']")
    private WebElement allTab;
    
    // Advance Filter button
    @FindBy(xpath = "/html/body/div[2]/div/main/div/div/main/div/div[2]/div[1]/div/div[1]/div[2]/div/div[2]/div[2]/a")
    private WebElement advanceFilterButton;
    
    // Table headers
    @FindBy(xpath = "//*[@id='customerListingContainer']/div/table/thead/tr/th[1]")
    private WebElement customerNoHeader;
    
    @FindBy(xpath = "//*[@id='customerListingContainer']/div/table/thead/tr/th[2]")
    private WebElement customerNameHeader;
    
    // Search and filter controls
    @FindBy(xpath = "//*[@id='CustomerNo']")
    private WebElement customerNoInput;
    
    @FindBy(xpath = "//*[@id='searchForm']/button")
    private WebElement searchButton;
    
    @FindBy(xpath = "//*[@id='filterWraper']/div/div[2]/button[1]")
    private WebElement applyButton;
    
    @FindBy(xpath = "//*[@id='filterWraper']/div/div[2]/button[2]")
    private WebElement clearButton;
    
    // Filter fields - Text inputs
    @FindBy(xpath = "//*[@id='AdvancefilterCustomerNos']")
    private WebElement customerNoFilterInput;
    
    @FindBy(xpath = "//*[@id='AdvancefilterCustomerNames']")
    private WebElement customerNameFilter;
    
    @FindBy(xpath = "//*[@id='AdvancefilterCustomerStatus']")
    private WebElement customerStatusFilter;
    
    @FindBy(xpath = "//*[@id='filterWraper']/div/div[1]/div[7]/div/div[1]")
    private WebElement industryFilter;
    
    @FindBy(xpath = "//*[@id='filterWraper']/div/div[1]/div[8]/div/div[1]")
    private WebElement segmentFilter;
    
    @FindBy(xpath = "//*[@id='filterWraper']/div/div[1]/div[9]/div/div[1]")
    private WebElement zoneFilter;
    
    @FindBy(xpath = "//*[@id='filterWraper']/div/div[1]/div[10]/div/div[1]")
    private WebElement stateFilter;
    
    @FindBy(xpath = "//*[@id='filterWraper']/div/div[1]/div[11]/div/div[1]")
    private WebElement locationFilter;
    
    @FindBy(xpath = "//*[@id='filterWraper']/div/div[1]/div[12]/div/div[1]")
    private WebElement trustServiceProviderFilter;
    
    @FindBy(xpath = "//*[@id='filterWraper']/div/div[1]/div[13]/div/div[1]")
    private WebElement businessUnitCategoryFilter;
    
    @FindBy(xpath = "//*[@id='filterWraper']/div/div[1]/div[14]/div/div[1]")
    private WebElement businessUnitFilter;
    
    @FindBy(xpath = "//*[@id='teamDropdownContainer']/div/div[1]")
    private WebElement userDropdown;
    
    // Constructor
    public CustomerFilterPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.random = new Random();
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Click ownership tab by name
     * @param tabName My, Team, or All
     */
    public void clickOwnershipTab(String tabName) {
        WebElement tab = null;
        switch (tabName.toLowerCase()) {
            case "my":
                tab = myTab;
                break;
            case "team":
                tab = teamTab;
                break;
            case "all":
                tab = allTab;
                break;
            default:
                throw new IllegalArgumentException("Invalid tab name: " + tabName);
        }
        
        try {
            // Wait for tab to be present and clickable
            waitHelper.waitForElementClickable(tab);
            waitHelper.scrollToElement(tab);
            
            // Use JavaScript click for reliable tab switching
            waitHelper.clickWithJavaScript(tab);
            System.out.println("Clicked " + tabName + " ownership tab using JavaScript");
            
            // Wait for tab transition
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Click Advance Filter button to show filter panel
            waitHelper.waitForElementClickable(advanceFilterButton);
            waitHelper.clickWithJavaScript(advanceFilterButton);
            System.out.println("Clicked Advance Filter button");
            
            // Wait for filter wrapper to be visible using WebDriverWait
            WebElement filterWrapper = driver.findElement(By.xpath("//*[@id='filterWraper']"));
            waitHelper.waitForElementVisible(filterWrapper);
            System.out.println("Filter panel is now visible");
            
        } catch (Exception e) {
            System.err.println("Error clicking " + tabName + " tab: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Search for customer by number
     * @param customerNumber customer number to search
     */
    public void searchCustomerByNumber(String customerNumber) {
        waitHelper.waitForElementVisible(customerNoInput);
        customerNoInput.clear();
        customerNoInput.sendKeys(customerNumber);
        System.out.println("Entered customer number: " + customerNumber);
        
        waitHelper.waitForElementClickable(searchButton);
        searchButton.click();
        System.out.println("Clicked search button");
        waitHelper.hardWait(2000); // Wait for results
    }
    
    /**
     * Select random option from dropdown element (skipping search bar)
     * @param dropdown WebElement dropdown
     * @param filterName name for logging
     */
    private void selectRandomFromDropdown(WebElement dropdown, String filterName) {
        try {
            System.out.println("\n=== " + filterName + " - Starting ===");
            
            // DEBUG: Print what element we're actually clicking
            String tagName = dropdown.getTagName();
            String className = dropdown.getAttribute("class");
            System.out.println(filterName + ": Element is <" + tagName + "> with class='" + className + "'");
            
            // STRATEGY 1: Check if this is actually a hidden SELECT element nearby
            try {
                WebElement parent = dropdown.findElement(By.xpath("./ancestor::div[1]"));
                List<WebElement> selects = parent.findElements(By.tagName("select"));
                
                if (!selects.isEmpty()) {
                    System.out.println(filterName + ": Found SELECT element - using native selection");
                    WebElement selectElement = selects.get(0);
                    selectElement.click();
                    Thread.sleep(1000);
                    
                    Select select = new Select(selectElement);
                    int optionCount = select.getOptions().size();
                    System.out.println(filterName + ": SELECT has " + optionCount + " options");
                    
                    if (optionCount > 1) {
                        // Skip first option, select random from rest
                        int randomIndex = 1 + random.nextInt(optionCount - 1);
                        String optionText = select.getOptions().get(randomIndex).getText();
                        System.out.println(filterName + ": Selecting option " + randomIndex + ": " + optionText);
                        select.selectByIndex(randomIndex);
                        Thread.sleep(1000);
                        System.out.println(filterName + ": Option selected successfully");
                        System.out.println("=== " + filterName + " - Completed ===\n");
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println(filterName + ": No SELECT element found: " + e.getMessage());
            }
            
            // STRATEGY 2: Try JavaScript click first (handles "element not interactable")
            System.out.println(filterName + ": Trying JavaScript click...");
            try {
                waitHelper.clickWithJavaScript(dropdown);
                Thread.sleep(1500);  // Wait for dropdown to open
                System.out.println(filterName + ": Dropdown opened with JavaScript");
            } catch (Exception jsEx) {
                System.out.println(filterName + ": JavaScript click failed, trying regular click");
                dropdown.click();
                Thread.sleep(1500);
            }
            
            // Now use Actions class to send keyboard keys (works when element is not directly interactable)
            System.out.println(filterName + ": Using Actions class to navigate dropdown...");
            Actions actions = new Actions(driver);
            
            // Send keys to the page (not to specific element)
            actions.sendKeys(Keys.ARROW_DOWN).pause(500).perform();  // Skip first option (search)
            
            // Press DOWN arrow 1-3 more times randomly to select an option
            int downPresses = 1 + random.nextInt(3);
            for (int i = 0; i < downPresses; i++) {
                actions.sendKeys(Keys.ARROW_DOWN).pause(300).perform();
            }
            
            actions.sendKeys(Keys.ENTER).perform();  // Select the option
            System.out.println(filterName + ": Selected via Actions keyboard (pressed DOWN " + (downPresses + 1) + " times)");
            Thread.sleep(1000);
            
            System.out.println("=== " + filterName + " - Completed ===\n");
        } catch (Exception e) {
            System.err.println("ERROR with " + filterName + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Select random option from standard HTML select element
     * @param selectElement WebElement select
     * @param filterName name for logging
     */
    private void selectRandomFromHtmlSelect(WebElement selectElement, String filterName) {
        try {
            System.out.println("\n=== " + filterName + " - Starting ===");
            
            // Scroll to select element
            waitHelper.scrollToElement(selectElement);
            waitHelper.waitForElementVisible(selectElement);
            
            // Get all options from select
            List<WebElement> options = selectElement.findElements(By.tagName("option"));
            System.out.println(filterName + ": Found " + options.size() + " options");
            
            // Filter out disabled/placeholder options
            List<WebElement> validOptions = new java.util.ArrayList<>();
            for (WebElement option : options) {
                String value = option.getAttribute("value");
                boolean isDisabled = option.getAttribute("disabled") != null;
                boolean isSelected = option.getAttribute("selected") != null;
                
                // Skip disabled or default selected options
                if (!isDisabled && value != null && !value.isEmpty()) {
                    validOptions.add(option);
                }
            }
            
            System.out.println(filterName + ": Valid options: " + validOptions.size());
            
            if (validOptions.size() > 0) {
                // Select a random option
                int randomIndex = random.nextInt(validOptions.size());
                WebElement selectedOption = validOptions.get(randomIndex);
                String optionText = selectedOption.getText().trim();
                
                // Click to select
                selectedOption.click();
                System.out.println(filterName + ": Selected '" + optionText + "'");
                
                // Wait for selection to apply
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                System.out.println(filterName + ": No valid options to select");
            }
            
            System.out.println("=== " + filterName + " - Completed ===\n");
        } catch (Exception e) {
            System.err.println("ERROR with " + filterName + " select: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Apply customer number filter
     * @param customerNumber customer number
     */
    public void applyCustomerNoFilter(String customerNumber) {
        try {
            waitHelper.waitForElementVisible(customerNoFilterInput);
            customerNoFilterInput.clear();
            customerNoFilterInput.sendKeys(customerNumber);
            System.out.println("Applied Customer No filter: " + customerNumber);
        } catch (Exception e) {
            System.err.println("Error applying Customer No filter: " + e.getMessage());
        }
    }
    
    /**
     * Apply customer name filter
     * @param customerName customer name
     */
    public void applyCustomerNameFilter(String customerName) {
        try {
            waitHelper.waitForElementVisible(customerNameFilter);
            customerNameFilter.clear();
            customerNameFilter.sendKeys(customerName);
            System.out.println("Applied Customer Name filter: " + customerName);
            
            // Adequate wait time after entering customer name
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } catch (Exception e) {
            System.err.println("Error applying Customer Name filter: " + e.getMessage());
        }
    }
    
    /**
     * Apply customer status filter with random value
     */
    public void applyCustomerStatusFilter() {
        selectRandomFromHtmlSelect(customerStatusFilter, "Customer Status");
    }
    
    /**
     * Apply industry filter with random value
     */
    public void applyIndustryFilter() {
        selectRandomFromDropdown(industryFilter, "Industry");
    }
    
    /**
     * Apply segment filter with random value
     */
    public void applySegmentFilter() {
        selectRandomFromDropdown(segmentFilter, "Segment");
    }
    
    /**
     * Apply zone filter with random value
     */
    public void applyZoneFilter() {
        selectRandomFromDropdown(zoneFilter, "Zone");
    }
    
    /**
     * Apply state filter with random value
     */
    public void applyStateFilter() {
        selectRandomFromDropdown(stateFilter, "State");
    }
    
    /**
     * Apply location filter with random value
     */
    public void applyLocationFilter() {
        selectRandomFromDropdown(locationFilter, "Location");
    }
    
    /**
     * Apply trust/service provider filter with random value
     */
    public void applyTrustServiceProviderFilter() {
        selectRandomFromDropdown(trustServiceProviderFilter, "Trust/Service Provider");
    }
    
    /**
     * Apply business unit category filter with random value
     */
    public void applyBusinessUnitCategoryFilter() {
        selectRandomFromDropdown(businessUnitCategoryFilter, "Business Unit Category");
    }
    
    /**
     * Apply business unit filter with random value
     */
    public void applyBusinessUnitFilter() {
        selectRandomFromDropdown(businessUnitFilter, "Business Unit");
    }
    
    /**
     * Apply user filter (for Team tab)
     */
    public void applyUserFilter() {
        selectRandomFromDropdown(userDropdown, "User");
    }
    
    /**
     * Click Apply button
     */
    public void clickApplyButton() {
        try {
            System.out.println(">>> Attempting to click Apply button...");
            waitHelper.waitForElementClickable(applyButton);
            waitHelper.clickWithJavaScript(applyButton);
            System.out.println(">>> Apply button CLICKED successfully");
            // Wait for filter results to load
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } catch (Exception e) {
            System.err.println(">>> ERROR clicking Apply button: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Click Clear button
     */
    public void clickClearButton() {
        try {
            System.out.println(">>> Attempting to click Clear button...");
            waitHelper.waitForElementClickable(clearButton);
            waitHelper.clickWithJavaScript(clearButton);
            System.out.println(">>> Clear button CLICKED successfully");
            // Wait for filter to clear and form to reset
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } catch (Exception e) {
            System.err.println(">>> ERROR clicking Clear button: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Test all ownership tabs with filters using customer data
     * @param customerNumber created customer number
     * @param customerName created customer name
     */
    public void testAllOwnershipTabsWithFilters(String customerNumber, String customerName) {
        String[] tabs = {"My", "Team", "All"};
        
        for (String tab : tabs) {
            System.out.println("\n========================================");
            System.out.println("Testing " + tab.toUpperCase() + " Ownership Tab");
            System.out.println("========================================");
            
            clickOwnershipTab(tab);
            
            // Test Customer Name filter only (skip Customer No as per current requirements)
            System.out.println("\n--- Testing Customer Name filter ---");
            applyCustomerNameFilter(customerName);
            // Note: Wait time is now included in applyCustomerNameFilter method
            
            // Test dropdown filters in exact sequence
            testAllFiltersForTab();
            
            // For Team tab, also test User filter
            if (tab.equalsIgnoreCase("Team")) {
                try {
                    testFilter("User");
                } catch (Exception e) {
                    System.err.println("Error testing User filter: " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * Apply and clear a specific filter
     * @param filterName name of filter to test
     */
    public void testFilter(String filterName) {
        System.out.println("\n--- Testing " + filterName + " filter ---");
        
        switch (filterName.toLowerCase()) {
            case "customer status":
                applyCustomerStatusFilter();
                break;
            case "industry":
                applyIndustryFilter();
                break;
            case "segment":
                applySegmentFilter();
                break;
            case "zone":
                applyZoneFilter();
                break;
            case "state":
                applyStateFilter();
                break;
            case "location":
                applyLocationFilter();
                break;
            case "trust/service provider":
                applyTrustServiceProviderFilter();
                break;
            case "business unit category":
                applyBusinessUnitCategoryFilter();
                break;
            case "business unit":
                applyBusinessUnitFilter();
                break;
            case "user":
                applyUserFilter();
                break;
            default:
                System.err.println("Unknown filter: " + filterName);
                return;
        }
        
        // Click Apply button after selecting filter value
        clickApplyButton();
        
        // Click Clear button after apply
        clickClearButton();
        
        System.out.println("--- Completed " + filterName + " filter test ---\n");
        
        // Wait for form to stabilize before next filter (adequate wait time)
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Test all filters for current ownership tab
     */
    public void testAllFiltersForTab() {
        // Test each dropdown filter
        String[] filters = {
            "Customer Status",
            "Industry",
            "Segment",
            "Zone",
            "State",
            "Location",
            "Trust/Service Provider",
            "Business Unit Category",
            "Business Unit"
        };
        
        for (String filter : filters) {
            try {
                testFilter(filter);
            } catch (Exception e) {
                System.err.println("Error testing " + filter + ": " + e.getMessage());
            }
        }
    }
    
    /**
     * Test all ownership tabs with filters (deprecated - use version with parameters)
     */
    @Deprecated
    public void testAllOwnershipTabsWithFilters() {
        System.err.println("Warning: Using deprecated method. Please provide customer number and name.");
        testAllOwnershipTabsWithFilters("", "");
    }
    
    /**
     * Verify search results contain customer
     * @param customerNumber customer number to verify
     * @return true if customer found in results
     */
    public boolean verifyCustomerInResults(String customerNumber) {
        try {
            waitHelper.hardWait(2000);
            WebElement result = driver.findElement(By.xpath("//td[contains(text(),'" + customerNumber + "')]"));
            return result.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
