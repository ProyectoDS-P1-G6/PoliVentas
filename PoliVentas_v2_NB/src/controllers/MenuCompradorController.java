package controllers;

import java.awt.event.ActionListener;
import javafx.event.ActionEvent;
import models.entities.Comprador;
import services.CompradorServiceDB;
import views.MenuComprador;

public class MenuCompradorController implements ActionListener {
    private Comprador comprador;
    private MenuComprador menuComprador;

    private CompradorServiceDB db;

    public MenuCompradorController(Comprador compradorModel, MenuComprador menuCompradorView) {
        this.comprador = compradorModel;
        this.menuComprador = menuCompradorView;
        

        db = new CompradorServiceDB();
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
