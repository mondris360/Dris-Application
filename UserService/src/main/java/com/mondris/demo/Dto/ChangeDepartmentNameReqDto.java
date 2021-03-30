package com.mondris.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
public class ChangeDepartmentNameReqDto {

    @NotBlank(message = "departmentId is mandatory")
    private long departmentId;

    @NotBlank(message = "name is mandatory")
    private String name;
}
