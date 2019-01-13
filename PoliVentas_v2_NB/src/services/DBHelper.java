package services;

import models.Carrito;
import models.entities.Comprador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

class DBHelper {

    Connection connection;

    CallableStatement authUserMethod;
    CallableStatement getCompras;     //son Callables ejemplos, se añadirán mas cuando se impplemente el sisteme.

    public DBHelper(){

        DBConnection.createConnection();
        this.connection  = DBConnection.connection;

        try {
            authUserMethod = connection.prepareCall("CALL checkUserAndPass(?,?)");
            getCompras     = connection.prepareCall("CALL getCompras(?,?)");
        } catch (SQLException e) {
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        } catch (Exception e){
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }
    };


    public List<Carrito> getCompras(Comprador comprador){
        List<Carrito> compras = new LinkedList<>();

        return compras;
    }

}