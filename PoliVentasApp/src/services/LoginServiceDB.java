package services;

import models.AuthInfo;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import models.entities.Rol;
import models.entities.Usuario;


public class LoginServiceDB {


    private CallableStatement authUserMethod;
    private CallableStatement getLoginUser;
    private CallableStatement registerUser;
    private static AuthInfo actualLogin;

    public LoginServiceDB(){

        DBConnection.createConnection();

        try {
            authUserMethod = DBConnection.getInstance().prepareCall("{CALL checkUserAndPass(?,?,?)}");
            getLoginUser   = DBConnection.getInstance().prepareCall("{CALL getUser(?)}");
            registerUser   = DBConnection.getInstance().prepareCall("{CALL registerUser(?,?,?,?,?,?,?,?,?)}");
            
        } catch (SQLException e) {
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        } catch (Exception e){
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }

    }

    public AuthInfo authUser(String user, String password){
        
        AuthInfo info = new AuthInfo(new Usuario(), new Date(), false, false);
        
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
        
        LoginServiceDB.actualLogin = info;
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
        
        Usuario usuario = Usuario.createUserByRol(Rol.parseRol(tipo));
        
        usuario.setCedula(data.getInt(1));
        usuario.setNombres(data.getString(2));
        usuario.setApellidos(data.getString(3));
        try {
            usuario.setContactInfo(data.getString(4), data.getInt(5), data.getBoolean(6));
        } catch (AddressException ex) {
            Logger.getLogger(LoginServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        usuario.setDireccion(data.getString(7));
        usuario.setMatricula(data.getInt(8));
        
        return usuario;
    }
    
    
    public Boolean registerUser(Usuario u){
        try {
            registerUser.setInt(1, u.getCedula());
            registerUser.setString(2, u.getNombres());
            registerUser.setString(3, u.getApellidos());
            registerUser.setString(4, u.getContactInfo().getEmail().toString());
            registerUser.setString(5, u.getContactInfo().getTelefono().toString());
            registerUser.setBoolean(6, u.getContactInfo().usaWhatsapp());
            registerUser.setString(7, u.getDireccion());
            registerUser.setInt(8, u.getMatricula());
            registerUser.setString(9, Rol.getRolChar(u.getRol()));
            registerUser.execute();
        }catch(SQLIntegrityConstraintViolationException e){
            return false;
        } 
        catch (SQLException ex) {
            Logger.getLogger(LoginServiceDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }
    

}
