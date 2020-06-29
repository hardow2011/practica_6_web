package practica_2.controladores;

import io.javalin.Javalin;
import practica_2.encapsulaciones.CarroCompra;
import practica_2.util.BaseControlador;
import practica_2.encapsulaciones.Producto;
import practica_2.encapsulaciones.VentasProductos;
import practica_2.services.ProductoServices;
import practica_2.services.VentaServices;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CarroCompraControlador extends BaseControlador {

    ProductoServices productoServices = new ProductoServices();
    VentaServices ventaServices = new VentaServices();
    Tienda tienda = Tienda.getInstancia();

    public CarroCompraControlador(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas() {
        
        app.routes(() -> {
            path("/carro-compra", () -> {

                get("/", ctx -> {
                    ctx.redirect("/carro-compra/vista-tienda");
                });

                get("/vista-tienda", ctx -> {
                    List<Producto> listaProductos = productoServices.listaProductos();
                    // Crear una lista de enteros del mismo tamaño que la lista de productos llena de ceros.
                    List<Integer> listaCantidades = new ArrayList<Integer>(Collections.nCopies(listaProductos.size(), 0));

                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("listaProductos", listaProductos);
                    modelo.put("listaCantidades", listaCantidades);
                    modelo.put("accion", "/carro-compra/compras");
                    if(ctx.sessionAttribute("carroCompra") != null){
                        modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                    }else{
                        modelo.put("tamagnoCarritoCompra", 0);
                    }
                    ctx.render("templates/vistaTienda.ftl", modelo);
                });

                get("/compras", ctx -> {


                    // ctx.result(Arrays.toString(listaCantidades.toArray()));
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("tituloVentana", "Titulo Plantilla");
                    modelo.put("titulo", "Titulo Plantilla");
                    modelo.put("carroCompra", ctx.sessionAttribute("carroCompra"));
                    if(ctx.sessionAttribute("carroCompra") != null){
                        modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                    }else{
                        modelo.put("tamagnoCarritoCompra", 0);
                    }
                    if(ctx.sessionAttribute("carroCompra") != null){
                        modelo.put("total", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getTotalCarrito());
                    }
                    ctx.render("templates/carroCompra.ftl", modelo);

                });

                post("/compras", ctx -> {

                    System.out.println(ctx.sessionAttribute("carroCompra") == null);

                    // Se pasan todos los elementos recibidos por cantidadProducto de vistaTienda a listaCantidades...
                    // Pero primero hay que convertir a int, porque llegan como String.
                    List<Integer> listaCantidades = new ArrayList<Integer>();
                    for(String s : ctx.formParams("listaCantidades")) listaCantidades.add(Integer.valueOf(s));

                    // Busco si hay un carro de compras en el contexto de sesión
                    CarroCompra carroCompra = ctx.sessionAttribute("carroCompra");

                    List<Producto> listaProductos = productoServices.listaProductos();
                    // Si no hay un carro de compras en el contexto de sesión
                    if(carroCompra == null){
                        // System.out.println("Era nulo");
                        carroCompra = new CarroCompra();
                        // Agrego todos los productos que tuvieron más de cero cantidades a un carro de compras
                        for(int i = 0; i < listaCantidades.size(); i++){
                            if(listaCantidades.get(i) != 0){
                                carroCompra.insertarProducto(listaProductos.get(i), listaCantidades.get(i));
                            }
                        }
                    // De otro modo
                    }else{
                        // System.out.println("No era nulo");
                        System.out.println(carroCompra.getListaCantidades().size());
                        for(int i = 0; i < listaCantidades.size(); i++){
                            if(listaCantidades.get(i) != 0){
                                System.out.println("Un prod");
                                if(carroCompra.getListaCantidades().size() == 0){
                                    carroCompra.insertarProducto(listaProductos.get(i), listaCantidades.get(i));
                                    System.out.println("Cond 1");
                                    continue;
                                }
                                for(int j = 0; j < carroCompra.getListaCantidades().size(); j++){
                                    // Le sumo la nueva cantidad a los productos ya existentes
                                    System.out.println("Entro aquiiiii");
                                    if(listaProductos.get(i).getId() == carroCompra.getListaProductos().get(j).getId()){
                                        System.out.println("Cond 2");
                                        carroCompra.getListaCantidades().set(j, carroCompra.getListaCantidades().get(j)+listaCantidades.get(i));
                                        break;
                                    }
                                    // O si el producto no estaba en el carro de compras, lo agrego
                                    else if(j ==  carroCompra.getListaCantidades().size()-1){
                                        carroCompra.insertarProducto(listaProductos.get(i), listaCantidades.get(i));
                                        System.out.println("Cond 3");
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    ctx.sessionAttribute("carroCompra", carroCompra);

                    // tienda.setCantidades(listaCantidades);

                    // ctx.result(Arrays.toString(listaCantidades.toArray()));
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("tituloVentana", "Titulo Plantilla");
                    modelo.put("titulo", "Titulo Plantilla");
                    modelo.put("carroCompra", ctx.sessionAttribute("carroCompra"));
                    if(ctx.sessionAttribute("carroCompra") != null){
                        modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                    }else{
                        modelo.put("tamagnoCarritoCompra", 0);
                    }
                    if(ctx.sessionAttribute("carroCompra") != null){
                        modelo.put("total", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getTotalCarrito());
                    }
                    ctx.render("templates/carroCompra.ftl", modelo);

                });

                get("/eliminar/:idProducto", ctx -> {
                    int idProducto = ctx.pathParam("idProducto", Integer.class).get();
                    // tienda.getProductoPorId(idProducto).setCantidad(0);

                    CarroCompra carroCompraActual = ctx.sessionAttribute("carroCompra");
                    CarroCompra nuevoCarroCompra = new CarroCompra();

                    for(int i = 0; i < carroCompraActual.getListaCantidades().size(); i++){
                        if(carroCompraActual.getListaProductos().get(i).getId() != idProducto){
                            nuevoCarroCompra.insertarProducto(carroCompraActual.getListaProductos().get(i), carroCompraActual.getListaCantidades().get(i));
                        }
                    }

                    // Para eliminar un producto del carro de compra, se borra el atributo de sesión y se crea de nuevo si el producto.
                    ctx.req.removeAttribute("carroCompra");
                    ctx.sessionAttribute("carroCompra", nuevoCarroCompra);

                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("tituloVentana", "Titulo Plantilla");
                    modelo.put("titulo", "Titulo Plantilla");
                    modelo.put("carroCompra", ctx.sessionAttribute("carroCompra"));
                    if(ctx.sessionAttribute("carroCompra") != null){
                        modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                    }else{
                        modelo.put("tamagnoCarritoCompra", 0);
                    }
                    modelo.put("total", tienda.getTotalCarrito());
                    ctx.render("templates/carroCompra.ftl", modelo);
                });

                post("/procesar-compra", ctx -> {
                    String nombreCliente = ctx.formParam("nombreCliente");
                    ventaServices.crearVenta(nombreCliente, ((CarroCompra) ctx.sessionAttribute("carroCompra")));
                    // Vaciar el carro de compras después de procesar la compra.
                    ctx.req.getSession().setAttribute("carroCompra", new CarroCompra());
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("listaVentas", ventaServices.getListaVentas());
                    if(ctx.sessionAttribute("carroCompra") != null){
                        modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                    }else{
                        modelo.put("tamagnoCarritoCompra", 0);
                    }
                    ctx.render("templates/ventasProductos.ftl", modelo);
                });

                get("ventas-productos", ctx -> {
                    Map<String, Object> modelo = new HashMap<>();
                    // for(int i = 0; i < ventaServices.getListaVentas().size(); i++){
                    //     for(int j = 0; j < ventaServices.getListaVentas().get(i).getListaProductos().size(); j++){
                    //         System.out.println(ventaServices.getListaVentas().get(i).getId()+": "+ventaServices.getListaVentas().get(i).getListaProductos().get(j).getNombre()+"("+ventaServices.getListaVentas().get(i).getListaCantidades().get(j)+")");
                    //     }
                    // }
                    modelo.put("listaVentas", ventaServices.getListaVentas());
                    if(ctx.sessionAttribute("carroCompra") != null){
                        modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                    }else{
                        modelo.put("tamagnoCarritoCompra", 0);
                    }
                    ctx.render("templates/ventasProductos.ftl", modelo);
                });

            });
        });

    }
    
}