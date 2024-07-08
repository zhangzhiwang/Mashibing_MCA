package com.mashibing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // 启动Eureka服务端，Eureka服务端使用该注解
public class EurekaServerSingleStarter {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerSingleStarter.class);
    }
}
