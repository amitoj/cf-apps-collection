package com.hanspal.cfapp5r;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
public class CfApp5rApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfApp5rApplication.class, args);
	}
}
