package com.project.facegram;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.project.facegram")
public class FacegramApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacegramApplication.class, args);
    }

}

