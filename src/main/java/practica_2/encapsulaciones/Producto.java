package practica_2.encapsulaciones;

import java.math.BigDecimal;

import org.checkerframework.common.reflection.qual.GetConstructor;

public class Producto {
    
    private int id;
    private String nombre;
    private double precio;
    private static int contadorIdProducto = 0;
    private int cantidad;

    public Producto(Producto producto){
        this.id = producto.id;
        this.nombre = producto.nombre;
        this.precio = producto.precio;
        this.cantidad = producto.cantidad;
    }

    public Producto(int id, String nombre, double precio){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = 0;
    }

    public Producto(String nombre, double precio){
        this.id = contadorIdProducto;
        this.nombre = nombre;
        this.precio = precio;
        contadorIdProducto++;
    }

    public int getId() {
        return id;
    }
    // public void setId(int id) {
    //     this.id = id;
    // }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}