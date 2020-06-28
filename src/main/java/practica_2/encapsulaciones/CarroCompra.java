package practica_2.encapsulaciones;

import java.util.ArrayList;
import java.util.List;

public class CarroCompra {
    
    private int id;
    private List<Producto> listaProductos;
    private List<Integer> listaCantidades;
    private static int contadorIdCarroCompra = 0;

    public CarroCompra(){
        listaProductos = new ArrayList<>();
        listaCantidades = new ArrayList<>();
        this.id = contadorIdCarroCompra;
        contadorIdCarroCompra++;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getListaCantidades() {
        return listaCantidades;
    }

    public void setListaCantidades(List<Integer> listaCantidades) {
        this.listaCantidades = listaCantidades;
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
        listaCantidades.add(cantidad);
    }

}