package com.mondris.dris.emailservice.demo.Util.Exception.CustomErrorClass;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class IllegalArgumentException extends RuntimeException {

    private String message;

    private HttpStatus statusCode;

    private String path;

    public IllegalArgumentException(String message, String path) {

        this.message = message;
        this.path = path;
    }

    public IllegalArgumentException(String message) {

        this.message = message;
    }
}

