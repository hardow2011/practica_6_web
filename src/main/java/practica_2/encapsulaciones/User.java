package practica_2.encapsulaciones;

public class User {

    String usuario;
    String nombre;
    String password;

    public Usuario(){
        
    }

    public Usuario(String usuario, String nombre, String password) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

}