package com.liber.recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.liber")
public class LiberApplication extends SpringBootServletInitializer {
    protected LiberApplication() {}
    public static void main(String[] args) {
        SpringApplication.run(LiberApplication.class, args);
    }
}