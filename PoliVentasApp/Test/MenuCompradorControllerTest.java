/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luis
 */
import controllers.MenuCompradorController;
public class MenuCompradorControllerTest extends TestCase{
    
    public void testPedidosActualizadosDB(){
        MenuCompradorController con = new MenuCompradorController();
        con.actualizarPedidosPendientes();
        assertNotNull(con.getDB());
        
        
    }
  
    
}
