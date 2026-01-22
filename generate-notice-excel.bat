@echo off
REM Batch file to generate Notice module Excel test cases
echo ===============================================
echo Generating Notice Module Excel Test Cases
echo ===============================================
echo.

mvn compile exec:java -Dexec.mainClass="com.example.GenerateExcelNoticeDetails"

echo.
pause
