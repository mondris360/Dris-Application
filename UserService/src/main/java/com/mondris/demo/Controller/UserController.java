package com.mondris.demo.Controller;

import com.mondris.demo.Dto.UserSignUpReqDto;
import com.mondris.demo.Service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
    public void completeSignProcess(@RequestBody  UserSignUpReqDto request){
        userService.createUser(request);
    }


}
