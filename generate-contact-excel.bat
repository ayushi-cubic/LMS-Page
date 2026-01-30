@echo off
echo ================================================
echo Generating Excel Report for Contact Tests
echo ================================================
echo.

REM Compile the project first
mvn clean compile

REM Run the Excel generator for Contact tests
mvn exec:java -Dexec.mainClass="com.example.GenerateContactExcel" -Dexec.classpathScope=compile

echo.
echo ================================================
echo Excel Report Generated Successfully
echo ================================================
echo Check Contact_TestCases.xlsx in the project root
pause
