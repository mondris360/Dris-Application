package com.mondris.demo.Service;

import com.mondris.demo.Dto.DepartmentHeadReqDto;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentHeadService {
     ResponseEntity<ApiResponse> createDepartmentHead(DepartmentHeadReqDto request);
}
