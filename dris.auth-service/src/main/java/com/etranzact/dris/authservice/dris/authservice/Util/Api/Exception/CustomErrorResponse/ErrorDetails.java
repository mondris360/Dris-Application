package com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception.CustomErrorResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class ErrorDetails {

    private HttpStatus status;
    private String message;
    private List<String> errors;
    private Timestamp timestamp =  new Timestamp(System.currentTimeMillis());

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
