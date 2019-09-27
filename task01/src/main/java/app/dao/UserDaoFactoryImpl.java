package app.dao;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class UserDaoFactoryImpl implements UserDaoFactory {
    // UserDaoFactory - абстрактная фабрика которая возвращает реализацию дао на основе файла property

    @Override
    public UserDao createUserDao() {
        Properties prop = getProperties();
        final String dbType = prop.getProperty("db.type");

        if (dbType.equals("jdbc")) {
            return new UserDaoJDBCImpl();
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
