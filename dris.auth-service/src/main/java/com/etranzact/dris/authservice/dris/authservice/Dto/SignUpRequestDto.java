package com.etranzact.dris.authservice.dris.authservice.Dto;

import com.etranzact.dris.authservice.dris.authservice.Dto.BaseDto.BaseDto;
import com.etranzact.dris.authservice.dris.authservice.Model.Authority;
import lombok.*;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignUpRequestDto extends BaseDto{

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

    @NotBlank(message = "streetAddress is mandatory")
    private String streetAddress;

    @NotBlank(message = "city is mandatory")
    private String city;

    @NotBlank(message = "state is mandatory")
    private String state;

    @NotBlank(message = "country is mandatory")
    private String country;

    @NotBlank(message = "zipCode is mandatory")
    private String zipCode;

    private Set< @Valid Authority> authorities;

}
