package practica_2.controladores;

import io.javalin.Javalin;
import practica_2.util.BaseControlador;

public class CookiesSesionesControlador extends BaseControlador {

    public CookiesSesionesControlador(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas(){

        app.post("/autenticar", ctx -> {
            // Recibiendo los valores en los campos usuario y password de un form que envíe a la acción autenticar.
            String usuario = ctx.formParam("usuario");
            String password = ctx.formParam("password");

            ctx.sessionAttribute("usuario", usuario);
            ctx.redirect("/");

        });

    }
    
}