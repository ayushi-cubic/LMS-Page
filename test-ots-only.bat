@echo off
echo Testing ONLY OTS Scenario...
cd "c:\Users\DELL\Documents\Automation framework\LMS-Page"
mvn clean test -Dtest=CustomerDetailManagementTestRunner "-Dcucumber.filter.tags=@AddOTS and not @FullFlow" 
pause
