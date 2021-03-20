package com.mondris.demo.Model;

import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name ="employee_employment_history")
public class EmpEmploymentHistory extends BaseModel {

    @Column(name = "is_current_office_position")
    private boolean isCurrentOfficePosition = false;

    @Column(name = "is_current_department")
    private boolean isCurrentDepartment = false;

    @CreationTimestamp
    private Timestamp from_date;

    @CreationTimestamp
    private Timestamp to_date; // the date  status was changed to false

    @JoinColumn(name =  "employee_id")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name="employment_details_fkey")
    @ManyToOne
    private EmploymentDetails employmentDetails;

    @JoinColumn(name="office_position_fkey")
    @ManyToOne(cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    private OfficePosition officePosition;

    @JoinColumn(name="department_fkey")
    @ManyToOne(cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    private Department department;


}
