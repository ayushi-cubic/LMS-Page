package com.example.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.example.utils.WaitHelper;

/**
 * Page Object for Notice Approval workflow
 * Handles Ajinkya's approval process for Advocate Allocation
 */
public class NoticeApprovalPage {
    
    private WebDriver driver;
    private WaitHelper waitHelper;
    
    // Locators
    private By actionableItemsMenu = By.xpath("//*[@id='navLinks']/a[3]");
    private By approvalTab = By.xpath("//*[@id='tab-approval']");
    private By advocateAllocationApprovalTab = By.xpath("//*[@id='ApprovalTypeTabs']/li[2]/a");
    private By advanceFilterLink = By.xpath("//*[@id='v-pills-home']/div[1]/div[2]/a");
    private By moduleNameDropdown = By.xpath("//*[@id='ModuleName']");
    private By applyButton = By.xpath("//*[@id='BtnAFApply1']");
    private By lastPageButton = By.xpath("//*[@id='paginationControls']/ul/li[7]/a");
    private By approvalCheckbox = By.xpath("//*[@id='chkApprovalReq']");
    private By approveButton = By.xpath("//*[@id='btnapproveadvocate']");
    
    public NoticeApprovalPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }
    
    /**
     * Navigate to Advocate Allocation Approval section
     */
    public void navigateToAdvocateAllocationApproval() {
        try {
            System.out.println("\n========== NAVIGATING TO ADVOCATE ALLOCATION APPROVAL ==========");
            
            // Click Actionable Items
            System.out.println("Clicking Actionable Items menu...");
            waitHelper.hardWait(2000);
            WebElement actionableItems = waitHelper.waitForElementClickable(actionableItemsMenu);
            actionableItems.click();
            waitHelper.hardWait(2000);
            System.out.println("✓ Actionable Items clicked");
            
            // Click Approval tab
            System.out.println("Clicking Approval tab...");
            waitHelper.hardWait(1000);
            WebElement approval = waitHelper.waitForElementClickable(approvalTab);
            approval.click();
            waitHelper.hardWait(2000);
            System.out.println("✓ Approval tab clicked");
            
            // Click Advocate Allocation Approval
            System.out.println("Clicking Advocate Allocation Approval tab...");
            waitHelper.hardWait(1000);
            WebElement advocateAllocation = waitHelper.waitForElementClickable(advocateAllocationApprovalTab);
            advocateAllocation.click();
            waitHelper.hardWait(2000);
            System.out.println("✓ Advocate Allocation Approval tab clicked");
            
            System.out.println("========== NAVIGATED TO ADVOCATE ALLOCATION APPROVAL ==========\n");
            
        } catch (Exception e) {
            System.out.println("❌ Error during navigation: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to navigate to Advocate Allocation Approval", e);
        }
    }
    
    /**
     * Apply notice filter and navigate to last page
     */
    public void applyNoticeFilter() {
        try {
            System.out.println("\n========== APPLYING NOTICE FILTER ==========");
            
            // Click Advance Filter
            System.out.println("Clicking Advance Filter...");
            waitHelper.hardWait(1000);
            WebElement advanceFilter = waitHelper.waitForElementClickable(advanceFilterLink);
            advanceFilter.click();
            waitHelper.hardWait(2000);
            System.out.println("✓ Advance Filter opened");
            
            // Select Notice from Module Name dropdown
            System.out.println("Selecting Notice from dropdown...");
            waitHelper.hardWait(1000);
            WebElement moduleDropdown = waitHelper.waitForElementVisible(moduleNameDropdown);
            Select select = new Select(moduleDropdown);
            select.selectByVisibleText("Notice");
            waitHelper.hardWait(1000);
            System.out.println("✓ Notice selected from dropdown");
            
            // Click Apply button
            System.out.println("Clicking Apply button...");
            waitHelper.hardWait(1000);
            WebElement apply = waitHelper.waitForElementVisible(applyButton);
            
            // Scroll to button
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", apply);
            waitHelper.hardWait(1000);
            
            // Click using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", apply);
            waitHelper.hardWait(4000);
            System.out.println("✓ Filter applied");
            
            // Click Last page
            System.out.println("Clicking Last page...");
            waitHelper.hardWait(2000);
            
            try {
                WebElement lastPage = waitHelper.waitForElementVisible(lastPageButton);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", lastPage);
                waitHelper.hardWait(1000);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", lastPage);
                waitHelper.hardWait(3000);
                System.out.println("✓ Navigated to last page");
            } catch (Exception e) {
                System.out.println("⚠ Last page button not found or already on last page: " + e.getMessage());
            }
            
            System.out.println("========== NOTICE FILTER APPLIED ==========\n");
            
        } catch (Exception e) {
            System.out.println("❌ Error applying filter: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to apply notice filter", e);
        }
    }
    
    /**
     * Approve the notice by checking checkbox and clicking approve
     * Searches for specific Case Unique ID if provided
     */
    public void approveNotice() {
        approveNotice(null);
    }
    
    /**
     * Approve the notice by checking checkbox and clicking approve
     * @param caseUniqueId The Case Unique ID to search for and approve
     */
    public void approveNotice(String caseUniqueId) {
        try {
            System.out.println("\n========== APPROVING NOTICE ==========");
            
            // Wait for table to load
            waitHelper.hardWait(3000);
            
            WebElement checkboxToClick = null;
            int matchingRowIndex = -1;
            
            if (caseUniqueId != null && !caseUniqueId.isEmpty()) {
                System.out.println("Searching for Case Unique ID: [" + caseUniqueId + "]");
                
                // Find all rows in the approval table
                try {
                    // Try multiple possible table structures
                    List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr[td]"));
                    System.out.println("Found " + rows.size() + " rows in approval table");
                    
                    // Search for the Case Unique ID in the table
                    for (int i = 0; i < rows.size(); i++) {
                        WebElement row = rows.get(i);
                        String rowText = row.getText();
                        System.out.println("Row " + (i + 1) + " text: " + rowText.substring(0, Math.min(100, rowText.length())));
                        
                        // Check if this row contains the exact Case Unique ID
                        if (rowText.contains(caseUniqueId)) {
                            System.out.println("✓✓✓ FOUND MATCHING CASE UNIQUE ID IN ROW " + (i + 1) + " ✓✓✓");
                            matchingRowIndex = i;
                            
                            // Find checkbox in this specific row - try multiple patterns
                            try {
                                // Try standard checkbox
                                List<WebElement> rowCheckboxes = row.findElements(By.xpath(".//input[@type='checkbox']"));
                                if (!rowCheckboxes.isEmpty()) {
                                    checkboxToClick = rowCheckboxes.get(0);
                                    System.out.println("✓ Found checkbox using standard method");
                                } else {
                                    // Try by id pattern
                                    rowCheckboxes = row.findElements(By.xpath(".//input[contains(@id, 'chk')]"));
                                    if (!rowCheckboxes.isEmpty()) {
                                        checkboxToClick = rowCheckboxes.get(0);
                                        System.out.println("✓ Found checkbox using id pattern");
                                    }
                                }
                                
                                if (checkboxToClick != null) {
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("⚠ Could not find checkbox in matching row: " + e.getMessage());
                            }
                        }
                    }
                    
                    if (checkboxToClick == null && matchingRowIndex == -1) {
                        System.out.println("⚠⚠⚠ WARNING: Could not find Case Unique ID [" + caseUniqueId + "] in ANY row ⚠⚠⚠");
                        System.out.println("Will use first checkbox as fallback");
                    }
                } catch (Exception e) {
                    System.out.println("⚠ Error searching for Case Unique ID: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.out.println("⚠ No Case Unique ID provided");
            }
            
            // If specific checkbox not found, use the default first checkbox
            if (checkboxToClick == null) {
                System.out.println("Using first checkbox in approval table...");
                checkboxToClick = waitHelper.waitForElementVisible(approvalCheckbox);
            }
            
            // Scroll to checkbox
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkboxToClick);
            waitHelper.hardWait(1000);
            
            // Click using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkboxToClick);
            waitHelper.hardWait(1500);
            System.out.println("✓ Checkbox selected");
            
            // Click Approve button
            System.out.println("Clicking Approve button...");
            waitHelper.hardWait(1000);
            WebElement approve = waitHelper.waitForElementVisible(approveButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", approve);
            waitHelper.hardWait(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", approve);
            waitHelper.hardWait(2000);
            System.out.println("✓ Approve button clicked");
            
            // Handle alert
            try {
                System.out.println("Handling approval confirmation alert...");
                waitHelper.hardWait(1000);
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                System.out.println("Alert message: " + alertText);
                alert.accept();
                waitHelper.hardWait(3000);
                System.out.println("✓ Alert accepted");
            } catch (Exception e) {
                System.out.println("⚠ No alert found or already handled: " + e.getMessage());
            }
            
            System.out.println("========== NOTICE APPROVED SUCCESSFULLY ==========\n");
            
        } catch (Exception e) {
            System.out.println("❌ Error approving notice: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to approve notice", e);
        }
    }
}
