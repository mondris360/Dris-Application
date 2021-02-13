package com.mondris.demo.Model;

import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Table(name="leave_request")
@Setter
@Getter
@Entity
public class LeaveRequest extends BaseModel {

    @NotBlank(message = "from_date is mandatory")
    private Timestamp from_date;

    @NotBlank(message = "to_date is mandatory")
    private Timestamp to_date;

    @OneToOne
    private OfficialLeave officialLeave;
}
