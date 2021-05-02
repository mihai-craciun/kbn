package com.mihaicraicun.kbn.kbn.controllers;

import com.mihaicraicun.kbn.kbn.model.User;
import com.mihaicraicun.kbn.kbn.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {

    @Autowired
    UserService userService;
    
    User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken)) {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            return userService.findByEmail(userName);
        }
        return userService.guestUser();
    }
    
}
