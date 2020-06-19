/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package practica_2;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.http.staticfiles.Location;
import practica_2.controladores.*;

public class Main {

    public static void main(String[] args) {
        // System.out.println(new Main().getGreeting());
        Javalin app = Javalin.create(config ->{
            // Si la carpeta /publico no tiene ningún archivo, el build de Gradle fallará.
             config.addStaticFiles("/publico");
            // config.registerPlugin(new RouteOverviewPlugin("/rutas"));
        }).start();

        new LoginControlador(app).aplicarRutas();
        new CookiesSesionesControlador(app).aplicarRutas();
    }
}