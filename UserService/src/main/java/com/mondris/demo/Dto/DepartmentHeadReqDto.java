package com.mondris.demo.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DepartmentHeadReqDto {

    @NotBlank(message = "userId is mandatory")
    private long userId;

    @Size(max=200)
    private String note;
}
