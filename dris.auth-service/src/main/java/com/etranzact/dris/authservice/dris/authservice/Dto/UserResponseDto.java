package com.etranzact.dris.authservice.dris.authservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String email;
    private String jwtToken;
    private Set<?> authorities;
}
