package com.mondris.demo.Service;

import com.mondris.demo.Dto.SubDepartmentReqDto;
import com.mondris.demo.Dto.UpdateSubDepartmentReqDto;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.stereotype.Service;


@Service
public interface SubDepartmentService {

    ApiResponse createSubDepartment(SubDepartmentReqDto request);
    ApiResponse updatedSubDepartment(UpdateSubDepartmentReqDto request);
    ApiResponse getSubDepartmentById(long id);
    ApiResponse deleteSubDepartmentById(long id);
    ApiResponse getAllSubDepartments();
    ApiResponse getAllSubDepartmentsByDeptId(long id);
    ApiResponse getAllSubDeptsCreatedByAUser(String userEmail);

}
