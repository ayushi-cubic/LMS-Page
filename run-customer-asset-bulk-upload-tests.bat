@echo off
REM Batch file to run Customer Asset Bulk Upload module tests only

echo Running Customer Asset Bulk Upload Module Tests
echo ================================================
echo.

call mvn clean test -Dtest=CustomerAssetBulkUploadTestRunner

echo.
echo Customer Asset Bulk Upload Tests Completed
pause
