package com.mondris.demo.Repository;

import com.mondris.demo.Model.Employee;
import com.mondris.demo.Model.SubDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubDepartmentRepository extends JpaRepository<SubDepartment, Long> {
    SubDepartment getByName(String name);
    List<SubDepartment> getAllByDepartment_Id(long departmentId);
    List<SubDepartment>getAllByCreatedByUser(Employee user);
}
