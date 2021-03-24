package com.mondris.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name="phone_contact")
@Entity
@Setter
@Getter
public class PhoneContact extends BaseModel {

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="is_main_number")
    private boolean IsMainNumber;

    private String note;

    @JoinColumn(name="employee_id")
    @ManyToOne
    @JsonIgnore
    private Employee employee;

}
