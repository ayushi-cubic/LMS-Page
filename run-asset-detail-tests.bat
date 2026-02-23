@echo off
REM Batch file to run Asset Detail module tests only
echo ===============================================
echo Running Asset Detail Module Tests
echo ===============================================
echo.

call mvn clean test "-Dcucumber.filter.tags=@UpdateAssetDetails"

echo.
echo ===============================================
echo Asset Detail Tests Execution Complete
echo ===============================================
pause
