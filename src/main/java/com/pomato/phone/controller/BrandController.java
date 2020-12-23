package com.pomato.phone.controller;

import com.pomato.phone.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrandController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/brand")
    public String handleBrandPage(Model model){

        model.addAttribute("phones", phoneService.getPhones());
        return "brand";
    }
}
