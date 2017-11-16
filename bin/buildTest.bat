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
REM Ensures the creation of classes and lib directories in test
REM ---------------------------------------------------------

cd ../test
mkdir classes
mkdir lib

REM ---------------------------------------------------------
REM The test classes in directory/source are compiled
REM ---------------------------------------------------------

cd source

javac -classpath ../../lib/sudoku.jar;../lib/junit.jar -d ../classes/ uniandes/cupi2/sudoku/test/*.java

REM ---------------------------------------------------------
REM The jar file is created from compiled files
REM ---------------------------------------------------------

cd ../classes

jar cf ../lib/sudokuTest.jar uniandes/* -C ../data .

cd ../../bin

pause