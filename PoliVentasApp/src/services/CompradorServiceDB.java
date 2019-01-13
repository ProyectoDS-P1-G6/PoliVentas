package services;

import models.Pedido;
import models.entities.Comprador;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CompradorServiceDB {

    CallableStatement getCompras;

    public CompradorServiceDB(){
        DBConnection.createConnection();

        try {
            getCompras     = DBConnection.getInstance().prepareCall("CALL getCompras(?,?)");
        } catch (SQLException e) {
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }catch (Exception e){
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }
    }

    public List<Pedido> getCompras(Comprador comprador){
        List<Pedido> compras = new LinkedList<>();

        return compras;
    }
}
