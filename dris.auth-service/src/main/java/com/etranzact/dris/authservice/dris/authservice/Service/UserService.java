package com.etranzact.dris.authservice.dris.authservice.Service;

import com.etranzact.dris.authservice.dris.authservice.Dto.AuthRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.SignUpRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<ApiResponse> createUser(SignUpRequestDto request);
    ResponseEntity<ApiResponse> login(AuthRequestDto request);
}
