package practica_2.controladores;

import javax.imageio.plugins.tiff.ExifGPSTagSet;

import org.jasypt.util.password.StrongPasswordEncryptor;

import io.javalin.Javalin;
import practica_2.encapsulaciones.Usuario;
import practica_2.services.UsuarioServices;
import practica_2.util.BaseControlador;


public class CookiesSesionesControlador extends BaseControlador {

    // Tienda tienda = Tienda.getInstancia();
    UsuarioServices usuarioServices = new UsuarioServices();
    StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();


    public CookiesSesionesControlador(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas(){

        app.post("/autenticar", ctx -> {
            // Recibiendo los valores en los campos usuario y password de un form que envíe a la acción autenticar.
            String nombreUsuario = ctx.formParam("usuario");
            // String nombre = ctx.formParam("nombre");
            String password = ctx.formParam("password");

            Boolean existe = false;

            // for (int i = 0; i < tienda.getListaUsuarios().size(); i++) {
            //     if(tienda.getListaUsuarios().get(i).getUsuario().equals(texto_usuario) && tienda.getListaUsuarios().get(i).getPassword().equals(password)){
            //         // System.out.println((tienda.getListaUsuarios().get(i).getUsuario()+" "+texto_usuario+" | "+tienda.getListaUsuarios().get(i).getPassword()+" "+password));
            //         System.out.println(tienda.getListaUsuarios().get(i).getUsuario().equals(texto_usuario) && tienda.getListaUsuarios().get(i).getPassword().equals(password));
            //         ctx.sessionAttribute("usuario", tienda.getListaUsuarios().get(i));
            //         existe = true;
            //         ctx.redirect("crud-productos");
            //         break;
            //     }
            // }

            for (int i = 0; i < usuarioServices.getListaUsuarios().size(); i++) {
                if(usuarioServices.getListaUsuarios().get(i).getUsuario().equals(nombreUsuario) && passwordEncryptor.checkPassword(password, usuarioServices.getListaUsuarios().get(i).getPassword())){
                    // System.out.println((tienda.getListaUsuarios().get(i).getUsuario()+" "+texto_usuario+" | "+tienda.getListaUsuarios().get(i).getPassword()+" "+password));
                    // System.out.println(tienda.getListaUsuarios().get(i).getUsuario().equals(texto_usuario) && tienda.getListaUsuarios().get(i).getPassword().equals(password));
                    ctx.sessionAttribute("usuario", usuarioServices.getListaUsuarios().get(i));
                    existe = true;
                    ctx.redirect("crud-productos");
                    break;
                }
            }

            if(existe == false){
                ctx.redirect("login.html");
            }
        });

    }
    
}