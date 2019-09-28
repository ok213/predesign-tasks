package app.factory;

import app.dao.UserDao;
import app.dao.UserDaoHibernateImpl;
import app.dao.UserDaoJDBCImpl;

import java.io.IOException;
import java.util.Properties;

public class UserDaoFactoryImpl {

    public UserDaoFactory getUserDaoFactory() {
        Properties prop = getProperties();
        final String dbType = prop.getProperty("db.type");

        if (dbType.equals("jdbc")) {
            return new UserDaoFactoryJDBC();
        }
        if (dbType.equals("hibernate")) {
            return new UserDaoFactoryHibernate();
        }

        throw new RuntimeException("Properties: connection type not specified!");
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
