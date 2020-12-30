package com.pomato.phone.controller;

import com.pomato.phone.entities.Phone;
import com.pomato.phone.forms.PhoneEditForm;
import com.pomato.phone.service.PhoneService;
import com.pomato.user.entities.User;
import com.pomato.user.entities.UserRole;
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

        if(user == null){
            model.addAttribute("phones", phoneService.getPhones());
            model.addAttribute("user" , user);
            return "brand";
        }

        if(user.hasAuthorities(UserRole.MODERATOR)){
            model.addAttribute("phones", phoneService.getPhonesAdmin());
            model.addAttribute("user" , user);
            return "brand";
        }

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
        model.addAttribute("editForm", new PhoneEditForm());
        return "unique";
    }

    @PostMapping("/editPhone")
    public String handleEditPhone(@RequestParam("id") Optional<Long> phoneId , @ModelAttribute("editForm") PhoneEditForm phoneEditForm ){
        if(phoneId.isEmpty())
            return "redirect:/brand";

        Optional<Phone> phone = phoneService.getPhone(phoneId.get());

        if(phone.isEmpty())
            return "redirect:/brand";

        phoneService.editPhone(phoneEditForm, phone.get());

        return "redirect:/unique?id="+phoneId.get();
    }

    @GetMapping("/deletePhone")
    public String handleDelete(@RequestParam("id") Optional<Long> phoneId){

        if(phoneId.isEmpty())
            return "redirect:/brand";

        Optional<Phone> phone = phoneService.getPhone(phoneId.get());

        if(phone.isEmpty())
            return "redirect:/brand";

        phoneService.deletePhone(phone.get());
        return "redirect:/brand";
    }

    @GetMapping("/addPhone")
    public String handleAddPhonePage(Model model , @AuthenticationPrincipal User user){

        if(!user.hasAuthorities(UserRole.MODERATOR))
            return "redirect:/";

        model.addAttribute("phones", phoneService.getPhones());
        model.addAttribute("user" , user);
        model.addAttribute("editForm", new PhoneEditForm());
        return "admin/addPhone";
    }

    @PostMapping("/addPhone")
    public String handleAddPhone(@ModelAttribute("editForm") PhoneEditForm phoneEditForm){

        phoneService.addPhone(phoneEditForm);
        return "redirect:/brand";
    }

    @GetMapping("/buy")
    public String handleBuy(@RequestParam("id") Optional<Long> phoneId , Model model , @AuthenticationPrincipal User user ){

        if(phoneId.isEmpty())
            return "redirect:/brand";

        Optional<Phone> phone = phoneService.getPhone(phoneId.get());

        if (phone.isEmpty())
            return "redirect:/brand";

        phoneService.buyPhone(phone.get());

        model.addAttribute("phone", phone.get());
        model.addAttribute("user" , user);
        return "buy";
    }
}
