package practica_2.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class BootStrapServices {

    static StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
    static UsuarioServices usuarioServices = new UsuarioServices();

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
                "  PRECIO FLOAT NOT NULL\n" +
                ");"+
                // "\n"+
                // "INSERT INTO PRODUCTO(NOMBRE, PRECIO) "+
                // "VALUES('Arroz', 75.99);\n"+
                // "INSERT INTO PRODUCTO(NOMBRE, PRECIO) "+
                // "VALUES('Globos', 15);\n"+
                // "INSERT INTO PRODUCTO(NOMBRE, PRECIO) "+
                // "VALUES('Gafas de sol', 250);"+
                "\n"+
                "CREATE TABLE IF NOT EXISTS VENTA\n" +
                "(\n" +
                "ID INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "FECHA DATE NOT NULL,\n" +
                "NOMBRECLIENTE VARCHAR(100) NOT NULL,\n" +
                "TOTALCOMPRA FLOAT NOT NULL\n"+
                ");"+
                "\n"+
                "CREATE TABLE IF NOT EXISTS PRODUCTOVENDIDO\n" +
                "(\n"+
                "ID INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "NOMBRE VARCHAR(100) NOT NULL,\n" +
                "PRECIO FLOAT NOT NULL,\n" +
                "CANTIDAD INT NOT NULL,\n" +
                "VENTA_ID INT NOT NULL,\n" +
                "FOREIGN KEY (VENTA_ID) REFERENCES VENTA(ID)\n"+
                ");"+
                "\n"+
                "CREATE TABLE IF NOT EXISTS USUARIO\n" +
                "(\n"+
                "ID INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "NOMBREUSUARIO VARCHAR(100) NOT NULL,\n" +
                "NOMBREPERSONA VARCHAR(100) NOT NULL,\n" +
                "CONTRASEGNA VARCHAR(100) NOT NULL\n" +
                ");";
        Connection con = DataBaseServices.getInstancia().getConexion();
        Statement statement = con.createStatement();
        statement.execute(sql);
        statement.close();
        con.close();

        // Si la tabla de usuarios está vacía, agregar el usuario admin
        if(usuarioServices.getListaUsuarios().size() == 0){
            usuarioServices.crearUsuario("admin", "admin", passwordEncryptor.encryptPassword("admin"));
        }

    }
    
}