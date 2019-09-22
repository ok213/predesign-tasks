package app.servlets;

import app.util.DBHelper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/demo")
public class DemoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        Connection connection = DBHelper.getConnection();
        try {
            Statement statement = connection.createStatement();
            // language=MYSQL
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            resultSet.next();
            response.getWriter().println(resultSet.getInt(1) + " --- " + resultSet.getString(2));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        response.getWriter().println(DBHelper.getConnection());


    }

}
