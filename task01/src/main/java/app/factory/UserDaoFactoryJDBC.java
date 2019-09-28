package app.factory;

import app.dao.UserDao;
import app.dao.UserDaoJDBCImpl;
import app.util.DBHelper;

public class UserDaoFactoryJDBC implements UserDaoFactory {

    @Override
    public UserDao createUserDao() {
        return UserDaoJDBCImpl.getInstance();
    }

    @Override
    public DBHelper createDBHelper() {
        return DBHelper.getInstance();
    }

}
