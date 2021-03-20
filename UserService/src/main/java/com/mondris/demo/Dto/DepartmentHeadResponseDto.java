package com.mondris.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentHeadResponseDto {

    private  long id;

    private  String userEmailAddress;

    private  String firstName;

    private String lastName;

    private String createdAt;

    private String note;

    private boolean status;
}
