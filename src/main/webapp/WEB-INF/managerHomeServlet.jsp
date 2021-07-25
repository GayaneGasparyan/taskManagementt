<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Task" %><%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 7/22/2021
  Time: 7:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ManagerHome</title>
</head>
<body>

<h5>ADD USER:</h5><br>
<form action="/managerHomeServlet" method="post">
    name:<input type="text" name="name"><br>
    surname:<input type="text" name="surname"><br>
    email:<input type="text" name="email"><br>
    password:<input type="password" name="password"><br>
    re-password: <input type="password" name="re-password"> <br>
    UserType: <input type="text" name="userType"> <br>
<%--    UserType:<select id="user_type">--%>
<%--    <option value="Manager">Manager</option>--%>
<%--    <option value="Admin">User</option>--%>
<%--</select>--%>
    <input type="submit" value="registerUser"><br>
</form>
<%
    User user = (User) request.getAttribute("user");
    List<User> users = (List<User>) request.getAttribute("allUsers");
    if (user!=null){
%>

welcome<%=user.getName()%> <%=user.getSurname()%><br>

<%}%>

ALL USERS:

<table border="1">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Email</td>
        <td>UserType</td>
    </tr>
    <% if (users!=null){
        for (User user1 : users) { %>
    <tr>
        <td><%=user1.getId()%>

        </td>
        <td><%=user1.getName()%>
        </td>

        <td><%=user1.getSurname()%>
        </td>

        <td><%=user1.getEmail()%>
        </td>
        <td><%=user1.getUserType()%>
    </td>
    </tr>

    <% }
    }  %>


</table>
<h3>ADD TASK:</h3>

<form action="/addTaskServlet" method="post">
    name:<input type="text" name="name"><br>
    <br>
    description:<input type="text" name="description"><br>
    <br>
    Status: <input type="text" name="status"> <br>

<%--    Status:<select id="status">--%>
<%--    <option value="Manager">IN_PROGRESS</option>--%>
<%--    <option value="Admin">TASK</option>--%>
<%--</select>--%>

    Deadline:<input type="date" name="deadline">  <br>
    <br>
    <input type="submit" value="Add"><br>
</form>



<%
    Task task = (Task) request.getAttribute("task");
    List<Task> tasks = (List<Task>) request.getAttribute("allTasks");
    if (task!=null){
%>

<%=task.getName() %> <%= task.getDescription()%><br>

<%}%>
ALL TASKS:

<table border="1">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Description</td>
        <td>Status</td>
        <td>Deadline</td>


    </tr>
    <% if (tasks!=null){
        for (Task task1 : tasks) { %>
    <tr>
        <td><%=task1.getId()%>
        </td>
        <td><%=task1.getName()%>
        </td>
        <td><%=task1.getDescription()%>
        </td>
        <td><%=task1.getStatus()%>
        </td>
        <td><%=task1.getDeadline()%>
        </td>


    </tr>

    <% }
    }  %>



</table>



</body>
</html>
