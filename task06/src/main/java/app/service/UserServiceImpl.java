package app.service;

import app.dao.RoleDAO;
import app.dao.UserDAO;
import app.model.Role;
import app.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public void create(User user) {
        if (!validate(user)) {
            user.setRoles(getUserRolesFromForm(user.getRoles()));
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
            user.setRoles(getUserRolesFromForm(user.getRoles()));
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

    private Set<Role> getUserRolesFromForm(Set<Role> userRolesForm) {
        Set<Role> userRoles = new HashSet<>();
        List<Role> allRoles = roleDAO.readAll();
        for (Role userRole : userRolesForm) {
            for (Role role : allRoles) {
                if (userRole.getRole().equals(role.getRole())) {
                    userRoles.add(role);
                }
            }
        }
        return userRoles;
    }

}
