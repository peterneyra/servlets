<%@page contentType="text/html" pageEncoding="UTF-8" %>

<jsp:include page="layout/header.jsp" />


<h2> ${title}</h2>
<div>"mostramos :${pageContext.request.contextPath}/login"</div>
<div>"mostramos :/webapp-session/login"</div>


<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="row my-2">
        <label for="username" class="form-label" >Usuario</label>
        <div>
        <input type="text" name="username" id="username" value="admin" class="form-control" >
        </div>
    </div>

    <div class="row my-2">
        <label for="password" class="form-label" >Password</label>
        <div>
        <input type="text" name="password" id="password" value="12345" class="form-control" >
        </div>
    </div>

    <div class="row my-2">
        <input type="submit" value="login" class="btn btn-primary" >
    </div>
</form>

<jsp:include page="layout/footer.jsp" />