package app.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String indexPage() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }

    @GetMapping("/admin")
    public String listUsers(Model model) {
        model.addAttribute("allUsers", userService.getAll());
        model.addAttribute("allRoles", roleService.getAll());
        return "admin";
    }

    @PostMapping("/admin/create")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        userService.create(user);
        return "redirect:/admin";
    }

    @ResponseBody
    @GetMapping("/admin/update/{id}")
    public Map<String, String> updatePage(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        String roles = roleService.getAll().stream().map(Object::toString).collect(Collectors.joining(","));
        Map<String, String> response = new HashMap<>();
        response.put("id", user.getId().toString());
        response.put("login", user.getLogin());
        response.put("password", user.getPassword());
        response.put("email", user.getEmail());
        response.put("roles", roles);
        return response;
    }

    @PostMapping("/admin/update")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String homePageUser() {
        return "user";
    }

}
