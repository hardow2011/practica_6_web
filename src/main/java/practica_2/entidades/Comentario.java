package practica_2.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Comentario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String texto;
    @ManyToOne
    @JoinColumn(name="PRODUCTO_ID")
    Producto producto;

    public Comentario(){
    }

    public Comentario(String texto) {
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}