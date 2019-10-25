package app.controller;

import app.model.User;

import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String indexPage() {
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String listUsers(Model model) {
        Iterable<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "read";
    }

    @GetMapping("/user")
    public String homePageUser() {
        return "user";
    }

    @GetMapping("/admin/create")
    public String createPage(Model model) {
        model.addAttribute("actionPath", "create");
        return "update";
    }

    @PostMapping("/admin/create")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        userService.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/update/{id}")
    public String updatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("actionPath", "update");
        model.addAttribute("user", userService.getUserById(id));
        return "update";
    }

    @PostMapping("/admin/update")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

}
