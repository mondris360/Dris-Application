package com.etranzact.dris.authservice.dris.authservice.Dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {
    @Email(message="Please enter a valid email address")
    private  String email;
    @NotBlank( message =  "Please enter a valid password")
    @Size(min = 8, max=50, message = "Pass must be a min of 8 characters and a max of 50 characters")
    private  String password;
}
