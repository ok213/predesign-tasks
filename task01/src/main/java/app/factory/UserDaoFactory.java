package app.factory;

import app.dao.UserDao;
import app.util.DBHelper;

public interface UserDaoFactory {

    UserDao createUserDao();
    DBHelper createDBHelper();

}
