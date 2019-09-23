<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Task01 Update user</title>
  <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
</head>

<body>
  <h2>TASK 01</h2>
  <hr><br>
  <form action="${pageContext.request.contextPath}/update" method="POST">
    <fieldset>
      <legend>Update user:</legend>
      <label for="login">Login <em>*</em></label>
      <input id="login" name="login" required><br>
      <label for="password">Password <em>*</em></label>
      <input id="password" name="password" required><br>
      <br>
      <legend>Change fields:</legend>
      <label for="newlogin">New login <em>*</em></label>
      <input id="newlogin" name="newlogin" required><br>
      <label for="newpassword">New password</label>
      <input id="newpassword" name="newpassword"><br>
      <label for="newname">New name</label>
      <input id="newname" name="newname"><br>
      <input type="hidden" name="_method" value="put">
    </fieldset>
    <p><input type="submit" value="send"></p>
  </form>
</body>

</html>