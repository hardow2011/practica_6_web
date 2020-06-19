<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Crear producto</title>
</head>
<body>
    <h1>Crear producto</h1>
    <!-- Se encuentra en la clase CookiesSesionesControlador -->
    <form enctype="application/x-www-form-urlencoded" action=${accion} method="post">
        Nombre <input type="text" name="nombreProducto" required/><br>
        Precio: <input type="nomber" name="precioProducto" required/><br>
        <input type="submit" value="Enviar"/>
    </form>
</body>
</html>