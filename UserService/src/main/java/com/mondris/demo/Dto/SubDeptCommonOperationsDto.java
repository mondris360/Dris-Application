package com.mondris.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubDeptCommonOperationsDto {
    private Long departmentId;
    private Long subDepartmentId;
    private String userEmail;
    private String subDepartmentName;
    private String note;
    private String successMsg;
    private String operationType;
    private HttpStatus httpStatus;
}
