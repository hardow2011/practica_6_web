<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de productos</title>
    <link href="/css/miEstilo.css" rel="stylesheet" >
</head>
<body>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">Lista de productos</h1>
        </div>
        </body>
        <a href="/crud-productos/crear" class="btn btn-primary">Nuevo Estudiante</a>
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
    </div>
</html>