package com.hanspal.cfapp3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.cloudfoundry.discovery.CloudFoundryDiscoveryClient;
import org.springframework.cloud.cloudfoundry.discovery.EnableCloudFoundryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class CfApp3Application {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(CfApp3Application.class, args);
    }

    @Bean
    CommandLineRunner demo(DiscoveryClient discoveryClient) {
        return args -> {
            discoveryClient.getServices().forEach(svc -> {
                log.info("service = " + svc);
                discoveryClient.getInstances(svc).forEach(si -> log.info("\tinstance = " + si));
            });
        };
    }

}
