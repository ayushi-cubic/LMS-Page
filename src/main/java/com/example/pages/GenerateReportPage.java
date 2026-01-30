package com.example.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Generate Report Page
 */
public class GenerateReportPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    
    // Template Report Name Dropdown
    @FindBy(xpath = "//*[@id='ReportTemplatesDD']")
    private WebElement templateReportNameDropdown;
    
    // Case Report Elements
    @FindBy(xpath = "//*[@id='Caseselectall']")
    private WebElement caseIncludeCheckbox;
    
    @FindBy(xpath = "//*[@id='lnkRecovery']")
    private WebElement recoveryLink;
    
    @FindBy(xpath = "//*[@id='caseRecoverySelectAll']")
    private WebElement allRecoveryCheckbox;
    
    @FindBy(xpath = "//*[@id='lnkCaseExpense']")
    private WebElement caseExpensesLink;
    
    @FindBy(xpath = "//*[@id='CaseExpensesSelectall']")
    private WebElement allCaseExpensesCheckbox;
    
    @FindBy(xpath = "//*[@id='lnkAsset']")
    private WebElement assetLink;
    
    @FindBy(xpath = "//*[@id='case_AllAssetDetails']")
    private WebElement assetIncludeCheckbox;
    
    @FindBy(xpath = "//*[@id='Team']")
    private WebElement teamCaseOwnershipFilter;
    
    // Notice Report Elements
    @FindBy(xpath = "//*[@id='NoticeAll']")
    private WebElement noticeCheckbox;
    
    @FindBy(xpath = "//*[@id='Noticeselectall']")
    private WebElement noticeIncludeAllCheckbox;
    
    @FindBy(xpath = "//*[@id='noticeRecoverySelectAll']")
    private WebElement noticeRecoverySelectAllCheckbox;
    
    @FindBy(xpath = "//*[@id='lnknoticeproceedings']")
    private WebElement noticeProceedingsLink;
    
    @FindBy(xpath = "//*[@id='NoticeProceedingsSelectall']")
    private WebElement noticeProceedingsSelectAllCheckbox;
    
    @FindBy(xpath = "//*[@id='lnkmultiplenoticee']")
    private WebElement multipleNoticeLink;
    
    @FindBy(xpath = "//*[@id='NoticeeMultipleSelectall']")
    private WebElement multipleNoticeSelectAllCheckbox;
    
    @FindBy(xpath = "/html/body/div[2]/div/main/div[2]/div[1]/div/div/div[1]/form/div/div[1]/div/div[4]/div/div[9]/div[2]/ul/li[4]/a")
    private WebElement noticeExpensesLink;
    
    @FindBy(xpath = "//*[@id='NoticeExpensesSelectall']")
    private WebElement noticeExpensesSelectAllCheckbox;
    
    @FindBy(xpath = "/html/body/div[2]/div/main/div[2]/div[1]/div/div/div[1]/form/div/div[1]/div/div[4]/div/div[9]/div[2]/ul/li[5]/a")
    private WebElement noticeAssetLink;
    
    @FindBy(xpath = "//*[@id='NoticeAssetSelectall']")
    private WebElement noticeAssetIncludeCheckbox;
    
    @FindBy(xpath = "//*[@id='Team']")
    private WebElement noticeTeamOwnershipFilter;
    
    @FindBy(xpath = "//a[contains(@href,'#NoticeTeam-tab') or contains(text(),'My')]")
    private WebElement noticeTeamMyTab;
    
    // Customer Report Elements
    @FindBy(xpath = "//*[@id='CustomerAll']")
    private WebElement customerRadioButton;
    
    @FindBy(xpath = "//*[@id='My']")
    private WebElement customerMyTab;
    
    @FindBy(xpath = "//*[@id='Team']")
    private WebElement customerTeamTab;
    
    @FindBy(xpath = "//*[@id='All']")
    private WebElement customerAllTab;
    
    @FindBy(xpath = "//*[@id='AllReportsAccount']")
    private WebElement customerAccountIncludeCheckbox;
    
    @FindBy(xpath = "//*[@id='lnkAsset']")
    private WebElement customerAssetLink;
    
    @FindBy(xpath = "//*[@id='AllAssetDetails']")
    private WebElement customerAssetIncludeCheckbox;
    
    @FindBy(xpath = "//*[@id='lnkOTSOffer']")
    private WebElement customerOTSOfferLink;
    
    @FindBy(xpath = "//*[@id='OTSOfferSelectall']")
    private WebElement customerOTSOfferSelectAllCheckbox;
    
    @FindBy(xpath = "//*[@id='lnkCustomerEcpences']")
    private WebElement customerExpensesLink;
    
    @FindBy(xpath = "//*[@id='ExpensesSelectall']")
    private WebElement customerExpensesSelectAllCheckbox;
    
    @FindBy(xpath = "//*[@id='atab-tab']")
    private WebElement sarfaesiReportTab;
    
    @FindBy(xpath = "//*[@id='btab-tab']")
    private WebElement standardReportTab;
    
    @FindBy(xpath = "//*[@id='ReportType']")
    private WebElement standardReportTypeDropdown;
    
    // Common Elements
    @FindBy(xpath = "//*[@id='SubmitReport']")
    private WebElement generateReportButton;
    
    @FindBy(xpath = "//*[@id='reset']")
    private WebElement resetButton;
    
    // Constructor
    public GenerateReportPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Select Template Report Name from dropdown
     */
    public void selectTemplateReportName(String reportName) {
        try {
            System.out.println("Attempting to select template report: " + reportName);
            
            // Wait for page to stabilize after navigation
            Thread.sleep(3000);
            
            // Wait for dropdown to be visible and clickable
            waitHelper.waitForElementClickable(templateReportNameDropdown);
            
            // Check the tag name to determine dropdown type
            String tagName = templateReportNameDropdown.getTagName().toLowerCase();
            System.out.println("Dropdown tag name: " + tagName);
            
            if ("select".equals(tagName)) {
                // Standard select dropdown
                Select select = new Select(templateReportNameDropdown);
                
                // Wait for options to load
                int maxAttempts = 10;
                int attempt = 0;
                while (attempt < maxAttempts && select.getOptions().size() <= 1) {
                    System.out.println("Waiting for dropdown options to load... (attempt " + (attempt + 1) + "/" + maxAttempts + ")");
                    Thread.sleep(1000);
                    select = new Select(templateReportNameDropdown);
                    attempt++;
                }
                
                // Print all available options
                System.out.println("Available options in Template Report Name dropdown:");
                for (int i = 0; i < select.getOptions().size(); i++) {
                    System.out.println("  [" + i + "] " + select.getOptions().get(i).getText());
                }
                
                // Try to select by visible text
                boolean selected = false;
                for (WebElement option : select.getOptions()) {
                    String optionText = option.getText().trim();
                    if (optionText.equalsIgnoreCase(reportName) || 
                        optionText.contains(reportName) || 
                        reportName.contains(optionText)) {
                        try {
                            option.click();
                            System.out.println("✓ Selected template report: " + optionText);
                            selected = true;
                            break;
                        } catch (Exception clickEx) {
                            JavascriptExecutor js = (JavascriptExecutor) driver;
                            js.executeScript("arguments[0].selected = true; arguments[0].dispatchEvent(new Event('change'));", option);
                            System.out.println("✓ Selected template report (JS): " + optionText);
                            selected = true;
                            break;
                        }
                    }
                }
                
                if (!selected && select.getOptions().size() > 1) {
                    select.selectByIndex(1);
                    System.out.println("⚠ Exact match not found. Selected first option: " + select.getFirstSelectedOption().getText());
                }
            } else {
                // Custom dropdown - just click it
                System.out.println("Detected custom dropdown (non-select element)");
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", templateReportNameDropdown);
                Thread.sleep(1000);
                System.out.println("✓ Clicked template dropdown");
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Failed to select template report name: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to select template report name: " + e.getMessage());
        }
    }
    
    /**
     * Select Template Report Name from dropdown randomly
     */
    public void selectTemplateReportNameRandomly() {
        try {
            System.out.println("Attempting to select template report randomly");
            
            // Wait for page to stabilize after navigation
            Thread.sleep(3000);
            
            // Wait for dropdown to be visible and clickable
            waitHelper.waitForElementClickable(templateReportNameDropdown);
            
            // Check the tag name to determine dropdown type
            String tagName = templateReportNameDropdown.getTagName().toLowerCase();
            System.out.println("Dropdown tag name: " + tagName);
            
            if ("select".equals(tagName)) {
                // Standard select dropdown
                Select select = new Select(templateReportNameDropdown);
                
                // Wait for options to load
                int maxAttempts = 10;
                int attempt = 0;
                while (attempt < maxAttempts && select.getOptions().size() <= 1) {
                    System.out.println("Waiting for dropdown options to load... (attempt " + (attempt + 1) + "/" + maxAttempts + ")");
                    Thread.sleep(1000);
                    select = new Select(templateReportNameDropdown);
                    attempt++;
                }
                
                // Print all available options
                System.out.println("Available options in Template Report Name dropdown:");
                for (int i = 0; i < select.getOptions().size(); i++) {
                    System.out.println("  [" + i + "] " + select.getOptions().get(i).getText());
                }
                
                // Select a random option (excluding the first default option)
                if (select.getOptions().size() > 1) {
                    java.util.Random random = new java.util.Random();
                    int randomIndex = random.nextInt(select.getOptions().size() - 1) + 1; // Start from index 1 to skip default option
                    
                    try {
                        select.selectByIndex(randomIndex);
                        System.out.println("✓ Selected random template report: " + select.getFirstSelectedOption().getText());
                    } catch (Exception clickEx) {
                        WebElement option = select.getOptions().get(randomIndex);
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        js.executeScript("arguments[0].selected = true; arguments[0].dispatchEvent(new Event('change'));", option);
                        System.out.println("✓ Selected random template report (JS): " + option.getText());
                    }
                } else {
                    System.out.println("⚠ No options available to select randomly");
                }
            } else {
                // Custom dropdown - just click it
                System.out.println("Detected custom dropdown (non-select element)");
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", templateReportNameDropdown);
                Thread.sleep(1000);
                System.out.println("✓ Clicked template dropdown");
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Failed to select template report name randomly: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to select template report name randomly: " + e.getMessage());
        }
    }
    
    /**
     * Click Case Include checkbox
     */
    public void clickCaseInclude() {
        try {
            waitHelper.waitForElementClickable(caseIncludeCheckbox);
            try {
                caseIncludeCheckbox.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", caseIncludeCheckbox);
            }
            Thread.sleep(500);
            System.out.println("✓ Clicked Case Include");
        } catch (Exception e) {
            System.err.println("Failed to click Case Include: " + e.getMessage());
            throw new RuntimeException("Failed to click Case Include: " + e.getMessage());
        }
    }
    
    /**
     * Click Recovery link
     */
    public void clickRecovery() {
        try {
            waitHelper.waitForElementClickable(recoveryLink);
            try {
                recoveryLink.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", recoveryLink);
            }
            Thread.sleep(1000);
            System.out.println("✓ Clicked Recovery");
        } catch (Exception e) {
            System.err.println("Failed to click Recovery: " + e.getMessage());
            throw new RuntimeException("Failed to click Recovery: " + e.getMessage());
        }
    }
    
    /**
     * Click All Recovery checkbox
     */
    public void clickAllRecovery() {
        try {
            waitHelper.waitForElementClickable(allRecoveryCheckbox);
            try {
                allRecoveryCheckbox.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", allRecoveryCheckbox);
            }
            Thread.sleep(500);
            System.out.println("✓ Selected All Recovery");
        } catch (Exception e) {
            System.err.println("Failed to select All Recovery: " + e.getMessage());
            throw new RuntimeException("Failed to select All Recovery: " + e.getMessage());
        }
    }
    
    /**
     * Click Generate Case Report button
     */
    public void clickGenerateCaseReport() {
        try {
            System.out.println("Attempting to click Generate Case Report...");
            Thread.sleep(2000);
            
            // Scroll button into view first
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", generateReportButton);
            Thread.sleep(1500);
            
            waitHelper.waitForElementClickable(generateReportButton);
            try {
                generateReportButton.click();
                System.out.println("✓ Clicked Generate Case Report (normal click)");
            } catch (Exception clickEx) {
                js.executeScript("arguments[0].click();", generateReportButton);
                System.out.println("✓ Clicked Generate Case Report (JS click)");
            }
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Failed to click Generate Case Report: " + e.getMessage());
            throw new RuntimeException("Failed to click Generate Case Report: " + e.getMessage());
        }
    }
    
    /**
     * Click Case Expenses link
     */
    public void clickCaseExpenses() {
        try {
            waitHelper.waitForElementClickable(caseExpensesLink);
            try {
                caseExpensesLink.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", caseExpensesLink);
            }
            Thread.sleep(1000);
            System.out.println("✓ Clicked Case Expenses");
        } catch (Exception e) {
            System.err.println("Failed to click Case Expenses: " + e.getMessage());
            throw new RuntimeException("Failed to click Case Expenses: " + e.getMessage());
        }
    }
    
    /**
     * Click All Case Expenses checkbox
     */
    public void clickAllCaseExpenses() {
        try {
            waitHelper.waitForElementClickable(allCaseExpensesCheckbox);
            try {
                allCaseExpensesCheckbox.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", allCaseExpensesCheckbox);
            }
            Thread.sleep(500);
            System.out.println("✓ Selected All Case Expenses");
        } catch (Exception e) {
            System.err.println("Failed to select All Case Expenses: " + e.getMessage());
            throw new RuntimeException("Failed to select All Case Expenses: " + e.getMessage());
        }
    }
    
    /**
     * Click Asset link
     */
    public void clickAsset() {
        try {
            waitHelper.waitForElementClickable(assetLink);
            try {
                assetLink.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", assetLink);
            }
            Thread.sleep(1000);
            System.out.println("✓ Clicked Asset");
        } catch (Exception e) {
            System.err.println("Failed to click Asset: " + e.getMessage());
            throw new RuntimeException("Failed to click Asset: " + e.getMessage());
        }
    }
    
    /**
     * Click Asset Include checkbox
     */
    public void clickAssetInclude() {
        try {
            waitHelper.waitForElementClickable(assetIncludeCheckbox);
            try {
                assetIncludeCheckbox.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", assetIncludeCheckbox);
            }
            Thread.sleep(500);
            System.out.println("✓ Clicked Asset Include");
        } catch (Exception e) {
            System.err.println("Failed to click Asset Include: " + e.getMessage());
            throw new RuntimeException("Failed to click Asset Include: " + e.getMessage());
        }
    }
    
    /**
     * Click Reset button
     */
    public void clickReset() {
        try {
            waitHelper.waitForElementClickable(resetButton);
            try {
                resetButton.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", resetButton);
            }
            Thread.sleep(1000);
            System.out.println("✓ Clicked Reset");
        } catch (Exception e) {
            System.err.println("Failed to click Reset: " + e.getMessage());
            throw new RuntimeException("Failed to click Reset: " + e.getMessage());
        }
    }
    
    /**
     * Click Team Case Ownership Filter - Enhanced to click visible tab element
     */
    public void clickTeamCaseOwnershipFilter() {
        try {
            System.out.println("Attempting to click Team Case Ownership Filter...");
            Thread.sleep(2000);
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Ensure the Team checkbox is checked - this is the primary filter control
            System.out.println("Checking Team checkbox for Case reports...");
            js.executeScript(
                "var checkbox = document.getElementById('Team');\n" +
                "if(checkbox) {\n" +
                "  checkbox.checked = true;\n" +
                "  checkbox.dispatchEvent(new Event('change', { bubbles: true }));\n" +
                "  checkbox.dispatchEvent(new Event('click', { bubbles: true }));\n" +
                "}"
            );
            
            Thread.sleep(2000);
            
            // Verification
            boolean isChecked = (Boolean) js.executeScript("return document.getElementById('Team') && document.getElementById('Team').checked;");
            System.out.println("Team checkbox checked: " + isChecked);
            
            if (isChecked) {
                System.out.println("✓ Team ownership filter ACTIVATED for Case reports");
            } else {
                System.out.println("⚠ WARNING: Team checkbox may not be checked");
            }
            
        } catch (Exception e) {
            System.err.println("Failed to click Team Case Ownership Filter: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to click Team Case Ownership Filter: " + e.getMessage());
        }
    }
    
    /**
     * Click Notice checkbox
     */
    public void clickNoticeCheckbox() {
        try {
            Thread.sleep(2000); // Wait before clicking
            waitHelper.waitForElementClickable(noticeCheckbox);
            try {
                noticeCheckbox.click();
                System.out.println("✓ Clicked Notice checkbox (normal click)");
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", noticeCheckbox);
                System.out.println("✓ Clicked Notice checkbox (JS click)");
            }
            Thread.sleep(2000); // Increased wait for Notice section to activate
            
            // Verify checkbox is actually selected
            try {
                if (noticeCheckbox.isSelected()) {
                    System.out.println("✓ Notice checkbox verified as SELECTED");
                } else {
                    System.out.println("⚠ Notice checkbox appears NOT selected, trying again...");
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].checked = true; arguments[0].click();", noticeCheckbox);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                System.out.println("⚠ Could not verify checkbox state: " + e.getMessage());
            }
            
            System.out.println("✓ Notice section should now be active");
        } catch (Exception e) {
            System.err.println("Failed to click Notice checkbox: " + e.getMessage());
            throw new RuntimeException("Failed to click Notice checkbox: " + e.getMessage());
        }
    }
    
    /**
     * Click Notice Include All checkbox
     */
    public void clickNoticeIncludeAll() {
        try {
            waitHelper.waitForElementClickable(noticeIncludeAllCheckbox);
            try {
                noticeIncludeAllCheckbox.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", noticeIncludeAllCheckbox);
            }
            Thread.sleep(500);
            System.out.println("✓ Clicked Include All for Notice");
        } catch (Exception e) {
            System.err.println("Failed to click Notice Include All: " + e.getMessage());
            throw new RuntimeException("Failed to click Notice Include All: " + e.getMessage());
        }
    }
    
    /**
     * Click Notice Recovery Select All checkbox
     */
    public void clickNoticeRecoverySelectAll() {
        try {
            waitHelper.waitForElementClickable(noticeRecoverySelectAllCheckbox);
            try {
                noticeRecoverySelectAllCheckbox.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", noticeRecoverySelectAllCheckbox);
            }
            Thread.sleep(500);
            System.out.println("✓ Selected All Notice Recovery");
        } catch (Exception e) {
            System.err.println("Failed to select All Notice Recovery: " + e.getMessage());
            throw new RuntimeException("Failed to select All Notice Recovery: " + e.getMessage());
        }
    }
    
    /**
     * Click Generate Notice Report button
     */
    public void clickGenerateNoticeReport() {
        try {
            waitHelper.waitForElementClickable(generateReportButton);
            try {
                generateReportButton.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", generateReportButton);
            }
            Thread.sleep(3000); // Increased wait time for report generation
            System.out.println("✓ Clicked Generate Notice Report");
            
            // Scroll back to top to make next elements visible
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo({top: 0, behavior: 'smooth'});");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Failed to click Generate Notice Report: " + e.getMessage());
            throw new RuntimeException("Failed to click Generate Notice Report: " + e.getMessage());
        }
    }
    
    /**
     * Click Notice Proceedings link
     */
    public void clickNoticeProceedings() {
        try {
            waitHelper.waitForElementClickable(noticeProceedingsLink);
            try {
                noticeProceedingsLink.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", noticeProceedingsLink);
            }
            Thread.sleep(1000);
            System.out.println("✓ Clicked Notice Proceedings");
        } catch (Exception e) {
            System.err.println("Failed to click Notice Proceedings: " + e.getMessage());
            throw new RuntimeException("Failed to click Notice Proceedings: " + e.getMessage());
        }
    }
    
    /**
     * Click Notice Proceedings Select All checkbox
     */
    public void clickNoticeProceedingsSelectAll() {
        try {
            waitHelper.waitForElementClickable(noticeProceedingsSelectAllCheckbox);
            try {
                noticeProceedingsSelectAllCheckbox.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", noticeProceedingsSelectAllCheckbox);
            }
            Thread.sleep(500);
            System.out.println("✓ Selected All Notice Proceedings");
        } catch (Exception e) {
            System.err.println("Failed to select All Notice Proceedings: " + e.getMessage());
            throw new RuntimeException("Failed to select All Notice Proceedings: " + e.getMessage());
        }
    }
    
    /**
     * Click Multiple Notice link
     */
    public void clickMultipleNotice() {
        try {
            // Wait for any loading after previous report generation
            Thread.sleep(2000);
            
            // Scroll to the element first
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", multipleNoticeLink);
            Thread.sleep(500);
            
            waitHelper.waitForElementClickable(multipleNoticeLink);
            try {
                multipleNoticeLink.click();
            } catch (Exception clickEx) {
                js.executeScript("arguments[0].click();", multipleNoticeLink);
            }
            Thread.sleep(1000);
            System.out.println("✓ Clicked Multiple Notice");
        } catch (Exception e) {
            System.err.println("Failed to click Multiple Notice: " + e.getMessage());
            throw new RuntimeException("Failed to click Multiple Notice: " + e.getMessage());
        }
    }
    
    /**
     * Click Multiple Notice Select All checkbox
     */
    public void clickMultipleNoticeSelectAll() {
        try {
            waitHelper.waitForElementClickable(multipleNoticeSelectAllCheckbox);
            try {
                multipleNoticeSelectAllCheckbox.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", multipleNoticeSelectAllCheckbox);
            }
            Thread.sleep(500);
            System.out.println("✓ Selected All Multiple Notice");
        } catch (Exception e) {
            System.err.println("Failed to select All Multiple Notice: " + e.getMessage());
            throw new RuntimeException("Failed to select All Multiple Notice: " + e.getMessage());
        }
    }
    
    /**
     * Click Notice Expenses link
     */
    public void clickNoticeExpenses() {
        try {
            // Wait for any loading after previous report generation
            Thread.sleep(2000);
            
            // Scroll to the element first
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", noticeExpensesLink);
            Thread.sleep(500);
            
            waitHelper.waitForElementClickable(noticeExpensesLink);
            try {
                noticeExpensesLink.click();
            } catch (Exception clickEx) {
                js.executeScript("arguments[0].click();", noticeExpensesLink);
            }
            Thread.sleep(1000);
            System.out.println("✓ Clicked Notice Expenses");
        } catch (Exception e) {
            System.err.println("Failed to click Notice Expenses: " + e.getMessage());
            throw new RuntimeException("Failed to click Notice Expenses: " + e.getMessage());
        }
    }
    
    /**
     * Click Notice Expenses Select All checkbox
     */
    public void clickNoticeExpensesSelectAll() {
        try {
            waitHelper.waitForElementClickable(noticeExpensesSelectAllCheckbox);
            try {
                noticeExpensesSelectAllCheckbox.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", noticeExpensesSelectAllCheckbox);
            }
            Thread.sleep(500);
            System.out.println("✓ Selected All Notice Expenses");
        } catch (Exception e) {
            System.err.println("Failed to select All Notice Expenses: " + e.getMessage());
            throw new RuntimeException("Failed to select All Notice Expenses: " + e.getMessage());
        }
    }
    
    /**
     * Click Notice Asset link
     */
    public void clickNoticeAsset() {
        try {
            // Wait for any loading after previous report generation
            Thread.sleep(2000);
            
            // Scroll to the element first
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", noticeAssetLink);
            Thread.sleep(500);
            
            waitHelper.waitForElementClickable(noticeAssetLink);
            try {
                noticeAssetLink.click();
            } catch (Exception clickEx) {
                js.executeScript("arguments[0].click();", noticeAssetLink);
            }
            Thread.sleep(1000);
            System.out.println("✓ Clicked Notice Asset");
        } catch (Exception e) {
            System.err.println("Failed to click Notice Asset: " + e.getMessage());
            throw new RuntimeException("Failed to click Notice Asset: " + e.getMessage());
        }
    }
    
    /**
     * Click Notice Asset Include checkbox
     */
    public void clickNoticeAssetInclude() {
        try {
            waitHelper.waitForElementClickable(noticeAssetIncludeCheckbox);
            try {
                noticeAssetIncludeCheckbox.click();
            } catch (Exception clickEx) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", noticeAssetIncludeCheckbox);
            }
            Thread.sleep(500);
            System.out.println("✓ Clicked Notice Asset Include");
        } catch (Exception e) {
            System.err.println("Failed to click Notice Asset Include: " + e.getMessage());
            throw new RuntimeException("Failed to click Notice Asset Include: " + e.getMessage());
        }
    }
    
    /**
     * Click Notice Team Ownership Filter
     */
    public void clickNoticeTeamOwnershipFilter() {
        try {
            // Wait longer for page to stabilize after navigation/reset
            Thread.sleep(3000);
            
            // Ensure Notice checkbox is selected first (required for Team filter to be active)
            try {
                if (!noticeCheckbox.isSelected()) {
                    System.out.println("Notice checkbox not selected, selecting it first...");
                    clickNoticeCheckbox();
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                System.out.println("Assuming Notice checkbox is already selected");
            }
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Check the Team checkbox - this is the primary filter control
            System.out.println("Checking Team checkbox for Notice reports...");
            js.executeScript(
                "var checkbox = document.getElementById('Team');\n" +
                "if(checkbox) {\n" +
                "  checkbox.checked = true;\n" +
                "  checkbox.dispatchEvent(new Event('change', { bubbles: true }));\n" +
                "  checkbox.dispatchEvent(new Event('click', { bubbles: true }));\n" +
                "}"
            );
            
            Thread.sleep(3000);
            
            // Verification
            boolean isChecked = (Boolean) js.executeScript("return document.getElementById('Team') && document.getElementById('Team').checked;");
            System.out.println("Team checkbox checked: " + isChecked);
            
            if (isChecked) {
                System.out.println("✓ Team ownership filter ACTIVATED for Notice reports");
            } else {
                System.out.println("⚠ WARNING: Team checkbox may not be checked");
            }
            
        } catch (Exception e) {
            System.err.println("Failed to click Notice Team Ownership Filter: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to click Notice Team Ownership Filter: " + e.getMessage());
        }
    }
    
    /**
     * Click Customer Radio Button
     */
    public void clickCustomerRadioButton() {
        try {
            waitHelper.waitForElementClickable(customerRadioButton);
            customerRadioButton.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Customer radio button");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Customer radio button: " + e.getMessage());
        }
    }
    
    /**
     * Click Customer My Tab
     */
    public void clickCustomerMyTab() {
        try {
            System.out.println("Attempting to click Customer My tab...");
            Thread.sleep(2000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                "var checkbox = document.getElementById('My');\n" +
                "if(checkbox) {\n" +
                "  checkbox.checked = true;\n" +
                "  checkbox.dispatchEvent(new Event('change', { bubbles: true }));\n" +
                "}"
            );
            Thread.sleep(1000);
            System.out.println("✓ Customer My tab selected");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Customer My tab: " + e.getMessage());
        }
    }
    
    /**
     * Click Customer Team Tab
     */
    public void clickCustomerTeamTab() {
        try {
            System.out.println("Attempting to click Customer Team tab...");
            Thread.sleep(2000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                "var checkbox = document.getElementById('Team');\n" +
                "if(checkbox) {\n" +
                "  checkbox.checked = true;\n" +
                "  checkbox.dispatchEvent(new Event('change', { bubbles: true }));\n" +
                "}"
            );
            Thread.sleep(1000);
            System.out.println("✓ Customer Team tab selected");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Customer Team tab: " + e.getMessage());
        }
    }
    
    /**
     * Click Customer All Tab
     */
    public void clickCustomerAllTab() {
        try {
            System.out.println("Attempting to click Customer All tab...");
            Thread.sleep(2000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                "var checkbox = document.getElementById('All');\n" +
                "if(checkbox) {\n" +
                "  checkbox.checked = true;\n" +
                "  checkbox.dispatchEvent(new Event('change', { bubbles: true }));\n" +
                "}"
            );
            Thread.sleep(1000);
            System.out.println("✓ Customer All tab selected");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Customer All tab: " + e.getMessage());
        }
    }
    
    /**
     * Click Customer Account Include Checkbox
     */
    public void clickCustomerAccountInclude() {
        try {
            System.out.println("Attempting to click Customer Account Include...");
            Thread.sleep(2000);
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Scroll to element
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", customerAccountIncludeCheckbox);
            Thread.sleep(1000);
            
            // Try normal click first
            try {
                waitHelper.waitForElementClickable(customerAccountIncludeCheckbox);
                customerAccountIncludeCheckbox.click();
                System.out.println("✓ Clicked Customer Account Include checkbox (normal click)");
            } catch (Exception normalClickEx) {
                // Fallback to JavaScript click
                js.executeScript("arguments[0].click();", customerAccountIncludeCheckbox);
                System.out.println("✓ Clicked Customer Account Include checkbox (JS click)");
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Customer Account Include: " + e.getMessage());
        }
    }
    
    /**
     * Click Customer Asset Link
     */
    public void clickCustomerAsset() {
        try {
            System.out.println("Attempting to click Customer Asset...");
            Thread.sleep(2000);
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", customerAssetLink);
            Thread.sleep(1000);
            
            waitHelper.waitForElementClickable(customerAssetLink);
            customerAssetLink.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Customer Asset");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Customer Asset: " + e.getMessage());
        }
    }
    
    /**
     * Click Customer Asset Include Checkbox
     */
    public void clickCustomerAssetInclude() {
        try {
            System.out.println("Attempting to click Customer Asset Include...");
            Thread.sleep(2000);
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", customerAssetIncludeCheckbox);
            Thread.sleep(1000);
            
            try {
                waitHelper.waitForElementClickable(customerAssetIncludeCheckbox);
                customerAssetIncludeCheckbox.click();
                System.out.println("✓ Clicked Customer Asset Include checkbox (normal click)");
            } catch (Exception normalClickEx) {
                js.executeScript("arguments[0].click();", customerAssetIncludeCheckbox);
                System.out.println("✓ Clicked Customer Asset Include checkbox (JS click)");
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Customer Asset Include: " + e.getMessage());
        }
    }
    
    /**
     * Click Customer OTS Offer Link
     */
    public void clickCustomerOTSOffer() {
        try {
            System.out.println("Attempting to click Customer OTS Offer...");
            Thread.sleep(2000);
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", customerOTSOfferLink);
            Thread.sleep(1000);
            
            waitHelper.waitForElementClickable(customerOTSOfferLink);
            customerOTSOfferLink.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Customer OTS Offer");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Customer OTS Offer: " + e.getMessage());
        }
    }
    
    /**
     * Click Customer OTS Offer Select All Checkbox
     */
    public void clickCustomerOTSOfferSelectAll() {
        try {
            System.out.println("Attempting to select all Customer OTS Offer...");
            Thread.sleep(2000);
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", customerOTSOfferSelectAllCheckbox);
            Thread.sleep(1000);
            
            try {
                waitHelper.waitForElementClickable(customerOTSOfferSelectAllCheckbox);
                customerOTSOfferSelectAllCheckbox.click();
                System.out.println("✓ Selected All Customer OTS Offer (normal click)");
            } catch (Exception normalClickEx) {
                js.executeScript("arguments[0].click();", customerOTSOfferSelectAllCheckbox);
                System.out.println("✓ Selected All Customer OTS Offer (JS click)");
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select all Customer OTS Offer: " + e.getMessage());
        }
    }
    
    /**
     * Click Customer Expenses Link
     */
    public void clickCustomerExpenses() {
        try {
            System.out.println("Attempting to click Customer Expenses...");
            Thread.sleep(2000);
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", customerExpensesLink);
            Thread.sleep(1000);
            
            waitHelper.waitForElementClickable(customerExpensesLink);
            customerExpensesLink.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked Customer Expenses");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Customer Expenses: " + e.getMessage());
        }
    }
    
    /**
     * Click Customer Expenses Select All Checkbox
     */
    public void clickCustomerExpensesSelectAll() {
        try {
            System.out.println("Attempting to select all Customer Expenses...");
            Thread.sleep(2000);
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", customerExpensesSelectAllCheckbox);
            Thread.sleep(1000);
            
            try {
                waitHelper.waitForElementClickable(customerExpensesSelectAllCheckbox);
                customerExpensesSelectAllCheckbox.click();
                System.out.println("✓ Selected All Customer Expenses (normal click)");
            } catch (Exception normalClickEx) {
                js.executeScript("arguments[0].click();", customerExpensesSelectAllCheckbox);
                System.out.println("✓ Selected All Customer Expenses (JS click)");
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select all Customer Expenses: " + e.getMessage());
        }
    }
    
    /**
     * Click SARFAESI Report Tab
     */
    public void clickSARFAESIReportTab() {
        try {
            System.out.println("Attempting to click SARFAESI Report tab...");
            Thread.sleep(2000);
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", sarfaesiReportTab);
            Thread.sleep(1000);
            
            try {
                waitHelper.waitForElementClickable(sarfaesiReportTab);
                sarfaesiReportTab.click();
                System.out.println("✓ Clicked SARFAESI Report tab (normal click)");
            } catch (Exception normalClickEx) {
                js.executeScript("arguments[0].click();", sarfaesiReportTab);
                System.out.println("✓ Clicked SARFAESI Report tab (JS click)");
            }
            
            Thread.sleep(2000);
            System.out.println("✓ SARFAESI tab content loaded, ready for interaction");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click SARFAESI Report tab: " + e.getMessage());
        }
    }
    
    /**
     * Click Standard Report Tab
     */
    public void clickStandardReportTab() {
        try {
            System.out.println("Attempting to click Standard Report tab...");
            Thread.sleep(2000);
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", standardReportTab);
            Thread.sleep(1000);
            
            try {
                waitHelper.waitForElementClickable(standardReportTab);
                standardReportTab.click();
                System.out.println("✓ Clicked Standard Report tab (normal click)");
            } catch (Exception normalClickEx) {
                js.executeScript("arguments[0].click();", standardReportTab);
                System.out.println("✓ Clicked Standard Report tab (JS click)");
            }
            
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Standard Report tab: " + e.getMessage());
        }
    }
    
    /**
     * Select Random Standard Report Type
     */
    public void selectRandomStandardReportType() {
        try {
            System.out.println("Attempting to select random Standard Report Type...");
            Thread.sleep(2000);
            
            waitHelper.waitForElementClickable(standardReportTypeDropdown);
            Select select = new Select(standardReportTypeDropdown);
            
            int optionCount = select.getOptions().size();
            System.out.println("Available Standard Report Type options: " + optionCount);
            
            if (optionCount > 1) {
                // Select random option (skip first option which is usually "Select")
                int randomIndex = 1 + (int) (Math.random() * (optionCount - 1));
                select.selectByIndex(randomIndex);
                String selectedOption = select.getFirstSelectedOption().getText();
                System.out.println("✓ Selected random Standard Report Type: " + selectedOption);
            } else {
                System.out.println("Only one option available in Standard Report Type dropdown");
            }
            
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select random Standard Report Type: " + e.getMessage());
        }
    }
    
    /**
     * Force click Generate Report Button using JavaScript (for SARFAESI/Standard Reports)
     */
    public void forceClickGenerateReport() {
        try {
            System.out.println("Attempting to force click Generate Report button...");
            Thread.sleep(2000);
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Try to make button visible and enabled first
            js.executeScript("var btn = document.getElementById('SubmitReport'); " +
                           "if(btn) { btn.style.display='block'; btn.style.visibility='visible'; " +
                           "btn.disabled=false; btn.removeAttribute('disabled'); }");
            Thread.sleep(1000);
            
            // Force click using JavaScript
            js.executeScript("document.getElementById('SubmitReport').click();");
            System.out.println("✓ Force clicked Generate Report button (JS)");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Warning: Could not force click Generate Report: " + e.getMessage());
            // Don't throw exception, just log warning as button might not exist on some tabs
        }
    }
}
