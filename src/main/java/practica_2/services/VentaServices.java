package practica_2.services;

import practica_2.entidades.Venta;

public class VentaServices extends GestionDb<Venta>{

    private static VentaServices instancia;

    private VentaServices() {
        super(Venta.class);
        // TODO Auto-generated constructor stub
    }

    public static VentaServices getInstancia(){
        if(instancia == null){
            instancia = new VentaServices();
        }
        return instancia;
    }
    
}