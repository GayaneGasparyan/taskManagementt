<%@ page import="model.Task" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 7/24/2021
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    Task task = (Task) request.getAttribute("task");
    List<Task> tasks = (List<Task>) request.getAttribute("allTasks");
    if (task!=null){
%>

<%=task.getName() %> <%= task.getDescription()%><br>

<%}%>
<h4>ALL TASKS:</h4>

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


</body>
</html>
