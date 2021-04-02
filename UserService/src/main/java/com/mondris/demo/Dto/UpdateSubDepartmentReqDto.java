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
public class UpdateSubDepartmentReqDto {

    @NotNull(message = "subDepartmentId is mandatory")
    @Positive(message = "subDepartmentId must be a positive integer ")
    private Long subDepartmentId;

    @NotNull(message = "departmentId is mandatory")
    @Positive(message = "departmentId must be a positive integer ")
    private Long departmentId;

    @NotBlank(message = "name is mandatory")
    private  String name;

    @NotBlank(message = "email is mandatory")
    @Email
    private String updatedByUserEmail;

    @Size(max =  2000, message = "note must  be lesser than or equal to 2000 characters")
    private String note;
}
