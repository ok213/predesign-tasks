package app.service;

import app.dao.UserDAO;
import app.dao.UserDAOImpl;
import app.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public void create(User user) {
        userDAO.create(user);
    }

    @Override
    public List<User> readAll() {
        return userDAO.readAll();
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }

    @Override
    public User getById(long id) {
        return userDAO.getById(id);
    }
}
