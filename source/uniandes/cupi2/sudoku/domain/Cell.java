/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Cell.java,v 1.1 2010/07/16 21:19:29 n-calder Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_sudoku
 * Autor: Manuel Muñoz - Aug 7, 2006
 * Autor: Daniel Romero - 17-nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sudoku.domain;

/**
 * Class that represents a square on the board of Sudoku
 */
public class Cell
{

    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Represents the actual value of the square in the game
     */
    private int actualValue;

    /**
     * Represents the value entered by the user on the square
     */
    private int enteredValue;

    /**
     * It points out if the square is displayed at the beginning of the game
     */
    private boolean initial;

    /**
     * It points out if the square must be displayed in red 
     */
    private boolean marked;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * It builds a square with the given value. <br>
     * <b> post: </b> The square was created with the given value. <br>
     * @param value The number corresponding to the square. 1<=value<=9.
     */
    public Cell( int value )
    {
        actualValue = value;
        enteredValue = 0;
        initial = false;
        marked = false;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    /**
     * It tells if the square was selected as a square to be shown at the beginning of the game
     * @return true is returned if the square is one of the initial squares, false otherwise.
     */
    public boolean isInitial( )
    {
        return initial;
    }

    /**
     * It returns the real value of the square
     * @return The real value is returned
     */
    public int getRealValue( )
    {
        return actualValue;
    }

    /**
     * It tells if the square is marked to be displayed in red on the board
     * @return true is returned if the square must be seen in red, false otherwise.
     */
    public boolean isMarked( )
    {
        return marked;
    }

    /**
     * It returns the value entered by the user
     * @return The entered value is returned
     */
    public int getEnteredValue( )
    {
        return enteredValue;
    }

    /**
     * It changes the status of the square so it can be displayed at the beginning of the game. <br>
     * <b> post: </b> initial= true <br>
     */
    public void setInitial( )
    {
        initial = true;
    }

    /**
     * It marks a square so it can be displayed in red. <br>
     * <b> post: </b> marked= true <br>
     */
    public void marked( )
    {
        marked = true;
    }

    /**
     * It changes the value entered by the user. <br>
     * <b> post: </b> enteredValue= nEnteredValue <br>
     * @param nEnteredValue An integer between 1 and 9
     */
    public void setEnteredValue( int nEnteredValue )
    {
        enteredValue = nEnteredValue;
    }

    /**
     * It removes the mark of the square so it is no longer displayed in red
     * <b> post: </b> marked= false <br>
     */
    public void unmark( )
    {
        marked = false;
    }
}
