package com.matt.feng.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class UserDetailApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserDetailApplication.class, args);
    }
}
