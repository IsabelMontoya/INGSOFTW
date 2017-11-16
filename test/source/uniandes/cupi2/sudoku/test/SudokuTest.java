/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: SudokuTest.java,v 1.1 2010/07/16 21:19:30 n-calder Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_sudoku
 * Autor: Manuel Muñoz - 07-Sep-2006
 * Autor: Daniel Romero - 17-nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sudoku.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import junit.framework.TestCase;
import uniandes.cupi2.sudoku.domain.Cell;
import uniandes.cupi2.sudoku.domain.Sudoku;

/**
 * This class is used to verify all the methods in the sudoku class
 */
public class SudokuTest extends TestCase
{
    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * THe class to be tested
     */
    private Sudoku sudoku;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * An empty game is built
     */
    private void setupScenario1( )
    {
        sudoku = new Sudoku( );
    }

    /**
     * It initializes a new sudoku with a valid board
     */
    private void setupScenario2( )
    {
        sudoku = new Sudoku( );
        try
        {
            Properties properties = new Properties( );
            properties.load( new FileInputStream( new File( "./test/data/sudoku.properties" ) ) );
            sudoku.loadBoard( properties );
        }
        catch( Exception e )
        {
            fail( "The exception should not be thrown" );
        }
    }

    /**
     * It tests the loading of a board
     */
    public void testLoadBoard( )
    {
        setupScenario1( );
        Properties properties = new Properties( );
        try
        {
            properties.load( new FileInputStream( new File( "./test/data/sudoku.properties" ) ) );
            sudoku.loadBoard( properties );

            // Each row must have nine numbers
            boolean[] numbers = new boolean[Sudoku.ROW_NUMBER];

            Cell[][] board = sudoku.getBoard( );

            for( int i = 0; i < Sudoku.ROW_NUMBER; i++ )
            {
                for( int k = 0; k < Sudoku.ROW_NUMBER; k++ )
                {
                    numbers[ k ] = false;
                }

                for( int j = 0; j < Sudoku.COLUMN_NUMBER; j++ )
                {
                    numbers[ board[ i ][ j ].getRealValue( ) - 1 ] = true;

                    // The entered value must be 0
                    assertEquals( "The entered valur should be 0", 0, board[ i ][ j ].getEnteredValue( ) );
                }

                for( int k = 0; k < Sudoku.ROW_NUMBER; k++ )
                {
                    assertTrue( "The number should be found", numbers[ k ] );
                }
            }
        }
        catch( Exception e )
        {
            fail( "The exception should not be thrown" );
        }
    }

    /**
     * It tests the beginGame, endGame and activeGame method
     */
    public void testBeginGame( ) throws FileNotFoundException, IOException
    {
        setupScenario2( );

        // It verifies that the squares are marked properly and that its status is active
        sudoku.beginGame( );
        assertTrue( "The game must have begun", sudoku.activeGame( ) );
        Cell[][] board = sudoku.getBoard( );

        int marked = 0;

        for( int i = 0; i < Sudoku.ROW_NUMBER; i++ )
        {
            for( int j = 0; j < Sudoku.COLUMN_NUMBER; j++ )
            {
                if( board[ i ][ j ].isInitial( ) )
                {
                    marked++;
                }
            }
        }
        assertEquals( "The game did not start successfully", Sudoku.INITIAL_CELL_NUMBER * Sudoku.NUMBER_AREAS, marked );

        // It verifies when the game is over that the status was changed
        sudoku.endGame( );
        assertFalse( "The game should have ended", sudoku.activeGame( ) );
    }

    /**
     * It tests the validateBoard method 
     */
    public void testValidateBoard( )
    {
        setupScenario2( );
        Cell[][] squares = sudoku.getBoard( );
        int[][] values = new int[Sudoku.ROW_NUMBER][Sudoku.COLUMN_NUMBER];
        for( int i = 0; i < Sudoku.ROW_NUMBER; i++ )
        {
            for( int j = 0; j < Sudoku.COLUMN_NUMBER; j++ )
            {
                values[ i ][ j ] = squares[ i ][ j ].getRealValue( );
            }
        }
        assertTrue( "The game should be valid", sudoku.validateBoard( values ) );

        values[ Sudoku.ROW_NUMBER - 1 ][ Sudoku.COLUMN_NUMBER - 1 ] = 7;

        assertFalse( "The game should not be valid", sudoku.validateBoard( values ) );

        for( int i = 0; i < Sudoku.ROW_NUMBER; i++ )
        {
            for( int j = 0; j < Sudoku.COLUMN_NUMBER; j++ )
            {
                values[ i ][ j ] = 3;
            }
        }

        assertFalse( "The game should not be valid", sudoku.validateBoard( values ) );
    }

    /**
     * It test the unmarkSquares method 
     */
    public void testUnmarkSquares( )
    {
        setupScenario2( );
        sudoku.beginGame( );

        int[][] values = new int[Sudoku.ROW_NUMBER][Sudoku.COLUMN_NUMBER];
        for( int i = 0; i < Sudoku.ROW_NUMBER; i++ )
        {
            for( int j = 0; j < Sudoku.COLUMN_NUMBER; j++ )
            {
                values[ i ][ j ] = 1;
            }
        }

        assertFalse( "The game should not be valid", sudoku.validateBoard( values ) );
        sudoku.unmarkCells( );
        Cell[][] squares = sudoku.getBoard( );

        for( int i = 0; i < Sudoku.ROW_NUMBER; i++ )
        {
            for( int j = 0; j < Sudoku.COLUMN_NUMBER; j++ )
            {
                assertFalse( "The square should not be marked", squares[ i ][ j ].isMarked( ) );
            }
        }
    }

    /**
     * It tests the clean method
     */
    public void testClean( ) throws FileNotFoundException, IOException
    {
        setupScenario2( );
        sudoku.clean( );

        Cell[][] squares = sudoku.getBoard( );

        for( int i = 0; i < Sudoku.ROW_NUMBER; i++ )
        {
            for( int j = 0; j < Sudoku.COLUMN_NUMBER; j++ )
            {
                assertNull( "The square should be null", squares[ i ][ j ] );
            }
        }
    }
}