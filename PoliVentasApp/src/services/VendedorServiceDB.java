package services;

import models.Pedido;
import models.entities.Vendedor;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;


public class VendedorServiceDB extends CompradorServiceDB {

    CallableStatement getVentas;
    CallableStatement getMisArticulos;

    public VendedorServiceDB() {
        
        try {
            getVentas = DBConnection.getInstance().prepareCall("{CALL getVentas(?)}");
            getMisArticulos = DBConnection.getInstance().prepareCall("{CALL getMisArticulos(?)}");

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
            venta.setId(data.getInt("id"));
            venta.setCantidad(data.getInt("cantidad"));
            venta.setFecha(data.getDate("fecha"));
            venta.setTotal(Double.parseDouble(new DecimalFormat("#.00").format(data.getDouble("total"))));
            venta.setEstado(Estado.parseEstado(data.getString("estado")));
            venta.setArticulo(getArticulo(data.getInt("id_articulo")));
            return venta;
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return venta;
    }
}
