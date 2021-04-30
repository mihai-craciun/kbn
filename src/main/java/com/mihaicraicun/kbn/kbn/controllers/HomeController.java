package com.mihaicraicun.kbn.kbn.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Value("${app.name}")
    public String appName;
    
    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("app_name", appName);
        return "home";
    }
}
