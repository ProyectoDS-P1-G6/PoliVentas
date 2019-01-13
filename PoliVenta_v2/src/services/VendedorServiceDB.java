package services;

import models.Pedido;
import models.entities.Vendedor;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class VendedorServiceDB extends CompradorServiceDB {

    CallableStatement getVentas;

    public VendedorServiceDB() {
        super();

        try {
            getVentas = DBConnection.getInstance().prepareCall("CALL getVentas()");
        } catch (SQLException e) {
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }catch (Exception e){
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }
    }

    public List<Pedido> getVentas(Vendedor vendedor){
        List<Pedido> ventas = new LinkedList<>();

        return ventas;
    }
}
