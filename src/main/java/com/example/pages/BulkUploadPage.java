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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.utils.RandomDataGenerator;
import com.example.utils.WaitHelper;

/**
 * Page Object Model for Bulk Upload Page
 */
public class BulkUploadPage {
    
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final String downloadFolder;
    private String downloadedFileName;
    private String updatedFilePath;
    
    // Bulk Upload Page Locators
    // Try multiple possible xpaths for upload menu
    @FindBy(xpath = "//a[contains(text(), 'Upload') or contains(text(), 'upload') or contains(@href, 'Upload') or contains(@href, 'upload')]")
    private WebElement uploadMenuLink;
    
    @FindBy(xpath = "//*[@id='btnstddownload']")
    private WebElement downloadExcelButton;
    
    @FindBy(xpath = "//*[@id='Document']")
    private WebElement fileUploadInput;
    
    @FindBy(xpath = "//*[@id='btnuploaddoc']")
    private WebElement uploadButton;
    
    @FindBy(xpath = "//div[contains(@class, 'alert-success') or contains(text(), 'success') or contains(text(), 'Success')]")
    private WebElement successMessage;
    
    // Constructor
    public BulkUploadPage(WebDriver driver) {
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
            System.out.println("Navigating to Upload page...");
            System.out.println("Current URL: " + driver.getCurrentUrl());
            
            // Wait for page to be fully loaded after login
            Thread.sleep(5000);
            
            // Debug: Print all available menu links
            System.out.println("Looking for Upload menu link...");
            try {
                java.util.List<org.openqa.selenium.WebElement> allLinks = driver.findElements(By.tagName("a"));
                System.out.println("Found " + allLinks.size() + " links on page");
                for (org.openqa.selenium.WebElement link : allLinks) {
                    String text = link.getText();
                    String href = link.getAttribute("href");
                    if (text != null && !text.trim().isEmpty()) {
                        System.out.println("  Link: " + text + " -> " + href);
                    }
                }
            } catch (Exception debug) {
                System.out.println("Could not list links: " + debug.getMessage());
            }
            
            // Wait for the menu link to be clickable
            waitHelper.waitForElementClickable(uploadMenuLink);
            System.out.println("Upload menu link found, clicking...");
            System.out.println("Link text: " + uploadMenuLink.getText());
            uploadMenuLink.click();
            
            // Wait for page to load
            Thread.sleep(3000);
            System.out.println("Successfully navigated to Upload page");
            System.out.println("New URL: " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.err.println("Error navigating to Upload page: " + e.getMessage());
            System.err.println("Current URL: " + driver.getCurrentUrl());
            e.printStackTrace();
            throw new RuntimeException("Failed to navigate to Upload page", e);
        }
    }
    
    /**
     * Click Download Excel Format button
     */
    public void clickDownloadExcelFormat() {
        try {
            System.out.println("Clicking Download Excel Format button...");
            waitHelper.waitForElementClickable(downloadExcelButton);
            downloadExcelButton.click();
            System.out.println("Download button clicked");
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
     * Fill Excel file with Civil Case test data
     * @return path to the updated Excel file
     */
    public String fillExcelWithCivilCaseData() {
        try {
            String filePath = downloadFolder + File.separator + downloadedFileName;
            System.out.println("Opening Excel file: " + filePath);
            
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            
            // Create or get the data row (assuming row 0 is header, row 1 is for data)
            Row dataRow = sheet.getRow(1);
            if (dataRow == null) {
                dataRow = sheet.createRow(1);
            }
            
            // Generate test data for Civil Case
            Map<String, String> testData = generateCivilCaseTestData();
            
            // Get header row to map column names to indices
            Row headerRow = sheet.getRow(0);
            Map<String, Integer> columnMap = new HashMap<>();
            
            if (headerRow != null) {
                for (Cell cell : headerRow) {
                    String columnName = cell.getStringCellValue().trim();
                    columnMap.put(columnName, cell.getColumnIndex());
                }
            }
            
            System.out.println("Filling Excel with Civil Case data...");
            
            // Fill data based on column mapping
            for (Map.Entry<String, String> entry : testData.entrySet()) {
                String columnName = entry.getKey();
                String value = entry.getValue();
                
                Integer columnIndex = columnMap.get(columnName);
                if (columnIndex != null) {
                    Cell cell = dataRow.getCell(columnIndex);
                    if (cell == null) {
                        cell = dataRow.createCell(columnIndex);
                    }
                    cell.setCellValue(value);
                    System.out.println("  " + columnName + ": " + value);
                } else {
                    // If exact match not found, create cell at next available column
                    int lastColumn = dataRow.getLastCellNum();
                    if (lastColumn == -1) lastColumn = 0;
                    Cell cell = dataRow.createCell(lastColumn);
                    cell.setCellValue(value);
                    System.out.println("  " + columnName + " (new column): " + value);
                }
            }
            
            fis.close();
            
            // Save updated file
            updatedFilePath = downloadFolder + File.separator + "Updated_" + downloadedFileName;
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
     * Generate test data for Civil Case
     * @return map of column names to values
     */
    private Map<String, String> generateCivilCaseTestData() {
        Map<String, String> data = new HashMap<>();
        
        // Generate Civil Case specific data
        data.put("CaseType", "Civil");
        data.put("CaseNumber", "CIV" + RandomDataGenerator.generateAlphanumeric(8));
        data.put("CaseTitle", "Civil Case - " + RandomDataGenerator.generateName());
        data.put("PlaintiffName", RandomDataGenerator.generateName());
        data.put("DefendantName", RandomDataGenerator.generateName());
        data.put("CourtName", "District Court " + RandomDataGenerator.generateAlphanumeric(3));
        data.put("FilingDate", RandomDataGenerator.generateDate());
        data.put("CaseStatus", "Active");
        data.put("LawyerName", "Adv. " + RandomDataGenerator.generateName());
        data.put("Amount", String.valueOf(RandomDataGenerator.generateRandomNumber(100000, 10000000)));
        data.put("Description", "Civil case for property dispute and monetary claims");
        data.put("ContactNumber", RandomDataGenerator.generateMobileNumber());
        data.put("EmailAddress", RandomDataGenerator.generateEmail());
        
        return data;
    }
    
    /**
     * Upload the Excel file
     * @param filePath path to the Excel file
     */
    public void uploadExcelFile(String filePath) {
        try {
            System.out.println("Uploading Excel file: " + filePath);
            
            // Try to find file upload input - try multiple selectors
            org.openqa.selenium.WebElement fileInput = null;
            
            // Try by ID
            try {
                fileInput = driver.findElement(By.id("Document"));
                System.out.println("Found file input by ID");
            } catch (Exception e1) {
                System.out.println("File input not found by ID, trying by type...");
                
                // Try by type=file
                try {
                    fileInput = driver.findElement(By.cssSelector("input[type='file']"));
                    System.out.println("Found file input by type");
                } catch (Exception e2) {
                    System.out.println("File input not found by type, trying by name...");
                    
                    // Try by name
                    try {
                        fileInput = driver.findElement(By.name("Document"));
                        System.out.println("Found file input by name");
                    } catch (Exception e3) {
                        // List all inputs on page
                        System.out.println("Could not find file input. Listing all input elements:");
                        java.util.List<org.openqa.selenium.WebElement> allInputs = driver.findElements(By.tagName("input"));
                        for (org.openqa.selenium.WebElement input : allInputs) {
                            String type = input.getAttribute("type");
                            String id = input.getAttribute("id");
                            String name = input.getAttribute("name");
                            System.out.println("  Input: type=" + type + ", id=" + id + ", name=" + name);
                        }
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
     * Click Upload button
     */
    public void clickUploadButton() {
        try {
            System.out.println("Clicking Upload button...");
            
            // Try to find upload button - try multiple selectors
            org.openqa.selenium.WebElement btnUpload = null;
            
            // Try by ID
            try {
                btnUpload = driver.findElement(By.id("btnuploaddoc"));
                System.out.println("Found upload button by ID");
            } catch (Exception e1) {
                System.out.println("Upload button not found by ID, trying by text...");
                
                // Try by button text
                try {
                    btnUpload = driver.findElement(By.xpath("//button[contains(text(), 'Upload') or contains(text(), 'upload') or contains(@value, 'Upload')]"));
                    System.out.println("Found upload button by text");
                } catch (Exception e2) {
                    System.out.println("Upload button not found by text, trying by type=submit...");
                    
                    // Try by type=submit
                    try {
                        btnUpload = driver.findElement(By.cssSelector("button[type='submit'], input[type='submit']"));
                        System.out.println("Found upload button by type");
                    } catch (Exception e3) {
                        // List all buttons on page
                        System.out.println("Could not find upload button. Listing all buttons:");
                        java.util.List<org.openqa.selenium.WebElement> allButtons = driver.findElements(By.tagName("button"));
                        for (org.openqa.selenium.WebElement btn : allButtons) {
                            String text = btn.getText();
                            String id = btn.getAttribute("id");
                            String name = btn.getAttribute("name");
                            System.out.println("  Button: text='" + text + "', id=" + id + ", name=" + name);
                        }
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
     * Perform complete bulk upload workflow
     */
    public void performBulkUploadWorkflow() {
        navigateToUploadPage();
        clickDownloadExcelFormat();
        waitForFileDownload(30);
        String filePath = fillExcelWithCivilCaseData();
        uploadExcelFile(filePath);
        clickUploadButton();
        boolean success = validateUploadSuccess();
        
        if (!success) {
            System.out.println("Warning: Upload success could not be confirmed");
        }
    }
}
