package com.mondris.demo.Controller;

import com.mondris.demo.Dto.UserSignUpReqDto;
import com.mondris.demo.Service.UserService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/completeSignUp")
    public ResponseEntity<ApiResponse> completeSignProcess(UserSignUpReqDto request){
       return userService.createUser(request);
    }
}
