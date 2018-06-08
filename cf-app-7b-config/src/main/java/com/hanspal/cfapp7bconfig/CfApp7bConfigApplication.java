package com.hanspal.cfapp7bconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CfApp7bConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CfApp7bConfigApplication.class, args);
    }
}
