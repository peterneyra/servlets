<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>formulario Login</title>
</head>
<body>
<h1> Iniciar Ssesion</h1>
<form action="/webapp-session/login" method="post">
    <div>
        <label for="username"></label>
        <div>
        <input type="text" name="username" id="username" value="admin">
        </div>
    </div>
    </br>
    <div>
        <label for="password"></label>
        <div>
        <input type="text" name="password" id="password" value="12345">
        </div>
    </div>
    </br>
    <div><input type="submit" value="login"></div>


</form>
</body>
</html>