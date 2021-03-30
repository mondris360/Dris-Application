package com.mondris.demo.Repository;

import com.mondris.demo.Model.Department;
import com.mondris.demo.Model.DepartmentHead;
import org.hibernate.type.TrueFalseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DepartmentHeadRespository  extends JpaRepository<DepartmentHead, Long> {
    DepartmentHead getDepartmentHeadByIdAndEnabledIsTrue(long departmentHeadId);
    DepartmentHead getDepartmentHeadByDepartment_IdAndEnabledIsTrue(long departmentId);
    DepartmentHead getDepartmentHeadByEmployee_EmailAndEnabledIsTrue(String userEmailAddress);
    List<DepartmentHead> getDepartmentHeadByEnabledIsTrue();
}
