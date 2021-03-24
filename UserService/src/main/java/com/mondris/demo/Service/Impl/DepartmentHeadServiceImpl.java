package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.ChangeDepartmentHeadReqDto;
import com.mondris.demo.Dto.DepartmentHeadReqDto;
import com.mondris.demo.Dto.DepartmentHeadResponseDto;
import com.mondris.demo.Model.Department;
import com.mondris.demo.Model.DepartmentHead;
import com.mondris.demo.Model.Employee;
import com.mondris.demo.Repository.DepartmentHeadRespository;
import com.mondris.demo.Repository.DepartmentRepository;
import com.mondris.demo.Repository.UserRepository;
import com.mondris.demo.Service.DepartmentHeadService;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.IllegalArgumentException;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.NotFoundException;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.UserNotFoundException;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentHeadServiceImpl implements DepartmentHeadService {

    @Resource
    private DepartmentHeadRespository departmentHeadRespository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private DepartmentRepository departmentRepository;

    @Override
    public ResponseEntity<ApiResponse> createDepartmentHead(DepartmentHeadReqDto request) {

        String currentPath = "/createDepartmentHead";

        ApiResponse apiResponse;

        final DepartmentHead departmentHead = departmentHeadRespository.getDepartmentHeadByEmployee_Email(request.getDepartmentHeadUserEmail());

        if(departmentHead != null){
            throw  new IllegalArgumentException("A department head with that email already exist", currentPath);
        }

        Employee employee = userRepository.getByEmail(request.getDepartmentHeadUserEmail());

        if (employee == null){

            throw new UserNotFoundException("Invalid user email Address", currentPath);
        }

        Department department = departmentRepository.findDepartmentById(request.getDepartmentId());

        if ( department == null){
            throw  new NotFoundException("Invalid department Id", currentPath );
        }

        DepartmentHead newDepartmentHead =  new DepartmentHead();
        newDepartmentHead.setEnabled(true);
        newDepartmentHead.setEmployee(employee);
        newDepartmentHead.setDepartment(department);
        newDepartmentHead.setNote(request.getNote());

        final DepartmentHead createdDepartmentHead = departmentHeadRespository.save(newDepartmentHead);

        DepartmentHeadResponseDto responseDto = new DepartmentHeadResponseDto();
        responseDto.setFirstName(employee.getFirstName());
        responseDto.setLastName(employee.getLastName());
        responseDto.setUserEmailAddress(employee.getEmail());
        responseDto.setDepartmentName(createdDepartmentHead.getDepartment().getName());
        responseDto.setNote(createdDepartmentHead.getNote());
        responseDto.setStatus(createdDepartmentHead.isEnabled());

        apiResponse =  new ApiResponse("Successful", HttpStatus.CREATED, "Department Head Was Created", responseDto);

        return new ResponseEntity<ApiResponse>(apiResponse, apiResponse.getHttpStatus());
    }

    @Override
    public ResponseEntity<ApiResponse> getAllDepartmentHeads() {

        ApiResponse apiResponse;

        final List<DepartmentHead> getDepartmentsHeads = departmentHeadRespository.findAll();

        apiResponse =  new ApiResponse("Successful", HttpStatus.OK, "List of Department Heads", getDepartmentsHeads);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());

    }


    @Override
    public ResponseEntity<ApiResponse> getByDepartmentId(long departmentId) {

        ApiResponse apiResponse;

        final DepartmentHead departmentHeadDetails = departmentHeadRespository.getDepartmentHeadById(departmentId);

        apiResponse =  new ApiResponse("Successful", HttpStatus.OK, "Department Head Details", departmentHeadDetails);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @Override
    public ResponseEntity<ApiResponse> changeDepartmentHead(ChangeDepartmentHeadReqDto request) {

        String newDepartmentHeadEmail = request.getNewDepartmentHeadEmail().toLowerCase().trim();
        ApiResponse apiResponse;

        String currentPath = "/getDepartmentHeadByDepartmentId/";

        final DepartmentHead departmentHead = departmentHeadRespository.getDepartmentHeadById(request.getDepartmentId());

        if(departmentHead == null){
            throw new NotFoundException("Invalid department Id", currentPath );
        }


        final Employee employee = userRepository.getByEmail(newDepartmentHeadEmail);

        if (employee == null){

            throw new NotFoundException("Invalid department head email address", currentPath);
        }

        final DepartmentHead userIsAlreadyADeptHead = departmentHeadRespository.
                getDepartmentHeadByEmployee_Email(newDepartmentHeadEmail);

        if (userIsAlreadyADeptHead != null){

            throw new IllegalArgumentException("The User is already a department Head", currentPath);
        }


        departmentHead.setEmployee(employee);
        final DepartmentHead updatedDepartmentHeadDetails = departmentHeadRespository.save(departmentHead);

        apiResponse = new ApiResponse("Successful", HttpStatus.OK, "Department Head Was Successfully Changed",
                updatedDepartmentHeadDetails);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
