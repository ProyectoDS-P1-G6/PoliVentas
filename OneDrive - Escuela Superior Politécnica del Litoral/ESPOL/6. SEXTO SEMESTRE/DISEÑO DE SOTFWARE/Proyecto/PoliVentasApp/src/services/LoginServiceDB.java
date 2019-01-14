package services;

import models.AuthInfo;
import models.entities.Administrador;
import models.entities.Comprador;
import models.entities.Vendedor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

public class LoginServiceDB {


    private CallableStatement authUserMethod;
    private AuthInfo actualLogin;

    public LoginServiceDB(){

        DBConnection.createConnection();


        try {
            authUserMethod = DBConnection.getInstance().prepareCall("CALL checkUserAndPass(?,?)");
        } catch (SQLException e) {
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        } catch (Exception e){
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }

    }

    public AuthInfo authUser(String user, String password){

        AuthInfo info = new AuthInfo(new Administrador(), new Date(), true, false);
        switch(user.toLowerCase()){
            case "admin":
                info = new AuthInfo(new Administrador(), new Date(), true, false);
                break;
            case "comprador":
                info = new AuthInfo(new Comprador(), new Date(), true, false);
                break;
            case "vendedor":
                info = new AuthInfo(new Vendedor(), new Date(), true, false);
                break;
        }
        this.actualLogin = info;
        return info;
    }
    

    public AuthInfo getActualLogin() {
        return actualLogin;
    }

    /**
     *  registra en la base de datos
     */
    void registarLoggeo(){

    }

}
