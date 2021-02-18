package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.UserSignUpReqDto;
import com.mondris.demo.Service.UserService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public ResponseEntity<ApiResponse> createUser(UserSignUpReqDto request) {
        return null;
    }
}
