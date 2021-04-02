package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.SubDepartmentReqDto;
import com.mondris.demo.Dto.SubDeptCommonOperationsDto;
import com.mondris.demo.Dto.UpdateSubDepartmentReqDto;
import com.mondris.demo.Model.Department;
import com.mondris.demo.Model.Employee;
import com.mondris.demo.Model.SubDepartment;
import com.mondris.demo.Repository.DepartmentRepository;
import com.mondris.demo.Repository.SubDepartmentRepository;
import com.mondris.demo.Repository.UserRepository;
import com.mondris.demo.Service.SubDepartmentService;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.IllegalArgumentException;
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

           SubDeptCommonOperationsDto subDeptCommonOperationsDto =   new SubDeptCommonOperationsDto();
           subDeptCommonOperationsDto.setDepartmentId(request.getDepartmentId());
           subDeptCommonOperationsDto.setHttpStatus(HttpStatus.CREATED);
           subDeptCommonOperationsDto.setNote(request.getNote());
           subDeptCommonOperationsDto.setOperationType("create");
           subDeptCommonOperationsDto.setSuccessMsg("subDepartment Was Successfully Created");
           subDeptCommonOperationsDto.setSubDepartmentName(request.getName());
           subDeptCommonOperationsDto.setUserEmail(request.getCreatedByUserEmail());

           return commonOperations(subDeptCommonOperationsDto);
    }


    @Override
    public ResponseEntity<ApiResponse> updatedSubDepartment(UpdateSubDepartmentReqDto request) {

        return null;
    }



    private ResponseEntity<ApiResponse> commonOperations(SubDeptCommonOperationsDto request){

        final Employee employee = userRepository.getByEmail(request.getUserEmail());

        if(employee ==  null){

            throw new NotFoundException("Invalid user email address", currentPath);
        }

        final Department department = departmentRepository.findDepartmentById(request.getDepartmentId());

        if (department == null){

            throw new NotFoundException("Invalid Department Id", currentPath);
        }

        String subDepartmentName = request.getSubDepartmentName().trim().toLowerCase();

        final SubDepartment subDepartmentByName = subDepartmentRepository.getByName(subDepartmentName);

        String operationType =  request.getOperationType().toLowerCase().trim();

        final SubDepartment subDepartment;

        // create a new sub department
        if (operationType.equals("create")){

            if (subDepartmentByName != null){

                throw new NotFoundException("A subDepartment with that name already exists", currentPath);
            }

                SubDepartment newSubDepartment =  new SubDepartment();
                newSubDepartment.setName(subDepartmentName);
                newSubDepartment.setNote(request.getNote());
                newSubDepartment.setDepartment(department);
                // the user that is creating the subDepartment
                newSubDepartment.setCreated_by_user(employee);

                subDepartment = subDepartmentRepository.save(newSubDepartment);

        } else {  // update already existing subDepartment


            final SubDepartment subDepartmentById = subDepartmentRepository.getById(request.getSubDepartmentId());

            if(subDepartmentById.getId() != subDepartmentByName.getId()){

                throw new IllegalArgumentException("Your new subDepartment name already exists", currentPath);
            }

            subDepartmentById.setName(request.getSubDepartmentName());
            subDepartmentById.setDepartment(department);
            subDepartmentById.setNote(request.getNote());
            subDepartmentById.setUpdated_by_user(employee);

            subDepartment = subDepartmentRepository.save(subDepartmentById);

        }

        ApiResponse apiResponse =  new ApiResponse("Successful", request.getHttpStatus(), request.getSuccessMsg(), subDepartment);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
