package com.xingyu.yygh.msm;

import com.xingyu.yygh.common.config.Swagger2Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xingyu"})
@EnableDiscoveryClient
public class ServiceMsmApplication {
    @Autowired
    Swagger2Config config;
    public static void main(String[] args) {
        SpringApplication.run(ServiceMsmApplication.class, args);
    }
}