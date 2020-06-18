package practica_2.encapsulaciones;

import java.util.Date;
import java.util.List;

public class VentasProductos {
    
    private int id;
    private Date fechaCompra;
    private String nombreCliente;
    private List<Producto> listaProductos;
    private int cantidad;
    private static int contadorIdVentaProductos = 0;

    public VentasProductos(Date fechaCompra, String nombreCliente, List<Producto> listaProductos, int cantidad) {
        this.id = contadorIdVentaProductos;
        this.fechaCompra = fechaCompra;
        this.nombreCliente = nombreCliente;
        this.listaProductos = listaProductos;
        this.cantidad = cantidad;
        contadorIdVentaProductos++;
    }

    public int getId() {
        return id;
    }

    // public void setId(int id) {
    //     this.id = id;
    // }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}