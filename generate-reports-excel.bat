@echo off
echo ========================================
echo Generating Reports Test Cases Excel
echo ========================================
mvn compile
java -cp "target/classes;target/test-classes;%USERPROFILE%\.m2\repository\org\apache\poi\poi\5.2.3\poi-5.2.3.jar;%USERPROFILE%\.m2\repository\org\apache\poi\poi-ooxml\5.2.3\poi-ooxml-5.2.3.jar;%USERPROFILE%\.m2\repository\org\apache\commons\commons-collections4\4.4\commons-collections4-4.4.jar;%USERPROFILE%\.m2\repository\org\apache\xmlbeans\xmlbeans\5.1.1\xmlbeans-5.1.1.jar" com.example.GenerateReportsExcel
echo.
echo Excel file generated successfully!
pause
