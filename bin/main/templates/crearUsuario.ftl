<#include "navbar.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
<body>
    <@navbar/>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">Crear usuario</h1>
        </div> 
        <form enctype="application/x-www-form-urlencoded" action=${accion} method="post">
          <div class="form-group">
            <label for="usuarioInput">Usuario</label>
            <input type="text" class="form-control" id="usuarioInput" name="usuario" required/><br>
            <label for="nombreInput">Nombre</label>
            <input type="text" class="form-control" id="nombreInput" name="nombre" required/><br>
            <label for="passwordInput">Contraseña</label>
            <input type="password" class="form-control" id="passwordInput" name="password" required/><br/>
            <input type="checkbox" id="esAdmin" name="esAdmin" value="true">
            <label for="esAdmin">Es admin?</label><br>
            <input type="submit" class="btn btn-success confirmar" value="Crear usuario"/>
          </div>
        </form>
    </div> 
</body>
</html>