package com.mondris.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentHeadReqDto {

    @NotBlank(message = "departmentHeadUserEmail is mandatory")
    private String departmentHeadUserEmail;

//    @NotBlank(message = "departmentId is mandatory")
    private long departmentId;


    @Size(max=2000, message = "note must be lesser than or equal to 2000 characters")
    private String note;
}
