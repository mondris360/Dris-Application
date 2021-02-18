package com.mondris.demo.Service.Impl;

import com.mondris.demo.Dto.UserSignUpReqDto;
import com.mondris.demo.Model.Employee;
import com.mondris.demo.Repository.UserRepository;
import com.mondris.demo.Service.UserService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    
    @Resource
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<ApiResponse> createUser(UserSignUpReqDto request) {

        final Employee user = modelMapper.map(request, Employee.class);



    }
}
