package com.mondris.dris.emailservice.demo.Controller;


import com.mondris.dris.emailservice.demo.Dto.SendMailRequest;
import com.mondris.dris.emailservice.demo.Dto.SendMailResponse;
import com.mondris.dris.emailservice.demo.Service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class EmailController {

    @Resource
    private EmailService emailService;

    @PostMapping("/sendEmail")
    ResponseEntity<SendMailResponse> sendEmail(@Valid @RequestBody  SendMailRequest request) throws Exception {
        System.out.println("request" +  request);
        return  emailService.sendEmail(request);
    }
}
