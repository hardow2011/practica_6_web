package practica_2.services;

import practica_2.entidades.ProductoVendido;

public class ProductoVendidoServices extends GestionDb<ProductoVendido>{

    private static ProductoVendidoServices instancia;

    private ProductoVendidoServices() {
        super(ProductoVendido.class);
        // TODO Auto-generated constructor stub
    }

    public static ProductoVendidoServices getInstancia(){
        if(instancia == null){
            instancia = new ProductoVendidoServices();
        }
        return instancia;
    }
    
}