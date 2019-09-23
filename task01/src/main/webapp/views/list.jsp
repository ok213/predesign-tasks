<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<sql:query var="rs" dataSource="jdbc/task01">
    SELECT * FROM users
</sql:query>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Task01</title>
  <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
</head>
<body>
  <h2>TASK 01</h2>
  <hr><br>
  <p>
  <a href="${pageContext.request.contextPath}/create">CREATE USER</a>
  <a href="${pageContext.request.contextPath}/update">UPDATE USER</a>
  <a href="${pageContext.request.contextPath}/delete">DELETE USER</a>
  </p>
    <table>
        <tr>
          <th>id</th>
          <th>login</th>
          <th>password</th>
          <th>name</th>

<%-- JSP with Connection Pooling --%>
          <c:forEach var="row" items="${rs.rows}">
            <tr>
                <td>${row.id}</td>
                <td>${row.login}</td>
                <td>${row.password}</td>
                <td>${row.name}</td>
            </tr>
          </c:forEach>

<%-- JNDI Datasource--%>
<%--        <c:forEach items="${listUsers}" var="user">--%>
<%--            <tr>--%>
<%--                <td>${user.getId()}</td>--%>
<%--                <td>${user.getLogin()}</td>--%>
<%--                <td>${user.getPassword()}</td>--%>
<%--                <td>${user.getName()}</td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
        </table>
</body>
</html>