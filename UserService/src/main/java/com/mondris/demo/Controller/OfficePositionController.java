package com.mondris.demo.Controller;

import com.mondris.demo.Dto.OfficePositionReqDto;
import com.mondris.demo.Service.OfficePositionService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class OfficePositionController {

    @Resource
    private OfficePositionService officePositionService;


    @PostMapping("/officePosition")
    ResponseEntity<ApiResponse> createOfficePosition(@Valid @RequestBody OfficePositionReqDto request){

        final ApiResponse apiResponse = officePositionService.createOfficePosition(request);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
