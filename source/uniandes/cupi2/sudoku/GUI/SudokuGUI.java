/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: SudokuGUI.java,v 1.1 2010/07/16 21:19:28 n-calder Exp $
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

package uniandes.cupi2.sudoku.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.sudoku.domain.Cell;
import uniandes.cupi2.sudoku.domain.Sudoku;

/**
 * This is the main application window
 */
public class SudokuGUI extends JFrame
{

    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Main world class
     */
    private Sudoku sudoku;

    // -----------------------------------------------------------------
    // GUI Fields
    // -----------------------------------------------------------------

    /**
     * Extension Pane
     */
    private ExtentionPane extentionPane;

    /**
     * Header image pane
     */
    private ImagePane imagePane;

    /**
     * Sudoku board Pane
     */
    private BoardPane boardPane;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Gui constructor
     */
    public SudokuGUI( )
    {
        // Main class is built
        sudoku = new Sudoku( );

        // The form is built
        setLayout( new BorderLayout( ) );
        setSize( 500, 500 );
        setResizable( false );
        setTitle( "Sudoku" );
        getContentPane( ).setBackground( Color.BLACK );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocationRelativeTo( null );

        // Pane initialization
        imagePane = new ImagePane( );
        add( imagePane, BorderLayout.NORTH );

        extentionPane = new ExtentionPane( this );
        add( extentionPane, BorderLayout.SOUTH );

        boardPane = new BoardPane( this );
        add( boardPane, BorderLayout.CENTER );

        // Auxiliary Panes
        JPanel auxiliaryPane1 = new JPanel( );
        auxiliaryPane1.setPreferredSize( new Dimension( 110, 0 ) );
        auxiliaryPane1.setBackground( Color.BLUE );
        add( auxiliaryPane1, BorderLayout.WEST );
        JPanel auxiliaryPane2 = new JPanel( );
        auxiliaryPane2.setPreferredSize( new Dimension( 110, 0 ) );
        auxiliaryPane2.setBackground( Color.BLUE );
        add( auxiliaryPane2, BorderLayout.EAST );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * The status of the sudoku is loaded from a given file
     */
    public void loadGame( )
    {
        // Crear el jfileChooser
        JFileChooser chooser = new JFileChooser( );
        chooser.setCurrentDirectory( new java.io.File( "./data" ) );
        chooser.setDialogTitle( "Select file" );
        chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
        chooser.setVisible( true );

        if( chooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
        {
            try
            {
                // Properties are initialized
                Properties propiedades = new Properties( );
                propiedades.load( new FileInputStream( chooser.getSelectedFile( ) ) );
                // Give the properties to the world
                sudoku.loadBoard( propiedades );

                // the game is initialized
                sudoku.beginGame( );
                // Update Board
                update( );
            }
            catch( Exception e )
            {                
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * It updates the grphic board according to the information in the world
     */
    private void update( )
    {
        boardPane.updateBoard( );
        extentionPane.updateButtons( );
    }

    /**
     * It validates the information entered by the user on the board
     */
    public void validateGame( )
    {
        int[][] theBoard;
        try
        {
            theBoard = boardPane.getMatrix( );
            sudoku.unmarkCells( );
            if( sudoku.validateBoard( theBoard ) )
            {
                sudoku.endGame( );
                drawCompletedBoard( );
                JOptionPane.showMessageDialog( this, "Game successfully completed", "Game Over", JOptionPane.INFORMATION_MESSAGE );
            }
            else
            {
                update( );
            }
        }
        catch( Exception e )
        {            
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        
    }

    /**
     * It changes the color of the board showing that the game is over
     */
    private void drawCompletedBoard( )
    {
        boardPane.drawCompletedBoard( );
        extentionPane.updateButtons( );
    }

    /**
     * It ends the game and displays the solution
     */
    public void displaySolution( )
    {
        sudoku.endGame( );
        boardPane.showSolution( );
        extentionPane.updateButtons( );
        sudoku.clean( );
    }

    /**
     * It returns if the game is active or not
     * @return true is returned if the game is active, false otherwise
     */
    public boolean activeGame( )
    {
        return sudoku.activeGame( );
    }

    /**
     * It returns the cells of the board
     * @return The cells of the board are returned
     */
    public Cell[][] getBoardCells( )
    {
        return sudoku.getBoard( );
    }

    // -----------------------------------------------------------------
    // Extension Points
    // -----------------------------------------------------------------

    /**
     * Extension Method 1
     */
    public void reqFuncOption1( )
    {
        String result = sudoku.method1( );
        JOptionPane.showMessageDialog( this, result, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Extension Method 2
     */
    public void reqFuncOption2( )
    {
        String result = sudoku.method2( );
        JOptionPane.showMessageDialog( this, result, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * this method executes the application, building a new graphical user interface
     * @param args Arguments for the execution of the application. They are not needed in this case
     */
    public static void main( String[] args )
    {

        SudokuGUI gui = new SudokuGUI( );
        gui.setVisible( true );
    }
}