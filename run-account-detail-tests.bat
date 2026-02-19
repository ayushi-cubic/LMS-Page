@echo off
REM Batch file to run Account Detail module tests only
echo ===============================================
echo Running Account Detail Module Tests
echo ===============================================
echo.

call mvn clean test "-Dcucumber.filter.tags=@AccountDetailManagement"

echo.
echo ===============================================
echo Account Detail Tests Execution Complete
echo ===============================================
pause
