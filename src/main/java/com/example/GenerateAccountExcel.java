package com.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.utils.ExcelTestCaseGenerator;

/**
 * Standalone class to generate Excel test case documentation for Account module
 * Run this class directly to create an Excel file with account test cases
 */
public class GenerateAccountExcel {
    
    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("Account Module - Excel Test Case Generator");
        System.out.println("===============================================");
        System.out.println();
        
        try {
            ExcelTestCaseGenerator generator = new ExcelTestCaseGenerator("Account Test Cases");
            
            // Create header
            generator.createHeader();
            System.out.println("✓ Header created");
            
            // Add test cases for Account module with sample data
            generator.addAccountLoginTestCase(true);
            System.out.println("✓ Login test case added");
            
            generator.addAccountNavigationTestCase(true);
            System.out.println("✓ Account navigation test case added");
            
            generator.addAccountCreationTestCase(true, "ACC987654", "CUST123456");
            System.out.println("✓ Account creation test case added");
            
            generator.addAccountSearchTestCase(true, "ACC987654");
            System.out.println("✓ Account search test case added");
            
            generator.addAccountEndToEndTestCase(true, "ACC987654", "CUST123456");
            System.out.println("✓ End-to-end account test case added");
            
            // Save to file in the project root directory
            String fileName = "Account_Test_Cases_" + 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".xlsx";
            generator.saveToFile(fileName);
            
            System.out.println();
            System.out.println("===============================================");
            System.out.println("SUCCESS!");
            System.out.println("===============================================");
            System.out.println("Excel file created: " + fileName);
            System.out.println("Location: " + System.getProperty("user.dir") + "\\" + fileName);
            System.out.println();
            System.out.println("You can now:");
            System.out.println("1. Open the Excel file to view test cases");
            System.out.println("2. Update pass/fail status after test execution");
            System.out.println("3. Share with your team for documentation");
            System.out.println("===============================================");
            
        } catch (IOException e) {
            System.err.println("ERROR: Failed to generate Excel file");
            System.err.println("Reason: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            System.err.println("ERROR: Unexpected error occurred");
            System.err.println("Reason: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
