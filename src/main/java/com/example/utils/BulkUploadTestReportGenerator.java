package com.example.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Generates comprehensive test reports for bulk upload modules
 */
public class BulkUploadTestReportGenerator {
    
    private String reportFilePath;
    private Workbook workbook;
    private Sheet sheet;
    private int currentRow = 0;
    private Map<String, TestResult> testResults;
    
    // Cell styles
    private CellStyle headerStyle;
    private CellStyle passStyle;
    private CellStyle failStyle;
    private CellStyle infoStyle;
    
    public static class TestResult {
        public String testName;
        public String status; // PASS, FAIL, SKIPPED
        public String module; // Customer, Asset, Account
        public String templateDownloaded;
        public int fieldsMatched;
        public int totalFields;
        public String dataEntered;
        public String uploadResult;
        public String errorMessage;
        public long executionTime; // in milliseconds
        
        public TestResult(String testName, String module) {
            this.testName = testName;
            this.module = module;
            this.status = "PENDING";
        }
    }
    
    public BulkUploadTestReportGenerator() {
        this.testResults = new LinkedHashMap<>();
        initializeWorkbook();
    }
    
    private void initializeWorkbook() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Bulk Upload Test Report");
        
        // Create styles
        createStyles();
        
        // Set column widths
        sheet.setColumnWidth(0, 1000);  // S.No
        sheet.setColumnWidth(1, 8000);  // Test Name
        sheet.setColumnWidth(2, 4000);  // Module
        sheet.setColumnWidth(3, 3000);  // Status
        sheet.setColumnWidth(4, 6000);  // Template Downloaded
        sheet.setColumnWidth(5, 4000);  // Fields Matched
        sheet.setColumnWidth(6, 8000);  // Data Summary
        sheet.setColumnWidth(7, 4000);  // Upload Result
        sheet.setColumnWidth(8, 3500);  // Execution Time
        sheet.setColumnWidth(9, 10000); // Error/Comments
    }
    
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
        
        // Fail style
        failStyle = workbook.createCellStyle();
        Font failFont = workbook.createFont();
        failFont.setBold(true);
        failFont.setColor(IndexedColors.RED.getIndex());
        failStyle.setFont(failFont);
        failStyle.setAlignment(HorizontalAlignment.CENTER);
        
        // Info style
        infoStyle = workbook.createCellStyle();
        infoStyle.setWrapText(true);
        infoStyle.setVerticalAlignment(VerticalAlignment.TOP);
    }
    
    public void addTestResult(TestResult result) {
        testResults.put(result.testName, result);
    }
    
    public TestResult getOrCreateTestResult(String testName, String module) {
        return testResults.computeIfAbsent(testName, k -> new TestResult(testName, module));
    }
    
    public void generateReport() {
        // Create header
        createHeader();
        
        // Add test results
        int serialNo = 1;
        for (TestResult result : testResults.values()) {
            addTestResultRow(serialNo++, result);
        }
        
        // Add summary section
        addSummary();
        
        // Save to file
        saveReport();
    }
    
    private void createHeader() {
        // Title row
        Row titleRow = sheet.createRow(currentRow++);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("BULK UPLOAD TEST EXECUTION REPORT");
        titleCell.setCellStyle(headerStyle);
        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 9));
        
        // Date row
        Row dateRow = sheet.createRow(currentRow++);
        Cell dateCell = dateRow.createCell(0);
        dateCell.setCellValue("Report Generated: " + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date()));
        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, 1, 0, 9));
        
        // Empty row
        currentRow++;
        
        // Column headers
        Row headerRow = sheet.createRow(currentRow++);
        String[] headers = {
            "S.No", "Test Name", "Module", "Status", "Template Downloaded", 
            "Fields Matched", "Data Summary", "Upload Result", "Time (s)", "Comments"
        };
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
    }
    
    private void addTestResultRow(int serialNo, TestResult result) {
        Row row = sheet.createRow(currentRow++);
        
        // S.No
        row.createCell(0).setCellValue(serialNo);
        
        // Test Name
        Cell testNameCell = row.createCell(1);
        testNameCell.setCellValue(result.testName);
        testNameCell.setCellStyle(infoStyle);
        
        // Module
        Cell moduleCell = row.createCell(2);
        moduleCell.setCellValue(result.module);
        moduleCell.setCellStyle(infoStyle);
        
        // Status
        Cell statusCell = row.createCell(3);
        statusCell.setCellValue(result.status);
        if ("PASS".equals(result.status)) {
            statusCell.setCellStyle(passStyle);
        } else if ("FAIL".equals(result.status)) {
            statusCell.setCellStyle(failStyle);
        }
        
        // Template Downloaded
        Cell templateCell = row.createCell(4);
        templateCell.setCellValue(result.templateDownloaded != null ? result.templateDownloaded : "N/A");
        templateCell.setCellStyle(infoStyle);
        
        // Fields Matched
        Cell fieldsCell = row.createCell(5);
        if (result.totalFields > 0) {
            fieldsCell.setCellValue(result.fieldsMatched + " / " + result.totalFields);
        } else {
            fieldsCell.setCellValue("N/A");
        }
        fieldsCell.setCellStyle(infoStyle);
        
        // Data Summary
        Cell dataCell = row.createCell(6);
        dataCell.setCellValue(result.dataEntered != null ? result.dataEntered : "N/A");
        dataCell.setCellStyle(infoStyle);
        
        // Upload Result
        Cell uploadCell = row.createCell(7);
        uploadCell.setCellValue(result.uploadResult != null ? result.uploadResult : "N/A");
        uploadCell.setCellStyle(infoStyle);
        
        // Execution Time
        Cell timeCell = row.createCell(8);
        if (result.executionTime > 0) {
            timeCell.setCellValue(String.format("%.2f", result.executionTime / 1000.0));
        } else {
            timeCell.setCellValue("N/A");
        }
        
        // Comments
        Cell commentsCell = row.createCell(9);
        commentsCell.setCellValue(result.errorMessage != null ? result.errorMessage : "Test completed successfully");
        commentsCell.setCellStyle(infoStyle);
    }
    
    private void addSummary() {
        // Empty rows
        currentRow += 2;
        
        // Summary header
        Row summaryHeaderRow = sheet.createRow(currentRow++);
        Cell summaryHeaderCell = summaryHeaderRow.createCell(0);
        summaryHeaderCell.setCellValue("TEST EXECUTION SUMMARY");
        summaryHeaderCell.setCellStyle(headerStyle);
        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(currentRow - 1, currentRow - 1, 0, 9));
        
        // Count results
        long totalTests = testResults.size();
        long passedTests = testResults.values().stream().filter(r -> "PASS".equals(r.status)).count();
        long failedTests = testResults.values().stream().filter(r -> "FAIL".equals(r.status)).count();
        long skippedTests = testResults.values().stream().filter(r -> "SKIPPED".equals(r.status)).count();
        
        // Total tests
        Row totalRow = sheet.createRow(currentRow++);
        totalRow.createCell(0).setCellValue("Total Tests:");
        totalRow.createCell(1).setCellValue(totalTests);
        
        // Passed tests
        Row passedRow = sheet.createRow(currentRow++);
        Cell passedLabel = passedRow.createCell(0);
        passedLabel.setCellValue("Passed:");
        Cell passedValue = passedRow.createCell(1);
        passedValue.setCellValue(passedTests);
        passedValue.setCellStyle(passStyle);
        
        // Failed tests
        Row failedRow = sheet.createRow(currentRow++);
        Cell failedLabel = failedRow.createCell(0);
        failedLabel.setCellValue("Failed:");
        Cell failedValue = failedRow.createCell(1);
        failedValue.setCellValue(failedTests);
        failedValue.setCellStyle(failStyle);
        
        // Skipped tests
        Row skippedRow = sheet.createRow(currentRow++);
        skippedRow.createCell(0).setCellValue("Skipped:");
        skippedRow.createCell(1).setCellValue(skippedTests);
        
        // Pass percentage
        Row passPercentRow = sheet.createRow(currentRow++);
        passPercentRow.createCell(0).setCellValue("Pass Percentage:");
        double passPercent = totalTests > 0 ? (passedTests * 100.0 / totalTests) : 0;
        Cell percentCell = passPercentRow.createCell(1);
        percentCell.setCellValue(String.format("%.2f%%", passPercent));
        if (passPercent >= 80) {
            percentCell.setCellStyle(passStyle);
        } else if (passPercent < 50) {
            percentCell.setCellStyle(failStyle);
        }
    }
    
    private void saveReport() {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            reportFilePath = "Bulk_Upload_Test_Report_" + timestamp + ".xlsx";
            
            FileOutputStream fileOut = new FileOutputStream(reportFilePath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("✓ Test Report Generated Successfully!");
            System.out.println("  Location: " + new File(reportFilePath).getAbsolutePath());
            System.out.println("=".repeat(60) + "\n");
            
        } catch (Exception e) {
            System.err.println("Error saving test report: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public String getReportFilePath() {
        return reportFilePath;
    }
}
