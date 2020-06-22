package practica_2.controladores;

import io.javalin.Javalin;
import practica_2.encapsulaciones.CarroCompra;
import practica_2.util.BaseControlador;
import practica_2.encapsulaciones.Producto;
import practica_2.encapsulaciones.VentasProductos;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CarroCompraControlador extends BaseControlador {

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
                    List<Producto> listaProductos = tienda.getListaProductos();

                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("listaProductos", listaProductos);
                    modelo.put("accion", "/carro-compra/compras");
                    modelo.put("tamagnoCarritoCompra", tienda.getListaProductosConMasDeCeroCantidad().size());
                    ctx.render("templates/vistaTienda.ftl", modelo);
                });

                get("/compras", ctx -> {


                    // ctx.result(Arrays.toString(listaCantidades.toArray()));
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("tituloVentana", "Titulo Plantilla");
                    modelo.put("titulo", "Titulo Plantilla");
                    modelo.put("listaProductosConMasDeUnaCantidad", tienda.getListaProductosConMasDeCeroCantidad());
                    modelo.put("tamagnoCarritoCompra", tienda.getListaProductosConMasDeCeroCantidad().size());
                    modelo.put("total", tienda.getTotalCarrito());
                    ctx.render("templates/carroCompra.ftl", modelo);

                });

                post("/compras", ctx -> {
                    List<Integer> listaCantidades = new ArrayList<Integer>();

                    // Se pasan todos los elementos recibidos por cantidadProducto de vistaTienda a listaCantidades...
                    // Pero primero hay que convertir a int, porque llegan como String.
                    for(String s : ctx.formParams("cantidadProducto")) listaCantidades.add(Integer.valueOf(s));

                    tienda.setCantidades(listaCantidades);

                    // ctx.result(Arrays.toString(listaCantidades.toArray()));
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("tituloVentana", "Titulo Plantilla");
                    modelo.put("titulo", "Titulo Plantilla");
                    modelo.put("listaProductosConMasDeUnaCantidad", tienda.getListaProductosConMasDeCeroCantidad());
                    modelo.put("tamagnoCarritoCompra", tienda.getListaProductosConMasDeCeroCantidad().size());
                    modelo.put("total", tienda.getTotalCarrito());
                    ctx.render("templates/carroCompra.ftl", modelo);

                });

                get("/eliminar/:idProducto", ctx -> {
                    int idProducto = ctx.pathParam("idProducto", Integer.class).get();
                    tienda.getProductoPorId(idProducto).setCantidad(0);

                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("tituloVentana", "Titulo Plantilla");
                    modelo.put("titulo", "Titulo Plantilla");
                    modelo.put("listaProductosConMasDeUnaCantidad", tienda.getListaProductosConMasDeCeroCantidad());
                    modelo.put("tamagnoCarritoCompra", tienda.getListaProductosConMasDeCeroCantidad().size());
                    modelo.put("total", tienda.getTotalCarrito());
                    ctx.render("templates/carroCompra.ftl", modelo);
                });

                post("/procesar-compra", ctx -> {
                    String nombreCliente = ctx.formParam("nombreCliente");
                    tienda.agregarVentaProducto(tienda.getListaProductosConMasDeCeroCantidad(), nombreCliente);
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("listaVentasProductos", tienda.getListaVentasProductos());
                    modelo.put("tamagnoCarritoCompra", tienda.getListaProductosConMasDeCeroCantidad().size());
                    ctx.render("templates/ventasProductos.ftl", modelo);
                });

                get("ventas-productos", ctx -> {
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("listaVentasProductos", tienda.getListaVentasProductos());
                    modelo.put("tamagnoCarritoCompra", tienda.getListaProductosConMasDeCeroCantidad().size());
                    ctx.render("templates/ventasProductos.ftl", modelo);
                });

            });
        });

    }
    
}