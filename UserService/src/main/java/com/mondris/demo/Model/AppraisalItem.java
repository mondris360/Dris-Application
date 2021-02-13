package com.mondris.demo.Model;

import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Table(name="appraisal_items")
@Getter
@Setter
@Entity
public class AppraisalItem  extends BaseModel {

    @NotBlank(message = "name is mandatory")
    private String name;

    @ManyToOne
    @Column(name="employee_appraisal")
    private EmployeeAppraisal employeeAppraisal;

}
