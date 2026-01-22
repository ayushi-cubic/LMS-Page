@echo off
echo ========================================
echo Running Asset Management Tests
echo ========================================
mvn clean test -D"cucumber.filter.tags=@AssetManagement"
pause
