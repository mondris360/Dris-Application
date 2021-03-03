package com.mondris.demo.Model;


import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Table(name="official_leave")
@Entity
@Setter
@Getter
public class OfficialLeave extends BaseModel {

    @Column(name="total_leave_per_year")
    @NotBlank( message = "totalLeavePerYear is mandatory")
    private int totalLeavePerYear;

    @Column(name="total_leave_taken")
    @NotBlank(message = "totalLeaveTaken is mandatory")
    private int totalLeaveTaken;

    @UpdateTimestamp
    private Timestamp lastLeaveDate;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name="employee_id_fkey")
    private Employee employee;

    @OneToOne(mappedBy = "officialLeave", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private LeaveRequest leaveRequest;


}
