@echo off
echo ========================================
echo Running Account Detail Complete Workflow Tests
echo (Basic 24 fields + 11 Additional Sections)
echo ========================================
mvn clean test -Dtest=AccountDetailManagementTestRunner
pause
