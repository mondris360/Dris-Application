package com.etranzact.dris.authservice.dris.authservice.Service.Impl;

import com.etranzact.dris.authservice.dris.authservice.Dto.AuthRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.SignUpRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.UserResponseDto;
import com.etranzact.dris.authservice.dris.authservice.Model.Authority;
import com.etranzact.dris.authservice.dris.authservice.Model.User;
import com.etranzact.dris.authservice.dris.authservice.Repository.UserRepository;
import com.etranzact.dris.authservice.dris.authservice.Service.UserService;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Response.ApiResponse;
import com.etranzact.dris.authservice.dris.authservice.Util.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    private JwtToken jwtToken;

    @Override
    // method to create a new user
    public ResponseEntity<ApiResponse> createUser(@Valid SignUpRequestDto requestDto) {
        ApiResponse apiResponse;

        try {
            // check if the user already exist
            final User userExists = userRepository.getByEmail(requestDto.getEmail());
            if (userExists != null) {
                apiResponse = new ApiResponse("Failed", HttpStatus.CONFLICT, "User Already Exists");

            } else {
                User user = convertToModel(requestDto);
                String token =   jwtToken.generateToken(user);
                userRepository.save(user);
                apiResponse = new ApiResponse("Successful", HttpStatus.CREATED, "User Created", token);
            }

        } catch (Exception e) {
            apiResponse = new ApiResponse("Failed", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            log.info(e.getMessage());
        }

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


    // method to authenticate a user and return a valid jwt token
    @Override
    public ResponseEntity<ApiResponse> login(@Valid AuthRequestDto request) {
        ApiResponse apiResponse;
        try {
            final User user = userRepository.getByEmail(request.getEmail());
            if (user == null) {
                apiResponse = new ApiResponse("Failed", HttpStatus.CONFLICT, "User Already Exists");

            } else if (!user.isEnabled()) {
                apiResponse = new ApiResponse("Failed", HttpStatus.OK, "Account is not active");
            } else {
                // validate the password using bcrypt
                boolean passwordIsValid = bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword());
                if (passwordIsValid) {
                    String token = jwtToken.generateToken(user);
                    apiResponse = new ApiResponse("Successful", HttpStatus.OK, "Login SuccessFul", token);
                } else {
                    apiResponse = new ApiResponse("Failed", HttpStatus.OK, "Invalid Login");
                }
            }
        } catch (Exception e) {
            apiResponse = new ApiResponse("Failed", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            log.info(e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


    // method to convert signUp Dto to signUp model
    private User convertToModel(@Valid SignUpRequestDto requestDto) {
        User user = new User();
        String encodedPassword = bCryptPasswordEncoder.encode(requestDto.getPassword());
        user.setPassword(encodedPassword);
        user.setEmail(requestDto.getEmail());
        user.setAuthorities(requestDto.getAuthorities());
        return user;
    }

//    //  method to convert ModelToUserResponseDto
//    public UserResponseDto convertToUserResponseDto(User request) {
//        return new UserResponseDto(request.getEmail(), request.getPassword());
//    }
}
