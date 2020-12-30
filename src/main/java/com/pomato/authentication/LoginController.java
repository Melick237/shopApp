package com.pomato.authentication;


import com.pomato.authentication.forms.LoginDto;
import com.pomato.security.StaticUtils;
import com.pomato.user.entities.User;
import com.pomato.user.services.UserManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserManagement userManagement;

    @GetMapping("/login")
    public String handleLoginPage(Model model , @AuthenticationPrincipal User user){

        model.addAttribute("loginDto" , new LoginDto());

        if(user != null)
            return "redirect:/";

        return "login";
    }

    @PostMapping("/login")
    public String authenticate(@ModelAttribute("loginDto") LoginDto loginDto ,  HttpServletRequest httpServletRequest ){

        try {
            httpServletRequest.login(loginDto.email.toLowerCase().trim(), loginDto.password);
        } catch (ServletException e){
            logger.error("Exception on logout after attempted login with bad credentials.");
            try {
                // invalidate session to clean up context in tomcat
                StaticUtils.logoutAndInvalidateSession();
            } catch (ServletException e2) {
                logger.error("Exception on logout after attempted login with bad credentials.", e2);
            }

            return "redirect:/login";
        }
        /* Here login was successful */
        final Optional<User> user = userManagement.findUserByEmail(loginDto.email);
        if (!user.isPresent()) {
            throw new RuntimeException("Login succeeded but user could not be found in repository.");
        }
        userManagement.rehashPassword(loginDto.password, user.get());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String handleLogout(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            try {
                StaticUtils.logoutAndInvalidateSession();
            } catch (ServletException e) {
                logger.error("Error at log out of '" + authentication.getName() + "'.", e);
            }
        }
        return "redirect:/";
    }

}
