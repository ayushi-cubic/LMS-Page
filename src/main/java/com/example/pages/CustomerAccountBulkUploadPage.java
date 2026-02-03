package com.example.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Customer Account Bulk Upload Page
 */
public class CustomerAccountBulkUploadPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final String downloadFolder;
    private String downloadedFileName;
    private String updatedFilePath;
    
    // Customer Account Bulk Upload Page Locators
    @FindBy(xpath = "//a[contains(text(), 'Upload') or contains(text(), 'upload') or contains(@href, 'Upload') or contains(@href, 'upload')]")
    private WebElement uploadMenuLink;
    
    @FindBy(xpath = "//*[@id='Cmbname']")
    private WebElement uploadTypeDropdown;
    
    @FindBy(xpath = "//*[@id='btnstddownload']")
    private WebElement downloadExcelButton;
    
    @FindBy(xpath = "//*[@id='Document']")
    private WebElement fileUploadInput;
    
    @FindBy(xpath = "//*[@id='btnuploaddoc']")
    private WebElement uploadButton;
    
    @FindBy(xpath = "//div[contains(@class, 'alert-success') or contains(text(), 'success') or contains(text(), 'Success')]")
    private WebElement successMessage;
    
    // Constructor
    public CustomerAccountBulkUploadPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
        
        // Set download folder path
        String userHome = System.getProperty("user.home");
        this.downloadFolder = userHome + "\\Documents\\Automation framework\\LMS-Page\\downloads";
        
        // Create download folder if it doesn't exist
        File folder = new File(downloadFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
    
    /**
     * Navigate to Upload page
     */
    public void navigateToUploadPage() {
        try {
            System.out.println("Navigating to Upload page for Customer Accounts...");
            System.out.println("Current URL: " + driver.getCurrentUrl());
            
            // Wait for page to be fully loaded after login
            Thread.sleep(5000);
            
            // Try multiple methods to find and click the Upload link
            WebElement uploadLink = null;
            
            // Method 1: Try the provided xpath
            try {
                uploadLink = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[8]/a"));
                System.out.println("Found Upload link using provided xpath");
            } catch (Exception e1) {
                System.out.println("Upload link not found with provided xpath, trying alternative methods...");
                
                // Method 2: Try by text content
                try {
                    uploadLink = driver.findElement(By.xpath("//a[contains(text(), 'Upload') or contains(text(), 'upload')]"));
                    System.out.println("Found Upload link by text content");
                } catch (Exception e2) {
                    System.out.println("Upload link not found by text, trying by href...");
                    
                    // Method 3: Try by href attribute
                    try {
                        uploadLink = driver.findElement(By.xpath("//a[contains(@href, 'Upload') or contains(@href, 'upload')]"));
                        System.out.println("Found Upload link by href attribute");
                    } catch (Exception e3) {
                        System.out.println("Upload link not found by href, trying broader search...");
                        
                        // Method 4: Try any link that might be the upload menu
                        try {
                            uploadLink = driver.findElement(By.xpath("//ul//li//a[contains(translate(text(), 'UPLOAD', 'upload'), 'upload')]"));
                            System.out.println("Found Upload link using broader search");
                        } catch (Exception e4) {
                            throw new RuntimeException("Could not find Upload menu link using any method");
                        }
                    }
                }
            }
            
            // Wait for element to be clickable
            waitHelper.waitForElementClickable(uploadLink);
            System.out.println("Upload menu link found, clicking...");
            System.out.println("Link text: " + uploadLink.getText());
            
            // Try JavaScript click if regular click fails
            try {
                uploadLink.click();
            } catch (Exception clickException) {
                System.out.println("Regular click failed, using JavaScript click...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", uploadLink);
            }
            
            // Wait for page to load
            Thread.sleep(3000);
            System.out.println("Successfully navigated to Upload page");
            System.out.println("New URL: " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.err.println("Error navigating to Upload page: " + e.getMessage());
            System.err.println("Current URL: " + driver.getCurrentUrl());
            
            // Print page source for debugging
            System.out.println("\n=== Page Source Debug ===");
            try {
                String pageSource = driver.getPageSource();
                System.out.println("Page contains 'Upload': " + pageSource.contains("Upload"));
                System.out.println("Page contains 'upload': " + pageSource.contains("upload"));
                
                // Try to find all links on the page
                System.out.println("\nAll links on the page:");
                java.util.List<WebElement> allLinks = driver.findElements(By.tagName("a"));
                for (int i = 0; i < Math.min(allLinks.size(), 20); i++) {
                    WebElement link = allLinks.get(i);
                    System.out.println("  Link " + i + ": " + link.getText() + " [href=" + link.getAttribute("href") + "]");
                }
            } catch (Exception debugEx) {
                System.err.println("Debug info collection failed: " + debugEx.getMessage());
            }
            System.out.println("=== End Debug ===\n");
            
            e.printStackTrace();
            throw new RuntimeException("Failed to navigate to Upload page", e);
        }
    }
    
    /**
     * Select Upload Type - Customers (value="5")
     */
    public void selectUploadTypeCustomers() {
        try {
            System.out.println("Selecting Upload Type: Customers...");
            waitHelper.waitForElementVisible(uploadTypeDropdown);
            Select uploadTypeSelect = new Select(uploadTypeDropdown);
            
            // Print all available options
            System.out.println("Available Upload Type options:");
            for (WebElement option : uploadTypeSelect.getOptions()) {
                System.out.println("  - " + option.getText() + " (value=" + option.getAttribute("value") + ")");
            }
            
            // Select by value "5" which corresponds to Customers
            uploadTypeSelect.selectByValue("5");
            System.out.println("Selected Upload Type: Customers (value=5)");
            
            // Verify selection
            String selectedText = uploadTypeSelect.getFirstSelectedOption().getText();
            System.out.println("Currently selected: " + selectedText);
            
            Thread.sleep(3000); // Wait for page to update
        } catch (Exception e) {
            System.err.println("Error selecting Upload Type: " + e.getMessage());
            throw new RuntimeException("Failed to select Upload Type - Customers", e);
        }
    }
    
    /**
     * Click Download Excel Format button
     */
    public void clickDownloadExcelFormat() {
        try {
            System.out.println("Clicking Download Excel Format button...");
            waitHelper.waitForElementClickable(downloadExcelButton);
            
            // Try regular click first
            try {
                downloadExcelButton.click();
                System.out.println("Download button clicked using regular click");
            } catch (Exception clickException) {
                System.out.println("Regular click failed, using JavaScript click...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", downloadExcelButton);
                System.out.println("Download button clicked using JavaScript");
            }
            
            System.out.println("Download button clicked successfully");
        } catch (Exception e) {
            System.err.println("Error clicking download button: " + e.getMessage());
            throw new RuntimeException("Failed to click Download Excel Format button", e);
        }
    }
    
    /**
     * Wait for Excel file to be downloaded
     * @param timeoutSeconds maximum time to wait
     * @return the name of the downloaded file
     */
    public String waitForFileDownload(int timeoutSeconds) {
        try {
            System.out.println("Waiting for file to download...");
            File dir = new File(downloadFolder);
            long startTime = System.currentTimeMillis();
            long timeout = timeoutSeconds * 1000;
            
            while ((System.currentTimeMillis() - startTime) < timeout) {
                File[] files = dir.listFiles((d, name) -> 
                    name.toLowerCase().endsWith(".xlsx") && !name.endsWith(".crdownload"));
                
                if (files != null && files.length > 0) {
                    // Get the most recently modified file
                    File latestFile = files[0];
                    for (File file : files) {
                        if (file.lastModified() > latestFile.lastModified()) {
                            latestFile = file;
                        }
                    }
                    downloadedFileName = latestFile.getName();
                    System.out.println("File downloaded successfully: " + downloadedFileName);
                    return downloadedFileName;
                }
                
                Thread.sleep(1000);
            }
            
            throw new RuntimeException("File download timeout after " + timeoutSeconds + " seconds");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Download wait interrupted", e);
        }
    }
    
    /**
     * Fill Excel file with Customer Account test data
     * @return path to the updated Excel file
     */
    public String fillExcelWithCustomerAccountData() {
        try {
            String filePath = downloadFolder + File.separator + downloadedFileName;
            System.out.println("Opening Excel file: " + filePath);
            
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            
            // Get header row (row 0)
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Header row not found in Excel file");
            }
            
            // Print all column headers for debugging
            System.out.println("Excel columns found:");
            Map<String, Integer> columnMap = new HashMap<>();
            for (Cell cell : headerRow) {
                String columnName = cell.getStringCellValue().trim();
                columnMap.put(columnName.toLowerCase(), cell.getColumnIndex());
                System.out.println("  Column [" + cell.getColumnIndex() + "]: " + columnName);
            }
            
            // Create data row at index 1 (row 2 in Excel - row 1 is header)
            Row dataRow = sheet.getRow(1);
            if (dataRow == null) {
                dataRow = sheet.createRow(1);
            }
            
            // Clear all existing cells in the data row to ensure empty columns stay empty
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                Cell cell = dataRow.getCell(i);
                if (cell != null) {
                    dataRow.removeCell(cell);
                }
            }
            
            // Generate test data for Customer Account
            Map<String, String> testData = generateCustomerAccountTestData();
            
            System.out.println("Filling Excel with Customer Account data (Row 2)...");
            
            // Fill only specified columns, leave others empty
            for (Map.Entry<String, String> entry : testData.entrySet()) {
                String columnName = entry.getKey();
                String value = entry.getValue();
                
                // Try to find column by case-insensitive match
                Integer columnIndex = columnMap.get(columnName.toLowerCase());
                
                if (columnIndex != null) {
                    // Only fill if value is not empty
                    if (value != null && !value.isEmpty()) {
                        Cell cell = dataRow.createCell(columnIndex);
                        cell.setCellValue(value);
                        System.out.println("  [Col " + columnIndex + "] " + columnName + ": " + value);
                    } else {
                        System.out.println("  [Col " + columnIndex + "] " + columnName + ": (empty)");
                    }
                } else {
                    System.out.println("  Column not found in template: " + columnName);
                }
            }
            
            fis.close();
            
            // Save updated file
            updatedFilePath = downloadFolder + File.separator + "CustomerAccountBulkUpload_" + System.currentTimeMillis() + ".xlsx";
            FileOutputStream fos = new FileOutputStream(updatedFilePath);
            workbook.write(fos);
            fos.close();
            workbook.close();
            
            System.out.println("Excel file updated and saved: " + updatedFilePath);
            return updatedFilePath;
            
        } catch (Exception e) {
            System.err.println("Error filling Excel file: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to fill Excel file with test data", e);
        }
    }
    
    /**
     * Generate test data for Customer Account
     * @return map of column names to values (lowercase keys for case-insensitive matching)
     */
    private Map<String, String> generateCustomerAccountTestData() {
        Map<String, String> data = new HashMap<>();
        
        // Fill all the data specified by the user for Customer Account bulk upload
        // Using lowercase keys for case-insensitive matching with Excel headers
        
        // Generate random alphanumeric Account Number
        String accountNumber = "ACC" + System.currentTimeMillis();
        
        data.put("account number", accountNumber);
        data.put("customer number", "202040");
        data.put("facility (account type)", "Saving account");
        data.put("principal amount", "100000");
        data.put("intrate (rate of interest)", "3");
        data.put("outstanding balance", "10000");
        data.put("business unit", "CTQA");
        data.put("zone", "West");
        data.put("state", "Maharashtra");
        data.put("location", "Akola");
        data.put("account status", "Live");
        data.put("loan agreement date", "03.08.2025");
        data.put("loan disbursal date", "05.08.2025");
        data.put("maturity date", "06.08.2025");
        
        // All other columns not mentioned will remain empty as per requirement
        
        return data;
    }
    
    /**
     * Upload the Excel file
     * @param filePath path to the Excel file
     */
    public void uploadExcelFile(String filePath) {
        try {
            System.out.println("Uploading Excel file: " + filePath);
            
            // Find file upload input using the provided xpath
            WebElement fileInput = null;
            
            // Try by ID
            try {
                fileInput = driver.findElement(By.id("Document"));
                System.out.println("Found file input by ID");
            } catch (Exception e1) {
                System.out.println("File input not found by ID, trying by xpath...");
                
                // Try by xpath
                try {
                    fileInput = driver.findElement(By.xpath("//*[@id='Document']"));
                    System.out.println("Found file input by xpath");
                } catch (Exception e2) {
                    System.out.println("File input not found by xpath, trying by type...");
                    
                    // Try by type=file
                    try {
                        fileInput = driver.findElement(By.cssSelector("input[type='file']"));
                        System.out.println("Found file input by type");
                    } catch (Exception e3) {
                        throw new RuntimeException("Could not find file upload input element");
                    }
                }
            }
            
            // Send the file path to the file input element
            fileInput.sendKeys(filePath);
            Thread.sleep(1000); // Wait for file to be attached
            
            System.out.println("File attached successfully");
        } catch (Exception e) {
            System.err.println("Error uploading file: " + e.getMessage());
            throw new RuntimeException("Failed to upload Excel file", e);
        }
    }
    
    /**
     * Click Upload button using the provided xpath
     */
    public void clickUploadButton() {
        try {
            System.out.println("Clicking Upload button...");
            
            // Find upload button using the provided xpath
            WebElement btnUpload = null;
            
            // Try by ID
            try {
                btnUpload = driver.findElement(By.id("btnuploaddoc"));
                System.out.println("Found upload button by ID");
            } catch (Exception e1) {
                System.out.println("Upload button not found by ID, trying by xpath...");
                
                // Try by xpath
                try {
                    btnUpload = driver.findElement(By.xpath("//*[@id='btnuploaddoc']"));
                    System.out.println("Found upload button by xpath");
                } catch (Exception e2) {
                    System.out.println("Upload button not found by xpath, trying by text...");
                    
                    // Try by button text
                    try {
                        btnUpload = driver.findElement(By.xpath("//button[contains(text(), 'Upload') or contains(text(), 'upload') or contains(@value, 'Upload')]"));
                        System.out.println("Found upload button by text");
                    } catch (Exception e3) {
                        throw new RuntimeException("Could not find upload button element");
                    }
                }
            }
            
            // Use JavaScript click as regular click may fail if element is overlapped
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnUpload);
            Thread.sleep(500);
            
            // Try regular click first
            try {
                btnUpload.click();
                System.out.println("Upload button clicked using regular click");
            } catch (Exception clickException) {
                System.out.println("Regular click failed, using JavaScript click...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnUpload);
                System.out.println("Upload button clicked using JavaScript");
            }
            
            Thread.sleep(3000); // Wait for upload to process
            System.out.println("Upload button clicked successfully");
        } catch (Exception e) {
            System.err.println("Error clicking upload button: " + e.getMessage());
            throw new RuntimeException("Failed to click Upload button", e);
        }
    }
    
    /**
     * Validate upload success
     * @return true if success message is displayed
     */
    public boolean validateUploadSuccess() {
        try {
            System.out.println("Validating upload success...");
            
            // Wait for success message
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Try multiple ways to detect success
            boolean isSuccess = false;
            
            // Method 1: Check for success message element
            try {
                wait.until(ExpectedConditions.visibilityOf(successMessage));
                isSuccess = successMessage.isDisplayed();
                System.out.println("Success message found: " + successMessage.getText());
            } catch (Exception e) {
                System.out.println("Success message element not found, trying alternative methods...");
            }
            
            // Method 2: Check for any success indicator in the page
            if (!isSuccess) {
                try {
                    WebElement anySuccessElement = driver.findElement(
                        By.xpath("//*[contains(text(), 'success') or contains(text(), 'Success') or contains(text(), 'uploaded')]"));
                    isSuccess = anySuccessElement.isDisplayed();
                    System.out.println("Success indicator found: " + anySuccessElement.getText());
                } catch (Exception e) {
                    System.out.println("No success indicator found in page text");
                }
            }
            
            // Method 3: Check page source for success keywords
            if (!isSuccess) {
                String pageSource = driver.getPageSource().toLowerCase();
                isSuccess = pageSource.contains("success") || 
                           pageSource.contains("uploaded successfully") ||
                           pageSource.contains("upload successful");
                if (isSuccess) {
                    System.out.println("Success keyword found in page source");
                }
            }
            
            if (isSuccess) {
                System.out.println("Upload validation: SUCCESS");
            } else {
                System.out.println("Upload validation: Could not confirm success");
            }
            
            return isSuccess;
            
        } catch (Exception e) {
            System.err.println("Error validating upload: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get the path to the updated Excel file
     * @return file path
     */
    public String getUpdatedFilePath() {
        return updatedFilePath;
    }
    
    /**
     * Perform complete customer account bulk upload workflow
     */
    public void performCustomerAccountBulkUploadWorkflow() {
        navigateToUploadPage();
        selectUploadTypeCustomers();
        clickDownloadExcelFormat();
        waitForFileDownload(30);
        String filePath = fillExcelWithCustomerAccountData();
        uploadExcelFile(filePath);
        clickUploadButton();
        boolean success = validateUploadSuccess();
        
        if (!success) {
            System.out.println("Warning: Upload success could not be confirmed");
        }
    }
}
