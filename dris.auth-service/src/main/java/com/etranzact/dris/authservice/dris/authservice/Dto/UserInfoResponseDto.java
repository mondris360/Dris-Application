package com.etranzact.dris.authservice.dris.authservice.Dto;

import com.etranzact.dris.authservice.dris.authservice.Model.Authority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDto {

    private  String email;

    private  Boolean enabled = false;

    private  Set<Authority> authorities;

    private Timestamp created_at;

    private Timestamp updated_at;
}
