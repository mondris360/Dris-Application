package com.mondris.demo.Controller;

import com.mondris.demo.Dto.DepartmentHeadReqDto;
import com.mondris.demo.Model.DepartmentHead;
import com.mondris.demo.Service.DepartmentHeadService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DepartmentHeadController {

    @Resource
    DepartmentHeadService departmentHeadService;

    @PostMapping("/createDepartmentHead")
    ResponseEntity<ApiResponse> createDepartmentHead(@RequestBody DepartmentHeadReqDto request){
        return departmentHeadService.createDepartmentHead(request);
    }
}
