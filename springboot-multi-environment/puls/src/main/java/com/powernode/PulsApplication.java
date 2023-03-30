package com.powernode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com/powernode/mapper")
public class PulsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PulsApplication.class, args);
    }

}
