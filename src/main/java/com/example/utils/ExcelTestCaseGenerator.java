package com.example.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Utility class for generating Excel test case documentation using Apache POI
 */
public class ExcelTestCaseGenerator {
    
    private Workbook workbook;
    private Sheet sheet;
    private int rowNum;
    private String moduleName;
    
    // Cell styles
    private CellStyle headerStyle;
    private CellStyle passStyle;
    private CellStyle failStyle;
    private CellStyle dataStyle;
    
    /**
     * Constructor - initializes workbook and styles
     */
    public ExcelTestCaseGenerator() {
        this("Customer Search Test Cases");
    }
    
    /**
     * Constructor with custom sheet name
     */
    public ExcelTestCaseGenerator(String sheetName) {
        workbook = new XSSFWorkbook();
        this.moduleName = sheetName;
        sheet = workbook.createSheet(sheetName);
        rowNum = 0;
        createStyles();
    }
    
    /**
     * Create cell styles for formatting
     */
    private void createStyles() {
        // Header style
        headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        
        // Pass style
        passStyle = workbook.createCellStyle();
        Font passFont = workbook.createFont();
        passFont.setBold(true);
        passFont.setColor(IndexedColors.GREEN.getIndex());
        passStyle.setFont(passFont);
        passStyle.setAlignment(HorizontalAlignment.CENTER);
        passStyle.setBorderBottom(BorderStyle.THIN);
        passStyle.setBorderTop(BorderStyle.THIN);
        passStyle.setBorderLeft(BorderStyle.THIN);
        passStyle.setBorderRight(BorderStyle.THIN);
        
        // Fail style
        failStyle = workbook.createCellStyle();
        Font failFont = workbook.createFont();
        failFont.setBold(true);
        failFont.setColor(IndexedColors.RED.getIndex());
        failStyle.setFont(failFont);
        failStyle.setAlignment(HorizontalAlignment.CENTER);
        failStyle.setBorderBottom(BorderStyle.THIN);
        failStyle.setBorderTop(BorderStyle.THIN);
        failStyle.setBorderLeft(BorderStyle.THIN);
        failStyle.setBorderRight(BorderStyle.THIN);
        
        // Data style
        dataStyle = workbook.createCellStyle();
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setWrapText(true);
    }
    
    /**
     * Create header row
     */
    public void createHeader() {
        Row headerRow = sheet.createRow(rowNum++);
        String[] headers = {
            "Test Case ID", "Test Case Name", "Test Scenario", "Test Steps", 
            "Test Data", "Expected Result", "Actual Result", "Status", 
            "Executed By", "Execution Date", "Comments"
        };
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
        
        // Set column widths
        sheet.setColumnWidth(0, 4000);  // Test Case ID
        sheet.setColumnWidth(1, 8000);  // Test Case Name
        sheet.setColumnWidth(2, 10000); // Test Scenario
        sheet.setColumnWidth(3, 12000); // Test Steps
        sheet.setColumnWidth(4, 8000);  // Test Data
        sheet.setColumnWidth(5, 8000);  // Expected Result
        sheet.setColumnWidth(6, 8000);  // Actual Result
        sheet.setColumnWidth(7, 3000);  // Status
        sheet.setColumnWidth(8, 5000);  // Executed By
        sheet.setColumnWidth(9, 5000);  // Execution Date
        sheet.setColumnWidth(10, 8000); // Comments
    }
    
    /**
     * Add test case for login
     */
    public void addLoginTestCase(boolean passed) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_LOGIN_001", dataStyle);
        createCell(row, cellNum++, "Login to LMS Application", dataStyle);
        createCell(row, cellNum++, "Verify user can successfully login to the application", dataStyle);
        createCell(row, cellNum++, 
            "1. Open URL: https://qalmsbu.cubictree.com/\n" +
            "2. Enter Email: ayushi\n" +
            "3. Enter Password: Legal@1234\n" +
            "4. Click Sign In button\n" +
            "5. Enter OTP: 123456\n" +
            "6. Click Submit button", dataStyle);
        createCell(row, cellNum++, "Email: ayushi\nPassword: Legal@1234\nOTP: 123456", dataStyle);
        createCell(row, cellNum++, "User should be logged in successfully and redirected to dashboard", dataStyle);
        createCell(row, cellNum++, passed ? "User logged in successfully" : "Login failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add test case for customer navigation
     */
    public void addCustomerNavigationTestCase(boolean passed) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_NAV_001", dataStyle);
        createCell(row, cellNum++, "Navigate to Add Customer Page", dataStyle);
        createCell(row, cellNum++, "Verify user can navigate to Add Customer page", dataStyle);
        createCell(row, cellNum++, 
            "1. Click on Borrowers menu\n" +
            "2. Click on Customer submenu\n" +
            "3. Click on Add New Customer button", dataStyle);
        createCell(row, cellNum++, "Menu paths as per application structure", dataStyle);
        createCell(row, cellNum++, "User should be navigated to Add Customer form", dataStyle);
        createCell(row, cellNum++, passed ? "Successfully navigated to Add Customer page" : "Navigation failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add test case for customer creation
     */
    public void addCustomerCreationTestCase(boolean passed, String customerNumber, String customerName) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_CUST_001", dataStyle);
        createCell(row, cellNum++, "Create New Customer", dataStyle);
        createCell(row, cellNum++, "Verify user can create a new customer with all required fields", dataStyle);
        createCell(row, cellNum++, 
            "1. Fill Customer Number with random alphanumeric\n" +
            "2. Fill Customer Name with random name\n" +
            "3. Fill Father Name with random name\n" +
            "4. Select Customer Type from dropdown\n" +
            "5. Select Industry from dropdown\n" +
            "6. Select Segment from dropdown\n" +
            "7. Fill Phone Number with 10 digits\n" +
            "8. Fill Email with random email\n" +
            "9. Select Business Unit from dropdown\n" +
            "10. Fill Mobile Number with 10 digits\n" +
            "11. Fill Aadhaar Number with 16 digits\n" +
            "12. Select Zone, State, Location dropdowns\n" +
            "13. Select Address Type\n" +
            "14. Fill Address 1 and Address 2\n" +
            "15. Click Next on Basic Details section\n" +
            "16. Click Next on NPA section\n" +
            "17. Click Next on third section\n" +
            "18. Select Sr Supervisor\n" +
            "19. Click Next on fourth section\n" +
            "20. Enter Remarks\n" +
            "21. Click Save button", dataStyle);
        createCell(row, cellNum++, 
            "Customer Number: " + (customerNumber != null ? customerNumber : "Random") + "\n" +
            "Customer Name: " + (customerName != null ? customerName : "Random") + "\n" +
            "All other fields: Random data", dataStyle);
        createCell(row, cellNum++, "Customer should be created successfully with all filled data", dataStyle);
        createCell(row, cellNum++, passed ? "Customer created successfully" : "Customer creation failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add test case for customer search
     */
    public void addCustomerSearchTestCase(boolean passed, String customerNumber) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_SEARCH_001", dataStyle);
        createCell(row, cellNum++, "Search Customer by Customer Number", dataStyle);
        createCell(row, cellNum++, "Verify user can search for a customer using customer number", dataStyle);
        createCell(row, cellNum++, 
            "1. Click Back button to return to customer list\n" +
            "2. Enter Customer Number in search field\n" +
            "3. Click Search button\n" +
            "4. Verify customer appears in search results", dataStyle);
        createCell(row, cellNum++, "Customer Number: " + (customerNumber != null ? customerNumber : "As created"), dataStyle);
        createCell(row, cellNum++, "Customer should be displayed in search results", dataStyle);
        createCell(row, cellNum++, passed ? "Customer found in search results" : "Customer not found", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add end-to-end test case
     */
    public void addEndToEndTestCase(boolean passed, String customerNumber, String customerName) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_E2E_001", dataStyle);
        createCell(row, cellNum++, "Complete Customer Creation and Search Flow", dataStyle);
        createCell(row, cellNum++, "Verify complete end-to-end flow from login to customer creation and search", dataStyle);
        createCell(row, cellNum++, 
            "1. Login to application\n" +
            "2. Enter OTP\n" +
            "3. Navigate to Add Customer page\n" +
            "4. Fill all customer details\n" +
            "5. Save customer\n" +
            "6. Navigate back to customer list\n" +
            "7. Search for created customer\n" +
            "8. Verify customer in results", dataStyle);
        createCell(row, cellNum++, 
            "Login: ayushi / Legal@1234\n" +
            "OTP: 123456\n" +
            "Customer Number: " + (customerNumber != null ? customerNumber : "Random") + "\n" +
            "Customer Name: " + (customerName != null ? customerName : "Random"), dataStyle);
        createCell(row, cellNum++, "Complete flow should execute successfully with customer created and searchable", dataStyle);
        createCell(row, cellNum++, passed ? "End-to-end flow completed successfully" : "Flow failed at some step", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Create a cell with value and style
     */
    private void createCell(Row row, int cellNum, String value, CellStyle style) {
        Cell cell = row.createCell(cellNum);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }
    
    /**
     * Get current date and time
     */
    private String getCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
    
    /**
     * Save workbook to file
     */
    public void saveToFile(String filePath) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Excel test case file created successfully: " + filePath);
        } finally {
            workbook.close();
        }
    }
    
    /**
     * Generate complete test case document with all scenarios
     * @return The path to the generated Excel file
     * @throws IOException if file cannot be created
     */
    public String generateTestCases() throws IOException {
        // Create header
        createHeader();
        
        // Add test cases with sample data
        addLoginTestCase(true);
        addCustomerNavigationTestCase(true);
        addCustomerCreationTestCase(true, "CUST123456", "John Doe");
        addCustomerSearchTestCase(true, "CUST123456");
        addEndToEndTestCase(true, "CUST123456", "John Doe");
        
        // Generate filename with timestamp
        String fileName = "Customer_Search_Test_Cases_" + 
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".xlsx";
        
        // Save to file
        saveToFile(fileName);
        
        return fileName;
    }
    
    /**
     * Add test case for account login (reusing login test case)
     */
    public void addAccountLoginTestCase(boolean passed) {
        addLoginTestCase(passed);
    }
    
    /**
     * Add test case for account navigation
     */
    public void addAccountNavigationTestCase(boolean passed) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_ACC_NAV_001", dataStyle);
        createCell(row, cellNum++, "Navigate to Add Account Page", dataStyle);
        createCell(row, cellNum++, "Verify user can navigate to Add Account page", dataStyle);
        createCell(row, cellNum++, 
            "1. Click on Borrowers menu\n" +
            "2. Click on Accounts submenu\n" +
            "3. Click on Add New Account button", dataStyle);
        createCell(row, cellNum++, "Menu paths as per application structure", dataStyle);
        createCell(row, cellNum++, "User should be navigated to Add Account form", dataStyle);
        createCell(row, cellNum++, passed ? "Successfully navigated to Add Account page" : "Navigation failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add test case for account creation
     */
    public void addAccountCreationTestCase(boolean passed, String accountNumber, String customerNumber) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_ACC_001", dataStyle);
        createCell(row, cellNum++, "Create New Account", dataStyle);
        createCell(row, cellNum++, "Verify user can create a new account with all required fields", dataStyle);
        createCell(row, cellNum++, 
            "1. Fill Account Number with random alphanumeric\n" +
            "2. Select Account Type from dropdown\n" +
            "3. Select Customer Number from dropdown\n" +
            "4. Fill Principal Amount with random numeric\n" +
            "5. Fill Rate of Interest with random decimal\n" +
            "6. Fill Outstanding Balance with random numeric\n" +
            "7. Fill Loan Disbursal Date with random date\n" +
            "8. Select Account Status from dropdown\n" +
            "9. Select Zone, State, Location dropdowns\n" +
            "10. Select Business Unit from dropdown\n" +
            "11. Click Next on section 1\n" +
            "12. Click Next on section 2 (Bank Consortium)\n" +
            "13. Click Next on section 3 (Address Information)\n" +
            "14. Click Save button", dataStyle);
        createCell(row, cellNum++, 
            "Account Number: " + (accountNumber != null ? accountNumber : "Random") + "\n" +
            "Customer Number: " + (customerNumber != null ? customerNumber : "Random") + "\n" +
            "All other fields: Random data", dataStyle);
        createCell(row, cellNum++, "Account should be created successfully with all filled data", dataStyle);
        createCell(row, cellNum++, passed ? "Account created successfully" : "Account creation failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add test case for account search
     */
    public void addAccountSearchTestCase(boolean passed, String accountNumber) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_ACC_SEARCH_001", dataStyle);
        createCell(row, cellNum++, "Search Account by Account Number", dataStyle);
        createCell(row, cellNum++, "Verify user can search for an account using account number", dataStyle);
        createCell(row, cellNum++, 
            "1. Click Back button to return to account list\n" +
            "2. Enter Account Number in search field\n" +
            "3. Click Search button\n" +
            "4. Verify account appears in search results", dataStyle);
        createCell(row, cellNum++, "Account Number: " + (accountNumber != null ? accountNumber : "As created"), dataStyle);
        createCell(row, cellNum++, "Account should be displayed in search results", dataStyle);
        createCell(row, cellNum++, passed ? "Account found in search results" : "Account not found", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add end-to-end test case for account
     */
    public void addAccountEndToEndTestCase(boolean passed, String accountNumber, String customerNumber) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_ACC_E2E_001", dataStyle);
        createCell(row, cellNum++, "Complete Account Creation and Search Flow", dataStyle);
        createCell(row, cellNum++, "Verify complete end-to-end flow from login to account creation and search", dataStyle);
        createCell(row, cellNum++, 
            "1. Login to application\n" +
            "2. Enter OTP\n" +
            "3. Navigate to Add Account page\n" +
            "4. Fill all account details\n" +
            "5. Save account\n" +
            "6. Navigate back to account list\n" +
            "7. Search for created account\n" +
            "8. Verify account in results", dataStyle);
        createCell(row, cellNum++, 
            "Login: ayushi / Legal@1234\n" +
            "OTP: 123456\n" +
            "Account Number: " + (accountNumber != null ? accountNumber : "Random") + "\n" +
            "Customer Number: " + (customerNumber != null ? customerNumber : "Random"), dataStyle);
        createCell(row, cellNum++, "Complete flow should execute successfully with account created and searchable", dataStyle);
        createCell(row, cellNum++, passed ? "End-to-end flow completed successfully" : "Flow failed at some step", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add Asset Login Test Case
     */
    public void addAssetLoginTestCase(boolean passed) {
        Row row = sheet.createRow(++rowNum);
        int cellNum = 0;
        createCell(row, cellNum++, "Asset_Login_001", dataStyle);
        createCell(row, cellNum++, "Verify user can login to LMS", dataStyle);
        createCell(row, cellNum++, "Login with valid credentials", dataStyle);
        createCell(row, cellNum++, "User should be logged in successfully", dataStyle);
        createCell(row, cellNum++, passed ? "Pass" : "Fail", passed ? passStyle : failStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Login successful" : "Login failed", dataStyle);
    }
    
    /**
     * Add Asset Navigation Test Case
     */
    public void addAssetNavigationTestCase(boolean passed) {
        Row row = sheet.createRow(++rowNum);
        int cellNum = 0;
        createCell(row, cellNum++, "Asset_Nav_001", dataStyle);
        createCell(row, cellNum++, "Navigate to Assets module", dataStyle);
        createCell(row, cellNum++, "Click Borrowers > Assets > Add New Asset", dataStyle);
        createCell(row, cellNum++, "Asset creation page should open", dataStyle);
        createCell(row, cellNum++, passed ? "Pass" : "Fail", passed ? passStyle : failStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Navigation successful" : "Navigation failed", dataStyle);
    }
    
    /**
     * Add Asset Creation Test Case
     */
    public void addAssetCreationTestCase(boolean passed, String assetName, String accountNumber) {
        int testCaseNum = 1;
        Row row = sheet.createRow(++rowNum);
        int cellNum = 0;
        
        createCell(row, cellNum++, "Asset_Create_" + String.format("%03d", testCaseNum++), dataStyle);
        createCell(row, cellNum++, "Create new asset with all mandatory fields", dataStyle);
        createCell(row, cellNum++, "1. Select Asset Category\n2. Enter Asset Name: " + assetName + 
                   "\n3. Select Account Number: " + accountNumber + 
                   "\n4. Fill all mandatory fields\n5. Click Next\n6. Click Save", dataStyle);
        createCell(row, cellNum++, "Asset should be created successfully", dataStyle);
        createCell(row, cellNum++, passed ? "Pass" : "Fail", passed ? passStyle : failStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Asset created with all details" : "Asset creation failed", dataStyle);
    }
    
    /**
     * Add Asset Details Verification Test Case
     */
    public void addAssetDetailsVerificationTestCase(boolean passed, String assetName) {
        int testCaseNum = 1;
        Row row = sheet.createRow(++rowNum);
        int cellNum = 0;
        
        createCell(row, cellNum++, "Asset_Verify_" + String.format("%03d", testCaseNum++), dataStyle);
        createCell(row, cellNum++, "Verify all asset details sections are accessible", dataStyle);
        createCell(row, cellNum++, "1. Verify Basic Information section\n2. Verify Linked Case/Notice section\n" +
                   "3. Verify Address Details section\n4. Verify Publications section\n" +
                   "5. Verify Bidders section\n6. Verify Fate of Sale/Auction section", dataStyle);
        createCell(row, cellNum++, "All sections should be accessible and functional", dataStyle);
        createCell(row, cellNum++, passed ? "Pass" : "Fail", passed ? passStyle : failStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "All sections verified for asset: " + assetName : "Verification failed", dataStyle);
    }
    
    /**
     * Add Asset Deletion Test Case
     */
    public void addAssetDeletionTestCase(boolean passed, String assetName) {
        int testCaseNum = 1;
        Row row = sheet.createRow(++rowNum);
        int cellNum = 0;
        
        createCell(row, cellNum++, "Asset_Delete_" + String.format("%03d", testCaseNum++), dataStyle);
        createCell(row, cellNum++, "Delete created asset", dataStyle);
        createCell(row, cellNum++, "1. Click Back button\n2. Search for asset: " + assetName + 
                   "\n3. Click Action button\n4. Click Delete\n5. Accept alert", dataStyle);
        createCell(row, cellNum++, "Asset should be deleted successfully", dataStyle);
        createCell(row, cellNum++, passed ? "Pass" : "Fail", passed ? passStyle : failStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Asset deleted successfully" : "Asset deletion failed", dataStyle);
    }
    
    /**
     * Add Asset End-to-End Test Case
     */
    public void addAssetEndToEndTestCase(boolean passed, String assetName, String accountNumber) {
        int testCaseNum = 1;
        Row row = sheet.createRow(++rowNum);
        int cellNum = 0;
        
        createCell(row, cellNum++, "Asset_E2E_" + String.format("%03d", testCaseNum++), dataStyle);
        createCell(row, cellNum++, "Complete asset lifecycle - Create, Verify, Delete", dataStyle);
        createCell(row, cellNum++, "1. Navigate to Assets module\n2. Create new asset: " + assetName + 
                   "\n3. Verify all details sections\n4. Delete the asset", dataStyle);
        createCell(row, cellNum++, "Complete asset lifecycle should execute successfully", dataStyle);
        createCell(row, cellNum++, passed ? "Pass" : "Fail", passed ? passStyle : failStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
        // ==================== CONTACT MODULE TEST CASES ====================
    
    /**
     * Add Contact Login Test Case
     */
    public void addContactLoginTestCase(boolean passed) {
        addLoginTestCase(passed);
    }
    
    /**
     * Add Contact Navigation Test Case
     */
    public void addContactNavigationTestCase(boolean passed) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_CONTACT_NAV_001", dataStyle);
        createCell(row, cellNum++, "Navigate to Contacts Module", dataStyle);
        createCell(row, cellNum++, "User navigates to Contacts module from dashboard", dataStyle);
        createCell(row, cellNum++, "1. Login to LMS application\n2. Click on Contacts module icon from sidebar", dataStyle);
        createCell(row, cellNum++, "N/A", dataStyle);
        createCell(row, cellNum++, "User should be navigated to Contacts page with different contact tabs (Firm, Individual, Employee, Other Parties)", dataStyle);
        createCell(row, cellNum++, passed ? "Navigated successfully to Contacts module" : "Navigation failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add Firm Contact Creation Test Case
     */
    public void addFirmContactCreationTestCase(boolean passed, String firmName) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_FIRM_CREATE_001", dataStyle);
        createCell(row, cellNum++, "Create New Firm Contact", dataStyle);
        createCell(row, cellNum++, "User creates a new Firm/Company contact", dataStyle);
        createCell(row, cellNum++, 
            "1. Navigate to Contacts module\n" +
            "2. Click on Firm tab\n" +
            "3. Click 'Add New' button\n" +
            "4. Select 'Single Entry' from dropdown\n" +
            "5. Select Contact Type (Firm)\n" +
            "6. Enter Firm Name\n" +
            "7. Enter Email (optional)\n" +
            "8. Click Next\n" +
            "9. Click Save", dataStyle);
        createCell(row, cellNum++, "Firm Name: " + (firmName != null ? firmName : "Random"), dataStyle);
        createCell(row, cellNum++, "Firm contact should be created successfully", dataStyle);
        createCell(row, cellNum++, passed ? "Firm contact created: " + firmName : "Firm contact creation failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add Individual Contact Creation Test Case
     */
    public void addIndividualContactCreationTestCase(boolean passed, String advocateName) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_INDIVIDUAL_CREATE_001", dataStyle);
        createCell(row, cellNum++, "Create New Individual Contact (Advocate)", dataStyle);
        createCell(row, cellNum++, "User creates a new Individual contact of type Advocate", dataStyle);
        createCell(row, cellNum++, 
            "1. Navigate to Contacts module\n" +
            "2. Click on Individual tab\n" +
            "3. Click 'Add New' button\n" +
            "4. Select 'Single Entry' from dropdown\n" +
            "5. Select Contact Type (Advocate - Option 3)\n" +
            "6. Enter Advocate Name\n" +
            "7. Enter Email address\n" +
            "8. Click Next\n" +
            "9. Click Save", dataStyle);
        createCell(row, cellNum++, "Advocate Name: " + (advocateName != null ? advocateName : "Random"), dataStyle);
        createCell(row, cellNum++, "Individual contact (Advocate) should be created successfully", dataStyle);
        createCell(row, cellNum++, passed ? "Advocate contact created: " + advocateName : "Advocate contact creation failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add Employee Contact Creation Test Case
     */
    public void addEmployeeContactCreationTestCase(boolean passed, String employeeName) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_EMPLOYEE_CREATE_001", dataStyle);
        createCell(row, cellNum++, "Create New Employee Contact", dataStyle);
        createCell(row, cellNum++, "User creates a new Employee contact", dataStyle);
        createCell(row, cellNum++, 
            "1. Navigate to Contacts module\n" +
            "2. Click on Employee tab\n" +
            "3. Click 'Add New' button\n" +
            "4. Select 'Single Entry' from dropdown\n" +
            "5. Select Contact Type (Employee - value='6')\n" +
            "6. Enter Employee Name\n" +
            "7. Click Next\n" +
            "8. Click Save", dataStyle);
        createCell(row, cellNum++, "Employee Name: " + (employeeName != null ? employeeName : "Random"), dataStyle);
        createCell(row, cellNum++, "Employee contact should be created successfully", dataStyle);
        createCell(row, cellNum++, passed ? "Employee contact created: " + employeeName : "Employee contact creation failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add Other Party Contact Creation Test Case
     */
    public void addOtherPartyContactCreationTestCase(boolean passed, String otherPartyName) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_OTHERPARTY_CREATE_001", dataStyle);
        createCell(row, cellNum++, "Create New Other Party Contact (Guarantor)", dataStyle);
        createCell(row, cellNum++, "User creates a new Other Party contact of type Guarantor", dataStyle);
        createCell(row, cellNum++, 
            "1. Navigate to Contacts module\n" +
            "2. Click on Other Parties tab\n" +
            "3. Click 'Add New' button\n" +
            "4. Select 'Single Entry' from dropdown\n" +
            "5. Select Contact Type (Guarantor - value='4')\n" +
            "6. Enter Other Party Name\n" +
            "7. Enter Email address\n" +
            "8. Click Next\n" +
            "9. Click Save", dataStyle);
        createCell(row, cellNum++, "Other Party Name: " + (otherPartyName != null ? otherPartyName : "Random"), dataStyle);
        createCell(row, cellNum++, "Other Party contact (Guarantor) should be created successfully", dataStyle);
        createCell(row, cellNum++, passed ? "Other Party contact created: " + otherPartyName : "Other Party contact creation failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add Employee Edit Test Case
     */
    public void addEmployeeEditTestCase(boolean passed, String employeeName, String employeeId) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_EMPLOYEE_EDIT_001", dataStyle);
        createCell(row, cellNum++, "Edit Employee Contact Details", dataStyle);
        createCell(row, cellNum++, "User searches for and edits an existing Employee contact", dataStyle);
        createCell(row, cellNum++, 
            "1. Navigate to Contacts module\n" +
            "2. Click on Employee tab\n" +
            "3. Enter contact name in search field\n" +
            "4. Click search icon\n" +
            "5. Click on the searched contact from results\n" +
            "6. Click on Employee details section\n" +
            "7. Click settings (three dots) button\n" +
            "8. Click Edit button\n" +
            "9. Enter Employee ID\n" +
            "10. Click Save", dataStyle);
        createCell(row, cellNum++, 
            "Employee Name: " + (employeeName != null ? employeeName : "As created") + "\n" +
            "Employee ID: " + (employeeId != null ? employeeId : "Random"), dataStyle);
        createCell(row, cellNum++, "Employee contact should be updated with new Employee ID", dataStyle);
        createCell(row, cellNum++, passed ? "Employee ID updated for: " + employeeName : "Employee update failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add Other Party Edit Test Case
     */
    public void addOtherPartyEditTestCase(boolean passed, String otherPartyName, String designation) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_OTHERPARTY_EDIT_001", dataStyle);
        createCell(row, cellNum++, "Edit Other Party Contact Details", dataStyle);
        createCell(row, cellNum++, "User searches for and edits an existing Other Party contact", dataStyle);
        createCell(row, cellNum++, 
            "1. Navigate to Contacts module\n" +
            "2. Click on Other Parties tab\n" +
            "3. Enter contact name in search field\n" +
            "4. Click search icon\n" +
            "5. Click on the searched contact from results\n" +
            "6. Click on Other Parties Details tab\n" +
            "7. Click settings (three dots) button\n" +
            "8. Click Edit button\n" +
            "9. Enter Designation\n" +
            "10. Click Save", dataStyle);
        createCell(row, cellNum++, 
            "Other Party Name: " + (otherPartyName != null ? otherPartyName : "As created") + "\n" +
            "Designation: " + (designation != null ? designation : "Random"), dataStyle);
        createCell(row, cellNum++, "Other Party contact should be updated with new Designation", dataStyle);
        createCell(row, cellNum++, passed ? "Designation updated for: " + otherPartyName : "Other Party update failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    // ==================== REPORTS MODULE TEST CASES ====================
    
    /**
     * Add Reports Login Test Case
     */
    public void addReportsLoginTestCase(boolean passed) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_REPORTS_LOGIN_001", dataStyle);
        createCell(row, cellNum++, "Login to LMS Application", dataStyle);
        createCell(row, cellNum++, "User logs into the system to access Reports module", dataStyle);
        createCell(row, cellNum++, 
            "1. Open LMS application\n" +
            "2. Enter username\n" +
            "3. Enter password\n" +
            "4. Enter OTP\n" +
            "5. Click login", dataStyle);
        createCell(row, cellNum++, 
            "Username: ayushi\n" +
            "Password: Legal@1234\n" +
            "OTP: 123456", dataStyle);
        createCell(row, cellNum++, "User should be logged in successfully", dataStyle);
        createCell(row, cellNum++, passed ? "Login successful" : "Login failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add Reports Navigation Test Case
     */
    public void addReportsNavigationTestCase(boolean passed) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_REPORTS_NAV_001", dataStyle);
        createCell(row, cellNum++, "Navigate to Reports Module", dataStyle);
        createCell(row, cellNum++, "User navigates to Reports module from main menu", dataStyle);
        createCell(row, cellNum++, 
            "1. After successful login\n" +
            "2. Click on Reports menu item (li[9])\n" +
            "3. Wait for Reports page to load", dataStyle);
        createCell(row, cellNum++, "XPath: /html/body/div[2]/div/div/div/ul/li[9]/a", dataStyle);
        createCell(row, cellNum++, "Reports module should open successfully", dataStyle);
        createCell(row, cellNum++, passed ? "Navigated to Reports successfully" : "Navigation failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add Case Reports Generation Test Case
     */
    public void addCaseReportsGenerationTestCase(boolean passed) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_REPORTS_CASE_001", dataStyle);
        createCell(row, cellNum++, "Generate Case Reports with Recovery, Expenses, and Assets", dataStyle);
        createCell(row, cellNum++, "User generates comprehensive case reports including recovery, expenses, and asset details", dataStyle);
        createCell(row, cellNum++, 
            "1. Navigate to Reports module\n" +
            "2. Select Template Report Name dropdown\n" +
            "3. Select Include checkbox for cases\n" +
            "4. Click Recovery section\n" +
            "5. Select All Recovery checkbox\n" +
            "6. Click Generate Case Report\n" +
            "7. Click Case Expenses section\n" +
            "8. Select All Case Expenses checkbox\n" +
            "9. Click Generate Case Report\n" +
            "10. Click Asset section\n" +
            "11. Select Asset Include checkbox\n" +
            "12. Click Generate Case Report", dataStyle);
        createCell(row, cellNum++, 
            "Template Dropdown: //*[@id='ReportTemplatesDD']\n" +
            "Include: //*[@id='Caseselectall']\n" +
            "Recovery: //*[@id='lnkRecovery']\n" +
            "All Recovery: //*[@id='caseRecoverySelectAll']\n" +
            "Case Expenses: //*[@id='lnkCaseExpense']\n" +
            "All Expenses: //*[@id='CaseExpensesSelectall']\n" +
            "Asset: //*[@id='lnkAsset']\n" +
            "Asset Include: //*[@id='case_AllAssetDetails']\n" +
            "Generate: //*[@id='SubmitReport']", dataStyle);
        createCell(row, cellNum++, "Case reports should be generated successfully with all sections", dataStyle);
        createCell(row, cellNum++, passed ? "Case reports generated successfully" : "Report generation failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add Team Ownership Report Test Case
     */
    public void addTeamOwnershipReportTestCase(boolean passed) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_REPORTS_TEAM_001", dataStyle);
        createCell(row, cellNum++, "Generate Team Case Ownership Report", dataStyle);
        createCell(row, cellNum++, "User generates team-based case ownership report after reset", dataStyle);
        createCell(row, cellNum++, 
            "1. Navigate to Reports module\n" +
            "2. Click Reset button\n" +
            "3. Select Template Report Name dropdown\n" +
            "4. Click Team case ownership filter\n" +
            "5. Click Generate Case Report", dataStyle);
        createCell(row, cellNum++, 
            "Reset: //*[@id='reset']\n" +
            "Template Dropdown: //*[@id='ReportTemplatesDD']\n" +
            "Team Filter: //*[@id='Team']\n" +
            "Generate: //*[@id='SubmitReport']", dataStyle);
        createCell(row, cellNum++, "Team ownership report should be generated successfully", dataStyle);
        createCell(row, cellNum++, passed ? "Team report generated successfully" : "Report generation failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add Notice Reports Generation Test Case
     */
    public void addNoticeReportsGenerationTestCase(boolean passed) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_REPORTS_NOTICE_001", dataStyle);
        createCell(row, cellNum++, "Generate Notice Reports with Recovery and Proceedings", dataStyle);
        createCell(row, cellNum++, "User generates notice reports including recovery and proceedings sections", dataStyle);
        createCell(row, cellNum++, 
            "1. Navigate to Reports module\n" +
            "2. Click Notice checkbox\n" +
            "3. Select Template Report Name dropdown\n" +
            "4. Click Include all for notices\n" +
            "5. Select all notice recovery checkbox\n" +
            "6. Click Generate Notice Report\n" +
            "7. Click Notice Proceedings section\n" +
            "8. Select all Notice Proceedings checkbox\n" +
            "9. Click Generate Notice Report", dataStyle);
        createCell(row, cellNum++, 
            "Notice Checkbox: //*[@id='NoticeAll']\n" +
            "Template: //*[@id='ReportTemplatesDD']\n" +
            "Include All: //*[@id='Noticeselectall']\n" +
            "Recovery All: //*[@id='noticeRecoverySelectAll']\n" +
            "Proceedings: //*[@id='lnknoticeproceedings']\n" +
            "Proceedings All: //*[@id='NoticeProceedingsSelectall']\n" +
            "Generate: //*[@id='SubmitReport']", dataStyle);
        createCell(row, cellNum++, "Notice reports should be generated with recovery and proceedings data", dataStyle);
        createCell(row, cellNum++, passed ? "Notice reports generated successfully" : "Report generation failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add Notice Team Ownership Report Test Case
     */
    public void addNoticeTeamOwnershipReportTestCase(boolean passed) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_REPORTS_NOTICE_TEAM_001", dataStyle);
        createCell(row, cellNum++, "Generate Notice Team Ownership Report", dataStyle);
        createCell(row, cellNum++, "User generates team ownership report for notices", dataStyle);
        createCell(row, cellNum++, 
            "1. Navigate to Reports module\n" +
            "2. Click Notice checkbox\n" +
            "3. Select Template Report Name dropdown\n" +
            "4. Click Notice Team ownership filter\n" +
            "5. Click Generate Notice Report", dataStyle);
        createCell(row, cellNum++, 
            "Notice Checkbox: //*[@id='NoticeAll']\n" +
            "Template: //*[@id='ReportTemplatesDD']\n" +
            "Team Filter: //*[@id='Team']\n" +
            "Generate: //*[@id='SubmitReport']", dataStyle);
        createCell(row, cellNum++, "Notice team ownership report should be generated", dataStyle);
        createCell(row, cellNum++, passed ? "Notice team report generated successfully" : "Report generation failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add Customer Report Switch Test Case
     */
    public void addCustomerReportSwitchTestCase(boolean passed) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_REPORTS_CUSTOMER_001", dataStyle);
        createCell(row, cellNum++, "Switch to Customer Report Section", dataStyle);
        createCell(row, cellNum++, "User switches to customer report section after reset", dataStyle);
        createCell(row, cellNum++, 
            "1. Navigate to Reports module\n" +
            "2. Click Reset button\n" +
            "3. Click Customer radio button", dataStyle);
        createCell(row, cellNum++, 
            "Reset: //*[@id='reset']\n" +
            "Customer Radio: //*[@id='CustomerAll']", dataStyle);
        createCell(row, cellNum++, "Customer report section should be displayed", dataStyle);
        createCell(row, cellNum++, passed ? "Switched to customer section successfully" : "Switch failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Add Reports End to End Test Case
     */
    public void addReportsEndToEndTestCase(boolean passed) {
        Row row = sheet.createRow(rowNum++);
        int cellNum = 0;
        
        createCell(row, cellNum++, "TC_REPORTS_E2E_001", dataStyle);
        createCell(row, cellNum++, "Generate All Types of Reports End to End", dataStyle);
        createCell(row, cellNum++, "User generates all report types in a complete workflow", dataStyle);
        createCell(row, cellNum++, 
            "1. Navigate to Reports module\n" +
            "2. Generate Case Reports (Recovery, Expenses, Assets)\n" +
            "3. Click Reset and generate Team Ownership Report\n" +
            "4. Switch to Notice checkbox\n" +
            "5. Generate Notice Reports (Recovery, Proceedings)\n" +
            "6. Generate Notice Team Ownership Report\n" +
            "7. Click Reset and switch to Customer section", dataStyle);
        createCell(row, cellNum++, "All XPaths used across previous test cases", dataStyle);
        createCell(row, cellNum++, "All report types should be generated successfully", dataStyle);
        createCell(row, cellNum++, passed ? "All reports workflow completed" : "Workflow failed", dataStyle);
        createCell(row, cellNum++, passed ? "PASS" : "FAIL", passed ? passStyle : failStyle);
        createCell(row, cellNum++, "Automation", dataStyle);
        createCell(row, cellNum++, getCurrentDateTime(), dataStyle);
        createCell(row, cellNum++, passed ? "Test executed successfully" : "Test failed - check logs", dataStyle);
    }
    
    /**
     * Main method to generate sample test case document
     */
    public static void main(String[] args) {
        try {
            ExcelTestCaseGenerator generator = new ExcelTestCaseGenerator();
            String filePath = generator.generateTestCases();
            System.out.println("Test case documentation generated successfully: " + filePath);
        } catch (IOException e) {
            System.err.println("Error generating Excel file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
