package services;

import models.AuthInfo;
import models.entities.Administrador;
import models.entities.Comprador;
import models.entities.Vendedor;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import models.Articulo;
import models.entities.Rol;
import models.entities.Usuario;


public class LoginServiceDB {


    private CallableStatement authUserMethod;
    private CallableStatement getLoginUser;
    private static AuthInfo actualLogin;

    public LoginServiceDB(){

        DBConnection.createConnection();

        try {
            authUserMethod = DBConnection.getInstance().prepareCall("{CALL checkUserAndPass(?,?,?)}");
            getLoginUser   = DBConnection.getInstance().prepareCall("{CALL getUser(?)}");
        } catch (SQLException e) {
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        } catch (Exception e){
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }

    }

    public AuthInfo authUser(String user, String password){
        
        AuthInfo info = new AuthInfo();
        
        try {
            authUserMethod.setString(1, user);
            authUserMethod.setString(2, password);
            authUserMethod.execute();
           
            
            if(authUserMethod.getInt(3)!= 0){
                
                info.setLoggeoExitoso(Boolean.TRUE);
                info.setUsuario( loadUsuario(authUserMethod.getInt(3)));
              
            }else{
                info.setLoggeoExitoso(Boolean.FALSE);
            }
                

        } catch (SQLException e) {
            System.out.printf("%s  %s", e.getCause(), e.getMessage());
        }
        
        this.actualLogin = info;
        return info;
    }
    

    public static AuthInfo getActualLogin() {
        return actualLogin;
    }

    /**
     *  registra en la base de datos
     */
    void registarLoggeo(){

    }
    
    
    
    public Usuario loadUsuario(Integer id) throws SQLException{
        getLoginUser.setInt(1, id);
        getLoginUser.execute();
        ResultSet data = getLoginUser.getResultSet();
        data.next();
        String tipo = data.getString("tipo");
        
        Usuario usuario = new Usuario();

        switch(Rol.parseRol(tipo)){
            case ADMIN:
                usuario = new Administrador();
                break;
            case COMPRADOR:
                usuario = new Comprador();
                break;
            case VENDEDOR:
                usuario = new Vendedor();
                break;
        };
        
        usuario.setCedula(data.getInt(1));
        usuario.setNombres(data.getString(2));
        usuario.setApellidos(data.getString(3));
        usuario.setContactInfo(data.getString(4), data.getInt(5), data.getBoolean(6));
        usuario.setDireccion(data.getString(7));
        usuario.setMatricula(data.getInt(8));
        
        return usuario;
    }
    

}
