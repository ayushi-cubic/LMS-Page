@echo off
REM Batch file to run all module tests (Customer + Account + Asset + Notice)
echo ===============================================
echo Running All Tests (Customer + Account + Asset + Notice)
echo ===============================================
echo.

mvn clean test "-Dcucumber.filter.tags=@CustomerManagement or @AccountManagement or @AssetManagement or @NoticeManagement"

echo.
echo ===============================================
echo All Tests Execution Complete
echo ===============================================
pause
