@echo off
REM Quick Excel Generator Script for Bulk Upload Module
echo ============================================
echo Generating Bulk Upload Excel Test Case Document
echo ============================================
echo.

REM Compile if needed
echo Compiling project...
mvn compile -q

echo.
echo Generating Excel file for Bulk Upload...
mvn exec:java -Dexec.mainClass="com.example.GenerateBulkUploadExcelTestCases" -q

echo.
echo ============================================
echo Done! Check the project root directory
echo for BulkUpload_Test_Cases_*.xlsx
echo ============================================
pause
