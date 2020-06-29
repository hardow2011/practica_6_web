package practica_2.controladores;

import io.javalin.Javalin;
import practica_2.encapsulaciones.Producto;
import practica_2.encapsulaciones.CarroCompra;
import practica_2.encapsulaciones.Usuario;
import practica_2.services.ProductoServices;
import practica_2.util.BaseControlador;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CrudControlador extends BaseControlador {

    ProductoServices productoServices = new ProductoServices();
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
                    List<Producto> listaProductos = productoServices.listaProductos();

                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("listaProductos", listaProductos);
                    if(ctx.sessionAttribute("carroCompra") != null){
                        modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                    }else{
                        modelo.put("tamagnoCarritoCompra", 0);
                    }
                    if(ctx.sessionAttribute("usuario") != null){
                        modelo.put("conectado", "true");
                        modelo.put("nombreUsuario", ((Usuario) ctx.sessionAttribute("usuario")).getUsuario());
                    }
                    ctx.render("templates/listaProductos.ftl", modelo);
                });

                get("/crear", ctx -> {
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("accion", "/crud-productos/crear");
                    modelo.put("titulo", "Crear");
                    if(ctx.sessionAttribute("carroCompra") != null){
                        modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                    }else{
                        modelo.put("tamagnoCarritoCompra", 0);
                    }
                    if(ctx.sessionAttribute("usuario") != null){
                        modelo.put("conectado", "true");
                        modelo.put("nombreUsuario", ((Usuario) ctx.sessionAttribute("usuario")).getUsuario());
                    }
                    ctx.render("/templates/crearEditarVisualizar.ftl", modelo);
                });

                post("/crear", ctx -> {
                    String nombreProducto = ctx.formParam("nombreProducto");
                    double precioProducto = ctx.formParam("precioProducto", Double.class).get();

                    // tienda.agregarProducto(nombreProducto, precioProducto);
                    productoServices.crearProducto(nombreProducto, precioProducto);
                    ctx.redirect("/crud-productos");
                });

                get("editar/:idProducto", ctx -> {
                    // Producto producto = tienda.getProductoPorId(Integer.parseInt(ctx.pathParam("idProducto")));
                    Producto producto = productoServices.getProductoPorId(Integer.parseInt(ctx.pathParam("idProducto")));

                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("producto", producto);
                    modelo.put("accion", "/crud-productos/editar");
                    modelo.put("titulo", "Editar");

                    // ctx.result(producto.getId() + " " + producto.getNombre() + " " + producto.getPrecio());
                    if(ctx.sessionAttribute("carroCompra") != null){
                        modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                    }else{
                        modelo.put("tamagnoCarritoCompra", 0);
                    }
                    if(ctx.sessionAttribute("usuario") != null){
                        modelo.put("conectado", "true");
                        modelo.put("nombreUsuario", ((Usuario) ctx.sessionAttribute("usuario")).getUsuario());
                    }
                    ctx.render("/templates/crearEditarVisualizar.ftl", modelo);
                });

                post("editar/", ctx -> {
                    int id = ctx.formParam("idProducto", Integer.class).get();
                    String nombre = ctx.formParam("nombreProducto");
                    Double precio = ctx.formParam("precioProducto", Double.class).get();

                    // Obtener la cantidad de un producto en caso de que alguien esté en medio de hacer su carrito de compras...
                    // así no pierde la cantidad que había seleccionado.

                    productoServices.modificarProducto(id, nombre, precio);
                    ctx.redirect("/crud-productos/listar");

                });

                get("visualizar/:idProducto", ctx ->{
                    // Producto producto = tienda.getProductoPorId(Integer.parseInt(ctx.pathParam("idProducto")));
                    Producto producto = productoServices.getProductoPorId(Integer.parseInt(ctx.pathParam("idProducto")));
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("producto", producto);
                    modelo.put("accion", "");
                    modelo.put("titulo", "Visualizar");
                    modelo.put("visualizar", true);
                    if(ctx.sessionAttribute("carroCompra") != null){
                        modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                    }else{
                        modelo.put("tamagnoCarritoCompra", 0);
                    }
                    if(ctx.sessionAttribute("usuario") != null){
                        modelo.put("conectado", "true");
                        modelo.put("nombreUsuario", ((Usuario) ctx.sessionAttribute("usuario")).getUsuario());
                    }
                    ctx.render("/templates/crearEditarVisualizar.ftl", modelo);

                });

                get("/eliminar/:idProducto", ctx -> {
                    // int idProducto = ctx.pathParam("idProducto", Integer.class).get();
                    // Producto producto = tienda.getProductoPorId(idProducto);
                    productoServices.eliminarProducto(Integer.parseInt(ctx.pathParam("idProducto")));
                    ctx.redirect("/crud-productos/listar");
                });

            });
        });
        
    }
    
}