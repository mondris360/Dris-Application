package com.mondris.demo.Controller;

import com.mondris.demo.Model.DepartmentHead;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentHeadController {

    @PostMapping("/createDepartmentHead")
    ResponseEntity<ApiResponse> createDepartmentHead(@RequestBody DepartmentHead request){
        return null;
    }
}
