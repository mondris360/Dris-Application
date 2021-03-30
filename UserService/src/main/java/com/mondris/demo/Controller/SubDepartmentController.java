package com.mondris.demo.Controller;

import com.mondris.demo.Dto.SubDepartmentReqDto;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubDepartmentController {

    @PostMapping("/subDepartment")
    ResponseEntity<ApiResponse> createSubDepartment(SubDepartmentReqDto request){

    }
}
