package app.controller.api;

import app.model.Role;
import app.model.User;
import app.service.api.RoleServiceApi;
import app.service.api.UserServiceApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    private final UserServiceApi userServiceApi;
    private final RoleServiceApi roleServiceApi;

    public UserRestController(UserServiceApi userServiceApi, RoleServiceApi roleServiceApi) {
        this.userServiceApi = userServiceApi;
        this.roleServiceApi = roleServiceApi;
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userServiceApi.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userServiceApi.getUserById(id);
        if (user == null) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        userServiceApi.createUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        if (userServiceApi.getUserById(user.getId()) == null) {
            ResponseEntity.badRequest().build();
        }
        userServiceApi.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (userServiceApi.getUserById(id) == null) {
            ResponseEntity.badRequest().build();
        }
        userServiceApi.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/role")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleServiceApi.getAllRoles());
    }

}
