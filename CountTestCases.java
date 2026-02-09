import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CountTestCases {
    public static void main(String[] args) {
        try {
            String fileName = args.length > 0 ? args[0] : "Bulk_Upload_Test_Cases_20260206_124417.xlsx";
            FileInputStream fis = new FileInputStream(fileName);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            
            int testCaseCount = 0;
            System.out.println("\n=== Test Cases in Excel ===");
            System.out.println("File: " + fileName);
            System.out.println("");
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell cell = row.getCell(0);
                    if (cell != null) {
                        String testId = cell.getStringCellValue();
                        Cell descCell = row.getCell(1);
                        String desc = descCell != null ? descCell.getStringCellValue() : "";
                        
                        System.out.println("Row " + (i+1) + ": " + testId + " - " + desc);
                        testCaseCount++;
                    }
                }
            }
            
            System.out.println("\n=== Summary ===");
            System.out.println("Total Test Cases: " + testCaseCount);
            
            workbook.close();
            fis.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
