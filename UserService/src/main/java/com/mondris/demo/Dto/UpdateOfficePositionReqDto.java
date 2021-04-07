package com.mondris.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateOfficePositionReqDto {
    @NotNull(message = "id is mandatory")
    @Positive(message = "id must be an integer number greater than zero")
    private Long id;

    @NotBlank( message = "name is mandatory")
    private String name;

    @NotBlank(message = "createdByUserEmail field  is mandatory")
    @Email
    private String updatedByUserEmail;

    @NotBlank(message = "note is mandatory")
    @Size(max =  2000, message = "newNote must  be lesser than or equal to 2000 characters")
    private  String note;
}
