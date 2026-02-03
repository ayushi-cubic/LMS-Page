@echo off
REM Batch file to run Civil Case Creation module tests only

echo Running Civil Case Creation Module Tests
echo ================================================
echo.

call mvn clean test -Dtest=CivilCaseCreationTestRunner

echo.
echo Civil Case Creation Tests Completed
pause
