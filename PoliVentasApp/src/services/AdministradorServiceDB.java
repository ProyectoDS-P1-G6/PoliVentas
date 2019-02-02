package services;

import java.sql.Array;
import models.Articulo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
        user.getCedula();
        user.getNombres();
        user.getApellidos();
        user.getContactInfo().getEmail();
        user.getContactInfo().getTelefono();
        user.getContactInfo().isUsaWhatsapp();
        user.getDireccion();
        user.getMatricula();
        user.getRol();
        try {
            createUsuario.setInt(1,user.getCedula());
            createUsuario.setString(2, user.getNombres());
            createUsuario.setString(3, user.getApellidos());
            createUsuario.setString(4, user.getContactInfo().getEmail());
            createUsuario.setString(5, user.getContactInfo().getTelefono().toString());
            createUsuario.setBoolean(6, user.getContactInfo().isUsaWhatsapp());
            createUsuario.setString(7, user.getDireccion());
            createUsuario.setInt(8, user.getMatricula());
            createUsuario.setString(9, Rol.inParseRol(user.getRol()));
            
            if(!createUsuario.execute()){
                JOptionPane.showMessageDialog(null, "Se ha registrado: "+user.getNombres()+"correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "ERROR!, no se ha podido registrar "+user.getNombres());
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    public void deleteUser(Usuario killUser){
        try {
            deleteUsuario.setInt(1, killUser.getCedula());
            if(!deleteUsuario.execute()){
                JOptionPane.showMessageDialog(null, killUser.getNombres()+" ha sido eliminado definitivamente");
            }else{
                JOptionPane.showMessageDialog(null, "ERROR!, no se ha podido eliminar "+killUser.getNombres());
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    public void changeRol(Usuario usuario,Rol rol){
        ResultSet result;
        try {
            
            changeRolUsuario.setString(usuario.getCedula(),Rol.inParseRol(rol));
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
        Usuario user = new Usuario();
        
        try {
            
            
            //Articulo a = getArticulo(data.getInt("id_articulo"));
            user.setCedula(data.getInt("id"));
            user.setNombres(data.getString("nombres"));
            user.setApellidos(data.getString("apellidos"));
            user.setContactInfo(data.getString("email"),
                    data.getInt("telefono"),
                    data.getBoolean("usa_whatsapp"));
            
            user.setDireccion(data.getString("direccion"));
            user.setRol(Rol.parseRol(data.getString("tipo")));
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
}
