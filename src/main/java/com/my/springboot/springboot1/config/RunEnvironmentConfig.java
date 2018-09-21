package com.my.springboot.springboot1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.profiles")
@Data
public class RunEnvironmentConfig {

    public String active;
}
