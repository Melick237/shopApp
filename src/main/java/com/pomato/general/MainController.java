package com.pomato.general;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

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

    @GetMapping("/contact")
    public String handleContactPage(){
        return "contact";
    }


}
