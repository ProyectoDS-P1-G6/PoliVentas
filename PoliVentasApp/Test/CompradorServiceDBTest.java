/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.List;
import junit.framework.TestCase;
import models.Articulo;
import models.Pedido;
import models.entities.Usuario;
import org.junit.Test;
import static org.junit.Assert.*;
import services.CompradorServiceDB ;

/**
 *
 * @author Luis
 */
public class CompradorServiceDBTest extends TestCase {
    
   public void compradorServicePedidosPendientesTest(){
       CompradorServiceDB cpdb = new CompradorServiceDB();
       assertNotNull( cpdb.getPedidosPendientes(new Usuario()));
       
   }
   public void CompradorServiceBuscarArticuloTest(){
       CompradorServiceDB cpdb = new CompradorServiceDB();
       assertNotNull(cpdb.buscarArticulo("MouseX"));
   }
   
   public void compradorServiceArticuloMasBuscadoTest(){
       CompradorServiceDB cpdb = new CompradorServiceDB();
       assertNotNull(cpdb.getArticulosMasBuscados());
       
   }
   public void compradorServiceResgistrarPedidoTest(){
       CompradorServiceDB cpdb = new CompradorServiceDB();
       Pedido pedido = new Pedido();
       assertTrue(cpdb.registrarPedido(pedido));
   }
   public void ConeccionesConBaseExceptionTest(){
       CompradorServiceDB cpdb = new CompradorServiceDB();
       Pedido pedido = new Pedido();
       cpdb.registrarPedido(pedido); 
       cpdb.getArticulosMasBuscados();
       cpdb.getPedidosPendientes(new Usuario());
       cpdb.buscarArticulo("MouseX");
       fail("No SQLEXCEPTION");
   }
   
   public void buscarArticuloTest(){
   CompradorServiceDB cpdb = new CompradorServiceDB();
   List<Articulo> art = cpdb.buscarArticulo("Lucky T-shirt");
   System.out.println(art.toString());
   
   }
    
}
