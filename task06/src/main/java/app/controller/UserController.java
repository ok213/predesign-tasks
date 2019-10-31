package app.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
        model.addAttribute("isUser", false);
        return "index";
    }

    @PostMapping("/admin/create")
    public String createUser(@ModelAttribute User user) {
        userService.create(user);
        return "redirect:/admin";
    }

    @ResponseBody
    @GetMapping("/admin/update/{id}")
    public String updatePage(@PathVariable("id") Long id) {
        List<Object> list = new ArrayList<>();
        list.add(userService.getById(id));
        list.add(roleService.getAll());
        return new Gson().toJson(list);
    }

    @PostMapping("/admin/update")
    public String updateUser(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String homePageUser(Model model) {
        model.addAttribute("isUser", true);
        return "index";
    }

}
