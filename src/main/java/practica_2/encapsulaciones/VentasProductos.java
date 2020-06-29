package practica_2.encapsulaciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentasProductos {
    
    private int id;
    private Date fechaCompra;
    private String nombreCliente;
    private List<Producto> listaProductos;
    private List<Integer> listaCantidades;
    private Double totalCompra;
    private static int contadorIdVentaProductos = 0;

    public VentasProductos(String nombreCliente, List<Producto> listaProductos) {
        this.id = contadorIdVentaProductos;
        this.fechaCompra = new Date();
        this.nombreCliente = nombreCliente;
        this.totalCompra = (double) 0;
        this.listaProductos = listaProductos;
        contadorIdVentaProductos++;
    }

    public VentasProductos(int id, Date fecha, Double total, String nombreCliente) {
        this.id = id;
        this.fechaCompra = fecha;
        this.nombreCliente = nombreCliente;
        this.totalCompra = total;
        this.listaProductos = listaProductos;
        this.listaCantidades = listaCantidades;
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

    public Double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(Double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public List<Integer> getListaCantidades() {
        return listaCantidades;
    }

    public void setListaCantidades(List<Integer> listaCantidades) {
        this.listaCantidades = listaCantidades;
    }

}