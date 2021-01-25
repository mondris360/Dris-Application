package com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception.CustomErrorResponse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Setter
@Getter
public class ApiExceptionMessage {
    private final String message;
    private  final HttpStatus httpStatus;
    private  final String path;
    private  final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public ApiExceptionMessage(String message, HttpStatus httpStatus, String path) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.path = path;
    }

}
