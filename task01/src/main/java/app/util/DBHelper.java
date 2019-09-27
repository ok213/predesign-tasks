package app.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
        Properties prop = getProperties();
        try {
            Class.forName(prop.getProperty("db.Driver"));
            connection = DriverManager.getConnection(prop.getProperty("db.Url"),
                                                     prop.getProperty("db.Username"),
                                                     prop.getProperty("db.Password"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Configuration getConfiguration() {
        Properties prop = getProperties();
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", prop.getProperty("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", prop.getProperty("db.Driver"));
        configuration.setProperty("hibernate.connection.url", prop.getProperty("db.Url"));
        configuration.setProperty("hibernate.connection.username", prop.getProperty("db.Username"));
        configuration.setProperty("hibernate.connection.password", prop.getProperty("db.Password"));
        configuration.setProperty("hibernate.show_sql", prop.getProperty("hibernate.show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", prop.getProperty("hibernate.hbm2ddl.auto"));
        return configuration;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
