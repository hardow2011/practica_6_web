package practica_2.encapsulaciones;

import java.util.ArrayList;
import java.util.List;

public class CarroCompra {
    
    private long id;
    private List<Producto> listaProductos;
    private int cantidad;
    private static int contadorIdCarroCompra = 0;

    public CarroCompra(){
        listaProductos = new ArrayList<>();
        this.id = contadorIdCarroCompra;
        this.cantidad = 0;
        contadorIdCarroCompra++;
    }

    public long getId() {
        return id;
    }

    // public void setId(int id) {
    //     this.id = id;
    // }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void insertarProducto(Producto producto) {
        listaProductos.add(producto);
        cantidad++;
    }

}