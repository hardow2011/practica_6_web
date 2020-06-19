package practica_2.controladores;

import io.javalin.Javalin;
import practica_2.encapsulaciones.Producto;
import practica_2.encapsulaciones.Usuario;
import practica_2.util.BaseControlador;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.lang.ProcessBuilder.Redirect;
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

                // before(ctx -> {
                //     Usuario usuario = ctx.sessionAttribute("usuario");
                //     if(usuario == null){
                //         ctx.redirect("/login.html");
                //     }
                // });
                
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

                get("/crear", ctx -> {
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("accion", "/crud-productos/crear");
                    modelo.put("titulo", "Crear");
                    ctx.render("/templates/crearEditarVisualizar.ftl", modelo);
                });

                post("/crear", ctx -> {
                    String nombreProducto = ctx.formParam("nombreProducto");
                    double precioProducto = ctx.formParam("precioProducto", Double.class).get();

                    tienda.agregarProducto(nombreProducto, precioProducto);
                    ctx.redirect("/crud-productos");
                });

                get("editar/:idProducto", ctx -> {
                    Producto producto = tienda.getProductoPorId(Integer.parseInt(ctx.pathParam("idProducto")));

                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("producto", producto);
                    modelo.put("accion", "/crud-productos/editar");
                    modelo.put("titulo", "Editar");

                    // ctx.result(producto.getId() + " " + producto.getNombre() + " " + producto.getPrecio());
                    ctx.render("/templates/crearEditarVisualizar.ftl", modelo);
                });

                post("editar/", ctx -> {
                    int idProducto = ctx.formParam("idProducto", Integer.class).get();
                    String nuevoNombreProducto = ctx.formParam("nombreProducto");
                    Double nuevoPrecioProducto = ctx.formParam("precioProducto", Double.class).get();

                    Producto productoModificado = new Producto(idProducto, nuevoNombreProducto, nuevoPrecioProducto);

                    tienda.modificarProducto(productoModificado);
                    ctx.redirect("/crud-productos/listar");

                });

            });
        });
        
    }
    
}