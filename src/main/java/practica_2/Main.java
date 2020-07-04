/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package practica_2;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.http.staticfiles.Location;
import practica_2.controladores.*;
import practica_2.encapsulaciones.Producto;
import practica_2.services.BootStrapServices;
import practica_2.services.DataBaseServices;
import practica_2.services.ProductoServices;

public class Main {

    public static void main(String[] args) throws SQLException {
        
        //Iniciando la base de datos.
        BootStrapServices.getInstancia().init();

        ProductoServices.getInstancia().crear(new Producto("mascarilla", 152));

        // Javalin app = Javalin.create(config ->{
        //     // Si la carpeta /publico no tiene ningún archivo, el build de Gradle fallará.
        //      config.addStaticFiles("/publico");
        //     // config.registerPlugin(new RouteOverviewPlugin("/rutas"));
        // }).start();

        // new LoginControlador(app).aplicarRutas();
        // new CookiesSesionesControlador(app).aplicarRutas();
        // new CrudControlador(app).aplicarRutas();
        // new CarroCompraControlador(app).aplicarRutas();

    }
}
