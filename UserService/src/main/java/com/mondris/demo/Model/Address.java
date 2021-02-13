package com.mondris.demo.Model;

import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class Address  extends BaseModel {

    @NotBlank(message = "streetAddress is mandatory")
    @Column(name="street_address")
    private String streetAddress;

    @ManyToOne
    @Column(name="employee_id_fkey")
    private Employee employee;


    @Column(name="address_type")
    private String AddressType = "home";

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Country country;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private State state;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private City city;
}
