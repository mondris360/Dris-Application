package com.mondris.demo.Model;


import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name="employee_appraisal")
@Setter
@Getter
@Entity
public class EmployeeAppraisal  extends BaseModel {

    @Column(name="total_score")
    private double totalScore;

    private String note;

    @ManyToOne
    @Column(name="employee_Id")
    private Employee employeeId;

    @OneToMany(mappedBy = "employeeAppraisal", cascade = CascadeType.ALL)
    @Column(name="appraisal_items")
    private Set<AppraisalItem> appraisalItem;


}
