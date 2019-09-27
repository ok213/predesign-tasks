package app.dao;

import java.io.IOException;
import java.util.Properties;

public class UserDaoFactoryImpl implements UserDaoFactory {

    private static UserDaoFactoryImpl instance;

    private UserDaoFactoryImpl() {

    }

    public static UserDaoFactoryImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoFactoryImpl();
        }
        return instance;
    }

    @Override
    public UserDao createUserDao() {
        Properties prop = getProperties();
        final String dbType = prop.getProperty("db.type");

        if (dbType.equals("jdbc")) {
            return UserDaoJDBCImpl.getInstance();
        }
        if (dbType.equals("hibernate")) {
            return UserDaoHibernateImpl.getInstance();
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
