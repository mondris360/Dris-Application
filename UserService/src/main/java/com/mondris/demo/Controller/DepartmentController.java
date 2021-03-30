package com.mondris.demo.Controller;

import com.mondris.demo.Dto.DepartmentReqDto;
import com.mondris.demo.Service.DepartmentService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;


    @PostMapping("/department")
    ResponseEntity<ApiResponse> createDepartment( @Valid @RequestBody DepartmentReqDto request){

        return departmentService.createDepartment(request);
    }


    @GetMapping("/department")
    ResponseEntity<ApiResponse> getDepartments(){

        return departmentService.getAllDepartments();
    }


}
