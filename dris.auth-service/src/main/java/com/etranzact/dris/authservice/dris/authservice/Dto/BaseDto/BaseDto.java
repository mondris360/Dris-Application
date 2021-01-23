package com.etranzact.dris.authservice.dris.authservice.Dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {
    @Email(message="Please enter a valid email address")
    private  String email;
    @NotBlank( message =  "password field cannot be blank")
    @Size(min = 8, max=50, message = "Pass must be a min of 8 characters and a max of 50 characters")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}", message = "Password must  contain" +
    "At least one digit, one  lower case, one upper case and any of the following characters @#$%^&+=")
    private  String password;
}
