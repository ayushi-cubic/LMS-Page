@echo off
REM Quick Excel Generator Script
echo ============================================
echo Generating Excel Test Case Document
echo ============================================
echo.

REM Compile if needed
echo Compiling project...
mvn compile -q

echo.
echo Generating Excel file...
mvn exec:java -Dexec.mainClass="com.example.GenerateExcelTestCases" -q

echo.
echo ============================================
echo Done! Check the project root directory
echo for Customer_Search_Test_Cases_*.xlsx
echo ============================================
pause
