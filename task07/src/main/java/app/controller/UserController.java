package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @GetMapping("/")
    public String indexPage() {
        return "restClient";
    }

}
