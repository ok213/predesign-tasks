package app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelperJDBC {

    private static final String dbUrl = "jdbc:mysql://127.0.0.1:3306/task01";
    private static final String dbUsername = "ok";
    private static final String dbPassword = "password2019";

    private static Connection connection;

    private DBHelperJDBC() {

    }

    public static Connection getConnection() {
        if (connection == null) {
            connection = createConnection();
        }
        return connection;
    }

    private static Connection createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
