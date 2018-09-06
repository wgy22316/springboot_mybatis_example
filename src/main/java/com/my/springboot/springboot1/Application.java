package com.my.springboot.springboot1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.ResourceUtils;
import tk.mybatis.spring.annotation.MapperScan;

import javax.activation.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

@MapperScan(basePackages = "com.my.springboot.springboot1.mapper")
////启用定时任务
//@EnableScheduling

//默认加载了thymeleaf 配置 如若不配置thymeleaf包则报错，所以不适用可以排除掉
@SpringBootApplication(exclude = ThymeleafAutoConfiguration.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
