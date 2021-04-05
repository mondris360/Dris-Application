//package com.mondris.demo.Util;
//
//
//import com.mondris.demo.Model.Employee;
//import com.mondris.demo.Model.SubDepartment;
//import com.mondris.demo.Repository.SubDepartmentRepository;
//import com.mondris.demo.Repository.TeamLeadRepository;
//import com.mondris.demo.Repository.EmployeeRepository;
//import com.mondris.demo.Util.Api.Exception.CustomErrorClass.NotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
//@Service
//public class Helper {
//
//    @Resource
//    private EmployeeRepository employeeRepository;
//
//    @Resource
//    private SubDepartmentRepository subDepartmentRepository;
//
//    @Resource
//    private TeamLeadRepository teamLeadRepository;
////
////    public  Employee getEmployeeByEmail(String email, String error, String currentPath) {
////
////        final Employee employee = employeeRepository.getByEmail(email);
////
////        if(employee ==  null){
////
////            throw new NotFoundException(error, currentPath);
////        }
////
////        return employee;
////    }
//
//
////    public SubDepartment getSubDepartmentById(long id, String currentPath) {
////
////        final SubDepartment subDepartment = subDepartmentRepository.getById(id).get();
////
////        if (subDepartment == null){
////
////            throw  new NotFoundException("Invalid Sub Department Id", currentPath);
////        }
////        return subDepartment;
////    }
////
//////    public TeamLead getTeamLeadById(long id, String currentPath) {
////
////        if (id < 1){
////
////            throw  new IllegalArgumentException("id must be greater than zero", currentPath);
////        }
////
////        final TeamLead teamLead = teamLeadRepository.getById(id);
////
////        if(teamLead == null) {
////
////            throw new NotFoundException("Invalid Team Lead Id", currentPath);
////        }
////
////        return teamLead;
////
////    }
//
//}
