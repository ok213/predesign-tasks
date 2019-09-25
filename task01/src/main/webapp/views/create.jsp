<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Task01 Create user</title>
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
<h2>TASK 01</h2>
<hr><br>
<form action="${pageContext.request.contextPath}/create" method="POST">
    <fieldset style="margin-bottom: 15px; padding: 10px;">
        <legend>Create user:</legend>
        <label for="login">Login <em>*</em></label>
        <input id="login" name="login" required><br>
        <label for="password">Password <em>*</em></label>
        <input id="password" name="password" required><br>
        <label for="name">Name</label>
        <input id="name" name="name"><br>
    </fieldset>
    <p><input type="submit" value="send"></p>
</form>
</body>

</html>