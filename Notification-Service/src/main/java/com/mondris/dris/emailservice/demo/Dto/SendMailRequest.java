package com.mondris.dris.emailservice.demo.Dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
public class SendMailRequest {

    @Email(message = "Please enter a valid email address")
    @NotBlank(message = "toEmail is mandatory")
    private String toEmail;

    @Email
    @NotBlank(message = "fromEmail is mandatory")
    private String fromEmail = "admin@drisapp.com";

    @NotBlank(message = "ReceiverFirstName is Mandatory")
    private String receiverFirstName;

    @NotBlank(message = "Receiver name is mandatory")
    private String senderFullName;


    @NotBlank(message = "subject is mandatory")
    private String subject;

    @NotBlank(message = "messageBodyTemplateName is  mandatory")
    private String messageBodyTemplateName;

    private String link;

}
