package practica_2.controladores;

import io.javalin.Javalin;
import practica_2.encapsulaciones.Producto;
import practica_2.encapsulaciones.Usuario;
import practica_2.util.BaseControlador;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CrudControlador extends BaseControlador {

    Tienda tienda = Tienda.getInstancia();

    public CrudControlador(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas() {
        app.routes(() -> {
            path("/crud-productos", () -> {

                before(ctx -> {
                    Usuario usuario = ctx.sessionAttribute("usuario");
                    if(usuario == null){
                        ctx.redirect("/login.html");
                    }
                });
                
                get("/", ctx -> {
                    ctx.redirect("/crud-productos/listar");
                });

                get("/listar", ctx -> {
                    // Recuperando el listado de productos del modelo Singleton.
                    List<Producto> listaProductos = tienda.getListaProductos();

                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("listaProductos", listaProductos);
                    ctx.render("templates/listaProductos.ftl", modelo);
                });

            });
        });
        
    }
    
}