package com.etranzact.dris.authservice.dris.authservice.Util.RabbitMQ;

import com.etranzact.dris.authservice.dris.authservice.Dto.SendEmailReqDto;
import com.etranzact.dris.authservice.dris.authservice.Dto.SignUpRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Model.User;
import com.etranzact.dris.authservice.dris.authservice.Util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AsyncPublisher {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Async
    public void emailPublisher(User user, String firstName, String link, String subject, String emailTemplateName) {

        SendEmailReqDto sendEmailReqDto =  new SendEmailReqDto();
        sendEmailReqDto.setFromEmail("admin@drisApp.com");
        sendEmailReqDto.setToEmail(user.getEmail());
        sendEmailReqDto.setReceiverFirstName(firstName);
        sendEmailReqDto.setLink(link);
        sendEmailReqDto.setMessageBodyTemplateName(emailTemplateName);
        sendEmailReqDto.setSenderFullName("Michael Mondris");
        sendEmailReqDto.setSubject(subject);
        rabbitTemplate.convertAndSend(Constants.DrisAppExchange.toString(), Constants.EmailQueueRoutingKey.toString(),
                sendEmailReqDto);

    }

    //  method to send user/ employment details to user service
    @Async
    public void userSignUpPublisher(SignUpRequestDto request) {
        log.info("inside userSignUpPublisher");
        System.out.println(request);
        rabbitTemplate.convertAndSend(Constants.DrisAppExchange.toString(), Constants.UserSignUpQueueRoutingKey.toString(), request);

    }
}
