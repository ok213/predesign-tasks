<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="userId" value="${user.id}" scope="request" />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <c:if test="${empty userId}">
        <title>Add user</title>
    </c:if>
    <c:if test="${!empty userId}">
        <title>Update user</title>
    </c:if>
    <style>
        legend {
            padding-left: 3px;
            font-weight: bold;
            font-variant: small-caps;
        }
        label {
            width: 110px;
            display: inline-block;
            vertical-align: top;
            margin: 6px;
        }
        em {
            font-weight: bold;
            font-style: normal;
            color: #f00;
        }
        input:focus {
            background: #eaeaea;
        }
        input {
            width: 250px;
        }
        input[type=submit] {
            width: 170px;
            padding: 10px;
        }
        select {
            width: 250px;
        }
    </style>
</head>

<body style="font-family: 'Palatino Linotype', serif; max-width: 500px; padding-left: 30px;">
<h2>TASK 03</h2>
<hr><br>
<c:if test="${empty userId}">
    <c:url value="/create" var="link"/>
</c:if>
<c:if test="${!empty userId}">
    <c:url value="/update" var="link"/>
</c:if>
<form action="${link}" method="POST">
    <fieldset>
        <legend>
            <c:if test="${empty userId}">
                Create user:
            </c:if>
            <c:if test="${!empty userId}">
                Update user:
            </c:if>
        </legend>
        <label for="iden">ID</label>
        <input id="iden" name="id" value="${userId}" style="background-color: lightgrey" readonly><br>
        <label for="login">Login</label>
        <input id="login" name="login" value="${user.login}"><br>
        <label for="password">Password</label>
        <input id="password" name="password" value="${user.password}"><br>
        <label for="name">Name</label>
        <input id="name" name="name" value="${user.name}"><br>

        <label>Roles</label>
        <select size="${user.authorities.size()}" multiple name="roles">
        <c:forEach var="role" items="${user.authorities}">
            <option value="${role.authority}">${role.authority}</option>
        </c:forEach>
        </select>


    </fieldset>
    <p>
        <c:if test="${empty userId}">
            <input type="submit" value="Create new user">
        </c:if>
        <c:if test="${!empty userId}">
            <input type="submit" value="Update user">
        </c:if>
    </p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>

</html>

