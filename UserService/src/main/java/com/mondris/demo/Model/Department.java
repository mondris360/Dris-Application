package com.mondris.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="departments")
@Getter
@Setter
public class Department extends BaseModel {

    @NotBlank(message = "department name is mandatory")
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DepartmentHead> departmentHead;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<EmpEmploymentHistory> empEmploymentHistory;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SubDepartment> subDepartment;
}
