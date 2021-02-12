package com.mondris.dris.emailservice.demo.Dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class SendMailResponse {

    private String status;

    private HttpStatus httpStatus;

    private String Message;

    private String error;

    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

}
