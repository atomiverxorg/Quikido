package com.quikido.auth;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class AuthApplication {

	public static void main(String[] args) {
//		SpringApplication.run(AuthApplication.class, args);
		SpringApplication app = new SpringApplication(AuthApplication.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8083"));
        app.run(args);
	}

}
