package app.controller;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ReadController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView allFilms() {
        List<User> users = userService.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("read");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

}
