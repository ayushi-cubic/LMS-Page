@echo off
REM Batch file to run Full Flow Customer Detail test
echo ===============================================
echo Running Full Flow Customer Detail Test
echo ===============================================
echo.

call mvn clean test "-Dcucumber.filter.tags=@FullFlow"

echo.
echo ===============================================
echo Full Flow Test Complete
echo ===============================================
pause
