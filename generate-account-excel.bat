@echo off
REM Batch file to generate Account module Excel test cases
echo ===============================================
echo Generating Account Module Excel Test Cases
echo ===============================================
echo.

mvn compile exec:java -Dexec.mainClass="com.example.GenerateAccountExcel"

echo.
pause
