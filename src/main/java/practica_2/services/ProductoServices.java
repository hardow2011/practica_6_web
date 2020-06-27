package practica_2.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import practica_2.encapsulaciones.Producto;

public class ProductoServices {
    
    /**
     * Listado de los estudiantes.
     * @return
     */
    public List<Producto> listaProductos() {
        List<Producto> lista = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {
            //
            String query = "SELECT * FROM PRODUCTO ";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexión.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");


                Producto prod = new Producto(id, nombre, precio, cantidad);

                lista.add(prod);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lista;
    }

    /**
     * 
     * @param est
     * @return
     */
    public boolean crearProducto(Producto prod){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "insert into producto(nombre, precio, cantidad) values(?,?,?)";
            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, prod.getId());
            prepareStatement.setDouble(2, prod.getPrecio());
            prepareStatement.setInt(3, prod.getCantidad());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

}