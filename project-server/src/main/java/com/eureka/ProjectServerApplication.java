package com.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaServer
@EnableSwagger2
@SpringBootApplication
public class ProjectServerApplication {
    public static void main(String[] args){
        SpringApplication.run(ProjectServerApplication.class);
    }
}
