package com.mondris.demo.Model;

import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name="phone_contact")
@Entity
@Setter
@Getter
@ToString
public class PhoneContact extends BaseModel {

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="is_main_number")
    private boolean IsMainNumber;

    private String note;

    @JoinColumn(name="employee_id")
    @ManyToOne
    private Employee employee;

}
