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
public class TeamLeadReqDto {

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "createdByUserEmail is mandatory")
    @Email
    private String createdByUserEmail;

    @NotNull(message = "invalid sub department id")
    @Positive
    private Long subDepartmentId;

    @Size(max=2000, message = "note must be lesser than or equal to 2000 characters")
    private String note;
}
