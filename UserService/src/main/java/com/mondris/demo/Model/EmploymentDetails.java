package com.mondris.demo.Model;

import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="employment_details")
@Setter
@Getter
public class EmploymentDetails extends BaseModel {

    @OneToOne
    private Employee employee;

    @OneToMany(mappedBy = "employmentDetails")
    private Set<EmpEmploymentHistory> empEmploymentHistory;


}
