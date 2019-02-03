package services;

import models.Articulo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import models.entities.Rol;
import models.entities.Usuario;

public class AdministradorServiceDB extends VendedorServiceDB{

    CallableStatement getAllUser;
    
    private CallableStatement createUsuario;
    private CallableStatement readUsuarios;
    private CallableStatement updateUsuario;
    private CallableStatement deleteUsuario;
    private CallableStatement changeRolUsuario;
    
    private CallableStatement createArticulo;
    private CallableStatement readArticulos;
    private CallableStatement updateArticulo;
    private CallableStatement deleteArticulo;


    public AdministradorServiceDB(){

        try {
            getAllUser      = DBConnection.getInstance().prepareCall("{CALL getAllUser()}");
            changeRolUsuario = DBConnection.getInstance().prepareCall("CALL changeRolUsuario(?,?)");
            createUsuario = DBConnection.getInstance().prepareCall("CALL createUsuarios(?,?,?,?,?,?,?,?,?)");
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
    
    
    
    public void createUser(Usuario user){
        try {
            createUsuario.setInt(1,user.getCedula());
            createUsuario.setString(2, user.getNombres());
            createUsuario.setString(3, user.getApellidos());
            createUsuario.setString(4, user.getContactInfo().getEmail().toString());
            createUsuario.setString(5, user.getContactInfo().getTelefono().toString());
            createUsuario.setBoolean(6, user.getContactInfo().usaWhatsapp());
            createUsuario.setString(7, user.getDireccion());
            createUsuario.setInt(8, user.getMatricula());
            createUsuario.setString(9, Rol.getRolChar(user.getRol()));
            createUsuario.executeQuery();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    
    
    public void changeRol(Usuario usuario,Rol rol){
        ResultSet result;
        try {
            
            changeRolUsuario.setString(usuario.getCedula(),Rol.getRolChar(rol));
            changeRolUsuario.execute();
        } catch (SQLException e) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, e);
            e.getStackTrace();
        }
    }
    
    
    
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new LinkedList<>();
        
        ResultSet  result;
        
        try {
            getAllUser.execute();
            
            result = getAllUser.getResultSet();
            
            while (result.next()) {  
                usuarios.add(parseUsuario(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.getStackTrace();
        }
        
        
        return usuarios;
    }
    
    
    
    public Usuario parseUsuario(ResultSet data){
        
        
        try {
            Usuario user = Usuario.createUserByRol(Rol.parseRol(data.getString("tipo")));
            user.setCedula(data.getInt("id"));
            user.setNombres(data.getString("nombres"));
            user.setApellidos(data.getString("apellidos"));
            try {
                user.setContactInfo(data.getString("email"),
                        data.getInt("telefono"),
                        data.getBoolean("usa_whatsapp"));
            } catch (AddressException ex) {
                Logger.getLogger(AdministradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            user.setDireccion(data.getString("direccion"));
            
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Usuario();
        
    }
    
}
