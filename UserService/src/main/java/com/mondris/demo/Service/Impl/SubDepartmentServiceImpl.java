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
import java.util.List;

@Service
@Slf4j
public class SubDepartmentServiceImpl implements SubDepartmentService {

    @Resource
    private DepartmentRepository departmentRepository;

    @Resource
    private SubDepartmentRepository subDepartmentRepository;

    @Resource
    private UserRepository userRepository;

    private final String currentPath = "/subDepartment";


    @Override
    public ApiResponse createSubDepartment(SubDepartmentReqDto request) {

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
    public ApiResponse updatedSubDepartment(UpdateSubDepartmentReqDto request) {

        SubDeptCommonOperationsDto subDeptCommonOperationsDto =   new SubDeptCommonOperationsDto();
        subDeptCommonOperationsDto.setDepartmentId(request.getDepartmentId());
        subDeptCommonOperationsDto.setSubDepartmentId(request.getSubDepartmentId());
        subDeptCommonOperationsDto.setHttpStatus(HttpStatus.OK);
        subDeptCommonOperationsDto.setNote(request.getNote());
        subDeptCommonOperationsDto.setOperationType("update");
        subDeptCommonOperationsDto.setSuccessMsg("subDepartment Was Successfully Updated");
        subDeptCommonOperationsDto.setSubDepartmentName(request.getName());
        subDeptCommonOperationsDto.setUserEmail(request.getUpdatedByUserEmail());

        return commonOperations(subDeptCommonOperationsDto);
    }

    @Override
    public ApiResponse getSubDepartmentById(long id) {

        String currentPath2 =  currentPath + "/id";

        final SubDepartment subDepartment = findSubDepartmentById(id, currentPath2);


        return  new ApiResponse("Successful", HttpStatus.OK, "Sub Department Details", subDepartment);

    }


    @Override
    public ApiResponse getAllSubDepartments() {

        final List<SubDepartment> allSubDepartments = subDepartmentRepository.findAll();

        return  new ApiResponse("Successful", HttpStatus.OK, "A List of All SubDepartments", allSubDepartments);

    }

    @Override
    public ApiResponse getAllSubDepartmentsByDeptId(long id) {

        final List<SubDepartment> allSubDepartmentsInADept = subDepartmentRepository.getAllByDepartment_Id(id);

        return  new ApiResponse("Successful", HttpStatus.OK,"A List of  ALl Sub Departments In A Department",
                allSubDepartmentsInADept);

    }

    @Override
    public ApiResponse getAllSubDeptsCreatedByAUser(String userEmail) {

        String currentPath = "/getAllSubDeptsCreatedByAUser/{email}";

        final Employee user = userRepository.getByEmail(userEmail);

        if (user == null){

            throw new NotFoundException("Invalid User Email Address", currentPath);
        }

        final List<SubDepartment> subDepartmentsCreatedByAUser = subDepartmentRepository.getAllByCreatedByUser(user);

        return new ApiResponse("Successful", HttpStatus.OK, "All Sub Departments Created By A User", subDepartmentsCreatedByAUser);
    }

    @Override
    public ApiResponse deleteSubDepartmentById(long id) {

        final SubDepartment subDepartment = findSubDepartmentById(id, currentPath);

        subDepartmentRepository.delete(subDepartment);

        return new ApiResponse("Successful", HttpStatus.OK, "Sub Department Was Successfully Deleted");

    }







    private ApiResponse commonOperations(SubDeptCommonOperationsDto request){

        final Employee employee = userRepository.getByEmail(request.getUserEmail());

        if(employee ==  null){

            throw new NotFoundException("Invalid user email address", currentPath);
        }

        final Department department = departmentRepository.findDepartmentById(request.getDepartmentId());

        if (department == null){

            throw new NotFoundException("Invalid Department Id", currentPath);
        }

        String subDepartmentName = request.getSubDepartmentName().trim().toLowerCase();


        String operationType =  request.getOperationType().toLowerCase().trim();

        final SubDepartment subDepartment;

        // create a new sub department
        final SubDepartment subDepartmentByName = subDepartmentRepository.getByName(subDepartmentName);

        if (operationType.equals("create")){

            if (subDepartmentByName != null){

                throw new NotFoundException("A subDepartment with that name already exists", currentPath);
            }

                SubDepartment newSubDepartment =  new SubDepartment();
                newSubDepartment.setName(subDepartmentName);
                newSubDepartment.setNote(request.getNote());
                newSubDepartment.setDepartment(department);
                // the user that is creating the subDepartment
                newSubDepartment.setCreatedByUser(employee);

                subDepartment = subDepartmentRepository.save(newSubDepartment);

        } else {  // update already existing subDepartment

            final SubDepartment subDepartmentById = findSubDepartmentById(request.getSubDepartmentId(), currentPath);

            if( subDepartmentByName != null  && (subDepartmentById.getId() != subDepartmentByName.getId())){

                throw new IllegalArgumentException("Your new subDepartment name already exists", currentPath);
            }

            subDepartmentById.setName(request.getSubDepartmentName());
            subDepartmentById.setDepartment(department);
            subDepartmentById.setNote(request.getNote());
            subDepartmentById.setUpdatedByUser(employee);

            subDepartment = subDepartmentRepository.save(subDepartmentById);

        }

        return new ApiResponse("Successful", request.getHttpStatus(), request.getSuccessMsg(), subDepartment);

    }



    private  SubDepartment findSubDepartmentById(long id, String currentPath){

        final SubDepartment subDepartment = subDepartmentRepository.getById(id);

        if (subDepartment == null){

            throw new NotFoundException("Invalid subDepartment Id", currentPath);
        }

        return  subDepartment;
    }


}
