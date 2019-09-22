<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Task01 Create user</title>
  <style>
    body {
      font-family: 'Palatino Linotype', serif;
      max-width: 450px;
      padding: 0px 30px;
    }
    fieldset {
      margin-bottom: 15px;
      padding: 10px;
    }
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

<body>
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