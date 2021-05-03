package com.mihaicraicun.kbn.kbn.controllers;

import com.mihaicraicun.kbn.kbn.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseController {

    @Autowired
    UserService userService;
    
}
