package com.mondris.demo.Controller;

import com.mondris.demo.Dto.OfficePositionReqDto;
import com.mondris.demo.Dto.UpdateOfficePositionReqDto;
import com.mondris.demo.Service.OfficePositionService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/officePosition")
    ResponseEntity<ApiResponse> getALlOfficePositions(){

        final ApiResponse apiResponse = officePositionService.getAllOfficePositions();

        return  new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @GetMapping("/officePosition/{id}")
    ResponseEntity<ApiResponse> getOfficePositionById(@PathVariable long id){

        final ApiResponse apiResponse = officePositionService.getOfficePositionById(id);

        return  new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @PutMapping("/officePosition")
    ResponseEntity<ApiResponse> updateOfficePosition(@Valid @RequestBody UpdateOfficePositionReqDto request){

        final ApiResponse apiResponse = officePositionService.updateOfficePosition(request);

        return  new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


}
