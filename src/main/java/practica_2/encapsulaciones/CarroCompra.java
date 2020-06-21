package practica_2.encapsulaciones;

import java.util.ArrayList;
import java.util.List;

public class CarroCompra {
    
    private long id;
    private List<Producto> listaProductos;
    private static int contadorIdCarroCompra = 0;

    public CarroCompra(){
        listaProductos = new ArrayList<>();
        this.id = contadorIdCarroCompra;
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

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void insertarProducto(Producto producto, int cantidad) {
        listaProductos.add(producto);
    }

}