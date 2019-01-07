package models;

import models.entities.Usuario;

import java.util.Date;

public class AuthInfo {
    Usuario usuario;
    Boolean estado;
    Boolean recordarLoggin;
    Date logginDate;

    public AuthInfo(Usuario usuario, Date logginDate,Boolean estado, Boolean recordarLoggin) {
        this.usuario = usuario;
        this.estado = estado;
        this.recordarLoggin = recordarLoggin;
        this.logginDate = logginDate;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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

    public void setLogginDate(Date logginDate) {
        this.logginDate = logginDate;
    }
}
