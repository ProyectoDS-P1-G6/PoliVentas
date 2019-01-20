package services;

import models.Pedido;
import models.entities.Comprador;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Articulo;
import models.ArticuloBuilder;
import models.Estado;
import models.PedidoBuilder;
import models.entities.AdministradorBuilder;
import models.entities.CompradorBuilder;
import models.entities.Rol;
import models.entities.Usuario;
import models.entities.Vendedor;
import models.entities.VendedorBuilder;

public class CompradorServiceDB {

    CallableStatement buscarArticulo;
    CallableStatement getArticulo;
    CallableStatement getUsuario;
    CallableStatement getPedidos;
    CallableStatement getSaldo;
    CallableStatement setSaldo;
    
    Usuario usuario;
    

    public CompradorServiceDB(Usuario usuario){
        this.usuario = usuario;
        DBConnection.createConnection();

        try {
            buscarArticulo  = DBConnection.getInstance().prepareCall("{CALL buscarArticulo(?)}");
            getArticulo     = DBConnection.getInstance().prepareCall("{CALL getArticulo(?)}");
            getUsuario      = DBConnection.getInstance().prepareCall("{CALL getUser(?)}");
            getPedidos      = DBConnection.getInstance().prepareCall("{CALL getPedidos(?)}");
            getSaldo        = DBConnection.getInstance().prepareCall("{CALL getSaldo(?,?)}");
            setSaldo        = DBConnection.getInstance().prepareCall("{CALL setSaldo(?,?)}");
        } catch (SQLException e) {
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }catch (Exception e){
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }
    }
    

    public List<Pedido> getPedidos(Usuario usuario){
        List<Pedido> pedidos = new LinkedList<>();
        
        ResultSet  result;
        
        try {
            getPedidos.setInt(1, usuario.getCedula());
            getPedidos.execute();
            
            result = getPedidos.getResultSet();
            
            while (result.next()) {
                Pedido pedido = new PedidoBuilder()
                        .setId(result.getInt("id"))
                        .setCantidad(result.getInt("cantidad"))
                        .setFecha(result.getDate("fecha"))
                        .setEstado(Estado.parseEstado(result.getString("estado")))
                        .setComprador((Comprador)usuario)
                        .setArticulo(getArticulo(result.getInt("id_articulo")))
                        .build();
                
                pedidos.add(pedido);
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
        
        return new  Articulo(new ArticuloBuilder());
    }
    
    public Articulo parseArticulo(ResultSet data){
        Articulo articulo = new Articulo(new ArticuloBuilder());
        try {

            articulo = new ArticuloBuilder()
                    .setId(data.getInt(1))
                    .setNombre(data.getString(2))
                    .setCategoria(data.getString(3))
                    .setDescripción(data.getString(4))
                    .setPrecio(data.getDouble(5))
                    .setTiempo_max_entrega(data.getInt(6))
                    .setIcon(data.getString(7))
                    .setVendedor((Vendedor) loadUsuario(data.getInt(8), Rol.VENDEDOR))
                    .build();
            System.out.printf("art: %d %s %s %s %f %d %s %s \n",
                    data.getInt(1), 
                    data.getString(2),
                    data.getString(3),
                    data.getString(4),
                    data.getDouble(5),
                    data.getInt(6),
                    data.getString(7),
                    (Vendedor) loadUsuario(data.getInt(8), Rol.VENDEDOR));
            return articulo;
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articulo;
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
    
    
    
    
    public Usuario loadUsuario(Integer id, Rol rol) throws SQLException{
        getUsuario.setInt(1, id);
        getUsuario.execute();
        ResultSet data = getUsuario.getResultSet();
        data.next();
        
        Usuario usuario = new Usuario();

        switch(rol){
            case ADMIN:
                usuario = new AdministradorBuilder()
                        .setCedula(data.getInt(1))
                        .setNombres(data.getString(2))
                        .setApellidos(data.getString(3))
                        .setContactInfo(data.getString(4), data.getInt(5), data.getBoolean(6))
                        .setDireccion(data.getString(7))
                        .setMatricula(data.getInt(8))
                        .build();
                break;
                
            case COMPRADOR:
                usuario = new CompradorBuilder()
                        .setCedula(data.getInt(1))
                        .setNombres(data.getString(2))
                        .setApellidos(data.getString(3))
                        .setContactInfo(data.getString(4), data.getInt(5), data.getBoolean(6))
                        .setDireccion(data.getString(7))
                        .setMatricula(data.getInt(8))
                        .build();
                break;
                
            case VENDEDOR:
                usuario = new VendedorBuilder()
                        .setCedula(data.getInt(1))
                        .setNombres(data.getString(2))
                        .setApellidos(data.getString(3))
                        .setContactInfo(data.getString(4), data.getInt(5), data.getBoolean(6))
                        .setDireccion(data.getString(7))
                        .setMatricula(data.getInt(8))
                        .build();
                break;
                
        }
        
        return usuario;
    }
    
    
}
