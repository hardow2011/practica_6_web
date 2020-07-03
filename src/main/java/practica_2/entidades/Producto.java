package practica_2.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Producto  implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //crear el ID de forma automática
    private int id;
    private String nombre;
    private double precio;

    public Producto(){

    }

        // // Para clonar producto
        // public Producto(Producto producto){
        //     this.id = producto.id;
        //     this.nombre = producto.nombre;
        //     this.precio = producto.precio;
        // }
    
        // // Para editar producto
        // public Producto(int id, String nombre, double precio){
        //     this.id = id;
        //     this.nombre = nombre;
        //     this.precio = precio;
        // }
    
        // Para crear producto
        public Producto(String nombre, double precio){
            this.nombre = nombre;
            this.precio = precio;
        }
    
        // // Para recuperar productos de la BD
        // public Producto(int id, String nombre, double precio, int cantidad){
        //     this.id = id;
        //     this.nombre = nombre;
        //     this.precio = precio;
        //     // El contador id será igual a último id recuperado +1 (para que el id de la próxima inserción continúe con el contador)
        //     // contadorIdProducto = id+1;
        // }

    public int getId() {
        return id;
    }

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