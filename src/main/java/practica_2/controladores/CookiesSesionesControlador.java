package practica_2.controladores;

import io.javalin.Javalin;
import practica_2.encapsulaciones.Usuario;
import practica_2.util.BaseControlador;

public class CookiesSesionesControlador extends BaseControlador {

    public CookiesSesionesControlador(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas(){

        app.post("/autenticar", ctx -> {
            // Recibiendo los valores en los campos usuario y password de un form que envíe a la acción autenticar.
            String texto_usuario = ctx.formParam("usuario");
            String nombre = ctx.formParam("nombre");
            String password = ctx.formParam("password");

            Usuario usuario = new Usuario(texto_usuario, nombre, password);

            ctx.sessionAttribute("usuario", usuario);
            // ctx.redirect("/");

        });

    }
    
}