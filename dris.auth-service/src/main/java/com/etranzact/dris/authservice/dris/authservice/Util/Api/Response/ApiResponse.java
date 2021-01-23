package com.etranzact.dris.authservice.dris.authservice.Util.Api.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
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

    public ApiResponse(String status, HttpStatus httpStatus, String message, List<Object> data, String jwtToken) {
        this.status = status;
        this.httpStatus =  httpStatus;
        this.message = message;
        this.data = data;
        this.jwtToken = jwtToken;
    }

}
