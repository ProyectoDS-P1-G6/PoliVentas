package services;

import models.AuthInfo;
import models.entities.Administrador;

import java.util.Date;

public class LoginService extends DBHelper{

    private AuthInfo actualLogin;

    public LoginService(){
        super();
    }

    public AuthInfo authUser(String user, String password){

        AuthInfo info = new AuthInfo(new Administrador(), new Date(), true, false);
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
