<#include "navbar.ftl">
<!DOCTYPE html>
<html lang="en">
<body>
    <@navbar/>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">Carro de compra</h1>
        </div>

        <div class="form-group bg-light rounded-corners">
            <h4 class="ten-percent-padding-left">Datos del cliente</h4>
            <hr/>
            <#--  <div class="form-inline">  -->
                <form class="form-horizontal" enctype="application/x-www-form-urlencoded" action="/carro-compra/procesar-compra" method="post">
                    <div class="form-group form-inline ten-percent-padding-left">
                        <label for="nombreClienteInput">Nombre del cliente</label>
                        <input type="text" class="form-control" id="nombreClienteInput" name="nombreCliente" required/><br>
                    </div>
            <#--  </div>  -->
        </div>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Total</th>
                    <#--  <th>Acción</th>  -->
                </tr>   
            </thead>
            <tbody> 
                <#if carroCompra??>
                    <#list carroCompra as productoEnCarrito>
                    <tr>
                        <td>${productoEnCarrito.nombre}</td>
                        <td>${productoEnCarrito.precio}</td>
                        <td>${productoEnCarrito.cantidad}</td>
                        <td>${productoEnCarrito.cantidad * productoEnCarrito.precio}</td>
                        <td><a href="/carro-compra/eliminar/${productoEnCarrito.id}" class="btn btn-danger">Eliminar</a></td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
        <#if carroCompra??>
            <h3>Total: ${total}</h3>
        </#if>
        <#--  <a href="/carro-compra/procesar-compra" class="btn btn-success confirmar">Procesar compra</a>  -->
        <input type="submit" class="btn btn-success crear" value="Procesar compra"/>
        </form>
    </div>
</body>
</html>