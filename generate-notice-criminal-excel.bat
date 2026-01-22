@echo off
REM Batch file to generate Excel test cases for Criminal Notice module
echo ===============================================
echo Generating Criminal Notice Excel Test Cases
echo ===============================================
echo.

call mvn exec:java -Dexec.mainClass="com.example.GenerateExcelNoticeCriminalDetails"

echo.
echo ===============================================
echo Excel Generation Complete
echo ===============================================
pause
