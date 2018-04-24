package com.hanspal.cfapp4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;

@SpringBootApplication
@EnableScheduling
public class CfApp4Application {

    private Random rand = new Random();

    private Logger log = LoggerFactory.getLogger(CfApp4Application.class);

    public static void main(String[] args) {
        SpringApplication.run(CfApp4Application.class, args);
    }

    @Scheduled(fixedRate = 1000)
    private void log1() {
        if (rand.nextInt(100) > 40)
            log.info("Info log : " + System.currentTimeMillis());
    }

    @Scheduled(fixedRate = 1000)
    private void log2() {
        if (rand.nextInt(100) > 90)
            log.debug("Debug info : " + System.currentTimeMillis());
    }

    @Scheduled(fixedRate = 1000)
    private void log3() {
        if (rand.nextInt(100) > 90)
            log.error("Error log : " + System.currentTimeMillis());
    }

    @Scheduled(fixedRate = 1000)
    private void log4() {
        if (rand.nextInt(100) > 80)
            log.warn("Warn log : " + System.currentTimeMillis());
    }


}
