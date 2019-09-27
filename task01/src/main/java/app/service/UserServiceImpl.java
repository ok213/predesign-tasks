package app.service;

import app.dao.UserDao;
import app.dao.UserDaoFactory;
import app.dao.UserDaoFactoryImpl;
import app.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        userDao = UserDaoFactoryImpl.getInstance().createUserDao();
    }

    @Override
    public void create(User user) {
        if (!validate(user)) {
            userDao.create(user);
        }
    }

    @Override
    public List<User> getAll() {
        return userDao.readAll();
    }

    @Override
    public void updateUser(User user) {
        if (getById(user.getId()) != null) {
            userDao.update(user);
        }
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public boolean validate(User user) {
        User userFromBase = userDao.getByLoginAndPassword(user.getLogin(), user.getPassword());
        return user.equals(userFromBase);
    }
}
