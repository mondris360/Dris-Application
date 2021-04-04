package com.mondris.demo.Controller;

import com.mondris.demo.Dto.TeamLeadReqDto;
import com.mondris.demo.Service.TeamLeadService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class TeamLeadController {

    @Resource
    private TeamLeadService teamLeadService;

    @PostMapping("/teamLead")
    public ResponseEntity<ApiResponse> createTeamLead(@Valid @RequestBody TeamLeadReqDto request){

        System.out.println("==============================" +  request);
        final ApiResponse apiResponse = teamLeadService.createTeamLead(request);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
