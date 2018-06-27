package com.my.springboot.springboot1.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * crontab定时器 可以精度到秒
     * 秒 分 时 日 月 星
     */
    @Scheduled(cron="*/6 * * * * *")
    public void process(){
        System.out.println("process1 current_time:" + dateFormat.format(new Date()));
    }

    /**
     * 每隔5秒钟执行
     */
    @Scheduled(fixedRate=5000)
    public void proess2(){
        System.out.println("process2 current_time:" + dateFormat.format(new Date()));
    }
}
