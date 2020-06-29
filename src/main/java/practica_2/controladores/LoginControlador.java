package practica_2.controladores;

import io.javalin.Javalin;
import practica_2.encapsulaciones.Usuario;
import practica_2.services.UsuarioServices;
import practica_2.encapsulaciones.CarroCompra;
import practica_2.util.BaseControlador;
// import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class LoginControlador extends BaseControlador {

    Tienda tienda = Tienda.getInstancia();
    StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
    UsuarioServices usuarioServices = new UsuarioServices();

    public LoginControlador(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas() {

        app.routes(() -> {

            path("/", () -> {
                before(ctx -> {
                    if(Objects.nonNull(ctx.cookie("recuerdame"))){
                        if(ctx.sessionAttribute("usuario") == null){
                            ctx.sessionAttribute("usuario", usuarioServices.getUsuariobyId(Integer.parseInt(ctx.cookie("recuerdame"))));
                        }
                    }
                });

                get("/login", ctx -> {
                    ctx.redirect("login.html");
                });
                get("/logout", ctx -> {
                    // Hacer nulo el atributo de sesión del usuario en caso de logout
                    ctx.req.getSession().setAttribute("usuario", null);
                    ctx.cookie("recuerdame", "null", 0);
                    ctx.redirect("login.html");
                });


                before("/crear-usuario", ctx -> {
                    Usuario usuario = ctx.sessionAttribute("usuario");
                    if(usuario == null){
                        ctx.redirect("/login.html");
                    }
                });

                get("/", ctx -> {
                    ctx.redirect("/carro-compra");
                });

                get("/crear-usuario", ctx -> {
                    Map<String, Object> modelo = new HashMap<>();
                    if(ctx.sessionAttribute("carroCompra") != null){
                        modelo.put("tamagnoCarritoCompra", ((CarroCompra) ctx.sessionAttribute("carroCompra")).getListaProductos().size());
                    }else{
                        modelo.put("tamagnoCarritoCompra", 0);
                    }
                    modelo.put("accion", "/crear-usuario");
                    if(ctx.sessionAttribute("usuario") != null){
                        modelo.put("conectado", "true");
                        modelo.put("nombreUsuario", ((Usuario) ctx.sessionAttribute("usuario")).getUsuario());
                    }
                    ctx.render("/templates/crearUsuario.ftl", modelo);
                });

                post("/crear-usuario", ctx -> {
                    String nombreUsuario = ctx.formParam("usuario");
                    String nombrePersona = ctx.formParam("nombre");
                    String password = ctx.formParam("password");

                    String encryptedPassword = passwordEncryptor.encryptPassword(password);
                    usuarioServices.crearUsuario(nombreUsuario, nombrePersona, encryptedPassword);

                    // tienda.agregarUsuario(nombreUsuario, nombrePersona, password);


                    // Map<String, Object> modelo = new HashMap<>();
                    // modelo.put("tamagnoCarritoCompra", tienda.getListaProductosConMasDeCeroCantidad().size());
                    // modelo.put("accion", "/crear-usuario");
                    ctx.redirect("crud-productos");
                });

            });
        });


    }
    
}