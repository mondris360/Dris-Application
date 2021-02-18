package com.mondris.demo.Util.Api.Exception.CustomErrorClass;

import lombok.*;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IllegalArgumentException extends RuntimeException {
    private String message;
    private HttpStatus statusCode;
    private  String path;

    public IllegalArgumentException(String message, String path){
        this.message = message;
        this.path = path;
    }
}
