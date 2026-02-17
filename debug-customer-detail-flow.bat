@echo off
echo ===================================
echo Debug Customer Detail Flow Test
echo ===================================
echo.
echo This will run the customer detail test with verbose output
echo Close all Chrome browsers before running
echo.
pause

REM Close any existing Chrome instances
taskkill /F /IM chrome.exe /T 2>nul
timeout /t 2 /nobreak >nul

REM Delete old reports
if exist "target\cucumber-reports" (
    echo Cleaning old reports...
    del /Q "target\cucumber-reports\*.*" 2>nul
    rmdir /S /Q "target\cucumber-reports" 2>nul
)

echo.
echo Running test with debug output...
echo.

REM Run the test
call mvn clean test -Dtest=CustomerDetailManagementTestRunner -X 2>&1 | findstr /I /C:"CustomerDetailPage" /C:"Error" /C:"Exception" /C:"BUILD"

echo.
echo ===================================
echo Test execution completed
echo ===================================
echo Check target\screenshots folder for screenshots
echo Check target\cucumber-reports for HTML report
echo.
pause
