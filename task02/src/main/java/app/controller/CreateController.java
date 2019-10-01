package app.controller;

import app.model.Model;
import app.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreateController {

    @GetMapping("/create")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView addFilm(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Model.getInstance().getUserService().create(user);
        return modelAndView;
    }

}
