package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.TeamLeadReqDto;
import com.mondris.demo.Model.Employee;
import com.mondris.demo.Model.SubDepartment;
import com.mondris.demo.Model.TeamLead;
import com.mondris.demo.Repository.SubDepartmentRepository;
import com.mondris.demo.Repository.TeamLeadRepository;
import com.mondris.demo.Repository.UserRepository;
import com.mondris.demo.Service.TeamLeadService;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.IllegalArgumentException;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.NotFoundException;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import com.mondris.demo.Util.Helper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class TeamLeadServiceImpl implements TeamLeadService {

    @Resource
    private TeamLeadRepository teamLeadRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private SubDepartmentRepository subDepartmentRepository;

    @Resource
    private Helper helper;

    private  String currentPath = "/teamLead";

    @Override
    public ApiResponse createTeamLead(TeamLeadReqDto request) {

        final SubDepartment subDepartment = subDepartmentRepository.getById(request.getSubDepartmentId());

        if (subDepartment == null){

            throw  new NotFoundException("Invalid Sub Department Id", currentPath);
        }

        final TeamLead teamLeadBySubDepartment = teamLeadRepository.getBySubDepartment(subDepartment);

        if ( teamLeadBySubDepartment != null){

            throw new IllegalArgumentException("Sorry, this sub Department already has a team lead", currentPath);
        }

        final Employee employee = helper.getEmployeeByEmail(request.getEmail(),"Invalid User Email Address",  currentPath);

        final TeamLead teamLead = teamLeadRepository.getByEmployee(employee);

        if (teamLead != null){

            throw new IllegalArgumentException("A TeamLead with this email address already exist", currentPath);
        }

        final Employee createdByUser = helper.getEmployeeByEmail(request.getCreatedByUserEmail(),
                "Invalid Created By Email Address",currentPath);

        TeamLead newTeamLead =  new TeamLead();
        newTeamLead.setEmployee(employee);
        newTeamLead.setSubDepartment(subDepartment);
        newTeamLead.setNote(request.getNote());
        newTeamLead.setCreatedByUser(createdByUser);

        final TeamLead createdTeamLead = teamLeadRepository.save(newTeamLead);

        return  new ApiResponse("Successful", HttpStatus.OK, "Team Lead Was Successfully Created", createdTeamLead);

    }



    private TeamLead getTeamLeadById(long id){

        final TeamLead teamLead = teamLeadRepository.getById(id);

        if (teamLead == null){

            throw new NotFoundException("Invalid id address", currentPath);
        }

        return teamLead;

    }

    private Employee getUserByEmail(String email, String error, String currentPath){

        final Employee user = userRepository.getByEmail(email);

        if (user == null){

            throw  new NotFoundException(error, currentPath );
        }

        return user;
    }
}