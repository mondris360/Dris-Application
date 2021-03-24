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
public class ChangeDepartmentHeadReqDto {

    @NotBlank(message = "newDepartmentHeadEmail is mandatory")
    @Email
    private String newDepartmentHeadEmail;

    @NotBlank(message = "departmentId is mandatory")
    private long departmentId;

    @Size(max=2000, message = "note must be lesser than or equal to 2000 characters")
    private String note;

}
