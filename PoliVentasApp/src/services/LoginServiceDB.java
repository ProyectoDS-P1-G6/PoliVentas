package services;

import models.AuthInfo;
import models.entities.Administrador;
import models.entities.Comprador;
import models.entities.Vendedor;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import models.entities.AdministradorBuilder;
import models.entities.CompradorBuilder;
import models.entities.VendedorBuilder;
import models.entities.Usuario;


public class LoginServiceDB {


    private CallableStatement authUserMethod;
    private CallableStatement getUser;
    private AuthInfo actualLogin;

    public LoginServiceDB(){

        DBConnection.createConnection();

        try {
            authUserMethod = DBConnection.getInstance().prepareCall("{CALL checkUserAndPass(?,?,?)}");
            getUser        = DBConnection.getInstance().prepareCall("{CALL getUser(?)}");
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
                getUser.setInt(1, authUserMethod.getInt(3));
                getUser.execute();
                ResultSet data = getUser.getResultSet();
                data.next();
                String tipo = data.getString("tipo");
                
                switch(tipo){
                    case "A":
                        Administrador administrador = new AdministradorBuilder()
                                .setCedula(data.getInt(1))
                                .setNombres(data.getString(2))
                                .setApellidos(data.getString(3))
                                .setContactInfo(data.getString(4), data.getInt(5), data.getBoolean(6))
                                .setDireccion(data.getString(7))
                                .setMatricula(data.getInt(8))
                                .setSaldo(data.getDouble(9))
                                .build();
                        info.setUsuario(administrador);
                        System.out.println(administrador);
                        break;
                    case "C":
                        Comprador comprador = new CompradorBuilder()
                                .setCedula(data.getInt(1))
                                .setNombres(data.getString(2))
                                .setApellidos(data.getString(3))
                                .setContactInfo(data.getString(4), data.getInt(5), data.getBoolean(6))
                                .setDireccion(data.getString(7))
                                .setMatricula(data.getInt(8))
                                .setSaldo(data.getDouble(9))
                                .build();
                        info.setUsuario(comprador);
                        System.out.println(comprador);
                        break;
                    case "V":
                        Vendedor vendedor = new VendedorBuilder()
                                .setCedula(data.getInt(1))
                                .setNombres(data.getString(2))
                                .setApellidos(data.getString(3))
                                .setContactInfo(data.getString(4), data.getInt(5), data.getBoolean(6))
                                .setDireccion(data.getString(7))
                                .setMatricula(data.getInt(8))
                                .setSaldo(data.getDouble(9))
                                .build();
                        info.setUsuario(vendedor);
                        System.out.println(vendedor);
                        break;
                };
              
            }else{
                info.setLoggeoExitoso(Boolean.FALSE);
            }
                
   

        } catch (SQLException e) {
            System.out.printf("%s  %s", e.getCause(), e.getMessage());
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
