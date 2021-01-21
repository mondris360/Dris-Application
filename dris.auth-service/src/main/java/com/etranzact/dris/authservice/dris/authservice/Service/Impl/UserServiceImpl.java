package com.etranzact.dris.authservice.dris.authservice.Service.Impl;

import com.etranzact.dris.authservice.dris.authservice.Dto.SignUpRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.UserResponseDto;
import com.etranzact.dris.authservice.dris.authservice.Model.User;
import com.etranzact.dris.authservice.dris.authservice.Repository.UserRepository;
import com.etranzact.dris.authservice.dris.authservice.Service.UserService;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    // method to create a new user
    public ResponseEntity<ApiResponse> createUser(@Valid SignUpRequestDto requestDto) {
        ApiResponse apiResponse;
        List<UserResponseDto> data;

        try {
            // check if the user already exist
            final User userExists = userRepository.getByEmail(requestDto.getEmail());
            if (userExists != null) {
                apiResponse = new ApiResponse("Failed", HttpStatus.CONFLICT, "User Already Exists");

            } else {
                User user = convertToModel(requestDto);
                final User createdUser = userRepository.save(user);
                UserResponseDto userResponseDto =  convertToUserResponseDto(createdUser);
                data = new ArrayList<>();
                data.add(userResponseDto);
                apiResponse = new ApiResponse("Successful", HttpStatus.CREATED, "User Created", "AWSS5415C", data);
            }

        } catch (Exception e) {
            apiResponse = new ApiResponse("Failed", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            log.info(e.getMessage());
        }

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }






    // method to convert signUp Dto to signUp model
    private User convertToModel(@Valid  SignUpRequestDto requestDto) {
        User user = new User();
        // encode the plain text password using bcrypt
        String encodedPassword  =  bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setRole(requestDto.getRole());

        return userRepository.save(user);
    }

    //  method to convert ModelToUserResponseDto
    public UserResponseDto convertToUserResponseDto(User request){
        return new UserResponseDto(request.getEmail(), request.getPassword(), request.getRole());
    }
}
