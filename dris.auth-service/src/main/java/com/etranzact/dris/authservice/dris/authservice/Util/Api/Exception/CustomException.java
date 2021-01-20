package com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {
    private String message;
    private HttpStatus statusCode;

}
