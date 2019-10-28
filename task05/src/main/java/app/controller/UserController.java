package app.controller;

import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


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

//    @GetMapping("/admin/update/{id}")
//    public String updatePage(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("actionPath", "update");
//        model.addAttribute("user", userService.getById(id));
//        return "admin";
//    }

//    @GetMapping("/admin/update/{id}")
//    public String updatePage(@PathVariable("id") Long id) {
////        User user = userService.getById(id);
////
////        Map<String, String> map = new HashMap<>();
////        map.put("id", user.getId().toString());
////        map.put("login", user.getLogin());
////        map.put("password", user.getPassword());
////        map.put("email", user.getEmail());
////        map.put("roles", user.printRoles());
////        return map;
//        return new String("{'aa':'bb'}");
//    }

    @GetMapping("/admin/update/{id}")
    public ModelAndView updateUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        return modelAndView;
    }














    @GetMapping("/user")
    public String homePageUser() {
        return "admin";
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
