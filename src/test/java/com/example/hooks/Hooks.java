package com.example.hooks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.example.utils.ExcelTestCaseGenerator;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Cucumber Hooks for setup and teardown
 */
public class Hooks {
    
    private static WebDriver driver;
    private static boolean isInitialized = false;
    private static int scenarioCount = 0;
    private static String screenshotFolder;
    private static int stepCounter = 0;
    
    /**
     * Setup method - runs before each scenario
     * But only initializes browser and generates Excel once
     */
    @Before(order = 0)
    public void setUp(Scenario scenario) {
        scenarioCount++;
        
        // Only initialize once for all scenarios
        if (!isInitialized) {
            // Create screenshots folder
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            screenshotFolder = "target/screenshots_" + timestamp;
            File screenshotDir = new File(screenshotFolder);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
                System.out.println("\nScreenshots folder created: " + screenshotFolder);
            }
            
            System.out.println("\n==============================================");
            System.out.println("STARTING TEST EXECUTION");
            System.out.println("==============================================");
            
            // Generate Excel test case document based on scenario tags
            try {
                System.out.println("\nGenerating Excel test case document...");
                ExcelTestCaseGenerator generator;
                
                // Check if this is Account, Asset, Customer, Contact, Notice or NoticeCriminal module
                if (scenario.getSourceTagNames().stream().anyMatch(tag -> tag.contains("AccountManagement"))) {
                    generator = new ExcelTestCaseGenerator("Account Test Cases");
                    generator.createHeader();
                    generator.addAccountLoginTestCase(true);
                    generator.addAccountNavigationTestCase(true);
                    generator.addAccountCreationTestCase(true, "ACC" + System.currentTimeMillis(), "CUST123456");
                    generator.addAccountSearchTestCase(true, "ACC" + System.currentTimeMillis());
                    generator.addAccountEndToEndTestCase(true, "ACC" + System.currentTimeMillis(), "CUST123456");
                    String filePath = "Account_Test_Cases_" + 
                        java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".xlsx";
                    generator.saveToFile(filePath);
                    System.out.println("Excel file generated successfully: " + filePath);
                } else if (scenario.getSourceTagNames().stream().anyMatch(tag -> tag.contains("AssetManagement"))) {
                    generator = new ExcelTestCaseGenerator("Asset Test Cases");
                    generator.createHeader();
                    generator.addAssetLoginTestCase(true);
                    generator.addAssetNavigationTestCase(true);
                    generator.addAssetCreationTestCase(true, "ASSET" + System.currentTimeMillis(), "ACC123456");
                    generator.addAssetDetailsVerificationTestCase(true, "ASSET" + System.currentTimeMillis());
                    generator.addAssetDeletionTestCase(true, "ASSET" + System.currentTimeMillis());
                    generator.addAssetEndToEndTestCase(true, "ASSET" + System.currentTimeMillis(), "ACC123456");
                    String filePath = "Asset_Test_Cases_" + 
                        java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".xlsx";
                    generator.saveToFile(filePath);
                    System.out.println("Excel file generated successfully: " + filePath);
                } else if (scenario.getSourceTagNames().stream().anyMatch(tag -> tag.contains("ContactManagement"))) {
                    // Generate Contact Excel
                    generator = new ExcelTestCaseGenerator("Contact Test Cases");
                    generator.createHeader();
                    generator.addContactNavigationTestCase(true);
                    generator.addFirmContactCreationTestCase(true, "Firm" + System.currentTimeMillis());
                    generator.addIndividualContactCreationTestCase(true, "John Doe");
                    generator.addEmployeeContactCreationTestCase(true, "Employee" + System.currentTimeMillis());
                    generator.addOtherPartyContactCreationTestCase(true, "Party" + System.currentTimeMillis());
                    generator.addEmployeeEditTestCase(true, "Employee" + System.currentTimeMillis(), "EMP" + System.currentTimeMillis());
                    generator.addOtherPartyEditTestCase(true, "Party" + System.currentTimeMillis(), "Manager");
                    String filePath = "Contact_Test_Cases_" + 
                        java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".xlsx";
                    generator.saveToFile(filePath);
                    System.out.println("Excel file generated successfully: " + filePath);
                } else if (scenario.getSourceTagNames().stream().anyMatch(tag -> tag.contains("NoticeCriminalManagement"))) {
                    // Generate Criminal Notice Excel
                    System.out.println("Generating Criminal Notice test case Excel...");
                    com.example.GenerateExcelNoticeCriminalDetails.main(null);
                    System.out.println("Criminal Notice Excel test case file generated successfully");
                } else if (scenario.getSourceTagNames().stream().anyMatch(tag -> tag.contains("NoticeManagement"))) {
                    // Generate Notice Excel
                    System.out.println("Generating Notice test case Excel...");
                    com.example.GenerateExcelNoticeDetails.main(null);
                    System.out.println("Notice Excel test case file generated successfully");
                } else if (scenario.getSourceTagNames().stream().anyMatch(tag -> tag.contains("ReportsManagement"))) {
                    // Generate Reports Excel
                    generator = new ExcelTestCaseGenerator("Reports Test Cases");
                    generator.createHeader();
                    generator.addReportsLoginTestCase(true);
                    generator.addReportsNavigationTestCase(true);
                    generator.addCaseReportsGenerationTestCase(true);
                    generator.addTeamOwnershipReportTestCase(true);
                    generator.addNoticeReportsGenerationTestCase(true);
                    generator.addNoticeTeamOwnershipReportTestCase(true);
                    generator.addCustomerReportSwitchTestCase(true);
                    generator.addReportsEndToEndTestCase(true);
                    String filePath = "Reports_Test_Cases_" + 
                        java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".xlsx";
                    generator.saveToFile(filePath);
                    System.out.println("Excel file generated successfully: " + filePath);
                } else if (scenario.getSourceTagNames().stream().anyMatch(tag -> tag.contains("BulkUpload"))) {
                    // Generate Bulk Upload Excel with granular test cases
                    generator = new ExcelTestCaseGenerator("Bulk Upload Test Cases");
                    generator.createHeader();
                    
                    // Add common test cases for all bulk uploads
                    generator.addBulkUploadLoginTestCase(true);
                    generator.addBulkUploadNavigationTestCase(true);
                    generator.addSelectUploadTypeTestCase(true, "Customers");
                    
                    // Add specific bulk upload test cases based on scenario
                    if (scenario.getSourceTagNames().stream().anyMatch(tag -> tag.contains("CustomerBulkUpload"))) {
                        generator.addSelectCustomerTypeTestCase(true, "Customer");
                        generator.addDownloadExcelTemplateTestCase(true, "Customer.xlsx");
                        generator.addFillExcelWithDataTestCase(true, "Customer", 13);
                        generator.addUploadExcelFileTestCase(true, "Customer.xlsx");
                        generator.addClickUploadButtonTestCase(true);
                        generator.addValidateUploadSuccessTestCase(true);
                        generator.addCustomerBulkUploadTestCase(true, "CUST" + System.currentTimeMillis(), "John Smith");
                    } else if (scenario.getSourceTagNames().stream().anyMatch(tag -> tag.contains("AssetBulkUpload"))) {
                        generator.addSelectCustomerTypeTestCase(true, "Customer Security");
                        generator.addDownloadExcelTemplateTestCase(true, "Customer Security.xlsx");
                        generator.addFillExcelWithDataTestCase(true, "Customer Asset", 8);
                        generator.addUploadExcelFileTestCase(true, "Customer Security.xlsx");
                        generator.addClickUploadButtonTestCase(true);
                        generator.addValidateUploadSuccessTestCase(true);
                        generator.addCustomerAssetBulkUploadTestCase(true, "New asset", "ayushi645311");
                    } else if (scenario.getSourceTagNames().stream().anyMatch(tag -> tag.contains("AccountBulkUpload"))) {
                        generator.addSelectCustomerTypeTestCase(true, "Customer Account");
                        generator.addDownloadExcelTemplateTestCase(true, "Customer Account.xlsx");
                        generator.addFillExcelWithDataTestCase(true, "Customer Account", 15);
                        generator.addUploadExcelFileTestCase(true, "Customer Account.xlsx");
                        generator.addClickUploadButtonTestCase(true);
                        generator.addValidateUploadSuccessTestCase(true);
                        generator.addCustomerAccountBulkUploadTestCase(true, "ACC" + System.currentTimeMillis(), "202040");
                    }
                    
                    String filePath = "Bulk_Upload_Test_Cases_" + 
                        java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".xlsx";
                    generator.saveToFile(filePath);
                    System.out.println("Excel file generated successfully: " + filePath);
                } else {
                    // Default to Customer
                    generator = new ExcelTestCaseGenerator("Customer Test Cases");
                    String filePath = generator.generateTestCases();
                    System.out.println("Excel file generated successfully: " + filePath);
                }
            } catch (Exception e) {
                System.err.println("Warning: Failed to generate Excel file: " + e.getMessage());
            }
            
            // Setup WebDriver using WebDriverManager
            System.out.println("\nInitializing browser...");
            WebDriverManager.chromedriver().setup();
            
            // Configure Chrome options
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--remote-allow-origins=*");
            
            // Disable password manager, credentials service, and password leak detection
            options.addArguments("--disable-password-manager-reauthentication");
            options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
                // Disable credentials/password manager
                put("credentials_enable_service", false);
                put("profile.password_manager_enabled", false);
                
                // Disable password leak detection
                put("profile.password_manager_leak_detection", false);
                
                // Disable browser notifications completely
                put("profile.default_content_setting_values.notifications", 2);
                
                // Disable automatic password saving prompts
                put("profile.password_manager_auto_signin", false);
                
                // Additional settings to prevent popups
                put("profile.default_content_settings.popups", 0);
                
                // Enable automatic downloads without confirmation
                put("download.default_directory", System.getProperty("user.dir") + File.separator + "downloads");
                put("download.prompt_for_download", false);
                put("download.directory_upgrade", true);
                put("safebrowsing.enabled", false);
                put("safebrowsing.disable_download_protection", true);
                
                // Allow multiple automatic downloads
                put("profile.default_content_setting_values.automatic_downloads", 1);
            }});
            
            // Uncomment for headless mode
            // options.addArguments("--headless");
            
            // Initialize WebDriver
            driver = new ChromeDriver(options);
            
            // Set implicit wait
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            
            System.out.println("Browser initialized successfully\n");
            isInitialized = true;
            
            // Add shutdown hook to close browser when JVM exits
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (driver != null) {
                    try {
                        driver.quit();
                        System.out.println("\n==============================================");
                        System.out.println("Browser closed successfully");
                        System.out.println("TEST EXECUTION COMPLETED");
                        System.out.println("==============================================\n");
                    } catch (Exception e) {
                        System.err.println("Error closing browser: " + e.getMessage());
                    }
                }
            }));
        }
        
        System.out.println("==============================================");
        System.out.println("Starting Scenario " + scenarioCount + ": " + scenario.getName());
        System.out.println("==============================================");
    }
    
    /**
     * Take screenshot after each step and save as file
     */
    @AfterStep
    public void afterStep(Scenario scenario) {
        // Take screenshot after every step
        if (driver != null) {
            try {
                stepCounter++;
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                
                // Attach to Cucumber report
                scenario.attach(screenshot, "image/png", "Step Screenshot");
                
                // Save as file
                String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
                String fileName = String.format("%s/step_%03d_%s.png", 
                    screenshotFolder, stepCounter, scenarioName);
                
                try (FileOutputStream fos = new FileOutputStream(fileName)) {
                    fos.write(screenshot);
                    System.out.println("Screenshot saved: " + fileName);
                } catch (IOException e) {
                    System.err.println("Failed to save screenshot file: " + e.getMessage());
                }
            } catch (Exception e) {
                System.err.println("Failed to capture step screenshot: " + e.getMessage());
            }
        }
    }
    
    /**
     * Teardown method - runs after each scenario
     * Only closes browser after the last scenario
     */
    @After(order = 0)
    public void tearDown(Scenario scenario) {
        // Take screenshot on failure
        if (scenario.isFailed() && driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
                
                // Save failure screenshot as file
                String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
                String fileName = String.format("%s/FAILED_%s.png", screenshotFolder, scenarioName);
                try (FileOutputStream fos = new FileOutputStream(fileName)) {
                    fos.write(screenshot);
                    System.out.println("Failure screenshot saved: " + fileName);
                } catch (IOException e) {
                    System.err.println("Failed to save failure screenshot: " + e.getMessage());
                }
                
                System.out.println("Screenshot captured for failed scenario: " + scenario.getName());
            } catch (Exception e) {
                System.err.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
        
        System.out.println("==============================================");
        System.out.println("Finished Scenario " + scenarioCount + ": " + scenario.getName());
        System.out.println("Status: " + scenario.getStatus());
        System.out.println("==============================================\n");
        
        // Browser will be closed by shutdown hook when all tests complete
    }
    
    /**
     * Get WebDriver instance
     * @return WebDriver
     */
    public static WebDriver getDriver() {
        return driver;
    }
}
