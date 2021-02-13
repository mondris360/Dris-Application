package com.mondris.demo.Model;

import com.mondris.demo.Model.BaseModel.BaseModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name="office_position")
public class OfficePosition extends BaseModel {

    @NotBlank(message = "name is mandatory")
    private String name;

    @OneToMany(mappedBy = "officePosition")
    @Column(name = "emp_employment_history")
    private Set<EmpEmploymentHistory> empEmploymentHistory;

}
