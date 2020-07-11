package practica_2.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;

public class BootStrapServices {

    private static BootStrapServices instancia;

    private BootStrapServices(){

    }

    public static BootStrapServices getInstancia(){
        if(instancia == null){
            instancia=new BootStrapServices();
        }
        return instancia;
    }

    /**
     *
     * @throws SQLException
     */
    public static void startDb(){
        try {
            // Modo servidor H2.
            Server.createTcpServer("-tcpPort",
                    "9092",
                    "-tcpAllowOthers",
                    "-tcpDaemon",
                    "-ifNotExists").start();
            //Abriendo el cliente web. El valor 0 representa puerto aleatorio.
            String status = Server.createWebServer("-trace", "-webPort", "0").start().getStatus();
            System.out.println("Status Web: "+status);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Problema con la base de datos: "+e.getMessage());
        }

    }

    public void init(){
        startDb();
   }
    
}