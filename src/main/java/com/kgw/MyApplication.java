package com.kgw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/14 19:43
 */
@SpringBootApplication
@MapperScan(basePackages = "com.kgw.mapper")
@EnableTransactionManagement
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class);
    }
}
