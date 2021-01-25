package com.etranzact.dris.authservice.dris.authservice.Controller;

import com.etranzact.dris.authservice.dris.authservice.Dto.AccountStatusDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.AuthRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.ChangePassRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.SignUpRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Service.UserService;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
public class AuthController {
    @Resource
    private  UserService userService;


    @PostMapping("/users/signUp")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody SignUpRequestDto request) throws Exception {
//        throw new CustomException("traddsh", HttpStatus.OK);
        final ApiResponse apiResponse = userService.createUser(request);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());

    }

    @PostMapping("/users/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody AuthRequestDto request){
        final ApiResponse apiResponse = userService.login(request);
        return  new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
    // private route to activate or disable user's account
    @PostMapping("/users/changeAccountStatus")
    public ResponseEntity<ApiResponse> changeAccountStatus(@Valid @RequestBody AccountStatusDto request){
        final ApiResponse apiResponse = userService.updateAccountStatus(request);
        return  new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
    // route to change user password
    @PostMapping("/users/changePassword")
    public ResponseEntity<ApiResponse> changePassword(@Valid @RequestBody ChangePassRequestDto request){
        final ApiResponse apiResponse = userService.changePassword(request);
        return  new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


}
