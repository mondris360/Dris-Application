package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.UpdateDepartmentReqDto;
import com.mondris.demo.Dto.DepartmentReqDto;
import com.mondris.demo.Model.Department;
import com.mondris.demo.Repository.DepartmentHeadRespository;
import com.mondris.demo.Repository.DepartmentRepository;
import com.mondris.demo.Service.DepartmentService;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.IllegalArgumentException;
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

    private  String currentPath =  "/department";

    @Override
    public ResponseEntity<ApiResponse> createDepartment(DepartmentReqDto request) {

        ApiResponse apiResponse;

        final String departmentName = request.getName().toLowerCase().trim();
        final Department department = departmentRepository.findDepartmentByName(departmentName);

        if (department != null){
            throw new IllegalArgumentException("Department name already exists", currentPath);
        }



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


    @Override
    public ResponseEntity<ApiResponse> updateDepartmentDetails(UpdateDepartmentReqDto request) {

        ApiResponse apiResponse;

        String currentPath = "";

        final Department department = departmentRepository.findDepartmentById(request.getDepartmentId());

        if (department ==  null){

            throw new NotFoundException("Invalid Department Id", currentPath);
        }

        department.setName(request.getName().trim().toLowerCase());
        department.setNote(request.getNote());

        final Department updatedDepartment = departmentRepository.save(department);

        apiResponse =  new ApiResponse("Successful", HttpStatus.OK, "Department Was Successfully Updated", updatedDepartment);

        return  new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());

    }


    @Override
    public ResponseEntity<ApiResponse> deleteDepartmentById(long departmentId) {
        return null;
    }
}
