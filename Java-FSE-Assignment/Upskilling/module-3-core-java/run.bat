@echo off
cd /d %~dp0
javac -d out src\com\upskilling\employee\*.java
if errorlevel 1 pause & exit /b 1
java -cp out com.upskilling.employee.Main
pause
