<%@page contentType="text/html" pageEncoding="UTF-8" import ="java.time.format.* " %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="layout/header.jsp" />

    <h2> ${title}</h2>

    <form action="${pageContext.request.contextPath}/productos/form" method="post">
        <div class="row mb-2">
            <label  class="col-form-label col-sm-2" for="nombre">Nombre</label>
            <div class="col-sm-4">
                <input type="text" name="nombre" id="nombre" value="${producto.nombre}"  class="form-control">
            </div>
            <c:if test="${errores != null && errores.containsKey('nombre')}">
                <div style="color: red;"> ${ errores.nombre }</div>
            </c:if>
        </div>
        <div class="row mb-2">
            <label  class="col-form-label col-sm-2" for="precio">Precio</label>
            <div class="col-sm-4">
                <input type="number" name="precio" id="precio" value="${producto.precio > 0 ? producto.precio: "" }" class="form-control">
            </div>
             <c:if test="${errores != null && !empty errores.precio}">
                <div style="color: red;"> ${ errores.precio }</div>
            </c:if>
        </div>
        <div class="row mb-2">
            <label  class="col-form-label col-sm-2" for="sku">SKU</label>
            <div class="col-sm-4">
                <input type="text" name="sku" id="sku" value="${producto.sku}" class="form-control">
            </div>
            <c:if test="${errores != null && !empty errores.sku }">
                <div style="color: red;"> ${ errores.sku }</div>
            </c:if>
        </div>
        <div class="row mb-2">
            <label  class="col-form-label col-sm-2" for="fecha_registro">Fecha Registro</label>
            <div class="col-sm-4">
                <input type="date" name="fecha_registro" id="fecha_registro"
                value="${ producto.fechaRegistro != null ? producto.fechaRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")):""}" class="form-control">
            </div>
            <c:if test="${errores != null &&  !empty errores.fecha_registro }">
                <div style="color: red;"> ${ errores.fecha_registro }</div>
            </c:if>
        </div>
        <div class="row mb-2">
            <label class="col-form-label col-sm-2"  for="categoria">Categoria</label>
            <div class="col-sm-4">
                <select name="categoria" id="categoria" class="form-select">
                    <option value=""> ------- Selecionar -------- </option>
                    <c:forEach items="${categorias}" var="c">
                    <option value="${c.id}"  ${ c.id.equals( producto.categoria.id ) ? "selected" : "" }> ${c.nombre}   </option>
                    </c:forEach>
                </select>
            </div>

             <c:if test="${errores != null && !empty errores.categoria }">
                 <div style="color: red;"> ${ errores.categoria }</div>
             </c:if>
        </div>
        <div>"ID : " ${producto.id}</div>
        <div class="row mb-2" >
            <div>
            <input class="btn btn-primary"  type="submit" value="${ producto.id !=null && producto.id>0 ? "Editar":"Crear"  }">
            </div>
        </div>
        <input type="text" name="id" value="${producto.id}">
    </form>

<jsp:include page="layout/footer.jsp" />