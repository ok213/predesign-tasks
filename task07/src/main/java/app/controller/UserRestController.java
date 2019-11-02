package app.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleServiceApi;
import app.service.UserServiceApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
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
    public ResponseEntity updateUser(@Valid @RequestBody User user) {
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
