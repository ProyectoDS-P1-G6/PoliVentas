/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controllers.RealizarCompraController;
import javafx.event.ActionEvent;
import junit.framework.TestCase;
import models.Articulo;
import org.junit.Test;
import static org.junit.Assert.*;
import services.CompradorServiceDB;

/**
 *
 * @author Luis
 */
public class RealizarCompraControllerTest extends TestCase{

    public RealizarCompraControllerTest() {
        Articulo a = new Articulo();
        RealizarCompraController con = new RealizarCompraController();
        con.setArticulo(a);
        assertNotNull(con.getArticulo());
    }
    public void testDB (){
        RealizarCompraController con = new RealizarCompraController();
        con.setDBService(new CompradorServiceDB());
        assertNotNull(con.getDb());
    }
    
    

    
  
    
}
