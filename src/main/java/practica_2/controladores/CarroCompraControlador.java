package practica_2.controladores;

import io.javalin.Javalin;
import practica_2.util.BaseControlador;
import practica_2.entidades.*;
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
                    List<Producto> listaProductos = ProductoServices.getInstancia().listar();
                    // Crear una lista de enteros del mismo tamaño que la lista de productos llena de ceros.
                    List<Integer> listaCantidades = new ArrayList<Integer>(Collections.nCopies(listaProductos.size(), 0));

                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("listaProductos", listaProductos);
                    modelo.put("listaCantidades", listaCantidades);
                    modelo.put("accion", "/carro-compra/compras");
                    if(ctx.sessionAttribute("carroCompra") != null){
                        // modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                        modelo.put("tamagnoCarritoCompra", 74);
                    }else{
                        modelo.put("tamagnoCarritoCompra", 0);
                    }
                    if(ctx.sessionAttribute("usuario") != null){
                        modelo.put("conectado", "true");
                        // modelo.put("nombreUsuario", ((Usuario) ctx.sessionAttribute("usuario")).getUsuario());
                        modelo.put("nombreUsuario", "LecheDeSoya");
                    }
                    ctx.render("templates/vistaTienda.ftl", modelo);
                });

                // get("/compras", ctx -> {


                //     // ctx.result(Arrays.toString(listaCantidades.toArray()));
                //     Map<String, Object> modelo = new HashMap<>();
                //     modelo.put("tituloVentana", "Titulo Plantilla");
                //     modelo.put("titulo", "Titulo Plantilla");
                //     modelo.put("carroCompra", ctx.sessionAttribute("carroCompra"));
                //     if(ctx.sessionAttribute("carroCompra") != null){
                //         modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                //     }else{
                //         modelo.put("tamagnoCarritoCompra", 0);
                //     }
                //     if(ctx.sessionAttribute("carroCompra") != null){
                //         modelo.put("total", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getTotalCarrito());
                //     }
                //     if(ctx.sessionAttribute("usuario") != null){
                //         modelo.put("conectado", "true");
                //         modelo.put("nombreUsuario", ((Usuario) ctx.sessionAttribute("usuario")).getUsuario());
                //     }
                //     ctx.render("templates/carroCompra.ftl", modelo);

                // });

                post("/compras", ctx -> {

                    // Se pasan todos los elementos recibidos por cantidadProducto de vistaTienda a listaCantidades...
                    // Pero primero hay que convertir a int, porque llegan como String.
                    List<Integer> listaCantidades = new ArrayList<Integer>();
                    for(String s : ctx.formParams("listaCantidades")) listaCantidades.add(Integer.valueOf(s));

                    // Busco si hay un carro de compras en el contexto de sesión
                    List<ProductoVendido> carroCompra = ctx.sessionAttribute("carroCompra");

                    List<Producto> listaProductos = ProductoServices.getInstancia().listar();
                    // Si no hay un carro de compras en el contexto de sesión
                    if(carroCompra == null){
                        // System.out.println("Era nulo");
                        carroCompra = new ArrayList<ProductoVendido>();
                        // Agrego todos los productos que tuvieron más de cero cantidades a un carro de compras
                        for(int i = 0; i < listaCantidades.size(); i++){
                            if(listaCantidades.get(i) != 0){
                                ProductoVendido productoEnCarrito = new ProductoVendido(listaProductos.get(i).getId(), listaProductos.get(i).getNombre(), listaProductos.get(i).getPrecio(), listaCantidades.get(i));
                                carroCompra.add(productoEnCarrito);
                            }
                        }
                    // De otro modo
                    }
                    else{
                        // System.out.println("No era nulo");
                        for(int i = 0; i < listaCantidades.size(); i++){
                            if(listaCantidades.get(i) != 0){
                                if(carroCompra.size() == 0){
                                    ProductoVendido productoEnCarrito = new ProductoVendido(listaProductos.get(i).getId(), listaProductos.get(i).getNombre(), listaProductos.get(i).getPrecio(), listaCantidades.get(i));
                                    carroCompra.add(productoEnCarrito);
                                    continue;
                                }
                                for(int j = 0; j < carroCompra.size(); j++){
                                    // Le sumo la nueva cantidad a los productos ya existentes
                                    if(listaProductos.get(i).getId() == carroCompra.get(j).getId()){
                                        ProductoVendido productoEnCarrito = carroCompra.get(j);
                                        productoEnCarrito.setCantidad(productoEnCarrito.getCantidad()+listaCantidades.get(i));
                                        carroCompra.set(j, productoEnCarrito);
                                        break;
                                    }
                                    // O si el producto no estaba en el carro de compras, lo agrego
                                    else if(j ==  carroCompra.size()-1){
                                        ProductoVendido productoEnCarrito = new ProductoVendido(listaProductos.get(i).getId(), listaProductos.get(i).getNombre(), listaProductos.get(i).getPrecio(), listaCantidades.get(i));
                                        carroCompra.add(productoEnCarrito);
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
                        modelo.put("tamagnoCarritoCompra", ((List<ProductoVendido>) ctx.sessionAttribute("carroCompra")).size());
                    }else{
                        modelo.put("tamagnoCarritoCompra", 0);
                    }
                    if(ctx.sessionAttribute("carroCompra") != null){
                        double total = 0;
                        for (ProductoVendido productoEnCarrito : ((List<ProductoVendido>) ctx.sessionAttribute("carroCompra"))) {
                            total += productoEnCarrito.getCantidad() * productoEnCarrito.getPrecio();
                        }
                        modelo.put("total", total);
                    }
                    if(ctx.sessionAttribute("usuario") != null){
                        modelo.put("conectado", "true");
                        // modelo.put("nombreUsuario", ((Usuario) ctx.sessionAttribute("usuario")).getUsuario());
                        modelo.put("nombreUsuario", "LecheDeSoya");
                    }
                    ctx.render("templates/carroCompra.ftl", modelo);

                });

                get("/eliminar/:idProducto", ctx -> {
                    int idProducto = ctx.pathParam("idProducto", Integer.class).get();
                    // tienda.getProductoPorId(idProducto).setCantidad(0);


                    List<ProductoVendido> carroCompraActual = ctx.sessionAttribute("carroCompra");
                    List<ProductoVendido> nuevoCarroCompra = new ArrayList<>();

                    for(int i = 0; i < carroCompraActual.size(); i++){
                        if(carroCompraActual.get(i).getId() != idProducto){
                            nuevoCarroCompra.add(carroCompraActual.get(i));
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
                        modelo.put("tamagnoCarritoCompra", ((List<ProductoVendido>) ctx.sessionAttribute("carroCompra")).size());
                    }else{
                        modelo.put("tamagnoCarritoCompra", 0);
                    }
                    double total = 0;
                    for (ProductoVendido productoEnCarrito : ((List<ProductoVendido>) ctx.sessionAttribute("carroCompra"))) {
                        total += productoEnCarrito.getCantidad() * productoEnCarrito.getPrecio();
                    }
                    modelo.put("total", total);
                    if(ctx.sessionAttribute("usuario") != null){
                        modelo.put("conectado", "true");
                        // modelo.put("nombreUsuario", ((Usuario) ctx.sessionAttribute("usuario")).getUsuario());
                        modelo.put("nombreUsuario", "LecheDeSoya");
                    }
                    ctx.render("templates/carroCompra.ftl", modelo);
                });

                // post("/procesar-compra", ctx -> {
                //     String nombreCliente = ctx.formParam("nombreCliente");
                //     ventaServices.crearVenta(nombreCliente, ((CarroCompra) ctx.sessionAttribute("carroCompra")));
                //     // Vaciar el carro de compras después de procesar la compra.
                //     ctx.req.getSession().setAttribute("carroCompra", new CarroCompra());
                //     Map<String, Object> modelo = new HashMap<>();
                //     modelo.put("listaVentas", ventaServices.getListaVentas());
                //     if(ctx.sessionAttribute("carroCompra") != null){
                //         modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                //     }else{
                //         modelo.put("tamagnoCarritoCompra", 0);
                //     }
                //     if(ctx.sessionAttribute("usuario") != null){
                //         modelo.put("conectado", "true");
                //         modelo.put("nombreUsuario", ((Usuario) ctx.sessionAttribute("usuario")).getUsuario());
                //     }
                //     ctx.render("templates/ventasProductos.ftl", modelo);
                // });

                // get("ventas-productos", ctx -> {
                //     Map<String, Object> modelo = new HashMap<>();
                //     // for(int i = 0; i < ventaServices.getListaVentas().size(); i++){
                //     //     for(int j = 0; j < ventaServices.getListaVentas().get(i).getListaProductos().size(); j++){
                //     //         System.out.println(ventaServices.getListaVentas().get(i).getId()+": "+ventaServices.getListaVentas().get(i).getListaProductos().get(j).getNombre()+"("+ventaServices.getListaVentas().get(i).getListaCantidades().get(j)+")");
                //     //     }
                //     // }
                //     modelo.put("listaVentas", ventaServices.getListaVentas());
                //     if(ctx.sessionAttribute("carroCompra") != null){
                //         modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                //     }else{
                //         modelo.put("tamagnoCarritoCompra", 0);
                //     }
                //     if(ctx.sessionAttribute("usuario") != null){
                //         modelo.put("conectado", "true");
                //         modelo.put("nombreUsuario", ((Usuario) ctx.sessionAttribute("usuario")).getUsuario());
                //     }
                //     ctx.render("templates/ventasProductos.ftl", modelo);
                // });

            });
        });

    }
    
}