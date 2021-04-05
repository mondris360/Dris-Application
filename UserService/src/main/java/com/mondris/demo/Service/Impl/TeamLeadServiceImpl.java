package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.TeamLeadReqDto;
import com.mondris.demo.Model.Employee;
import com.mondris.demo.Model.SubDepartment;
import com.mondris.demo.Model.TeamLead;
import com.mondris.demo.Repository.SubDepartmentRepository;
import com.mondris.demo.Repository.TeamLeadRepository;
import com.mondris.demo.Repository.EmployeeRepository;
import com.mondris.demo.Service.TeamLeadService;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.IllegalArgumentException;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.NotFoundException;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import com.mondris.demo.Util.Helper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TeamLeadServiceImpl implements TeamLeadService {

    @Resource
    private TeamLeadRepository teamLeadRepository;

    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private SubDepartmentRepository subDepartmentRepository;

    @Resource
    private Helper helper;

    private  String currentPath = "/teamLead";

    @Override
    public ApiResponse createTeamLead(TeamLeadReqDto request) {

        final SubDepartment subDepartment = subDepartmentRepository.findById(request.getSubDepartmentId()).orElseThrow(
                () -> new NotFoundException("Invalid sub department id", currentPath));

        final TeamLead teamLeadBySubDepartment = teamLeadRepository.getBySubDepartment(subDepartment);

        if ( teamLeadBySubDepartment != null){

            throw new IllegalArgumentException("Sorry, this sub Department already has a team lead", currentPath);
        }

        final Employee employee = helper.getEmployeeByEmail(request.getEmail(),"Invalid User Email Address",  currentPath);

        final TeamLead teamLead = teamLeadRepository.getByEmployee(employee);

        if (teamLead != null){

            throw new IllegalArgumentException("Sorry, this team  lead is already assigned to a sub department", currentPath);
        }

        final Employee createdByUser = helper.getEmployeeByEmail(request.getCreatedByUserEmail(),
                "Invalid Created By Email Address",currentPath);

        TeamLead newTeamLead =  new TeamLead();
        newTeamLead.setEmployee(employee);
        newTeamLead.setSubDepartment(subDepartment);
        newTeamLead.setNote(request.getNote());
        newTeamLead.setCreatedByUser(createdByUser);

        final TeamLead createdTeamLead = teamLeadRepository.save(newTeamLead);

        return  new ApiResponse("Successful", HttpStatus.CREATED, "Team Lead Was Successfully Created", createdTeamLead);

    }



    @Override
    public ApiResponse getTeamLeadById(long id) {

        String currentPath2 =  currentPath + "/{id}";

        final TeamLead teamLead = teamLeadRepository.getById(id).orElseThrow(() -> new NotFoundException("Invalid id", currentPath2));

        return new ApiResponse("Successful", HttpStatus.OK, "Team Lead Details", teamLead);

    }


    @Override
    public ApiResponse getAllTeamLeads() {

        final List<TeamLead> allTeamLeads = teamLeadRepository.findAll();

        return new ApiResponse("Successful", HttpStatus.OK, "List of all Team Leads", allTeamLeads);
    }


}
