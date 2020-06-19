package practica_2.controladores;


import io.javalin.Javalin;
import practica_2.encapsulaciones.Usuario;
import practica_2.util.BaseControlador;
// import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

public class LoginControlador extends BaseControlador {

    public LoginControlador(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas() {

        app.routes(() -> {

            path("/", () -> {
                // before("/", ctx -> {
                //     Usuario usuario = ctx.sessionAttribute("usuario");
                //     if(usuario == null){
                //         ctx.redirect("/login.html");
                        
                //     }
                // });

                get("/", ctx -> {
                    ctx.redirect("/crud-productos/listar");
                });

            });
        });


    }
    
}