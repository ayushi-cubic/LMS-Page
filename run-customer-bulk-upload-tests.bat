@echo off
REM Batch file to run Customer Bulk Upload module tests only
echo ===============================================
echo Running Customer Bulk Upload Module Tests
echo ===============================================
echo.

call mvn clean test -Dtest=CustomerBulkUploadTestRunner

echo.
echo ===============================================
echo Customer Bulk Upload Tests Completed
echo ===============================================
pause
