package com.etranzact.dris.authservice.dris.authservice.Dto;

import com.etranzact.dris.authservice.dris.authservice.Dto.BaseDto.BaseDto;
import com.etranzact.dris.authservice.dris.authservice.Model.Authority;
import lombok.*;

import javax.validation.Valid;

import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto extends BaseDto{
    private Set< @Valid Authority> authorities;

}
