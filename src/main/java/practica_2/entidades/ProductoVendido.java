package practica_2.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class ProductoVendido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // crear el ID de forma automática
    private Integer id;
    private String nombre;
    private double precio;
    private int cantidad;
    @ManyToOne
    private Venta venta;

    public ProductoVendido() {
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ProductoVendido(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}