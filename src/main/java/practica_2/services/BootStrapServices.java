package practica_2.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;

public class BootStrapServices {

    private static BootStrapServices db;
    private static Server tcp;

    /**
     *
     * @throws SQLException
     */
    public static void startDb() throws SQLException {
        // Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
        tcp = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-tcpDaemon", "-ifNotExists").start();
    }

    /**
     *
     * @throws SQLException
     */
    public static void stopDb() throws SQLException {
        // Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
        tcp.stop();
    }

    /**
     * Metodo para recrear las tablas necesarios
     * @throws SQLException
     */
    public static void crearTablas() throws  SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS PRODUCTO\n" +
                "(\n" +
                "  ID INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "  NOMBRE VARCHAR(100) NOT NULL,\n" +
                "  PRECIO FLOAT NOT NULL,\n" +
                "  CANTIDAD INTEGER NOT NULL\n" +
                ");"+
                "\n"+
                "INSERT INTO PRODUCTO(NOMBRE, PRECIO, CANTIDAD) "+
                "VALUES('Arroz', 75.99, 0);\n"+
                "INSERT INTO PRODUCTO(NOMBRE, PRECIO, CANTIDAD) "+
                "VALUES('Globos', 15, 0);\n"+
                "INSERT INTO PRODUCTO(NOMBRE, PRECIO, CANTIDAD) "+
                "VALUES('Gafas de sol', 250, 0);";
        Connection con = DataBaseServices.getInstancia().getConexion();
        Statement statement = con.createStatement();
        statement.execute(sql);
        statement.close();
        con.close();
    }
    
}