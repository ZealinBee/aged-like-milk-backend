package com.alm.exceptions;

import org.springframework.http.*;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomRunTimeException.class)
    public ResponseEntity<Object> handler(CustomRunTimeException e, HttpRequest req) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
