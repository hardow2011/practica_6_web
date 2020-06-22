package practica_2.controladores;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import practica_2.encapsulaciones.CarroCompra;
import practica_2.encapsulaciones.Producto;
import practica_2.encapsulaciones.Usuario;

public class Tienda {

    private static Tienda instancia;
    private List<Usuario> listaUsuarios;
    private List<Producto> listaProductos;
    private CarroCompra carroCompra;

    public Tienda(){
        listaUsuarios = new ArrayList<>();
        listaProductos = new ArrayList<>();
        carroCompra = new CarroCompra();

        listaProductos.add(new Producto("Papel de baño", 115.0));
        listaProductos.add(new Producto("Barco de pesca", 12500.0));
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