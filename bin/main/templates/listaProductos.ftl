<#include "navbar.ftl">
<!DOCTYPE html>
<html>
<body>
    <@navbar/>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">Lista de productos</h1>
        </div> 
        </body>
        <div><a href="/crud-productos/crear" class="btn btn-success crear">Nuevo producto</a></div>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Acción</th>
                </tr>   
            </thead>
            <tbody>
                <#foreach producto in listaProductos>
                <tr>
                    <td>${producto.id}</td>
                    <td>${producto.nombre}</td>
                    <td>${producto.precio}</td>
                    <td><a class="btn btn-primary" href="/crud-productos/editar/${producto.id}">Editar</a> |
                        <a class="btn btn-info" href="/crud-productos/visualizar/${producto.id}">Visualizar</a> |
                        <a class="btn btn-danger" href="/crud-productos/eliminar/${producto.id}">Eliminar</a>
                    </td>
                </tr>
                </#foreach>
            </tbody>
        </table>
    </div>
</body>
</html>