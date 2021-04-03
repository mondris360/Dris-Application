package com.mondris.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SubDepartmentReqDto {

    @NotNull(message = "departmentId is mandatory")
    @Positive(message = "departmentId must be a positive integer ")
    private Long departmentId;

    @NotBlank(message = "name is mandatory")
    private  String name;

    @NotBlank(message = "createdByUserEmail is mandatory")
    @Email
    private String createdByUserEmail;

    @Size(max =  2000, message = "note must  be lesser than or equal to 2000 characters")
    private String note;
}
