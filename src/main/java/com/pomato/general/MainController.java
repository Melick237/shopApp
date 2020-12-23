package com.pomato.general;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String handleIndexPage(){
        return "index";
    }

    @GetMapping("/about")
    public String handleAboutPage(){
        return "about";
    }
}
