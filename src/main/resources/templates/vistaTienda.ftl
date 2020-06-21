<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista producto</title>
</head>
<body>
    <h1>Lista productos</h1>
    <form enctype="application/x-www-form-urlencoded" action=${accion} method="post">
        <table>
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                </tr>   
            </thead>
            <tbody>
                <#foreach producto in listaProductos>
                <tr>
                    <td>${producto.nombre}</td>
                    <td>${producto.precio}</td>
                    <td><input type="number" value="${producto.cantidad}" name="cantidadProducto" required/><br></td>
                </tr>
                </#foreach>
            </tbody>
        </table>
        <input type="submit" value="Enviar"/>
    </form>
</body>
</html>