package com.mondris.demo.Controller;

import com.mondris.demo.Dto.UserSignUpReqDto;
import com.mondris.demo.Service.UserService;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/completeSignUp")
    @RabbitListener(queues = "UserSignUpQueue")
    public ResponseEntity<ApiResponse> completeSignProcess(@RequestBody  UserSignUpReqDto request){
       return userService.createUser(request);
    }

    @GetMapping("/hello")
    public String Hello(){
        System.out.println("=======================================================");
        return "Hello World";
    }
}
