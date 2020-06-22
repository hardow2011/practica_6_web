<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de productos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="/css/miEstilo.css" rel="stylesheet" >

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div style="display:flex; justify-content:flex-end; width:100%; padding:0;">
        <a class="nav-link" href="/carro-compra/vista-tienda">Comprar</a>
        <a class="nav-link" href="#">Ventas realizadas</a>
        <a class="nav-link" href="/crud-productos/listar">Administrar productos</a>
          <a class="nav-link" href="/carro-compra/compras">Carrito(${tamagnoCarritoCompra})</a>
        </div>
      </div>
    </nav>
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
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>