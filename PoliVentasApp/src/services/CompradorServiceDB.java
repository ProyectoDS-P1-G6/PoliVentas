package services;

import models.Pedido;
import models.entities.Comprador;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import models.Articulo;
import models.Estado;
import models.entities.Administrador;
import models.entities.Rol;
import models.entities.Usuario;
import models.entities.Vendedor;

public class CompradorServiceDB {

    CallableStatement buscarArticulo;
    CallableStatement addBusqueda;
    CallableStatement getArticulosMasBuscados;
    CallableStatement getArticulo;
    CallableStatement getUsuario;
    CallableStatement getPedidosPendientes;
    CallableStatement getSaldo;
    CallableStatement setSaldo;
    CallableStatement registrarPedido;
    
    

    public CompradorServiceDB(){
        
        DBConnection.createConnection();

        try {
            buscarArticulo  = DBConnection.getInstance().prepareCall("{CALL buscarArticulo(?)}");
            addBusqueda     = DBConnection.getInstance().prepareCall("{CALL addBusqueda(?)}");
            getArticulosMasBuscados = DBConnection.getInstance().prepareCall("{CALL getArticulosMasBuscados(?)}");
            getArticulo     = DBConnection.getInstance().prepareCall("{CALL getArticulo(?)}");
            getUsuario      = DBConnection.getInstance().prepareCall("{CALL getUser(?)}");
            getPedidosPendientes      = DBConnection.getInstance().prepareCall("{CALL getPedidosPendientes(?)}");
            getSaldo        = DBConnection.getInstance().prepareCall("{CALL getSaldo(?,?)}");
            setSaldo        = DBConnection.getInstance().prepareCall("{CALL setSaldo(?,?)}");
            registrarPedido = DBConnection.getInstance().prepareCall("{CALL registrarPedido(?,?,?,?)}");
        } catch (SQLException e) {
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }catch (Exception e){
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }
    }
    

    
    public List<Pedido> getPedidosPendientes(Usuario usuario){
        List<Pedido> pedidos = new LinkedList<>();
        
        ResultSet  result;
        
        try {
            getPedidosPendientes.setInt(1, usuario.getCedula());
            getPedidosPendientes.execute();
            
            result = getPedidosPendientes.getResultSet();
            
            while (result.next()) {  
                pedidos.add(parsePedido(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return pedidos;
    }
    
    
    
    
    public Articulo getArticulo(Integer id){
        
        try {
            getArticulo.setInt(1, id);
            getArticulo.execute();
            ResultSet data = getArticulo.getResultSet();
            data.next();              
            return parseArticulo(data);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new  Articulo();
    }
    
    
    
    
    public List<Articulo> buscarArticulo(String entry){
        List<Articulo> articulos = new LinkedList<>();
        
        try {
            buscarArticulo.setString(1, entry);
            buscarArticulo.execute();
            ResultSet result = buscarArticulo.getResultSet();
            
            while (result.next()) {                
                articulos.add(parseArticulo(result));
            }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return articulos;
    }
    
    public void addPuntoDeBusqueda(Articulo articulo){
        
        try {
            addBusqueda.setInt(1, articulo.getId());
            addBusqueda.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public List<Articulo> getArticulosMasBuscados(){
        List<Articulo> articulos = new LinkedList<>();
        
        try {
            getArticulosMasBuscados.setInt(1, 5);
            getArticulosMasBuscados.execute();
            
            ResultSet result = getArticulosMasBuscados.getResultSet();
            
            while (result.next()) {                
                articulos.add(parseArticulo(result));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return articulos;
    }
    
    public Usuario loadUsuario(Integer id, Rol rol) throws SQLException{
        getUsuario.setInt(1, id);
        getUsuario.execute();
        ResultSet data = getUsuario.getResultSet();
        data.next();
        
        Usuario usuario = new Usuario();

        switch(rol){
            case ADMIN:
                usuario = new Administrador();
                break;
                
            case COMPRADOR:
                usuario = new Comprador();
                break;
                
            case VENDEDOR:
                usuario = new Vendedor();
                break;  
        }
        
        usuario.setCedula(data.getInt(1));
        usuario.setNombres(data.getString(2));
        usuario.setApellidos(data.getString(3));
        try {
            usuario.setContactInfo(data.getString(4), data.getInt(5), data.getBoolean(6));
        } catch (AddressException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        usuario.setDireccion(data.getString(7));
        usuario.setMatricula(data.getInt(8));

        return usuario;
    }
    
    public Boolean registrarPedido(Pedido pedido){
        
        try {
            registrarPedido.setInt(1, pedido.getCantidad());
            registrarPedido.setDate(2, new java.sql.Date(pedido.getFecha().getTime()));
            registrarPedido.setInt(3, pedido.getComprador().getCedula());
            registrarPedido.setInt(4, pedido.getArticulo().getId());
            registrarPedido.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");
            return false;
        }
        return true;
    }
    
    
    public Pedido parsePedido(ResultSet data){
        Pedido pedido = new Pedido();
        
        try {
            Articulo a = getArticulo(data.getInt("id_articulo"));
            
            pedido = new Pedido();
            pedido.setId(data.getInt("id"));
            pedido.setCantidad(data.getInt("cantidad"));
            pedido.setFecha(data.getDate("fecha"));
            pedido.setEstado(Estado.parseEstado(data.getString("estado")));
            pedido.setComprador(LoginServiceDB.getActualLogin().getUsuario());
            pedido.setArticulo(a);
            return pedido;
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedido;
    }
    
    
    
    public Articulo parseArticulo(ResultSet data){
        Articulo articulo = new Articulo();
        try {
            articulo.setId(data.getInt(1));
            articulo.setNombre(data.getString(2));
            articulo.setCategoria(data.getString(3));
            articulo.setDescripción(data.getString(4));
            articulo.setPrecio(data.getDouble(5));
            articulo.setTiempo_max_entrega(data.getInt(6));
            articulo.setIcon(data.getString(7));
            articulo.setVendedor((Vendedor) loadUsuario(data.getInt(8), Rol.VENDEDOR));
            articulo.setNumero_busquedas(data.getInt(9));
            articulo.setEstado(data.getBoolean(10));

            return articulo;
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articulo;
    }
    
}
