<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Task01</title>
    <link href="<c:url value="/static/css/style.css"/>" rel="stylesheet">
</head>
<body>
<h2>TASK 01</h2>
<hr><br>
<p>
    <a href="<c:url value="/create"/>">CREATE USER</a>
    <a href="<c:url value="/update"/>">UPDATE USER</a>
    <a href="<c:url value="/delete"/>">DELETE USER</a>
</p>
<table>
    <tr>
        <th>id</th>
        <th>login</th>
        <th>password</th>
        <th>name</th>
    </tr>
    <c:forEach var="user" items="${listUsers}">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getLogin()}</td>
            <td>${user.getPassword()}</td>
            <td>${user.getName()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>