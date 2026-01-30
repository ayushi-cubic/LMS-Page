@echo off
echo ================================================
echo Running Contact Management Tests
echo ================================================
echo.

REM Run Contact Management tests using Maven
mvn clean test -Dtest=ContactTestRunner

echo.
echo ================================================
echo Contact Management Test Execution Completed
echo ================================================
echo Check target/cucumber-reports/cucumber.html for detailed report
pause
