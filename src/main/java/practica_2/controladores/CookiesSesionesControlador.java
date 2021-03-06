package practica_2.controladores;

import java.util.List;
import java.util.Objects;

import javax.imageio.plugins.tiff.ExifGPSTagSet;

import org.jasypt.util.password.StrongPasswordEncryptor;

import io.javalin.Javalin;
import practica_2.entidades.*;
import practica_2.services.UsuarioServices;
import practica_2.util.BaseControlador;


public class CookiesSesionesControlador extends BaseControlador {

    // Tienda tienda = Tienda.getInstancia();
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

            List<Usuario> listaUsuarios = UsuarioServices.getInstancia().listar();

            for (int i = 0; i < listaUsuarios.size(); i++) {
                if(listaUsuarios.get(i).getNombreUsuario().equals(nombreUsuario) && passwordEncryptor.checkPassword(password, listaUsuarios.get(i).getPassword())){
                    ctx.sessionAttribute("usuario", listaUsuarios.get(i));
                    // ctx.cookie("recuerdame", String.valueOf(usuarioServices.getListaUsuarios().get(i).getId()), 604800);
                    existe = true;
                    // Si el campo recuerdame no es nulo, crear una cookie llamada recuerdame con el nombre de usuario
                    if(Objects.nonNull(ctx.formParam("recuerdame"))){
                        ctx.cookie("recuerdame", String.valueOf(listaUsuarios.get(i).getId()), 604800);
                    }

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