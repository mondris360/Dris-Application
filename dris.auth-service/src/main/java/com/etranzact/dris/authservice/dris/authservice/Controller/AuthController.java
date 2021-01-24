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


    @PostMapping("/users/signup")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody SignUpRequestDto request){
        return userService.createUser(request);
    }

    @PostMapping("/users/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody AuthRequestDto request){
        return userService.login(request);
    }
    // private route to activate or disable user's account
    @PostMapping("/users/changeAccountStatus")
    public ResponseEntity<ApiResponse> changeAccountStatus(@Valid @RequestBody AccountStatusDto request){
       return  userService.updateAccountStatus(request);
    }
    // route to change user password
    @PostMapping("/users/changePassword")
    public ResponseEntity<ApiResponse> changePassword(@Valid @RequestBody ChangePassRequestDto request){
        return  userService.changePassword(request);
    }


}
