<#include "navbar.ftl">
<!DOCTYPE html>
<html lang="en">
<body>
    <@navbar/>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">${titulo} producto</h1>
        </div> 
        <form enctype="multipart/form-data" action=${accion} method="post">
          <div class="form-group">
                <#if producto??>
                    <#if visualizar ??>
                        <label for="nombreInput">Nombre</label>
                        <input readonly="true" type="text" class="form-control" value="${producto.nombre}"  id="nombreInput" name="nombreProducto"/><br>
                        <label for="descripcionInput">Descripción</label>
                        <input readonly="true" type="text" class="form-control" value="${(producto.descripcion)!}" id="descripcionInput" name="descripcionProducto"/><br>
                        <label for="precioInput">Precio</label>
                        <input readonly="true" type="number" step="0.01" class="form-control" value="${producto.precio?string['0']}" id="precioInput" name="precioProducto" disables/><br>
                        <#if listaFotos??>
                            <h1>Fotos</h1>
                            <#foreach foto in listaFotos>
                                <img src="data:${foto.mimeType};base64,${foto.fotoBase64}" class="imagen-producto" alt="Foto enviada">
                            </#foreach>
                        <#else>
                            <h1>No hay fotos</h1>
                        </#if>
                        <#if usuario??>
                            <form enctype="application/x-www-form-urlencoded" action=${accion} method="post">
                                <div class="form-group">
                                <label for="comentarioInput">Agregar comentario</label>
                                <input type="text" class="form-control"  id="comentarioInput" name="comentarioProducto"/><br>
                                <input type="hidden" value="${producto.id}" name="idProducto"/>
                                <input type="submit" class="btn btn-success confirmar" value="Publicar comentario"/>
                                </div>
                            </form>
                        <#else>
                            <a class="nav-link" href="/login">Iniciar sesión para comentar</a>
                        </#if>
                        <#if listaComentarios??>
                            <h1>Comentarios</h1>
                            <#foreach comentario in listaComentarios>
                                <#if usuario??>
                                    <#if usuario.admin>
                                        <a class="btn btn-danger" href="/crud-productos/eliminar-comentario/${comentario.id}/${producto.id}">Eliminar</a>
                                    </#if>
                                </#if>
                                <input readonly="true" type="text" class="form-control" value="${comentario.texto}"  id="comentario" name="comentario"/><br>
                            </#foreach>
                        <#else>
                        <h1>No hay comentarios</h1>
                        </#if>
                    <#else>
                        <input type="hidden" value="${(producto.id)!}" name="idProducto"/>
                        <label for="nombreInput">Nombre</label>
                        <input type="text" class="form-control" value="${producto.nombre}" id="nombreInput" name="nombreProducto" required/><br>
                        <label for="precioInput">Precio</label>
                        <input type="number" step="0.01" class="form-control" id="precioInput" value="${producto.precio?string['0']}" name="precioProducto" required/><br>
                        <label for="fotoInput">Agregar fotos</label>
                        <input class="form-control" id="fotoInput" type="file" name="fotoProducto" multiple>
                        <#if listaFotos??>
                            <h1>Fotos</h1>
                            <#foreach foto in listaFotos>
                            <div>
                                <a class="btn btn-danger" href="/crud-productos/eliminar-foto/${foto.id}/${producto.id}">Eliminar</a>    
                                <img src="data:${foto.mimeType};base64,${foto.fotoBase64}" class="imagen-producto" alt="Foto enviada"/>
                            </div>
                            </#foreach>
                        <#else>
                            <h1>No hay fotos</h1>
                        </#if>
                    </#if>
                <#else>
                <label for="nombreInput">Nombre</label>
                <input type="text" name="nombreProducto" class="form-control" id="nombreInput" required/><br>
                <label for="precioInput">Precio</label>
                <input type="number" step="0.01" name="precioProducto"  class="form-control" id="precioInput" required/><br>
                <label for="descripcionInput">Descripción</label>
                <input type="text" name="descripcionProducto" class="form-control" id="descripcionInput"/><br>
                <label for="fotoInput">Fotos</label>
                <input class="form-control" id="fotoInput" type="file" name="fotoProducto" multiple required>
                </#if>
            </div>
            <#if !visualizar ??>
                <input type="submit" class="btn btn-success crear" value="Enviar"/>
            <#--  <#else>
                <a href="/crud-productos/listar" class="btn btn-primary regresar">Regresar</a>  -->
            </#if>
        </form>
    </div> 
</body>
</html>