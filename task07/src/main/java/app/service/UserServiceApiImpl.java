package app.service;

import app.model.Role;
import app.model.User;
import app.repository.RoleRepository;
import app.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceApiImpl implements UserServiceApi {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceApiImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public void createUser(User user) {
        if (userRepository.findUserByLogin(user.getLogin()) == null) {
            user.setRoles(getUserRoles(user.getRoles()));
            userRepository.save(user);
        }
    }

    @Override
    public void updateUser(User user) {
        user.setRoles(getUserRoles(user.getRoles()));
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        getUserById(id).setRoles(null);
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    private Set<Role> getUserRoles(Set<Role> rolesFromForm) {
        Set<Role> roles = new HashSet<>();
        List<Role> allRoles = new ArrayList<>();
        roleRepository.findAll().forEach(allRoles::add);
        for (Role userRole : rolesFromForm) {
            for (Role role : allRoles) {
                if (role.getId().equals(userRole.getId())) {
                    roles.add(role);
                }
            }
        }
        return roles;
    }
}
