package com.mondris.demo.Service;

import com.mondris.demo.Dto.SubDepartmentReqDto;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface SubDepartmentService {
    ResponseEntity<ApiResponse> createSubDepartment(SubDepartmentReqDto request);
}
