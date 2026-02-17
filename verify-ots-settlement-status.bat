@echo off
echo ===================================
echo Testing ONLY OTS Scenario
echo ===================================
cd "c:\Users\DELL\Documents\Automation framework\LMS-Page"
mvn test -Dtest=CustomerDetailManagementTestRunner -Dcucumber.filter.tags="@AddOTS and not @FullFlow" > ots_test.log 2>&1
echo.
echo Test completed. Searching for Settlement Status logs...
echo.
findstr /C:"Settlement Status" /C:"Setteled" /C:"Broken" /C:"selected:" ots_test.log
echo.
pause
