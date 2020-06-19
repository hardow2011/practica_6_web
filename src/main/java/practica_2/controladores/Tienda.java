package practica_2.controladores;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import practica_2.encapsulaciones.Producto;
import practica_2.encapsulaciones.Usuario;

public class Tienda {

    private static Tienda instancia;
    private List<Usuario> listaUsuarios;
    private List<Producto> listaProductos;

    public Tienda(){
        listaUsuarios = new ArrayList<>();
        listaProductos = new ArrayList<>();

        // listaProductos.add(new Producto("Papel de baño", 115.0));
        // listaProductos.add(new Producto("Barco de pesca", 12500.0));
    }

    public static Tienda getInstancia() {
        if(instancia == null){
            instancia = new Tienda();
        }
        return instancia;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void agregarUsuario(String nombreUsuario, String nombrePersona, String password){
        Usuario usuario = new Usuario(nombreUsuario, nombrePersona, password);
        listaUsuarios.add(usuario);
    }

    public void agregarProducto(String nombre, double precio){
        Producto producto = new Producto(nombre, precio);
        listaProductos.add(producto);
    }

}