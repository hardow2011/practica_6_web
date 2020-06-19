package practica_2.encapsulaciones;

import java.math.BigDecimal;

import org.checkerframework.common.reflection.qual.GetConstructor;

public class Producto {
    
    private int id;
    private String nombre;
    private double precio;
    private static int contadorIdProducto = 0;

    // public Producto(){

    // }

    public Producto(int id, String nombre, double precio){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
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

}