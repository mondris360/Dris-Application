package com.mondris.demo.Repository;

import com.mondris.demo.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findDepartmentByName(String name);
    Optional<Department> findDepartmentById(long id);

}
