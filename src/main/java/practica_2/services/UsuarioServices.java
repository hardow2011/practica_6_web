package practica_2.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import practica_2.encapsulaciones.Usuario;

public class UsuarioServices {
    
    public boolean crearUsuario(String nombreUsuario, String nombrePersona, String contrasegna){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "insert into usuario(nombreusuario, nombrepersona, CONTRASEGNA) values(?,?,?)";
            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            // prepareStatement.setInt(1, prod.getId());
            prepareStatement.setString(1, nombreUsuario);
            prepareStatement.setString(2, nombrePersona);
            prepareStatement.setString(3, contrasegna);
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

    public List<Usuario> getListaUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {
            //
            String query = "SELECT * FROM USUARIO ";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexión.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){

                int id = rs.getInt("id");
                String nombreUsuario = rs.getString("NOMBREUSUARIO");
                String nombrePersona = rs.getString("nombrepersona");
                String password = rs.getString("CONTRASEGNA");


                Usuario usuario = new Usuario(id, nombreUsuario, nombrePersona, password);

                lista.add(usuario);
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

    public Usuario getUsuariobyId(int id) {
        Usuario usuario = null;
        Connection con = null;
        try {
            //utilizando los comodines (?)...
            String query = "select * from usuario where id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, id);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                String nombreUsuario = rs.getString("NOMBREUSUARIO");
                String nombrePersona = rs.getString("NOMBREPERSONA");
                String password = rs.getString("CONTRASEGNA");

                usuario = new Usuario(id, nombreUsuario, nombrePersona, password);

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

        return usuario;
    }

}