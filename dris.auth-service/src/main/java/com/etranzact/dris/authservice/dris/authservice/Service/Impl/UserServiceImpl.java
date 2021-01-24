package com.etranzact.dris.authservice.dris.authservice.Service.Impl;

import com.etranzact.dris.authservice.dris.authservice.Dto.AccountStatusDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.AuthRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.ChangePassRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.SignUpRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Model.PreviousPassword;
import com.etranzact.dris.authservice.dris.authservice.Model.User;
import com.etranzact.dris.authservice.dris.authservice.Repository.UserRepository;
import com.etranzact.dris.authservice.dris.authservice.Service.UserService;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Response.ApiResponse;
import com.etranzact.dris.authservice.dris.authservice.Util.JwtToken;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

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
                User user = new User();
                // copy the values of requestDTO to user
                BeanUtils.copyProperties(requestDto, user);
                String token =   jwtToken.generateToken(user);
                user.setPassword(bCryptPasswordEncoder.encode(requestDto.getPassword()));
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
                apiResponse = new ApiResponse("Failed", HttpStatus.CONFLICT, "Invalid Login Details");
            } else {
                // validate the password using bcrypt
                boolean passwordIsValid = bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword());
                if (passwordIsValid) {
                    String token = jwtToken.generateToken(user);
                    apiResponse = new ApiResponse("Successful", HttpStatus.OK, "Login SuccessFul", token);
                } else {
                    apiResponse = new ApiResponse("Failed", HttpStatus.OK, "Invalid Login Details");
                }
            }
        } catch (Exception e) {
            apiResponse = new ApiResponse("Failed", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            log.info(e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


    // method to change user password
    @Override
    public ResponseEntity<ApiResponse> changePassword(@Valid ChangePassRequestDto request) {
        ApiResponse apiResponse = null;
        try {
            final User user = userRepository.getByEmail(request.getEmail());
            boolean passwordIsValid = bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword());
            if(!passwordIsValid){
                apiResponse =  new ApiResponse("Failed", HttpStatus.NOT_FOUND, "Invalid Login Details");
                // if the current password is equal to the new password
            } else if(request.getPassword().equals(request.getNewPassword())) {
                apiResponse = new ApiResponse("Failed", HttpStatus.BAD_REQUEST, "Your new password must be different " +
                        "from your current password");
                // if the user is changing to a previously used  password
            } else if(isAPreviousPassword(user, request.getNewPassword())) {
                apiResponse = new ApiResponse("Failed", HttpStatus.BAD_REQUEST, "You cannot" +
                        "change to a previously used password");
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

        } catch(Exception e) {
             apiResponse = new ApiResponse("Failed", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
             log.info(e.getMessage());
        }
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    // method  to activate or disable user's account (only a user with HR role can call this method from the user service)
    @Override
    public ResponseEntity<ApiResponse> updateAccountStatus(@ Valid AccountStatusDto request) {
        ApiResponse apiResponse;

        try {
            final User user = userRepository.getByEmail(request.getEmail());
            if (user == null){
                apiResponse = new ApiResponse("Failed", HttpStatus.BAD_REQUEST, "Invalid Email Address");
                // check if  current account status and new account status are the same
            } else if (user.getEnabled() ==  request.getIsActivate()){

                if(user.getEnabled()){
                    apiResponse = new ApiResponse("Failed",  HttpStatus.BAD_REQUEST, "Account is Already Active");
                } else {
                    apiResponse = new ApiResponse("Failed",  HttpStatus.BAD_REQUEST, "Account is Already Inactive");
                }

            } else {
                String typeOfUpdate =  request.getIsActivate() ? "Activation" : "Deactivation";
                user.setEnabled(request.getIsActivate());
                userRepository.save(user);
                apiResponse =  new ApiResponse("Successful", HttpStatus.OK, "Account " + typeOfUpdate + " Was " + " Successful");
            }

        } catch ( Exception e){
            apiResponse = new ApiResponse("Failed", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            log.info(e.getMessage());
        }

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


    // method to check if the user has used  the new password before
    private boolean isAPreviousPassword(User user,  String newPassword){
        return user.getPreviousPasswords().stream()
                .anyMatch(previousPassword -> bCryptPasswordEncoder.matches(newPassword, previousPassword.getNewPassword()));
    }
}
