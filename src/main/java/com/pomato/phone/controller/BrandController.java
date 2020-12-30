package com.pomato.phone.controller;

import com.pomato.phone.entities.Phone;
import com.pomato.phone.service.PhoneService;
import com.pomato.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class BrandController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/brand")
    public String handleBrandPage(Model model , @AuthenticationPrincipal User user){

        model.addAttribute("phones", phoneService.getPhones());

        model.addAttribute("user" , user);
        return "brand";
    }

    @GetMapping("/gap")
    public String handlePriceGap(@RequestParam("min") Optional<Long> min, @RequestParam("max") Optional<Long> max , Model model , @AuthenticationPrincipal User user){
        if(min.isEmpty() || max.isEmpty())
            return "redirect:/brand";
        model.addAttribute("phones", phoneService.searchPhoneInGap(min.get(),max.get()));
        model.addAttribute("user" , user);
        return "brand";


    }

    @GetMapping("/stern")
    public String handleStars(@RequestParam("stars") Optional<Integer> stars, Model model , @AuthenticationPrincipal User user){
        if(stars.isEmpty())
            return "redirect:/brand";
        model.addAttribute("phones", phoneService.searchPhoneStars(stars.get()));
        model.addAttribute("user" , user);
        return "brand";


    }

    @GetMapping("/stock")
    public String handleStock(@RequestParam("stock") Optional<Long> stock, Model model , @AuthenticationPrincipal User user){
        if(stock.isEmpty())
            return "redirect:/brand";
        model.addAttribute("phones", phoneService.searchPhoneStock(stock.get()));
        model.addAttribute("user" , user);
        return "brand";


    }

    @GetMapping("/unique")
    public String handleUnique(@RequestParam("id") Optional<Long> phoneId , Model model , @AuthenticationPrincipal User user){
        if(phoneId.isEmpty())
            return "redirect:/brand";
        //model.addAttribute("loginDto", new LoginDto());

        Optional<Phone> phone = phoneService.getPhone(phoneId.get());

        if(phone.isEmpty())
            return "redirect:/brand";

        model.addAttribute("phone", phone.get());
        model.addAttribute("user" , user);

        return "unique";
    }

    @PostMapping("/unique")
    public String handlePostUnique(@RequestParam("id") Optional<Long> phoneId ){
        if(phoneId.isEmpty())
            return "redirect:/brand";
        phoneService.uniquePhone(phoneId.get());

        return "redirect:/brand";
    }
}
