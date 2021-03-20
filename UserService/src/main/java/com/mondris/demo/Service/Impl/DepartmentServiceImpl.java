package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.DepartmentReqDto;
import com.mondris.demo.Service.DepartmentService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public ResponseEntity<ApiResponse> createDepartment(DepartmentReqDto request) {
        return null;
    }
}
