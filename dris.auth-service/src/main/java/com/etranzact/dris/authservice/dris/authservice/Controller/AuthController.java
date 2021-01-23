package com.etranzact.dris.authservice.dris.authservice.Controller;

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

    // route to change user password
    @PostMapping("/users/changePassword")
    public ResponseEntity<ApiResponse> changePassword(@Valid @RequestBody ChangePassRequestDto request){
        System.out.println("======= inside change password controller");
        System.out.println(request);
        return  userService.changePassword(request);
    }



    @GetMapping("/")
    public String allUsers(){
        return "Not restricted route";
    }

    @GetMapping("/users")
    public String restricted(){
        return "Welcome User/Admin";
    }

    @GetMapping("/admin")
    public String  adminRoute(){
        return "Welcome Admin";
    }
}
