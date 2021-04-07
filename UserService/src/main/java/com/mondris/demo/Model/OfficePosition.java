package com.mondris.demo.Model;

import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name="office_position")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OfficePosition extends BaseModel {

    @NotBlank(message = "name is mandatory")
    private String name;

    @OneToMany(mappedBy = "officePosition")
    @Column(name = "emp_employment_history")
    private Set<EmpEmploymentHistory> empEmploymentHistory;

}
