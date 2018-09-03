package com.my.springboot.springboot1.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloSenderTest {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private HelloSender2 helloSender2;

    @Test
    public void send() {
        for (int i = 0; i < 20; i++){
            helloSender.send(i);
            helloSender2.send(i);
        }

    }
}