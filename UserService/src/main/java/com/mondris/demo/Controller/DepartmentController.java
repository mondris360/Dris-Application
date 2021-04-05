package com.mondris.demo.Controller;

import com.mondris.demo.Dto.UpdateDepartmentReqDto;
import com.mondris.demo.Dto.DepartmentReqDto;
import com.mondris.demo.Service.DepartmentService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import feign.Response;
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

        final ApiResponse apiResponse = departmentService.createDepartment(request);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


    @GetMapping("/department")
    ResponseEntity<ApiResponse> getDepartments(){

        final ApiResponse apiResponse = departmentService.getAllDepartments();

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());

    }

    @GetMapping("/department/{id}")
    ResponseEntity<ApiResponse> getDepartmentById(@PathVariable long id){

        final ApiResponse apiResponse = departmentService.getDepartmentId(id);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @PutMapping("/department")
    ResponseEntity<ApiResponse> updateDepartmentDetails(@Valid @RequestBody UpdateDepartmentReqDto request){

        final ApiResponse apiResponse = departmentService.updateDepartmentDetails(request);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


    @DeleteMapping("/department/{departmentId}")
    ResponseEntity<ApiResponse> DeleteDepartmentById(@PathVariable long departmentId){

        final ApiResponse apiResponse = departmentService.deleteDepartmentById(departmentId);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


}
