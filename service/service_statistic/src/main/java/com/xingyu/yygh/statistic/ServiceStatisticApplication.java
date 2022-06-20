package com.xingyu.yygh.statistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication(exclude = {})//取消数据源自动配置
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.xingyu"})
@ComponentScan(basePackages = {"com.xingyu"})
public class ServiceStatisticApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceStatisticApplication.class, args);
    }
}
