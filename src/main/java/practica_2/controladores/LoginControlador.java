package practica_2.controladores;

import io.javalin.Javalin;
import practica_2.encapsulaciones.Usuario;
import practica_2.encapsulaciones.CarroCompra;
import practica_2.util.BaseControlador;
// import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.HashMap;
import java.util.Map;

public class LoginControlador extends BaseControlador {

    Tienda tienda = Tienda.getInstancia();

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


                before("/crear-usuario", ctx -> {
                    Usuario usuario = ctx.sessionAttribute("usuario");
                    if(usuario == null){
                        ctx.redirect("/login.html");
                    }
                });

                get("/", ctx -> {
                    ctx.redirect("/carro-compra");
                });

                get("/crear-usuario", ctx -> {
                    Map<String, Object> modelo = new HashMap<>();
                    if(ctx.sessionAttribute("carroCompra") != null){
                        modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                    }else{
                        modelo.put("tamagnoCarritoCompra", 0);
                    }
                    modelo.put("accion", "/crear-usuario");
                    ctx.render("/templates/crearUsuario.ftl", modelo);
                });

                post("/crear-usuario", ctx -> {
                    String nombreUsuario = ctx.formParam("usuario");
                    String nombrePersona = ctx.formParam("nombre");
                    String password = ctx.formParam("password");
                    tienda.agregarUsuario(nombreUsuario, nombrePersona, password);
                    // Map<String, Object> modelo = new HashMap<>();
                    // modelo.put("tamagnoCarritoCompra", tienda.getListaProductosConMasDeCeroCantidad().size());
                    // modelo.put("accion", "/crear-usuario");
                    ctx.redirect("crud-productos");
                });

            });
        });


    }
    
}