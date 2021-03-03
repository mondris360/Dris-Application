package com.mondris.demo.Model;


import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name="team_lead")
@Getter
@Setter
@Entity
public class TeamLead extends BaseModel {

    @OneToOne
    @JoinColumn(name="employee_id_fkey")
    private Employee employee;
}
