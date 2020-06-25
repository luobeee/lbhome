package com.luobee.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//标注一个主程序类，标明是一个springboot应用
@SpringBootApplication
//启用注解事务管理 然后在在业务层Service方法上标注@Transactional，也可以标注在Service类上，则类下的所有方法都被事务增强
@EnableTransactionManagement
public class LbhomeApplication {
    public static void main(String[] args) {
        SpringApplication.run(LbhomeApplication.class, args);
    }
}
