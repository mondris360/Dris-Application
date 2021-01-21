package com.etranzact.dris.authservice.dris.authservice.Controller;

import com.etranzact.dris.authservice.dris.authservice.Dto.SignUpRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Service.UserService;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Resource
    private  UserService userService;

    @PostMapping("/users")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody SignUpRequestDto request){
        return userService.createUser(request);
    }

    @GetMapping("/users")
    public String getnothing(){
        return "Hello";
    }
}
