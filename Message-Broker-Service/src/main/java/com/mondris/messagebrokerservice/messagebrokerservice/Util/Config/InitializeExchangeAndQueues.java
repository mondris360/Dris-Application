package com.mondris.messagebrokerservice.messagebrokerservice.Util.Config;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

// once the application is ready, create the declared exchange and queues in the config file if they have not been created
@Component
public class InitializeExchangeAndQueues implements ApplicationListener<ApplicationReadyEvent> {
    @Resource
     private RabbitAdmin rabbitAdmin;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("Initializing RabbitMQ Exchange and queues if they do not exists");
            rabbitAdmin.initialize();
    }
}
