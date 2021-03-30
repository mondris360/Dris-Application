package com.mondris.demo.Controller;

import com.mondris.demo.Dto.UpdateDepartmentReqDto;
import com.mondris.demo.Dto.DepartmentReqDto;
import com.mondris.demo.Service.DepartmentService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/department")
    ResponseEntity<ApiResponse> updateDepartmentDetails(@Valid @RequestBody UpdateDepartmentReqDto request){

        return  departmentService.updateDepartmentDetails(request);
    }


    @DeleteMapping("/department/{departmentId}")
    ResponseEntity<ApiResponse> DeleteDepartmentById(@PathVariable long departmentId){

        return  departmentService.deleteDepartmentById(departmentId);
    }


}
