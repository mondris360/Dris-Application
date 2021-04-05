package com.mondris.demo.Service;

import com.mondris.demo.Dto.UpdateDepartmentReqDto;
import com.mondris.demo.Dto.DepartmentReqDto;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
    ApiResponse createDepartment(DepartmentReqDto request);
    ApiResponse getDepartmentId(long id);
    ApiResponse getAllDepartments();
    ApiResponse updateDepartmentDetails(UpdateDepartmentReqDto request);
    ApiResponse deleteDepartmentById(long departmentId);

}
