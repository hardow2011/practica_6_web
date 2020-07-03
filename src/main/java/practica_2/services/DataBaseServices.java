package practica_2.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseServices {
    
    private static DataBaseServices instancia;
    private String URL = "jdbc:h2:tcp://localhost/~/practica_4_web"; // Modo Server

    private DataBaseServices() {
        registrarDriver();
    }

    /**
     * Retornando la instancia.
     * @return
     */
    public static DataBaseServices getInstancia(){
        if(instancia==null){
             instancia = new DataBaseServices();
        }
        return instancia;
    }

    /**
     * Método para el registro de driver de conexión.
     */
    private void registrarDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, "sa", "");
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void testConexion() {
        try {
            getConexion().close();
            System.out.println("Conexión realizado con éxito...");
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}