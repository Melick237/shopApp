package com.pomato.general;

import com.pomato.phone.entities.Phone;
import com.pomato.phone.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/")
    public String handleIndexPage(Model model){
        List<Phone> phones = new ArrayList<>();

        for(Phone p : phoneService.getPhones()){
            if(phones.size() < 6)
                phones.add(p);
        }

        model.addAttribute("phones",phones);
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
