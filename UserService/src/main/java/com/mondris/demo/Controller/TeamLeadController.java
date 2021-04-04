package com.mondris.demo.Controller;

import com.mondris.demo.Dto.TeamLeadReqDto;
import com.mondris.demo.Service.TeamLeadService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class TeamLeadController {

    @Resource
    private TeamLeadService teamLeadService;

    @PostMapping("/teamLead")
    public ResponseEntity<ApiResponse> createTeamLead(@Valid @RequestBody TeamLeadReqDto request){

        final ApiResponse apiResponse = teamLeadService.createTeamLead(request);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @GetMapping("teamLead/{id}")
    public ResponseEntity<ApiResponse> getTeamLeadById(@PathVariable long id){

        final ApiResponse apiResponse = teamLeadService.getTeamLeadById(id);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
