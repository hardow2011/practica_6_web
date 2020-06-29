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
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      </div>
        <div style="display:flex; justify-content:flex-end; width:100%; padding:0;">
            <a class="nav-link" href="/carro-compra/vista-tienda">Comprar</a>
            <a class="nav-link" href="/carro-compra/ventas-productos">Ventas realizadas</a>
            <a class="nav-link" href="/crud-productos/listar">Administrar productos</a>
            <a class="nav-link" href="/crear-usuario">Crear usuario</a>
            <a class="nav-link" href="/carro-compra/compras">Carrito(${tamagnoCarritoCompra})</a>
        </div>
      </div>
    </nav>
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
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>