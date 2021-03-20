package com.mondris.demo.Dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    @NotBlank( message = "Office position is compulsory")
    private String officePosition;

    @NotBlank(message = "mobile Number is mandatory")
    private String mobileNumber;

    private String officeNumber;

    @NotBlank(message = "address is compulsory is mandatory")
    private String streetAddress;

    @NotBlank(message = "city is mandatory")
    private String city;

    @NotBlank(message = "state is mandatory")
    private String state;

    @NotBlank(message = "country is mandatory")
    private String country;

    @NotBlank(message = "zipcode is mandatory")
    private String zipCode;

    private String addressType = "home";

}
