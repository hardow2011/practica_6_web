<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista Productos</title>
</head>
<body>
<h1>Lista Productos</h1>
</body>
<table>
    <thead>
        <tr>
            <th>id</th>
            <th>Nombre</th>
            <th>Precio</th>
        </tr>
    </thead>
    <tbody>
        <#foreach producto in listaProductos>
        <tr>
            <td>${producto.id}</td>
            <td>${producto.nombre}</td>
            <td>${producto.precio}</td>
        </tr>
        </#foreach>
        
    </tbody>
</table>
</html>