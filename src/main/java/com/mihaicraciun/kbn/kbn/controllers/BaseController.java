package com.mihaicraciun.kbn.kbn.controllers;

import com.mihaicraciun.kbn.kbn.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseController {

    @Autowired
    UserService userService;
    
}
