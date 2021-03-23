package com.mondris.demo.Model;

import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address  extends BaseModel {

    @NotBlank(message = "streetAddress is mandatory")
    @Column(name="street_address")
    private String streetAddress;

    @ManyToOne
    private Employee employee;


    @Column(name="address_type")
    private String addressType = "home";

    @OneToOne( fetch = FetchType.LAZY)
    private Country country;

    @OneToOne( fetch = FetchType.LAZY)
    private State state;

    @OneToOne( fetch = FetchType.LAZY)
    private City city;

}
