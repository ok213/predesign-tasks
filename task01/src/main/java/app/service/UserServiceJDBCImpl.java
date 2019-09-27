package app.service;

import app.dao.UserDaoJDBCImpl;
import app.model.User;

import java.util.List;

public class UserServiceJDBCImpl implements UserService {

    private UserDaoJDBCImpl userDaoJDBCImpl;

    public UserServiceJDBCImpl() {
        userDaoJDBCImpl = new UserDaoJDBCImpl();
    }

    @Override
    public void create(User user) {
        if (!validate(user)) {
            userDaoJDBCImpl.create(user);
        }
    }

    @Override
    public List<User> getAll() {
        return userDaoJDBCImpl.readAll();
    }

    @Override
    public void updateUser(User user) {
        if (getById(user.getId()) != null) {
             userDaoJDBCImpl.update(user);
        }
    }

    @Override
    public void delete(long id) {
        userDaoJDBCImpl.delete(id);
    }

    @Override
    public User getById(long id) {
        return userDaoJDBCImpl.getById(id);
    }

    @Override
    public boolean validate(User user) {
        User userFromBase = userDaoJDBCImpl.getByLoginAndPassword(user.getLogin(), user.getPassword());
        return user.equals(userFromBase);
    }

}
