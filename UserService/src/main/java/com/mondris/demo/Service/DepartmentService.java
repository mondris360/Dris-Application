package com.mondris.demo.Service;

import com.mondris.demo.Dto.ChangeDepartmentReqDto;
import com.mondris.demo.Dto.DepartmentReqDto;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
    ResponseEntity<ApiResponse> createDepartment(DepartmentReqDto request);
    ResponseEntity<ApiResponse> getAllDepartments();
    ResponseEntity<ApiResponse> updateDepartmentDetails(ChangeDepartmentReqDto request);
    ResponseEntity<ApiResponse> deleteDepartmentById(long departmentId);


}
