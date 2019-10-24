package app.service;

import app.model.User;

public interface UserService {

    Iterable<User> getAllUsers();
    void createUser(User user);
    void updateUser(User user);
    void deleteUserById(Long id);
    User getUserById(Long id);
}
