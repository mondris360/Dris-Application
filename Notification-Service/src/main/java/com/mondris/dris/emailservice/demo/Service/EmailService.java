package com.mondris.dris.emailservice.demo.Service;

import com.mondris.dris.emailservice.demo.Dto.SendMailRequest;
import com.mondris.dris.emailservice.demo.Dto.SendMailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

  ResponseEntity<SendMailResponse> sendEmail(SendMailRequest request) throws Exception;

}
