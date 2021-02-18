package com.etranzact.dris.authservice.dris.authservice.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailVerificationLinkReqDto {

    @Email(message = "Please Enter A Valid Email Address")
    @NotBlank(message = "email address is mandatory")
    private String email;
}
