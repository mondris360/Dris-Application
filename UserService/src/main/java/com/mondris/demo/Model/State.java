package com.mondris.demo.Model;

import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class State extends BaseModel {

    @NotBlank(message = "name is mandatory")
    private String name;

    @OneToOne
    private Address address;
}
