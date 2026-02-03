@echo off
REM Batch file to run Customer Account Bulk Upload module tests only

echo Running Customer Account Bulk Upload Module Tests
echo ================================================
echo.

call mvn clean test -Dtest=CustomerAccountBulkUploadTestRunner

echo.
echo Customer Account Bulk Upload Tests Completed
pause
