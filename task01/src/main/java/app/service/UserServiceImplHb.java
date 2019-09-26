package app.service;

import app.dao.UserDaoImplHb;
import app.model.User;
import app.util.DBHelper;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserServiceImplHb implements UserService{

    private static UserServiceImplHb userServiceImplHb;
    private SessionFactory sessionFactory;

    public UserServiceImplHb(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserServiceImplHb getInstance() {
        if (userServiceImplHb == null) {
            userServiceImplHb = new UserServiceImplHb(DBHelper.getSessionFactory());
        }
        return userServiceImplHb;
    }


    @Override
    public void create(User user) {
        if (!validate(user)) {
            new UserDaoImplHb(sessionFactory.openSession()).create(user);
        }
    }

    @Override
    public List<User> getAll() {
        return new UserDaoImplHb(sessionFactory.openSession()).readAll();
    }

    @Override
    public void updateUser(User user) {
        if (getById(user.getId()) != null) {
            new UserDaoImplHb(sessionFactory.openSession()).update(user);
        }
    }

    @Override
    public void delete(long id) {
        new UserDaoImplHb(sessionFactory.openSession()).delete(id);
    }

    @Override
    public User getById(long id) {
        return new UserDaoImplHb(sessionFactory.openSession()).getById(id);
    }

    @Override
    public boolean validate(User user) {
        User userFromBase = new UserDaoImplHb(sessionFactory.openSession())
                            .getByLoginAndPassword(user.getLogin(), user.getPassword());
        return user.equals(userFromBase);
    }
}
