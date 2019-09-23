package app.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.annotation.Resource;

@Deprecated
public class DBHelper {

    private final String RESOURSE_NAME = "jdbc/task01";
    private final String RESOURSE_URL = "jdbc:mysql://localhost:3306/task01";
    private final String RESOURSE_USER = "root";
    private final String RESOURSE_PASSWORD = "toortoor";

    private static Connection connection;

    private DBHelper() {

    }

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (DBHelper.class) {
                if (connection == null) {
                    connection = new DBHelper().init();
                }
            }
        }
        return connection;
    }

    private Connection init() {
        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            DataSource ds = (DataSource) ctx.lookup(RESOURSE_NAME);
            return ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Deprecated
    private Connection initOld() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(RESOURSE_URL, RESOURSE_USER, RESOURSE_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
