package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.TeamLeadReqDto;
import com.mondris.demo.Dto.UpdateTeamLeadReqDto;
import com.mondris.demo.Model.Employee;
import com.mondris.demo.Model.SubDepartment;
import com.mondris.demo.Model.TeamLead;
import com.mondris.demo.Repository.SubDepartmentRepository;
import com.mondris.demo.Repository.TeamLeadRepository;
import com.mondris.demo.Repository.EmployeeRepository;
import com.mondris.demo.Service.TeamLeadService;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.IllegalArgumentException;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.NotFoundException;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.UserNotFoundException;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import com.mondris.demo.Util.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


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

    @Resource
    private ModelMapper modelMapper;

    private final String currentPath = "/teamLead";

    @Override
    public ApiResponse createTeamLead(TeamLeadReqDto request) {

        final SubDepartment subDepartment = subDepartmentRepository.findById(request.getSubDepartmentId()).orElseThrow(
                () -> new NotFoundException("Invalid sub department id", currentPath));

        final TeamLead teamLeadBySubDepartment = teamLeadRepository.getBySubDepartment(subDepartment);

        if ( teamLeadBySubDepartment != null){

            throw new IllegalArgumentException("Sorry, this sub Department already have a team lead", currentPath);
        }

        final Employee employee = employeeRepository.getByEmail(request.getEmail()).orElseThrow(
                () -> new UserNotFoundException("Invalid Team Lead Email Address",  currentPath));

        final TeamLead teamLead = teamLeadRepository.getByEmployee(employee);

        if (teamLead != null){

            throw new IllegalArgumentException("Sorry, this team  lead is already assigned to a different sub department", currentPath);
        }

        final Employee createdByUser = employeeRepository.getByEmail(request.getEmail()).orElseThrow(
                () -> new UserNotFoundException("Invalid Created By Email Address",  currentPath));

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

        final TeamLead teamLead = teamLeadRepository.getById(id).orElseThrow(
                () -> new NotFoundException("Invalid id", currentPath + "/{id}"));

        return new ApiResponse("Successful", HttpStatus.OK, "Team Lead Details", teamLead);

    }


    @Override
    public ApiResponse getAllTeamLeads() {

        final List<TeamLead> allTeamLeads = teamLeadRepository.findAll();

        return new ApiResponse("Successful", HttpStatus.OK, "List of all Team Leads", allTeamLeads);
    }

    @Override
    public ApiResponse deleteTeamLeadById(long id) {

        helper.isPositiveNumber(id, currentPath);

        final TeamLead teamLead = teamLeadRepository.getById(id).orElseThrow(
                () -> new NotFoundException("Invalid Team Lead id", currentPath));

        teamLeadRepository.delete(teamLead);

        return  new ApiResponse("Successful", HttpStatus.OK,"Team Lead was deleted successfully");

    }

    @Override
    public ApiResponse updateTeamLead(UpdateTeamLeadReqDto request) {

        final TeamLead teamLeadById = teamLeadRepository.getById(request.getTeamLeadId()).orElseThrow(
                () -> new NotFoundException("Invalid Team Lead Id", currentPath));

        final SubDepartment subDepartment = subDepartmentRepository.findById(request.getSubDepartmentId()).orElseThrow(
                () -> new NotFoundException("Invalid Sub Department Id", currentPath));

        final TeamLead teamLeadBySubDepartment = teamLeadRepository.getBySubDepartment(subDepartment);

        if (teamLeadBySubDepartment != null && (teamLeadById.getId() != teamLeadBySubDepartment.getId())){

            throw  new IllegalArgumentException("Sorry, this  sub department already have a team lead", currentPath);
        }

        Employee teamLeadUser = employeeRepository.getByEmail(request.getTeamLeadEmail()).orElseThrow(
                () -> new UserNotFoundException("Invalid Team Lead Email Address", currentPath));

        final TeamLead teamLeadByEmail = teamLeadRepository.getByEmployee(teamLeadUser);

        final Employee updatedByUser = employeeRepository.getByEmail(request.getUpdatedByUserEmail()).orElseThrow(
                () -> new UserNotFoundException("Invalid Created By User Email", currentPath));

        if(teamLeadByEmail != null  && teamLeadById.getId() != teamLeadByEmail.getId()){

            throw new IllegalArgumentException("Sorry, this user is already a team lead in a different sub department", currentPath);
        }

        teamLeadById.setEmployee(teamLeadUser);
        teamLeadById.setNote(request.getNote());
        teamLeadById.setUpdatedByUser(updatedByUser);
        teamLeadById.setSubDepartment(subDepartment);

        final TeamLead updatedTeamLeadDetails = teamLeadRepository.save(teamLeadById);

        return new ApiResponse("Successful", HttpStatus.OK, "Team Lead Info Was Updated", updatedTeamLeadDetails);


    }
}
