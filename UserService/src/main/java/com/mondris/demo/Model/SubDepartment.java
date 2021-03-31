package com.mondris.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "created_by_user_email")
    private Employee employee;



}
