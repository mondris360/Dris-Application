package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.DepartmentReqDto;
import com.mondris.demo.Model.Department;
import com.mondris.demo.Model.DepartmentHead;
import com.mondris.demo.Repository.DepartmentHeadRespository;
import com.mondris.demo.Repository.DepartmentRepository;
import com.mondris.demo.Service.DepartmentService;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.NotFoundException;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentRepository departmentRepository;

    @Resource
    private DepartmentHeadRespository departmentHeadRespository;

    private  String currentPath =  "/createDepartment";

    @Override
    public ResponseEntity<ApiResponse> createDepartment(DepartmentReqDto request) {

        ApiResponse apiResponse;

        final String departmentName = request.getName().toLowerCase().trim();
        final Department department = departmentRepository.findDepartmentByName(departmentName);

        if (department != null){
            throw new IllegalArgumentException("Department name already exists");
        }

//        final DepartmentHead departmentHead = departmentHeadRespository.getDepartmentHeadById(request.getDepartmentHeadId());
//
//        if (departmentHead == null){
//
//            throw  new NotFoundException("Invalid Department Head Id", currentPath);
//        }


        Department newDepartment =  new Department();
        newDepartment.setName(departmentName);
        newDepartment.setNote(request.getNote().trim());

        final Department createdDepartment = departmentRepository.save(newDepartment);

        apiResponse =  new ApiResponse("Successful", HttpStatus.CREATED, "Department Was Created Successfully",createdDepartment);

        return  new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


    @Override
    public ResponseEntity<ApiResponse> getAllDepartments() {
        ApiResponse apiResponse;

        final List<Department> allDepartments= departmentRepository.findAll();

        apiResponse = new ApiResponse("Successful", HttpStatus.OK," List of All The Departments", allDepartments);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
