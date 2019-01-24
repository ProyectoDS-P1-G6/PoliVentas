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
import models.entities.*;
import models.*;


public class VendedorServiceDB extends CompradorServiceDB {

    CallableStatement getVentas;
    CallableStatement getMisArticulos;

    public VendedorServiceDB(Usuario vendedor) {
        
        super(vendedor);
        try {
            getVentas = DBConnection.getInstance().prepareCall("{CALL getVentas(?)}");
            getMisArticulos = DBConnection.getInstance().prepareCall("{CALL getMisArticulos(?)}");
            buscarArticulo  = DBConnection.getInstance().prepareCall("{CALL buscarArticulo(?)}");
            addBusqueda     = DBConnection.getInstance().prepareCall("{CALL addBusqueda(?)}");
            getArticulosMasBuscados = DBConnection.getInstance().prepareCall("{CALL getArticulosMasBuscados(?)}");
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

    public List<Venta> getVentas(Vendedor vendedor){
        List<Venta> ventas = new LinkedList<>();
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
    
    public Venta parseVenta(ResultSet data){
        Venta venta = new Venta();
        
        try {
            venta = new Venta()
                    .setId(data.getInt("id"))
                    .setCantidad(data.getInt("cantidad"))
                    .setFecha(data.getDate("fecha"))
                    .setEstado(Estado.parseEstado(data.getString("estado")))
                    .setVendedor((Vendedor)usuario)
                    .setArticulo(getArticulo(data.getInt("id_articulo")));
            return venta;
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return venta;
    }
}
