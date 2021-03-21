package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.DepartmentReqDto;
import com.mondris.demo.Model.Department;
import com.mondris.demo.Model.DepartmentHead;
import com.mondris.demo.Model.Employee;
import com.mondris.demo.Repository.DepartmentHeadRespository;
import com.mondris.demo.Repository.DepartmentRepository;
import com.mondris.demo.Service.DepartmentService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.servlet.headers.HttpPublicKeyPinningDsl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentRepository departmentRepository;

    @Resource
    private DepartmentHeadRespository departmentHeadRespository;

    @Override
    public ResponseEntity<ApiResponse> createDepartment(DepartmentReqDto request) {

        ApiResponse apiResponse;

        final String departmentName = request.getName().toLowerCase().trim();
        System.out.println("deptByName===================" +  departmentName);
        final Department department = departmentRepository.findDepartmentByName(departmentName);

        if (department != null){
            throw new IllegalArgumentException("Department name already exists");
        }

        final DepartmentHead departmentHead = departmentHeadRespository.getDepartmentHeadById(request.getDepartmentHeadUserId());

        if (departmentHead == null){

            throw  new  IllegalArgumentException("Invalid Department Head Id");
        }


        Department newDepartment =  new Department();
        newDepartment.setDepartmentHead(departmentHead);
        newDepartment.setName(departmentName);

        final Department createdDepartment = departmentRepository.save(newDepartment);

        apiResponse =  new ApiResponse("Successful", HttpStatus.CREATED, "Department Was Created Successfully",createdDepartment);

        return  new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
