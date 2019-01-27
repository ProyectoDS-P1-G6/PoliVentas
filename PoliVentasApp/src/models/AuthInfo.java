package models;

import models.entities.Usuario;

import java.util.Date;

public class AuthInfo {
    Usuario usuario;
    private Boolean loggeoExitoso;
    private Boolean recordarLoggin;
    Date logginDate;

    public AuthInfo() {
    }


    public AuthInfo(Usuario usuario, Date logginDate, Boolean loggeoExitoso, Boolean recordarLoggin) {
        this.usuario = usuario;
        this.loggeoExitoso = loggeoExitoso;
        this.recordarLoggin = recordarLoggin;
        this.logginDate = logginDate;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setLoggeoExitoso(Boolean loggeoExitoso) {
        this.loggeoExitoso = loggeoExitoso;
    }

    
    public Boolean isLoggeoExitoso() {
        return loggeoExitoso;
    }

    public Boolean getRecordarLoggin() {
        return recordarLoggin;
    }

    public void setRecordarLoggin(Boolean recordarLoggin) {
        this.recordarLoggin = recordarLoggin;
    }

    public Date getLogginDate() {
        return logginDate;
    }

}
