package com.mondris.demo.Controller;

import com.mondris.demo.Dto.SubDepartmentReqDto;
import com.mondris.demo.Dto.UpdateSubDepartmentReqDto;
import com.mondris.demo.Service.SubDepartmentService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class SubDepartmentController {

    @Resource
    private SubDepartmentService subDepartmentService;

    @PostMapping("/subDepartment")
    ResponseEntity<ApiResponse> createSubDepartment(@Valid  @RequestBody  SubDepartmentReqDto request){

        return  subDepartmentService.createSubDepartment(request);
    }

    @PutMapping("/subDepartment")
    ResponseEntity<ApiResponse> updateSubDepartment(@Valid @RequestBody UpdateSubDepartmentReqDto request){

        return subDepartmentService.updatedSubDepartment(request);
    }
}
