package com.alm.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;


public class CustomRunTimeException extends RuntimeException {
    private String code;
    private HttpStatus httpStatus;
    private String message;

    public CustomRunTimeException(String code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
