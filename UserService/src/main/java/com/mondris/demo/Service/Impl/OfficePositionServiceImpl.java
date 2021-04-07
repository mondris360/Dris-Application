package com.mondris.demo.Service.Impl;


import com.mondris.demo.Dto.OfficePositionReqDto;
import com.mondris.demo.Dto.UpdateOfficePositionReqDto;
import com.mondris.demo.Model.Employee;
import com.mondris.demo.Model.OfficePosition;
import com.mondris.demo.Repository.EmployeeRepository;
import com.mondris.demo.Repository.OfficePositionRepository;
import com.mondris.demo.Service.OfficePositionService;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.IllegalArgumentException;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.NotFoundException;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.UserNotFoundException;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OfficePositionServiceImpl implements OfficePositionService {

    @Resource
    private OfficePositionRepository officePositionRepository;

    @Resource
    private EmployeeRepository employeeRepository;

    private String currentPath = "/officePosition";

    @Override
    public ApiResponse createOfficePosition(OfficePositionReqDto request) {

        String officePositionName =  request.getName().toLowerCase().trim();

        final OfficePosition officePosition = officePositionRepository.getByName(officePositionName);

        if(officePosition != null){

            throw new IllegalArgumentException("Office position already exist", currentPath);
        }

        final String createdByUserEmail = request.getCreatedByUserEmail().toLowerCase().trim();
        final Employee createdByUser = employeeRepository.getByEmail(createdByUserEmail).orElseThrow(
                () -> new UserNotFoundException("Invalid createdByUserEmail", currentPath));

        OfficePosition newOfficePosition =  new OfficePosition();
        newOfficePosition.setName(officePositionName);
        newOfficePosition.setNote(request.getNote());
        newOfficePosition.setCreatedByUser(createdByUser);

        final OfficePosition createdOfficePosition = officePositionRepository.save(newOfficePosition);

        return new ApiResponse("Successful", HttpStatus.CREATED, "Office Position Was Successfully Created", createdOfficePosition);
    }

    @Override
    public ApiResponse getOfficePositionById(Long id) {

        final OfficePosition officePosition = officePositionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Invalid Office Position Id", currentPath));

        return new ApiResponse("Successful", HttpStatus.OK, "Office Position Details", officePosition);

    }

    @Override
    public ApiResponse updateOfficePosition(UpdateOfficePositionReqDto request) {
        return null;
    }
}
