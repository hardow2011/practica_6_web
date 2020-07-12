package practica_2.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.transaction.Transactional;

import org.hibernate.Session;

import practica_2.services.ProductoServices;

@Entity
// Esas queries son necesarias para recuperar los atributos de manera LAZY...
// porque si no se recuperan de manera EAGER, se cierra la conexión...
// y hay que volver a abrirla en el Service.
@NamedQueries({
    @NamedQuery(name = "Producto.obtenerFotosPorProductoId", query = "SELECT f FROM Foto f WHERE f.producto.id = :idProducto"),  
    @NamedQuery(name = "Producto.obtenerComentariosPorProductoId", query = "SELECT c FROM Comentario c WHERE c.producto.id = :idProducto")
})
public class Producto  implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //crear el ID de forma automática
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    // Cambiar List a Set porque al parecer dos Lists no pueden ser FetchType.Eager a la vez
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "producto", fetch = FetchType.LAZY)
    private Set<Foto> listaFotos = new HashSet<>();
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "producto", fetch = FetchType.LAZY)
    private Set<Comentario> listaComentarios = new HashSet<>();

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
    public Producto(String nombre, String descripcion, double precio){
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

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

    public Set<Foto> getListaFotos() {
        listaFotos = ProductoServices.getInstancia().obtenerFotosPorProductoId(id);
        return listaFotos;
    }

    public void setListaFotos(Set<Foto> listaFotos) {
        this.listaFotos = listaFotos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Transactional
    public Set<Comentario> getListaComentarios() {
        listaComentarios = ProductoServices.getInstancia().obtenerComentariosPorProductoId(id);
        return listaComentarios;
    }
}