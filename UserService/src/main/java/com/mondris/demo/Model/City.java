package com.mondris.demo.Model;


import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
@Setter
@Getter
public class City extends BaseModel {

    @NotBlank(message = "name is mandatory")
    private String name;

    @OneToOne
    private Address address;
}
