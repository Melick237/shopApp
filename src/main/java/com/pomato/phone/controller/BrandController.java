package com.pomato.phone.controller;

import com.pomato.phone.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BrandController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/brand")
    public String handleBrandPage(Model model){

        model.addAttribute("phones", phoneService.getPhones());
        return "brand";
    }

    @GetMapping("/gap")
    public String handlePriceGap(@RequestParam("min") Optional<Long> min, @RequestParam("max") Optional<Long> max , Model model){
        if(min.isEmpty() || max.isEmpty())
            return "redirect:/brand";
        model.addAttribute("phones", phoneService.searchPhoneInGap(min.get(),max.get()));
        return "brand";


    }

    @GetMapping("/stern")
    public String handleStars(@RequestParam("stars") Optional<Integer> stars, Model model){
        if(stars.isEmpty())
            return "redirect:/brand";
        model.addAttribute("phones", phoneService.searchPhoneStars(stars.get()));
        return "brand";


    }
}
