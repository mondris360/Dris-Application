package com.mondris.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mondris.demo.Model.BaseModel.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name="employees")
@Getter
@Setter
public class Employee extends BaseModel {

    @Column(name="first_name")
    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    @Column(name="last_name")
    @NotBlank(message = "lastName is mandatory")
    private String lastName;

    @NotBlank(message = "email is mandatory")
    @Email
    private String email;

    @Column(name = "employment_details")
    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EmploymentDetails employmentDetails;

    @Column(name = "phone_contact")
    @OneToMany(mappedBy = "employeeId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PhoneContact> phoneContact;

    @OneToMany(mappedBy = "employeeId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Column(name="employee_appraisals")
    private Set<EmployeeAppraisal> employeeAppraisals;

    @OneToMany(mappedBy= "employee", fetch =  FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OfficialLeave> officialLeaves;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private Set<Address> address;

    private String note;

}
