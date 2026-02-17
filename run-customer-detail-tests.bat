@echo off
REM Batch file to run Customer Detail module tests only
echo ===============================================
echo Running Customer Detail Module Tests
echo ===============================================
echo.

call mvn clean test "-Dcucumber.filter.tags=@CustomerDetailManagement"

echo.
echo ===============================================
echo Customer Detail Tests Execution Complete
echo ===============================================
pause
