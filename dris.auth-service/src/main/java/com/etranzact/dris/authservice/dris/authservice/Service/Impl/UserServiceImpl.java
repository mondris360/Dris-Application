package com.etranzact.dris.authservice.dris.authservice.Service.Impl;

import com.etranzact.dris.authservice.dris.authservice.Dto.AccountStatusDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.AuthRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.ChangePassRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.SignUpRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Model.PreviousPassword;
import com.etranzact.dris.authservice.dris.authservice.Model.User;
import com.etranzact.dris.authservice.dris.authservice.Repository.UserRepository;
import com.etranzact.dris.authservice.dris.authservice.Service.UserService;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception.CustomErrorClass.CustomException;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception.CustomErrorClass.IllegalArgumentException;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception.CustomErrorClass.UnAuthorizedException;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Exception.CustomErrorClass.UserNotFoundException;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Response.ApiResponse;
import com.etranzact.dris.authservice.dris.authservice.Util.JwtToken;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@CacheConfig(cacheNames = "users")
public class UserServiceImpl implements UserService {
    @Value("${server.servlet.context-path}")
    private String baseRoute;
    @Resource
    private UserRepository userRepository;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    private JwtToken jwtToken;

    @Override
    // method to create a new user
    public ApiResponse createUser(@Valid SignUpRequestDto requestDto) {
        ApiResponse apiResponse;
        String currentRoute =  baseRoute+"/users/signUp";
        final User userExists = userRepository.getByEmail(requestDto.getEmail());

        if (userExists != null) {
            throw new CustomException("Email Address Already Exists", currentRoute);
        } else {
            User user = new User();
            // copy the values of requestDTO to user
            BeanUtils.copyProperties(requestDto, user);
            user.setPassword(bCryptPasswordEncoder.encode(requestDto.getPassword()));
            userRepository.save(user);
            String token =   jwtToken.generateToken(user);
            apiResponse = new ApiResponse("Successful", HttpStatus.CREATED, "User Created", token);
        }
        return apiResponse;
    }


    // method to authenticate a user and return a valid jwt token
    @Override
    @Cacheable(key = "#request.getEmail()")
    public ApiResponse login(@Valid AuthRequestDto request)  {
        String currentRoute = baseRoute + "/users/login";
        ApiResponse apiResponse;

        final User user = userRepository.getByEmail(request.getEmail());
        if (user == null) {
            throw new UserNotFoundException("Invalid Login Details", currentRoute);
        }  else if (!user.getEnabled()) {
            throw  new UnAuthorizedException("Sorry this  account is not active", currentRoute);
        }
        boolean passwordIsValid = bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword());
        if (passwordIsValid) {
            String token = jwtToken.generateToken(user);
            apiResponse = new ApiResponse("Successful", HttpStatus.OK, "Login SuccessFul", token);
        } else {
            throw new CustomException("Invalid Login Details", currentRoute);
        }

        return apiResponse;
    }


    // method to change user password
    @Override
    public ApiResponse changePassword(@Valid ChangePassRequestDto request) {
        ApiResponse apiResponse;
        String currentRoute = baseRoute + "/users/login";

        final User user = userRepository.getByEmail(request.getEmail());
        boolean passwordIsValid = bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword());

        if(!passwordIsValid){
            throw new UserNotFoundException("Invalid Login Details", currentRoute);
            // if the current password is equal to the new password
        } else if(request.getPassword().equals(request.getNewPassword())) {
            throw new IllegalArgumentException("Your new password must be different from your current password", currentRoute);
            // if the user is changing to a previously used  password
        } else if(isAPreviousPassword(user, request.getNewPassword())) {
            throw new IllegalArgumentException("You cannot  change to a previously used password", currentRoute);
        } else {
            String encodedNewPassword =  bCryptPasswordEncoder.encode(request.getNewPassword());
            String encodedCurrentPassword =  user.getPassword();
            user.setPassword(encodedNewPassword);
            Set<PreviousPassword> data =  new HashSet<>();
            data.add(new PreviousPassword(encodedCurrentPassword, user));
            user.setPreviousPasswords(data);
            final User updatedUser = userRepository.save(user);
            apiResponse = new ApiResponse("Successful", HttpStatus.OK, "Password Changed Successfully");
        }
        return apiResponse;
    }


    // method  to activate or disable user's account (only a user with an HR role can access this method from the user service)
    @Override
    public ApiResponse updateAccountStatus(@ Valid AccountStatusDto request) {
        ApiResponse apiResponse;
        String currentRoute = baseRoute+"users/changePassword";

        final User user = userRepository.getByEmail(request.getEmail());
        if (user == null){
            throw new UserNotFoundException("Invalid  Login Details", currentRoute);
            // check if  current account status and new account status are the same
        } else if (user.getEnabled() ==  request.getIsActivate()){
            if(user.getEnabled()){
                throw new IllegalArgumentException("Account is Already Active", currentRoute);
            }
            throw new IllegalArgumentException("Account is Already Inactive", currentRoute);
        } else {
            String typeOfUpdate =  request.getIsActivate() ? "Activation" : "Deactivation";
            user.setEnabled(request.getIsActivate());
            userRepository.save(user);
            apiResponse =  new ApiResponse("Successful", HttpStatus.OK, "Account " + typeOfUpdate + " Was " + " Successful");
        }
        return apiResponse;
    }


    // method to check if the user has used  the new password before
    private boolean isAPreviousPassword(User user,  String newPassword){
        return user.getPreviousPasswords().stream()
                .anyMatch(previousPassword -> bCryptPasswordEncoder.matches(newPassword, previousPassword.getNewPassword()));
    }
}
