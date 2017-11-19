/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ExtentionPane.java,v 1.1 2010/07/16 21:19:28 n-calder Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_sudoku
 * Autor: Manuel Mu�oz - 07-Sep-2006
 * Autor: Daniel Romero - 17-nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sudoku.GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Extension management class
 */
public class ExtentionPane extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Command option 1
     */
    private static final String OPTION_1 = "OPTION_1";

    /**
     * Command option 2
     */
    private static final String OPTION_2 = "OPTION_2";

    /**
     * Command for open a new sudoku
     */
    private static final String OPEN_FILE = "OPEN_FILE";

    /**
     * Command to validate the sudoku
     */
    private static final String VALIDATE = "VALIDATE";

    /**
     * Command to display the solution
     */
    private static final String LOAD_GAME = "LOAD_GAME";

    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Main application window
     */
    private SudokuGUI principal;

    // -----------------------------------------------------------------
    // GUI Fields
    // -----------------------------------------------------------------

    /**
     * Button Option 1
     */
    private JButton btnOption1;

    /**
     * Button Option 2
     */
    private JButton btnOption2;

    /**
     * Button to open a new file
     */
    private JButton btnFile;

    /**
     * Button to validate a board
     */
    private JButton btnValidate;

    /**
     * Button to show the sudoku solution
     */
    private JButton btnDisplaySolution;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Pane constructor
     * @param window Main window. window!=null
     */
    public ExtentionPane( SudokuGUI window )
    {
        principal = window;

        TitledBorder border = new TitledBorder( "Opciones" );
        border.setTitleColor( Color.WHITE );
        setBorder( border );
        setLayout( new GridLayout( 1, 2 ) );
        setBackground( Color.BLACK );

        // Button open file
        btnFile = new JButton( "Cargar" );
        btnFile.setActionCommand( OPEN_FILE );
        btnFile.addActionListener( this );
        add( btnFile );

        // Button Validate
        btnValidate = new JButton( "Validar" );
        btnValidate.setActionCommand( VALIDATE );
        btnValidate.addActionListener( this );
        btnValidate.setEnabled( false );
        add( btnValidate );

        // Button display solution
        btnDisplaySolution = new JButton( "Solucion" );
        btnDisplaySolution.setActionCommand( LOAD_GAME );
        btnDisplaySolution.addActionListener( this );
        btnDisplaySolution.setEnabled( false );
        add( btnDisplaySolution );

        // Bot�n option 1
        btnOption1 = new JButton( "Opcion 1" );
        btnOption1.setActionCommand( OPTION_1 );
        btnOption1.setBackground(Color.GREEN);
        btnOption1.addActionListener( this );
        add( btnOption1 );

        // Bot�n opci�n 2
        btnOption2 = new JButton( "Opcion 2" );
        btnOption2.setActionCommand( OPTION_2 );
        btnOption2.addActionListener( this );
        add( btnOption2 );

    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Event managing method
     * @param event Action generated by the event. event!=null
     */
    public void actionPerformed( ActionEvent event )
    {
        if( OPTION_1.equals( event.getActionCommand( ) ) )
        {
            principal.reqFuncOption1( );
        }
        else if( OPTION_2.equals( event.getActionCommand( ) ) )
        {
            principal.reqFuncOption2( );
        }
        else if( OPEN_FILE.equals( event.getActionCommand( ) ) )
        {
            principal.loadGame( );
        }
        else if( VALIDATE.equals( event.getActionCommand( ) ) )
        {
            principal.validateGame( );
        }
        else if( LOAD_GAME.equals( event.getActionCommand( ) ) )
        {
            principal.displaySolution( );
        }
    }

    /**
     * Enable or disables the validate and display solution buttons <br>
     * depending on the game status
     */
    public void updateButtons( )
    {
        if( principal.activeGame( ) )
        {
            btnValidate.setEnabled( true );
            btnDisplaySolution.setEnabled( true );
        }
        else
        {
            btnValidate.setEnabled( false );
            btnDisplaySolution.setEnabled( false );
        }
    }

}
