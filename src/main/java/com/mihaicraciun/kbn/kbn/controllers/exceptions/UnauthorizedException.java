package com.mihaicraciun.kbn.kbn.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "You are not allowed here!")
public class UnauthorizedException extends RuntimeException {
    // empty
}
