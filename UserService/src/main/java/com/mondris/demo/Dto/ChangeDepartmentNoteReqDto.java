package com.mondris.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Setter
@Getter
public class ChangeDepartmentNoteReqDto {

    @NotBlank(message = "departmentId is mandatory")
    private long departmentId;

    @NotBlank(message = "note is mandatory")
    @Size(max =  2000, message = "newNote must  be lesser than or equal to 2000 characters")
    private  String note;
}
