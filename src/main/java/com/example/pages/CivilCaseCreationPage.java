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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Civil Case Creation
 */
public class CivilCaseCreationPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final Random random;
    private String capturedSystemId;
    
    // Locators
    @FindBy(xpath = "/html/body/div[3]/div/div/div/ul/li[3]/a")
    private WebElement mattersMenu;
    
    @FindBy(xpath = "/html/body/div[3]/div/div/div/ul/li[3]/ul/li[3]/a")
    private WebElement caseSubmenu;
    
    @FindBy(xpath = "/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div[1]/div[2]/div/div/div[3]/a")
    private WebElement addNewCaseButton;
    
    @FindBy(xpath = "/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div[1]/div[2]/div/div/div[3]/ul/li[1]/a")
    private WebElement civilButton;
    
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
    
    @FindBy(xpath = "//*[@id='createContingentLiabilityNXT']")
    private WebElement nextContingentLiabilityButton;
    
    @FindBy(xpath = "//*[@id='CustomerCreateNew']")
    private WebElement saveButton;
    
    @FindBy(xpath = "//*[@id='case-basicdetails']/div/div[2]/div/div[1]/div")
    private WebElement systemIdElement;
    
    @FindBy(xpath = "/html/body/div[2]/div/main/div[3]/div/div/main/div/div[1]/a")
    private WebElement backButton;
    
    @FindBy(xpath = "//*[@id='CaseSystemId']")
    private WebElement searchInput;
    
    @FindBy(xpath = "//*[@id='quickSearchForm']/button")
    private WebElement searchButton;
    
    @FindBy(xpath = "//*[@id='caseListingContainer']/div/table/tbody/tr/td[10]/div/a/I")
    private WebElement actionButton;
    
    @FindBy(xpath = "//*[@id='caseListingContainer']/div/table/tbody/tr/td[10]/div/ul/li[2]/a")
    private WebElement detailsLink;
    
    // Constructor
    public CivilCaseCreationPage(WebDriver driver) {
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
     * Click Civil button
     */
    public void clickCivil() {
        try {
            System.out.println("Clicking Civil button...");
            
            // Store the current window handle
            String mainWindow = driver.getWindowHandle();
            System.out.println("Main window handle: " + mainWindow);
            
            waitHelper.waitForElementClickable(civilButton);
            
            try {
                civilButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", civilButton);
            }
            
            System.out.println("Civil button clicked successfully");
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
            System.err.println("Error clicking Civil button: " + e.getMessage());
            throw new RuntimeException("Failed to click Civil button", e);
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
            
            System.out.println("Customer ID selected successfully");
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
            
            System.out.println("Account Number selected successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error selecting Account Number: " + e.getMessage());
            throw new RuntimeException("Failed to select Account Number", e);
        }
    }
    
    /**
     * Enter Party name
     */
    public void enterParty() {
        try {
            System.out.println("Entering Party name...");
            String partyName = "TestParty" + System.currentTimeMillis();
            waitHelper.waitForElementVisible(partyInput);
            partyInput.clear();
            partyInput.sendKeys(partyName);
            System.out.println("Entered Party: " + partyName);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error entering Party: " + e.getMessage());
            throw new RuntimeException("Failed to enter Party", e);
        }
    }
    
    /**
     * Select random Issuing Party
     */
    public void selectRandomIssuingParty() {
        try {
            System.out.println("Selecting random Issuing Party...");
            Thread.sleep(2000);
            
            WebElement issuingPartyDropdown = driver.findElement(By.xpath("//*[@id='byRoleSection']/div/div[1]/div/div[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", issuingPartyDropdown);
            Thread.sleep(500);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", issuingPartyDropdown);
            Thread.sleep(2000);
            
            // Select first available option from dropdown - click on the li element or label instead of checkbox
            List<WebElement> options = driver.findElements(By.xpath("//*[@id='businessunitPetResDropdown']//li"));
            if (!options.isEmpty()) {
                // Try clicking the first li element
                WebElement firstOption = options.get(0);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstOption);
                System.out.println("Issuing Party selected successfully");
            } else {
                // Fallback: try clicking first label
                List<WebElement> labels = driver.findElements(By.xpath("//*[@id='businessunitPetResDropdown']//label"));
                if (!labels.isEmpty()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", labels.get(0));
                    System.out.println("Issuing Party selected successfully (via label)");
                }
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error selecting Issuing Party: " + e.getMessage());
            throw new RuntimeException("Failed to select Issuing Party", e);
        }
    }
    
    /**
     * Select random Respondent
     */
    public void selectRandomRespondent() {
        try {
            System.out.println("Selecting random Respondent...");
            Thread.sleep(2000);
            
            WebElement respondentDropdown = driver.findElement(By.xpath("//*[@id='againstRoleSection']/div/div[1]/div/div[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", respondentDropdown);
            Thread.sleep(500);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", respondentDropdown);
            Thread.sleep(2000);
            
            // Select first available option from dropdown - click on the li element instead of checkbox
            List<WebElement> options = driver.findElements(By.xpath("//*[@id='NoticeePetResDropdown']//li"));
            if (!options.isEmpty()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", options.get(0));
                System.out.println("Respondent selected successfully");
            } else {
                List<WebElement> labels = driver.findElements(By.xpath("//*[@id='NoticeePetResDropdown']//label"));
                if (!labels.isEmpty()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", labels.get(0));
                    System.out.println("Respondent selected successfully (via label)");
                }
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error selecting Respondent: " + e.getMessage());
            throw new RuntimeException("Failed to select Respondent", e);
        }
    }
    
    /**
     * Select random Petitioner's Advocate
     */
    public void selectRandomAdvocate() {
        try {
            System.out.println("Selecting random Petitioner's Advocate...");
            Thread.sleep(2000);
            
            WebElement advocateDropdown = driver.findElement(By.xpath("//*[@id='issuingAdvocateSection']/div/div[1]/div/div[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", advocateDropdown);
            Thread.sleep(500);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", advocateDropdown);
            Thread.sleep(2000);
            
            // Select first available option from dropdown - click on the li element instead of checkbox
            List<WebElement> options = driver.findElements(By.xpath("//*[@id='advocatesDropdown']//li"));
            if (!options.isEmpty()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", options.get(0));
                System.out.println("Advocate selected successfully");
            } else {
                List<WebElement> labels = driver.findElements(By.xpath("//*[@id='advocatesDropdown']//label"));
                if (!labels.isEmpty()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", labels.get(0));
                    System.out.println("Advocate selected successfully (via label)");
                }
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
            String caseNumber = "CASE" + System.currentTimeMillis();
            waitHelper.waitForElementVisible(caseNumberInput);
            caseNumberInput.clear();
            caseNumberInput.sendKeys(caseNumber);
            System.out.println("Entered Case Number: " + caseNumber);
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
            waitHelper.waitForElementVisible(priorityDropdown);
            Select select = new Select(priorityDropdown);
            
            List<WebElement> options = select.getOptions();
            if (options.size() > 1) {
                int randomIndex = random.nextInt(options.size() - 1) + 1;
                select.selectByIndex(randomIndex);
                System.out.println("Selected Priority: " + select.getFirstSelectedOption().getText());
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error selecting Priority: " + e.getMessage());
            throw new RuntimeException("Failed to select Priority", e);
        }
    }
    
    /**
     * Select random Parties
     */
    public void selectRandomParties() {
        try {
            System.out.println("Selecting random Parties...");
            Thread.sleep(2000);
            
            WebElement partiesDropdown = driver.findElement(By.xpath("//*[@id='flush-collapseOne']/div/div[6]/div/div[1]/div/div[21]/div/div/div[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", partiesDropdown);
            Thread.sleep(500);
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", partiesDropdown);
            Thread.sleep(2000);
            
            // Select first available option from dropdown - click on the li element instead of checkbox
            List<WebElement> options = driver.findElements(By.xpath("//*[@id='partyDropdown']//li"));
            if (!options.isEmpty()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", options.get(0));
                System.out.println("Party selected successfully");
            } else {
                List<WebElement> labels = driver.findElements(By.xpath("//*[@id='partyDropdown']//label"));
                if (!labels.isEmpty()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", labels.get(0));
                    System.out.println("Party selected successfully (via label)");
                }
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error selecting Parties: " + e.getMessage());
            throw new RuntimeException("Failed to select Parties", e);
        }
    }
    
    /**
     * Click Next - Basic Details
     */
    public void clickNextBasicDetails() {
        try {
            System.out.println("Clicking Next - Basic Details...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBasicDetailsButton);
            Thread.sleep(500);
            
            try {
                nextBasicDetailsButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBasicDetailsButton);
            }
            
            System.out.println("Next - Basic Details clicked successfully");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error clicking Next - Basic Details: " + e.getMessage());
            throw new RuntimeException("Failed to click Next - Basic Details", e);
        }
    }
    
    /**
     * Enter Claim Amount
     */
    public void enterClaimAmount() {
        try {
            System.out.println("Entering Claim Amount...");
            Thread.sleep(2000);
            
            // Try to find the claim amount field - it might not exist or be on a different page
            List<WebElement> claimAmountElements = driver.findElements(By.xpath("//*[@id='ClaimAmount']"));
            
            if (claimAmountElements.isEmpty()) {
                System.out.println("Claim Amount field not found on this page - skipping");
                return;
            }
            
            WebElement claimAmountField = claimAmountElements.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", claimAmountField);
            Thread.sleep(500);
            
            int claimAmount = random.nextInt(900000) + 100000; // Random between 100000-999999
            claimAmountField.clear();
            claimAmountField.sendKeys(String.valueOf(claimAmount));
            System.out.println("Entered Claim Amount: " + claimAmount);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error entering Claim Amount: " + e.getMessage());
            System.out.println("Continuing without Claim Amount");
        }
    }
    
    /**
     * Click Next - Stake Details
     */
    public void clickNextStakeDetails() {
        try {
            System.out.println("Clicking Next - Stake Details...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextStakeDetailsButton);
            Thread.sleep(500);
            
            try {
                nextStakeDetailsButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextStakeDetailsButton);
            }
            
            System.out.println("Next - Stake Details clicked successfully");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error clicking Next - Stake Details: " + e.getMessage());
            throw new RuntimeException("Failed to click Next - Stake Details", e);
        }
    }
    
    /**
     * Select Ayushi G from Sr Supervisor
     */
    public void selectSrSupervisor() {
        try {
            System.out.println("Selecting Sr Supervisor - Ayushi G...");
            Thread.sleep(2000);
            
            // Check if supervisor dropdown exists on this page
            List<WebElement> supervisorElements = driver.findElements(By.xpath("//*[@id='4']"));
            
            if (supervisorElements.isEmpty()) {
                System.out.println("Sr Supervisor dropdown not found on this page - skipping");
                return;
            }
            
            WebElement supervisorDropdown = supervisorElements.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", supervisorDropdown);
            Thread.sleep(500);
            
            Select select = new Select(supervisorDropdown);
            
            // Try to select "Ayushi G" or select first available option
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
            
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error selecting Sr Supervisor: " + e.getMessage());
            System.out.println("Continuing without Sr Supervisor selection");
        }
    }
    
    /**
     * Click Next - Owner Details
     */
    public void clickNextOwnerDetails() {
        try {
            System.out.println("Clicking Next - Owner Details...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextOwnerDetailsButton);
            Thread.sleep(500);
            
            try {
                nextOwnerDetailsButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextOwnerDetailsButton);
            }
            
            System.out.println("Next - Owner Details clicked successfully");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error clicking Next - Owner Details: " + e.getMessage());
            throw new RuntimeException("Failed to click Next - Owner Details", e);
        }
    }
    
    /**
     * Click Next - Case Analysis
     */
    public void clickNextCaseAnalysis() {
        try {
            System.out.println("Clicking Next - Case Analysis...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextCaseAnalysisButton);
            Thread.sleep(500);
            
            try {
                nextCaseAnalysisButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextCaseAnalysisButton);
            }
            
            System.out.println("Next - Case Analysis clicked successfully");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error clicking Next - Case Analysis: " + e.getMessage());
            throw new RuntimeException("Failed to click Next - Case Analysis", e);
        }
    }
    
    /**
     * Enter Registration Date
     */
    public void enterRegistrationDate() {
        try {
            System.out.println("Entering Registration Date...");
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            String formattedDate = date.format(formatter);
            
            waitHelper.waitForElementVisible(registrationDateInput);
            registrationDateInput.clear();
            registrationDateInput.sendKeys(formattedDate);
            System.out.println("Entered Registration Date: " + formattedDate);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error entering Registration Date: " + e.getMessage());
            throw new RuntimeException("Failed to enter Registration Date", e);
        }
    }
    
    /**
     * Click Next - Important Dates
     */
    public void clickNextImpDates() {
        try {
            System.out.println("Clicking Next - Important Dates...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextImpDatesButton);
            Thread.sleep(500);
            
            try {
                nextImpDatesButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextImpDatesButton);
            }
            
            System.out.println("Next - Important Dates clicked successfully");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error clicking Next - Important Dates: " + e.getMessage());
            throw new RuntimeException("Failed to click Next - Important Dates", e);
        }
    }
    
    /**
     * Click Next - Contingent Liability
     */
    public void clickNextContingentLiability() {
        try {
            System.out.println("Clicking Next - Contingent Liability...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextContingentLiabilityButton);
            Thread.sleep(500);
            
            try {
                nextContingentLiabilityButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextContingentLiabilityButton);
            }
            
            System.out.println("Next - Contingent Liability clicked successfully");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error clicking Next - Contingent Liability: " + e.getMessage());
            throw new RuntimeException("Failed to click Next - Contingent Liability", e);
        }
    }
    
    /**
     * Click Save button
     */
    public void clickSave() {
        try {
            System.out.println("Clicking Save button...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
            Thread.sleep(500);
            
            try {
                saveButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
            }
            
            System.out.println("Save button clicked successfully");
            Thread.sleep(5000); // Wait for save to complete
        } catch (Exception e) {
            System.err.println("Error clicking Save button: " + e.getMessage());
            throw new RuntimeException("Failed to click Save button", e);
        }
    }
    
    /**
     * Capture System ID
     */
    public String captureSystemId() {
        try {
            System.out.println("Capturing System ID...");
            Thread.sleep(3000);
            
            waitHelper.waitForElementVisible(systemIdElement);
            capturedSystemId = systemIdElement.getText().trim();
            System.out.println("Captured System ID: " + capturedSystemId);
            
            return capturedSystemId;
        } catch (Exception e) {
            System.err.println("Error capturing System ID: " + e.getMessage());
            throw new RuntimeException("Failed to capture System ID", e);
        }
    }
    
    /**
     * Click Back button
     */
    public void clickBack() {
        try {
            System.out.println("Clicking Back button...");
            Thread.sleep(2000);
            
            try {
                backButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", backButton);
            }
            
            System.out.println("Back button clicked successfully");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error clicking Back button: " + e.getMessage());
            throw new RuntimeException("Failed to click Back button", e);
        }
    }
    
    /**
     * Search for System ID
     */
    public void searchSystemId() {
        try {
            System.out.println("Searching for System ID: " + capturedSystemId);
            waitHelper.waitForElementVisible(searchInput);
            searchInput.clear();
            searchInput.sendKeys(capturedSystemId);
            Thread.sleep(1000);
            
            searchButton.click();
            System.out.println("Search button clicked");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error searching for System ID: " + e.getMessage());
            throw new RuntimeException("Failed to search for System ID", e);
        }
    }
    
    /**
     * Click Action button
     */
    public void clickAction() {
        try {
            System.out.println("Clicking Action button...");
            waitHelper.waitForElementClickable(actionButton);
            
            try {
                actionButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actionButton);
            }
            
            System.out.println("Action button clicked successfully");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error clicking Action button: " + e.getMessage());
            throw new RuntimeException("Failed to click Action button", e);
        }
    }
    
    /**
     * Click Details link
     */
    public void clickDetails() {
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
    
    /**
     * Get captured System ID
     */
    public String getCapturedSystemId() {
        return capturedSystemId;
    }
}
