package com.mondris.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateTeamLeadReqDto {

    @NotNull(message = "Team Lead Id Is Mandatory")
    @Positive(message = "team Lead  Id must be greater than zero")
    private Long teamLeadId;

    @NotNull(message = "Sub Department  Id Is Mandatory")
    @Positive(message = "Sub Department Id must be greater than zero")
    private Long subDepartmentId;

    @NotBlank(message = "Team Lead Email is mandatory")
    @Email
    private  String teamLeadEmail;

    @NotBlank(message = "updatedByUserEmail Is Mandatory")
    @Email
    private String updatedByUserEmail;

    @Size(max =  2000, message = "note must  be lesser than or equal to 2000 characters")
    private String note;

}
