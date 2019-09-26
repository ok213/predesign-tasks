<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="user" scope="request" type="app.model.User"/>
<jsp:useBean id="link" scope="request" type="java.lang.String"/>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Task01 Update and Delete User</title>
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
<form action="${link}" method="POST">
    <fieldset>
        <legend>
            <c:if test = "${link == '/update'}">Update user</c:if>
            <c:if test = "${link == '/delete'}">Delete user</c:if>
        </legend>
        <label for="login">Login <em>*</em></label>
        <input id="login" name="login" required><br>
        <label for="password">Password <em>*</em></label>
        <input id="password" name="password" required><br>
        <br>
        <legend>USER:</legend>
        <label for="iden">ID</label>
        <input id="iden" name="iden" value="${user.getId()}" style="background-color: lightgrey" readonly><br>
        <label for="newlogin">New login</label>
        <input id="newlogin" name="newlogin" value="${user.getLogin()}"
               <c:if test = "${link == '/delete'}">style="background-color: lightgrey" readonly</c:if>><br>
        <label for="newpassword">New password</label>
        <input id="newpassword" name="newpassword" value="${user.getPassword()}"
                <c:if test = "${link == '/delete'}">style="background-color: lightgrey" readonly</c:if>><br>
        <label for="newname">New name</label>
        <input id="newname" name="newname" value="${user.getName()}"
                <c:if test = "${link == '/delete'}">style="background-color: lightgrey" readonly</c:if>><br>
    </fieldset>
    <p><input type="submit" value="send"></p>
</form>
</body>

</html>
