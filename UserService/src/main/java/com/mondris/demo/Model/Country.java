package com.mondris.demo.Model;


import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Country extends BaseModel {

    @NotBlank(message = "name is mandatory")
    private String name;

    @Column(name="zip_code")
    @NotBlank(message = "zipcode is mandatory")
    private String zipCode;

//    @OneToOne(cascade = CascadeType.ALL)
//    private Address address;
}
