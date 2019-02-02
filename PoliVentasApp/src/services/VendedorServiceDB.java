package services;

import models.Pedido;
import models.entities.Vendedor;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;
import models.entities.Comprador;
import models.entities.Rol;


public class VendedorServiceDB extends CompradorServiceDB {

    CallableStatement getVentas;
    CallableStatement getMisArticulos;
    CallableStatement modificarProducto;
    CallableStatement anularVenta;
    CallableStatement agregarProducto;
    CallableStatement getCategorias;
    CallableStatement getVentasPendientes;
        
    public VendedorServiceDB() {
        
        try {
            getVentas = DBConnection.getInstance().prepareCall("{CALL getVentas(?)}");
            getMisArticulos = DBConnection.getInstance().prepareCall("{CALL getMisArticulos(?)}");
            anularVenta = DBConnection.getInstance().prepareCall("{CALL anularVenta(?)}");
            agregarProducto = DBConnection.getInstance().prepareCall("{CALL agregarProducto(?,?,?,?,?,?)}");
            modificarProducto = DBConnection.getInstance().prepareCall("{CALL modificarProducto(?,?,?,?,?,?)}");
            getCategorias = DBConnection.getInstance().prepareCall("{CALL getCategorias()}");
            getVentasPendientes = DBConnection.getInstance().prepareCall("{CALL getVentasPendientes(?)}");

        } catch (SQLException e) {
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }catch (Exception e){
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }
    }

    public List<Pedido> getVentas(Vendedor vendedor){
        List<Pedido> ventas = new LinkedList<>();
        try{
            getVentas.setInt(1,vendedor.getCedula());
            getVentas.execute();
            
            ResultSet result = getVentas.getResultSet();
            
            while(result.next()){
                ventas.add(parseVenta(result));
            }
        }
        catch (SQLException ex){
             Logger.getLogger(VendedorServiceDB.class.getName()).log(Level.SEVERE, null, ex);

    }
        return ventas;
    }
    
    public List<Pedido> getVentasPendientes(Vendedor vendedor){
        List<Pedido> ventaspendientes = new LinkedList<>();
        try{
            getVentasPendientes.setInt(1,vendedor.getCedula());
            getVentasPendientes.execute();
            
            ResultSet result = getVentasPendientes.getResultSet();
            
            while(result.next()){
                ventaspendientes.add(parseVenta(result));
            }
        }
        catch (SQLException ex){
             Logger.getLogger(VendedorServiceDB.class.getName()).log(Level.SEVERE, null, ex);

    }
        return ventaspendientes;
    }
    
    
    public List<Articulo> getMisArticulos(Vendedor vendedor){
        List<Articulo> misArticulos = new LinkedList<>();
        try {
            getMisArticulos.setInt(1, vendedor.getCedula());
            getMisArticulos.execute();
            
            ResultSet result = getMisArticulos.getResultSet();
            
            while (result.next()) {                
                misArticulos.add(parseArticulo(result));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VendedorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return misArticulos;
    }
    
    
    public Pedido parseVenta(ResultSet data){
        Pedido venta = new Pedido();
        
        try {
            Articulo a = getArticulo(data.getInt("id_articulo"));
            
            venta.setId(data.getInt("id"));
            venta.setCantidad(data.getInt("cantidad"));
            venta.setFecha(data.getDate("fecha"));
            venta.setEstado(Estado.parseEstado(data.getString("estado")));
            venta.setArticulo(a);
            venta.setComprador((Comprador) loadUsuario(data.getInt("id_comprador"), Rol.COMPRADOR));
            return venta;
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return venta;
    }
    
    public int anularVenta(Pedido venta) {

        try {
            anularVenta.setInt(1, venta.getId());
            anularVenta.execute();

            ResultSet result = anularVenta.getResultSet();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(VendedorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public Articulo modificarProducto(Articulo articulo){
    try {
            modificarProducto.setInt(1, articulo.getId());
            modificarProducto.setString(2, articulo.getNombre());
            modificarProducto.setString(3,articulo.getCategoria());
            modificarProducto.setString(4, articulo.getDescripción());
            modificarProducto.setDouble(5, articulo.getPrecio().getAmountMinorLong());
            modificarProducto.setInt(6, articulo.getTiempo_max_entrega());
            modificarProducto.execute();

            ResultSet result = modificarProducto.getResultSet();
            return articulo;

        } catch (SQLException ex) {
            Logger.getLogger(VendedorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articulo;
    }
    
    public boolean agregarProducto(String nombre, Integer categoria, String descripcion, Double precio, Integer tiempo, Integer vendedor){
        try {
            agregarProducto.setString(1, nombre);
            agregarProducto.setInt(2, categoria);
            agregarProducto.setString(3, descripcion);
            agregarProducto.setDouble(4, precio);
            agregarProducto.setInt(5, tiempo);
            agregarProducto.setInt(6, vendedor);
            agregarProducto.execute();

            ResultSet result = agregarProducto.getResultSet();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(VendedorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<String> getCategorias() {
        List<String> categorias = new LinkedList<>();
        try {
           
            getCategorias.execute();

            ResultSet result = getCategorias.getResultSet();

            while (result.next()) {
                categorias.add(result.getString("nombre_categoria"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendedorServiceDB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return categorias;
    }
}
