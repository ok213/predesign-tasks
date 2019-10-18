package app.controller;

import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public ModelAndView loginUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

//    @GetMapping("/sign_up")
//    public String getSignUp(Model model) {
//        model.addAttribute("user", new User());
//        return "/auth/sign_up";
//    }
//
//    @PostMapping("/sign_up")
//    public String signUp(@ModelAttribute("user") User user) {
////        userValidator.validate(user, result);
////        if (result.hasErrors()) {
////            return "/auth/sign_up";
////        }
////        userService.add(user);
//        return "redirect:/";
//    }
//
//    @RequestMapping("/login")
//    public String login(@RequestParam(name = "error", required = false) Boolean error,
//                        Model model) {
//        if (Boolean.TRUE.equals(error)) {
//            model.addAttribute("error", true);
//        }
//        return "auth/sign_in";
//    }



}
