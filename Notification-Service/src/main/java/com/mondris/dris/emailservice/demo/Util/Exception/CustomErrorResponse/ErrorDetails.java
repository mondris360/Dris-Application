package com.mondris.dris.emailservice.demo.Util.Exception.CustomErrorResponse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
public class ErrorDetails {

    private HttpStatus status;

    private String message;

    private List<String> errors;

    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());


    public ErrorDetails(HttpStatus status, String message, List<String> errors) {

        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ErrorDetails(HttpStatus status, String message, String error) {

        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}


