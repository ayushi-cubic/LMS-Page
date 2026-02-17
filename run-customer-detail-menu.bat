@echo off
REM Batch file to run specific Customer Detail section tests
echo ===============================================
echo Customer Detail Module - Quick Test Commands
echo ===============================================
echo.
echo Available test options:
echo 1. Full Flow (all sections)
echo 2. Basic Details only
echo 3. NPA Details only
echo 4. Customer Account only
echo 5. Address Details only
echo 6. All Tests
echo.

SET /P choice=Enter your choice (1-6): 

IF "%choice%"=="1" (
    echo Running Full Flow test...
    call mvn clean test "-Dcucumber.filter.tags=@FullFlow"
) ELSE IF "%choice%"=="2" (
    echo Running Basic Details test...
    call mvn clean test "-Dcucumber.filter.tags=@EditBasicDetails"
) ELSE IF "%choice%"=="3" (
    echo Running NPA Details test...
    call mvn clean test "-Dcucumber.filter.tags=@EditNPADetails"
) ELSE IF "%choice%"=="4" (
    echo Running Customer Account test...
    call mvn clean test "-Dcucumber.filter.tags=@AddCustomerAccount"
) ELSE IF "%choice%"=="5" (
    echo Running Address Details test...
    call mvn clean test "-Dcucumber.filter.tags=@AddAddressDetails"
) ELSE IF "%choice%"=="6" (
    echo Running All Customer Detail tests...
    call mvn clean test "-Dcucumber.filter.tags=@CustomerDetailManagement"
) ELSE (
    echo Invalid choice!
)

echo.
echo ===============================================
echo Test Execution Complete
echo ===============================================
pause
