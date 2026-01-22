@echo off
REM Batch file to run Account module tests only
echo ===============================================
echo Running Account Module Tests
echo ===============================================
echo.

call mvn clean test "-Dcucumber.filter.tags=@AccountManagement"

echo.
echo ===============================================
echo Account Tests Execution Complete
echo ===============================================
pause
