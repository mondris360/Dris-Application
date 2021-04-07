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
import java.util.List;

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
                () -> new NotFoundException("Invalid Office Position Id", currentPath+"/{id}"));

        return new ApiResponse("Successful", HttpStatus.OK, "Office Position Details", officePosition);

    }

    @Override
    public ApiResponse getAllOfficePositions() {

        final List<OfficePosition> allOfficePositions = officePositionRepository.findAllByOrderByNameAsc();

        return new ApiResponse("Successful", HttpStatus.OK, "List Of All Office Positions", allOfficePositions);
    }

    @Override
    public ApiResponse updateOfficePosition(UpdateOfficePositionReqDto request) {

        final OfficePosition officePositionById = officePositionRepository.findById(request.getId()).orElseThrow(
                () -> new NotFoundException("Invalid office position id", currentPath));

        String updatedByUserEmail = request.getUpdatedByUserEmail().toLowerCase().trim();

        final Employee updatedByUser = employeeRepository.getByEmail(updatedByUserEmail).orElseThrow(
                () -> new UserNotFoundException("Invalid updatedByUserEmail", currentPath));

        String officePositionName =  request.getName().trim().toLowerCase();

        final OfficePosition officePositionByName = officePositionRepository.getByName(officePositionName);

        if (officePositionByName != null && officePositionById.getId() != officePositionByName.getId()){

            throw new IllegalArgumentException("Sorry, An Office Position With This Name Already Exist", currentPath);

        }
        officePositionById.setName(officePositionName);
        officePositionById.setNote(request.getNote());
        officePositionById.setUpdatedByUser(updatedByUser);

        final OfficePosition updatedOfficePosition = officePositionRepository.save(officePositionById);

        return new ApiResponse("Successful", HttpStatus.OK, "Office Position Was Successfully Updated", updatedOfficePosition);
    }
}
