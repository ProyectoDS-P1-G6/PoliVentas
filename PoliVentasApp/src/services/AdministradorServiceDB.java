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
import javax.swing.JOptionPane;
import models.Pedido;
import models.entities.Rol;
import models.entities.Usuario;

public class AdministradorServiceDB extends VendedorServiceDB{

    CallableStatement getAllUser;
    CallableStatement getArticulos;
    
    private CallableStatement createUsuario;
    private CallableStatement updateUsuario;
    //private CallableStatement deleteUsuario;
    private CallableStatement deleteUsuarioLogic;
    private CallableStatement changeRolUsuario;
    
    private CallableStatement getPedidos;
    
    private CallableStatement deleteArticulo;


    public AdministradorServiceDB(){

        try {
            getAllUser      = DBConnection.getInstance().prepareCall("{CALL getAllUser()}");
            changeRolUsuario = DBConnection.getInstance().prepareCall("CALL changeRolUsuario(?,?)");
            getArticulos = DBConnection.getInstance().prepareCall("{CALL getAllArticulos()}");
            
            createUsuario = DBConnection.getInstance().prepareCall("CALL createUsuarios(?,?,?,?,?,?,?,?,?)");
            updateUsuario = DBConnection.getInstance().prepareCall("CALL updateUsuario(?,?,?,?,?,?,?,?)");
            //deleteUsuario = DBConnection.getInstance().prepareCall("CALL deleteUsuario(?)");
            deleteUsuarioLogic = DBConnection.getInstance().prepareCall("CALL deleteUsuarioLogic(?)");
            
            getPedidos = DBConnection.getInstance().prepareCall("CALL getPedidos(?)");
            deleteArticulo = DBConnection.getInstance().prepareCall("CALL deleteArticulo(?)");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e){
            System.out.printf("Error %s %s\n",e.getMessage(), e.getCause());
        }
    }

    public List<Articulo> getArticulos(){
        List<Articulo> articulos = new ArrayList<>();
        ResultSet  result;
        
        try {
            getArticulos.execute();
            
            result = getArticulos.getResultSet();
            
            while (result.next()) {  
                articulos.add(parseArticulo(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
            ex.getStackTrace();
        }
        
        return articulos;
    }
    
    public List<Pedido> getPedidos(Usuario usuario){
        List<Pedido> pedidos = new LinkedList<>();
        
        ResultSet  result;
        
        try {
            getPedidos.setInt(1, usuario.getCedula());
            getPedidos.execute();
            
            result = getPedidos.getResultSet();
            
            while (result.next()) {  
                pedidos.add(parsePedido(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return pedidos;
    }
    
    
    public void updateUser(Usuario user){
        try {
            updateUsuario.setInt(1,user.getCedula());
            updateUsuario.setString(2, user.getNombres());
            updateUsuario.setString(3, user.getApellidos());
            updateUsuario.setString(4, user.getContactInfo().getEmail().toString());
            updateUsuario.setString(5, user.getContactInfo().getTelefono().toString());
            updateUsuario.setBoolean(6, user.getContactInfo().usaWhatsapp());
            updateUsuario.setString(7, user.getDireccion());
            updateUsuario.setString(8, Rol.getRolChar(user.getRol()));
            updateUsuario.executeQuery();
        } catch (SQLException e) {
            e.getStackTrace();
        }
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
            createUsuario.setString(8, user.getMatricula().toString());
            createUsuario.setString(9, Rol.getRolChar(user.getRol()));
            createUsuario.executeQuery();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    public void deleteUser(Usuario killUser){
        try {
            deleteUsuarioLogic.setInt(1, killUser.getCedula());
            if(!deleteUsuarioLogic.execute()){
                JOptionPane.showMessageDialog(null, killUser.getNombres()+" ha sido eliminado definitivamente");
            }else{
                JOptionPane.showMessageDialog(null, "ERROR!, no se ha podido eliminar "+killUser.getNombres());
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    public void deleteProducto (Articulo killArticulo){
        try {
            deleteArticulo.setInt(1, killArticulo.getId());
            if(!deleteArticulo.execute()){
                JOptionPane.showMessageDialog(null, killArticulo.getNombre()+" ha sido eliminado definitivamente");
            }else{
                JOptionPane.showMessageDialog(null, "ERROR!, no se ha podido eliminar "+killArticulo.getNombre());
            }
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
            user.setEstado(data.getBoolean("eliminado"));
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(CompradorServiceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Usuario();
        
    }
    
}
