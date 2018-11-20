/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.FlowLayout;
import javafx.scene.layout.GridPane;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author Usuario
 */
public class FGUIview extends JFrame {
    
    public FGUIview(){
        JFrame internal = new JFrame();
        JDesktopPane internalContent = new JDesktopPane();
        internal.getContentPane().add(internalContent);
        
        JPanel panelPrueba = new JPanel();
        panelPrueba.setLayout(new FlowLayout());
        panelPrueba.add(new JLabel("Hola"));
        GridPane pane = new GridPane();
        
        
        JInternalFrame internalF = new JInternalFrame("Int");
        internalF.add(panelPrueba);
        //tamano pack importante 
        internalF.setSize(800,800);
        
        internalF.setResizable(true);
        internalF.setClosable(true);
        
        internalContent.add(internalF);
        
        internal.setSize(800,800);
        internal.setVisible(true);
        
        internal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        internalF.setVisible(true);
        
        }
    
}
