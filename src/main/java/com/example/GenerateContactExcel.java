package com.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.utils.ExcelTestCaseGenerator;

/**
 * Standalone class to generate Excel test case documentation for Contact module
 * Run this class directly to create an Excel file with contact test cases
 */
public class GenerateContactExcel {
    
    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("Contact Module - Excel Test Case Generator");
        System.out.println("===============================================");
        System.out.println();
        
        try {
            ExcelTestCaseGenerator generator = new ExcelTestCaseGenerator("Contact Test Cases");
            
            // Create header
            generator.createHeader();
            System.out.println("✓ Header created");
            
            // Add test cases for Contact module with sample data
            generator.addContactLoginTestCase(true);
            System.out.println("✓ Login test case added");
            
            generator.addContactNavigationTestCase(true);
            System.out.println("✓ Contact navigation test case added");
            
            generator.addFirmContactCreationTestCase(true, "Firm1234");
            System.out.println("✓ Firm contact creation test case added");
            
            generator.addIndividualContactCreationTestCase(true, "Advocate5678");
            System.out.println("✓ Individual contact creation test case added");
            
            generator.addEmployeeContactCreationTestCase(true, "Employee9012");
            System.out.println("✓ Employee contact creation test case added");
            
            generator.addOtherPartyContactCreationTestCase(true, "OtherParty3456");
            System.out.println("✓ Other Party contact creation test case added");
            
            generator.addEmployeeEditTestCase(true, "Employee9012", "EmpId7890");
            System.out.println("✓ Employee edit test case added");
            
            generator.addOtherPartyEditTestCase(true, "OtherParty3456", "Manager");
            System.out.println("✓ Other Party edit test case added");
            
            // Save to file in the project root directory
            String fileName = "Contact_Test_Cases_" + 
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
