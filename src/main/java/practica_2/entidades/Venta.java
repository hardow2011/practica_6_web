package practica_2.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Venta  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // crear el ID de forma automática
    private int id;
    private Date fechaCompra;
    private String nombreCliente;
    @OneToMany(mappedBy = "venta", fetch = FetchType.EAGER)
    private List<ProductoVendido> listaProductos = new ArrayList<>();
    private Double totalCompra;

    public Venta() {
    }

    public Venta(Date fechaCompra, String nombreCliente, Double totalCompra) {
        this.fechaCompra = fechaCompra;
        this.nombreCliente = nombreCliente;
        this.totalCompra = totalCompra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(Double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public List<ProductoVendido> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ProductoVendido> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}