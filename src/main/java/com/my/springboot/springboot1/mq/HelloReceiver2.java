package com.my.springboot.springboot1.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "Hello")
public class HelloReceiver2 {

    @RabbitHandler
    public void process(String Hello){
        System.out.println("Receiver2:" + Hello);
    }
}
