package services;

import models.Articulo;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministradorServiceDB extends VendedorServiceDB{


    private CallableStatement createUsuario;
    private CallableStatement readUsuarios;
    private CallableStatement updateUsuario;
    private CallableStatement deleteUsuario;

    private CallableStatement createArticulo;
    private CallableStatement readArticulos;
    private CallableStatement updateArticulo;
    private CallableStatement deleteArticulo;


    public AdministradorServiceDB(){
        super();

        try {
            createUsuario = DBConnection.getInstance().prepareCall("CALL createUsuario(?)");
            readUsuarios  = DBConnection.getInstance().prepareCall("CALL readUsuarios(?)");
            updateUsuario = DBConnection.getInstance().prepareCall("CALL updateUsuario(?)");
            deleteUsuario = DBConnection.getInstance().prepareCall("CALL deleteUsuario(?)");

            createArticulo = DBConnection.getInstance().prepareCall("CALL createArticulo(?)");
            readArticulos = DBConnection.getInstance().prepareCall("CALL readArticulos(?)");
            updateArticulo = DBConnection.getInstance().prepareCall("CALL updateArticulo(?)");
            deleteArticulo = DBConnection.getInstance().prepareCall("CALL deleteArticulo(?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e){
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }
    }

    public List<Articulo> getArticulos(){
        List<Articulo> articulos = new ArrayList<>();

        return articulos;
    }

}
