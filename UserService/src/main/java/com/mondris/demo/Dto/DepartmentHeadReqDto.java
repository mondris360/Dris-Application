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

    @Size(max=200)
    private String note;
}
