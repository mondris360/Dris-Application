package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.SubDepartmentReqDto;
import com.mondris.demo.Model.Department;
import com.mondris.demo.Model.Employee;
import com.mondris.demo.Model.SubDepartment;
import com.mondris.demo.Repository.DepartmentRepository;
import com.mondris.demo.Repository.SubDepartmentRepository;
import com.mondris.demo.Repository.UserRepository;
import com.mondris.demo.Service.SubDepartmentService;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.NotFoundException;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SubDepartmentServiceImpl implements SubDepartmentService {

    @Resource
    private DepartmentRepository departmentRepository;

    @Resource
    private SubDepartmentRepository subDepartmentRepository;

    @Resource
    private UserRepository userRepository;

    private String currentPath = "/subDepartment";


    @Override
    public ResponseEntity<ApiResponse> createSubDepartment(SubDepartmentReqDto request) {

        final Employee createdByUser = userRepository.getByEmail(request.getCreatedByUserEmail());

        if(createdByUser ==  null){

            throw new NotFoundException("Invalid user email address", currentPath);
        }

        final Department department = departmentRepository.findDepartmentById(request.getDepartmentId());

        if (department == null){

            throw new NotFoundException("Invalid Department Id", currentPath);
        }

        String subDepartmentName = request.getName().trim().toLowerCase();

        final SubDepartment subDepartment = subDepartmentRepository.getByName(subDepartmentName);

        if (subDepartment != null){

            throw new NotFoundException("A subDepartment with that name already exists", currentPath);
        }

        SubDepartment newSubDepartment =  new SubDepartment();
        newSubDepartment.setName(subDepartmentName);
        newSubDepartment.setNote(request.getNote());
        newSubDepartment.setDepartment(department);
        // the user that is creating the subDepartment
        newSubDepartment.setEmployee(createdByUser);

        final SubDepartment createdSubDepartment = subDepartmentRepository.save(newSubDepartment);

        ApiResponse apiResponse =  new ApiResponse("Successful", HttpStatus.OK, "SubDepartment Was " +
                "Successfully Created", createdSubDepartment);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
