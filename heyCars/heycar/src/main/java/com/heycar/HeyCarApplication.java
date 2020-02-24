package com.heycar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(value = "com.heycar.model")
public class HeyCarApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HeyCarApplication.class, args);
	}

}
