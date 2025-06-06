package com.acad.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
@SpringBootApplication
@EnableConfigServer
public class AcadConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcadConfigServerApplication.class, args);
    }

}
