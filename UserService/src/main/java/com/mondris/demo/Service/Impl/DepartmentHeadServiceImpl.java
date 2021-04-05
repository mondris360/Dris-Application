package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.ChangeDepartmentHeadReqDto;
import com.mondris.demo.Dto.DepartmentHeadReqDto;
import com.mondris.demo.Dto.DepartmentHeadResponseDto;
import com.mondris.demo.Model.Department;
import com.mondris.demo.Model.DepartmentHead;
import com.mondris.demo.Model.Employee;
import com.mondris.demo.Repository.DepartmentHeadRespository;
import com.mondris.demo.Repository.DepartmentRepository;
import com.mondris.demo.Repository.EmployeeRepository;
import com.mondris.demo.Service.DepartmentHeadService;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.IllegalArgumentException;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.NotFoundException;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.UserNotFoundException;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class DepartmentHeadServiceImpl implements DepartmentHeadService {

    @Resource
    private DepartmentHeadRespository departmentHeadRespository;

    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private DepartmentRepository departmentRepository;

    private final String currentPath = "/departmentHead";

    @Override
    public ResponseEntity<ApiResponse> createDepartmentHead(DepartmentHeadReqDto request) {

        String departmentHeadEmail = request.getDepartmentHeadUserEmail().toLowerCase().trim();

        ApiResponse apiResponse;

        final DepartmentHead departmentHead = departmentHeadRespository.
                getDepartmentHeadByEmployee_EmailAndEnabledIsTrue(departmentHeadEmail);

        if(departmentHead != null){
            throw  new IllegalArgumentException("A Department Head With That Email Already Exist", currentPath);
        }

        Employee employee = employeeRepository.getByEmail(departmentHeadEmail).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Email Address", currentPath) );


        Department department = departmentRepository.findDepartmentById(request.getDepartmentId());

        if ( department == null){
            throw  new NotFoundException("Invalid Department Id", currentPath );
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

        final List<DepartmentHead> getDepartmentsHeads = departmentHeadRespository.getDepartmentHeadByEnabledIsTrue();

         if (getDepartmentsHeads == null){
             throw new NotFoundException("No Department  Found", currentPath);
         }

        apiResponse =  new ApiResponse("Successful", HttpStatus.OK, "List of Department Heads", getDepartmentsHeads);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());

    }


    @Override
    public ResponseEntity<ApiResponse> getByDepartmentHeadId(long departmentId) {

        ApiResponse apiResponse;
        String currentPath = "/departmentHead/{departmentHeadId}";

        final DepartmentHead departmentHeadDetails = departmentHeadRespository.getDepartmentHeadByIdAndEnabledIsTrue(departmentId);

        if (departmentHeadDetails == null){
            throw new NotFoundException("Invalid/Inactive Department Head Id", currentPath);
        }
        apiResponse =  new ApiResponse("Successful", HttpStatus.OK, "Department Head Details", departmentHeadDetails);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


    @Override
    public ResponseEntity<ApiResponse> getByDepartmentId(long departmentId) {

       String currentPath = "/departmentHeadByDepartmentId/{departmentHeadId}";

       ApiResponse apiResponse;

        final DepartmentHead departmentHead = departmentHeadRespository.getDepartmentHeadByDepartment_IdAndEnabledIsTrue(departmentId);

        if (departmentHead == null){

            throw new NotFoundException("Invalid Department Id", currentPath);
        }
        apiResponse =  new ApiResponse("Successful", HttpStatus.OK, "Department Head Details", departmentHead);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


    @Override
    public ResponseEntity<ApiResponse> changeDepartmentHead(ChangeDepartmentHeadReqDto request) {

        String newDepartmentHeadEmail = request.getNewDepartmentHeadEmail().toLowerCase().trim();
        ApiResponse apiResponse;

        String currentPath2 = currentPath+"/departmentId";

        final DepartmentHead departmentHead = departmentHeadRespository.getDepartmentHeadByIdAndEnabledIsTrue(request.getDepartmentId());

        if(departmentHead == null){
            throw new NotFoundException("Invalid department Id", currentPath2 );
        }


        final Employee employee = employeeRepository.getByEmail(newDepartmentHeadEmail).orElseThrow(

                () -> new NotFoundException("Invalid department head email address", currentPath));


        final DepartmentHead userIsAlreadyADeptHead = departmentHeadRespository.
                getDepartmentHeadByEmployee_EmailAndEnabledIsTrue(newDepartmentHeadEmail);

        if (userIsAlreadyADeptHead != null){

            throw new IllegalArgumentException("The User is already a department Head", currentPath);
        }


        departmentHead.setEmployee(employee);
        final DepartmentHead updatedDepartmentHeadDetails = departmentHeadRespository.save(departmentHead);

        apiResponse = new ApiResponse("Successful", HttpStatus.OK, "Department Head Was Successfully Changed",
                updatedDepartmentHeadDetails);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @Override
    public ResponseEntity<ApiResponse> deleteDepartHeadById(long departmentHeadId) {

        ApiResponse apiResponse;

        final DepartmentHead departmentHead = departmentHeadRespository.getDepartmentHeadByIdAndEnabledIsTrue(departmentHeadId);

        if (departmentHead == null) {

            throw new NotFoundException("Invalid/deleted Department Head Id", currentPath);
        }

        departmentHead.setEnabled(false);

        departmentHeadRespository.save(departmentHead);

        apiResponse = new ApiResponse("Successful", HttpStatus.OK, "Department Head Was Deleted Successfully");

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }





}
