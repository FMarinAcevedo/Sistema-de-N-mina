@echo off
cd /d "%~dp0"
if not exist out mkdir out
(for /r src %%f in (*.java) do @echo "%%f") > fuentes.txt
(for /r tests %%f in (*.java) do @echo "%%f") >> fuentes.txt
javac -encoding UTF-8 -d out @fuentes.txt
if errorlevel 1 exit /b 1
java -cp out nomina.app.Main
pause
