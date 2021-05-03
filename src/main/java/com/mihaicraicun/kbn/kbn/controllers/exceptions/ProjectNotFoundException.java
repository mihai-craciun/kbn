package com.mihaicraicun.kbn.kbn.controllers.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The project you are looking for does not exist!")
public class ProjectNotFoundException extends RuntimeException {
    // empty
}