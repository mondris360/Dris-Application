package com.mondris.demo.Model;

import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name="departments")
@Getter
@Setter
public class Department extends BaseModel {

    @NotBlank(message = "name is mandatory")
    private String name;

    @Column(name="department_head")
    @OneToOne
    private DepartmentHead   departmentHead;

    @OneToMany(mappedBy = "department", cascade =  CascadeType.ALL)
    @Column(name="emp_employment_history")
    private Set<EmpEmploymentHistory> empEmploymentHistory;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SubDepartment> subDepartment;
}
