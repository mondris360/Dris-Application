package com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception.CustomErrorClass;

import lombok.*;
import org.springframework.http.HttpStatus;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAlreadyExistsException extends   RuntimeException {
    private String message;
    private HttpStatus statusCode;
    private  String path;

    public UserAlreadyExistsException(String message, String path){
        this.message = message;
        this.path = path;
    }
}


