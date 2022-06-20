package com.xingyu.yygh.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xingyu.yygh.user.mapper")
public class UserConfig {
}
