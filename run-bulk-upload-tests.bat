@echo off
REM Batch file to run Bulk Upload module tests only
echo ===============================================
echo Running Bulk Upload Module Tests
echo ===============================================
echo.

call mvn clean test -Dtest=BulkUploadTestRunner

echo.
echo ===============================================
echo Bulk Upload Tests Completed
echo ===============================================
pause
