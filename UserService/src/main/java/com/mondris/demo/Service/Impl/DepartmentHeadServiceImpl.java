package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.DepartmentHeadReqDto;
import com.mondris.demo.Dto.DepartmentHeadResponseDto;
import com.mondris.demo.Model.DepartmentHead;
import com.mondris.demo.Model.Employee;
import com.mondris.demo.Repository.DepartmentHeadRespository;
import com.mondris.demo.Repository.UserRepository;
import com.mondris.demo.Service.DepartmentHeadService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DepartmentHeadServiceImpl implements DepartmentHeadService {

    @Resource
    private DepartmentHeadRespository departmentHeadRespository;

    @Resource
    private UserRepository userRepository;


    @Override
    public ResponseEntity<ApiResponse> createDepartmentHead(DepartmentHeadReqDto request) {

        ApiResponse apiResponse;
        final DepartmentHead departmentHead = departmentHeadRespository.getDepartmentHeadByEmployee_Email(request.getDepartmentHeadUserEmail());

        if(departmentHead != null){
            throw  new IllegalArgumentException("A department head with that email already exist");
        }

        Employee employee = userRepository.getByEmail(request.getDepartmentHeadUserEmail());

        DepartmentHead newDepartmentHead =  new DepartmentHead();
        newDepartmentHead.setEnabled(true);
        newDepartmentHead.setEmployee(employee);
        newDepartmentHead.setNote(request.getNote());

        final DepartmentHead createdDepartmentHead = departmentHeadRespository.save(newDepartmentHead);

        DepartmentHeadResponseDto responseDto = new DepartmentHeadResponseDto();
        responseDto.setFirstName(employee.getFirstName());
        responseDto.setLastName(employee.getLastName());
        responseDto.setNote(createdDepartmentHead.getNote());
        responseDto.setStatus(createdDepartmentHead.isEnabled());

        apiResponse =  new ApiResponse("Successful", HttpStatus.CREATED, "Department Head Was Created", responseDto);

        return new ResponseEntity<ApiResponse>(apiResponse, apiResponse.getHttpStatus());
    }
}
