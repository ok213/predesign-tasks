<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Task01 Create user</title>
  <link href="static/css/style.css" rel="stylesheet">
</head>

<body>
  <h2>TASK 01</h2>
  <hr><br>
  <form action="/create" method="POST">
    <fieldset>
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