package com.pomato.user.controllers;

import com.pomato.user.services.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    public UserManagement userManagement;

    @GetMapping("/users")
    public String handleUsersPages(Model model , Authentication authentication){

        if(authentication == null || !authentication.isAuthenticated()){
            return "redirect:/";
        }

        model.addAttribute("users",userManagement.getUsers());
        return "admin/users";
    };
}
