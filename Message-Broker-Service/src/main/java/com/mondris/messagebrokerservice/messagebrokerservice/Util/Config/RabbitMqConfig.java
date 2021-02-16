package com.mondris.messagebrokerservice.messagebrokerservice.Util.Config;

import com.mondris.messagebrokerservice.messagebrokerservice.Util.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue emailQueue(){
     return new Queue(Constants.EmailQueue.toString());
    }

    @Bean
    public Queue smsQueue(){
        return new Queue(Constants.SMSQueue.toString());
    }

    @Bean
    public Queue userNotificationQueue(){
        return new Queue(Constants.UserNotificationQueue.toString());
    }


    @Bean
    public TopicExchange myExchange(){
        return new TopicExchange(Constants.DrisAppExchange.toString());
    }

    // bind the queues to the exchange
    @Bean
    public Binding emailQueueBinding(TopicExchange myExchange){
        return  BindingBuilder.bind(emailQueue()).to(myExchange).with(Constants.EmailQueueRoutingKey.toString());
    }

    @Bean
    public Binding smsQueueBinding(TopicExchange myExchange){
        return BindingBuilder.bind(smsQueue()).to(myExchange).with(Constants.SMSQueueRoutingKey.toString());
    }


    @Bean
    public  Binding  userNotificationQueueBinding(TopicExchange myExchange){
        return BindingBuilder.bind(userNotificationQueue()).to(myExchange).with(Constants.UserNotificationQueueRoutingKey.toString());
    }

    @Bean
    public MessageConverter pojoToJsonConverter(){
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public RabbitAdmin rabbitAdmin (ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

   
}
