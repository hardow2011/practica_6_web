package practica_2.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Comentario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String texto;
    @ManyToOne
    Producto producto;

    public Comentario(){
    }

    public Comentario(String texto) {
        this.texto = texto;
    }

    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}