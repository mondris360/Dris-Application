package com.mondris.demo.Controller;

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

    @PostMapping("/createDepartmentHead")
    ResponseEntity<ApiResponse> createDepartmentHead(@RequestBody DepartmentHeadReqDto request){
        return departmentHeadService.createDepartmentHead(request);
    }

    @GetMapping("/getDepartmentHeads")
    ResponseEntity<ApiResponse> getDepartmentHeads(){
        return departmentHeadService.getAllDepartmentHeads();
    }

    @GetMapping("/getDepartmentHeadByDepartmentId/{departmentId}")
     ResponseEntity<ApiResponse> getDepartmentHeadByDepartmentId(@PathVariable long departmentId){
        return departmentHeadService.getByDepartmentId(departmentId);
    }

}
