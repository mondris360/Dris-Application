package com.mondris.demo.Dto;

import com.mondris.demo.Model.City;
import com.mondris.demo.Model.Country;
import com.mondris.demo.Model.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String officePosition;

    private String mobileNumber;

    private String officeNumber;

    private String streetAddress;

    private City city;

    private State state;

    private Country country;

    private String zipCode;

    private String addressType = "home";
}
