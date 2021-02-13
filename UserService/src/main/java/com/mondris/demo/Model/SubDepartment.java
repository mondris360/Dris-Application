package com.mondris.demo.Model;

import lombok.Getter;
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
public class SubDepartment {

    @NotBlank(message = "name is mandatory")
    private  String name;

    @ManyToOne
    private Department department;

    @OneToOne
    private TeamLead   teamLead;


}
