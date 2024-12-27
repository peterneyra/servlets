<%@page contentType="text/html"  pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!--   <%@include file ="layout/header.jsp" %>    -->
<jsp:include page="layout/header.jsp" />

    <h2> ${title}</h2>
    <c:if test="${not empty username}">
        <div class="alert alert-info" > Hola ${username} , bienvenido !!!</div>
        <div>
        <a class="btn btn-primary my-2" href="${pageContext.request.contextPath}/productos/form"> Crear [+]</a>
        </div>
    </c:if>
    <table class="table table-hover table-triped">
    <tr>
        <th>id</th>
        <th>nombre</th>
        <th>Tipo Categ</th>
        <c:if test="${not empty username}">
            <th>Precio</th>
            <th>Agregar</th>
        </c:if>
    </tr>

    <c:forEach items="${productos}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.nombre}</td>
            <td>${p.categoria.nombre}</td>
            <c:if test="${ not empty username}">
                <td>${p.precio}</td>
                <td>
                    <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/agregar-carro?id=${p.id}">Agregar al Carro</a>
                </td>
                <td>
                    <a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/productos/form?id=${p.id}">Editar</a>
                </td>
                <td>
            <form action="${pageContext.request.contextPath}/productos" method="post">
                                <input type="hidden" name ="id" value = ${p.id}>
                                <input type="hidden" name ="cadena" value= 'si'>
                                <button class="btn btn-sm btn-danger" type="submit">Eliminar</button>
             </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>


    </table>
    <p>${applicationScope.mensaje}</p>
    <p>${requestScope.mensaje}</p>

<h1>"--> : " ${error}</h1>
<jsp:include page="layout/footer.jsp" />