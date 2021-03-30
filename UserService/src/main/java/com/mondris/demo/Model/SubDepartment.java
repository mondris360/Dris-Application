package com.mondris.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Table(name="sub_department")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SubDepartment extends BaseModel {

    @NotBlank(message = "name is mandatory")
    private  String name;

    @ManyToOne
    @JsonIgnore
    private Department department;



}
