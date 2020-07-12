package practica_2.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import practica_2.entidades.Producto;

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

    public List<Producto> obtenerPorPaginacion(int pagina){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("from Producto", Producto.class);
        int paginaPaginacion = 10*(pagina-1);
        query.setFirstResult(paginaPaginacion);
        query.setMaxResults(10);
        List<Producto> lista = query.getResultList();

        System.out.println("\n\n\n\n\n");
        System.out.println(lista.size());
        for (Producto producto : lista) {
            System.out.println(producto.getNombre());
        }
        return lista;
    }

}