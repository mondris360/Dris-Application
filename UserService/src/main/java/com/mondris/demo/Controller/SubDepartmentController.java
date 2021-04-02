package com.mondris.demo.Controller;

import com.mondris.demo.Dto.SubDepartmentReqDto;
import com.mondris.demo.Dto.UpdateSubDepartmentReqDto;
import com.mondris.demo.Service.SubDepartmentService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class SubDepartmentController {

    @Resource
    private SubDepartmentService subDepartmentService;

    @PostMapping("/subDepartment")
    ResponseEntity<ApiResponse> createSubDepartment(@Valid  @RequestBody  SubDepartmentReqDto request){

        final ApiResponse subDepartment = subDepartmentService.createSubDepartment(request);

        return new ResponseEntity<>(subDepartment, subDepartment.getHttpStatus());
    }


    @GetMapping("/subDepartment/{id}")
    ResponseEntity<ApiResponse> getSubDepartmentById(@PathVariable long id){

        final ApiResponse subDepartment = subDepartmentService.getSubDepartmentById(id);

        return new ResponseEntity<>(subDepartment, subDepartment.getHttpStatus());
    }


    @GetMapping("/subDepartment")
    ResponseEntity<ApiResponse> getAllSubDepartments(){

        final ApiResponse allSubDepartments = subDepartmentService.getAllSubDepartments();

        return new ResponseEntity<>(allSubDepartments, HttpStatus.OK);

    }


    @PutMapping("/subDepartment")
    ResponseEntity<ApiResponse> updateSubDepartment(@Valid @RequestBody UpdateSubDepartmentReqDto request){

        final ApiResponse apiResponse = subDepartmentService.updatedSubDepartment(request);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @DeleteMapping("/subDepartment/{id}")
    ResponseEntity<ApiResponse> deleteSubDepartmentById(@PathVariable  long id){

        final ApiResponse apiResponse = subDepartmentService.deleteSubDepartmentById(id);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


}
