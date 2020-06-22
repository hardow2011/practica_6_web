package practica_2.controladores;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import practica_2.encapsulaciones.CarroCompra;
import practica_2.encapsulaciones.Producto;
import practica_2.encapsulaciones.Usuario;
import practica_2.encapsulaciones.VentasProductos;

public class Tienda {

    private static Tienda instancia;
    private List<Usuario> listaUsuarios;
    private List<Producto> listaProductos;
    private List<VentasProductos> listaVentasProductos;
    private CarroCompra carroCompra;

    public Tienda(){
        listaUsuarios = new ArrayList<>();
        listaProductos = new ArrayList<>();
        listaVentasProductos = new ArrayList<>();
        carroCompra = new CarroCompra();
        
        agregarUsuario("admin", "admin", "admin");
    }

    public static Tienda getInstancia() {
        if(instancia == null){
            instancia = new Tienda();
        }
        return instancia;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public List<VentasProductos> getListaVentasProductos() {
        return listaVentasProductos;
    }

    public void agregarVentaProducto(List<Producto> listaProductos, String nombreCliente){
        VentasProductos ventasProductos = new VentasProductos(nombreCliente, clonarLista(listaProductos));

        // Obtengo el total del carrito
        Double total = getTotalCarrito();
        ventasProductos.setTotalCompra(total);

        listaVentasProductos.add(ventasProductos);

        // Después de comprar  los productos, se vacía el carrito de compras
        for(int i = 0; i < getListaProductos().size(); i++){
            getListaProductos().get(i).setCantidad(0);
        }

    }

    // Es nececario clonar la lista de productos al pasarla a la venta. Porque de otro modo, si se modifica...
    // el producto en la tienda, también se modifica en la venta.
    // Eso no es deseable ya que las cantidades de todos los productos se reinician a cero después de una venta.
    public static List<Producto> clonarLista(List<Producto> listaProductos) {
        List<Producto> clonedList = new ArrayList<Producto>(listaProductos.size());
        for (Producto producto : listaProductos) {
            clonedList.add(new Producto(producto));
        }
        return clonedList;
    }
    
    public void setCantidades(List<Integer> listaCantidades) {
        for(int i = 0; i < listaCantidades.size(); i++){
            listaProductos.get(i).setCantidad(listaCantidades.get(i));
        }
    }

    public void agregarUsuario(String nombreUsuario, String nombrePersona, String password){
        Usuario usuario = new Usuario(nombreUsuario, nombrePersona, password);
        listaUsuarios.add(usuario);
    }

    public void agregarProducto(String nombre, double precio){
        Producto producto = new Producto(nombre, precio);
        listaProductos.add(producto);
    }

    public Producto getProductoPorId(int id){
        return listaProductos.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public void modificarProducto(Producto producto) {
        Producto productoAModificar = getProductoPorId(producto.getId());

        listaProductos.set(listaProductos.indexOf(productoAModificar), producto);
    }

	public void eliminarProducto(Producto producto) {
        listaProductos.remove(producto);
    }
    
    public List<Producto> getListaProductosConMasDeCeroCantidad() {

        List<Producto> listaProductosConMasDeUnaCantidad = new ArrayList<>();

        // Recupero todos los productos con la cantidad mayor a cero y los agrego al arreglo productosConMasDeUnaCantidad
        for(int i = 0; i < getListaProductos().size(); i++){
            if(getListaProductos().get(i).getCantidad() > 0){
                listaProductosConMasDeUnaCantidad.add(getListaProductos().get(i));
            }
        }
        return listaProductosConMasDeUnaCantidad;
    }

    public Double getTotalCarrito() {
        Double total = (double) 0;

        for(int i = 0; i < getListaProductosConMasDeCeroCantidad().size(); i++){
            total += getListaProductosConMasDeCeroCantidad().get(i).getPrecio() * getListaProductosConMasDeCeroCantidad().get(i).getCantidad();
        }

        return total;
        
    }

}