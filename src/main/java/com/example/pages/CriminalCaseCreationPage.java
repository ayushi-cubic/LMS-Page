package com.example.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Criminal Case Creation
 */
public class CriminalCaseCreationPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final Random random;
    private String capturedCaseId;
    
    // Locators - Navigation
    @FindBy(xpath = "/html/body/div[3]/div/div/div/ul/li[3]/a")
    private WebElement mattersMenu;
    
    @FindBy(xpath = "/html/body/div[3]/div/div/div/ul/li[3]/ul/li[3]/a")
    private WebElement caseSubmenu;
    
    @FindBy(xpath = "/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div[1]/div[2]/div/div/div[3]/a")
    private WebElement addNewCaseButton;
    
    @FindBy(xpath = "/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div[1]/div[2]/div/div/div[3]/ul/li[2]/a")
    private WebElement criminalButton;
    
    // Locators - Form Fields
    @FindBy(xpath = "//*[@id='CaseTypeId']")
    private WebElement caseTypeDropdown;
    
    @FindBy(xpath = "//*[@id='WeAre']")
    private WebElement partyInput;
    
    @FindBy(xpath = "//*[@id='CaseNumber']")
    private WebElement caseNumberInput;
    
    @FindBy(xpath = "//*[@id='PriorityId']")
    private WebElement priorityDropdown;
    
    @FindBy(xpath = "//*[@id='createbasicdetailscust']")
    private WebElement nextBasicDetailsButton;
    
    @FindBy(xpath = "//*[@id='ClaimAmount']")
    private WebElement claimAmountInput;
    
    @FindBy(xpath = "//*[@id='createStakeDetailsNXT']")
    private WebElement nextStakeDetailsButton;
    
    @FindBy(xpath = "//*[@id='4']")
    private WebElement srSupervisorDropdown;
    
    @FindBy(xpath = "//*[@id='createOwnerDetailsNXT']")
    private WebElement nextOwnerDetailsButton;
    
    @FindBy(xpath = "//*[@id='createcaseAnalysisNXT']")
    private WebElement nextCaseAnalysisButton;
    
    @FindBy(xpath = "//*[@id='RegistrationFillingDate']")
    private WebElement registrationDateInput;
    
    @FindBy(xpath = "//*[@id='createimpdatesNXT']")
    private WebElement nextImpDatesButton;
    
    @FindBy(xpath = "//*[@id='createChequeDetailsNXT']")
    private WebElement nextChequeDetailsButton;
    
    @FindBy(xpath = "//*[@id='createAddFIRNXT']")
    private WebElement nextAddFIRButton;
    
    @FindBy(xpath = "//*[@id='createSummonDetailsNXT']")
    private WebElement nextSummonDetailsButton;
    
    @FindBy(xpath = "//*[@id='createContingentLiabilityNXT']")
    private WebElement nextContingentLiabilityButton;
    
    @FindBy(xpath = "//*[@id='CustomerCreateNew']")
    private WebElement saveButton;
    
    @FindBy(xpath = "//*[@id='case-basicdetails']/div/div[2]/div/div[4]/div")
    private WebElement caseIdElement;
    
    // Logout and User dropdown elements
    @FindBy(xpath = "/html/body/header/div/div[2]/a")
    private WebElement userDropdown;
    
    @FindBy(xpath = "/html/body/header/div/div[2]/div[2]/div/div/div[1]/div/ul/li[2]/a")
    private WebElement logoutLink;
    
    // Login fields
    @FindBy(xpath = "//*[@id='USEREMAILID']")
    private WebElement usernameInput;
    
    @FindBy(xpath = "//*[@id='USERPASSWORD']")
    private WebElement passwordInput;
    
    @FindBy(xpath = "//*[@id='submitbtn']")
    private WebElement signInButton;
    
    @FindBy(xpath = "//*[@id='Otp']")
    private WebElement otpInput;
    
    // Actionable items and approval elements
    @FindBy(xpath = "//*[@id='navLinks']/a[3]")
    private WebElement actionableItemsLink;
    
    @FindBy(xpath = "//*[@id='tab-approval']")
    private WebElement approvalTab;
    
    @FindBy(xpath = "//*[@id='ApprovalTypeTabs']/li[2]/a")
    private WebElement advocateAllocationApprovalTab;
    
    @FindBy(xpath = "//*[@id='v-pills-home']/div[1]/div[2]/a")
    private WebElement advanceFilterLink;
    
    @FindBy(xpath = "//*[@id='BtnAFApply1']")
    private WebElement applyButton;
    
    @FindBy(xpath = "//*[@id='paginationControls']/ul/li[7]/a")
    private WebElement lastPageLink;
    
    @FindBy(xpath = "//*[@id='chkApprovalReq']")
    private WebElement approvalCheckbox;
    
    @FindBy(xpath = "//*[@id='btnapproveadvocate']")
    private WebElement approveButton;
    
    // Matters > Case > Load cases elements
    @FindBy(xpath = "//*[@id='loadCaseBtn']")
    private WebElement loadCasesButton;
    
    @FindBy(xpath = "//*[@id='caseListingContainer']/div/table/tbody/tr[1]/td[10]/div/a/i")
    private WebElement actionDropdown;
    
    @FindBy(xpath = "//*[@id='caseListingContainer']/div/table/tbody/tr[1]/td[10]/div/ul/li[2]/a")
    private WebElement detailsLink;
    
    // Constructor
    public CriminalCaseCreationPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.random = new Random();
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Click Matters menu
     */
    public void clickMattersMenu() {
        try {
            System.out.println("Clicking Matters menu...");
            Thread.sleep(3000);
            waitHelper.waitForElementClickable(mattersMenu);
            
            try {
                mattersMenu.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mattersMenu);
            }
            
            System.out.println("Matters menu clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Matters menu: " + e.getMessage());
            throw new RuntimeException("Failed to click Matters menu", e);
        }
    }
    
    /**
     * Click Case submenu
     */
    public void clickCaseSubmenu() {
        try {
            System.out.println("Clicking Case submenu...");
            waitHelper.waitForElementClickable(caseSubmenu);
            
            try {
                caseSubmenu.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", caseSubmenu);
            }
            
            System.out.println("Case submenu clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Case submenu: " + e.getMessage());
            throw new RuntimeException("Failed to click Case submenu", e);
        }
    }
    
    /**
     * Click Add New Case button
     */
    public void clickAddNewCase() {
        try {
            System.out.println("Clicking Add New Case button...");
            waitHelper.waitForElementClickable(addNewCaseButton);
            
            try {
                addNewCaseButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNewCaseButton);
            }
            
            System.out.println("Add New Case button clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Add New Case button: " + e.getMessage());
            throw new RuntimeException("Failed to click Add New Case button", e);
        }
    }
    
    /**
     * Click Criminal button
     */
    public void clickCriminal() {
        try {
            System.out.println("Clicking Criminal button...");
            
            // Store the current window handle
            String mainWindow = driver.getWindowHandle();
            System.out.println("Main window handle: " + mainWindow);
            
            waitHelper.waitForElementClickable(criminalButton);
            
            try {
                criminalButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", criminalButton);
            }
            
            System.out.println("Criminal button clicked successfully");
            Thread.sleep(3000);
            
            // Check if a new window/tab opened
            java.util.Set<String> windowHandles = driver.getWindowHandles();
            System.out.println("Total windows open: " + windowHandles.size());
            
            if (windowHandles.size() > 1) {
                // Switch to the new window
                for (String handle : windowHandles) {
                    if (!handle.equals(mainWindow)) {
                        driver.switchTo().window(handle);
                        System.out.println("Switched to new window: " + handle);
                        break;
                    }
                }
                
                // Wait for the new page to load
                Thread.sleep(3000);
                System.out.println("New page URL: " + driver.getCurrentUrl());
            } else {
                System.out.println("No new window opened, staying on current page");
            }
            
        } catch (Exception e) {
            System.err.println("Error clicking Criminal button: " + e.getMessage());
            throw new RuntimeException("Failed to click Criminal button", e);
        }
    }
    
    /**
     * Select random Case Type
     */
    public void selectRandomCaseType() {
        try {
            System.out.println("Selecting random Case Type...");
            waitHelper.waitForElementVisible(caseTypeDropdown);
            Select select = new Select(caseTypeDropdown);
            
            List<WebElement> options = select.getOptions();
            // Skip the first option (Select)
            if (options.size() > 1) {
                int randomIndex = random.nextInt(options.size() - 1) + 1;
                select.selectByIndex(randomIndex);
                System.out.println("Selected Case Type: " + select.getFirstSelectedOption().getText());
            }
            
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error selecting Case Type: " + e.getMessage());
            throw new RuntimeException("Failed to select Case Type", e);
        }
    }
    
    /**
     * Select random Customer ID
     */
    public void selectRandomCustomerId() {
        try {
            System.out.println("Selecting random Customer ID...");
            Thread.sleep(2000);
            
            WebElement customerInput = driver.findElement(By.xpath("//*[@id='flush-collapseOne']/div/div[6]/div/div[1]/div/div[3]/div/span/span[1]/span/ul/li/input"));
            waitHelper.waitForElementVisible(customerInput);
            
            customerInput.click();
            Thread.sleep(1000);
            
            customerInput.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(500);
            customerInput.sendKeys(Keys.ENTER);
            
            System.out.println("Random Customer ID selected successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error selecting Customer ID: " + e.getMessage());
            throw new RuntimeException("Failed to select Customer ID", e);
        }
    }
    
    /**
     * Select random Account Number
     */
    public void selectRandomAccountNumber() {
        try {
            System.out.println("Selecting random Account Number...");
            Thread.sleep(2000);
            
            WebElement accountInput = driver.findElement(By.xpath("//*[@id='flush-collapseOne']/div/div[6]/div/div[1]/div/div[4]/div/span/span[1]/span/ul/li/input"));
            waitHelper.waitForElementVisible(accountInput);
            
            accountInput.click();
            Thread.sleep(1000);
            
            accountInput.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(500);
            accountInput.sendKeys(Keys.ENTER);
            
            System.out.println("Random Account Number selected successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error selecting Account Number: " + e.getMessage());
            throw new RuntimeException("Failed to select Account Number", e);
        }
    }
    
    /**
     * Enter Party name (We Are field) - Must be between 1 and 200
     */
    public void enterParty() {
        try {
            System.out.println("Entering Party name (We Are field)...");
            
            // Generate random number between 1 and 200
            int weAreNumber = random.nextInt(200) + 1;
            
            waitHelper.waitForElementVisible(partyInput);
            partyInput.clear();
            partyInput.sendKeys(String.valueOf(weAreNumber));
            System.out.println("Entered We Are: " + weAreNumber + " (valid range: 1-200)");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error entering Party (We Are): " + e.getMessage());
            throw new RuntimeException("Failed to enter Party (We Are)", e);
        }
    }
    
    /**
     * Select random Issuing Party (supports multiple selections)
     */
    public void selectRandomIssuingParty() {
        try {
            System.out.println("Selecting Issuing Party(s)...");
            Thread.sleep(2000);
            
            WebElement issuingPartyDropdown = driver.findElement(By.xpath("//*[@id='byRoleSection']/div/div[1]/div/div[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", issuingPartyDropdown);
            Thread.sleep(800);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", issuingPartyDropdown);
            System.out.println("Clicked Issuing Party dropdown");
            Thread.sleep(2000);
            
            // Find checkbox inputs specifically
            List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@id='businessunitPetResDropdown']//input[@type='checkbox']"));
            System.out.println("Found " + checkboxes.size() + " issuing party checkboxes");
            
            if (!checkboxes.isEmpty()) {
                // Filter valid checkboxes
                List<WebElement> validCheckboxes = new java.util.ArrayList<>();
                for (WebElement checkbox : checkboxes) {
                    if (checkbox.isDisplayed() && checkbox.isEnabled()) {
                        validCheckboxes.add(checkbox);
                    }
                }
                
                if (!validCheckboxes.isEmpty()) {
                    // Select 1-3 random parties
                    int numToSelect = Math.min(random.nextInt(3) + 1, validCheckboxes.size());
                    System.out.println("Selecting " + numToSelect + " Issuing Party(s)...");
                    
                    for (int i = 0; i < numToSelect; i++) {
                        try {
                            WebElement checkbox = validCheckboxes.get(i);
                            // Get label text
                            WebElement parent = checkbox.findElement(By.xpath("./parent::*"));
                            String labelText = parent.getText().trim();
                            
                            // Scroll into view and click
                            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
                            Thread.sleep(300);
                            
                            if (!checkbox.isSelected()) {
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
                                Thread.sleep(500);
                                System.out.println("  ✓ Selected Issuing Party: " + labelText);
                            }
                        } catch (Exception ex) {
                            System.out.println("  ! Failed to select party #" + (i + 1) + ": " + ex.getMessage());
                        }
                    }
                    System.out.println("Issuing Party(s) selected successfully");
                } else {
                    System.out.println("⚠ No valid issuing party checkboxes");
                }
            } else {
                System.out.println("⚠ No issuing party checkboxes available");
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error selecting Issuing Party: " + e.getMessage());
            throw new RuntimeException("Failed to select Issuing Party", e);
        }
    }
    
    /**
     * Select random Respondent (supports multiple selections)
     */
    public void selectRandomRespondent() {
        try {
            System.out.println("Selecting Respondent(s)...");
            Thread.sleep(2000);
            
            WebElement respondentDropdown = driver.findElement(By.xpath("//*[@id='againstRoleSection']/div/div[1]/div/div[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", respondentDropdown);
            Thread.sleep(800);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", respondentDropdown);
            System.out.println("Clicked Respondent dropdown");
            Thread.sleep(2000);
            
            // Find checkbox inputs specifically
            List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@id='NoticeePetResDropdown']//input[@type='checkbox']"));
            System.out.println("Found " + checkboxes.size() + " respondent checkboxes");
            
            if (!checkboxes.isEmpty()) {
                // Filter valid checkboxes
                List<WebElement> validCheckboxes = new java.util.ArrayList<>();
                for (WebElement checkbox : checkboxes) {
                    if (checkbox.isDisplayed() && checkbox.isEnabled()) {
                        WebElement parent = checkbox.findElement(By.xpath("./parent::*"));
                        String labelText = parent.getText().trim();
                        if (!labelText.isEmpty() && !labelText.equalsIgnoreCase("No Record Found")) {
                            validCheckboxes.add(checkbox);
                        }
                    }
                }
                
                if (!validCheckboxes.isEmpty()) {
                    // Select 1-3 random respondents
                    int numToSelect = Math.min(random.nextInt(3) + 1, validCheckboxes.size());
                    System.out.println("Selecting " + numToSelect + " Respondent(s)...");
                    
                    for (int i = 0; i < numToSelect; i++) {
                        try {
                            WebElement checkbox = validCheckboxes.get(i);
                            // Get label text
                            WebElement parent = checkbox.findElement(By.xpath("./parent::*"));
                            String labelText = parent.getText().trim();
                            
                            // Scroll into view and click
                            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
                            Thread.sleep(300);
                            
                            if (!checkbox.isSelected()) {
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
                                Thread.sleep(500);
                                System.out.println("  ✓ Selected Respondent: " + labelText);
                            }
                        } catch (Exception ex) {
                            System.out.println("  ! Failed to select respondent #" + (i + 1) + ": " + ex.getMessage());
                        }
                    }
                    System.out.println("Respondent(s) selected successfully");
                } else {
                    System.out.println("⚠ No valid respondent checkboxes");
                }
            } else {
                System.out.println("⚠ No respondent checkboxes available");
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error selecting Respondent: " + e.getMessage());
            throw new RuntimeException("Failed to select Respondent", e);
        }
    }
    
    /**
     * Select random Petitioner's Advocate (supports multiple selections)
     */
    public void selectRandomAdvocate() {
        try {
            System.out.println("Selecting Petitioner's Advocate(s)...");
            Thread.sleep(2000);
            
            WebElement advocateDropdown = driver.findElement(By.xpath("//*[@id='issuingAdvocateSection']/div/div[1]/div/div[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", advocateDropdown);
            Thread.sleep(800);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", advocateDropdown);
            System.out.println("Clicked Petitioner's Advocate dropdown");
            Thread.sleep(2000);
            
            // Find checkbox inputs specifically
            List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@id='advocatesDropdown']//input[@type='checkbox']"));
            System.out.println("Found " + checkboxes.size() + " advocate checkboxes");
            
            if (!checkboxes.isEmpty()) {
                // Filter valid checkboxes
                List<WebElement> validCheckboxes = new java.util.ArrayList<>();
                for (WebElement checkbox : checkboxes) {
                    if (checkbox.isDisplayed() && checkbox.isEnabled()) {
                        validCheckboxes.add(checkbox);
                    }
                }
                
                if (!validCheckboxes.isEmpty()) {
                    // Select 1-2 random advocates
                    int numToSelect = Math.min(random.nextInt(2) + 1, validCheckboxes.size());
                    System.out.println("Selecting " + numToSelect + " Petitioner's Advocate(s)...");
                    
                    for (int i = 0; i < numToSelect; i++) {
                        try {
                            WebElement checkbox = validCheckboxes.get(i);
                            // Get label text
                            WebElement parent = checkbox.findElement(By.xpath("./parent::*"));
                            String labelText = parent.getText().trim();
                            
                            // Scroll into view and click
                            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
                            Thread.sleep(300);
                            
                            if (!checkbox.isSelected()) {
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
                                Thread.sleep(500);
                                System.out.println("  ✓ Selected Advocate: " + labelText);
                            }
                        } catch (Exception ex) {
                            System.out.println("  ! Failed to select advocate #" + (i + 1) + ": " + ex.getMessage());
                        }
                    }
                    System.out.println("Petitioner's Advocate(s) selected successfully");
                } else {
                    System.out.println("⚠ No valid advocate checkboxes");
                }
            } else {
                System.out.println("⚠ No advocate checkboxes available");
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error selecting Advocate: " + e.getMessage());
            throw new RuntimeException("Failed to select Advocate", e);
        }
    }
    
    /**
     * Enter random Case Number
     */
    public void enterCaseNumber() {
        try {
            System.out.println("Entering random Case Number...");
            waitHelper.waitForElementVisible(caseNumberInput);
            
            String caseNumber = "CRIM" + System.currentTimeMillis();
            caseNumberInput.clear();
            caseNumberInput.sendKeys(caseNumber);
            
            System.out.println("Case Number entered: " + caseNumber);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error entering Case Number: " + e.getMessage());
            throw new RuntimeException("Failed to enter Case Number", e);
        }
    }
    
    /**
     * Select random Priority
     */
    public void selectRandomPriority() {
        try {
            System.out.println("Selecting random Priority...");
            Thread.sleep(1500);
            
            // Try to find element with both xpaths
            WebElement priorityElement = null;
            try {
                priorityElement = driver.findElement(By.xpath("//*[@id='PriorityId']"));
            } catch (Exception e) {
                priorityElement = driver.findElement(By.xpath("/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div/form/div[1]/div/div/div[1]/div[2]/div/div[6]/div/div[1]/div/div[20]/div/select"));
            }
            
            // Scroll element into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", priorityElement);
            Thread.sleep(800);
            
            // Highlight element for debugging
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", priorityElement);
            Thread.sleep(500);
            
            System.out.println("Priority dropdown found. Tag: " + priorityElement.getTagName() + ", Displayed: " + priorityElement.isDisplayed() + ", Enabled: " + priorityElement.isEnabled());
            
            // Click to focus the dropdown first using JavaScript
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", priorityElement);
                Thread.sleep(500);
                System.out.println("Clicked Priority dropdown with JavaScript");
            } catch (Exception clickEx) {
                System.out.println("JavaScript click attempt: " + clickEx.getMessage());
            }
            
            // Now try to select using JavaScript directly
            String selectedOption = (String) ((JavascriptExecutor) driver).executeScript(
                "var select = arguments[0];" +
                "console.log('Options count: ' + select.options.length);" +
                "if(select.options.length > 1) {" +
                "  var validOptions = [];" +
                "  for(var i = 1; i < select.options.length; i++) {" +
                "    if(select.options[i].value && select.options[i].value !== '') {" +
                "      validOptions.push(i);" +
                "    }" +
                "  }" +
                "  if(validOptions.length > 0) {" +
                "    var randomIndex = validOptions[Math.floor(Math.random() * validOptions.length)];" +
                "    select.selectedIndex = randomIndex;" +
                "    select.dispatchEvent(new Event('change', { bubbles: true }));" +
                "    select.dispatchEvent(new Event('blur', { bubbles: true }));" +
                "    console.log('Selected index: ' + randomIndex + ', value: ' + select.options[randomIndex].text);" +
                "    return select.options[randomIndex].text;" +
                "  }" +
                "}" +
                "return 'No valid options';", 
                priorityElement
            );
            
            System.out.println("✓ Selected Priority: " + selectedOption);
            Thread.sleep(1000);
            
            // Remove highlight
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", priorityElement);
            
        } catch (Exception e) {
            System.err.println("Error selecting Priority: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to select Priority", e);
        }
    }
    
    /**
     * Select random Parties (supports multiple selections)
     */
    public void selectRandomParties() {
        try {
            System.out.println("Selecting Parties...");
            Thread.sleep(2000);
            
            WebElement partiesDropdown = driver.findElement(By.xpath("//*[@id='flush-collapseOne']/div/div[6]/div/div[1]/div/div[21]/div/div/div[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", partiesDropdown);
            Thread.sleep(800);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", partiesDropdown);
            System.out.println("Clicked Parties dropdown");
            Thread.sleep(2000);
            
            // Find checkbox inputs specifically
            List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@id='partyDropdown']//input[@type='checkbox']"));
            System.out.println("Found " + checkboxes.size() + " party checkboxes");
            
            if (!checkboxes.isEmpty()) {
                // Filter valid checkboxes
                List<WebElement> validCheckboxes = new java.util.ArrayList<>();
                for (WebElement checkbox : checkboxes) {
                    if (checkbox.isDisplayed() && checkbox.isEnabled()) {
                        WebElement parent = checkbox.findElement(By.xpath("./parent::*"));
                        String labelText = parent.getText().trim();
                        if (!labelText.isEmpty() && !labelText.equalsIgnoreCase("No Record Found")) {
                            validCheckboxes.add(checkbox);
                        }
                    }
                }
                
                if (!validCheckboxes.isEmpty()) {
                    // Select 1-3 random parties
                    int numToSelect = Math.min(random.nextInt(3) + 1, validCheckboxes.size());
                    System.out.println("Selecting " + numToSelect + " Parties...");
                    
                    for (int i = 0; i < numToSelect; i++) {
                        try {
                            WebElement checkbox = validCheckboxes.get(i);
                            // Get label text
                            WebElement parent = checkbox.findElement(By.xpath("./parent::*"));
                            String labelText = parent.getText().trim();
                            
                            // Scroll into view and click
                            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
                            Thread.sleep(300);
                            
                            if (!checkbox.isSelected()) {
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
                                Thread.sleep(500);
                                System.out.println("  ✓ Selected Party: " + labelText);
                            }
                        } catch (Exception ex) {
                            System.out.println("  ! Failed to select party #" + (i + 1) + ": " + ex.getMessage());
                        }
                    }
                    System.out.println("Parties selected successfully");
                } else {
                    System.out.println("⚠ No valid party checkboxes");
                }
            } else {
                System.out.println("⚠ No party checkboxes available");
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error selecting Parties: " + e.getMessage());
            throw new RuntimeException("Failed to select Parties", e);
        }
    }
    
    /**
     * Click Next for Basic Details
     */
    public void clickNextBasicDetails() {
        try {
            System.out.println("Clicking Next for Basic Details...");
            waitHelper.waitForElementClickable(nextBasicDetailsButton);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBasicDetailsButton);
            Thread.sleep(500);
            
            try {
                nextBasicDetailsButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBasicDetailsButton);
            }
            
            System.out.println("Next for Basic Details clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Next for Basic Details: " + e.getMessage());
            throw new RuntimeException("Failed to click Next for Basic Details", e);
        }
    }
    
    /**
     * Enter random Claim Amount
     */
    public void enterClaimAmount() {
        try {
            System.out.println("Entering random Claim Amount...");
            waitHelper.waitForElementVisible(claimAmountInput);
            
            int claimAmount = random.nextInt(900000) + 100000; // Between 100000 and 1000000
            claimAmountInput.clear();
            claimAmountInput.sendKeys(String.valueOf(claimAmount));
            
            System.out.println("Claim Amount entered: " + claimAmount);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error entering Claim Amount: " + e.getMessage());
            throw new RuntimeException("Failed to enter Claim Amount", e);
        }
    }
    
    /**
     * Click Next for Stake Details
     */
    public void clickNextStakeDetails() {
        try {
            System.out.println("Clicking Next for Stake Details...");
            waitHelper.waitForElementClickable(nextStakeDetailsButton);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextStakeDetailsButton);
            Thread.sleep(500);
            
            try {
                nextStakeDetailsButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextStakeDetailsButton);
            }
            
            System.out.println("Next for Stake Details clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Next for Stake Details: " + e.getMessage());
            throw new RuntimeException("Failed to click Next for Stake Details", e);
        }
    }
    
    /**
     * Select Ayushi G from Sr Supervisor
     */
    public void selectSrSupervisor() {
        try {
            System.out.println("Selecting Ayushi G from Sr Supervisor...");
            waitHelper.waitForElementVisible(srSupervisorDropdown);
            Select select = new Select(srSupervisorDropdown);
            
            // Try to select "Ayushi G" by visible text, otherwise select first option after "Select"
            try {
                select.selectByVisibleText("Ayushi G");
                System.out.println("Selected Sr Supervisor: Ayushi G");
            } catch (Exception e) {
                List<WebElement> options = select.getOptions();
                if (options.size() > 1) {
                    select.selectByIndex(1);
                    System.out.println("Selected Sr Supervisor: " + select.getFirstSelectedOption().getText());
                }
            }
            
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error selecting Sr Supervisor: " + e.getMessage());
            throw new RuntimeException("Failed to select Sr Supervisor", e);
        }
    }
    
    /**
     * Click Next for Owner Details
     */
    public void clickNextOwnerDetails() {
        try {
            System.out.println("Clicking Next for Owner Details...");
            waitHelper.waitForElementClickable(nextOwnerDetailsButton);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextOwnerDetailsButton);
            Thread.sleep(500);
            
            try {
                nextOwnerDetailsButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextOwnerDetailsButton);
            }
            
            System.out.println("Next for Owner Details clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Next for Owner Details: " + e.getMessage());
            throw new RuntimeException("Failed to click Next for Owner Details", e);
        }
    }
    
    /**
     * Click Next for Case Analysis
     */
    public void clickNextCaseAnalysis() {
        try {
            System.out.println("Clicking Next for Case Analysis...");
            waitHelper.waitForElementClickable(nextCaseAnalysisButton);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextCaseAnalysisButton);
            Thread.sleep(500);
            
            try {
                nextCaseAnalysisButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextCaseAnalysisButton);
            }
            
            System.out.println("Next for Case Analysis clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Next for Case Analysis: " + e.getMessage());
            throw new RuntimeException("Failed to click Next for Case Analysis", e);
        }
    }
    
    /**
     * Enter Registration Date (random past date)
     */
    public void enterRegistrationDate() {
        try {
            System.out.println("Entering Registration Date...");
            
            // Generate random date between 30 to 365 days in the past
            int daysInPast = random.nextInt(336) + 30; // 30 to 365 days ago
            LocalDate date = LocalDate.now().minusDays(daysInPast);
            
            // Format as dd.mm.yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String formattedDate = date.format(formatter);
            
            System.out.println("Generated date: " + formattedDate + " (" + daysInPast + " days ago)");
            
            // Scroll to element
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", registrationDateInput);
            Thread.sleep(800);
            
            waitHelper.waitForElementVisible(registrationDateInput);
            
            // Click on the date input field to open date picker
            try {
                registrationDateInput.click();
                System.out.println("Clicked on Registration Date field");
                Thread.sleep(1000);
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", registrationDateInput);
                System.out.println("Clicked Registration Date field via JavaScript");
                Thread.sleep(1000);
            }
            
            // Clear any existing value and enter the date
            try {
                ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].removeAttribute('readonly');" +
                    "arguments[0].value = '';" +
                    "arguments[0].focus();", 
                    registrationDateInput
                );
                Thread.sleep(300);
                
                registrationDateInput.clear();
                Thread.sleep(300);
                
                registrationDateInput.sendKeys(formattedDate);
                System.out.println("Entered date: " + formattedDate);
                Thread.sleep(1000);
                
                // Trigger change event
                ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
                    "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
                    registrationDateInput
                );
                Thread.sleep(500);
                
                // Click the field again or press Enter to confirm the date
                try {
                    registrationDateInput.sendKeys(Keys.ENTER);
                    System.out.println("Pressed Enter to confirm date");
                    Thread.sleep(500);
                } catch (Exception enterEx) {
                    System.out.println("Enter key not needed");
                }
                
                // If there's a date picker calendar visible, try to click on today or close it
                try {
                    // Try to click outside to close any open date picker
                    driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
                    Thread.sleep(300);
                } catch (Exception escEx) {
                    // Ignore
                }
                
                System.out.println("✓ Registration Date set successfully: " + formattedDate);
                
            } catch (Exception dateEntryEx) {
                System.err.println("Date entry failed: " + dateEntryEx.getMessage());
                // Try one more time with pure JavaScript
                ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].removeAttribute('readonly');" +
                    "arguments[0].value = arguments[1];" +
                    "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                    "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
                    "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
                    registrationDateInput, formattedDate
                );
                System.out.println("✓ Set date via JavaScript: " + formattedDate);
            }
            
            Thread.sleep(1000);
            
        } catch (Exception e) {
            System.err.println("Error entering Registration Date: " + e.getMessage());
            e.printStackTrace();
            // Don't throw exception, just log and continue
            System.out.println("Continuing without Registration Date...");
        }
    }
    
    /**
     * Click Next for Important Dates
     */
    public void clickNextImpDates() {
        try {
            System.out.println("Clicking Next for Important Dates...");
            waitHelper.waitForElementClickable(nextImpDatesButton);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextImpDatesButton);
            Thread.sleep(500);
            
            try {
                nextImpDatesButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextImpDatesButton);
            }
            
            System.out.println("Next for Important Dates clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Next for Important Dates: " + e.getMessage());
            throw new RuntimeException("Failed to click Next for Important Dates", e);
        }
    }
    
    /**
     * Click Next for Cheque Details
     */
    public void clickNextChequeDetails() {
        try {
            System.out.println("Clicking Next for Cheque Details...");
            waitHelper.waitForElementClickable(nextChequeDetailsButton);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextChequeDetailsButton);
            Thread.sleep(500);
            
            try {
                nextChequeDetailsButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextChequeDetailsButton);
            }
            
            System.out.println("Next for Cheque Details clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Next for Cheque Details: " + e.getMessage());
            throw new RuntimeException("Failed to click Next for Cheque Details", e);
        }
    }
    
    /**
     * Click Next for Add FIR
     */
    public void clickNextAddFIR() {
        try {
            System.out.println("Clicking Next for Add FIR...");
            waitHelper.waitForElementClickable(nextAddFIRButton);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextAddFIRButton);
            Thread.sleep(500);
            
            try {
                nextAddFIRButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextAddFIRButton);
            }
            
            System.out.println("Next for Add FIR clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Next for Add FIR: " + e.getMessage());
            throw new RuntimeException("Failed to click Next for Add FIR", e);
        }
    }
    
    /**
     * Click Next for Summon Details
     */
    public void clickNextSummonDetails() {
        try {
            System.out.println("Clicking Next for Summon Details...");
            waitHelper.waitForElementClickable(nextSummonDetailsButton);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextSummonDetailsButton);
            Thread.sleep(500);
            
            try {
                nextSummonDetailsButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextSummonDetailsButton);
            }
            
            System.out.println("Next for Summon Details clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Next for Summon Details: " + e.getMessage());
            throw new RuntimeException("Failed to click Next for Summon Details", e);
        }
    }
    
    /**
     * Click Next for Contingent Liability
     */
    public void clickNextContingentLiability() {
        try {
            System.out.println("Clicking Next for Contingent Liability...");
            waitHelper.waitForElementClickable(nextContingentLiabilityButton);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextContingentLiabilityButton);
            Thread.sleep(500);
            
            try {
                nextContingentLiabilityButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextContingentLiabilityButton);
            }
            
            System.out.println("Next for Contingent Liability clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Next for Contingent Liability: " + e.getMessage());
            throw new RuntimeException("Failed to click Next for Contingent Liability", e);
        }
    }
    
    /**
     * Click Save button
     */
    public void clickSave() {
        try {
            System.out.println("Clicking Save button...");
            waitHelper.waitForElementClickable(saveButton);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
            Thread.sleep(500);
            
            try {
                saveButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
            }
            
            System.out.println("Save button clicked successfully");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error clicking Save button: " + e.getMessage());
            throw new RuntimeException("Failed to click Save button", e);
        }
    }
    
    /**
     * Capture the Case ID
     */
    public String captureCaseId() {
        try {
            System.out.println("Capturing Case ID...");
            Thread.sleep(3000);
            
            waitHelper.waitForElementVisible(caseIdElement);
            capturedCaseId = caseIdElement.getText().trim();
            
            System.out.println("Captured Case ID: " + capturedCaseId);
            return capturedCaseId;
        } catch (Exception e) {
            System.err.println("Error capturing Case ID: " + e.getMessage());
            throw new RuntimeException("Failed to capture Case ID", e);
        }
    }
    
    /**
     * Get captured Case ID
     */
    public String getCapturedCaseId() {
        return capturedCaseId;
    }
    
    /**
     * Click user dropdown
     */
    public void clickUserDropdown() {
        try {
            System.out.println("Clicking user dropdown...");
            Thread.sleep(2000);
            waitHelper.waitForElementClickable(userDropdown);
            
            try {
                userDropdown.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", userDropdown);
            }
            
            System.out.println("User dropdown clicked successfully");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error clicking user dropdown: " + e.getMessage());
            throw new RuntimeException("Failed to click user dropdown", e);
        }
    }
    
    /**
     * Click Logout
     */
    public void clickLogout() {
        try {
            System.out.println("Clicking Logout...");
            waitHelper.waitForElementClickable(logoutLink);
            
            try {
                logoutLink.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutLink);
            }
            
            System.out.println("Logout clicked successfully");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error clicking Logout: " + e.getMessage());
            throw new RuntimeException("Failed to click Logout", e);
        }
    }
    
    /**
     * Login as specific user
     */
    public void loginAs(String username, String password, String otp) {
        try {
            System.out.println("Logging in as: " + username);
            Thread.sleep(2000);
            
            // Enter username
            waitHelper.waitForElementVisible(usernameInput);
            usernameInput.clear();
            usernameInput.sendKeys(username);
            System.out.println("Username entered: " + username);
            
            // Enter password
            passwordInput.clear();
            passwordInput.sendKeys(password);
            System.out.println("Password entered");
            
            // Click sign in
            signInButton.click();
            Thread.sleep(3000);
            
            // Enter OTP
            waitHelper.waitForElementVisible(otpInput);
            otpInput.clear();
            otpInput.sendKeys(otp);
            System.out.println("OTP entered");
            
            // Click sign in again
            signInButton.click();
            Thread.sleep(3000);
            
            System.out.println("Successfully logged in as: " + username);
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
            throw new RuntimeException("Failed to login as " + username, e);
        }
    }
    
    /**
     * Click Actionable Items
     */
    public void clickActionableItems() {
        try {
            System.out.println("Clicking Actionable Items...");
            Thread.sleep(2000);
            waitHelper.waitForElementClickable(actionableItemsLink);
            
            try {
                actionableItemsLink.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actionableItemsLink);
            }
            
            System.out.println("Actionable Items clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Actionable Items: " + e.getMessage());
            throw new RuntimeException("Failed to click Actionable Items", e);
        }
    }
    
    /**
     * Click Approval tab
     */
    public void clickApprovalTab() {
        try {
            System.out.println("Clicking Approval tab...");
            waitHelper.waitForElementClickable(approvalTab);
            
            try {
                approvalTab.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", approvalTab);
            }
            
            System.out.println("Approval tab clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Approval tab: " + e.getMessage());
            throw new RuntimeException("Failed to click Approval tab", e);
        }
    }
    
    /**
     * Click Advocate Allocation Approval
     */
    public void clickAdvocateAllocationApproval() {
        try {
            System.out.println("Clicking Advocate Allocation Approval tab...");
            waitHelper.waitForElementClickable(advocateAllocationApprovalTab);
            
            try {
                advocateAllocationApprovalTab.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", advocateAllocationApprovalTab);
            }
            
            System.out.println("Advocate Allocation Approval tab clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Advocate Allocation Approval tab: " + e.getMessage());
            throw new RuntimeException("Failed to click Advocate Allocation Approval tab", e);
        }
    }
    
    /**
     * Click Advance Filter
     */
    public void clickAdvanceFilter() {
        try {
            System.out.println("Clicking Advance Filter...");
            waitHelper.waitForElementClickable(advanceFilterLink);
            
            try {
                advanceFilterLink.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", advanceFilterLink);
            }
            
            System.out.println("Advance Filter clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Advance Filter: " + e.getMessage());
            throw new RuntimeException("Failed to click Advance Filter", e);
        }
    }
    
    /**
     * Click Apply button
     */
    public void clickApply() {
        try {
            System.out.println("Clicking Apply button...");
            waitHelper.waitForElementClickable(applyButton);
            
            try {
                applyButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", applyButton);
            }
            
            System.out.println("Apply button clicked successfully");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error clicking Apply button: " + e.getMessage());
            throw new RuntimeException("Failed to click Apply button", e);
        }
    }
    
    /**
     * Click Last page
     */
    public void clickLastPage() {
        try {
            System.out.println("Clicking Last page...");
            waitHelper.waitForElementClickable(lastPageLink);
            
            try {
                lastPageLink.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", lastPageLink);
            }
            
            System.out.println("Last page clicked successfully");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error clicking Last page: " + e.getMessage());
            throw new RuntimeException("Failed to click Last page", e);
        }
    }
    
    /**
     * Find and click checkbox for captured Case ID
     */
    public void clickApprovalCheckbox() {
        try {
            System.out.println("===================================================");
            System.out.println("Searching for Case ID in approval table: " + capturedCaseId);
            System.out.println("===================================================");
            Thread.sleep(2000);
            
            // Find all table rows in the approval list
            List<WebElement> tableRows = driver.findElements(By.xpath("//table/tbody/tr"));
            System.out.println("Found " + tableRows.size() + " rows in approval table");
            
            boolean caseFound = false;
            
            // Iterate through each row to find the matching Case ID
            for (int i = 0; i < tableRows.size(); i++) {
                try {
                    WebElement row = tableRows.get(i);
                    String rowText = row.getText();
                    
                    // Check if this row contains the captured Case ID
                    if (rowText.contains(capturedCaseId)) {
                        System.out.println("✓ Found matching case in row " + (i + 1));
                        System.out.println("Row text: " + rowText);
                        
                        // Find the checkbox in this specific row
                        WebElement checkbox = row.findElement(By.xpath(".//input[@type='checkbox']"));
                        
                        // Scroll to checkbox
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkbox);
                        Thread.sleep(500);
                        
                        // Click the checkbox
                        try {
                            checkbox.click();
                        } catch (Exception e) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
                        }
                        
                        System.out.println("✓ Clicked checkbox for Case ID: " + capturedCaseId);
                        caseFound = true;
                        break;
                    }
                } catch (Exception e) {
                    // Skip rows that cause errors
                    continue;
                }
            }
            
            if (!caseFound) {
                System.err.println("ERROR: Case ID " + capturedCaseId + " not found in approval table!");
                System.err.println("Please check if the case appears on the last page or needs pagination");
                throw new RuntimeException("Case ID " + capturedCaseId + " not found in approval table");
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error clicking case checkbox: " + e.getMessage());
            throw new RuntimeException("Failed to click case checkbox for Case ID: " + capturedCaseId, e);
        }
    }
    
    /**
     * Click Approve button and handle alert
     */
    public void clickApprove() {
        try {
            System.out.println("Clicking Approve button...");
            waitHelper.waitForElementClickable(approveButton);
            
            try {
                approveButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", approveButton);
            }
            
            System.out.println("Approve button clicked successfully");
            Thread.sleep(1000);
            
            // Handle the first alert: "Selected records will be approved. Continue?"
            try {
                System.out.println("Waiting for confirmation alert...");
                Thread.sleep(500);
                
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                System.out.println("Alert text: " + alertText);
                alert.accept();
                System.out.println("✓ Alert 1 accepted: Confirmation alert");
                Thread.sleep(1500);
            } catch (Exception alertEx) {
                System.err.println("No first alert appeared: " + alertEx.getMessage());
            }
            
            // Handle the second alert: "Advocate(s) approved!"
            try {
                System.out.println("Waiting for success alert...");
                Thread.sleep(500);
                
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                System.out.println("Alert text: " + alertText);
                alert.accept();
                System.out.println("✓ Alert 2 accepted: Success alert");
                Thread.sleep(2000);
            } catch (Exception alertEx) {
                System.err.println("No second alert appeared: " + alertEx.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Error clicking Approve button: " + e.getMessage());
            throw new RuntimeException("Failed to click Approve button", e);
        }
    }
    
    /**
     * Accept alert (if present)
     */
    public void acceptAlert() {
        try {
            System.out.println("Checking for alert...");
            Thread.sleep(1000);
            
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            alert.accept();
            
            System.out.println("✓ Alert accepted successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("No alert to accept (already handled or not present)");
            // Don't throw exception - alert might have been handled by clickApprove()
        }
    }
    
    /**
     * Click Load Cases button
     */
    public void clickLoadCases() {
        try {
            System.out.println("Clicking Load Cases button...");
            waitHelper.waitForElementClickable(loadCasesButton);
            
            try {
                loadCasesButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loadCasesButton);
            }
            
            System.out.println("Load Cases button clicked successfully");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error clicking Load Cases button: " + e.getMessage());
            throw new RuntimeException("Failed to click Load Cases button", e);
        }
    }
    
    /**
     * Click Action dropdown
     */
    public void clickActionDropdown() {
        try {
            System.out.println("Clicking Action dropdown...");
            waitHelper.waitForElementClickable(actionDropdown);
            
            // Scroll to element
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actionDropdown);
            Thread.sleep(500);
            
            try {
                actionDropdown.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actionDropdown);
            }
            
            System.out.println("Action dropdown clicked successfully");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error clicking Action dropdown: " + e.getMessage());
            throw new RuntimeException("Failed to click Action dropdown", e);
        }
    }
    
    /**
     * Click Details link
     */
    public void clickDetailsLink() {
        try {
            System.out.println("Clicking Details link...");
            waitHelper.waitForElementClickable(detailsLink);
            
            try {
                detailsLink.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", detailsLink);
            }
            
            System.out.println("Details link clicked successfully");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error clicking Details link: " + e.getMessage());
            throw new RuntimeException("Failed to click Details link", e);
        }
    }
}
