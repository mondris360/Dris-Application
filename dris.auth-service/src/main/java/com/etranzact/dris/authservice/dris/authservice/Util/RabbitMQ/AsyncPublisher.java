package com.etranzact.dris.authservice.dris.authservice.Util.RabbitMQ;

import com.etranzact.dris.authservice.dris.authservice.Dto.SendEmailReqDto;
import com.etranzact.dris.authservice.dris.authservice.Model.User;
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
        log.info("done with  async()");
        log.info(Thread.currentThread().getName());
        rabbitTemplate.convertAndSend("DrisAppExchange", "EmailQueueRoutingKey", sendEmailReqDto);

    }
}
