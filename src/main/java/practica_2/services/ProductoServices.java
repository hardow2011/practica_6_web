package practica_2.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import practica_2.entidades.Comentario;
import practica_2.entidades.Foto;
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

    // Esa función es necesaria para recuperar los atributos de manera LAZY...
    // porque si no se recuperan de manera EAGER, se cierra la conexión...
    // y hay que volver a abrirla por aquí.
    public Set<Foto> obtenerFotosPorProductoId(int idProducto) {
        Query query = getEntityManager().createNamedQuery("Producto.obtenerFotosPorProductoId", Foto.class);
        query.setParameter("idProducto", idProducto);
        return new HashSet<Foto>(query.getResultList());

    }
    
    // Esa función es necesaria para recuperar los atrubutos de manera LAZY...
    // porque si no se recuperan de manera EAGER, se cierra la conexión...
    // y hay que volver a abrirla por aquí.
    public Set<Comentario> obtenerComentariosPorProductoId(int idProducto) {
        Query query = getEntityManager().createNamedQuery("Producto.obtenerComentariosPorProductoId", Comentario.class);
        query.setParameter("idProducto", idProducto);
        return new HashSet<Comentario>(query.getResultList());
    }

}