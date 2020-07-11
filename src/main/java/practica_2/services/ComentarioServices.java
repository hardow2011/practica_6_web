package practica_2.services;

import practica_2.entidades.Comentario;

public class ComentarioServices  extends GestionDb<Comentario>{

    private static ComentarioServices instancia;

    public ComentarioServices() {
        super(Comentario.class);
        // TODO Auto-generated constructor stub
    }

    public static ComentarioServices getInstancia(){
        if(instancia == null){
            instancia = new ComentarioServices();
        }
        return instancia;
    }
    
}