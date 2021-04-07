package com.mondris.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OfficePositionReqDto {
    @NotBlank( message = "name is mandatory")
    private String name;

    @NotBlank(message = "createdByUserEmail field  is mandatory")
    @Email
    private String createdByUserEmail;

    @Size(max =  2000, message = "note must  be lesser than or equal to 2000 characters")
    private String note;
}
