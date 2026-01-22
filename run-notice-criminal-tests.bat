@echo off
REM Batch file to run Criminal Notice module tests only
echo ===============================================
echo Running Criminal Notice Module Tests
echo ===============================================
echo.

call mvn clean test "-Dcucumber.filter.tags=@NoticeCriminalManagement"

echo.
echo ===============================================
echo Criminal Notice Tests Execution Complete
echo ===============================================
pause
