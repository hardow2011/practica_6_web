package practica_2.entidades;

import java.io.Serializable;

import javax.persistence.*;

public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // crear el ID de forma automática
    private int id;
    private String nombreUsuario;
    private String nombrePersona;
    private String password;

    public Usuario() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario(String nombreUsuario, String nombrePersona, String password) {
        this.setNombreUsuario(nombreUsuario);
        this.setNombrePersona(nombrePersona);
        this.setPassword(password);
    }
    
}