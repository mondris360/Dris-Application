package com.mondris.messagebrokerservice.messagebrokerservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageBrokerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageBrokerServiceApplication.class, args);
    }

}
