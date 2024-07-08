package com.mashibing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient // spc netflix提供的注解，eureka客户端使用
/*
spc提供的注解，服务发现客户端使用，一般建议使用这个注解，因为@EnableEurekaClient是专门针对eureka作为注册中心使用的，如果换个注册中心该注解就不能用了。
而@EnableDiscoveryClient换任何注册中心都可以使用
 */
//@EnableDiscoveryClient
public class EurekaClientOrderStarter {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientOrderStarter.class);
    }
}
