package com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception.CustomErrorClass;

import lombok.*;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {
    private String message;
    private HttpStatus statusCode;
    private String path;

    public CustomException(String message, String path) {
        this.message = message;
        this.path = path;
    }
}