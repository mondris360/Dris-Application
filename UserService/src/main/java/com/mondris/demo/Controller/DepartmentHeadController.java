package com.mondris.demo.Controller;

import com.mondris.demo.Dto.ChangeDepartmentHeadReqDto;
import com.mondris.demo.Dto.DepartmentHeadReqDto;
import com.mondris.demo.Service.DepartmentHeadService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class DepartmentHeadController {

    @Resource
    DepartmentHeadService departmentHeadService;

    @PostMapping("/departmentHead")
    ResponseEntity<ApiResponse> createDepartmentHead(@RequestBody DepartmentHeadReqDto request){

        return departmentHeadService.createDepartmentHead(request);
    }

    @GetMapping("/departmentHead")
    ResponseEntity<ApiResponse> getCurrentDepartmentHeads(){

        return departmentHeadService.getAllDepartmentHeads();
    }

    @PutMapping("/departmentHead")
    ResponseEntity<ApiResponse> changeDepartmentHead(@RequestBody ChangeDepartmentHeadReqDto request){

        return departmentHeadService.changeDepartmentHead(request);
    }

    @GetMapping("/departmentHead/{departmentHeadId}")
    ResponseEntity<ApiResponse> getDepartmentHeadByDepartmentHeadId(@PathVariable long departmentHeadId){
        return departmentHeadService.getByDepartmentHeadId(departmentHeadId);
    }

    @DeleteMapping("/departmentHead/{id}")
    ResponseEntity<ApiResponse> deleteDepartmentHeadById(@PathVariable long id){

        return departmentHeadService.deleteDepartHeadById(id);
    }

}
