<%@ page import="java.util.List" %>
<%@ page import="app.models.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Task01</title>
  <style>
      table {
       width: 70%; /* Ширина таблицы */
       border-spacing: 0px; /*Расстояние между ячейками*/
       border: 1px solid grey;
      }
      td, th {
       padding: 3px;
       border: 1px solid grey;
      }
      td:first-child {
          text-align: center;
      }
      a {
        margin-left: 30px;
      }
     </style>
</head>
<body>
        <p><a href="/demo">DEMO</a></p>
  <p>
  <a href="/create">CREATE USER</a>
  <a href="/update">UPDATE USER</a>
  <a href="/delete">DELETE USER</a>
  </p>
    <table>
        <tr>
          <th>id</th>
          <th>login</th>
          <th>password</th>
          <th>name</th>
        </tr>
        <%
            List<User> listUsers = (List<User>) request.getAttribute("listUsers");
            if (listUsers != null && !listUsers.isEmpty()) {
                for (User user : listUsers) {
                    out.println("<tr>");
                    out.println("<td>" + user.getId() + "</td>");
                    out.println("<td>" + user.getLogin() + "</td>");
                    out.println("<td>" + user.getPassword() + "</td>");
                    out.println("<td>" + user.getName() + "</td>");
                    out.println("</tr>");
                }
            }
        %>
       </table>
</body>
</html>