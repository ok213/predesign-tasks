package app.service;

import app.dao.UserDAO;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void create(User user) {
        if (!validate(user)) {
            userDAO.create(user);
        }
    }

    @Override
    public List<User> getAll() {
        return userDAO.readAll();
    }

    @Override
    public void update(User user) {
        if (getById(user.getId()) != null) {
            userDAO.update(user);
        }
    }

    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }

    @Override
    public User getById(long id) {
        return userDAO.getById(id);
    }

    @Override
    public boolean validate(User user) {
        User userFromBase = userDAO.getByLoginAndPassword(user.getLogin(), user.getPassword());
        return user.equals(userFromBase);
    }
}
