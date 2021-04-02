package com.mondris.demo.Repository;

import com.mondris.demo.Model.Employee;
import com.mondris.demo.Model.SubDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubDepartmentRepository extends JpaRepository<SubDepartment, Long> {
    SubDepartment getByName(String name);
    SubDepartment getById(long id);
    List<SubDepartment> getAllByDepartment_Id(long departmentId);
    List<SubDepartment>getAllByCreatedByUser(Employee user);
}
