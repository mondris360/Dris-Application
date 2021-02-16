package com.mondris.demo.Util.Api.Exception.CustomErrorClass;

import lombok.*;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UnAuthorizedException extends RuntimeException{
    private String message;
    private HttpStatus statusCode;
    private  String path;

    public UnAuthorizedException(String message, String path) {
        this.message = message;
        this.path = path;
    }
}
