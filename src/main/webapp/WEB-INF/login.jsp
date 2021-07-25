<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 7/20/2021
  Time: 4:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<% if (request.getAttribute("message") != null) { %>
<p style="color: red"><%=request.getAttribute("message")%>
</p>


<%}%>

<form action="/login" method="post">
    email:<input type="text" name="email"><br>
    <br>
    password:<input type="password" name="password"><br>
    <br>
    <input type="submit" name="Login">
</form>
</body>
</html>
