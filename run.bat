@echo off
cd src
javac -d ../bin *.java
cd ../bin
java main
cmd /k