package com.example;

import com.example.utils.ExcelTestCaseGenerator;

/**
 * Generate Excel Test Case Document for Reports Module
 */
public class GenerateReportsExcel {
    
    public static void main(String[] args) {
        try {
            System.out.println("Generating Reports Test Cases Excel...");
            
            ExcelTestCaseGenerator generator = new ExcelTestCaseGenerator("Reports Test Cases");
            generator.createHeader();
            
            // Add test cases
            generator.addReportsLoginTestCase(true);
            generator.addReportsNavigationTestCase(true);
            generator.addCaseReportsGenerationTestCase(true);
            generator.addTeamOwnershipReportTestCase(true);
            generator.addNoticeReportsGenerationTestCase(true);
            generator.addNoticeTeamOwnershipReportTestCase(true);
            generator.addCustomerReportSwitchTestCase(true);
            generator.addReportsEndToEndTestCase(true);
            
            // Save file
            String timestamp = java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String filePath = "Reports_Test_Cases_" + timestamp + ".xlsx";
            generator.saveToFile(filePath);
            
            System.out.println("✓ Reports Test Cases Excel generated successfully: " + filePath);
            
        } catch (Exception e) {
            System.err.println("Error generating Reports Excel: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
