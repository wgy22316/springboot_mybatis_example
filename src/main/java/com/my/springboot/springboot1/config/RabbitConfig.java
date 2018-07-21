package com.my.springboot.springboot1.config;

//import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue(){
        return new Queue("Hello");
    }
}
