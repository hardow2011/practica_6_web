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


                Producto prod = new Producto(id, nombre, precio);

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

    public boolean crearProducto(String nombre, Double precio){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "insert into producto(nombre, precio) values(?,?)";
            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            // prepareStatement.setInt(1, prod.getId());
            prepareStatement.setString(1, nombre);
            prepareStatement.setDouble(2, precio);
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

    public Producto getProductoPorId(int id) {
        Producto prod = null;
        Connection con = null;
        try {
            //utilizando los comodines (?)...
            String query = "select * from producto where id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, id);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                String nombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio");

                prod = new Producto(id, nombre, precio);

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

        return prod;
    }

    public boolean modificarProducto(int id, String nombre, Double precio){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "update producto set nombre=?, precio=? where id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, nombre);
            prepareStatement.setDouble(2, precio);
            //Indica el where...
            prepareStatement.setInt(3, id);
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

    public boolean eliminarProducto(int id){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "delete from producto where id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);

            //Indica el where...
            prepareStatement.setInt(1, id);
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