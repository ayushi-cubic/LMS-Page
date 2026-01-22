@echo off
REM Batch file to run Customer module tests only
echo ===============================================
echo Running Customer Module Tests
echo ===============================================
echo.

call mvn clean test "-Dcucumber.filter.tags=@CustomerManagement"

echo.
echo ===============================================
echo Customer Tests Execution Complete
echo ===============================================
pause
