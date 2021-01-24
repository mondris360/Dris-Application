package com.etranzact.dris.authservice.dris.authservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatusDto {
    @Email(message = "Please enter a valid Email")
    private  String email;
    @NotNull(message="isActive field  is mandatory")
    private Boolean isActivate;
}
