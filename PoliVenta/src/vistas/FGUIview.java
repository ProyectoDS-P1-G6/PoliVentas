/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.FlowLayout;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    private Pane panelPrincipal;

    public FGUIview(){
                              
        }
    public Pane getVentana(){
        return this.panelPrincipal;
    }
    public void setVentana(Pane panel){
        this.panelPrincipal=panel;
    }

 }
