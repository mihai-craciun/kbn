package com.mihaicraciun.kbn.kbn.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The project you are looking for does not exist!")
public class TaskNotFoundException extends RuntimeException {
    // empty
}