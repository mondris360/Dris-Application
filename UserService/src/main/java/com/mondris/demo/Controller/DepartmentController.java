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

@RestController
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;


    @PostMapping("/createDepartment")
    ResponseEntity<ApiResponse> createDepartment(@RequestBody DepartmentReqDto request){

        return departmentService.createDepartment(request);
    }



    @GetMapping("/getDepartments")
    ResponseEntity<ApiResponse> getDepartments(){

        return departmentService.getDepartments();
    }


}
