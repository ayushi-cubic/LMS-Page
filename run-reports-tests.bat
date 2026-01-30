@echo off
echo ========================================
echo Running REPORTS Tests Only
echo ========================================
mvn clean test -Dtest=ReportsTestRunner
pause
