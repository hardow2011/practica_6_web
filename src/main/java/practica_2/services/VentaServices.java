package practica_2.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import practica_2.encapsulaciones.CarroCompra;
import practica_2.encapsulaciones.Producto;
import practica_2.encapsulaciones.VentasProductos;

public class VentaServices {
    

    public boolean crearVenta(String nombreCliente, CarroCompra carroCompra){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "insert into venta(fecha, nombrecliente, totalcompra) values(?,?, ?)";
            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            // prepareStatement.setInt(1, prod.getId());
            prepareStatement.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
            prepareStatement.setString(2, nombreCliente);
            prepareStatement.setDouble(3, carroCompra.getTotalCarrito());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

            registrarProductosCompra(carroCompra);

        } catch (SQLException ex) {
            Logger.getLogger(VentaServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(VentaServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

    private boolean registrarProductosCompra(CarroCompra carroCompra){
        boolean ok =false;

        Connection con = null;
        try {

            for(int index = 0; index < carroCompra.getListaCantidades().size(); index++){
                String query = "insert into productovendido(nombre, precio, cantidad, venta_id) values(?,?,?,?)";
                con = DataBaseServices.getInstancia().getConexion();
                PreparedStatement prepareStatement = con.prepareStatement(query);
                //Antes de ejecutar seteo los parametros.
                prepareStatement.setString(1, carroCompra.getListaProductos().get(index).getNombre());
                prepareStatement.setDouble(2, carroCompra.getListaProductos().get(index).getPrecio());
                prepareStatement.setInt(3, carroCompra.getListaCantidades().get(index));
                prepareStatement.setInt(4, getMaxIdVenta());
                int fila = prepareStatement.executeUpdate();
                ok = fila > 0 ;
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

        return ok;
    }

    public int getMaxIdVenta() {
        int maxId = -1;
        Connection con = null;
        try {
            //utilizando los comodines (?)...
            String query = "select max(id) as maxid from venta";
            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                
                maxId = rs.getInt("maxid");

            }

        } catch (SQLException ex) {
            Logger.getLogger(VentaServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(VentaServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return maxId;
    }

    public List<VentasProductos> getListaVentas() {
        List<VentasProductos> lista = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {
            // Recupero las ventas sin lista de productos ni sus cantidades
            String query = "SELECT * FROM VENTA ";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexión.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){

                int id = rs.getInt("id");
                Date fecha = rs.getDate("fecha");
                String nombreCliente = rs.getString("nombrecliente");
                Double total = rs.getDouble("totalcompra");

                VentasProductos ventaProductos = new VentasProductos(id, fecha, total, nombreCliente);

                lista.add(ventaProductos);
            }

            // Por cada venta recupero la lista de productos y su cantidad
            for(int index = 0; index < lista.size(); index++){
                List<Producto> listaProductos = new ArrayList<>();
                List<Integer> listaCantidades = new ArrayList<>();
                query = "select * from productovendido where venta_id = ?";
                prepareStatement = con.prepareStatement(query);
                prepareStatement.setInt(1, lista.get(index).getId());
                rs = prepareStatement.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    Double precio = rs.getDouble("precio");
                    int cantidad = rs.getInt("cantidad");
    
                    Producto prod = new Producto(id, nombre, precio);
                    listaProductos.add(prod);
                    listaCantidades.add(cantidad);
                }

                lista.get(index).setListaProductos(listaProductos);
                lista.get(index).setListaCantidades(listaCantidades);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VentaServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(VentaServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lista;
    }

    // public void setProductosVend() {
        
    // }

}