@echo off
REM Batch file to run Criminal Case Creation module tests only

echo Running Criminal Case Creation Module Tests
echo ================================================
echo.

call mvn clean test -Dtest=CriminalCaseCreationTestRunner

echo.
echo Criminal Case Creation Tests Completed
pause
