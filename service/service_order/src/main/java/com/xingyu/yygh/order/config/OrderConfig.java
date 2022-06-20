package com.xingyu.yygh.order.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.xingyu.yygh.order.mapper")
public class OrderConfig {
}
