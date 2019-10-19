<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Task03</title>
    <style>
        td, th {
            padding: 3px;
            border: 1px solid grey;
        }
    </style>
</head>
<body style="font-family: 'Palatino Linotype', serif; max-width: 60%; padding-left: 30px;">
<h2>TASK 03</h2>
<hr><br>
<p>
    <a href="<c:url value="/create"/>" style="margin-left: 30px;">CREATE USER</a>
</p>
<table style="width: 100%; border-spacing: 0; border: 1px solid grey;">
    <tr>
        <th>id</th>
        <th>login</th>
        <th>password</th>
        <th>name</th>
        <th>roles</th>
        <th>action</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td style="text-align: center;">${user.id}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>${user.name}</td>
            <td>
                <c:forEach var="role" items="${user.roles}">
                    ${role.role}<br>
                </c:forEach>
            </td>
            <td style="text-align: center;">
                <c:if test = "${user.login != 'admin'}">
                    <c:if test="${user.login != 'user'}">
                        <a href="/update/${user.id}">edit</a>&nbsp;&nbsp;
                        <a href="/delete/${user.id}">delete</a>
                    </c:if>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<br><br>
<form action="/logout" method="post">
    <input value="Logout" type="submit">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
