package practica_2.services;

import practica_2.encapsulaciones.Producto;

public class ProductoServices extends GestionDb<Producto>{

    private static ProductoServices instancia;

    private ProductoServices() {
        super(Producto.class);
        // TODO Auto-generated constructor stub
    }

    public static ProductoServices getInstancia(){
        if(instancia == null){
            instancia = new ProductoServices();
        }
        return instancia;
    }

}