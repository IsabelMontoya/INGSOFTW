/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ImagePane.java,v 1.1 2010/07/16 21:19:28 n-calder Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_sudoku
 * Autor: Manuel Muñoz - Aug 7, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sudoku.GUI;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Class for the header image
 */
public class ImagePane extends JPanel
{

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Default constructor method. It puts the header image of the application.
     */
    public ImagePane( )
    {
        JLabel image = new JLabel( );
        ImageIcon icono = new ImageIcon( "data/title.png" );
        // The label is added
        image = new JLabel( "" );
        image.setIcon( icono );
        add( image );

        setBackground( Color.BLACK );
        setBorder( new LineBorder( Color.BLACK ) );
    }

}
