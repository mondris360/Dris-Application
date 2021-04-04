package com.mondris.demo.Service;

import com.mondris.demo.Dto.TeamLeadReqDto;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface TeamLeadService {
    ApiResponse createTeamLead(TeamLeadReqDto request);
    ApiResponse getTeamLeadById(long id);
    ApiResponse getAllTeamLeads();
}
