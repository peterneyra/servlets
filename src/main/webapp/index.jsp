<jsp:include page="layout/header.jsp" />

<h2> ${title}</h2>
<h4> Manejo de cookies/Sesiones en el servidor HTTP</h4>

<ul class="list-group">
 <!--para ver referer ${pageContext.request.contextPath}  ==>> /webapp-session -->
    <li class="list-group-item active" >Menu de Opciones</li>
    <li class="list-group-item" ><a href="${pageContext.request.contextPath}/productos.html"> Mostrar Productos HTML </a> </li>
    <li class="list-group-item" ><a href="${pageContext.request.contextPath}/login.jsp"> Login </a> </li>
    <li class="list-group-item" ><a href="${pageContext.request.contextPath}/logout"> Cerrar Sesion  </a> </li>
    <li class="list-group-item" ><a href="${pageContext.request.contextPath}/ver-carro"> Ver carrito de Compras  </a> </li>
</ul>

<div>"mostramos : "  ${pageContext.request.contextPath} </div>
<jsp:include page="layout/footer.jsp" />