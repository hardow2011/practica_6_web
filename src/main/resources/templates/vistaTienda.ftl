#<#include "navbar.ftl"/>
<!DOCTYPE html>
<html lang="en">
<body>
    <@navbar>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">Lista de productos</h1>
        </div> 
        <form enctype="application/x-www-form-urlencoded" action=${accion} method="post">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                    </tr>   
                </thead>
                <tbody>
                    <#list listaCantidades as cantidad>
                    <tr>
                        <td>${listaProductos[cantidad?index].nombre}</td>
                        <td>${listaProductos[cantidad?index].precio}</td>
                        <td><input type="number" value="${cantidad}" name="listaCantidades" required/><br></td>
                    </tr>
                    </#list>
                </tbody>
            </table>
            <input class="btn btn-success confirmar" type="submit" value="Agregar al carrito"/>
        </form>
    </div> 
</body>
</html>