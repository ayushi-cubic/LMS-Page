@echo off
REM ============================================
REM Customer Management Test Automation Runner
REM ============================================

echo ============================================
echo Customer Management Test Automation
echo ============================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven and add it to your PATH
    pause
    exit /b 1
)

echo Maven found. Proceeding with test execution...
echo.

REM Show menu
echo Select test execution option:
echo 1. Run all tests
echo 2. Run CreateCustomer test only
echo 3. Run SearchCustomer test only
echo 4. Run End-to-End test only
echo 5. Clean and install dependencies
echo 6. Generate Excel test case document
echo 7. Exit
echo.

set /p choice="Enter your choice (1-7): "

if "%choice%"=="1" goto runall
if "%choice%"=="2" goto runcreate
if "%choice%"=="3" goto runsearch
if "%choice%"=="4" goto rune2e
if "%choice%"=="5" goto cleaninstall
if "%choice%"=="6" goto generateexcel
if "%choice%"=="7" goto end
goto invalidchoice

:runall
echo.
echo Running all tests...
echo.
mvn clean test
goto showreport

:runcreate
echo.
echo Running Create Customer test...
echo.
mvn clean test -Dcucumber.filter.tags="@CreateCustomer"
goto showreport

:runsearch
echo.
echo Running Search Customer test...
echo.
mvn clean test -Dcucumber.filter.tags="@SearchCustomer"
goto showreport

:rune2e
echo.
echo Running End-to-End test...
echo.
mvn clean test -Dcucumber.filter.tags="@EndToEnd"
goto showreport

:cleaninstall
echo.
echo Cleaning and installing dependencies...
echo.
mvn clean install -DskipTests
echo.
echo Dependencies installed successfully!
pause
exit /b 0

:generateexcel
echo.
echo Generating Excel test case document...
echo.
mvn exec:java -Dexec.mainClass="com.example.GenerateExcelTestCases"
echo.
if %ERRORLEVEL% EQU 0 (
    echo ============================================
    echo Excel document generated successfully!
    echo Check the project root directory for the .xlsx file
    echo ============================================
) else (
    echo ============================================
    echo Failed to generate Excel document
    echo Please check the error messages above
    echo ============================================
)
echo.
pause
exit /b 0

:showreport
echo.
echo ============================================
echo Test execution completed!
echo ============================================
echo.
echo Reports generated at:
echo   HTML: target\cucumber-reports\cucumber.html
echo   JSON: target\cucumber-reports\cucumber.json
echo.
pause
exit /b 0

:invalidchoice
echo.
echo Invalid choice! Please select 1-7.
echo.
pause
exit /b 1

:end
echo.
echo Exiting...
exit /b 0
