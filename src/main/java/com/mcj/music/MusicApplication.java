package com.mcj.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)   // 开启PreAuthorize注解
public class MusicApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MusicApplication.class, args);
        System.out.println("123");
    }



}
