color b
cls
@echo off
set /p msg=commit desc: 
git add .
git commit -m "%msg%"
git push -u origin main
pause
