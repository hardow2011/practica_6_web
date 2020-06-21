<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de productos</title>
    <script src="myscripts.js"></script>
</head>
<body>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">Lista de productos</h1>
        </div> 
        </body>
        <a href="/crud-productos/crear" class="btn btn-primary">Nuevo producto</a>
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
                    <td><a href="/crud-productos/editar/${producto.id}">Editar</a> |
                        <a href="/crud-productos/visualizar/${producto.id}">Visualizar</a> |
                        <a href="/crud-productos/eliminar/${producto.id}">Eliminar</a>
                    </td>
                </tr>
                </#foreach>
            </tbody>
        </table>
    </div>
</html>