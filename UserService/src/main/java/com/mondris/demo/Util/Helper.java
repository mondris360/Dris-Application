package com.mondris.demo.Util;


import com.mondris.demo.Model.Employee;
import com.mondris.demo.Model.SubDepartment;
import com.mondris.demo.Repository.SubDepartmentRepository;
import com.mondris.demo.Repository.UserRepository;
import com.mondris.demo.Util.Api.Exception.CustomErrorClass.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Helper {

    @Resource
    private  UserRepository userRepository;

    @Resource
    private SubDepartmentRepository subDepartmentRepository;


    public  Employee getEmployeeByEmail(String email, String error, String currentPath) {

        final Employee employee = userRepository.getByEmail(email);

        if(employee ==  null){

            throw new NotFoundException(error, currentPath);
        }

        return employee;
    }


    public SubDepartment getSubDepartmentById(long id, String currentPath) {

        final SubDepartment subDepartment = subDepartmentRepository.getById(id);

        if (subDepartment == null){

            throw  new NotFoundException("Invalid Sub Department Id", currentPath);
        }
        return subDepartment;
    }

}
