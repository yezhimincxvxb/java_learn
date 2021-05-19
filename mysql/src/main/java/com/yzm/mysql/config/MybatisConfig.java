package com.yzm.mysql.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("com.yzm.mysql.mapper")
@EnableTransactionManagement
public class MybatisConfig {
}
