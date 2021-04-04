package com.mondris.demo.Dto;

import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeamLeadReqDto {

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "createdByUserEmail is mandatory")
    @Email
    private String createdByUserEmail;

    @NotNull(message = "invalid sub department id")
    @Positive (message = "sub Department id must be  an integer greater than zero")
    private Long subDepartmentId;

    @Size(max=2000, message = "note must be lesser than or equal to 2000 characters")
    private String note;
}
