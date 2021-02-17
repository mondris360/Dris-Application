package com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception.CustomErrorClass;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MalformedJwtException extends RuntimeException {
    private String message;
    private HttpStatus statusCode;
    private  String path;

    public MalformedJwtException(String message, String path){
        this.message = message;
        this.path = path;
    }

    public MalformedJwtException(String message){
        this.message = message;
    }
}
