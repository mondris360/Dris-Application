package com.mondris.demo.Repository;

import com.mondris.demo.Model.DepartmentHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentHeadRespository  extends JpaRepository<DepartmentHead, Long> {
    DepartmentHead getDepartmentHeadById(long departmentHeadId);
    DepartmentHead getDepartmentHeadByEmployee_Email(String userEmailAddress);
}
