<#include "navbar.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista producto</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="/css/miEstilo.css" rel="stylesheet" >

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <@navbar/>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">Lista de productos</h1>
        </div>
        <div>
            <#if !(numPagina-1 lte 0)>
                <a href="/carro-compra/vista-tienda/${(numPagina-1)!}">Anterior</a>
            </#if>
            <#if hayProximaPagina>
                <a href="/carro-compra/vista-tienda/${(numPagina+1)!}">Siguiente</a>
            </#if>
        </div>
        <form enctype="application/x-www-form-urlencoded" action=${accion} method="post">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Visualizar</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                    </tr>   
                </thead>
                <tbody>
                    <#list listaCantidades as cantidad>
                    <tr>
                        <td><a href="/crud-productos/visualizar/${listaProductos[cantidad?index].id}" class="btn btn-info">Visualizar</a></td>
                        <td>${listaProductos[cantidad?index].nombre}</td>
                        <td>${listaProductos[cantidad?index].precio}</td>
                        <td><input type="number" value="${cantidad}" name="listaCantidades" required/><br></td>
                    </tr>
                    </#list>
                </tbody>
            </table>
            <input type="hidden" value="${numPagina}" name="numPagina"/>
            <input class="btn btn-success confirmar" type="submit" value="Agregar al carrito"/>
        </form>
    </div>
</body>
</html>