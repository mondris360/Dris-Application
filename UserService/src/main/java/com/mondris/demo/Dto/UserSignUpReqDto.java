package com.mondris.demo.Dto;

import com.mondris.demo.Model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpReqDto {
    @Column(name="first_name")
    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    @Column(name="last_name")
    @NotBlank(message = "lastName is mandatory")
    private String lastName;

    @NotBlank(message = "email is mandatory")
    @Email
    private String email;
}
