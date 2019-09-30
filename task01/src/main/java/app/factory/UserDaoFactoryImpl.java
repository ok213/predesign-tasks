package app.factory;

import app.util.PropHelper;

import java.util.Properties;

public class UserDaoFactoryImpl {

    public UserDaoFactory getUserDaoFactory() {
        Properties prop = PropHelper.getInstance().getProperties();
        final String dbType = prop.getProperty("db.type");

        if (dbType.equals("jdbc")) {
            return new UserDaoFactoryJDBC();
        }
        if (dbType.equals("hibernate")) {
            return new UserDaoFactoryHibernate();
        }

        throw new RuntimeException("Properties: connection type not specified!");
    }

}
