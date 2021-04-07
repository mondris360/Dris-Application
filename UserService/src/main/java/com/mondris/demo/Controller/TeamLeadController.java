package com.mondris.demo.Controller;

import com.mondris.demo.Dto.TeamLeadReqDto;
import com.mondris.demo.Dto.UpdateTeamLeadReqDto;
import com.mondris.demo.Service.TeamLeadService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.PostUpdate;
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

    @GetMapping("/teamLead/{id}")
    public ResponseEntity<ApiResponse> getTeamLeadById(@PathVariable long id){

        final ApiResponse apiResponse = teamLeadService.getTeamLeadById(id);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @GetMapping("/teamLead")
    public ResponseEntity<ApiResponse> getTeamLeads(){

        final ApiResponse apiResponse = teamLeadService.getAllTeamLeads();

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());

    }

    @PutMapping("/teamLead")
    public ResponseEntity<ApiResponse> updateTeamLead(@Valid @RequestBody UpdateTeamLeadReqDto request){

        final ApiResponse apiResponse = teamLeadService.updateTeamLead(request);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @DeleteMapping("/teamLead/{id}")
    public ResponseEntity<ApiResponse> deleteTeamLeadById(@PathVariable long id){

        final ApiResponse apiResponse = teamLeadService.deleteTeamLeadById(id);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());

    }

}
