/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: BoardPane.java,v 1.1 2010/07/16 21:19:28 n-calder Exp $
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

package uniandes.cupi2.sudoku.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import uniandes.cupi2.sudoku.domain.Cell;
import uniandes.cupi2.sudoku.domain.Sudoku;

/**
 * Pane that contains the squares of the board
 */
public class BoardPane extends JPanel
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant that represents the number of cells of an area
     */
    public static final int NUM_CELLS_AREA = 9;

    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Main application window
     */
    private SudokuGUI principal;

    /**
     * It represents the cells color when the sudoku is solved
     */
    private Color solvedColor;

    /**
     * It represents the cells color when the sudoku has errors
     */
    private Color errorColor;

    /**
     * It represents the cells color when they are empty
     */
    private Color emptyColor;

    // -----------------------------------------------------------------
    // GUI Fields
    // -----------------------------------------------------------------

    /**
     * The board cells
     */
    private JTextField[][] cells;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Constructor using fields.
     * @param mainWindow Main application window
     */
    public BoardPane( SudokuGUI mainWindow )
    {
        // the fields ant properties of the panel are initialized
        principal = mainWindow;
        setLayout( new GridLayout( Sudoku.ROW_NUMBER / 3, Sudoku.COLUMN_NUMBER / 3 ) );
        setBackground( Color.BLACK );

        // Colors
       // solvedColor = new Color( 87, 150, 38 );
       // errorColor = new Color( 200, 1, 1 );
       // emptyColor = new Color( 229, 132, 15 );

        cells = new JTextField[Sudoku.ROW_NUMBER][Sudoku.COLUMN_NUMBER];

        // Pane areas are built
        JPanel[][] areaPanes = new JPanel[Sudoku.NUMBER_AREAS / 3][Sudoku.NUMBER_AREAS / 3];
        Border border = new BevelBorder( BevelBorder.RAISED );

        for( int i = 0; i < Sudoku.NUMBER_AREAS / 3; i++ )
        {
            for( int j = 0; j < Sudoku.NUMBER_AREAS / 3; j++ )
            {
                areaPanes[ i ][ j ] = new JPanel( );
                areaPanes[ i ][ j ].setLayout( new GridLayout( NUM_CELLS_AREA / 3, NUM_CELLS_AREA / 3 ) );
                areaPanes[ i ][ j ].setBorder( border );
                add( areaPanes[ i ][ j ] );
            }
        }

        // The text fields are added to the area panes
        for( int i = 0; i < Sudoku.ROW_NUMBER; i++ )
        {
            for( int j = 0; j < Sudoku.COLUMN_NUMBER; j++ )
            {
                cells[ i ][ j ] = new JTextField( "" );
                cells[ i ][ j ].setHorizontalAlignment( JTextField.CENTER );                
                Font fontType = cells[ i ][ j ].getFont( );
                Font newFontType = new Font( fontType.getName( ), Font.PLAIN, fontType.getSize( ) + 3 );
                cells[ i ][ j ].setFont( newFontType );
                cells[ i ][ j ].setPreferredSize( new Dimension( 50, 50 ) );
                cells[ i ][ j ].setEditable( false );
                areaPanes[ i / 3 ][ j / 3 ].add( cells[ i ][ j ] );
            }
        }
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    /**
     * It repaints the text fields
     */
    public void updateBoard( )
    {
        Cell[][] boardCells = principal.getBoardCells( );

        for( int i = 0; i < Sudoku.ROW_NUMBER; i++ )
        {
            for( int j = 0; j < Sudoku.COLUMN_NUMBER; j++ )
            {
                // The text field is added with the default configuration
                cells[ i ][ j ].setText( "" );
                cells[ i ][ j ].setEditable( true );
                cells[ i ][ j ].setBackground( Color.WHITE );
                Font fontType = cells[ i ][ j ].getFont( );
                Font newFontType = new Font( fontType.getName( ), Font.PLAIN, fontType.getSize( ) );
                cells[ i ][ j ].setFont( newFontType );

                // The look and feel is changed according to the information
                if( boardCells[ i ][ j ].isInitial( ) && boardCells[ i ][ j ].isMarked( ) )
                {
                    cells[ i ][ j ].setBackground( errorColor );
                    cells[ i ][ j ].setEditable( false );
                    cells[ i ][ j ].setText( "" + boardCells[ i ][ j ].getRealValue( ) );
                    Font font = cells[ i ][ j ].getFont( );
                    Font newFont = new Font( font.getName( ), Font.BOLD, font.getSize( ) );
                    cells[ i ][ j ].setFont( newFont );

                }
                else if( boardCells[ i ][ j ].isInitial( ) )
                {
                    cells[ i ][ j ].setText( "" + boardCells[ i ][ j ].getRealValue( ) );
                    Font font = cells[ i ][ j ].getFont( );
                    Font newFont = new Font( font.getName( ), Font.BOLD, font.getSize( ) );
                    cells[ i ][ j ].setFont( newFont );
                    cells[ i ][ j ].setEditable( false );
                    cells[ i ][ j ].setBackground( Color.WHITE );
                }
                else if( boardCells[ i ][ j ].isMarked( ) )
                {
                    cells[ i ][ j ].setBackground( errorColor );
                    if( boardCells[ i ][ j ].getEnteredValue( ) != 0 )
                    {
                        cells[ i ][ j ].setText( "" + boardCells[ i ][ j ].getEnteredValue( ) );
                    }
                    else
                    {
                        cells[ i ][ j ].setText( "" );
                    }
                }
                else if( boardCells[ i ][ j ].getEnteredValue( ) == 0 )
                {
                    cells[ i ][ j ].setBackground( emptyColor );
                }
                else if( boardCells[ i ][ j ].getEnteredValue( ) != 0 )
                {
                    cells[ i ][ j ].setBackground( Color.WHITE );
                    cells[ i ][ j ].setText( "" + boardCells[ i ][ j ].getEnteredValue( ) );
                }
            }

        }
    }

    /**
     * It returns the matrix with the values entered by the user
     * @return The matrix is returned with the values entered by the user
     * @throws Exception If the content of some cell is not a number between 1 and 9
     */
    public int[][] getMatrix( ) throws Exception
    {
        // Matrix initialization
        int[][] board = new int[Sudoku.ROW_NUMBER][Sudoku.COLUMN_NUMBER];

        for( int i = 0; i < Sudoku.ROW_NUMBER; i++ )
        {
            for( int j = 0; j < Sudoku.COLUMN_NUMBER; j++ )
            {
                int value = 0;
                try
                {
                    String content= ( String )cells[ i ][ j ].getText( ); 
                    
                    if(content!=null && !content.equals(""))
                    {
                        value = Integer.parseInt( ( String )cells[ i ][ j ].getText( ) );
                        if(value<1 || value>9)
                        {                        
                            throw new Exception("The content entered in each cell must be a number between 1 and 9");
                        }
                    }
                    
                    board[ i ][ j ] = value;                                                            
                }
                catch( NumberFormatException e )
                {
                    throw new Exception("The content entered in each cell must be a number between 1 and 9");
                }
                
            }
        }

        return board;
    }

    /**
     * It puts the text fields in the right color when the game is successfully competed
     */
    public void drawCompletedBoard( )
    {
        for( int i = 0; i < Sudoku.NUMBER_AREAS; i++ )
        {
            for( int j = 0; j < Sudoku.COLUMN_NUMBER; j++ )
            {
                cells[ i ][ j ].setBackground( solvedColor );
                ;
            }

        }
    }

    /**
     * The solution is shown in the text fields
     */
    public void showSolution( )
    {
        Cell[][] boardCells = principal.getBoardCells( );
        for( int i = 0; i < Sudoku.NUMBER_AREAS; i++ )
        {
            for( int j = 0; j < Sudoku.COLUMN_NUMBER; j++ )
            {
                cells[ i ][ j ].setBackground( solvedColor );
                cells[ i ][ j ].setText( "" + boardCells[ i ][ j ].getRealValue( ) );
                cells[ i ][ j ].setEditable( false );
            }

        }
    }
}
