package practica_2.entidades;

import java.io.Serializable;

import javax.persistence.*;

public class ProductoVendido extends Producto  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // crear el ID de forma automática
    private Integer id;
    private int cantidad;
    @ManyToOne
    private Venta venta;

    public ProductoVendido() {
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