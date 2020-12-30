package com.pomato.register.controllers;

import com.pomato.authentication.forms.LoginDto;
import com.pomato.register.forms.RegisterDto;
import com.pomato.security.StaticUtils;
import com.pomato.user.entities.User;
import com.pomato.user.services.UserManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class RegisterController {

    @Autowired
    private UserManagement userManagement;

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @GetMapping("/register")
    public String handleRegisterPage(Model model ,  @AuthenticationPrincipal User user){
        model.addAttribute("registerDto" , new RegisterDto());

        model.addAttribute("user" , user);
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("registerDto") RegisterDto registerDto,  HttpServletRequest httpServletRequest, Model model){

        if(!registerDto.password.equals(registerDto.passwordConfirm)){
            model.addAttribute("error", "Passwordconfirm is different from password !");
            return "register";
        }
        if(userManagement.doesEmailAlreadyExists(registerDto.email)){
            model.addAttribute("error", "This email is already in our database");
            return "register";
        }

        userManagement.registerUser(registerDto);
        return autologin(httpServletRequest , registerDto);
    }

    private String autologin(HttpServletRequest httpServletRequest , RegisterDto registerDto ){
        try {
            httpServletRequest.login(registerDto.email, registerDto.password);
        } catch (ServletException e){
            try {
                // invalidate session to clean up context in tomcat
                StaticUtils.logoutAndInvalidateSession();
            } catch (ServletException e2) {
                logger.error("Exception on logout after attempted login with bad credentials.", e2);
            }
            return "redirect:/login";
        }
        /* Here login was successful */
        final Optional<User> user = userManagement.findUserByEmail(registerDto.email);
        if (!user.isPresent()) {
            throw new RuntimeException("Login succeeded but user could not be found in repository.");
        }
        userManagement.rehashPassword(registerDto.password, user.get());

        return "redirect:/";
    }
}
