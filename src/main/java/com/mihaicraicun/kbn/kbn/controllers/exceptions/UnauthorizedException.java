package com.mihaicraicun.kbn.kbn.controllers.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "You are not allowed here!")
public class UnauthorizedException extends RuntimeException {
    // empty
}
