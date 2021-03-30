package com.mondris.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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

    @Size(max =  2000, message = "note must  be lesser than or equal to 2000 characters")
    private String note;
}
