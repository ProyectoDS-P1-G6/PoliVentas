package services;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnection {
//    private static Connection connection = null;
//    private static final String USER = "poliventas";
//    private static final String PASS = "1controlXYZW#";
//    private static final String DATABASE_PATH = "jdbc:mysql://den1.mysql4.gear.host/poliventas?useSSL=false";
//    
    private static Connection connection = null;
   // private static final String USER = "neythan";
    //private static final String PASS = "root";
  //  private static final String DATABASE_PATH = "jdbc:mysql://localhost:3306/poliventas?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "Root24021997-";
        private static final String DATABASE_PATH = "jdbc:mysql://localhost:3306/POLIVENTAS?useSSL=false";
    static void createConnection(){

        if(connection != null){
            System.out.println("Ya existe una conección a la base de datos");
        }
        else {
            try {
                connection = DriverManager.getConnection(DATABASE_PATH ,USER ,PASS );
            } catch (SQLException e){
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, "Problema al crear la conexión con la base de datos");
            } catch (Exception e){
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,"Error: "+ e.getMessage()+ " causa: "+ e.getCause() );
            }
        }
    }

    public static Connection getInstance() {

        if(connection == null)
            createConnection();

        return connection;
    }

    public static void shutdownConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,"Error: "+ e.getMessage()+ " causa: "+ e.getCause() );
            }
        }
    }
}
