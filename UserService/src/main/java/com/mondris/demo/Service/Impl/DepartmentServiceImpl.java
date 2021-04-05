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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.Positive;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentRepository departmentRepository;

    @Resource
    private DepartmentHeadRespository departmentHeadRespository;

    private final String currentPath =  "/department";

    @Override
    public ApiResponse createDepartment(DepartmentReqDto request) {

        final String departmentName = request.getName().toLowerCase().trim();

        final Department department = departmentRepository.findDepartmentByName(departmentName);

        if (department != null){

            throw new IllegalArgumentException("Department name already exists", currentPath);
        }

        Department newDepartment =  new Department();
        newDepartment.setName(departmentName);
        newDepartment.setNote(request.getNote().trim());

        final Department createdDepartment = departmentRepository.save(newDepartment);

        return new ApiResponse("Successful", HttpStatus.CREATED, "Department Was Created Successfully",createdDepartment);

    }


    @Override
    public ApiResponse getDepartmentId(@Positive(message = "Id must be a positive number") long id) {

        final Department department = departmentRepository.findDepartmentById(id).orElseThrow(
                () -> new NotFoundException("Invalid Department Id", currentPath + "/{departmentId}"));

        return new ApiResponse("Successful", HttpStatus.OK, "Department Details", department);
    }

    @Override
    public ApiResponse getAllDepartments() {

        final List<Department> allDepartments= departmentRepository.findAll();

       return new ApiResponse("Successful", HttpStatus.OK," List of All The Departments", allDepartments);
    }


    @Override
    public ApiResponse updateDepartmentDetails(UpdateDepartmentReqDto request) {

        final Department department = departmentRepository.findDepartmentById(request.getDepartmentId()).orElseThrow(
                ()-> new NotFoundException("Invalid Department Id", currentPath));


        department.setName(request.getName().trim().toLowerCase());
        department.setNote(request.getNote());

        final Department updatedDepartment = departmentRepository.save(department);

        return new ApiResponse("Successful", HttpStatus.OK, "Department Was Successfully Updated", updatedDepartment);

    }


    @Override
    public ApiResponse deleteDepartmentById(long departmentId) {

        boolean isValidId = departmentRepository.existsById(departmentId);

        if(!isValidId){

            throw new NotFoundException("Invalid Department Id", currentPath);
        }

        departmentRepository.deleteById(departmentId);

        return new ApiResponse("Successful", HttpStatus.OK, "Department Was Deleted Successfully");
    }
}
