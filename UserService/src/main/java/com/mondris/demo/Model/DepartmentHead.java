package com.mondris.demo.Model;

import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name="department_head")
@Entity
@Setter
@Getter
public class DepartmentHead  extends BaseModel {

    @Column(name="employee_id_fkey")
    @OneToOne(cascade = CascadeType.ALL)
    private Employee employee;

    private String note;

}
