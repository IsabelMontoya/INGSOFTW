/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: SquareTest.java,v 1.1 2010/07/16 21:19:30 n-calder Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_sudoku
 * Autor: Manuel Muñoz - Sep 28, 2006
 * Autor: Daniel Romero - 17-nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sudoku.test;

import uniandes.cupi2.sudoku.domain.Cell;
import junit.framework.TestCase;

/**
 * Test for the square class
 */
public class SquareTest extends TestCase
{

    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------
    /**
     * Class to be tested
     */
    private Cell square;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * It builds a new square with value 8
     */
    private void setupTest1( )
    {
        square = new Cell( 8 );
    }

    /**
     * It tests the method isInitial and setInitial
     */
    public void testIsInitial( )
    {
        setupTest1( );
        assertFalse( "The square is not initial", square.isInitial( ) );
        square.setInitial( );
        assertTrue( "The square is initial", square.isInitial( ) );
    }

    /**
     * It tests the getRealValue method
     */
    public void testGetRealValue( )
    {
        setupTest1( );
        assertEquals( "The real value of the square is 8", 8, square.getRealValue( ) );
    }

    /**
     * It tests the isMarked and unmark method
     */
    public void testIsMarked( )
    {
        setupTest1( );
        assertFalse( "The square is not marked", square.isMarked( ) );
        square.marked( );
        assertTrue( "The square is marked", square.isMarked( ) );
        square.unmark( );
        assertFalse( "The square is not marked", square.isMarked( ) );
    }

    /**
     * It test the getEnteredValue and setEnteredValue method
     */
    public void testSetEnteredValue( )
    {
        setupTest1( );
        assertEquals( "The real initial value is 0", 0, square.getEnteredValue( ) );
        square.setEnteredValue( 2 );
        assertEquals( "The real value is 2", 2, square.getEnteredValue( ) );
    }
}
