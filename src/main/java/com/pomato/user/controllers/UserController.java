package com.pomato.user.controllers;

import com.pomato.register.forms.RegisterDto;
import com.pomato.user.entities.User;
import com.pomato.user.entities.UserRole;
import com.pomato.user.services.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    public UserManagement userManagement;

    @GetMapping("/users")
    public String handleUsersPages(Model model ,  @AuthenticationPrincipal User user ){

        if(user == null)
            return "redirect:/";

        if(!user.hasAuthorities(UserRole.MODERATOR))
            return "redirect:/";

        model.addAttribute("users",userManagement.getUsers());
        model.addAttribute("user" , user);
        return "admin/users";
    }

    @GetMapping("/user")
    public String handleUser(@RequestParam("id") Optional<Long> id , Model model , @AuthenticationPrincipal User authUser){
        if(authUser == null)
            return "redirect:/";

        if(id.isEmpty())
            return "redirect:/";

        Optional<User> user = userManagement.findUserById(id.get());
        if(user.isEmpty())
            return "redirect:/";

        if(!user.get().email.equals(authUser.email)){
            if(!authUser.hasAuthorities(UserRole.MODERATOR))
                return "redirect:/";
        }

        model.addAttribute("registerDto", new RegisterDto());
        model.addAttribute("user" , authUser);
        model.addAttribute("requestUser" , user);

        return "admin/user";
    }

    @PostMapping("/editUser")
    public String handleEditUser(@RequestParam("id") Optional<Long> id , @AuthenticationPrincipal User authUser ,
                                 @ModelAttribute("registerDto") RegisterDto registerDto , Model model){
        if(id.isEmpty())
            return "redirect:/";

        Optional<User> user = userManagement.findUserById(id.get());
        if(user.isEmpty())
            return "redirect:/";

        if(!registerDto.password.equals(registerDto.passwordConfirm)){
            model.addAttribute("error", "Passwordconfirm is different from password !");
            return "register";
        }

        userManagement.editUser(registerDto);


        return "redirect:/user?id="+id.get();
    }
}
