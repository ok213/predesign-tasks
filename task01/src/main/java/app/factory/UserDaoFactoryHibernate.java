package app.factory;

import app.dao.UserDao;
import app.dao.UserDaoHibernateImpl;
import app.util.DBHelper;

public class UserDaoFactoryHibernate implements UserDaoFactory {

    @Override
    public UserDao createUserDao() {
        return UserDaoHibernateImpl.getInstance();
    }

    @Override
    public DBHelper createDBHelper() {
        return DBHelper.getInstance();
    }

}
