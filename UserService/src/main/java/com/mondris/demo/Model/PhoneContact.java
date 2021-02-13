package com.mondris.demo.Model;

import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="phone_contact")
@Entity
@Setter
@Getter
public class PhoneContact extends BaseModel {

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="is_main_number")
    private boolean isMainNumber;

    private String note;

    @Column(name="employee_id")
    @ManyToOne
    private Employee employeeId;

}
