package com.mondris.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name="department_head")
@Entity
@Setter
@Getter
@ToString
public class DepartmentHead  extends BaseModel {

    @JoinColumn(name="employee_id_fkey")
    @OneToOne
    private Employee employee;

    @JoinColumn(name="department_id_fkey")
    @OneToOne
    private Department department;

    private String note;

}
