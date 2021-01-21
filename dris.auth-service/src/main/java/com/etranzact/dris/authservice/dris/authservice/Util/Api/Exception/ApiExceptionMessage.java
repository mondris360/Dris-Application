package com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public class ApiExceptionMessage {
    private final String message;
    private  final HttpStatus httpStatus;
    private  final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public ApiExceptionMessage(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
