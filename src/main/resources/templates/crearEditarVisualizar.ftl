<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${titulo} producto</title>
</head>
<body>
    <h1>${titulo} producto</h1>
    <form enctype="application/x-www-form-urlencoded" action=${accion} method="post">
        <#if producto??>
            <input type="hidden" value="${producto.id}" name="idProducto"/>
            Nombre <input type="text" value="${producto.nombre}" name="nombreProducto" required/><br>
            Precio: <input type="number" step="0.01" value="${producto.precio?string['0']}" name="precioProducto" required/><br>
        <#else>
        Nombre <input type="text" name="nombreProducto" required/><br>
            Precio: <input type="number" step="0.01" name="precioProducto" required/><br>
        </#if>
        <input type="submit" value="Enviar"/>
    </form>
</body>
</html>