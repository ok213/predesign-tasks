<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Task03</title>
</head>
<body style="font-family: 'Palatino Linotype', serif; max-width: 60%; padding-left: 30px;">
<h2>TASK 03</h2>
<hr><br>
User page!

<br><br>
<form action="/logout" method="post">
    <input value="Logout" type="submit">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>

