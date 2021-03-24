package com.mondris.demo.Dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentReqDto {
    private String name;
    private long departmentHeadId;
}
