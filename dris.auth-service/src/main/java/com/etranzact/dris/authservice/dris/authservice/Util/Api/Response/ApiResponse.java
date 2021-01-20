package com.etranzact.dris.authservice.dris.authservice.Util.Api.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String status;
    private HttpStatus httpStatus;
    private  String message;
    private  String jwtToken;


    public ApiResponse(String status, HttpStatus httpStatus, String message) {
        this.status = status;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
