@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n6_sudoku
REM Autor: Manuel Muñoz - 07-Sep-2006
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ensures the creation of classes and lib directories
REM ---------------------------------------------------------

cd ..
mkdir classes
mkdir lib

REM ---------------------------------------------------------
REM Compiles the source directory class
REM ---------------------------------------------------------
cd source
javac -d ../classes/ uniandes/cupi2/sudoku/world/*.java
javac -d ../classes/ uniandes/cupi2/sudoku/GUI/*.java

REM ---------------------------------------------------------
REM The jar file is created from compiled files
REM ---------------------------------------------------------

cd ..
cd classes
jar cf ../lib/sudoku.jar uniandes/*

cd ../bin

pause
