package app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import app.model.User;
import org.hibernate.cfg.Configuration;

public class DBHelper {

    private static volatile DBHelper instance;

    private DBHelper() {

    }

    public static DBHelper getInstance() {
        /* используем дублирующую локальную переменную: Это — микрооптимизация.
           Поле одиночки объявлено как volatile, что заставляет программу обновлять её значение
           из памяти каждый раз при доступе к переменной, тогда как значение обычной переменной
           может быть записано в регистр процессора для более быстрого чтения.
           Используя дополнительную локальную перменную, мы можем ускорить работу с переменной,
           обновляя значение поля только тогда, когда действительно нужно.
         */
        DBHelper result = instance;
        if (result == null) {
            synchronized (DBHelper.class) {
                result = instance;
                if (result == null) {
                    instance = result = new DBHelper();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        String dbUrl = "";
        String dbUsername = "";
        String dbPassword = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
//        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/task01");
        configuration.setProperty("hibernate.connection.username", "ok");
        configuration.setProperty("hibernate.connection.password", "password2019");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
//        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }


}
