<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <c:if test="${empty user.getId()}">
        <title>Add user</title>
    </c:if>
    <c:if test="${!empty user.getId()}">
        <title>Update user</title>
    </c:if>
    <style>
        legend {
            padding: 0px 3px;
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
    </style>
</head>

<body style="font-family: 'Palatino Linotype', serif; max-width: 500px; padding: 0px 30px;">
<h2>TASK 02</h2>
<hr><br>
<c:if test="${empty user.getId()}">
    <c:url value="/create" var="link"/>
</c:if>
<c:if test="${!empty user.getId()}">
    <c:url value="/update" var="link"/>
</c:if>
<form action="${link}" method="POST">
    <fieldset>
        <legend>
            <c:if test="${empty user.getId()}">
                Create user:
            </c:if>
            <c:if test="${!empty user.getId()}">
                Update user:
            </c:if>
        </legend>
        <label for="iden">ID</label>
        <input id="iden" name="id" value="${user.getId()}" style="background-color: lightgrey" readonly><br>
        <label for="login">Login</label>
        <input id="login" name="login" value="${user.getLogin()}"><br>
        <label for="password">Password</label>
        <input id="password" name="password" value="${user.getPassword()}"><br>
        <label for="name">Name</label>
        <input id="name" name="name" value="${user.getName()}"><br>
    </fieldset>
    <p>
        <c:if test="${empty user.getId()}">
            <input type="submit" value="Create new user">
        </c:if>
        <c:if test="${!empty user.getId()}">
            <input type="submit" value="Update user">
        </c:if>
    </p>
</form>
</body>

</html>

