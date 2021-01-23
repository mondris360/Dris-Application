package com.etranzact.dris.authservice.dris.authservice.Dto;

import com.etranzact.dris.authservice.dris.authservice.Dto.BaseDto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassRequestDto extends BaseDto {
    @NotBlank(message = "Please enter a valid new password")
    @Size(min = 8, max=50, message = "Pass must be a min of 8 characters and a max of 50 characters")
    private  String newPassword;
}
