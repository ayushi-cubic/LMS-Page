package com.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Generates Excel test case documentation for Account Details Verification
 */
public class GenerateExcelAccountDetails {

    public static void main(String[] args) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "Account_Details_Verification_" + timestamp + ".xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Account Details Test Cases");
            
            // Set column widths
            sheet.setColumnWidth(0, 1500);  // Sr No
            sheet.setColumnWidth(1, 8000);  // Test Step
            sheet.setColumnWidth(2, 12000); // Expected Result
            sheet.setColumnWidth(3, 10000); // XPath
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
            titleCell.setCellValue("Account Details Verification Test Cases");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
            
            rowNum++; // Empty row

            // Header row
            Row headerRow = sheet.createRow(rowNum++);
            String[] headers = {"Sr No", "Test Step", "Expected Result", "XPath", "Status"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Test cases data
            Object[][] testCases = {
                {"SECTION", "Search and Navigate to Account Details", "", "", ""},
                {1, "Click Action button", "Action dropdown menu should open", "//*[@id='accountTableBody']/tr/td[11]/div/a/I", "Pass/Fail"},
                {2, "Click Details button", "Account details page opens in new tab", "//*[@id='accountTableBody']/tr/td[11]/div/ul/li[2]/a", "Pass/Fail"},
                
                {"SECTION", "Basic Information", "", "", ""},
                {3, "Click Edit button", "Edit form should open", "//*[@id='flush-collapse14']/div/div/div/div[1]/a", "Pass/Fail"},
                {4, "Enter Business Date (DD-MMM-YYYY)", "Date entered successfully, must be after loan disbursal date", "//*[@id='EditBusinessDate']", "Pass/Fail"},
                {5, "Click Save button", "Basic information saved successfully", "//*[@id='SubmitBasicEditDetails']", "Pass/Fail"},
                
                {"SECTION", "Bad Debt Section", "", "", ""},
                {6, "Click Bad Debt section", "Bad Debt section should expand", "//*[@id='accordionFlushExample']/div[2]/div[1]/div", "Pass/Fail"},
                {7, "Click Edit button", "Edit form should open", "//*[@id='CustomerBadDepdDetailsSection']/div/div/div[1]/a", "Pass/Fail"},
                {8, "Click Save button", "Bad debt details saved successfully", "//*[@id='submitButtonBadDebt']", "Pass/Fail"},
                
                {"SECTION", "Account Details Section", "", "", ""},
                {9, "Click Account Details section", "Account Details section should expand", "//*[@id='accordionFlushExample']/div[3]/div[1]/div", "Pass/Fail"},
                {10, "Click Edit button", "Edit form should open", "//*[@id='CustomerAccountCustomerAccountDetailsSection']/div/div/div[1]/a", "Pass/Fail"},
                {11, "Enter Principal Outstanding Amount", "Numeric value entered successfully", "//*[@id='PrincipalOustandingAmountOnEdit']", "Pass/Fail"},
                {12, "Click Save button", "Account details saved successfully", "//*[@id='SubmitOnlyAccountEditDetails']", "Pass/Fail"},
                
                {"SECTION", "Foreclosure Details", "", "", ""},
                {13, "Click Account Details section", "Section expanded", "//*[@id='accordionFlushExample']/div[3]/div[1]/div", "Pass/Fail"},
                {14, "Click Foreclosure Details tab", "Foreclosure tab should open", "//*[@id='lForeclosure-tab']", "Pass/Fail"},
                {15, "Click Edit button", "Edit form should open", "//*[@id='CustomerAccountForeclosureDetailsSection']/div/div/div[1]", "Pass/Fail"},
                {16, "Click Save button", "Foreclosure details saved successfully", "//*[@id='facebox']/div/table/tbody/tr[2]/td[2]/div/form/div[2]/button[1]", "Pass/Fail"},
                
                {"SECTION", "Conciliation Details", "", "", ""},
                {17, "Click Account Details section", "Section expanded", "//*[@id='accordionFlushExample']/div[3]/div[1]/div", "Pass/Fail"},
                {18, "Click Conciliation Details tab", "Conciliation tab should open", "//*[@id='lConcilation-tab']", "Pass/Fail"},
                {19, "Click Edit button", "Edit form should open", "//*[@id='CustomerAccountConcilationDetailsList']/div/div/div[1]/a", "Pass/Fail"},
                {20, "Enter OD value", "Numeric value entered successfully", "//*[@id='EditCustomerAccountOD']", "Pass/Fail"},
                {21, "Click Save button", "Conciliation details saved successfully", "//*[@id='submitconsillationButton']", "Pass/Fail"},
                
                {"SECTION", "Present Status", "", "", ""},
                {22, "Click Present Status section", "Present Status section should expand", "//*[@id='accordionFlushExample']/div[4]/div[1]/div", "Pass/Fail"},
                {23, "Click Edit button", "Edit form should open", "//*[@id='CustomerAccountPrasentStatusSection']/div/div/div[2]/table/tbody/tr/td[5]/a", "Pass/Fail"},
                {24, "Click Cancel button", "Edit form should close", "//*[@id='facebox']/div/table/tbody/tr[2]/td[2]/div/form/div[2]/button[2]", "Pass/Fail"},
                
                {"SECTION", "Recovery Summary", "", "", ""},
                {25, "Click Recovery Summary section", "Recovery Summary section should expand", "//*[@id='accordionFlushExample']/div[5]/div[1]/div", "Pass/Fail"},
                
                {"SECTION", "Applicant Section", "", "", ""},
                {26, "Click Applicant section", "Applicant section should expand", "//*[@id='accordionFlushExample']/div[6]/div[1]/div", "Pass/Fail"},
                {27, "Click Plus button", "Add applicant form should open", "//*[@id='flush-collapse5']/div/div/div/div[1]/div[2]/a[1]", "Pass/Fail"},
                {28, "Click Cancel button", "Form should close", "//*[@id='facebox']/div/table/tbody/tr[2]/td[2]/div/form/div[2]/button[2]", "Pass/Fail"},
                
                {"SECTION", "Address Section", "", "", ""},
                {29, "Click Address section", "Address section should expand", "//*[@id='flush-collapse6']/div/div/div/div[1]", "Pass/Fail"},
                {30, "Click Plus button", "Add address form should open", "//*[@id='flush-collapse6']/div/div/div/div[1]/div[2]/a", "Pass/Fail"},
                {31, "Click Cancel button", "Form should close", "//*[@id='facebox']/div/table/tbody/tr[2]/td[2]/div/form/div[2]/button[2]", "Pass/Fail"},
                
                {"SECTION", "Information Request", "", "", ""},
                {32, "Click Information Request section", "Information Request section should expand", "//*[@id='accordionFlushExample']/div[8]/div[1]/div", "Pass/Fail"},
                {33, "Click Cancel button", "Request cancelled", "//*[@id='create-informationrequest']/div/div[4]/button", "Pass/Fail"},
                
                {"SECTION", "Bank Consortium Details", "", "", ""},
                {34, "Click Bank Consortium Details section", "Bank Consortium section should expand", "//*[@id='accordionFlushExample']/div[9]/div[1]/div", "Pass/Fail"},
                {35, "Click Plus button", "Add consortium form should open", "//*[@id='flush-collapse8']/div/div/div/div[1]/a", "Pass/Fail"},
                {36, "Click Save button", "Consortium details saved successfully", "//*[@id='facebox']/div/table/tbody/tr[2]/td[2]/div/form/div[2]/button[1]", "Pass/Fail"},
                
                {"SECTION", "Documents Section", "", "", ""},
                {37, "Click Documents section", "Documents section should expand", "//*[@id='accordionFlushExample']/div[10]/div[1]/div", "Pass/Fail"},
                {38, "Click Legal Document tab", "Legal Document tab should open", "//*[@id='CustomerAccountLegelDocument-tab']", "Pass/Fail"},
                {39, "Click Plus button", "Add document form should open", "//*[@id='AccountLegelDocumentUniqueId']/div/div[1]/div[2]/a", "Pass/Fail"},
                {40, "Click Cancel button", "Form should close", "//*[@id='new-document']/div[2]/button", "Pass/Fail"},
                
                {"SECTION", "Instrument Details", "", "", ""},
                {41, "Click Instrument Details section", "Instrument Details section should expand", "//*[@id='accordionFlushExample']/div[11]/div[1]/div", "Pass/Fail"},
                {42, "Click Plus button", "Add instrument form should open", "//*[@id='flush-collapse11']/div/div/div/div[1]/a", "Pass/Fail"},
                {43, "Click Cancel button", "Form should close", "//*[@id='facebox']/div/table/tbody/tr[2]/td[2]/div/form/div[2]/button[2]", "Pass/Fail"},
                
                {"SECTION", "Linked Case, Notice, Asset", "", "", ""},
                {44, "Click Linked Case, Notice, Asset section", "Section should expand", "//*[@id='accordionFlushExample']/div[12]/div[1]/div", "Pass/Fail"},
                {45, "Click Linked Cases tab", "Linked Cases tab should open", "//*[@id='CustomerAccountCaseLink-tab']", "Pass/Fail"},
                {46, "Click Linked Notices tab", "Linked Notices tab should open", "//*[@id='CustomerAccountNoticeLink-tab']", "Pass/Fail"},
                
                {"SECTION", "Log Section", "", "", ""},
                {47, "Click Log section", "Log section should expand", "//*[@id='accordionFlushExample']/div[13]/div[1]/div", "Pass/Fail"},
                {48, "Click Audit Trail tab", "Audit Trail tab should open", "//*[@id='lAudittrail-tab']", "Pass/Fail"},
                
                {"SECTION", "Navigation and Cleanup", "", "", ""},
                {49, "Click Back button", "Navigated back to account list", "/html/body/div[2]/div/main/div/div[1]/a", "Pass/Fail"},
                {50, "Click Load Customer Account button", "Account list refreshed", "//*[@id='LoadCustomerAccounts']", "Pass/Fail"},
                {51, "Click Action button", "Action dropdown menu should open", "//*[@id='accountTableBody']/tr[1]/td[11]/div/a", "Pass/Fail"},
                {52, "Click Delete button", "Account deleted successfully", "//*[@id='accountTableBody']/tr[1]/td[11]/div/ul/li[4]/a", "Pass/Fail"}
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

            // Write to file
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
            }

            System.out.println("Excel file generated successfully: " + fileName);

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
