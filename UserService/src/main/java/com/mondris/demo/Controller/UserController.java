package com.mondris.demo.Controller;

import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("user/completeSignUp/{token}")
    public ResponseEntity<ApiResponse> completeSignProcess(@PathVariable String token){

    }
}
