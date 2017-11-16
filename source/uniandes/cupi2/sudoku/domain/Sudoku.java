/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Sudoku.java,v 1.1 2010/07/16 21:19:29 n-calder Exp $
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

package uniandes.cupi2.sudoku.domain;

import java.util.Properties;

/**
 * Class that represents a sudoku game
 */
public class Sudoku
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * It represents the name for the key properties file
     */
    private static final String KEY_PROPERTIES = "sudoku.row";

    /**
     * It represents the number of rows of the board
     */
    public static final int ROW_NUMBER = 9;

    /**
     * It represents the number of columns of the board
     */
    public static final int COLUMN_NUMBER = 9;

    /**
     * It represents the number of areas that the board has
     */
    public static final int NUMBER_AREAS = 9;

    /**
     * It represents the number of cells to be displayed at the beginning of the game. <br>
     * Its maximum values should be 9 and its minimum value should be 1
     */
    public static final int INITIAL_CELL_NUMBER = 3;

    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * It tells if the game is being played
     */
    private boolean playing;

    /**
     * The matrix of cells of the board
     */
    private Cell[][] board;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructor method of the game. <br>
     * <b> post: </b> board!=null.<br>
     */
    public Sudoku( )
    {
        board = new Cell[ROW_NUMBER][COLUMN_NUMBER];
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * It initializes the board with the values that are in the properties files. <br>
     * <b> post: </b> board!=null.<br>
     * @param properties Object with the solution of the game. properties!=null
     * @throws Exception It throws this exception if there are problems reading the file.
     */
    public void loadBoard( Properties properties ) throws Exception
    {
        for( int i = 0; i < COLUMN_NUMBER; i++ )
        {
            String row = ( String )properties.get( KEY_PROPERTIES + ( i + 1 ) );
            if( row != null && row.length( ) == ROW_NUMBER )
                loadRow( row, i );
            else
                throw new Exception( "The file does not have the expected format" );
        }
    }

    /**
     * It begins the game, marking the cells to be displayed <br>
     * <b> post: </b> playing = true. <br>
     */
    public void beginGame( )
    {
        playing = true;
        initialzeMarks( );

    }

    /**
     * It verifies the compliance of the rules of the game. <br>
     * <b> pre: </b> board!=null y playing=true <br>
     * @param theBoard Board with the values entered by the user
     * @return true is returned if the game is correct, false otherwise
     */
    public boolean validateBoard( int[][] theBoard )
    {
        enterUserValues( theBoard );
        boolean areas = validateAreas( );
        boolean rows = validateRows( );
        boolean columns = validateColumns( );
        boolean zeros = validateFilling( );
        return areas && rows && columns && zeros;

    }

    /**
     * It fills the board with the values entered by the user. <br>
     * <b> pre: </b> board!=null y playing= true. <br>
     * @param theBoard Board with the values entered by the user. theBoard!=null
     */
    private void enterUserValues( int[][] theBoard )
    {
        for( int i = 0; i < ROW_NUMBER; i++ )
        {
            for( int j = 0; j < COLUMN_NUMBER; j++ )
            {
                board[ i ][ j ].setEnteredValue( theBoard[ i ][ j ] );
            }
        }
    }

    /**
     * It cleans the board, rebuilding. <br>
     * <b> post: </b> board!=null and the board is empty<br>
     */
    public void clean( )
    {
        board = new Cell[ROW_NUMBER][COLUMN_NUMBER];
    }

    /**
     * It is used to indicate the end of the game <br>
     * <b> post: </b> playing= false <br>
     */
    public void endGame( )
    {
        playing = false;
    }

    /**
     * It points out if the game is active or not(if the game has not ended)
     * @return true is returned if the game has not finished yet, false otherwise
     */
    public boolean activeGame( )
    {
        return playing;
    }

    /**
     * It builds a row of cells from a String read from a properties file.<br>
     * <b> pre: </b>cells!=null. <br>
     * @param row The String read from the properties file. row!=null and row must have the same length as ROW_NUMBER
     * @param numRow The row in the matrix where the String is going to be put. 0<=numRow<= ROW_NUMBER
     * @throws Exception The exception is thrown if there are problems loading the file.
     */
    private void loadRow( String row, int numRow ) throws Exception
    {

        for( int i = 0; i < row.length( ); i++ )
        {
            int value;
            try
            {
                value = Integer.parseInt( row.substring( i, i + 1 ) );
            }
            catch( NumberFormatException e )
            {
                throw new Exception( "The properties file does not have the expected format" );
            }
            Cell cell = new Cell( value );
            board[ numRow ][ i ] = cell;
        }
    }

    /**
     * It picks out and marks the cells that are going to be displayed when the game is loaded. <br>
     * <b> pre: </b> board!=null. <br>
     */
    private void initialzeMarks( )
    {
        for( int i = 0; i < NUMBER_AREAS; i++ )
        {
            Cell[] cells = getCellsArea( i + 1 );
            int counter = 0;
            while( counter < INITIAL_CELL_NUMBER )
            {
                int value = generateRandomNumberWithinRange( cells.length );
                Cell cell = cells[ value ];
                if( !cell.isInitial( ) )
                {
                    counter++;
                    cell.setInitial( );
                }
            }
        }
    }

    /**
     * It generates a random number between 0 and rangeSize -1
     * @param rangeSize Range Size
     * @return Positive integer between 0 y rangeSize - 1
     */
    private int generateRandomNumberWithinRange( int rangeSize )
    {
        return ( int ) ( Math.random( ) * rangeSize );
    }

    /**
     * It returns an array with the cells that are in a given area. <br>
     * <b> pre: </b> board!=null. <br>
     * @param area The area of the wanted cells. 1<=area<=9
     * @return an array is returned with the cells of the given area
     */
    public Cell[] getCellsArea( int area )
    {
        Cell[] cells = new Cell[ROW_NUMBER];

        int rowBeginning = ( ( area - 1 ) / 3 ) * 3;
        int columnBeginning = ( ( area - 1 ) % 3 ) * 3;
        int counter = 0;

        for( int i = rowBeginning; i < rowBeginning + 3; i++ )
        {
            for( int j = columnBeginning; j < columnBeginning + 3; j++ )
            {
                cells[ counter ] = board[ i ][ j ];
                counter++;
            }
        }

        return cells;
    }

    /**
     * It verifies the compliance of the non-repeating rule in rows of the game. <br>
     * <b> pre: </b> board!=null and each one of the positions of the board are initialized. <br>
     * @return true is returned if the rule is complied, false otherwise
     */
    private boolean validateRows( )
    {
        boolean answer = true;
        for( int i = 0; i < COLUMN_NUMBER; i++ )
        {
            Cell[] cells = getRow( i );
            if( thereAreRepeated( cells ) )
            {
                mark( cells );
                answer = false;
            }

        }
        return answer;
    }

    /**
     * It verifies the compliance of the non-repeating rule in columns of the game. <br>
     * <b> pre: </b> board!=null and each one of the positions of the board are initialized. <br>
     * @return true is returned if the rule is complied, false otherwise
     */
    private boolean validateColumns( )
    {
        boolean answer = true;
        for( int i = 0; i < ROW_NUMBER; i++ )
        {
            Cell[] cells = board[ i ];
            if( thereAreRepeated( cells ) )
            {
                mark( cells );
                answer = false;
            }
        }
        return answer;
    }

    /**
     * It verifies the compliance of the non-repeating rule in areas of the game. <br>
     * <b> pre: </b> board!=null and each one of the positions of the board are initialized. <br>
     * @return true is returned if the rule is complied, false otherwise
     */
    private boolean validateAreas( )
    {
        boolean answer = true;
        for( int i = 0; i < NUMBER_AREAS; i++ )
        {
            Cell[] cells = getCellsArea( i + 1 );
            if( thereAreRepeated( cells ) )
            {
                mark( cells );
                answer = false;
            }
        }
        return answer;
    }

    /**
     * It marks the cells to be displayed as wrong.<br>
     * <b>post: </b> The cells of the array are marked. <br>
     * @param cells Array of cells to be marked. cells!=null
     */
    private void mark( Cell[] cells )
    {
        for( int i = 0; i < cells.length; i++ )
        {
            cells[ i ].marked( );
        }
    }

    /**
     * It returns the cells of a row. <br>
     * <b> pre: </b> cells!=null. <br>
     * @param numRow The number of the row to be obtained. 0<=numRow<ROW_NUMBER
     * @return An array of cells of the given row is returned
     */
    private Cell[] getRow( int numRow )
    {
        Cell[] cells = new Cell[COLUMN_NUMBER];
        for( int i = 0; i < ROW_NUMBER; i++ )
        {
            cells[ i ] = board[ i ][ numRow ];
        }
        return cells;
    }

    /**
     * Method that evaluates if there are repeated numbers in an array of cells. <br>
     * @param cells cells to be evaluated. cells!=null and each one of the positions of the board are initialized <br>
     * @return true is returned if there are non-repeated values in the array, false otherwise
     */
    private boolean thereAreRepeated( Cell[] cells )
    {
        for( int i = 0; i < cells.length; i++ )
        {
            Cell cell = cells[ i ];
            for( int j = 0; cell != null && j < cells.length; j++ )
            {
                Cell cell2 = cells[ j ];
                if( cell.getEnteredValue( ) == cell2.getEnteredValue( ) && i != j && cell.getEnteredValue( ) != 0 )
                    return true;

            }
        }
        return false;
    }

    /**
     * It removes the error mark of the cells. <br>
     * <b> pre: </b> cells!=null and each one of the positions of the board are initialized. <br>
     * <b> post: </b> Every cell of the matrix is unmarked
     * 
     */
    public void unmarkCells( )
    {
        for( int i = 0; i < ROW_NUMBER; i++ )
        {
            for( int j = 0; j < COLUMN_NUMBER; j++ )
            {
                board[ i ][ j ].unmark( );
            }
        }
    }

    /**
     * It validates that there are no empty cells in the board. <br>
     * <b> pre: </b> cells!=null and each one of the positions of the board are initialized. <br>
     * @return true is returned if there is an empty cell, false otherwise
     */
    private boolean validateFilling( )
    {
        for( int i = 0; i < ROW_NUMBER; i++ )
        {
            for( int j = 0; j < COLUMN_NUMBER; j++ )
            {
                Cell cell = board[ i ][ j ];
                if( cell == null || cell.getEnteredValue( ) == 0 )
                    return false;
            }
        }
        return true;
    }

    /**
     * Method that returns the game board
     * @return the cells of the board
     */
    public Cell[][] getBoard( )
    {
        return board;
    }

    // -----------------------------------------------------------------
    // Extension Points
    // -----------------------------------------------------------------

    /**
     * Extension Method 1
     * @return answer1
     */
    public String method1( )
    {
        return "Answer 1";
    }

    /**
     * Extension Method2
     * @return answer2
     */
    public String method2( )
    {
        return "Answer 2";
    }

}