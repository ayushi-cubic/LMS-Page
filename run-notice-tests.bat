@echo off
REM Batch file to run Notice module tests only
echo ===============================================
echo Running Notice Module Tests
echo ===============================================
echo.

call mvn clean test "-Dcucumber.filter.tags=@NoticeManagement"

echo.
echo ===============================================
echo Notice Tests Execution Complete
echo ===============================================
pause
