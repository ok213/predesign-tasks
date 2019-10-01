package app.controller;

import app.model.Model;
import app.model.User;
import app.service.UserService;
import app.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UpdateController {

    @GetMapping("/update/{id}")
    public ModelAndView updatePage(@PathVariable("id") int id) {
        User user = Model.getInstance().getUserService().getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateUser(@ModelAttribute("film") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Model.getInstance().getUserService().update(user);
        return modelAndView;
    }
}
