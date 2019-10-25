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
        userService.create(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/update/{id}")
    public String updatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("actionPath", "update");
        model.addAttribute("user", userService.getById(id));
        return "update";
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

}
