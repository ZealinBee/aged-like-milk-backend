package com.alm.exceptions;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = CustomRunTimeException.class)
    public ResponseEntity<?> handler(final CustomRunTimeException e, HttpRequest req) {
        return new ResponseEntity(e.getHttpStatus());
    }
}
