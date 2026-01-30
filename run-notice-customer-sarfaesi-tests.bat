@echo off
echo ==========================================
echo Running Notice + Customer + SARFAESI Tests
echo ==========================================
mvn clean test "-Dtest=ReportsTestRunner" "-Dcucumber.filter.tags=@GenerateNoticeReports"
pause
