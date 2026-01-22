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
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Utility class to generate Excel test cases for Criminal Notice Management
 * This creates 60 test case variations for comprehensive testing
 */
public class GenerateExcelNoticeCriminalDetails {

    public static void main(String[] args) {
        String fileName = "NoticeCriminal_TestCases_" + 
                         LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + 
                         ".xlsx";
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Criminal Notice Test Cases");
            
            // Create header style
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle dataStyle = createDataStyle(workbook);
            
            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {
                "Test Case ID", "Test Case Name", "Serial Number", "Notice Type", 
                "Customer Number", "Customer Account", "We Are", "Issuing Party",
                "Noticee", "Notice Reply", "Parties", "Sr Supervisor",
                "Expected Result", "Status", "Comments"
            };
            
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Generate 60 test cases
            int rowNum = 1;
            
            // Test Case 1-10: Different Notice Types
            String[] noticeTypes = {
                "Civil", "Civil (Advocate)", "Civil (Exclude)", "Civil Notice", 
                "Demand", "Legal Notice", "LRN", "MS", "New UI", "Notice111"
            };
            for (int i = 0; i < noticeTypes.length; i++) {
                Row row = sheet.createRow(rowNum++);
                createTestCase(row, dataStyle, 
                    "TC_NC_" + String.format("%03d", i + 1),
                    "Create criminal notice with " + noticeTypes[i] + " type",
                    "SN" + (10000 + i),
                    noticeTypes[i],
                    "CUST" + (1000 + i),
                    "ACC" + (2000 + i),
                    String.valueOf(50 + i),
                    "CUBICTREE",
                    "Party A",
                    "Sent",
                    "CT, NA",
                    "Ayushi G",
                    "Criminal notice created successfully",
                    "PASS",
                    "Validating " + noticeTypes[i] + " notice type"
                );
            }
            
            // Test Case 11-20: Different We Are values
            for (int i = 0; i < 10; i++) {
                Row row = sheet.createRow(rowNum++);
                createTestCase(row, dataStyle,
                    "TC_NC_" + String.format("%03d", i + 11),
                    "Criminal notice with We Are value " + ((i + 1) * 20),
                    "SN" + (20000 + i),
                    "Civil",
                    "CUST" + (3000 + i),
                    "ACC" + (4000 + i),
                    String.valueOf((i + 1) * 20),
                    "CAPRI",
                    "Party B",
                    "Received",
                    "CT",
                    "Ayushi G",
                    "Criminal notice created successfully",
                    "PASS",
                    "Testing We Are field with value " + ((i + 1) * 20)
                );
            }
            
            // Test Case 21-30: Different Issuing Parties
            String[] issuingParties = {
                "CUBICTREE", "CAPRI", "CUBICTREE", "CAPRI", "CUBICTREE",
                "CAPRI", "CUBICTREE", "CAPRI", "CUBICTREE", "CAPRI"
            };
            for (int i = 0; i < issuingParties.length; i++) {
                Row row = sheet.createRow(rowNum++);
                createTestCase(row, dataStyle,
                    "TC_NC_" + String.format("%03d", i + 21),
                    "Criminal notice with issuing party " + issuingParties[i],
                    "SN" + (30000 + i),
                    "Legal Notice",
                    "CUST" + (5000 + i),
                    "ACC" + (6000 + i),
                    String.valueOf(100 + i),
                    issuingParties[i],
                    "Noticee " + (i + 1),
                    "Sent",
                    "NA, Nayan",
                    "Ayushi G",
                    "Criminal notice created successfully",
                    "PASS",
                    "Validating issuing party: " + issuingParties[i]
                );
            }
            
            // Test Case 31-40: Different Notice Reply options
            String[] noticeReplies = {
                "Sent", "Received", "Sent", "Received", "Sent",
                "Received", "Sent", "Received", "Sent", "Received"
            };
            for (int i = 0; i < noticeReplies.length; i++) {
                Row row = sheet.createRow(rowNum++);
                createTestCase(row, dataStyle,
                    "TC_NC_" + String.format("%03d", i + 31),
                    "Criminal notice with reply status " + noticeReplies[i],
                    "SN" + (40000 + i),
                    "Demand",
                    "CUST" + (7000 + i),
                    "ACC" + (8000 + i),
                    String.valueOf(150 + i),
                    "CUBICTREE",
                    "Party C",
                    noticeReplies[i],
                    "CT, NA, Nayan",
                    "Ayushi G",
                    "Criminal notice created successfully",
                    "PASS",
                    "Testing notice reply: " + noticeReplies[i]
                );
            }
            
            // Test Case 41-50: Different Party combinations
            String[] partyCombinations = {
                "CT", "NA", "Nayan", "CT, NA", "CT, Nayan",
                "NA, Nayan", "CT, NA, Nayan", "CT", "NA", "Nayan"
            };
            for (int i = 0; i < partyCombinations.length; i++) {
                Row row = sheet.createRow(rowNum++);
                createTestCase(row, dataStyle,
                    "TC_NC_" + String.format("%03d", i + 41),
                    "Criminal notice with parties: " + partyCombinations[i],
                    "SN" + (50000 + i),
                    "LRN",
                    "CUST" + (9000 + i),
                    "ACC" + (10000 + i),
                    String.valueOf(75 + i),
                    "CAPRI",
                    "Party D",
                    "Sent",
                    partyCombinations[i],
                    "Ayushi G",
                    "Criminal notice created successfully",
                    "PASS",
                    "Validating party combination: " + partyCombinations[i]
                );
            }
            
            // Test Case 51-60: Comprehensive scenarios
            for (int i = 0; i < 10; i++) {
                Row row = sheet.createRow(rowNum++);
                createTestCase(row, dataStyle,
                    "TC_NC_" + String.format("%03d", i + 51),
                    "Criminal notice comprehensive test " + (i + 1),
                    "SN" + (60000 + i),
                    noticeTypes[i % noticeTypes.length],
                    "CUST" + (11000 + i),
                    "ACC" + (12000 + i),
                    String.valueOf((i + 1) * 15),
                    issuingParties[i % issuingParties.length],
                    "Comprehensive Noticee " + (i + 1),
                    noticeReplies[i % noticeReplies.length],
                    partyCombinations[i % partyCombinations.length],
                    "Ayushi G",
                    "Criminal notice created successfully",
                    "PASS",
                    "End-to-end test with mixed configurations"
                );
            }
            
            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            // Write to file
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
                System.out.println("✓✓✓ Excel file created successfully: " + fileName);
                System.out.println("Total test cases generated: 60");
            }
            
        } catch (IOException e) {
            System.err.println("Error creating Excel file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void createTestCase(Row row, CellStyle style, String id, String name,
                                      String serialNum, String noticeType, String custNum,
                                      String custAcc, String weAre, String issuingParty,
                                      String noticee, String noticeReply, String parties,
                                      String srSupervisor, String expectedResult, 
                                      String status, String comments) {
        int col = 0;
        createCell(row, col++, id, style);
        createCell(row, col++, name, style);
        createCell(row, col++, serialNum, style);
        createCell(row, col++, noticeType, style);
        createCell(row, col++, custNum, style);
        createCell(row, col++, custAcc, style);
        createCell(row, col++, weAre, style);
        createCell(row, col++, issuingParty, style);
        createCell(row, col++, noticee, style);
        createCell(row, col++, noticeReply, style);
        createCell(row, col++, parties, style);
        createCell(row, col++, srSupervisor, style);
        createCell(row, col++, expectedResult, style);
        createCell(row, col++, status, style);
        createCell(row, col++, comments, style);
    }
    
    private static void createCell(Row row, int column, String value, CellStyle style) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }
    
    private static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        Font font = workbook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        
        return style;
    }
    
    private static CellStyle createDataStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setWrapText(true);
        
        return style;
    }
}
