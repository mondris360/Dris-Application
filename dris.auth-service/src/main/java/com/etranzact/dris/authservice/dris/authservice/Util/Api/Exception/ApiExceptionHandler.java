package com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
@ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        HttpStatus httpStatus =  HttpStatus.BAD_REQUEST;
        ApiExceptionMessage apiExceptionMessage =  new ApiExceptionMessage(e.getMessage(), httpStatus);
        return new ResponseEntity<>(apiExceptionMessage, httpStatus);
    }

    @ExceptionHandler(value ={CustomException.class})
    public ResponseEntity<Object> handleNotFoundCustomException(CustomException e) {
          HttpStatus httpStatus = HttpStatus.NOT_FOUND;
          ApiExceptionMessage  apiExceptionMessage = new ApiExceptionMessage(e.getMessage(), httpStatus);
          return new ResponseEntity<>(apiExceptionMessage, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//
//        return new ResponseEntity<Object>(errors);

        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, "Invalid User Input", errorList);
        return handleExceptionInternal(ex, errorDetails, headers, errorDetails.getStatus(), request);

//            ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed",
//                    ex.getBindingResult().toString());
//
//            return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}

