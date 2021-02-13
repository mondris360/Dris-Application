package com.mondris.demo.Model;


import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="team_lead")
@Getter
@Setter
@Entity
public class TeamLead extends BaseModel {

    @OneToOne
    @Column(name="employee_id_fkey")
    private Employee employee;
}
