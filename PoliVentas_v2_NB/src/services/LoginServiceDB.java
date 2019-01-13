package services;

import models.AuthInfo;
import models.entities.Administrador;
import models.entities.Comprador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

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

        if(user.isEmpty() || password.isEmpty()){
            return info;
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
