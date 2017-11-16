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
REM Test Execution
REM ---------------------------------------------------------
cd..

java -classpath ./lib/sudoku.jar;./test/lib/sudokuTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.sudoku.test.SudokuTest
java -classpath ./lib/sudoku.jar;./test/lib/sudokuTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.sudoku.test.SquareTest

cd bin