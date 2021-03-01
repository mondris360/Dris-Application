package com.etranzact.dris.authservice.dris.authservice.Util.Api.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse implements Serializable {
    private String status;
    private HttpStatus httpStatus;
    private  String message;
    private  String jwtToken;
    private Object data;


    public ApiResponse(String status, HttpStatus httpStatus, String message) {
        this.status = status;
        this.httpStatus = httpStatus;
        this.message = message;

    }

    public ApiResponse(String status, HttpStatus httpStatus, Object data) {
        this.status = status;
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String status, HttpStatus httpStatus, String message, String jwtToken) {
        this.status = status;
        this.httpStatus = httpStatus;
        this.message = message;
        this.jwtToken = jwtToken;
    }

}
