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
                        <input type="text" class="form-control" id="nombreClienteInput" name="nombreCliente"/><br>
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
                    <th>Acción</th>
                </tr>   
            </thead>
            <tbody>
                <#if carroCompra??>
                    <#list carroCompra.listaProductos as producto>
                    <tr>
                        <td>${producto.nombre}</td>
                        <td>${producto.precio}</td>
                        <td>${carroCompra.listaCantidades[producto?index]}</td>
                        <td>${carroCompra.listaCantidades[producto?index] * producto.precio}</td>
                        <td><a href="/carro-compra/eliminar/${producto.id}" class="btn btn-danger">Eliminar</a></td>
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