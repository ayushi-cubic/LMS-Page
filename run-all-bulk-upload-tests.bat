@echo off
echo ================================================
echo Running All Bulk Upload Tests
echo ================================================
echo.

REM Clean up old downloads (optional)
echo Cleaning old template files from downloads folder...
if exist "downloads\Customer*.xlsx" (
    echo Deleting old Customer templates...
    del /Q "downloads\Customer*.xlsx" 2>nul
)
if exist "downloads\*Security*.xlsx" (
    echo Deleting old Security templates...
    del /Q "downloads\*Security*.xlsx" 2>nul
)
if exist "downloads\*Account*.xlsx" (
    echo Deleting old Account templates...
    del /Q "downloads\*Account*.xlsx" 2>nul
)
echo.

echo ================================================
echo Test 1/3: Customer Bulk Upload
echo ================================================
call mvn clean test -Dtest=CustomerBulkUploadTestRunner
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Customer Bulk Upload test failed!
) else (
    echo [SUCCESS] Customer Bulk Upload test passed!
)
echo.

echo ================================================
echo Test 2/3: Customer Asset Bulk Upload
echo ================================================
call mvn test -Dtest=CustomerAssetBulkUploadTestRunner
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Customer Asset Bulk Upload test failed!
) else (
    echo [SUCCESS] Customer Asset Bulk Upload test passed!
)
echo.

echo ================================================
echo Test 3/3: Customer Account Bulk Upload
echo ================================================
call mvn test -Dtest=CustomerAccountBulkUploadTestRunner
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Customer Account Bulk Upload test failed!
) else (
    echo [SUCCESS] Customer Account Bulk Upload test passed!
)
echo.

echo ================================================
echo All Bulk Upload Tests Completed!
echo ================================================
echo.
echo Check the following for results:
echo   - Test Reports: Bulk_Upload_Test_Report_*.xlsx
echo   - Screenshots: target/screenshots_*
echo   - Maven Results: target/surefire-reports/
echo   - Downloaded Templates: downloads/
echo.

pause
