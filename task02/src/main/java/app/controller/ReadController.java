package app.controller;

import app.model.Model;
import app.model.User;
import app.service.UserService;
import app.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ReadController {

    @GetMapping("/")
    public ModelAndView allFilms() {
        List<User> users = Model.getInstance().getUserService().readAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("read");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

}
