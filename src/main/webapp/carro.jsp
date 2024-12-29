<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="layout/header.jsp" />
   <h2> ${title}</h2>
    <c:choose>
    <c:when test="${carro.listItems.isEmpty()}">
        <div class="alert alert-warning"> Lo sentimos no hay Productos en el carro de compras</div>
    </c:when>
    <c:otherwise>
    <form name="formcarro" action="${pageContext.request.contextPath}/carro/actualizar" method="post">
          <table class="table table-hover table-striped">
            <tr>
                <th>id</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>total</th>
            </tr>
            <c:forEach items="${carro.listItems}" var="item">
                <tr>
                    <td>${item.producto.id}</td>
                    <td>${item.producto.nombre}</td>
                    <td>${item.producto.precio}</td>
                    <td>${item.cantidad}</td>
                    <td>${item.importe}</td>
                </tr>
             </c:forEach>
                <tr>
                    <td colspan="4" style="text-align: right">Total :</td>
                    <td>${carro.total}</td>
                </tr>

          </table>
          <a class="btn btn-primary" href="javascript:document.form.carro.submit();">Actualizar</a>
     </form>
    </c:otherwise>
    </c:choose>
    <div class="my-2">
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/index.jsp"> Volver...</a>
        <a class="btn btn-success" href="${pageContext.request.contextPath}/productos">seguir comprando</a>
    </div>

<jsp:include page="layout/footer.jsp" />
