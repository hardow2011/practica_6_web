<#include "navbar.ftl">
<!DOCTYPE html>
<html lang="en">
<body>
    <@navbar/>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">Listado de ventas</h1>
        </div>
        <#foreach venta in listaVentas>
            <h1>${venta.nombreCliente} - ${venta.fechaCompra?string('dd/MM/yyyy')}</h1>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Producto</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Total</th>
                    </tr>   
                </thead>
                <tbody>
                <#list venta.listaProductos as producto>
                    <tr>
                        <td>${producto.nombre}</td>
                        <td>${producto.precio}</td>
                        <td>${venta.listaCantidades[producto?index]}</td>
                        <td>${venta.listaCantidades[producto?index] * producto.precio}</td>
                        <#--  <td>${venta.fechaCompra?string('dd/MM/yyyy')}</td>
                        <td>${venta.totalCompra}</td>  -->
                    </tr>
                </#list>
                </tbody>
            </table>
            <h3 class="float-right">Total: ${venta.totalCompra}</h3>
        </#foreach>
    </div>
</body>
</html>