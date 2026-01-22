package com.example;

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
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Generates Excel test case documentation for Notice Management
 */
public class GenerateExcelNoticeDetails {

    public static void main(String[] args) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "Notice_Management_Test_Cases_" + timestamp + ".xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Notice Management Test Cases");
            
            // Set column widths
            sheet.setColumnWidth(0, 1500);  // Sr No
            sheet.setColumnWidth(1, 8000);  // Test Step
            sheet.setColumnWidth(2, 12000); // Expected Result
            sheet.setColumnWidth(3, 15000); // XPath/Locator
            sheet.setColumnWidth(4, 3000);  // Status

            // Create styles
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle titleStyle = createTitleStyle(workbook);
            CellStyle dataStyle = createDataStyle(workbook);
            CellStyle sectionStyle = createSectionStyle(workbook);

            int rowNum = 0;

            // Title
            Row titleRow = sheet.createRow(rowNum++);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Notice Management - Civil Notice Creation Test Cases");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
            
            rowNum++; // Empty row

            // Header row
            Row headerRow = sheet.createRow(rowNum++);
            String[] headers = {"Sr No", "Test Step", "Expected Result", "XPath/Locator", "Status"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Test cases data
            Object[][] testCases = {
                {"SECTION", "Login to Application", "", "", ""},
                {1, "Open LMS Application", "Application should load successfully", "URL: [Application URL]", "Pass/Fail"},
                {2, "Enter Username", "Username entered successfully", "//*[@id='username']", "Pass/Fail"},
                {3, "Enter Password", "Password entered successfully", "//*[@id='password']", "Pass/Fail"},
                {4, "Click Login button", "OTP page should appear", "//*[@id='loginButton']", "Pass/Fail"},
                {5, "Enter OTP", "OTP entered successfully", "//*[@id='otp']", "Pass/Fail"},
                {6, "Click Submit OTP", "User logged in successfully and dashboard displayed", "//*[@id='submitOtp']", "Pass/Fail"},
                
                {"SECTION", "Navigate to Notice Section", "", "", ""},
                {7, "Click on Matters menu", "Matters menu should expand showing submenu options", "/html/body/div[2]/div/div/div/ul/li[3]/a", "Pass/Fail"},
                {8, "Click on Notice submenu", "Notice page should load with notice list", "/html/body/div[2]/div/div/div/ul/li[3]/ul/li[2]/a", "Pass/Fail"},
                {9, "Click Add New Notice button", "Add New Notice dropdown should appear", "/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div/div[1]/div[2]/div/div[3]/a", "Pass/Fail"},
                {10, "Click Civil option from dropdown", "New tab opens with Civil Notice creation form", "/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div/div[1]/div[2]/div/div[3]/ul/li[2]/a", "Pass/Fail"},
                
                {"SECTION", "Basic Information Section", "", "", ""},
                {11, "Enter Serial Number (Alphanumeric)", "Random alphanumeric serial number entered successfully", "//*[@id='SerialNumber']", "Pass/Fail"},
                {12, "Click and select Notice Type", "Notice type selected from dropdown", "//*[@id='NoticeTypeId']", "Pass/Fail"},
                {13, "Click Customer Number Select2 dropdown", "Dropdown opens showing customer list", "//*[@id='flush-collapse1']/div/div[5]/div[5]/div/span[1]/span[1]/span", "Pass/Fail"},
                {14, "Search and select Customer Number", "Customer selected successfully", "Input: class='select2-search__field' with placeholder='Select Customer'", "Pass/Fail"},
                {15, "Click Customer Account Select2 dropdown", "Dropdown opens showing account list for selected customer", "//*[@id='flush-collapse1']/div/div[5]/div[6]/div/span[1]/span[1]/span/ul/li/input", "Pass/Fail"},
                {16, "Search and select Customer Account", "Customer account selected successfully", "Input: class='select2-search__field' with placeholder='Select LAN'", "Pass/Fail"},
                {17, "Enter We Are value (1-200)", "Numeric value between 1-200 entered successfully", "//*[@id='WeAre']", "Pass/Fail"},
                {18, "Click and select Issuing Party", "Issuing party dropdown opens", "//*[@id='byRoleSection']/div/div[1]/div/div[1]", "Pass/Fail"},
                {19, "Select Issuing Party option", "Issuing party selected from dropdown menu", "Dropdown ID: businessunitPetResDropdown", "Pass/Fail"},
                {20, "Click and select Noticee", "Noticee dropdown opens", "//*[@id='againstRoleSection']/div/div[1]/div/div[1]", "Pass/Fail"},
                {21, "Select Noticee option", "Noticee selected from dropdown menu", "Dropdown ID: NoticeePetResDropdown", "Pass/Fail"},
                {22, "Click and select Notice Reply-Response", "Notice Reply-Response selected from dropdown", "//*[@id='NoticeSentReceive']", "Pass/Fail"},
                {23, "Click and select Parties", "Parties dropdown opens", "Parent element of partyDropdown", "Pass/Fail"},
                {24, "Select Party option", "Party selected from dropdown menu", "Dropdown ID: partyDropdown", "Pass/Fail"},
                {25, "Click Next button (Basic Information)", "Moves to Stake Details section", "//*[@id='BasicInformationCollapse']", "Pass/Fail"},
                
                {"SECTION", "Stake Details Section", "", "", ""},
                {26, "Review Stake Details section", "Stake details displayed correctly", "Section: Stake Details", "Pass/Fail"},
                {27, "Click Next button (Stake Details)", "Moves to PDO Notice section", "//*[@id='StakeDetailsCollapse']", "Pass/Fail"},
                
                {"SECTION", "PDO Notice Section - Supervisor Selection", "", "", ""},
                {28, "Click Sr Supervisor checkbox", "Sr Supervisor checkbox selected", "//*[@id='4']", "Pass/Fail"},
                {29, "Verify Sr Supervisor selection", "Sr Supervisor checkbox is checked", "//*[@id='4']", "Pass/Fail"},
                {30, "Click Next button (PDO Notice)", "Moves to final review/submission section", "//*[@id='PDONoticeCollapse']", "Pass/Fail"},
                
                {"SECTION", "Notice Submission", "", "", ""},
                {31, "Review all entered information", "All fields contain correct data", "Review all sections", "Pass/Fail"},
                {32, "Click Create button", "Notice creation process initiated", "//*[@id='btnSubmit']", "Pass/Fail"},
                {33, "Verify notice creation success", "Success message displayed, notice created successfully", "Success notification or page redirect", "Pass/Fail"},
                {34, "Verify notice saved in system", "Notice appears in notice list", "Notice list page", "Pass/Fail"},
                
                {"SECTION", "Post-Creation Verification", "", "", ""},
                {35, "Navigate to Notices list", "Notices list displayed", "Notices list page", "Pass/Fail"},
                {36, "Search for created notice by Serial Number", "Created notice found in list", "Search functionality", "Pass/Fail"},
                {37, "Verify Serial Number", "Serial Number matches entered value", "Notice details", "Pass/Fail"},
                {38, "Verify Notice Type", "Notice Type matches selected value", "Notice details", "Pass/Fail"},
                {39, "Verify Customer Number", "Customer Number matches selected value", "Notice details", "Pass/Fail"},
                {40, "Verify Customer Account", "Customer Account matches selected value", "Notice details", "Pass/Fail"},
                {41, "Verify We Are value", "We Are value matches entered value", "Notice details", "Pass/Fail"},
                {42, "Verify Issuing Party", "Issuing Party matches selected value", "Notice details", "Pass/Fail"},
                {43, "Verify Noticee", "Noticee matches selected value", "Notice details", "Pass/Fail"},
                {44, "Verify Notice Reply-Response", "Notice Reply-Response matches selected value", "Notice details", "Pass/Fail"},
                {45, "Verify Parties", "Parties matches selected value", "Notice details", "Pass/Fail"},
                {46, "Verify Sr Supervisor assignment", "Sr Supervisor correctly assigned", "Notice details", "Pass/Fail"},
                
                {"SECTION", "Negative Test Cases", "", "", ""},
                {47, "Submit form with empty Serial Number", "Validation error displayed for Serial Number", "//*[@id='SerialNumber']", "Pass/Fail"},
                {48, "Submit form without selecting Notice Type", "Validation error displayed for Notice Type", "//*[@id='NoticeTypeId']", "Pass/Fail"},
                {49, "Enter We Are value > 200", "Validation error: value must be between 1-200", "//*[@id='WeAre']", "Pass/Fail"},
                {50, "Enter We Are value < 1", "Validation error: value must be between 1-200", "//*[@id='WeAre']", "Pass/Fail"},
                {51, "Submit without selecting Customer Number", "Validation error displayed for Customer Number", "Customer Number field", "Pass/Fail"},
                {52, "Submit without selecting Customer Account", "Validation error displayed for Customer Account", "Customer Account field", "Pass/Fail"},
                
                {"SECTION", "Edge Cases and Additional Scenarios", "", "", ""},
                {53, "Enter maximum length Serial Number", "Serial Number accepted with maximum length", "//*[@id='SerialNumber']", "Pass/Fail"},
                {54, "Enter special characters in Serial Number", "Only valid alphanumeric characters accepted", "//*[@id='SerialNumber']", "Pass/Fail"},
                {55, "Click Back/Cancel during creation", "Confirmation dialog appears for unsaved changes", "Browser back or cancel button", "Pass/Fail"},
                {56, "Create notice with minimum required fields", "Notice created successfully with mandatory fields only", "All mandatory fields", "Pass/Fail"},
                {57, "Verify notice creation timestamp", "Correct creation date and time recorded", "Notice metadata", "Pass/Fail"},
                {58, "Verify user who created notice", "Logged-in user recorded as creator", "Notice metadata", "Pass/Fail"},
                {59, "Test concurrent notice creation", "Multiple notices can be created simultaneously", "Multiple browser sessions", "Pass/Fail"},
                {60, "Close browser tab during creation", "Notice saved as draft or discarded appropriately", "Browser behavior", "Pass/Fail"}
            };

            // Add test case rows
            for (Object[] testCase : testCases) {
                Row row = sheet.createRow(rowNum++);
                
                if (testCase[0].equals("SECTION")) {
                    // Section header
                    Cell cell = row.createCell(0);
                    cell.setCellValue((String) testCase[1]);
                    cell.setCellStyle(sectionStyle);
                    sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 4));
                } else {
                    // Regular test case
                    for (int i = 0; i < testCase.length; i++) {
                        Cell cell = row.createCell(i);
                        if (testCase[i] instanceof Integer) {
                            cell.setCellValue((Integer) testCase[i]);
                        } else {
                            cell.setCellValue((String) testCase[i]);
                        }
                        cell.setCellStyle(dataStyle);
                    }
                }
            }

            // Add summary section
            rowNum++;
            Row summaryHeaderRow = sheet.createRow(rowNum++);
            Cell summaryHeaderCell = summaryHeaderRow.createCell(0);
            summaryHeaderCell.setCellValue("Test Execution Summary");
            summaryHeaderCell.setCellStyle(sectionStyle);
            sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 4));
            
            String[][] summaryFields = {
                {"Total Test Cases:", "60"},
                {"Passed:", ""},
                {"Failed:", ""},
                {"Not Executed:", ""},
                {"Test Execution Date:", ""},
                {"Tester Name:", ""},
                {"Environment:", ""},
                {"Build Version:", ""},
                {"Remarks:", ""}
            };
            
            for (String[] field : summaryFields) {
                Row row = sheet.createRow(rowNum++);
                Cell labelCell = row.createCell(0);
                labelCell.setCellValue(field[0]);
                labelCell.setCellStyle(dataStyle);
                
                Cell valueCell = row.createCell(1);
                valueCell.setCellValue(field[1]);
                valueCell.setCellStyle(dataStyle);
                sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 1, 4));
            }

            // Write to file
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
            }

            System.out.println("✓ Excel file generated successfully: " + fileName);
            System.out.println("✓ Total test cases: 60");
            System.out.println("✓ File location: " + new java.io.File(fileName).getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error generating Excel file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    private static CellStyle createTitleStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        font.setColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    private static CellStyle createDataStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setWrapText(true);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        return style;
    }

    private static CellStyle createSectionStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 11);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.DARK_GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }
}
