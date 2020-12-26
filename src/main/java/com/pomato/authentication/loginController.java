package com.pomato.authentication;


import com.pomato.phone.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
    @Autowired
    private PhoneService phoneService;

    @GetMapping("/login")
    public String handleLoginPage(Model model){

        model.addAttribute("phones", phoneService.getPhones());
        return "login";
    }

    @GetMapping("/register")
    public String handleRegisterPage(Model model){

        model.addAttribute("phones", phoneService.getPhones());
        return "register";
    }
}
