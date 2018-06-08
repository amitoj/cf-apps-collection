package com.hanspal.cfapp7adiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CfApp7aDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CfApp7aDiscoveryApplication.class, args);
    }
}