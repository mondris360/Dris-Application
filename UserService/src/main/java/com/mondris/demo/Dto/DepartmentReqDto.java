package com.mondris.demo.Dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentReqDto {

    @NotBlank(message = "name is  mandatory")
    private String name;

    @Size(max =  2000, message = "note must  be lesser than or equal to 2000 characters")
    private String note;
}
