package app.service.api;

import app.model.User;

import java.util.List;

public interface UserServiceApi {

    List<User> getAllUsers();
    void createUser(User user);
    void updateUser(User user);
    void deleteUserById(Long id);

    User getUserById(Long id);

}
