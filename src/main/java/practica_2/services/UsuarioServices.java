package practica_2.services;

import practica_2.encapsulaciones.Usuario;

public class UsuarioServices extends GestionDb<Usuario>{

    private static UsuarioServices instancia;

    private UsuarioServices() {
        super(Usuario.class);
        // TODO Auto-generated constructor stub
    }

    public static UsuarioServices getInstancia(){
        if(instancia == null){
            instancia = new UsuarioServices();
        }
        return instancia;
    }

}