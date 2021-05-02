package com.mihaicraicun.kbn.kbn.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.mihaicraicun.kbn.kbn.misc.ErrorHandler;
import com.mihaicraicun.kbn.kbn.model.User;
import com.mihaicraicun.kbn.kbn.model.forms.LoginForm;
import com.mihaicraicun.kbn.kbn.model.forms.RegisterForm;
import com.mihaicraicun.kbn.kbn.services.SecurityService;
import com.mihaicraicun.kbn.kbn.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;
    
    @GetMapping("/login")
    public String getLoginPage(Model model, String error, String logout) {
        model.addAttribute("loginFormObject", new LoginForm());

        if (error != null) {
            model.addAttribute("loginError", true);
            model.addAttribute("error", "Your username and password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("logoutSuccess", true);
            model.addAttribute("message", "You have been logged out successfully.");
        }
        
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerFormObject", new RegisterForm());

        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerFormObject") RegisterForm registerFormObject,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        User user;
        String fullName = registerFormObject.getFullName();
        String email = registerFormObject.getEmail();
        String password = registerFormObject.getPassword();
        String passwordConfirm = registerFormObject.getPasswordConfirm();

        Map<String, List<String>> registrationErrors;

        if (bindingResult.hasErrors())
            registrationErrors = ErrorHandler.convertBindingResultErrorsToMap(bindingResult);
        else
            registrationErrors = new HashMap<>();

        if (!password.equals(passwordConfirm)) {
            if (!registrationErrors.containsKey("passwordConfirm"))
                registrationErrors.put("passwordConfirm", new ArrayList<String>());
            registrationErrors.get("passwordConfirm").add("PasswordConfirm must be the same to password!");
        }

        if (userService.findByEmail(email) != null) {
            if (!registrationErrors.containsKey("email"))
                registrationErrors.put("email", new ArrayList<String>());
            registrationErrors.get("email").add("An account already exists for this email!");
        }

        if (registrationErrors.size() > 0) {
            for (String key : registrationErrors.keySet())
                redirectAttributes.addFlashAttribute(key + "Error", registrationErrors.get(key));
            return "redirect:/register";
        }

        try {
            user = userService.create(fullName, email, password);
            userService.save(user);

            securityService.autoLogin(user.getEmail(), password);
        } catch (Exception e) {
            return "error";
        }

        return "redirect:/";
    }
}
