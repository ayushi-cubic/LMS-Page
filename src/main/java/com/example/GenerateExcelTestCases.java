package com.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.utils.ExcelTestCaseGenerator;

/**
 * Standalone class to generate Excel test case documentation
 * Run this class directly to create an Excel file with test cases
 */
public class GenerateExcelTestCases {
    
    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("Excel Test Case Generator");
        System.out.println("===============================================");
        System.out.println();
        
        try {
            ExcelTestCaseGenerator generator = new ExcelTestCaseGenerator();
            
            // Create header
            generator.createHeader();
            System.out.println("✓ Header created");
            
            // Add test cases with sample data
            generator.addLoginTestCase(true);
            System.out.println("✓ Login test case added");
            
            generator.addCustomerNavigationTestCase(true);
            System.out.println("✓ Navigation test case added");
            
            generator.addCustomerCreationTestCase(true, "CUST123456", "John Doe");
            System.out.println("✓ Customer creation test case added");
            
            generator.addCustomerSearchTestCase(true, "CUST123456");
            System.out.println("✓ Customer search test case added");
            
            generator.addEndToEndTestCase(true, "CUST123456", "John Doe");
            System.out.println("✓ End-to-end test case added");
            
            // Save to file in the project root directory
            String fileName = "Customer_Search_Test_Cases_" + 
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
