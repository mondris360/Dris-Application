package com.mondris.demo.Service;

import com.mondris.demo.Dto.ChangeDepartmentHeadReqDto;
import com.mondris.demo.Dto.DepartmentHeadReqDto;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentHeadService {

     ResponseEntity<ApiResponse> createDepartmentHead(DepartmentHeadReqDto request);

     ResponseEntity<ApiResponse> getAllDepartmentHeads();

     ResponseEntity<ApiResponse> getByDepartmentHeadId(long departmentHeadId);

     ResponseEntity<ApiResponse> getByDepartmentId(long departmentId);

     ResponseEntity<ApiResponse> changeDepartmentHead(ChangeDepartmentHeadReqDto request);

     ResponseEntity<ApiResponse> deleteDepartHeadById(long departmentHeadId);
}
