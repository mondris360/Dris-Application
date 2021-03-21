package com.mondris.demo.Model;

import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name="departments")
@Getter
@Setter
public class Department extends BaseModel {

    @NotBlank(message = "department name is mandatory")
    private String name;

    @OneToOne(mappedBy = "employee")
    @JoinColumn(name="department_head")
    private DepartmentHead   departmentHead;

    @OneToMany(mappedBy = "department", cascade =  CascadeType.ALL)
    private Set<EmpEmploymentHistory> empEmploymentHistory;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SubDepartment> subDepartment;
}
