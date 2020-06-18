package practica_2.encapsulaciones;

import java.math.BigDecimal;

import org.checkerframework.common.reflection.qual.GetConstructor;

public class Producto {
    
    private int id;
    private String nombre;
    private BigDecimal precio;
    private static int contadorIdProducto = 0;

    public Producto(){

    }

    public Producto(String nombre, BigDecimal precio){
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

}