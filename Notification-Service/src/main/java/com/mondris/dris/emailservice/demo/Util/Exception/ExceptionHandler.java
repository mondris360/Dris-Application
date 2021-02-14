package com.mondris.dris.emailservice.demo.Util.Exception;

import com.mondris.dris.emailservice.demo.Util.Exception.CustomErrorClass.IllegalArgumentException;
import com.mondris.dris.emailservice.demo.Util.Exception.CustomErrorResponse.ApiExceptionMessage;
import com.mondris.dris.emailservice.demo.Util.Exception.CustomErrorResponse.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.List;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(e.getMessage(), httpStatus, e.getPath());

        return new ResponseEntity<>(apiExceptionMessage, httpStatus);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, "Invalid User Input", errorList);
        return handleExceptionInternal(ex, errorDetails, headers, errorDetails.getStatus(), request);
    }

}
