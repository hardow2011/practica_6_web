<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario de Acceso</title>
</head>
<body>
    <h1>Formulario de Acceso</h1>
    <!-- Se encuentra en la clase CookiesSesionesControlador -->
    <form action="/autenticar" method="post">
        Usuario: <input type="text" name="usuario" required/><br>
        Nombre: <input type="text" name="nombre" required/><br>
        Password: <input type="password" name="password" required/><br/>
        <input type="hidden" name="redirect_to" value=${redirect_to}> 
        <input type="submit" value="Enviar"/>
    </form>
</body>
</html>